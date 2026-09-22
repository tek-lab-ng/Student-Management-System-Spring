package Universities.Exceptions;

public class InvalidAgeException extends RuntimeException {
    //Creating the InvalidAgeException Class to handle wrong age input
        public InvalidAgeException(String e){

            super(e);
        }

}
