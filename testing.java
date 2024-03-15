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
        Advisor test = new Advisor("Thien", "Le", "", "1234", true);
        Student newStudent = new Student("Hi", "ho", "", "12345");
        Student newStudent2 = new Student("Hi", "ho", "", "12345");

        test.addStudent(newStudent);
        test.addStudent(newStudent2);
        System.out.println(test.toString());
    }

}
