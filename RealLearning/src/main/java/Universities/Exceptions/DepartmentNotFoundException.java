package Universities.Exceptions;

public class DepartmentNotFoundException extends ResourceNotFoundException{
    public DepartmentNotFoundException(String message) {
        super(message);
    }
}
