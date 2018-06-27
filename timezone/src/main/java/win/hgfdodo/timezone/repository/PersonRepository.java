package win.hgfdodo.timezone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import win.hgfdodo.timezone.bean.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
