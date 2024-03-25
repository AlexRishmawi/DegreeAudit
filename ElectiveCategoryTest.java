import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class ElectiveCategoryTest {
    private ElectiveCategory electiveCategory1;
    private ElectiveCategory electiveCategory2;
    private HashMap<Course, Integer> courseChoices1;
    private HashMap<Course, Integer> courseChoices2;

    @Before
    public void setUp() {
        courseChoices1 = new HashMap<>();
        courseChoices1.put(new Course("ID1", "Course1", "Description1", "Dept1", 3, new ArrayList<>(Arrays.asList(Season.FALL)), new ArrayList<>()), 3);
        courseChoices1.put(new Course("ID2", "Course2", "Description2", "Dept2", 2, new ArrayList<>(Arrays.asList(Season.SPRING)), new ArrayList<>()), 2);

        electiveCategory1 = new ElectiveCategory("Science", 5, courseChoices1);

        courseChoices2 = new HashMap<>();
        courseChoices2.put(new Course("ID3", "Course3", "Description3", "Dept3", 1, new ArrayList<>(Arrays.asList(Season.SUMMER)), new ArrayList<>()), 1);

        electiveCategory2 = new ElectiveCategory("Arts", 1, courseChoices2);
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals("Science", electiveCategory1.getType());
        assertEquals(5, electiveCategory1.getCreditsRequired());
        assertEquals(2, electiveCategory1.getCourseChoices().size());

        assertEquals("Arts", electiveCategory2.getType());
        assertEquals(1, electiveCategory2.getCreditsRequired());
        assertEquals(1, electiveCategory2.getCourseChoices().size());
    }

    @Test
    public void testToString() {
        assertTrue(electiveCategory1.toString().contains("Science"));
        assertTrue(electiveCategory1.toString().contains("Course1"));
        assertTrue(electiveCategory1.toString().contains("Course2"));

        assertTrue(electiveCategory2.toString().contains("Arts"));
        assertTrue(electiveCategory2.toString().contains("Course3"));
    }
}
