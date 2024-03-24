import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.*;

/**
 * @author Aarsh Patel
 */
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

    // Test the removeMajorCourse method
    @Test
    public void testremoveMajorCourseNull() {
        Degree degree = new Degree("Bachelor of Science", "Computer Science", 120, null, null);
        assertFalse(degree.removeMajorCourse(null));
    }

    @Test
    public void testremoveMajorCourseNotInDegree() {
        Degree degree = new Degree("Bachelor of Science", "Computer Science", 120, null, null);
        assertFalse(degree.removeMajorCourse(new Course("Vector Calculus", "MATH", "241", "", 3, null, null)));
    }

    @Test
    public void testremoveMajorCourseInDegree() {
        Degree degree = new Degree("Bachelor of Science", "Computer Science", 120, null, null);
        Course course = new Course("Vector Calculus", "MATH", "241", "", 3, null, null);
        degree.addMajorCourse(course, 3);
        assertTrue(degree.removeMajorCourse(course));
    }

    @Test
    public void testremoveMajorCourseInDegreeMultiple() {
        Degree degree = new Degree("Bachelor of Science", "Computer Science", 120, null, null);
        Course course1 = new Course("Vector Calculus", "MATH", "241", "", 3, null, null);
        Course course2 = new Course("Linear Algebra", "MATH", "242", "", 3, null, null);
        degree.addMajorCourse(course1, 3);
        degree.addMajorCourse(course2, 3);
        assertTrue(degree.removeMajorCourse(course1));
    }

    @Test
    public void testremoveMajorCourseInEmptyDegree() {
        Degree degree = new Degree("Bachelor of Science", "Computer Science", 120, null, null);
        Course course = new Course("Vector Calculus", "MATH", "241", "", 3, null, null);
        assertFalse(degree.removeMajorCourse(course));
    }

    // Test the equals method
    @Test
    public void testEqualsSameObject() {
        Degree degree = new Degree("Bachelor of Science", "Computer Science", 120, null, null);
        assertTrue(degree.equals(degree));
    }

    @Test
    public void testEqualsDifferentObjectEquals() {
        Degree degree1 = new Degree("Bachelor of Science", "Computer Science", 120, null, null);
        Degree degree2 = new Degree("Bachelor of Science", "Computer Science", 120, null, null);
        assertTrue(degree1.equals(degree2));
    }

    @Test
    public void testEqualsDifferentObjectNotEquals() {
        Degree degree1 = new Degree("Bachelor of Science", "Computer Science", 120, null, null);
        Degree degree2 = new Degree("Bachelor of Science", "Computer Science", 120, null, null);
        degree2.setTotalCreditRequired(121);
        assertFalse(degree1.equals(degree2));
    }

    @Test
    public void testEqualsDifferentObjectNull() {
        Degree degree1 = new Degree("Bachelor of Science", "Computer Science", 120, null, null);
        assertFalse(degree1.equals(null));
    }

    @Test
    public void testEqualsDifferentObjectDifferentType() {
        Degree degree1 = new Degree("Bachelor of Science", "Computer Science", 120, null, null);
        assertFalse(degree1.equals("Degree"));
    }

    // Test the toString method
    @Test
    public void testToString() {
        Degree degree = new Degree("Bachelor of Science", "Computer Science", 120, null, null);
        String expected = "Degree: Bachelor of Science in Computer Science\nTotal Credit Required: 120\nMajor Courses\n";
        assertTrue(degree.toString().contains(expected));
    }

    @Test
    public void testToStringMajorCourses() {
        Degree degree = new Degree("Bachelor of Science", "Computer Science", 120, null, null);
        Course course = new Course("Vector Calculus", "MATH", "241", "", 3, null, null);
        degree.addMajorCourse(course, 3);
        String expected = "Degree: Bachelor of Science in Computer Science\nTotal Credit Required: 120\nMajor Courses\n";
        expected += course.toString();
        assertTrue(degree.toString().contains(expected));
    }
    


}