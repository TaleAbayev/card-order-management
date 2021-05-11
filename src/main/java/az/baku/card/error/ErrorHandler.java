package az.baku.card.error;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.UUID;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({DataNotFoundException.class})
    public RestErrorResponse handleDataNotFoundException(DataNotFoundException ex) {
        String uuid = UUID.randomUUID().toString();
        log.error("Data not found. errorCode {} , errorMessage {}", ex.getErrorCode(), ex.getErrorMessage());
        return new RestErrorResponse(
                uuid,
                ex.getErrorCode(),
                ex.getErrorMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({OrderAlreadySubmitted.class})
    public RestErrorResponse handleOrderAlreadySubmitted(OrderAlreadySubmitted ex) {
        String uuid = UUID.randomUUID().toString();
        log.error("Order already submitted. errorCode {} , errorMessage {}", ex.getErrorCode(), ex.getErrorMessage());
        return new RestErrorResponse(
                uuid,
                ex.getErrorCode(),
                ex.getErrorMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({UserCredentialNotCorrect.class})
    public RestErrorResponse handleUserCredentialNotCorrect(UserCredentialNotCorrect ex) {
        String uuid = UUID.randomUUID().toString();
        log.error("Incorrect username or password. errorCode {} , errorMessage {}", ex.getErrorCode(), ex.getErrorMessage());
        return new RestErrorResponse(
                uuid,
                ex.getErrorCode(),
                ex.getErrorMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ExpiredJwtException.class})
    public RestErrorResponse handleExpiredJwtException() {
        String uuid = UUID.randomUUID().toString();
        log.error("Token Expired");
        return new RestErrorResponse(
                uuid,
                ErrorCodes.TOKEN_EXPIRED,
                "Token Expired");
    }

}
