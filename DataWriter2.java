	
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataWriter2 extends DataConstants {

    @SuppressWarnings("unchecked")
    public static void writeUser() {
        // JSONParser parser = new JSONParser();
        // Object obj  = parser.parse(content);
        // JSONArray array = new JSONArray();
        // array.add(obj);
        UserList userList = UserList.getInstance();
        ArrayList<User> allUsers = userList.getAllUsers();
        JSONArray allStudentObject = new JSONArray();
        JSONArray allAdvisorObject = new JSONArray();
        
        for(User user: allUsers) {
            JSONObject userObject = new JSONObject();
            userObject.put(USER_ID, user.getID());
            userObject.put(USER_TYPE, user.getUserType().toString());
            userObject.put(USER_FIRST_NAME, user.getFirstName());
            userObject.put(USER_LAST_NAME, user.getLastName());
            userObject.put(USER_EMAIL, user.getEmail());
            userObject.put(USER_PASSWORD, user.getPassword());

            if(user.getUserType().equals(UserType.STUDENT)) {
                Student student = (Student) user;
                userObject.put(STUDENT_ID, student.getStudentID());
                userObject.put(STUDENT_CLASSIFICATION, student.getLevel().toString());
                userObject.put(STUDENT_ADVISOR_ID, student.getAdvisor().getID().toString());

                JSONArray studentNote = new JSONArray();
                for(String note: student.getNotes()) {
                    studentNote.add(note);
                }
                userObject.put(STUDENT_NOTES, studentNote);

                userObject.put(STUDENT_DEGREE_ID, student.getDegree().getID().toString());
                userObject.put(STUDENT_INSTITUTE_GPA, student.getInstituteGPA());
                userObject.put(STUDENT_PROGRAM_GPA, student.getProgramGPA());
                userObject.put(STUDENT_STATUS, student.getStatus());
                
                HashMap<Course, String> completedCourse = student.getCompletedCourse();
                JSONObject completedCourseByID = new JSONObject();
                for (Map.Entry<Course, String> entry : completedCourse.entrySet()) {
                    String courseID = entry.getKey().getID().toString();
                    String graded = entry.getValue();
                    completedCourseByID.put(courseID, graded);
                }
                userObject.put(STUDENT_COMPLETED_COURSES, (JSONObject) completedCourseByID);

                allStudentObject.add(userObject);
            }
        }

        try (FileWriter file = new FileWriter("./json/user_testing.json")) {
 
            file.write(allStudentObject.toJSONString());
            // file.flush();
            file.close();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        writeUser();
    }
}
