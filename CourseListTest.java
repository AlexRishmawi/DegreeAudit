import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.UUID;

public class CourseListTest {
    private Course course1;
    private Course course2;
    private CourseList courseList;
    private UUID id1;
    private UUID id2;


    @Before
    public void setUp() {
        courseList = null;
        courseList = CourseList.getInstance();
        id1 = UUID.randomUUID();
        id2 = UUID.randomUUID();
        course1 = new Course(id1,"CSCE", "145", "Algorithmic Design I", "Introduction to basic coding.", 3, new ArrayList(), new ArrayList());
        course2 = new Course(id2,"CSCE", "146", "Algorithmic Design II", "Continuation of basic coding algorithms.", 3, new ArrayList(), new ArrayList());
        courseList.addCourse(course1);
        courseList.addCourse(course2);
    }

    @Test
    public void getCourses_Test() {
        ArrayList<Course> coursesActual = courseList.getAllCourse();
        ArrayList<Course> coursesExpected = new ArrayList<Course>();
        coursesExpected.add(course1);
        coursesExpected.add(course2);
        assertEquals(coursesActual, coursesExpected);
    }

    @Test
    public void deleteCourse_Test() {
        courseList.deleteCourse(id1);
        ArrayList<Course> coursesActual = courseList.getAllCourse();
        ArrayList<Course> coursesExpected = new ArrayList<Course>();
        coursesExpected.add(course2);
        assertEquals(coursesActual, coursesExpected);
    }

    @Test
    public void updateCourse_Test() {
        Course course3 = new Course(UUID.randomUUID(),"CSCE", "211", "Digital Logic Design", "Basics of computer logic.", 3, new ArrayList(), new ArrayList());
        courseList.updateCourse(id1, course3);
        ArrayList<Course> coursesActual = courseList.getAllCourse();
        ArrayList<Course> coursesExpected = new ArrayList<Course>();
        coursesExpected.add(course3);
        coursesExpected.add(course2);
        assertEquals(coursesActual, coursesExpected);
    }



    @After
    public void tearDown() {
        CourseList courseList = CourseList.getInstance();
    }
}
