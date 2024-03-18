import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

public class DataWriterTest {
    public static void main(String[] args) {
        // Create prerequisite course(s) without prerequisites for simplicity
        Course math101 = new Course(UUID.randomUUID(), "Mathematics", "101", "Calculus I", "Differential calculus.", 3,
                                    new ArrayList<>(Arrays.asList(Season.FALL, Season.SPRING)), new ArrayList<>());

        // Use the prerequisite course to create a Prerequisites object for a new course
        Prerequisites calcPrerequisites = new Prerequisites(1, "C", new ArrayList<>(Arrays.asList(math101)));
        
        // Now create a course with prerequisites
        Course cs101 = new Course(UUID.randomUUID(), "CS", "101", "Intro to Programming", "Introduction to programming concepts.", 4,
                                  new ArrayList<>(Arrays.asList(Season.FALL, Season.SPRING)), new ArrayList<>(Arrays.asList(calcPrerequisites)));

        // Example Degree, Advisor, and Student setup
        HashMap<Course, Integer> majorCourses = new HashMap<>();
        majorCourses.put(cs101, 1); // Assuming the Integer represents the semester the course is recommended

        Degree degree = new Degree(UUID.randomUUID(), "Bachelor of Science", "Computer Science", 120, majorCourses, new ArrayList<>());
        Advisor advisor = new Advisor(UUID.randomUUID(), "Jane", "Doe", "jane.doe@example.com", "securepassword", new ArrayList<>(), true);

        Student student = new Student(UUID.randomUUID(), "Alice", "Wonderland", "alice@example.com", "password123", "X123456789",
                                      "Junior", advisor, new ArrayList<>(Arrays.asList("Good progress", "Participates actively in class")),
                                      degree, 3.5, 3.7, "Active", null, new ArrayList<>());

        // Setting Complete Courses for the Student
        HashMap<Course, String> completeCourses = new HashMap<>();
        completeCourses.put(math101, "A"); // Assuming Math 101 was completed with grade A
        student.setCompleteCourses(completeCourses);

        // Semester setup
        Semester currentSemester = new Semester("FALL", 2023, 18, new ArrayList<>(Arrays.asList(cs101)));
        student.setCurrentSemester(currentSemester);
        student.setAllSemester(new ArrayList<>(Arrays.asList(currentSemester)));

        // Serialize to JSON using DataWriter
        DataWriter dataWriter = new DataWriter();
        dataWriter.writeUser(student);
        dataWriter.writeUser(advisor);

        System.out.println("Student and Advisor data successfully written to JSON files.");
    }
}
