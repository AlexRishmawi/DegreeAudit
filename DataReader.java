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
        HashMap<UUID, Admin> adminMap = new HashMap<>();
        HashMap<UUID, Advisor> advisorMap = new HashMap<>();
        HashMap<UUID, Student> studentMap = new HashMap<>();
    
        // Read advisor, admin, student in order to get the most performance
        String[] FILES = {ADVISOR_FILE_NAME, ADMIN_FILE_NAME, STUDENT_FILE_NAME};

        for(String file: FILES) {
            try {
                FileReader reader = new FileReader(file);
                JSONArray readerJSON = (JSONArray) new JSONParser().parse(reader);

                // Read and crate parent USER
                for(int i = 0; i < readerJSON.size(); i++) {
                    JSONObject userJSON = (JSONObject) readerJSON.get(i);
                    UUID id = UUID.fromString((String) userJSON.get(USER_ID));
                    String firstName = (String) userJSON.get(USER_FIRST_NAME);
                    String lastName = (String) userJSON.get(USER_LAST_NAME);
                    String email = (String) userJSON.get(USER_EMAIL);
                    String password = (String) userJSON.get(USER_PASSWORD);
                    UserType type = UserType.valueOf((String)userJSON.get(USER_TYPE));

                    User user = new User(id, type, firstName, lastName, email, password);

                    if(type.equals(UserType.STUDENT)) {
                        ClassLevel level = ClassLevel.valueOf((String) userJSON.get(STUDENT_CLASSIFICATION))
                        UUID advisorId = UUID.fromString((String) userJSON.get(STUDENT_ADVISOR_ID));
                        ArrayList<String> notes = new ArrayList<>();
                        JSONArray noteJSON = (JSONArray) userJSON.get(STUDENT_NOTES)
                        for(int j = 0; j < noteJSON.size(); j++) {
                            notes.add((String) noteJSON.get(j));
                        }
                        UUID degreeID = UUID.fromString((String) userJSON.get(STUDENT_DEGREE_ID));
                        Integer instituteGPA = (Integer) userJSON.get(STUDENT_INSTITUTE_GPA);
                        Integer programGPA = (Integer) userJSON.get(STUDENT_PROGRAM_GPA);
                        
                        //TODO: initalize student here and add to studentMap
                        
                    } else if(type.equals(UserType.ADVISOR)) {
                        ArrayList<Student> studentList = new ArrayList<>();
                        JSONArray studentJSON = (JSONArray) userJSON.get(ADVISOR_STUDENT_LIST);
                        for(int j = 0; j < studentJSON.size(); j++) {
                            UUID studentID = UUID.fromString((String) studentJSON.get(j));
                            studentList.add(studentMap.get(studentID));
                        }

                        // Todo: initalize advisor and add to advisorMap

                    } else if(type.equals(UserType. ADMIN)) {
                        ArrayList<Advisor> advisorList = new ArrayList<>();
                        JSONArray advisorJSON = (JSONArray) userJSON.get(ADMIN_ADVISOR_LIST);
                        for(int j = 0; j < advisorJSON.size(); j++) {
                            UUID studentID = UUID.fromString((String) advisorJSON.get(j));
                            advisorList.add(advisorMap.get(studentID));
                        }

                        //TODO: initialize admin and add to adminMap
                    } else {
                        System.err.println("ERROR --- Couldn't match the UserType" + user.getUserType());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("ERROR --- reading file:" + file);
            }
        }
        
        // get all 3 types of User
        ArrayList<User> users = new ArrayList<>();
        users.addAll(adminMap.values());
        users.addAll(advisorMap.values());
        users.addAll(studentMap.values());
        return users;
    }

    public ArrayList<Program> loadProgram() {
        ArrayList<Program> program = new ArrayList<>();
        try {
            FileReader reader = new FileReader(PROGRAM_FILE_NAME);
            JSONArray readerJSON = (JSONArray) new JSONParser().parse(reader);
            

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("ERROR --- Couldn't load Program file");
        }
        return;
    }

    public ArrayList<Course> loadCourse() {
        HashMap<String, Course> courseMap = new HashMap<>();
        HashMap<Course, ArrayList<Object>> preMap = new HashMap<>();

        ArrayList<Course> courses = new ArrayList<>();
        try {
            FileReader reader = new FileReader(COURSE_FILE_NAME);
            JSONArray readerJSON = (JSONArray) new JSONParser().parse(reader);
            for(int i = 0; i < readerJSON.size(); i++) {
                JSONObject courseJSON = (JSONObject) readerJSON.get(i);
                UUID id = UUID.fromString((String) courseJSON.get(COURSE_ID));
                String name = (String) courseJSON.get(COURSE_NAME);
                String department = (String) courseJSON.get(COURSE_DEPARTMENT);
                String code = (String) courseJSON.get(COURSE_CODE);
                Integer credit = (Integer) courseJSON.get(COURSE_CREDIT_HOURS);

                ArrayList<Season> semesterOffer = new ArrayList<>();
                JSONArray semesterJSON = (JSONArray) courseJSON.get(COURSE_SEMESTER_OFFER);
                for(int j = 0; j < semesterJSON.size(); j++) {
                    semesterOffer.add(Season.valueOf(((String)semesterJSON.get(j)).toUpperCase()));
                }
                
                String description = (String) courseJSON.get(COURSE_DESCRIPTION);
                String gradeToPass = (String) courseJSON.get(COURSE_GRADE_TO_PASS);

                // ArrayList<Object> new
                


            }
            
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("ERROR --- Couldn't load Course file");
        }
        return new ArrayList<>();
    }
}

