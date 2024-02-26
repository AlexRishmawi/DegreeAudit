
import java.util.ArrayList;
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

    public ArrayList<Course> getCourseList() { return this.courseList; }
    public ArrayList<Advisor> getAdvisorList() { return this.advisorList; }

    public void setCourseList(ArrayList<Course> courses) { this.courseList = courses; }
    public void setAdvisorList(ArrayList<Advisor> advisors) { this.advisorList = advisors; }

    public boolean addCourse(String courseName, int creditHours, ArrayList<Season> semesterOffer, 
                                String department, String courseCode, 
                                ArrayList<Course> prerequisites, String description, 
                                String gradetoPass)
    {
        Course course = new Course(courseName, creditHours, semesterOffer, department, courseCode, prerequisites, description, gradetoPass);
        this.courseList.add(course);
        return false;
    }

    public boolean editCourseName(UUID id, String name){
        Course course = findCourse(id);
        if (course == null)
            return false;
        course.setCourseName(name);
        return true;
    }

    public boolean editCourseDepartment(UUID id, String department){
        Course course = findCourse(id);
        if (course == null)
            return false;
        course.setCourseName(department);
        return true;
    }

    public boolean editCourseCode(UUID id, String code){
        Course course = findCourse(id);
        if (course == null)
            return false;
        course.setCode(code);
        return true;
    }

    public boolean editCourseCredit(UUID id, int credit){
        Course course = findCourse(id);
        if (course == null)
            return false;
        course.setCreditHours(credit);
        return true;
    }

    public boolean editCourseSemesterOffer(UUID id, ArrayList<Season> season){
        Course course = findCourse(id);
        if (course == null)
            return false;
        course.setSemesterOffer(season);
        return true;
    }

    public boolean editCoursePrerequisites(UUID id, ArrayList<Course> prerequisites){
        Course course = findCourse(id);
        if (course == null)
            return false;
        course.setPrerequisites(prerequisites);
        return true;
    }

    public boolean editCourseDescription(UUID id, String description){
        Course course = findCourse(id);
        if (course == null)
            return false;
        course.setDescription(description);
        return true;
    }

    public boolean removeCourse(UUID id){
        Course course = findCourse(id);
        if (course == null)
            return false;
        this.courseList.remove(course);
        return true;
    }

    public Course findCourse(UUID id){
        if(this.courseList.size() == 0) {
            System.out.println("WARN --- Course List is empty => can't use findCourse method");
            return null;
        }
        for(int i = 0; i < this.courseList.size(); i++) {
            if(courseList.get(i).getID().equals(id)) {
                return this.courseList.get(i);
            }
        }
        return null;
    }

    public Advisor findUser(UUID id) {
        if(this.advisorList.size() == 0) {
            System.out.println("WARN --- Advisor List is empty => can't use findUser method");
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
        this.advisorList.remove(advisor);
        return true;
    }
}
