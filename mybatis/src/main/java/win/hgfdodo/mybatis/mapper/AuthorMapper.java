package win.hgfdodo.mybatis.mapper;

import org.apache.ibatis.annotations.*;
import win.hgfdodo.mybatis.domain.Author;

import java.util.List;

@Mapper
public interface AuthorMapper {
    @Select("select * from author")
    List<Author> getAuthors();

    @Insert("insert into author(name, born) values (#{name},#{born})")
    public int save(Author author);
}
