package win.hgfdodo.timezonemybatis.service;

import org.springframework.stereotype.Service;
import win.hgfdodo.timezonemybatis.bean.Person;
import win.hgfdodo.timezonemybatis.mapper.PersonMapper;

import java.util.Date;

@Service
public class PersonService {

    private final PersonMapper personMapper;

    public PersonService(PersonMapper personMapper) {
        this.personMapper = personMapper;
    }

    public void init(){
        Person person = new Person(2L,"hgf", new Date());
        Person tmp = personMapper.get(2L);
        if(tmp!=null){
            personMapper.update(person);
        }else {
            personMapper.insert(person);
        }
    }

    public Date getBirth(){
        Person person = personMapper.get(1L);
        return person.getBirth();
    }
}
