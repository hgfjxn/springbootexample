package win.hgfdodo.timezonemybatis.mapper;

import org.apache.ibatis.annotations.*;
import win.hgfdodo.timezonemybatis.bean.Person;

@Mapper
public interface PersonMapper {
    @Select("select * from person where person.id = #{id}")
    public Person get(@Param("id") Long id);

    @Insert("insert into person(id, name, birth) values(#{person.id}, #{person.name}, #{person.birth})")
    public void insert(@Param("person") Person person);

    @Update("update person set name=#{person.name}, birth=#{person.birth} where id=#{person.id}")
    void update(@Param("person") Person person);
}
