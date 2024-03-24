import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.*;

/**
 * @author Aarsh Patel
 */
public class DegreeListTest {
    // Test the getDegree method
    @Test
    public void testGetDegreeNull() {
        DegreeList degreeList = DegreeList.getInstance();
        assertTrue(degreeList.getDegree(null) == null);
    }

    @Test
    public void testGetDegreeNotInList() {
        DegreeList degreeList = DegreeList.getInstance();
        assertTrue(degreeList.getDegree(UUID.randomUUID()) == null);
    }

    @Test
    public void testGetDegreeInList() {
        DegreeList degreeList = DegreeList.getInstance();
        Degree degree = new Degree("Bachelor of Science", "Computer Science", 120, null, null);
        assertTrue(degreeList.getDegree(degree.getID()).equals(degree));
    }

    @Test
    public void testGetDegreeInEmptyList() {
        DegreeList degreeList = DegreeList.getInstance();
        degreeList.getAllDegree().clear();
        assertTrue(degreeList.getDegree(UUID.randomUUID()) == null);
    }
}
