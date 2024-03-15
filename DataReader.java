import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Array;

import java.util.ArrayList;
import java.util.UUID;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataReader extends DataConstants {

    public static ArrayList<User> loadUser() {

    }

    public static ArrayList<Course> loadCourse() {
        ArrayList<Course> loadedCourse = new ArrayList<>();
        HashMap<UUID, Integer> mappingCourse = new HashMap<>();
        Queue<Pair<Course, Object>> queueCourse = new LinkedList<>();

        // Read a file and load the course with no prerequisite first
        try {
            FileReader reader = new FileReader(COURSE_FILE_NAME);
            JSONArray readerJSON = (JSONArray) new JSONParser().parse(reader);
            for (int i = 0; i < readerJSON.size(); i++) {
                JSONObject courseJSON = (JSONObject) readerJSON.get(i);

                UUID id = UUID.fromString((String) courseJSON.get(COURSE_ID));
                String subject = (String) courseJSON.get(COURSE_SUBJECT);
                String code = (String) courseJSON.get(COURSE_CODE);

                String name = (String) courseJSON.get(COURSE_NAME);
                String description = (String) courseJSON.get(COURSE_DESCRIPTION);

                int credit = ((Long) courseJSON.get(COURSE_CREDIT_HOURS)).intValue();

                ArrayList<Season> semesterOffer = new ArrayList<>();
                JSONArray semesterJSON = (JSONArray) courseJSON.get(COURSE_SEMESTER_OFFER);
                for (int j = 0; j < semesterJSON.size(); j++) {
                    semesterOffer.add(Season.valueOf(((String) semesterJSON.get(j)).toUpperCase()));
                }

                ArrayList<Prerequisites> prerequisites = new ArrayList<Prerequisites>();
                JSONArray prerequisiteJsonObject = (JSONArray) courseJSON.get(COURSE_PREREQUISITE);

                Course course = new Course(id, subject, code, name, description, credit, semesterOffer, prerequisites);
                if (prerequisiteJsonObject.size() == 0) {
                    loadedCourse.add(course);
                    int index = loadedCourse.indexOf(course);
                    mappingCourse.put(course.getID(), index);
                } else {
                    queueCourse.add(new Pair<Course, Object>(course, prerequisiteJsonObject));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("ERROR --- Couldn't load Course file");
        }

        while (!queueCourse.isEmpty()) {
            Pair<Course, Object> item = queueCourse.poll();
            Course course = item.getKey();
            JSONArray prereJson = (JSONArray) item.getValue();

            ArrayList<Prerequisites> allPrerequisites = new ArrayList<>();
            boolean isGoodToLoad = true;

            for (int i = 0; i < prereJson.size(); i++) {
                JSONObject prereJSON = (JSONObject) prereJson.get(i);
                int choices = ((Long) prereJSON.get(COURSE_PREREQUISITE_CHOICES)).intValue();
                String minGrade = (String) prereJSON.get(COURSE_PREREQUISITE_MIN_GRADE);

                ArrayList<Course> courseOptions = new ArrayList<>();
                JSONArray courseOptionJSON = (JSONArray) prereJSON.get(COURSE_PREREQUISITE_COURSE_OPTION);
                for (int j = 0; j < courseOptionJSON.size(); j++) {
                    UUID courseID = UUID.fromString((String) courseOptionJSON.get(j));
                    if (mappingCourse.containsKey(courseID)) {
                        courseOptions.add(loadedCourse.get(mappingCourse.get(courseID)));
                    }
                }

                // check wherether if all options is loaded
                if (courseOptions.size() == courseOptionJSON.size()) {
                    allPrerequisites.add(new Prerequisites(choices, minGrade, courseOptions));
                } else {
                    isGoodToLoad = false;
                    break;
                }
            }

            // if it good condition then load, otherwise load back to queue
            if (isGoodToLoad) {
                course.setPrerequisites(allPrerequisites);
                loadedCourse.add(course);
                mappingCourse.put(course.getID(), loadedCourse.indexOf(course));
            } else {
                queueCourse.add(item);
            }
        }

        return loadedCourse;
    }

    // ----- Private Data Structure -----
    private static class Pair<K, V> {
        private final K key;
        private final V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public String toString() {
            return "(" + key + ", " + value + ")";
        }
    }
}
