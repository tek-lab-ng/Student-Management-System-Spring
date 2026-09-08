package Universities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class UniversityFileProcessor {

    public static List<String> findFiles(Path directory, String extension){
        try {
            List<String> outcome = Files.walk(directory).filter(Files::isRegularFile).filter(path -> path.toString().endsWith(extension)).map(Path::toString).toList();
            return outcome;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static List<String> readFile(Path file){
        List<String> outcome = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file.toFile()))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.trim().isEmpty()) { // optional: skips empty lines
                    outcome.add(line);
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e){
            System.out.println("error reading from file");
        }

        return outcome;

    }

    //Student(int id, String name, int age, String course, String libraryCardNumber, String email)
    public static Student convertToStudent(String record){
       String[] inputParam = record.split(",");

        Student st = new Student(parseOrThrow(inputParam[0]),
                inputParam[1], parseOrThrow(inputParam[2]),
                inputParam[3], parseOrThrow(inputParam[4]), inputParam[5], parseOrThrow(inputParam[6]));

        return st;
    }

    public static List<Student> convertStudents(List<String> records){
//        return records.stream().map(UniversityFileProcessor::convertToStudent).toList();
            List<Student> student = new ArrayList<>();
            for(String each : records){

                try{
                    Student st = UniversityFileProcessor.convertToStudent(each);
                    student.add(st);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            return student;
    }

    public static List<Student> findStudentsByCourse(List<Student> students, String course){
       return students.stream().filter(n->n.getCourse().equals(course)).toList();
    }

    public static int parseOrThrow(String input) {

           if (input == null || !input.matches("-?\\d+")) {
               throw new NumberFormatException("Invalid student record: " + input);
           }
           return Integer.parseInt(input);

    }

}


