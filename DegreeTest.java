import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.*;

public class DegreeTest {

    // Test the addMajorCourse method
    @Test
    public void testaddMajorCourseNull() {
        Degree degree = new Degree("Bachelor of Science", "Computer Science", 120, null, null);
        assertFalse(degree.addMajorCourse(null, 3));
    }

    @Test
    public void testaddMajorCourseNegativeSemester() {
        Degree degree = new Degree("Bachelor of Science", "Computer Science", 120, null, null);
        assertFalse(degree.addMajorCourse(new Course("Vector Calculus", "MATH", "241", "", 3, null, null), -1));
    }

    @Test
    public void testaddMajorCourseZeroSemester() {
        Degree degree = new Degree("Bachelor of Science", "Computer Science", 120, null, null);
        assertFalse(degree.addMajorCourse(new Course("Vector Calculus", "MATH", "241", "", 3, null, null), 0));
    }

    @Test
    public void testaddMajorCourseGreaterThanEightSemester() {
        Degree degree = new Degree("Bachelor of Science", "Computer Science", 120, null, null);
        assertFalse(degree.addMajorCourse(new Course("Vector Calculus", "MATH", "241", "", 3, null, null), 9));
    }

    @Test
    public void testaddMajorCourseInBoundsSemester() {
        Degree degree = new Degree("Bachelor of Science", "Computer Science", 120, null, null);
        assertTrue(degree.addMajorCourse(new Course("Vector Calculus", "MATH", "241", "", 3, null, null), 3));
        
    }

    
}