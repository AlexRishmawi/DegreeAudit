
import java.util.ArrayList;
import java.util.UUID;

public class Advisor extends User implements editableUser {
    private ArrayList<Student> studentList;
    private Student currentStudent;

    public Advisor(String firstName, String lastName, String email, String password, ArrayList<Student> studentList){
        super(firstName, lastName, email, password);
        this.studentList = studentList;
    }

    public Advisor(UUID id, String firstName, String lastName, String email, String password, ArrayList<Student> studentList) {
        super(id, firstName, lastName, email, password);
        this.studentList = studentList;
    }
    
    public void setCurrentStudent(String id){

    }

    public void addProgram(Program program){
        
    }

    public void removeProgram(Program program){

    }

    public void addNotes(String note){
        
    }
}
