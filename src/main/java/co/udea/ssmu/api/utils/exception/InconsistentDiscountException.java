package co.udea.ssmu.api.utils.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InconsistentDiscountException extends RuntimeException{
    public InconsistentDiscountException(String message) {
        super(message);
    }
}
