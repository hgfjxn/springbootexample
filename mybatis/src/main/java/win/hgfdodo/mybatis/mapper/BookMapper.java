package win.hgfdodo.mybatis.mapper;

import org.apache.ibatis.annotations.*;
import win.hgfdodo.mybatis.domain.Book;

import java.util.List;
import java.util.Map;

@Mapper
public interface BookMapper {
    @Select("select * from book")
    public List<Book> getBooks();

    @Select("select book.id as bid,author.id as aid, book.name as bname, author.name as aname,publish_time as publish_time from book left join author on book.author_id=author.id  where author_id = #{author_id}")
    @ResultType(Map.class)
    public List<Map<String, Object>> getBookWithMap(@Param("author_id") Long author_id);


    @Insert("insert into book(name, pages,author_id, publish_time) values(#{name},#{pages},#{authorId},#{publishTime})")
    public int save(Book book);
}
