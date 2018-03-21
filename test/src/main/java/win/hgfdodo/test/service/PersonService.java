package win.hgfdodo.test.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import win.hgfdodo.test.domain.Person;
import win.hgfdodo.test.repository.PersonRepository;

import java.util.List;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private int i = 0;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getPersonsByNameLike(String name) {
        return personRepository.getAllByNameContaining(name);
    }

    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Transactional(readOnly = true, rollbackFor = RuntimeException.class)
    public Person findOne(Long id) {
        System.out.println(i++);
        return personRepository.findOne(id);
    }
}
