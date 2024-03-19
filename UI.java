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
        Scanner keyboard = new Scanner(System.in);
        System.out.println("--------------------DegreeWorks--------------------");
        System.out.println("1. Login");
        System.out.println("2. Create new account");
        System.out.println("What would you like to do?");
        int choice = Integer.parseInt(keyboard.nextLine());
        switch (choice) {
            case 1:
                loginScenario(keyboard, degreeWork);
                break;
            case 2:
                signupScenario(keyboard, degreeWork);
                break;
            default:
                System.out.println("Invalid Input");
        }
    }
    
    private void loginScenario(Scanner keyboard, DegreeWork degreeWork) {
        String email;
        while (true) {
            System.out.print("Email: ");
            email = keyboard.nextLine();
            if (email.endsWith("@email.sc.edu")){
                break;
            }
            else {
                System.out.println("Invalid email. Please enter a valid email.");
            }
        }
        System.out.print("Please enter your password: ");
        String password = keyboard.nextLine();
        boolean loginSuccessful = degreeWork.login(email, password);
        if (loginSuccessful) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Login failed. Please try again.");
            loginScenario(keyboard, degreeWork);
        }
        if(degreeWork.isStudent()) {
            System.out.println(degreeWork.displayDegreeProgress());
        }
        else {
            System.out.print("Enter your students USCID: ");
            String uscID = keyboard.nextLine();
            degreeWork.findStudent(uscID);
            System.out.println(degreeWork.displayDegreeProgress());   
        }
    }
    private void signupScenario(Scanner keyboard, DegreeWork degreeWork) {
        System.out.println("Create a new account:");
        System.out.println("1. Student");
        System.out.println("2. Advisor or Admin");
        int choice = Integer.parseInt(keyboard.nextLine());

        switch (choice) {
            case 1:
                studentRegistration(keyboard, degreeWork);
                break;
            case 2:
                advisorRegistration(keyboard, degreeWork);
                break;
            default:
                break;
        }
    }

    private void studentRegistration(Scanner keyboard, DegreeWork degreeWork) {
        System.out.println("Please fill out the following information:");
        System.out.print("First Name: ");
        String name = keyboard.nextLine();
        System.out.print("Last Name: ");
        String lastName = keyboard.nextLine();
        String email;
        while (true) {
            System.out.print("Email: ");
            email = keyboard.nextLine();
            if (email.endsWith("@email.sc.edu")){
                break;
            }
            else {
                System.out.println("Invalid email. Please enter a valid email.");
            }
        }
        System.out.print("Password: ");
        String password = keyboard.nextLine();
        System.out.print("USCID: ");
        String uscID = keyboard.nextLine();
        degreeWork.createUser("Student", name, lastName, password, uscID, email);
        degreeWork.login(email, password);
        System.out.println(degreeWork.displayDegreeProgress());
    }

    private void advisorRegistration(Scanner keyboard, DegreeWork degreeWork) {
        System.out.println("Please fill out the following information:");
        System.out.print("First Name: ");
        String name = keyboard.nextLine();
        System.out.print("Last Name: ");
        String lastName = keyboard.nextLine();
        String email;
        while (true) {
            System.out.print("Email: ");
            email = keyboard.nextLine();
            if (email.endsWith("@email.sc.edu")){
                break;
            }
            else {
                System.out.println("Invalid email. Please enter a valid email.");
            }
        }
        System.out.print("Password: ");
        String password = keyboard.nextLine();
        System.out.println("Are you an Admin(true or false): ");
        boolean title = Boolean.parseBoolean(keyboard.nextLine());
        degreeWork.createAdvisor(name, lastName, email, password, new ArrayList<Student>(), title); 
        degreeWork.login(email, password);
        System.out.println("Enter your students USCID: ");
        String uscID = keyboard.nextLine();
        degreeWork.findStudent(uscID);
        System.out.println(degreeWork.displayDegreeProgress());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UI ui = new UI(scanner);
        ui.run();
    }
}

