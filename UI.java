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
        scenario_1();
    }
    
    private void scenario_1() {
        HashMap<Course, Integer> majorCourses = new HashMap<Course, Integer>();
        Degree degree = new Degree("Bachelor", "Computer Science", 120, majorCourses, new ArrayList<ElectiveCategory>());
        Student brax = new Student("Brax", "West", "Bwest@email.sc.edu", "password","X23456789", "Junior", new Advisor("John", "Doe", "jDoe@email.sc.edu", "password", false), new ArrayList<>(), degree, 0, 0, "Good Standing");
        
        //brax.toString();
        DegreeWork degreeWork = new DegreeWork();
        degreeWork.createStudent("Brax", "West", "Bwest@email.sc.edu", "password","X23456789", "Junior", new Advisor("John", "Doe", "jDoe@email.sc.edu", "password", false), new ArrayList<>(), degree, 0, 0, "Good Standing");
        degreeWork.login("Brax", "West", "password");
        
        degreeWork.displayDegreeProgress();
        //Generate 8 semester plan
        for(Semester semester : brax.getAllSemester()) {
            semester.toString();
        }
        //Advisor osbert = new Advisor("Osbert", "Odden", "oOdden@email.sc.edu", "password", new ArrayList<Student>(),true);
        Advisor osbert = degreeWork.createAdvisor("Osbert", "Odden", "oOdden@email.sc.edu", "password", new ArrayList<Student>(),true);
        //Student tawnie = new Student("Tawnie", "Hill", "tHill@email.sc.edu", "password", "X33457890", "Sophomore", osbert, new ArrayList<>(), degree, 0, 0, "Good Standing");
        Student tawnie = degreeWork.createStudent("Tawnie", "Hill", "tHill@email.sc.edu", "password", "X33457890", "Sophomore", osbert, new ArrayList<>(), degree, 0, 0, "Good Standing");

        osbert.setCurrentStudent(tawnie.getID());
        degreeWork.setCurrentUser(osbert);
        //tawnie.toString();
        degreeWork.displayDegreeProgress();
        //degreeWork.getUserList().add(brax);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UI ui = new UI(scanner);
        ui.run();
    }
}

