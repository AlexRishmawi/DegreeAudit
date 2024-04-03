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
        Student b = new Student("T", "Le", "tl210303", "4fgef", "thwidwnd");
        userList.addUser(b);
        userList.writeToFile();
    }

}
