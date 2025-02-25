package shop.managementapplication.errorhandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INSUFFICIENT_STORAGE)
public class InsufficientStockException extends Throwable {
    public InsufficientStockException(String message) {
        super(message);
    }
}
