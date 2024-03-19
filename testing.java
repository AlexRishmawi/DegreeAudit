import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Array;

import java.util.ArrayList;
import java.util.UUID;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class testing {
    public static void main(String[] args) {
        UserList studentList = UserList.getInstance();
        // System.out.println(studentList.getAllUsers().size());
        UUID id = UUID.fromString("6e30c187-5592-4d8a-91e4-e874f34a41cd");
        Student student = (Student) studentList.getUser(id);
        student.initializeSemesterPlan();
        // System.out.println(student.toString());

        // DegreeList degreeList = DegreeList.getInstance();
        // System.out.println(degreeList.getAllDegree());

        // CourseList courseList = CourseList.getInstance();
        // System.out.println(courseList.getAllCourse().size());
    }

}
