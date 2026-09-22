package Universities;

import Universities.AbstractObject.Person;
import Universities.Exceptions.DuplicatePersonIdException;
import Universities.Exceptions.InvalidAgeException;
import Universities.Exceptions.InvalidEmailException;
import Universities.ObjectClass.Lecturer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class University {
        private final List<Person> people = new ArrayList<>();

        public void addPerson(Person person) {
            validateAge(person);
            validateEmail(person.getEmail(), person.getName());
            validateDuplicateId(person);
            people.add(person);

        }

        public Person findById(int id) {
            for (Person person : people){
                if(person.getId() == id)
                    return person;
            }
            return null;
        }

        public void displayEveryone() {
            for (Person person : people){
                person.introduce();
            }
        }

        public List<Person> getPerson(){
            return people;
        }


        public List<Person> findPerson(Predicate<Person> predicate){
            List<Person> pops = new ArrayList<>();
            for(Person person : people){
                if(predicate.test(person)){
                    pops.add(person);
                }
            }
            return pops;
        }

    private void validateAge(Person person){
        if (person.getAge() < 18) {
            throw new InvalidAgeException(person.getName() + " must be at least 18 years old.");
        }
    }
    private void validateEmail(String email, String personName){
        String verify = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

        if (!email.matches(verify)) {
            throw new InvalidEmailException(
                    "Invalid email for " + personName
            );
        }
    }
    private void validateDuplicateId(Person person){
        for (Person p : people) {
            if (p.getId() == person.getId()) {
                throw new DuplicatePersonIdException("The id enter for this person " + person.getName() + " already exist");
            }

        }
    }

    public Person findByName(String name){
            for (Person personByName : people){
                if(personByName.getName().equals(name))
                    return personByName;
            }
            return null;
    }

    public void removePerson(int id){
            people.removeIf(n -> n.getId() == id);
    }

    public void updateEmail(int id, String newEmail){

            for(Person personId : people){
                if(personId.getId() == id) {
                    validateEmail(personId.getEmail(), personId.getName());
                    personId.setEmail(newEmail);
                }
            }
    }

    public void updateCourse(int studentId, String newCourse){
            for (Person person : people){
                if(person instanceof Student && person.getId() == studentId)
                    ((Student) person).setCourse(newCourse);
            }
    }

    public List<Student> displayStudents(){
            List<Student> persons = new ArrayList<>();
           for(Person person : people){
               if (person instanceof Student)
                   persons.add((Student)person);
           }

           return persons;
    }

    public List<Lecturer> displayLecturers(){
        List<Lecturer> persons = new ArrayList<>();
        for(Person person : people){
            if (person instanceof Lecturer)
                persons.add((Lecturer) person);
        }

        return persons;
    }


}
