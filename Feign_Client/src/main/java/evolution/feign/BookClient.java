package evolution.feign;

import java.util.List;

import evolution.pojo.Book;
import evolution.pojo.BookResource;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface BookClient {
    @RequestLine("GET /{isbn}")
    BookResource findByIsbn(@Param("isbn") String isbn);
 
    @RequestLine("GET /find/all")
    List<BookResource> findAll();
 
    @RequestLine("POST")
    @Headers("Content-Type: application/json")
    void create(Book book);
}
