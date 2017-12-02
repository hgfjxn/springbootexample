package win.hgfdodo.test.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import win.hgfdodo.test.domain.Person;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest(showSql = true)
public class PersonRepositoryTest {
    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    private PersonRepository personRepository;

    @Before
    public void init(){
        Person person = new Person();
        person.setName("hgf");
        person.setAge(1);

        testEntityManager.persist(person);
        person = new Person();
        person.setName("hgfgood");
        person.setAge(2);

        testEntityManager.persist(person);
    }

    @Test
    public void getAllByNameLike() throws Exception {
        List<Person> personList = personRepository.getAllByNameContaining("hgf");
        System.out.println(personList);
    }

}
