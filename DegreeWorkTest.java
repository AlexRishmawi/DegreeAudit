import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.junit.*;

public class DegreeWorkTest {
    private DegreeWork dw;

    @Before
    public void setUp() {
        dw = new DegreeWork();
    }
    
    @Test
    public void testLogin() {
        dw.login("admin@email.sc.edu", "admin");
        Assert.assertFalse(dw.login("admin","admin"));
    }

    @Test
    public void testLogin2() {
        dw.login("John", "Doe", "bestpassword");
        Assert.assertFalse(dw.login("708142", "9010", "exmapleidk"));
    }

    @Test
    public void testCreateUser() {
        dw.createUser("Admin", "Jacob", "Jones", "example123", "X23468523", "jjacob@email.sc.edu");
        Assert.assertFalse(dw.createUser("Admin", "Ja23cob", "Jon234234es", "example123", "268523", "Done Done"));
    }

    @Test
    public void testCreateStudent() {
        Degree degree = new Degree("Bachelor of Science", "Computer Science", 120, new HashMap<Course, Integer>(), new ArrayList<>());
        Advisor advisor = new Advisor("Jane", "Doe", "djane@email.sc.edu", "password", true);
        dw.createStudent("Alice", "Wonderland", "walice@email.sc.edu", "password", "X123456789", "Junior", advisor, new ArrayList<>(Arrays.asList("Good progress", "Participates actively in class")), degree, 3.5, 3.7, "Active");
        Assert.assertTrue(Student.class.isInstance(dw.createStudent("Alice", "Wonderland", "walice@email.sc.edu", "password", "X123456789", "Junior", advisor, new ArrayList<>(Arrays.asList("Good progress", "Participates actively in class")), degree, 3.5, 3.7, "Active")));
    }

    @Test
    public void testCreateAdvisor() {
        dw.createAdvisor("Jane", "Doe", "djane@email.sc.edu", "password", new ArrayList<>(), true);
    }

    @Test
    public void testRemoveUser() {
        
    }

    @Test
    public void testDisplayDegreeProgress() {
        Degree degree = new Degree("Bachelor of Science", "Computer Science", 120, new HashMap<Course, Integer>(), new ArrayList<>());
        Student student = dw.createStudent("Alice", "Wonderland", "alice@example.com", "password", "X123456789", "Junior", null, new ArrayList<>(), degree, 3.5, 3.7, "Active");
        dw.setCurrentUser(student);
        String progress = dw.displayDegreeProgress();
        String expectedProgress = "Degree Progress: XX%"; // Replace XX with the expected progress
        Assert.assertEquals(expectedProgress, progress);
    }
}