import java.util.*;
/**
 * 
 * @author Aarsh Patel
 */
public class Degree {
    private UUID id;
    private String degreeType;
    private String subjectName;
    private int totalCreditRequired;
    private ArrayList<Course> majorCourses;
    private ArrayList<ElectiveCategory> electiveList;


    public Degree(String degreeType, String subjectName, int totalCreditRequired, 
        ArrayList<Course> majorCourses, ArrayList<ElectiveCategory> electiveList) 
    {
        this.id = UUID.randomUUID();
        this.subjectName = subjectName;
        this.degreeType = degreeType;
        this.totalCreditRequired = totalCreditRequired;
        this.majorCourses = majorCourses;
        this.electiveList = electiveList; 
    }
    
    public ArrayList<Course> getMajorCourses() {
        return this.majorCourses;
    }

    public int getTotalCreditRequired() {
        return this.totalCreditRequired;
    }

    public UUID getID() {
        return this.id;
    }

    public boolean addMajorCourse(Course course) {
        if (course != null) {
            majorCourses.add(course);
            return true;
        }
        return false;
    }

    public boolean removeMajorCourse(Course course) {
        if (course != null) {
            majorCourses.remove(course);
            return true;
        }
        return false;
    }
    
    public boolean equals(Degree degree) {
        return this == degree;
    }

    public String toString() {
        StringBuilder retString = new StringBuilder();
        retString.append("Degree: " + this.degreeType + " in " + this.subjectName + "\n");
        retString.append("Total Credit Required: " + this.totalCreditRequired + "\n");
        retString.append("Courses\n");
        for(Course course : majorCourses) {
            retString.append(course.toString() + "\n");
        }
        for (ElectiveCategory element : electiveList) {
            retString.append("\n" + element.getType() + "\n");
            retString.append("Total Credits Required: " + element.getCreditsRequired() + "\n");
            retString.append(element + "\n");
        }
        return retString.toString();
    }
    // public static void main(String[] args) {
    //     UUID id = UUID.randomUUID();
    //     String type = "Bachelors";
    //     String name = "Computer Science";
    //     int totalCredits = 125;
    //     ArrayList<Course> courses = new ArrayList<>();
    //     courses.add(new Course("Vector Calculus", "MATH", "241", 3, new ArrayList<Season>(), new ArrayList<Course>(), "None", "C"));
    //     ArrayList<ElectiveCategory> electives = new ArrayList<>();
    //     electives.add(new ElectiveCategory("Application Area", 16, courses));
    //     Degree testDegree = new Degree(type, name, totalCredits, courses, electives);
    //     System.out.println(testDegree);
    // }
}
