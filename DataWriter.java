import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.UUID;

public class DataWriter extends DataConstants {
    private JSONParser parser = new JSONParser();

    public void writeUser(User user) {
        JSONObject userJson = userToJson(user);
        String filePath = user instanceof Student ? STUDENT_FILE_NAME : ADVISOR_FILE_NAME;
        appendToFile(filePath, userJson);
    }

    private JSONObject userToJson(User user) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put(USER_ID, user.getID().toString());
        map.put(USER_TYPE, user instanceof Student ? "Student" : "Advisor");
        map.put(USER_FIRST_NAME, user.getFirstName());
        map.put(USER_LAST_NAME, user.getLastName());
        map.put(USER_EMAIL, user.getEmail());
        map.put(USER_PASSWORD, user.getPassword());

        if (user instanceof Student) {
            fillStudentDetails(map, (Student) user);
        } else if (user instanceof Advisor) {
            fillAdvisorDetails(map, (Advisor) user);
        }

        return new JSONObject(map);
    }

    private void fillStudentDetails(LinkedHashMap<String, Object> map, Student student) {
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
        map.put(STUDENT_COMPLETED_COURSES, student.serializeCompleteCourses());
        map.put(STUDENT_ALL_SEMESTERS, serializeAllSemesters(student.getAllSemester()));
        map.put(STUDENT_CURRENT_SEMESTER, serializeSemester(student.getCurrentSemester()));
    }

    @SuppressWarnings("unchecked")
    private void fillAdvisorDetails(LinkedHashMap<String, Object> map, Advisor advisor) {
        map.put(ADVISOR_IS_ADMIN, advisor.getIsAdmin().toString());
        JSONArray studentListJson = new JSONArray();
        advisor.getStudentList().forEach(student -> studentListJson.add(student.getID().toString()));
        map.put(ADVISOR_STUDENT_LIST, studentListJson);
    }

    @SuppressWarnings("unchecked")
    private JSONArray serializeAllSemesters(ArrayList<Semester> semesters) {
        JSONArray semestersJson = new JSONArray();
        semesters.forEach(semester -> semestersJson.add(serializeSemester(semester)));
        return semestersJson;
    }

    @SuppressWarnings("unchecked")
    private JSONObject serializeSemester(Semester semester) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put(SEMESTER_SEASON, semester.getSeason().toString());
        map.put(SESMESTER_YEAR, semester.getYear());
        map.put(SEMSESTER_LIMIT, semester.getCreditLimit());
        JSONArray coursesJson = new JSONArray();
        semester.getCourses().forEach(course -> {
            JSONArray courseInfo = new JSONArray();
            courseInfo.add(course.getID().toString());
            courseInfo.add("grade_placeholder");
            coursesJson.add(courseInfo);
        });
        map.put(SESMESTER_COURSES, coursesJson);
        return new JSONObject(map);
    }

    @SuppressWarnings("unchecked")
    private void appendToFile(String filePath, JSONObject jsonObject) {
        JSONArray jsonArray;
        try (FileReader reader = new FileReader(filePath)) {
            jsonArray = (JSONArray) parser.parse(reader);
        } catch (IOException | ParseException e) {
            jsonArray = new JSONArray();
        }

        jsonArray.add(jsonObject);
        String prettyPrintedJsonString = prettyPrintJsonArray(jsonArray);

        try (FileWriter file = new FileWriter(filePath)) {
            file.write(prettyPrintedJsonString);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private String prettyPrintJsonArray(JSONArray jsonArray) {
        StringBuilder prettyJsonBuilder = new StringBuilder("[\n");
        jsonArray.forEach(item -> prettyJsonBuilder.append(prettyPrintJsonObject((JSONObject) item, 1)).append(",\n"));
        if (prettyJsonBuilder.length() > 2) {
            prettyJsonBuilder.setLength(prettyJsonBuilder.length() - 2);
        }
        prettyJsonBuilder.append("\n]");
        return prettyJsonBuilder.toString();
    }

    @SuppressWarnings("unchecked")
    private String prettyPrintJsonObject(JSONObject jsonObject, int indentLevel) {
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

    private String getIndent(int level) {
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < level; i++) {
            indent.append("\t");
        }
        return indent.toString();
    }
}
