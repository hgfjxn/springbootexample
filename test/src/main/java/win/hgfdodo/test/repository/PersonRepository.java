package win.hgfdodo.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import win.hgfdodo.test.domain.Person;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Long>, JpaRepository<Person, Long>, JpaSpecificationExecutor<Person> {
    public List<Person> getAllByNameContaining(@Param("name") String name);
}
