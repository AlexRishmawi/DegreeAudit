import java.util.Scanner;
public class UI {
    private Scanner scanner;
    private System application;
    
    public UI(Scanner scanner, System application) {
        this.scanner = scanner;
        this.application = application;
    }
    
    public void run() {
        DegreeWork degreeWork = new DegreeWork();
        degreeWork.login("Brax", "West", "password");
        degreeWork.displayDegreeProgress();

    }
    
    private void display() {
        
    }
}

