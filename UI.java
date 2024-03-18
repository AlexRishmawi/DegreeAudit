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
        DegreeWork degreeWork = new DegreeWork();
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please enter your email: ");
        String email = keyboard.nextLine();
        System.out.println("Please enter your password: ");
        String password = keyboard.nextLine();
        System.out.println((degreeWork.login(email, password)) ? "Login successful! Pulling up Current Progress" : "Login failed");
        System.out.println(degreeWork.displayDegreeProgress());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UI ui = new UI(scanner);
        ui.run();
    }
}

