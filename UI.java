import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
public class UI {
    private Scanner scanner;
    private System application;
    
    public UI(Scanner scanner) { //System application) {
        this.scanner = scanner;
        //this.application = application;
    }
    
    public void run() {

        DegreeWork degreeWork = new DegreeWork();
        HashMap<Course, Integer> majorCourses = new HashMap<Course, Integer>();
        Degree degree = new Degree("Bachelor", "Computer Science", 120, majorCourses, new ArrayList<ElectiveCategory>());
        Student brax = degreeWork.createStudent("Brax", "West", "Bwest@email.sc.edu", "password","X23456789", "Junior", new Advisor("John", "Doe", "jDoe@email.sc.edu", "password", false), new ArrayList<>(), degree, 0, 0, "Good Standing");
        degreeWork.login("Brax", "West", "password");
        
        degreeWork.setCurrentUser(brax);
        degreeWork.displayDegreeProgress();
        //Generate 8 semester plan
        for(Semester semester : brax.getAllSemester()) {
            semester.toString();
        }
        
        Advisor osbert = degreeWork.createAdvisor("Osbert", "Odden", "oOdden@email.sc.edu", "password", new ArrayList<Student>(),true);
        Student tawnie = degreeWork.createStudent("Tawnie", "Hill", "tHill@email.sc.edu", "password", "X33457890", "Sophomore", osbert, new ArrayList<>(), degree, 0, 0, "Good Standing");

        osbert.setCurrentStudent(tawnie.getID());
        degreeWork.setCurrentUser(osbert);
        degreeWork.displayDegreeProgress();
        

    }
    
    private void display() {
        
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UI ui = new UI(scanner);
        ui.run();
    }
}

