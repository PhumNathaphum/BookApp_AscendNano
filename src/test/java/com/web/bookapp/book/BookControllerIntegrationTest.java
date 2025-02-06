package com.web.bookapp.book;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateBook() throws Exception {
        String bookJson = "{\"title\": \"Book#1\", \"author\": \"Peter\", \"publishedDate\": \"2020-01-08\"}";

        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value(201))
                .andExpect(jsonPath("$.desc").value("Create success"))
                .andExpect(jsonPath("$.book.title").value("Book#1"))
                .andExpect(jsonPath("$.book.author").value("Peter"))
                .andExpect(jsonPath("$.book.publishedDate").value("2020-01-08"));
    }
    
    @Test
    void shouldErrorAuthorEmpty() throws Exception {
        String bookJson = "{\"title\": \"Book#2\", \"author\": \"\", \"publishedDate\": \"2020-01-08\"}";

        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Bad Request"))
                .andExpect(jsonPath("$.path").value("/books"))
                .andExpect(jsonPath("$.message").value("Validation failed"))
                .andExpect(jsonPath("$.errors.author").value("author must not be empty"));
    }
    
    @Test
    void shouldErrorTitleEmpty() throws Exception {
        String bookJson = "{\"title\": \"\", \"author\": \"Peter\", \"publishedDate\": \"2020-01-08\"}";

        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Bad Request"))
                .andExpect(jsonPath("$.path").value("/books"))
                .andExpect(jsonPath("$.message").value("Validation failed"))
                .andExpect(jsonPath("$.errors.title").value("title must not be empty"));
    }
    
    @Test
    void shouldCreateBookwithBuddhist() throws Exception {
        String bookJson = "{\"title\": \"Book#2\", \"author\": \"Peter\", \"publishedDate\": \"2568-01-09\"}";

        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value(201))
                .andExpect(jsonPath("$.desc").value("Create success"))
                .andExpect(jsonPath("$.book.title").value("Book#2"))
                .andExpect(jsonPath("$.book.author").value("Peter"))
                .andExpect(jsonPath("$.book.publishedDate").value("2025-01-09"));
    }
    
    @Test
    void shouldErrorDate() throws Exception {
        String bookJson = "{\"title\": \"Book#3\", \"author\": \"Peter\", \"publishedDate\": \"3000-01-09\"}";

        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.desc").value("Error : publishedDate is invalid"));
    }
    
    @Test
    void shouldErrorDate2() throws Exception {
        String bookJson = "{\"title\": \"Book#3\", \"author\": \"Peter\", \"publishedDate\": \"0500-01-09\"}";

        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.desc").value("Error : publishedDate is invalid"));
    }

    @Test
    void shouldReturnBookByAuthor() throws Exception {
    	String bookJson = "{\"title\": \"Book#3\", \"author\": \"Jane\", \"publishedDate\": \"2000-01-09\"}";
    	 mockMvc.perform(post("/books")
                 .contentType(MediaType.APPLICATION_JSON)
                 .content(bookJson));
    	
        String author = "Peter";

        mockMvc.perform(get("/books?author=" + author))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.desc").value("Search success"))
                .andExpect(jsonPath("$.data.length()").value(2))
                .andExpect(jsonPath("$.data[*].author",Matchers.everyItem(Matchers.equalTo(author))));
    }
    
    @Test
    void shouldNotFoundBook() throws Exception {
        String author = "Steve";

        mockMvc.perform(get("/books?author=" + author))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.desc").value("Search success"))
                .andExpect(jsonPath("$.data.length()").value(0))
                .andExpect(jsonPath("$.data[*].author",Matchers.everyItem(Matchers.equalTo(author))));
    }

}