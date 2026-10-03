package Universities.Exceptions;

public class ProgramNotFoundException extends ResourceNotFoundException {
    public ProgramNotFoundException(String message) {
        super(message);
    }
}
