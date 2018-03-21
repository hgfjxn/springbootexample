package win.hgfdodo.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import win.hgfdodo.mybatis.domain.Author;
import win.hgfdodo.mybatis.domain.Book;
import win.hgfdodo.mybatis.mapper.AuthorMapper;
import win.hgfdodo.mybatis.mapper.BookMapper;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@SpringBootApplication
@MapperScan("win.hgfdodo.mybatis.mapper")
public class MybatisApplication implements CommandLineRunner {

    private final BookMapper bookMapper;
    private final AuthorMapper authorMapper;

    public MybatisApplication(BookMapper bookMapper, AuthorMapper authorMapper) {
        this.bookMapper = bookMapper;
        this.authorMapper = authorMapper;
    }

    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        System.out.println(bookMapper.getBooks());
        System.out.println(authorMapper.getAuthors());
        System.out.println(bookMapper.getBookWithMap(1L));

        System.out.println("-------------------------------");
        Book book = new Book();
        book.setAuthorId(1L);
        book.setName("insert");
        book.setPages(1111);
        book.setPublishTime(Instant.now());
        bookMapper.save(book);
        System.out.println(bookMapper.getBooks());
        Author author = new Author();
        LocalDateTime localDateTime = LocalDateTime.parse("2017-11-18T12:11:23");
        System.out.println(ZoneId.systemDefault());
        System.out.println(localDateTime.atZone(ZoneId.systemDefault()));
        System.out.println(localDateTime);
        author.setBorn(localDateTime);
        author.setName("ii");
        authorMapper.save(author);
        System.out.println(authorMapper.getAuthors());
    }
}
