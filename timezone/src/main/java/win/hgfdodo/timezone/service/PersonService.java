package win.hgfdodo.timezone.service;

import org.springframework.stereotype.Service;
import win.hgfdodo.timezone.bean.Person;
import win.hgfdodo.timezone.repository.PersonRepository;

import java.util.Date;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void init(){
        Person person = new Person(1L,"hgf", new Date());
        personRepository.save(person);
    }

    public Date getBirth(){
        Person person = personRepository.getOne(1L);
        return person.getBirth();
    }
}
