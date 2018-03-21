package win.hgfdodo.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import win.hgfdodo.test.domain.Person;
import win.hgfdodo.test.service.PersonService;

import java.util.List;

@Controller
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping(value = "/person/find", produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<Person> getPersonByNameLike(@Param("name") String name) {
        return personService.getPersonsByNameLike(name);
    }

    @GetMapping(value = "/person/findone", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Person findOne(@Param("id") Long id) {
        return personService.findOne(id);
    }

    @PostMapping("/person")
    public ResponseEntity<Person> addPerson(Person person){
        return new ResponseEntity<Person>(personService.save(person),HttpStatus.OK);
    }
}
