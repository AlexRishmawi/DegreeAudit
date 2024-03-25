import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

/**
 * @author Paksh Patel
 * 
 */

public class DegreeWorkTest {

    private DegreeWork dw;


    //Login
    @Test
    public void testValidEmailPassword(){
        dw = new DegreeWork();
        assertTrue(dw.login("bwest@email.sc.edu", "password"));
    }

    @Test
    public void testValidNamePassword(){
        dw = new DegreeWork();
        assertTrue(dw.login("Tawnie", "Hill", "password"));
    }

    @Test
    public void testInvalidEmail(){
        dw = new DegreeWork();
        assertFalse(dw.login("notanemail", "password"));
    }

    @Test
    public void testInvalidNamePassword(){
        dw = new DegreeWork();
        assertFalse(dw.login("Pakshv", "Patel", "password"));
    }

    @Test
    public void testInvalidPassword(){
        dw = new DegreeWork();
        assertFalse(dw.login("paksh@email.sc.edu", "password1"));
    }

    //Logout
    @Test
    public void testLogout() {
        dw = new DegreeWork();
        assertTrue(dw.logout());
    }

    //User Creation Tests
    @Test
    public void testValidCreation() {
        dw = new DegreeWork();
        assertTrue(dw.createUser("Student", "Paksh", "Patel", "password", "X234789012", "paksh@email.sc.edu"));
    }

    @Test
    public void testCreationWithExisting() {
        dw = new DegreeWork();
        dw.createUser("Student", "Tawnie", "Hill", "password", "X33457890", "tHill@email.sc.edu"); 
        
        assertFalse(dw.createUser("Student", "Tawnie", "Hill", "password", "X33457890", "tHill@email.sc.edu"));
    }

    @Test
    public void testCreationWithInvalidOrIncomplete() {
        dw = new DegreeWork();
        assertFalse(dw.createUser("Studen", "Tawnie", "Hill", "password", "X337890", "tHill@.edu"));
    }

    //Remove User Tests
    @Test
    public void testRemoveExisting() {
        dw = new DegreeWork();
        assertTrue(dw.createUser("Student", "Tawnie", "Hill", "password", "X33457890", "tHill@email.sc.edu"));
    }

    @Test
    public void testRemoveNonExisting() {
        dw = new DegreeWork();
        String id = UUID.randomUUID().toString();
        assertFalse(dw.removeUser(id));
    }

    //Student and Advisor Functionality Tests
    @Test
    public void testCreateValidStudent() {
        dw = new DegreeWork();
        Advisor a = new Advisor("John", "Doe", "dJohn@email.sc.edu", "password", false);
        String firstName = "Alice";
        String lastName = "Johnson";
        String email = "alice@email.sc.edu";
        String password = "securepassword";
        String studentID = "S12345678";
        String level = "Freshman";
        ArrayList<String> notes = new ArrayList<>();
        Degree degree = new Degree("Bachelor of Science", "Computer Science", 120, null, null); 
        
        assertNotNull(dw.createStudent(firstName, lastName, email, password, studentID, level, a, notes, degree, 0.0, 0.0, "Good Standing"));
    }

    @Test
    public void testCreateWithoutAdvisor() {
        dw = new DegreeWork();
        Advisor a = null;

        String firstName = "Alice";
        String lastName = "Johnson";
        String email = "alice@email.sc.edu";
        String password = "securepassword";
        String studentID = "S12345678";
        String level = "Freshman";
        ArrayList<String> notes = new ArrayList<>();
        Degree degree = new Degree("Bachelor of Science", "Computer Science", 120, null, null); 
        
        assertNull(dw.createStudent(firstName, lastName, email, password, studentID, level, a, notes, degree, 0.0, 0.0, "Good Standing"));
    }

    @Test
    public void testCreateValidAdvisor() {
        dw = new DegreeWork();
        String firstName = "Jane";
        String lastName = "Doe";
        String email = "jane.doe@example.com";
        String password = "securepassword";
        ArrayList<Student> studentList = new ArrayList<>(); // Initially, no students are associated.

        assertNotNull(dw.createAdvisor(firstName, lastName, email, password, studentList, false));
    }

    @Test
    public void testCreateAdvisorWithMixedStudentList() {
        dw = new DegreeWork();
        
        Student existingStudent1 = null;
        Student existingStudent2 = null;

        ArrayList<Student> studentList = new ArrayList<>();
        studentList.add(existingStudent1);
        studentList.add(existingStudent2);

        assertNotNull(dw.createAdvisor("Alice", "Smith", "alice.smith@example.com", "password", studentList, false));
    }

    @Test
    public void testDisplayDegreeForStudent() {
        dw = new DegreeWork();
        Advisor a = new Advisor("John", "Doe", "dJohn@email.sc.edu", "password", false);
        String firstName = "Alice";
        String lastName = "Johnson";
        String email = "alice@email.sc.edu";
        String password = "securepassword";
        String studentID = "S12345678";
        String level = "Freshman";
        ArrayList<String> notes = new ArrayList<>();
        Degree degree = new Degree("Bachelor of Science", "Computer Science", 120, null, null); 
        Student student = dw.createStudent(firstName, lastName, email, password, studentID, level, a, notes, degree, 4.0, 4.0, "Good Standing");
        dw.setCurrentUser(student);

        String progress = dw.displayDegreeProgress();

        assertNotNull(progress);
    }

    @Test
    public void testDisplayDegreeProgressForAdvisorsCurrentStudent() {
        dw = new DegreeWork();
        Advisor advisor = dw.createAdvisor("Jane", "Smith", "jane@example.com", "password", new ArrayList<>(), true);
        Student student = dw.createStudent("John", "Doe", "john.doe@example.com", "password", "654321", "Junior", advisor, new ArrayList<>(), new Degree("BA", "English", 120, null, null), 3.2, 3.2, "Active");
        dw.setCurrentUser(advisor); 
        dw.setCurrentStudent(student.getID());

        String progress = dw.displayDegreeProgress();

        assertNotNull(progress);
        assertTrue(progress.contains("John Doe") && progress.contains("English"));
    }

    @Test
    public void testDisplayEightSemesterPlanForStudent() {
        dw = new DegreeWork();
        Student student = dw.createStudent("Emily", "Clark", "emily@example.com", "password", "789012", "Sophomore", null, new ArrayList<>(), new Degree("BS", "Biology", 120, null, null), 3.6, 3.6, "Active");
        dw.setCurrentUser(student);

        String eightSemesterPlan = dw.displayEightSemesterPlan();

        assertNotNull("Eight-semester plan should not be null", eightSemesterPlan);
        assertTrue("Eight-semester plan should contain specific student information", eightSemesterPlan.contains("Emily Clark") && eightSemesterPlan.contains("Biology"));
    }

    @Test
    public void testDisplayEightSemesterPlanForAdvisorsCurrentStudent() {
        dw = new DegreeWork();
        Advisor advisor = dw.createAdvisor("Mark", "Brown", "mark@example.com", "password", new ArrayList<>(), true);
        Student student = dw.createStudent("Alex", "Johnson", "alex.johnson@example.com", "password", "345678", "Freshman", advisor, new ArrayList<>(), new Degree("BFA", "Art", 120, null, null), 3.1, 3.1, "Active");
        dw.setCurrentUser(advisor); 
        dw.setCurrentStudent(student.getID());

        String eightSemesterPlan = dw.displayEightSemesterPlan();

        assertNotNull("Eight-semester plan for advisor's current student should not be null", eightSemesterPlan);
        assertTrue("Eight-semester plan should contain specific student information", eightSemesterPlan.contains("Alex Johnson") && eightSemesterPlan.contains("Art"));
    }

    @Test
    public void testNoteToSelf() {
        dw = new DegreeWork();
        Student student = dw.createStudent("John", "Doe", "john@example.com", "password", "123456", "Senior", null, new ArrayList<>(), null, 3.5, 3.5, "Active");
        dw.setCurrentUser(student);

        assertTrue(dw.addNotes("This is a note to self"));
    }

    @Test
    public void testAdvisorAddingNoteToStudent() {
        dw = new DegreeWork();
        Advisor advisor = dw.createAdvisor("Mark", "Brown", "mark@example.com", "password", new ArrayList<>(), true);
        dw.setCurrentUser(advisor);

        boolean result = dw.addNotes("This note is added by the advisor.");

        assertTrue(result);
    }

    //Course and Degree Management Tests
    @Test
    public void testAddNewCourseWithValidDetails() {
        dw = new DegreeWork();
        dw.login("admin@example.com", "adminPassword");

        assertTrue(dw.addCourse("CS", "101", "Intro to Computer Science", "Basics of CS", 4, new ArrayList<>(), new ArrayList<>()));
    }

    @Test
    public void testEditExistingCourseDetails() {
        dw = new DegreeWork();
        dw.login("oOdden@email.sc.edu", "password");
        UUID courseId = UUID.fromString("554472ab-c7cb-4ac6-bf69-0d8b58a99d3d");

        boolean result = dw.editCourseName(courseId, "ACCT");

        assertTrue(result);
    }

    @Test
    public void testEditCourseWithInvalidPermissions() {
        dw = new DegreeWork();
        dw.login("tHill@email.sc.edu", "password");
        UUID courseId = UUID.fromString("554472ab-c7cb-4ac6-bf69-0d8b58a99d3d");

        boolean result = dw.editCourseName(courseId, "ACCT");

        assertFalse(result);
    }

    //Degree Progress and Planning Tests
    @Test
    public void testDisplayDegreeProgressForStudent() {
        dw = new DegreeWork();
        dw.login("student@example.com", "studentPassword");

        String progress = dw.displayDegreeProgress();

        assertNotNull(progress);
    }

    //User Account Editing Tests
    @Test
    public void testEditStudentFirstName() {
        dw = new DegreeWork();
        dw.login("student@example.com", "studentPassword");

        boolean result = dw.editUserFirstName(dw.getCurrentUser().getID(), "NewFirstName");

        assertTrue(result);
    }

    //General and Edge Case Tests
    @Test(expected = IllegalArgumentException.class)
    public void testAddCourseWithNullDetails() {
        dw = new DegreeWork();
        dw.login("admin@example.com", "adminPassword");

        dw.addCourse(null, null, null, null, 0, null, null);
    }

    @Test
    public void testAddCourseWithUnauthorizedUser() {
        dw = new DegreeWork();
        dw.login("student@example.com", "studentPassword");

        boolean result = dw.addCourse("CS", "102", "Data Structures", "Intermediate CS course", 4, new ArrayList<>(), new ArrayList<>());

        assertFalse(result);
    }

}