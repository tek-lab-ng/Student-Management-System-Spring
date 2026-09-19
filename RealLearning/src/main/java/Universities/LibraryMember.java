package Universities;

//This is the Library Faculty class
abstract class  LibraryMember  extends Person{

    private int libraryCardNumber;

    public LibraryMember(int id, String name, int age, int libraryCardNumber, String email) {
        super(id, name, age, email);
        this.libraryCardNumber = libraryCardNumber;
    }

    public LibraryMember(String name, int age, int libraryCardNumber, String email){
        super(name,age, email);
        this.libraryCardNumber = libraryCardNumber;
    }

    public LibraryMember() {
        super();
    }

    public int getLibraryCardNumber() {
        return libraryCardNumber;
    }

    public void setLibraryCardNumber(int libraryCardNumber) {
        this.libraryCardNumber = libraryCardNumber;
    }
}
