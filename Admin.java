
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
        //TODO: ....
        return false;
    }

    public boolean editCourseName(UUID id, String name){
        return false;
    }

    public boolean editCourseDepartment(UUID id, String department){
        return false;
    }

    public boolean editCourseCode(UUID id, int code){
        return false;
    }

    public boolean editCourseCredit(UUID id, int credit){
        return false;
    }

    public boolean editCourseSemesterOffer(UUID id, ArrayList<Season> season){
        return false;
    }

    public boolean editCoursePrerequisites(UUID id, ArrayList<Course> course){
        return false;
    }

    public boolean editCourseDescription(UUID id, String name){
        return false;
    }

    public boolean removeCourse(UUID id){
        return false;
    }

    public Course findCourse(){
        return null;
    }

    public Advisor findUser(UUID id) {
        if(this.advisorList.size() == 0) {
            System.out.println("WARN --- Advisor have empty advisor list");
            return null;
        }

        for(int i = 0; i < this.advisorList.size(); i++) {
            if(this.advisorList.get(i).getID().equals(id)) {
                return this.advisorList.get(i);
            }
        }
        System.out.println("WARN --- not found advisor in Advisor's advisor list");
        return null;
    }

    public boolean editUserFirstName(UUID id, String name) {
        Advisor advisor = findUser(id);
        if(advisor == null)
            return false;
        advisor.setFirstName(name);
        return true;
    }

    public boolean editUserLastName(UUID id, String name) {
        Advisor advisor = findUser(id);
        if(advisor == null)
            return false;
        advisor.setLastName(name);
        return true;
    }
    public boolean editUserEmail(UUID id, String email) {
        Advisor advisor = findUser(id);
        if(advisor == null)
            return false;
        advisor.setEmail(email);
        return true;
    }
    public boolean editUserUSCID(UUID id, UUID newID) {
        Advisor advisor = findUser(id);
        if(advisor == null)
            return false;
        advisor.setID(newID);
        return true;
    }

    public boolean editUserPassword(UUID id, String newPassword) {
        Advisor advisor = findUser(id);
        if(advisor == null)
            return false;
        advisor.setPassword(newPassword);
        return true;
    }

    public boolean deleteUser(UUID id) {
        Advisor advisor = findUser(id);
        if(advisor == null)
            return false;
        for(int i = 0; i < this.advisorList.size(); i++) {
            if(this.advisorList.get(i).getID().equals(id)) {
                this.advisorList.remove(i);
                return true;
            }
        }
        return false;
    }
}
