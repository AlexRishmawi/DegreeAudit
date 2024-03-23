import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.*;

/**
 * @author Aarsh Patel
 */
public class DegreeTest {
    // Test the constructor
    @Test
    public void testAdminDegreeConstructor() {
        Degree degree = new Degree("Bachelor of Science", "Computer Science", 120, null, null);
        assertTrue(degree.getSubject().equals("Computer Science") && 
            degree.getDegreeType().equals("Bachelor of Science") &&
            degree.getTotalCreditRequired() == 120 &&
            degree.getMajorCourses() != null &&
            degree.getElectiveList() != null &&
            degree.getID() != null);
    }

    @Test
    public void testJSONReadDegreeConstructor() {
        Degree degree = new Degree("Bachelor of Science", "Computer Science", 120, null, null);
        Degree degree2 = new Degree(degree.getID(), "Bachelor of Science", "Computer Science", 120, null, null);
        assertTrue(degree.equals(degree2));
    }

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
    public void testToStringAllInfoAndEmptyMajorCoursesAndElectiveList() {
        Degree degree = new Degree("Bachelor of Science", "Computer Science", 120, new HashMap<Course,Integer>(), new ArrayList<ElectiveCategory>());
        String expected = "Degree: Bachelor of Science in Computer Science\nTotal Credit Required: 120\nMajor Courses\n";
        assertTrue(degree.toString().contains(expected));
    }

    @Test
    public void testToStringAllInfoAndNullMajorCoursesAndElectiveList() {
        Degree degree = new Degree("Bachelor of Science", "Computer Science", 120, null, null);
        String expected = "Degree: Bachelor of Science in Computer Science\nTotal Credit Required: 120\nMajor Courses\n";
        assertTrue(degree.toString().contains(expected));
    }

    @Test
    public void testToStringAllInfoAndMajorCoursesAndElectiveList() {
        Degree degree = new Degree("Bachelor of Science", "Computer Science", 120, new HashMap<Course,Integer>(), new ArrayList<ElectiveCategory>());
        Course course = new Course("Vector Calculus", "MATH", "241", "", 3, null, null);
        degree.addMajorCourse(course, 3);
        ArrayList<ElectiveCategory> electiveList = new ArrayList<ElectiveCategory>();
        electiveList.add(new ElectiveCategory("Math Elective", 3, new HashMap<Course, Integer>()));
        degree.setElectiveList(electiveList);
        String expected = "Degree: Bachelor of Science in Computer Science\nTotal Credit Required: 120\nMajor Courses\n" + course.toString() + "\nElective List\n" + electiveList.get(0).toString() + "\n";
        assertTrue(degree.toString().contains(expected));
    }


}