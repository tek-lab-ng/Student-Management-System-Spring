package Universities;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Revised {

    public static void main(String[] args) throws InterruptedException {


//        ArrayList<String> student = new ArrayList<String>();
//        student.add("Gabriel");
//        student.add("Janet");
//        student.add("Abel");
//
//        String[] caps = {"Joseph", "Caleb"};
//
//       System.out.println( Collections.addAll(student, caps ));
//       //System.out.println(student);
//       Collections.sort(student);
//       System.out.println(student);
//
//       //system.out.println(student);
//
//        List<String> teacher = new ArrayList<String>();
//        teacher.add("Kemi");
//        teacher.add("Fred");
//        teacher.add("kemi");
//        teacher.add("Gabriel");
//        System.out.println(teacher);
//        Collections.sort(teacher);
//
//        List<String> t = Collections.synchronizedList(teacher);
//
//        String carrot = "flowed";
//       boolean value = carrot.matches("\\D+");
//       System.out.println(value);
//
//       List capetown = new ArrayList<>();
//        capetown.add("Janet");
//        capetown.add("Gabriel");
//        capetown.add("Janet");
//        capetown.add("Sandra");
//
//        Collections.sort(capetown);
//
//        System.out.println(capetown);



       //new Revised().checkings(caps);

//        Person p1 = new Person(10, "Gabriel", 20);
//        Person p2 = new Person(10, "Fidelis", 25);
//
//
//        Map<Integer, Person> Class = new HashMap<>();
//        Class.put(1, p1);
//        Class.put(2, p2);
//
//        //System.out.println(Class.entrySet());
//
//        Set<Map.Entry<Integer, Person >> EntrySet = Class.entrySet();
//
//        for(Map.Entry<Integer, Person> outings : EntrySet) {
//            System.out.println(outings.getKey() );
//            System.out.println(outings.getValue().getName());
//        }
        //Practised the Pattern and Match feature to verify an input
//        String verify = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
//        String email = "gabrieljames85gmail";
//
//        if((email.matches(verify))){
//            System.out.println("Very correct information");
//        } else {
//            System.out.println("This is false");
//        }

        // Here practising the collectors feature within stream

        University people = new University();

        Student s1 = new Student(1, "Gabriel", 25, "Computer Science", 010, "gabrieljames85@gmail.com", 0 );

        Student s2 = new Student(2, "Jayden", 21, "Chemistry", 011, "jayden@gmail.com", 0 );

        Lecturer l1 = new Lecturer(3, "Fred", 40, "Computer Science", 012, "fred@gmail.com", 10000);
        Lecturer l2 = new Lecturer(4, "Lucy", 45, "Physics", 012, "Lucy@gmail.com", 20000);

        Security sec1 = new Security(5, "Mike", 28, "Mike@gmail.com", 1, 200000);
        Security sec2 = new Security(6, "Ben", 29, "Ben@gmail.com", 2, 20000);


        Janitor jan1 = new Janitor(7, "Kemi", 30, "Kemi@gmail.com", "First Floor", 150000);
        Janitor jan2 = new Janitor(8, "Aunty", 28, "Aunty@gmail.com", "Second Floor", 150000);

        people.addPerson(s1);
        people.addPerson(s2);
        people.addPerson(l1);
        people.addPerson(l2);
        people.addPerson(sec1);
        people.addPerson(sec2);
        people.addPerson(jan1);
        people.addPerson(jan2);

        //List of Persons from the University
        List<Person> persons = people.getPerson();
        int numberOfPersons = persons.size();
        System.out.println(numberOfPersons);

        //Collectors starts.

        System.out.println("This is the outcome of collectors for above 20 years" + persons.stream()
                .filter(person -> person.getAge() > 20)
                .map(n->n.getName())
                .collect(Collectors.toList()));

        System.out.println(persons.stream()
                .map(Person::getName)
                .collect(Collectors.toCollection(ArrayList::new)).toString());

       System.out.println( persons.stream().filter(n -> n instanceof Student)
                .collect(Collectors.toMap(Person::getName, Person::getEmail, (n, k) -> n)));


        System.out.println(persons.stream().filter(n -> n.getAge() > 25)
                .collect(Collectors.groupingBy(Person::getName, Collectors.collectingAndThen(Collectors.counting(), f -> f.toString()))));

        System.out.println(persons.stream().filter(n -> n.getAge() > 25)
                .collect(Collectors.groupingBy(Person::getName, Collectors.toCollection(ArrayList::new))));

        System.out.println(persons.stream().filter(n -> n.getAge() > 25)
                .collect(Collectors.groupingBy(Person::getName, Collectors.mapping(Person::getAge, Collectors.toList()))));

        //Checking time
        LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd:mm:yy hh:mm:ss");

        System.out.println("The time and date is: " + current.format(format));

        ZonedDateTime myTime = ZonedDateTime.now();
        ZoneId ghana = ZoneId.of("Asia/Tokyo");

        ZonedDateTime localghanaTime = myTime.withZoneSameInstant(ghana);

        System.out.println("The current time in ghana is: " + localghanaTime);


        //Multithreadingr

        //Thread with the Thread class
        BookStore bk1 = new BookStore("Alvin and Shimpmonk", 100);
        BookStore bk2 = new BookStore("Micky mouse", 85);
        BookStore bk3 = new BookStore("Harry Potter", 150);
        BookStore bk4 = new BookStore("Spoon and plate", 250);

        bk1.start();
        bk2.start();
        bk3.start();
        bk4.start();

        System.out.println(" ");
        //Thread with Runnable implemented class
        Thread seller1 = new Thread(new BookSellers("Femi", 1));
        Thread seller2 = new Thread(new BookSellers("David", 2));
        Thread seller3 = new Thread(new BookSellers("Joseph", 3));
        Thread seller4 = new Thread(new BookSellers("Chioma", 4));

        seller1.start();
        seller2.start();
        Thread.sleep(1000);
        seller3.start();
        seller4.start();

        //used to temporarily create threads that be used to run some programs
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(() -> {System.out.println(2);});
        executor.shutdown();

        //Synchronization is used to control the execution of multiple processes or threads so that shared resources are accessed in a proper and orderly manner.

        BookSellers k = new BookSellers("Kemi", 5);
        Thread checkSync = new Thread(() -> {
                    for(int i = 0; i < 1000; i++)
                        k.getSellerName();

        });

        Thread checkeys = new Thread(() -> {
            for(int i = 0; i < 1000; i++)
                k.getSellerName();

        });

        checkSync.start();
        checkeys.start();

        try{
            checkeys.join();
            checkeys.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("The output of the counter is: " + k.counter());




        



    }

    public void checkings(String ...n){
        System.out.println(Arrays.toString(n));
    }

    public String calculate(int k, int A, String ops){
        try {
            if (ops.equals("**")) {
                throw new illegalOperationException("you have entered a wrong operation for maths ");
            }
            return null;
        } catch (illegalOperationException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


}

class illegalOperationException extends Exception {
    illegalOperationException(String e){
        super(e);
    }
}

class BookStore extends Thread{

    String name;
    int pages;

    BookStore(String name, int pages){
        this.name = name;
        this.pages = pages;
    }

    public String getBookName() {
        return name;
    }

    public void setBookName(String name) {
        this.name = name;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    @Override
    public void run() {
        System.out.println("The book currently running is: " + getBookName() + " " + Thread.currentThread().getName());
    }
}


class BookSellers extends Thread{
    String name;
    int number;
    int c;
    BookSellers(String name, int number){
        this.name = name;
        this.number = number;

    }

    public String getSellerName() {
        c++;
        return name;
    }

    public void setSellerName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public synchronized int counter(){
        return c;
    }

    @Override
    public void run() {
        System.out.println("This class implements runnable with sellers: " + getSellerName() + " " + Thread.currentThread().getName());
    }
}

