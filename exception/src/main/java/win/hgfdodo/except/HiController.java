package win.hgfdodo.except;

import java.sql.SQLException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {

    @GetMapping("/hi")
    public String hello(String name) throws SQLException {
        if (name == null) {
            throw new NobodyException();
        }
        throw new SQLException();
//        return 1/0+"";
    }

}
