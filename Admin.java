
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Admin extends User implements editableUser {
    private ArrayList<Advisor> advisorList;
    private ArrayList<Course> courseList;

    public Admin(String firstName, String lastName, String email, String password){
        super(firstName, lastName, email, password);
    }

    public Admin(UUID id, String firstName, String lastName, String email, String password, ArrayList<Advisor> advisorList) {
        super(id, firstName, lastName, email, password);
        this.advisorList = advisorList;
    }

    public boolean addCourse(String courseName, String department, int code, int creditHours, ArrayList<Season> semesterOffer, ArrayList<HashMap<Course, String>> prerequisites, String description, char gradeToPass, String courseGrade){
        return false;
    }

    public boolean editCourseName(String name){
        return false;
    }

    public boolean editCourseDepartment(String department){
        return false;
    }

    public boolean editCourseCode(int code){
        return false;
    }

    public boolean editCourseCredit(int credit){
        return false;
    }

    public boolean editCourseSemesterOffer(ArrayList<Season> season){
        return false;
    }

    public boolean editCoursePrerequisites(ArrayList<Course> course){
        return false;
    }

    public boolean editCourseDescription(String name){
        return false;
    }

    public boolean removeCourse(){
        return false;
    }

    private Course findCourse(){
        return null;
    }
}
