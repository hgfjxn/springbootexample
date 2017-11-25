package win.hgfdodo.test.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import win.hgfdodo.test.TestApplication;
import win.hgfdodo.test.domain.Person;
import win.hgfdodo.test.repository.PersonRepository;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
@Transactional
public class PersonServiceTestWithoutMock {

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;

    private List<Person> persons = new ArrayList<>();

    @Before
    public void init() {
        for (int i = 0; i < 3; i++) {
            Person person = new Person();
            person.setName("hgf"+i);
            person.setAge(i);
            persons.add(personRepository.save(person));
        }
    }

    @Test
    public void getPersonsByNameLike() throws Exception {
        List<Person> personList = personService.getPersonsByNameLike("hgf");
        assertThat(personList.size()).isEqualTo(3);
    }

    @Test
    public void testSaveAndFind() {
        Person t = personService.save(persons.get(0));
        assertThat(t).isEqualTo(persons.get(0));

        t = personService.findOne(t.getId());
        assertThat(t).isEqualTo(persons.get(0));
    }

}
