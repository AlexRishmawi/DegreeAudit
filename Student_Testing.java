import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.HashMap;
import java.util.UUID;

import org.junit.*;

public class Student_Testing {
    @Test
    public void test_first_constructor() {
        Student student_1 = new Student("T", "Le", "@gmail", "password", "studentID");
        Student student_2 = new Student("T", "Le", "@gmail", "password", "studentID");
        // System.out.println(student_1.toString());
        assertNotEquals(student_1, student_2);
    }

    @Test
    public void test_second_constructor() {
        UserList userList = UserList.getInstance();
        Student sample_Student = (Student) userList.getUser(UUID.fromString("465ff326-776a-4d98-af63-3b57551bb3b4"));

        Student student_1 = new Student("T", "Le", "@gmail", "password", "studentID", sample_Student.getLevel().toString(),
                                                    sample_Student.getAdvisor(),
                                                    sample_Student.getNotes(),
                                                    sample_Student.getDegree(),
                                                    sample_Student.getInstituteGPA(),
                                                    sample_Student.getProgramGPA(),
                                                    sample_Student.getStatus(),
                                                    sample_Student.getCompletedCourse(),
                                                    sample_Student.getCurrentSemester(),
                                                    sample_Student.getAllSemester());

        Student student_2 = new Student("T", "Le", "@gmail", "password", "studentID", sample_Student.getLevel().toString(),
                                                    sample_Student.getAdvisor(),
                                                    sample_Student.getNotes(),
                                                    sample_Student.getDegree(),
                                                    sample_Student.getInstituteGPA(),
                                                    sample_Student.getProgramGPA(),
                                                    sample_Student.getStatus(),
                                                    sample_Student.getCompletedCourse(),
                                                    sample_Student.getCurrentSemester(),
                                                    sample_Student.getAllSemester());
        assertNotEquals(student_1, student_2);                             
    }

    @Test
    public void test_8_semester_plan() {
        Student student_1 = new Student("T", "Le", "@gmail", "password", "studentID");
        Student student_2 = new Student("T", "Le", "@gmail", "password", "studentID");
        assertEquals(student_1.getAllSemester(), student_2.getAllSemester());

        
        CourseList courseList = CourseList.getInstance();
        Course course = courseList.getCourse(UUID.fromString("e53c6dfc-fdeb-458e-a2b3-a7d076dfbb77"));
        student_1.setCourseCompleted(course, "c");
        student_2.setCourseCompleted(course, "c");


        assertEquals(student_1.getAllSemester(), student_2.getAllSemester());
    }
}
