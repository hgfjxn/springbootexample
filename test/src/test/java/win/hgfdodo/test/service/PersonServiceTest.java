package win.hgfdodo.test.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import win.hgfdodo.test.domain.Person;
import win.hgfdodo.test.repository.PersonRepository;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class PersonServiceTest {

    private PersonService personService;

    private PersonRepository personRepository;

    private List<Person> persons = new ArrayList<>();

    @Before
    public void init() {
        personRepository = mock(PersonRepository.class);
        personService = new PersonService(personRepository);
        for (int i = 0; i < 3; i++) {
            Person person = mock(Person.class);
            persons.add(person);
        }
        when(personRepository.getAllByNameContaining("hgf")).thenReturn(persons);
        given(personRepository.save(persons.get(0))).willReturn(persons.get(0));
        given(personRepository.saveAndFlush(persons.get(0))).willReturn(persons.get(0));
        given(personRepository.findOne(persons.get(0).getId())).willReturn(persons.get(0));
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
