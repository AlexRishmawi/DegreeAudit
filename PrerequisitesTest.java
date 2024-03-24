import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.*;

public class PrerequisitesTest {
    // ----- Constructor test -----
    @Test
    public void testConstructorWithNoArguments() {
        Prerequisites prerequisites = new Prerequisites();
        assertTrue(prerequisites.getChoices() == 0 && 
            prerequisites.getMinGrade().equals("F") &&
            prerequisites.getCourseOptions() == null);
    }

    @Test
    public void testConstructorWithArguments() {
        Prerequisites prerequisites = new Prerequisites(1, "C", null);
        assertTrue(prerequisites.getChoices() == 1 && 
            prerequisites.getMinGrade().equals("C") &&
            prerequisites.getCourseOptions() == null);
    }

    // toString() test
    @Test
    public void testToStringWithNullCourseOptions() {
        Prerequisites prerequisites = new Prerequisites();
        String expected = "\n-- Min Grade: F\n-- Course Options: ";
        String actual = prerequisites.toString();
        assertTrue(expected.equals(actual));
    }

    @Test
    public void testToStringWithEmptyCourseOptions() {
        Prerequisites prerequisites = new Prerequisites(1, "C", new ArrayList<Course>());
        String expected = "\n-- Min Grade: C\n-- Course Options: ";
        String actual = prerequisites.toString();
        assertTrue(expected.equals(actual));
    }

    @Test
    public void testToStringWithMinGradeAndCourseOptions() {
        Prerequisites prerequisites = new Prerequisites(1, "C", null);
        Course course = new Course("CSCE", "101", "Introduction to Computer Science", "Description", 3, new ArrayList<Season>(), new ArrayList<Prerequisites>());
        prerequisites.getCourseOptions().add(course);
        String expected = "\n-- Min Grade: C\n-- Course Options: CSC 101\n";
        String actual = prerequisites.toString();
        assertTrue(expected.equals(actual));
    }

    @Test
    public void testToStringWithMinGradeAndWithoutCourseOptions() {
        Prerequisites prerequisites = new Prerequisites(1, "C", null);
        String expected = "\n-- Min Grade: C\n-- Course Options: ";
        String actual = prerequisites.toString();
        assertTrue(expected.equals(actual));
    }

    @Test
    public void testToStringWithMinGradeAndEmptyCourseOptions() {
        Prerequisites prerequisites = new Prerequisites(1, "C", new ArrayList<Course>());
        String expected = "\n-- Min Grade: C\n-- Course Options: ";
        String actual = prerequisites.toString();
        assertTrue(expected.equals(actual));
    }


}
