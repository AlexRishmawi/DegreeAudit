import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.*;

/**
 * @author Aarsh Patel
 */
public class DegreeListTest {
    // Test the constructor
    @Test
    public void testDefaultConstructor() {
        DegreeList degreeList = DegreeList.getInstance();
        assertTrue(degreeList.getAllDegree() != null);
    }

    // Test the getInstance method
    @Test
    public void testGetInstanceWithNull() {
        DegreeList degreeList = DegreeList.getInstance();
        assertTrue(degreeList != null);
    }

    @Test
    public void testGetInstanceNotNull() {
        DegreeList degreeList = DegreeList.getInstance();
        assertTrue(degreeList == DegreeList.getInstance());
    }

    @Test
    public void testGetInstanceWithEmptyList() {
        DegreeList degreeList = DegreeList.getInstance();
        degreeList.getAllDegree().clear();
        assertTrue(degreeList != null);
    }

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
        degreeList.addDegree(degree);
        assertTrue(degreeList.getDegree(degree.getID()).equals(degree));
    }

    @Test
    public void testGetDegreeInEmptyList() {
        DegreeList degreeList = DegreeList.getInstance();
        degreeList.getAllDegree().clear();
        assertTrue(degreeList.getDegree(UUID.randomUUID()) == null);
    }

    @Test
    public void testWriteDegree() {
        DegreeList degreeList = DegreeList.getInstance();
        assertTrue(degreeList.writeToFile());
    }

    @Test  // This test logic not right
    public void testWriteDegreeEmptyList() {
        DegreeList degreeList = DegreeList.getInstance();
        degreeList.getAllDegree().clear();
        assertTrue(degreeList.writeDegree());
    }

    
}
