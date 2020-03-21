package at.ac.tuwien.sepm.assignment.individual.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class OwnershipException extends PersistenceException {

    public OwnershipException(String message) {
        super(message);
    }

    public OwnershipException(String message, Throwable cause) {
        super(message, cause);
    }

}
