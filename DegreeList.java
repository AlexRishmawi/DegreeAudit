import java.util.ArrayList;
import java.util.UUID;
/**
 * @author Alex Rishmawi
 */
public class DegreeList {
    private static DegreeList degreeList;
    private ArrayList<Degree> degree;
    private DegreeList() {
        degree = new ArrayList<Degree>();
        // Read a database
    }

    /**
     * Gets instance of DegreeList
     * @return degreeList
     */
    public static DegreeList getInstance() {
        return degreeList != null ? degreeList : new DegreeList();
    }

    /**
     * Returns degree from degree list by id
     * @param id
     * @return Degree
     */
    public Degree getDegree(UUID id) {
        for(Degree degree: degree) {
            if(degree.getID().equals(id)) {
                return degree;
            }
        }
        return null;
    }
    /**
     * Prints DegreeList
     * @return degree
     */
    public Degree printDegree() {
        for(int i= 0;i < degree.size();i++) {
            return degree.get(i);
        }
        return null;
    }
}
