package evolution.feign;

import java.util.List;

import evolution.pojo.Book;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface BookClient {
    @RequestLine("GET find/by/{isbn}")
    Book findByIsbn(@Param("isbn") String isbn);
 
    @RequestLine("GET /find/all")
    List<Book> findAll();
 
    @RequestLine("POST /create")
    @Headers("Content-Type: application/json")// The syntax is very strict, it should be exactly "Content-Type: application/json"
    void create(Book book);
}
