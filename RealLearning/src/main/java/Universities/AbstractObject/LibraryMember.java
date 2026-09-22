package Universities.AbstractObject;

import jakarta.persistence.Column;

//This is the Library Faculty class
public abstract class  LibraryMember  extends Person {


    @Column(name="library_card_number")
    private int libraryCardNumber;

    public LibraryMember(Long id, String name, int age, int libraryCardNumber, String email) {
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
