import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

public class DataWriter {
    JSONParser parser = new JSONParser();
    JSONArray jsonArray = new JSONArray();

    @SuppressWarnings("unchecked")
    private JSONObject userToJson(User user) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", user.getID().toString());
        jsonObject.put("firstName", user.getFirstName());
        jsonObject.put("lastName", user.getLastName());
        jsonObject.put("email", user.getEmail());
        jsonObject.put("password", user.getPassword());

        if (user instanceof Student) {
            Student student = (Student) user;
            // Student specific serialization
            jsonObject.put("classification", student.getLevel().toString());
            jsonObject.put("advisorID", student.getAdvisor().getID().toString()); // Adapt as needed
            //jsonObject.put("degreeID", "Hello"); // Adapt as needed
            jsonObject.put("degreeID", student.getDegree().getID().toString()); // Adapt as needed
            jsonObject.put("instituteGPA", student.getInstituteGPA());
            jsonObject.put("programGPA", student.getProgramGPA());
            jsonObject.put("status", student.getStatus());

            JSONArray notesJson = new JSONArray();
            student.getNotes().forEach(notesJson::add);
            jsonObject.put("notes", notesJson);

            // Serializing allSemesters
            JSONArray allSemestersJson = new JSONArray();
            student.getAllSemester().forEach(semester -> {
                JSONObject semesterJson = new JSONObject();
                semesterJson.put("season", semester.getSeason().toString());
                semesterJson.put("year", semester.getYear());
                semesterJson.put("creditLimit", semester.getCreditLimit());

                JSONArray coursesJson = new JSONArray();
                semester.getCourses().entrySet().forEach(entry -> {
                    JSONObject courseGradeJson = new JSONObject();
                    courseGradeJson.put("courseID", entry.getKey().getID().toString()); // Assuming Course has getID()
                    courseGradeJson.put("grade", entry.getValue());
                    coursesJson.add(courseGradeJson);
                });
                semesterJson.put("courses", coursesJson);
                allSemestersJson.add(semesterJson);
            });
            jsonObject.put("allSemesters", allSemestersJson);
        } else if (user instanceof Advisor) {
            Advisor advisor = (Advisor) user;
            JSONArray studentListJson = new JSONArray();
            advisor.getStudentList().forEach(studentID -> studentListJson.add(studentID.toString())); // Assuming getStudentList() returns List<String>
            jsonObject.put("studentList", studentListJson);
            jsonObject.put("isAdmin", advisor.getIsAdmin());
        }

        return jsonObject;
    }



    //writeUser
    public void writeUser(User user, char mode) {
        JSONObject userJson = userToJson(user);
        String fileName;
    
        // Determine the file path based on the user's class type
        if (user instanceof Student) {
            fileName = "student.json";
        } else if (user instanceof Advisor) {
            fileName = "advisor.json";
        } else {
            // Handle the case where user is neither a Student nor an Advisor
            System.err.println("Incorrect User type entered.");
            return; // Exit the method early as we cannot determine the correct file
        }
    
        String filePath = "./json/" + fileName; // Build the file path
        writeToFile(filePath, userJson, mode); // Write the JSON object to the determined file
    }
    


    //writeToFile
    @SuppressWarnings("unchecked")
    private void writeToFile(String fileName, JSONObject jsonObject, char mode) {
        JSONArray jsonArray = new JSONArray();

        if (mode == 'a' && new File(fileName).exists()) {
            try (FileReader reader = new FileReader(fileName)) {
                Object obj = parser.parse(reader);
                jsonArray = (JSONArray) obj;
            } catch (IOException | ParseException e) {
                e.printStackTrace();
                return;
            }
        }

        jsonArray.add(jsonObject);

        try (FileWriter file = new FileWriter(fileName)) {
            file.write(jsonArray.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Helper method to convert a Course object to JSONObject
    @SuppressWarnings("unchecked")
    private JSONObject courseToJson(Course course) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", course.getID().toString());
        jsonObject.put("courseName", course.getCourseName());
        jsonObject.put("department", course.getDepartment());
        jsonObject.put("code", course.getCode());
        jsonObject.put("creditHours", course.getCreditHours());
    
        // Convert semesterOffer to JSON array
        JSONArray semesterOfferJson = new JSONArray();
        for (Season season : course.getSemesterOffer()) {
            semesterOfferJson.add(season.toString());
        }
        jsonObject.put("semesterOffer", semesterOfferJson);
    
        // Convert prerequisites to JSON format as per the provided structure
        JSONArray prerequisitesJson = new JSONArray();
        for (Course prereqCourse : course.getPrerequisites()) {
            // Assuming each prerequisite course is represented by its ID and a grade
            // This part needs to be adapted based on how you manage prerequisite relations and grades
            JSONArray prereqPair = new JSONArray();
            prereqPair.add(prereqCourse.getID().toString());
            prereqPair.add("C"); // Example grade, adjust according to your logic
            JSONArray prereqGroup = new JSONArray();
            prereqGroup.add(prereqPair);
            prerequisitesJson.add(prereqGroup);
        }
        jsonObject.put("prerequisite", prerequisitesJson);
    
        jsonObject.put("description", course.getDescription());
        jsonObject.put("gradeToPass", course.getGradeToPass());
    
        return jsonObject;
    }


    public void writeCourse(Course course, char mode) {
        JSONObject courseJson = courseToJson(course);
        writeToFile("./json/course.json", courseJson, mode);
    }

    private void printFileContents(String fileName) {
        try (FileReader reader = new FileReader(fileName)) {
            JSONArray jsonArray = (JSONArray) parser.parse(reader);
            Iterator<Object> iterator = jsonArray.iterator();
            
            System.out.println("Contents of " + fileName + ":");
            while (iterator.hasNext()) {
                JSONObject jsonObject = (JSONObject) iterator.next();
                System.out.println(jsonObject.toJSONString());
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
