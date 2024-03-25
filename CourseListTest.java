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
        courseList = courseList.getInstance();
        id1 = UUID.randomUUID();
        id2 = UUID.randomUUID();
        course1 = new Course(id1,"CSCE", "145", "Algorithmic Design I", "Introduction to basic coding.", 3, new ArrayList(), new ArrayList());
        course2 = new Course(id2,"CSCE", "146", "Algorithmic Design II", "Continuation of basic coding algorithms.", 3, new ArrayList(), new ArrayList());
        courseList.addCourse(course1);
        courseList.addCourse(course2);
    }

    @Test
    public void getInstanceNotNull_Test() {
        assertNotNull(courseList.getInstance());
    }

    @Test
    public void getInstanceNull_Test() {
        courseList = courseList.getInstance();
        assertTrue(courseList != null);
    }

    @Test
    public void getInstanceEmptyList_Test() {
        courseList.getInstance().getAllCourse().clear();
        assertTrue(courseList != null);
    }




    @Test
    public void getCourseInList_Test() {
        assertNotNull(courseList.getCourse(id1));
    }

    @Test
    public void getCourseNotInList_Test() {
        assertNull(courseList.getCourse(UUID.randomUUID()));
    }

    @Test
    public void addCourse_Test() {
        courseList = courseList.getInstance();
        courseList.getAllCourse().clear();
        Course course3 = new Course(UUID.randomUUID(),"CSCE", "211", "Digital Logic Design", "Basics of computer logic.", 3, new ArrayList(), new ArrayList());
        courseList.addCourse(course1);
        courseList.addCourse(course2);
        courseList.addCourse(course3);
        ArrayList<Course> coursesActual = courseList.getAllCourse();
        ArrayList<Course> coursesExpected = new ArrayList<Course>();
        coursesExpected.add(course1);
        coursesExpected.add(course2);
        coursesExpected.add(course3);
        assertArrayEquals(coursesActual.toArray(), coursesExpected.toArray());
    }

    @Test
    public void deleteCourse_Test() {
        courseList = courseList.getInstance();
        courseList.getAllCourse().clear();
        courseList.addCourse(course1);
        courseList.deleteCourse(id1);
        ArrayList<Course> coursesActual = courseList.getAllCourse();
        ArrayList<Course> coursesExpected = new ArrayList<Course>();
        assertArrayEquals(coursesActual.toArray(), coursesExpected.toArray());
    }

    @Test
    public void updateCourse_Test() {
        courseList = courseList.getInstance();
        courseList.getAllCourse().clear();
        courseList.addCourse(course1);
        Course course3 = new Course(UUID.randomUUID(),"CSCE", "211", "Digital Logic Design", "Basics of computer logic.", 3, new ArrayList(), new ArrayList());
        courseList.updateCourse(id1, course3);
        ArrayList<Course> coursesActual = courseList.getAllCourse();
        ArrayList<Course> coursesExpected = new ArrayList<Course>();
        Course course4 = new Course(id1,"CSCE", "211", "Digital Logic Design", "Basics of computer logic.", 3, new ArrayList(), new ArrayList());
        coursesExpected.add(course4);
        assertArrayEquals(coursesActual.toArray(), coursesExpected.toArray());
    }


    /* 
    @After
    public void tearDown() {
        CourseList courseList = CourseList.getInstance();
    }
    */

}
