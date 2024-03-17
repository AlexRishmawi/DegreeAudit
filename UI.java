import java.util.Scanner;
import java.util.ArrayList;
public class UI {
    private Scanner scanner;
    private System application;
    
    public UI(Scanner scanner) { //System application) {
        this.scanner = scanner;
        //this.application = application;
    }
    
    public void run() {
        DegreeWork degreeWork = new DegreeWork();
        Student student = degreeWork.createStudent("Brax", "West", "Bwest@email.sc.edu", "password", "Junior", null, null, degree, 0, 0, "Good Standing");
        degreeWork.login("Brax", "West", "password");
        Degree degree = new Degree("Bachelor", "Computer Science", 120, null, null);
        degreeWork.setCurrentUser(student);
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

