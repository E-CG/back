package co.udea.ssmu.api.utils.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidCouponAmount extends RuntimeException {
    public InvalidCouponAmount(String message) {
        super(message);
    }
}
