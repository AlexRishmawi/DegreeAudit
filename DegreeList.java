import java.util.ArrayList;
import java.util.UUID;
/**
 * @author Alex Rishmawi
 */
public class DegreeList {
    private DegreeList degreeList;
    private ArrayList<Degree> degree;
    private DegreeList() {
        degree = new ArrayList<Degree>;
    }

    /**
     * Gets instance of DegreeList
     * @return degreeList
     */
    public DegreeList getInstance() {
        if (degreeList == null) {
            degreeList = new DegreeList();
        }
        return degreeList;
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
