package uoc.tfg.cvelascofa.pageturner_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class GenreInUseException extends RuntimeException {
    public GenreInUseException(String message) {
        super(message);
    }
}
