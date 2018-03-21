package win.hgfdodo.except;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ResponseStatus(value = HttpStatus.OK, reason = "conflict -- by controller advice")
    @ExceptionHandler(ArithmeticException.class)
    public void hasConflict() {
    }

    @ResponseStatus(value = HttpStatus.ACCEPTED, reason = "default exception -- by controller advice")
    @ExceptionHandler(Exception.class)
    public void defaultException(Exception e) throws Exception {

        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }
        else{
            //do with ModelAndView
        }
    }

}
