package win.hgfdodo.except;

import java.sql.SQLException;
import javax.naming.OperationNotSupportedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(String name) throws SQLException, OperationNotSupportedException {
        if (name == null) {
            throw new NobodyException();
        }
        throw new OperationNotSupportedException();

//        return 1/0+"";
    }

    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "conflict")
    @ExceptionHandler(ArithmeticException.class)
    public void conflict() {
        System.out.println("z");
    }

    @ExceptionHandler(SQLException.class)
    public ModelAndView exception(Exception e) {
        System.out.println("x");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", e);
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
