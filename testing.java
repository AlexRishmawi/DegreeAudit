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
        System.out.println(studentList.getAllUsers().size());

        DegreeList degreeList = DegreeList.getInstance();
        System.out.println(degreeList.getAllDegree().size());

        CourseList courseList = CourseList.getInstance();
        System.out.println(courseList.getAllCourse().size());
    }

}
