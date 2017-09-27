package evolution;

import static org.junit.Assert.*;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import evolution.feign.BookClient;
import evolution.pojo.Book;
import evolution.pojo.BookResource;
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
	public void givenBookClient_shouldRunSuccessfully() throws Exception {
	   List<Book> books = bookClient.findAll().stream()
	     .map(BookResource::getBook)
	     .collect(Collectors.toList());
	}
}
