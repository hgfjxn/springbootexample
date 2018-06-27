package win.hgfdodo.transaction.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import win.hgfdodo.transaction.bean.Person;
import win.hgfdodo.transaction.repository.PersonRepository;

import java.util.List;

@Service
public class DemoService {
    private static final Logger log = LoggerFactory.getLogger(DemoService.class);

    private final PersonRepository personRepository;

    public DemoService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Transactional
    public void init() {
        log.debug("Request to init DB");
        Person person = new Person();
        person.setId(1L);
        person.setName("hgf");
        person.setAge(1);
        update(person);

        Person person1 = new Person();
        person.setName("hgfhgf");
        person.setId(2L);
        person.setAge(10);
        update(person1);
    }

    @Transactional()
    public Person update(Person person) {
        log.debug("Request to update person {}", person);
        return personRepository.save(person);
    }

    @Transactional()
    public Person med(Person person, int age) {
        log.debug("Request to return young person {}, {}", person, age);
        person.setAge(person.getAge() - age + 1);
        return personRepository.save(person);
    }

    public Person get(Long id) {
        log.debug("Request to get {}", id);
        return personRepository.getOne(id);
    }

    public List<Person> getAll() {
        log.debug("Request to get all person");
        return personRepository.findAll();
    }


    public void callInner(Person person){
        innerRoll(person, 1);
    }

    @Transactional
    public void innerRoll(Person person, int age) {
        person.setAge(person.getAge() + age);
        this.update(person);
        throw new RuntimeException();
    }
}
