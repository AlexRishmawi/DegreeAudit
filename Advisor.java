
import java.util.ArrayList;
import java.util.UUID;

public class Advisor extends User{
    private ArrayList<Student> studentList;
    private Student currentStudent;
    private Boolean isAdmin;

    public Advisor(String firstName, String lastName, String email, String password, Boolean isAdmin) {
        super(firstName, lastName, email, password);
        setAdmin(isAdmin);
    }

    public Advisor(String firstName, String lastName, String email, String password, ArrayList<Student> studentList, Boolean isAdmin){
        super(firstName, lastName, email, password);
        setStudentList(studentList);
        setAdmin(isAdmin);
    }

    public Advisor(UUID id, String firstName, String lastName, String email, String password, ArrayList<Student> studentList, Boolean isAdmin) {
        super(id, firstName, lastName, email, password);
        setStudentList(studentList);
        setAdmin(isAdmin);
    }
    
    // ----- Accessor -----
    public ArrayList<Student> getStudentList() {
        return this.studentList;
    }

    public Student getCurrentStudent() {
        return this.currentStudent;
    }

    public Boolean getIsAdmin() {
        return this.isAdmin;
    }

    // ----- Mutator -----
    public void setCurrentStudent(UUID id) {
        this.currentStudent = findUser(id);
    }

    public void setStudentList(ArrayList<Student> studentList) {
        this.studentList = studentList;
    }

    public void setAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }


    // ----- Advisor method -----
    public void addStudent(Student student) { 
        this.studentList.add(student); 
    }

    public boolean removeStudent(Student student) {
        for(int i = 0; i < this.studentList.size(); i++) {
            if(student.equals(this.studentList.get(i))) {
                studentList.remove(i);
                return true;
            }
        }
        return false;
    }
    
    public void addStudentProgram(UUID id, Program program) {
        Student student = this.findUser(id);
        if(student == null) {
            return;
        }
        Degree temp = student.getDegree();
        temp.addProgram(program);
        student.setDegree(temp);
    }

    public void removeStudentProgram(UUID id, Program program) {
        Student student = this.findUser(id);
        if(student == null) {
            return;
        }
        Degree temp = student.getDegree();
        temp.removeProgram(program.getID());
        student.setDegree(temp);
    }

    public Student findUser(UUID id) {
        if(this.studentList.size() == 0) {
            System.out.println("WARN --- Advisor have empty student list");
            return null;
        }

        for(int i = 0; i < this.studentList.size(); i++) {
            if(this.studentList.get(i).getID().equals(id)) {
                return this.studentList.get(i);
            }
        }
        System.out.println("WARN --- not found student in Advisor's student list");
        return null;
    }

    public boolean editUserFirstName(UUID id, String name) {
        Student student = findUser(id);
        if(student == null)
            return false;
        student.setFirstName(name);
        return true;
    }

    public boolean editUserLastName(UUID id, String name) {
        Student student = findUser(id);
        if(student == null)
            return false;
        student.setLastName(name);
        return true;
    }
    public boolean editUserEmail(UUID id, String email) {
        Student student = findUser(id);
        if(student == null)
            return false;
        student.setEmail(email);
        return true;
    }
    public boolean editUserUSCID(UUID id, UUID newID) {
        Student student = findUser(id);
        if(student == null)
            return false;
        student.setID(newID);
        return true;
    }

    public boolean editUserPassword(UUID id, String newPassword) {
        Student student = findUser(id);
        if(student == null)
            return false;
        student.setPassword(newPassword);
        return true;
    }

    // ----- Admin method -----

}
