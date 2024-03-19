import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class DataWriter extends DataConstants {
    private JSONParser parser = new JSONParser();

    @SuppressWarnings("unchecked")
    public static void writeUser() {
        UserList userList = UserList.getInstance();
        ArrayList<User> allUsers = userList.getAllUsers();
        JSONArray allStudentObject = new JSONArray();
        JSONArray allAdvisorObject = new JSONArray();

        for(User user: allUsers) {
            HashMap<String, Object> userObject = new HashMap<>();
            userObject.put(USER_ID, user.getID().toString());
            userObject.put(USER_TYPE, user.getUserType().toString());
            userObject.put(USER_FIRST_NAME, user.getFirstName());
            userObject.put(USER_LAST_NAME, user.getLastName());
            userObject.put(USER_EMAIL, user.getEmail());
            userObject.put(USER_PASSWORD, user.getPassword());

            if (user instanceof Student) {
                fillStudentDetails(userObject, (Student) user);
                allStudentObject.add(new JSONObject(userObject));
            } else if (user instanceof Advisor) {
                fillAdvisorDetails(userObject, (Advisor) user);
                allAdvisorObject.add(new JSONObject(userObject));
            }
        }

        writeToFile("./json/student_testing.json", allStudentObject);
        writeToFile("./json/advisor_testing.json", allAdvisorObject);
        // writeToFile(STUDENT_FILE_NAME, allStudentObject);
        // writeToFile(ADVISOR_FILE_NAME, allAdvisorObject);
    }
    
    @SuppressWarnings("unchecked")
    private static void fillAdvisorDetails(HashMap<String, Object> map, Advisor advisor) {
        map.put(ADVISOR_IS_ADMIN, advisor.getIsAdmin().toString());
        JSONArray studentListJson = new JSONArray();
        advisor.getStudentList().forEach(student -> studentListJson.add(student.getID().toString()));
        map.put(ADVISOR_STUDENT_LIST, studentListJson);
    }

    @SuppressWarnings("unchecked")
    private static void fillStudentDetails(HashMap<String, Object> map, Student student) {
        map.put("type", "Student");
        map.put(STUDENT_ID, student.getStudentID());
        map.put(STUDENT_CLASSIFICATION, student.getLevel().toString());
        map.put(STUDENT_ADVISOR_ID, student.getAdvisor().getID().toString());
        
        JSONArray notesJson = new JSONArray();
        for (String note : student.getNotes()) {
            notesJson.add(note);
        }
        map.put(STUDENT_NOTES, notesJson);
        
        map.put(STUDENT_DEGREE_ID, student.getDegree().getID().toString());
        map.put(STUDENT_INSTITUTE_GPA, student.getInstituteGPA());
        map.put(STUDENT_PROGRAM_GPA, student.getProgramGPA());
        map.put(STUDENT_STATUS, student.getStatus());
        map.put(STUDENT_COMPLETED_COURSES, serializeCompleteCourses(student.getCompletedCourse()));
        map.put(STUDENT_ALL_SEMESTERS, serializeAllSemesters(student.getAllSemester()));
        map.put(STUDENT_CURRENT_SEMESTER, serializeSemester(student.getCurrentSemester()));
    }

    private static JSONObject serializeCompleteCourses(HashMap<Course, String> completedCourse) {
        HashMap<String, String> completedCourseByID = new HashMap<>();
        for (Map.Entry<Course, String> entry : completedCourse.entrySet()) {
            String courseID = entry.getKey().getID().toString();
            String graded = entry.getValue();
            completedCourseByID.put(courseID, graded);
        }

        return new JSONObject(completedCourseByID);
    }

    @SuppressWarnings("unchecked")
    private static JSONArray serializeAllSemesters(ArrayList<Semester> semesters) {
        JSONArray semestersJson = new JSONArray();
        semesters.forEach(semester -> semestersJson.add(serializeSemester(semester)));
        return semestersJson;
    }

    @SuppressWarnings("unchecked")
    private static JSONObject serializeSemester(Semester semester) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put(SEMESTER_SEASON, semester.getSeason().toString());
        map.put(SESMESTER_YEAR, semester.getYear());
        map.put(SEMSESTER_LIMIT, semester.getCreditLimit());
        JSONArray coursesJson = new JSONArray();
        semester.getCourses().forEach(course -> coursesJson.add(course.getID().toString()));
        map.put(SESMESTER_COURSES, coursesJson);
        return new JSONObject(map);
    }

    private static void writeToFile(String filePath, JSONArray jsonArray) {
        String prettyPrintedJsonString = prettyPrintJsonArray(jsonArray);

        try (FileWriter file = new FileWriter(filePath)) {
            file.write(prettyPrintedJsonString);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private static String prettyPrintJsonArray(JSONArray jsonArray) {
        StringBuilder prettyJsonBuilder = new StringBuilder("[\n");
        jsonArray.forEach(item -> prettyJsonBuilder.append(prettyPrintJsonObject((JSONObject) item, 1)).append(",\n"));
        if (prettyJsonBuilder.length() > 2) {
            prettyJsonBuilder.setLength(prettyJsonBuilder.length() - 2);
        }
        prettyJsonBuilder.append("\n]");
        return prettyJsonBuilder.toString();
    }

    @SuppressWarnings("unchecked")
    private static String prettyPrintJsonObject(JSONObject jsonObject, int indentLevel) {
        StringBuilder prettyJson = new StringBuilder("{\n");
        jsonObject.forEach((key, value) -> {
            prettyJson.append(getIndent(indentLevel)).append("\"").append(key).append("\": ");
            if (value instanceof JSONObject) {
                prettyJson.append(prettyPrintJsonObject((JSONObject) value, indentLevel + 1));
            } else if (value instanceof JSONArray) {
                prettyJson.append(value.toString());
            } else {
                prettyJson.append(value instanceof String ? "\"" + value + "\"" : value);
            }
            prettyJson.append(",\n");
        });
        if (prettyJson.length() > 2) {
            prettyJson.setLength(prettyJson.length() - 2);
        }
        prettyJson.append("\n").append(getIndent(indentLevel - 1)).append("}");
        return prettyJson.toString();
    }

    private static String getIndent(int level) {
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < level; i++) {
            indent.append("\t");
        }
        return indent.toString();
    }

    public static void main(String[] args) {
        UserList userList = UserList.getInstance();
        // System.out.println(userList.getAllUsers().toString());
        DataWriter.writeUser();
    }
}
