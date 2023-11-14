package co.udea.ssmu.api.utils.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidDiscountPercentage extends RuntimeException {
    public InvalidDiscountPercentage(String message) {
        super(message);
    }
}
