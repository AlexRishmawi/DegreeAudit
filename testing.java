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
        UserList userList = UserList.getInstance();
        Student student = (Student) userList.getUser(UUID.fromString("6e30c187-5592-4d8a-91e4-e874f34a41cd"));
        // ArrayList<Semester> allSemester = student.getAllSemester();
        // for(Semester s: allSemester) {
        //     System.out.println(s.toString());
        // }
        // for(Course course: complete.keySet()) {
        //     System.out.println(complete.get(course));
        // }
    }

}
