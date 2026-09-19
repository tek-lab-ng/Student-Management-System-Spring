package Universities;

/**
import Employees.Employee;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MyApp {

    private  static final StudentService studentService = new StudentService();

    public static void main(String[] args) throws SQLException {

        /**
        University people = new University();

        Student s1 = new Student(1, "Gabriel", 25, "Computer Science", 010, "gabrieljames85@gmail.com", 0 );

        Student s2 = new Student(2, "Jayden", 21, "Chemistry", 011, "jayden@gmail.com", 0 );

        Lecturer l1 = new Lecturer(3, "Fred", 40, "Computer Science", 012, "fred@gmail.com", 700000);
        Lecturer l2 = new Lecturer(4, "Lucy", 45, "Physics", 012, "Lucy@gmail.com", 700000);

        Security sec1 = new Security(5, "Mike", 28, "Mike@gmail.com", 1, 200000);
        Security sec2 = new Security(6, "Ben", 29, "Ben@gmail.com", 2, 200000);


        Janitor jan1 = new Janitor(7, "Kemi", 30, "Kemi@gmail.com", "First Floor", 15000);
        Janitor jan2 = new Janitor(8, "Aunty", 28, "Aunty@gmail.com", "Second Floor", 150000);

        try {
            people.addPerson(s1);
            people.addPerson(s2);
            people.addPerson(l1);
            people.addPerson(l2);
            people.addPerson(sec1);
            people.addPerson(sec2);
            people.addPerson(jan1);
            people.addPerson(jan2);
        } catch (InvalidAgeException | InvalidEmailException | DuplicatePersonIdException e){
            System.out.println(e.getMessage());
        }

        //people.displayEveryone();
        //challenge 1
            people.getPerson().forEach(Person::introduce);
        // people.getPerson().stream().forEach(Person::introduce);

        //challenge 2
        List<Person> personAdult = people.getPerson().stream().filter(n -> n.getAge() > 18).toList();
        //challenge 3

        List<Lecturer> lecturers = people.getPerson().stream().filter(person -> person instanceof Lecturer).map(person-> (Lecturer) person).toList();
        //To get the department of lecturers
        lecturers.forEach(lect -> System.out.println(lect.getDepartment()));
        //people.getPerson().stream().filter(person -> person.)
        //Challenge 4
        List<String> output = people.getPerson().stream().map(Person::getEmail).toList();
        Predicate<Person> predicate = (Person p) -> p.getName().equals("Gabriel");

        List<Person> results = people.findPerson(predicate);

        //results.forEach(n -> System.out.println(n.getName()));


        List<Person> everyOne = people.getPerson();

        Collections.sort(everyOne, (n1, n2) -> Integer.compare(n1.getAge(), n2.getAge()));

        everyOne.forEach(n-> System.out.println(n.getName()));

//       int numberOfPerson = people.getPerson().size();
//       System.out.println(numberOfPerson);
        // Finding person by id
        Person person =  people.findByName("Gabriel");
        System.out.println("Using the find by name feature " + person.getEmail() + " " + person.getName() + " " + person.getAge());
        people.removePerson(1);
        System.out.println(people.getPerson().stream().map(n->n.getName()).toList());

        System.out.println("This are the lecturers in the university " + people.displayLecturers().stream().map(Person::getName).toList());

        //Implemented the Interface Payable and filtered the class that implemented it
        Payable payable = l1;
        System.out.println("The salary of this lecture l1 is: " + payable.calculatePay());


        System.out.println("Checking the list of the payables");
        List<Payable> payables = new ArrayList<>(people.getPerson().stream().filter(persons -> persons instanceof Payable).map(persons -> (Payable) persons).toList());
//        payables.addAll(people.getPerson().stream().filter(persons -> persons instanceof Janitor).map(persons-> (Janitor) persons).toList());
//        payables.addAll(people.getPerson().stream().filter(persons -> persons instanceof Security).map(persons-> (Security) persons).toList());

        Map<String, Double> allPay = payables.stream().collect(Collectors.groupingBy(n-> n.getClass().getSimpleName(), Collectors.summingDouble(Payable::calculatePay)));

        //Printed out the class that implemented payable and the total money in the class calculated...
        allPay.forEach((ClassName, TotalValue) -> {
            System.out.println("My Class: " + ClassName + "->" + " TotalValue  " + TotalValue);
        });

        Path filepath = Paths.get("C:\\Users\\Gabriel.Osaji\\Documents\\Public Class A\\RealLearning\\src\\main\\java\\Universities");
        try{

            System.out.println("  Challenge 1");
            //challenge 1
            Files.walk(filepath).forEach(System.out::println);

            System.out.println("  Challenge 2");

            //challenge 2
            Files.walk(filepath).filter(Files::isRegularFile).forEach(path -> System.out.println("The paths that are regularFiles are" + path));

            System.out.println("  Challenge 3");
            //challenge 3
            System.out.println("The list files ending with .txt");
            Files.walk(filepath).filter(n->n.toString().endsWith(".txt")).forEach(System.out::println);

            //challenge 4
            System.out.println("The list files ending with .txt only in the Parent folder not child folder");
            Files.walk(filepath, 1).filter(n->n.toString().endsWith(".txt")).forEach(System.out::println);

            //challenge 5 and 6
            List<String> filenames = Files.walk(filepath).filter(Files::isRegularFile).map(path -> path.getFileName().toString()).toList();
           System.out.println(filenames);

        }catch (IOException e){
            throw new RuntimeException();
        }

        System.out.println("The outcome of the university text files");
        System.out.println(UniversityFileProcessor.findFiles(filepath, ".txt"));

        System.out.println("The list outcome of reading from a single file");
        Path filePath1 = Paths.get("C:\\Users\\Gabriel.Osaji\\Documents\\Public Class A\\RealLearning\\src\\main\\java\\Universities\\Archive\\oldStudent.txt");
        System.out.println(UniversityFileProcessor.readFile(filePath1));

//
//        System.out.println(UniversityFileProcessor.convertToStudent("1,Gabriel,25,Computer Science,010,gabriel@gmail.com").getEmail());
        List<Student> result;

            //Passing all the list of students from the file
            result = new ArrayList<>(UniversityFileProcessor.convertStudents(UniversityFileProcessor.readFile(filePath1)));

            //List all eligible or valid student entry from the list
            result.forEach(n->System.out.println("Valid Students: " + n.getName()));

            //This to println the name of student currently studying computer science
            String compStudent = UniversityFileProcessor.findStudentsByCourse(result, "Computer Science").stream().map(Person::getName).toString();
//                    .forEach(n -> System.out.println(n.getName()));


            //Assigning grade value to each of the student
            result.forEach(n -> Lecturer.assignGrade(n));
            Predicate<Student> predicate1 = (Student s) -> Integer.parseInt(s.getGrade()) >= 50;

            //Filter student with grades above 50
            List<String> withGradeAbove = result.stream().filter(predicate1).map(st -> st.getName()).toList();

            System.out.println("The list of student with grade above 50 are: " + withGradeAbove);

            // Returning the higest score student from the list
            result.sort((S1, S2) -> Integer.compare(Integer.parseInt(S2.getGrade()), Integer.parseInt(S1.getGrade())));

            System.out.println("The student with the highest grade would be: " + result.get(0).getName());

            // Same highest score returning student
            Optional<Student> highest = result.stream().max(Comparator.comparing(student -> Integer.parseInt(student.getGrade())));
            System.out.println("The student with the highest grade is: " + highest.get().getName());

            //Student with the lowest score
            Optional<Student> lowest = result.stream().min(Comparator.comparing(student -> Integer.parseInt(student.getGrade())));

            // Output of all student from highest to lowest
            List<String> outcome = result.stream().filter(predicate1).sorted((S1, S2) -> Integer.compare(Integer.parseInt(S2.getGrade()), Integer.parseInt(S1.getGrade()))).map(Student::getName).toList();

            System.out.println("The list of student from highest to lowest is: " + outcome);

            System.out.println(
                    "========== UNIVERSITY REPORT ========== \n" +
                    "Total Students: " + result.size() + "    \n" +

                    "Passed Students: " +  withGradeAbove.size() + " \n" +
                    "Failed Students: " +  (result.size() - withGradeAbove.size()) + " \n" +

                    "Highest Grade Student: "  + highest.get().getName() + "  \n" +
                    "Highest Grade:  " + highest.get().getGrade() + "  \n" +

                    "Lowest Grade Student: "  + lowest.get().getName() + " \n" +
                    "Lowest Grade:  " + lowest.get().getGrade() + "  \n" +

                    "Computer Science Students:  " + compStudent +  " \n" +

                    "========================================"
            );




        Student st = new Student("Joachim", 40, "Computer Science",107,"joachim@gmail.com", 75);

        studentService.addStudent(st);
       studentService.getAllStudents().forEach(n -> System.out.println(n));
        System.out.println(" ");

        System.out.println(studentService.getStudentById(9));

        //System.out.println(" ");

        //  studentService.deleteStudent(9);


        List<Student> studentByMinGrade = studentService.getStudentsByMinimumGrade(70);
        if(!studentByMinGrade.isEmpty()) {
            for (Student student : studentByMinGrade) {
                System.out.println(student);
            }
        } else
            System.out.println("No student found for this course!!!");


//         Student st = studentService.getStudentWithHighestScore();
//         System.out.println(st);

        Student st1 = new Student(99, "sweet", 23, "Biochemistry", 1205, "Vero@gmail.com", 90);
//       studentService.addStudentAndUpdateGrade(st1, 30, 92);
//
//       boolean value = StudentDao.addStudent(con, st1) && StudentDao.updateGrade(con, 99, 100);
//        System.out.println("Print " + value);
//        studentService.updateBothStudent(1, 100, 30, 100);
        studentService.updateThreeStudent(1, 98, 2, 97, 35, 96);



    }
 This point tested that we are able to use the student service is working and student is supplying the required details..

    public void displayStudentById(int id){
        Student student = studentService.getStudentById(id);

        if(student != null)
            System.out.println(student);
        else
            System.out.println("No Student found with that ID");
    }

    public void createNewStudent(Student st){
        studentService.addStudent(st);
    }


}
 **/