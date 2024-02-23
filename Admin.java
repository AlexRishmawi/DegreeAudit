
import java.util.ArrayList;
import java.util.HashMap;

public class Admin {
    private ArrayList<String> userList;
    private ArrayList<String> courseList;

    public Admin(String firstName, String lastName, String password){

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
