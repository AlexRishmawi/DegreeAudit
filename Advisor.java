import java.util.ArrayList;
import java.util.UUID;

/**
 * Advisor class represents a user with advisor privileges.
 * It extends the User class and contains methods to manage students and perform advisor-specific actions.
 */

public class Advisor extends User{
    private ArrayList<Student> studentList;
    private Student currentStudent;
    private int currentStudentIndex;
    private Boolean isAdmin;

    /**
     * Constructor for creating an Advisor instance.
     * @param firstName First name of the advisor.
     * @param lastName Last name of the advisor.
     * @param email Email address of the advisor.
     * @param password Password of the advisor.
     * @param isAdmin Specifies whether the advisor is an administrator.
     */
    public Advisor(String firstName, String lastName, String email, String password, Boolean isAdmin) 
    {
        super(firstName, lastName, email, password);
        this.studentList = new ArrayList<Student>();
        setAdmin(isAdmin);
    }

    /**
     * Constructor for creating an Advisor instance with an existing list of students.
     * @param firstName First name of the advisor.
     * @param lastName Last name of the advisor.
     * @param email Email address of the advisor.
     * @param password Password of the advisor.
     * @param studentList List of students managed by the advisor.
     * @param isAdmin Specifies whether the advisor is an administrator.
     */
    public Advisor(String firstName, String lastName, String email, String password, ArrayList<Student> studentList, Boolean isAdmin)
    {
        super(firstName, lastName, email, password);
        setStudentList(studentList);
        setAdmin(isAdmin);
    }

    /**
     * Constructor for creating an Advisor instance with a specific ID and an existing list of students.
     * @param id Unique identifier of the advisor.
     * @param firstName First name of the advisor.
     * @param lastName Last name of the advisor.
     * @param email Email address of the advisor.
     * @param password Password of the advisor.
     * @param studentList List of students managed by the advisor.
     * @param isAdmin Specifies whether the advisor is an administrator.
     */
    public Advisor(UUID id, String firstName, String lastName, String email, String password, ArrayList<Student> studentList, Boolean isAdmin) 
    {
        super(id, firstName, lastName, email, password);
        setStudentList(studentList);
        setAdmin(isAdmin);
    }
    
    // ----- Accessor -----
    /**
     * Retrieves the list of students managed by the advisor.
     * @return ArrayList containing the students managed by the advisor.
     */
    public ArrayList<Student> getStudentList() 
    {
        return this.studentList;
    }

    /**
     * Retrieves the currently selected student.
     * @return The currently selected student.
     */
    public Student getCurrentStudent() 
    {
        return this.currentStudent;
    }

    /**
     * Checks if the advisor is an administrator.
     * @return True if the advisor is an administrator, otherwise false.
     */
    public Boolean getIsAdmin() 
    {
        return this.isAdmin;
    }

    // ----- Mutator -----
    /**
     * Sets the currently selected student based on their unique ID.
     * @param id The unique ID of the student.
     */
    public void setCurrentStudent(UUID id) 
    {
        this.currentStudent = findStudent(id);
    }

    /**
     * Sets the list of students managed by the advisor.
     * @param studentList ArrayList containing the students to be managed by the advisor.
     */
    public void setStudentList(ArrayList<Student> studentList) 
    {
        this.studentList = studentList;
    }

    /**
     * Sets whether the advisor is an administrator.
     * @param isAdmin True if the advisor is an administrator, otherwise false.
     */
    public void setAdmin(Boolean isAdmin) 
    {
        this.isAdmin = isAdmin;
    }

    // ----- Advisor method -----
    /**
     * Adds a new student to the advisor's list of managed students.
     * @param student The student to be added.
     */
    public void addStudent(Student student) 
    {
        this.studentList.add(student); 
    }

    /**
     * Removes a student from the advisor's list of managed students.
     * @param student The student to be removed.
     * @return True if the student was successfully removed, otherwise false.
     */
    public boolean removeStudent(Student student) 
    {
        for(int i = 0; i < this.studentList.size(); i++) {
            if(student.equals(this.studentList.get(i))) {
                studentList.remove(i);
                return true;
            }
        }
        return false;
    }
    
    /**
     * Finds a student in the advisor's list of managed students based on their unique ID.
     * @param id The unique ID of the student to be found.
     * @return The student if found, otherwise null.
     */
    public Student findStudent(UUID id) 
    {
        if(this.studentList.size() == 0) {
            return null;
        }

        for(int i = 0; i < this.studentList.size(); i++) {
            if(this.studentList.get(i).getID().equals(id)) {
                this.currentStudentIndex = i;
                return this.studentList.get(i);
            }
        }
        return null;
    }

    /**
     * Edits the first name of the currently selected student.
     * @param fname The new first name.
     * @return True if the first name was successfully updated, otherwise false.
     */
    public boolean editStudentFirstName(String fname)
    {
        this.currentStudent.setFirstName(fname);
        return updateStudentInStudentList();
    }

    /**
     * Edits the last name of the currently selected student.
     * @param lname The new last name.
     * @return True if the last name was successfully updated, otherwise false.
     */
    public boolean editStudentLastName(String lname)
    {
        this.currentStudent.setLastName(lname);
        return updateStudentInStudentList();
    }

    /**
     * Edits the email of the currently selected student.
     * @param email The new email address.
     * @return True if the email was successfully updated, otherwise false.
     */
    public boolean editStudentEmail(String email) {
        this.currentStudent.setEmail(email);
        return updateStudentInStudentList();
    }

    /**
     * Edits the password of the currently selected student.
     * @param newPassword The new password.
     * @return True if the password was successfully updated, otherwise false.
     */
    public boolean editStudentPassword(String newPassword) {
        this.currentStudent.setPassword(newPassword);
        return updateStudentInStudentList();
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        if(isAdmin) {
            result.append("Admin Information:\n");
            result.append(super.toString());
            result.append("-- Student: \n");
            for (Student student: studentList) {
                result.append("    [" + student.toStringAccount() + "]\n");
            }
        } else {
            result.append("Advisor Information:\n");
            result.append(super.toString());
        }
        return result.toString();
    }

    // Private helper method

    /**
     * Updates the currently selected student in the list of managed students.
     * @return True if the student was successfully updated, otherwise false.
     */
    private boolean updateStudentInStudentList() {
        this.studentList.set(this.currentStudentIndex, this.currentStudent);
        return true;
    }

}
