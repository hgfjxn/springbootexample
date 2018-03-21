package win.hgfdodo.except;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "nobody to say hello")
public class NobodyException extends RuntimeException {

}
