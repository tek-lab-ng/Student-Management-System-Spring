package Universities.AbstractObject;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

//This is the Library Faculty class
@MappedSuperclass
public abstract class  LibraryMember  extends Person {


    @Column(name="library_card_number")
    private Long libraryCardNumber;

    public LibraryMember(Long id, String name, int age, Long libraryCardNumber, String email) {
        super(id, name, age, email);
        this.libraryCardNumber = libraryCardNumber;
    }

    public LibraryMember(String name, int age, Long libraryCardNumber, String email){
        super(name,age, email);
        this.libraryCardNumber = libraryCardNumber;
    }

    public LibraryMember() {
        super();
    }

    public Long getLibraryCardNumber() {
        return libraryCardNumber;
    }

    public void setLibraryCardNumber(Long libraryCardNumber) {
        this.libraryCardNumber = libraryCardNumber;
    }
}
