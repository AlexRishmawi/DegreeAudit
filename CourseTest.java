import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.UUID;

public class CourseTest {
    
    @Test
    public void no_id_constructor() {
        Course testCourse1 = new Course("CSCE", "145", "Algorithmic Design I", "Introduction to basic coding.", 3, new ArrayList(), new ArrayList());
        Course testCourse2 = new Course("CSCE", "145", "Algorithmic Design I", "Introduction to basic coding.", 3, new ArrayList(), new ArrayList());
        Course testCourse3 = new Course("CSCE", "146", "Algorithmic Design II", "Continuation of basic coding algorithms.", 3, new ArrayList(), new ArrayList());
        assertNotEquals(testCourse1, testCourse3);
        assertEquals(testCourse1, testCourse2); 

    }

    @Test
    public void id_constructor() {
        Course testCourse1 = new Course(UUID.randomUUID(), "CSCE", "145", "Algorithmic Design I", "Introduction to basic coding.", 3, new ArrayList(), new ArrayList());
        Course testCourse2 = new Course(UUID.randomUUID(), "CSCE", "145", "Algorithmic Design I", "Introduction to basic coding.", 3, new ArrayList(), new ArrayList());
        Course testCourse3 = new Course(UUID.randomUUID(), "CSCE", "146", "Algorithmic Design II", "Continuation of basic coding algorithms.", 3, new ArrayList(), new ArrayList());
        assertNotEquals(testCourse1, testCourse3);
        assertEquals(testCourse1, testCourse2);
    }

    @Test
    public void toString_Course_Abrev_test() {
        Course testCourse1 = new Course(UUID.randomUUID(), "CSCE", "145", "Algorithmic Design I", "Introduction to basic coding.", 3, new ArrayList(), new ArrayList());
        assertEquals("CSCE145", testCourse1.toStringCourseAbbr());
    }

    
}
