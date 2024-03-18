import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.UUID;

public class DataWriter {
    private JSONParser parser = new JSONParser();

    public void writeUser(User user) {
        JSONObject userJson = userToJson(user);
        String filePath = user instanceof Student ? "json/student.json" : "json/advisor.json";
        appendToFile(filePath, userJson);
    }

    private JSONObject userToJson(User user) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("id", user.getID().toString());
        map.put("type", user instanceof Student ? "Student" : "Advisor");
        map.put("firstName", user.getFirstName());
        map.put("lastName", user.getLastName());
        map.put("email", user.getEmail());
        map.put("password", user.getPassword());

        if (user instanceof Student) {
            fillStudentDetails(map, (Student) user);
        } else if (user instanceof Advisor) {
            fillAdvisorDetails(map, (Advisor) user);
        }

        return new JSONObject(map);
    }

    private void fillStudentDetails(LinkedHashMap<String, Object> map, Student student) {
        map.put("type", "Student");
        map.put("studentID", student.getStudentID());
        map.put("classification", student.getLevel().toString());
        map.put("advisorID", student.getAdvisor().getID().toString());
        
        JSONArray notesJson = new JSONArray();
        for (String note : student.getNotes()) {
            notesJson.add(note);
        }
        map.put("notes", notesJson);
        
        map.put("degreeID", student.getDegree().getID().toString());
        map.put("instituteGPA", student.getInstituteGPA());
        map.put("programGPA", student.getProgramGPA());
        map.put("status", student.getStatus());
        map.put("completeCourses", student.serializeCompleteCourses());
        map.put("allSemesters", serializeAllSemesters(student.getAllSemester()));
        map.put("currentSemester", serializeSemester(student.getCurrentSemester()));
    }

    @SuppressWarnings("unchecked")
    private void fillAdvisorDetails(LinkedHashMap<String, Object> map, Advisor advisor) {
        map.put("isAdmin", advisor.getIsAdmin().toString());
        JSONArray studentListJson = new JSONArray();
        advisor.getStudentList().forEach(student -> studentListJson.add(student.getID().toString()));
        map.put("studentList", studentListJson);
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
        map.put("season", semester.getSeason().toString());
        map.put("year", semester.getYear());
        map.put("creditLimit", semester.getCreditLimit());
        JSONArray coursesJson = new JSONArray();
        semester.getCourses().forEach(course -> {
            JSONArray courseInfo = new JSONArray();
            courseInfo.add(course.getID().toString());
            courseInfo.add("grade_placeholder");
            coursesJson.add(courseInfo);
        });
        map.put("courses", coursesJson);
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
