package evolution;

import java.util.List;

import org.junit.Test;

import evolution.feign.BookClient;
import evolution.pojo.Book;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;

public class FeignTest {
	BookClient bookClient = Feign.builder()
			  .client(new OkHttpClient())
			  .encoder(new GsonEncoder())
			  .decoder(new GsonDecoder())
			  .logger(new Slf4jLogger(BookClient.class))
			  .logLevel(Logger.Level.FULL)
			  .target(BookClient.class, "http://localhost:8081/api/books");
	
	@Test
	public void testFindAll() throws Exception {
	   List<Book> books = bookClient.findAll();
	   System.out.println(String.format("The books are %s.", books));
	}
	
	@Test
	public void testFindAlld() throws Exception {
	   Book book = new Book();
	   book.setAuthor("Chen");
	   bookClient.create(book);
	}
	
	@Test
	public void testFindByIsbn() throws Exception {
	   bookClient.findByIsbn("001");
	}
}
