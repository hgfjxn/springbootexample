package win.hgfdodo.timezone.bean;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
public class Person{

    @Id
    private Long id;

    private String name;

    @Fetch(FetchMode.JOIN)
    private Date birth;

    public Person() {
    }

    public Person(Long id, String name, Date birth) {
        this.id = id;
        this.name = name;
        this.birth = birth;
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

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", birth=" + getBirth() +
                '}';
    }
}