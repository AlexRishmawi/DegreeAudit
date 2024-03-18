import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class DataWriter {
    private JSONParser parser = new JSONParser();

    public void writeUser(User user) {
        JSONObject userJson = userToJson(user);
        String filePath = user instanceof Student ? "json/students.json" : "json/advisors.json";
        appendToFile(filePath, userJson);
    }

    @SuppressWarnings("unchecked")
private JSONObject userToJson(User user) {
    JSONObject jsonObject = new JSONObject();

    // Add in the desired order
    jsonObject.put("id", user.getID().toString());
    jsonObject.put("type", user instanceof Student ? "Student" : "Advisor");
    jsonObject.put("firstName", user.getFirstName());
    jsonObject.put("lastName", user.getLastName());
    jsonObject.put("email", user.getEmail());
    jsonObject.put("password", user.getPassword());
    
    if (user instanceof Student) {
        Student student = (Student) user;
        jsonObject.put("studentID", student.getStudentID());
        jsonObject.put("classification", student.getLevel().toString());
        jsonObject.put("advisorID", student.getAdvisor().getID().toString());
        
        JSONArray notesJson = new JSONArray();
        student.getNotes().forEach(notesJson::add);
        jsonObject.put("notes", notesJson);
        
        jsonObject.put("degreeID", student.getDegree().getID().toString());
        jsonObject.put("instituteGPA", student.getInstituteGPA());
        jsonObject.put("programGPA", student.getProgramGPA());
        jsonObject.put("status", student.getStatus());
        jsonObject.put("completeCourses", student.serializeCompleteCourses());
        jsonObject.put("allSemesters", serializeAllSemesters(student.getAllSemester()));
        jsonObject.put("currentSemester", serializeSemester(student.getCurrentSemester()));
    } else if (user instanceof Advisor) {
        fillAdvisorDetails(jsonObject, (Advisor) user);
    }

    return jsonObject;
}

    @SuppressWarnings("unchecked")
    private void fillStudentDetails(JSONObject jsonObject, Student student) {
        jsonObject.put("type", "Student");
        jsonObject.put("studentID", student.getStudentID());
        jsonObject.put("classification", student.getLevel().toString());
        jsonObject.put("advisorID", student.getAdvisor().getID().toString());
        
        JSONArray notesJson = new JSONArray();
        student.getNotes().forEach(notesJson::add);
        jsonObject.put("notes", notesJson);
        
        jsonObject.put("degreeID", student.getDegree().getID().toString());
        jsonObject.put("instituteGPA", student.getInstituteGPA());
        jsonObject.put("programGPA", student.getProgramGPA());
        jsonObject.put("status", student.getStatus());
        // Assumes serializeCompleteCourses returns JSONArray
        jsonObject.put("completeCourses", student.serializeCompleteCourses());
        jsonObject.put("allSemesters", serializeAllSemesters(student.getAllSemester()));
        jsonObject.put("currentSemester", serializeSemester(student.getCurrentSemester()));
    }

    @SuppressWarnings("unchecked")
    private void fillAdvisorDetails(JSONObject jsonObject, Advisor advisor) {
        jsonObject.put("type", "Advisor");
        jsonObject.put("isAdmin", advisor.getIsAdmin().toString());
        JSONArray studentListJson = new JSONArray();
        advisor.getStudentList().forEach(student -> studentListJson.add(student.getID().toString()));
        jsonObject.put("studentList", studentListJson);
    }

    @SuppressWarnings("unchecked")
    private JSONArray serializeAllSemesters(ArrayList<Semester> semesters) {
        JSONArray semestersJson = new JSONArray();
        semesters.forEach(semester -> semestersJson.add(serializeSemester(semester)));
        return semestersJson;
    }

    @SuppressWarnings("unchecked")
    private JSONObject serializeSemester(Semester semester) {
        JSONObject semesterJson = new JSONObject();
        semesterJson.put("season", semester.getSeason().toString());
        semesterJson.put("year", semester.getYear());
        semesterJson.put("creditLimit", semester.getCreditLimit());
        JSONArray coursesJson = new JSONArray();
        semester.getCourses().forEach(course -> {
            JSONArray courseInfo = new JSONArray();
            courseInfo.add(course.getID().toString());
            // Placeholder for actual grade value
            courseInfo.add("grade_placeholder");
            coursesJson.add(courseInfo);
        });
        semesterJson.put("courses", coursesJson);
        return semesterJson;
    }

    @SuppressWarnings("unchecked")
    private void appendToFile(String filePath, JSONObject jsonObject) {
        JSONArray jsonArray;
        try (FileReader reader = new FileReader(filePath)) {
            Object obj = parser.parse(reader);
            jsonArray = (JSONArray) obj;
            System.out.println("Existing data read successfully.");
        } catch (IOException e) {
            System.out.println("File not found. Creating a new one.");
            jsonArray = new JSONArray();
        } catch (ParseException e) {
            System.out.println("Failed to parse the existing file. Starting fresh.");
            jsonArray = new JSONArray();
        }

        jsonArray.add(jsonObject);
        System.out.println("Added new user to the JSON array.");

        String prettyPrintedJsonString = prettyPrintJsonArray(jsonArray);

        try (FileWriter file = new FileWriter(filePath, false)) {
            file.write(prettyPrintedJsonString);
            file.flush();
            System.out.println("Data successfully written to " + filePath);
        } catch (IOException e) {
            System.out.println("Failed to write data to " + filePath);
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private String prettyPrintJsonArray(JSONArray jsonArray) {
        StringBuilder prettyJsonBuilder = new StringBuilder("[\n");
        jsonArray.forEach(item -> {
            prettyJsonBuilder.append(prettyPrintJsonObject((JSONObject) item, 1)).append(",\n");
        });
        // Handle trailing comma
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
                // This part is simplified; in a real scenario, you might need to handle nested arrays/objects
                prettyJson.append(value.toString());
            } else {
                prettyJson.append(value instanceof String ? "\"" + value + "\"" : value);
            }
            prettyJson.append(",\n");
        });
        // Handle trailing comma
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
