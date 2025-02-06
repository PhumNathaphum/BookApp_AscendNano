/////////////////////////////////////////////////

setup database MySQL

Run these sql lines :

CREATE DATABASE book_database;
USE book_database;
CREATE TABLE Book (id int NOT NULL AUTO_INCREMENT, title varchar(255), author varchar(255), publishedDate date, PRIMARY KEY (id));
CREATE USER bookadmin IDENTIFIED BY 'book2025';
GRANT ALL PRIVILEGES ON book_database.Book TO bookadmin;

Use default port 3306
/////////////////////////////////////////////////

Instructions for running the server

1.git clone https://github.com/PhumNathaphum/BookApp_AscendNano.git
2.cd [repository]
3.Check java version (17.0.10)
4.Check Maven version (Apache Maven 3.8.8)
5.mvn clean install
6.mvn spring-boot:run
//////////////////////////////////////////////////

Integration tests

//////////////////////////////////////////////////

Example API

Request : GET /books?author=JohnDoe

Respond :
{
    "status": "200",
    "desc": "Search success",
    "data": [
        {
            "id": 5,
            "title": "book#1",
            "author": "JohnDoe",
            "publishedDate": "2024-09-19"
        },
        {
            "id": 6,
            "title": "book#2",
            "author": "JohnDoe",
            "publishedDate": "1999-09-19"
        }
    ],
    "book": null
}

-------------------------------------

Request : POST /books
{
    "title" : "book#3",
    "author" : "JohnDoe",
    "publishedDate" : "2018-02-02" 
}

Respond :
{
    "status": "201",
    "desc": "Create success",
    "data": null,
    "book": {
        "id": 7,
        "title": "book#3",
        "author": "JohnDoe",
        "publishedDate": "2018-02-02"
    }
}

---------------------------------------

Request : POST /books
{
    "title" : "book#4",
    "author" : "",
    "publishedDate" : "2019-03-02" 
}

Respond :
{
    "path": "/books",
    "error": "Bad Request",
    "message": "Validation failed",
    "errors": {
        "author": "author must not be empty"
    },
    "status": 400
}

----------------------------------------

Request : POST /books
{
    "title" : "",
    "author" : "John Doe",
    "publishedDate" : "2019-03-02" 
}

Respond :
{
    "path": "/books",
    "error": "Bad Request",
    "message": "Validation failed",
    "errors": {
        "title": "title must not be empty"
    },
    "status": 400
}

-------------------------------------

Request : POST /books
{
    "title" : "book#5",
    "author" : "John Doe",
    "publishedDate" : "3000-03-02" 
}

Respond :
{
    "status": "400",
    "desc": "Error : publishedDate is invalid",
    "data": null,
    "book": null
}
