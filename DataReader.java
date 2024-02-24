import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Array;

import java.util.ArrayList;
import java.util.UUID;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataReader extends DataConstants{

    public static ArrayList<User> loadUser() {
        ArrayList<User> users = new ArrayList<User>();
        
        String[] FILES = { ADMIN_FILE_NAME, ADVISOR_FILE_NAME, STUDENT_FILE_NAME };

        JSONParser parser = new JSONParser();

        for (String file: FILES) {
            try {
                FileReader reader = new FileReader(file.getKey());
                JSONArray usersJSON = (JSONArray) new JSONParser().parse(reader);
                
                for(int i = 0; i < usersJSON.size(); i++) {
                    JSONObject userJSON = (JSONObject) usersJSON.get(i);
                    UUID id = UUID.fromString((String) userJSON.get(USER_ID));
                    String firstName = (String) userJSON.get(USER_FIRST_NAME);
                    String lastName = (String) userJSON.get(USER_LAST_NAME);
                    String email = (String) userJSON.get(USER_EMAIL);
                    String password = (String) userJSON.get(USER_PASSWORD);
                    String type = (String) userJSON.get(USER_TYPE);
                    
                    if(type.equalsIgnoreCase("Admin")) {
                        // TODO: Add AdvisorList to admin
                        ArrayList<Advisor> advisorList = new ArrayList<>();
                        users.add(new Admin(id, firstName, lastName, email, password, advisorList));
                    } else if(type.equalsIgnoreCase("Advisor")) {
                        // TODO: Add studentList to student
                        ArrayList<Student> studentList = new ArrayList<>();
                        users.add(new Advisor(id, firstName, lastName, email, password, studentList));
                    } else if(type.equalsIgnoreCase("Student")) {
                        // TODO: add Student info
                        UUID advisorID = UUID.fromString((String) userJSON.get(STUDENT_ADVISOR_ID));
                        // ArrayList
                        // users.add(new Advisor(id, firstName, lastName, email, password));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    public ArrayList<Course> loadProgram(Program program) {
        return new ArrayList<>();        
    }

    public ArrayList<Course> loadCourse() {
        return new ArrayList<>();
    }
}

