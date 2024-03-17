import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class DataWriterTest {
    public static void main(String[] args) {
        // Create Courses
        Course course1 = new Course(UUID.randomUUID(), "Intro to Programming", "CS", "CS101", 3, new ArrayList<>(Arrays.asList(Season.FALL, Season.SPRING)), new ArrayList<>(), "Introductory programming course.", "B");
        Course course2 = new Course(UUID.randomUUID(), "Data Structures", "CS", "CS201", 4, new ArrayList<>(Arrays.asList(Season.FALL, Season.SPRING)), new ArrayList<>(Arrays.asList(course1)), "Data structures course.", "C");
        
        // Student Degree
        Degree studentDegree = new Degree("Computer Science", 30, new ArrayList<>(Arrays.asList(course1, course2)), null);

        //Student List for advisor
        Advisor advisor2 = new Advisor("John", "Doe", "john.doe@example.com", "password123", true);
        Student exStudent = new Student("Alice", "Wonderland", "alice@example.com", "password456", "Junior", advisor2, new ArrayList<>(Arrays.asList("Good progress", "Needs improvement in math")), studentDegree, 3.5, 3.7, "Active");
        @SuppressWarnings("unused")
        ArrayList<Student> advisorStudentList = new ArrayList<>(Arrays.asList(exStudent));

        // Create Semester with courses
        ArrayList<Course> courseList = new ArrayList<>();
        courseList.add(course1);
        courseList.add(course2);
        Semester semester1 = new Semester("FALL", 2023, 18, courseList);
        
        // Create an Advisor
        Advisor advisor1 = new Advisor("John", "Doe", "john.doe@example.com", "password123", true);
        
        // Create a Student and assign the semester and advisor
        Student student1 = new Student("Alice", "Wonderland", "alice@example.com", "password456", "Junior", advisor1, new ArrayList<>(Arrays.asList("Good progress", "Needs improvement in math")), studentDegree, 3.5, 3.7, "Active");
        student1.setAllSemester(new ArrayList<>(Arrays.asList(semester1)));
        
        // Use DataWriter to write the student and advisor to their respective JSON files
        DataWriter dataWriter = new DataWriter();
        dataWriter.writeUser(student1, 'a'); // Assuming 'a' is for append mode
        //dataWriter.writeUser(advisor1, 'a');
        
        System.out.println("Student and Advisor data successfully written to JSON files.");
    }
}
