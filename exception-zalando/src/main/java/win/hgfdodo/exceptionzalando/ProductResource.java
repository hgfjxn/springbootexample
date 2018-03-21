package win.hgfdodo.exceptionzalando;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import javax.naming.OperationNotSupportedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
class ProductsResource {

    @RequestMapping(method = GET, value = "/{productId}", produces = APPLICATION_JSON_VALUE)
    public Product getProduct(String productId) {
        // TODO implement
        return null;
    }

    @ResponseStatus(value = HttpStatus.ACCEPTED, reason = "XXXXX")
    @RequestMapping(method = PUT, value = "/{productId}", consumes = APPLICATION_JSON_VALUE)
    public void updateProduct(String productId, Product product)
        throws OperationNotSupportedException {
        // TODO implement
        throw new OperationNotSupportedException();
    }

}
