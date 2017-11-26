package win.hgfdodo.test.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import win.hgfdodo.test.domain.Person;
import win.hgfdodo.test.service.PersonService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
public class PersonControllerTest {
    @MockBean
    private PersonService personService;

    /**
     * personList cannot annotated as MockBean and it cannot contains mock bean!!!
     * if so ,the personList would be null and mockMvc response will convert null to json,
     * and it will throw an exception when convert personList to json string.
     */
    private List<Person> personList = new ArrayList<>();

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void init() {
        for (int i = 0; i < 3; i++) {
            Person person = new Person();
            person.setAge(i);
            person.setName("hgf" + i);
            personList.add(person);
        }
        given(personService.getPersonsByNameLike("hgf")).willReturn(personList);
    }

    @Test
    public void getPersonByNameLike() throws Exception {
        mockMvc.perform(get("/person/find").param("name", "hgf"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

}
