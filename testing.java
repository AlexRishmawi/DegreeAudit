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
        Student student = (Student) userList.getUser(UUID.fromString("465ff326-776a-4d98-af63-3b57551bb3b4"));
        System.out.println(student);
        // System.out.print(student.getSemesterPlans().size());
    }

}
