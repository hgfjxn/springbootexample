package win.hgfdodo.transaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import win.hgfdodo.transaction.bean.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {
}
