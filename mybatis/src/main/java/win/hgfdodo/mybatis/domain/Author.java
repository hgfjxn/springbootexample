package win.hgfdodo.mybatis.domain;

import java.time.Instant;
import java.time.LocalDateTime;

public class Author {
    private Long id;
    private String name;
    private Integer age;
    private LocalDateTime born;

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", born=" + born +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDateTime getBorn() {
        return born;
    }

    public void setBorn(LocalDateTime born) {
        this.born = born;
    }
}
