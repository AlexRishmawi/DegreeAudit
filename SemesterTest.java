import org.junit.*;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.UUID;

public class SemesterTest {

    private Semester semester;
    private ArrayList<Course> courses;
    private ArrayList<Season> offeredSeasons;
    private ArrayList<Prerequisites> prerequisites;

    @Before
    public void setUp() {
        offeredSeasons = new ArrayList<>();
        offeredSeasons.add(Season.SPRING);
        offeredSeasons.add(Season.FALL);


        // ArrayList of courses to make use in the semester object
        courses = new ArrayList<>();
        courses.add(new Course("Computer Science", "101", "Introduction to Computer Science", "Basics of CS", 3, offeredSeasons, prerequisites));
        courses.add(new Course("Math", "201", "Calculus I", "Differential Calculus", 4, offeredSeasons, prerequisites));

        semester = new Semester("Spring", 2021, 18, courses);
    }

    @Test
    public void testConstructorAndAccessors() {
        Assert.assertEquals("The season should be SPRING.", Season.SPRING, semester.getSeason());
        Assert.assertEquals("The year should be 2021.",2021, semester.getYear());
        Assert.assertEquals("The credit limit should be 18",18, semester.getCreditLimit());
        Assert.assertEquals("The Course List should match the input list" ,courses, semester.getCourses());
    }

    
    @Test
    public void testMutators() {
        // Testing setYear
        semester.setYear(2022);
        Assert.assertEquals("The year should now be 2022.", 2022, semester.getYear());

        // Testing setCreditLimit
        semester.setCreditLimit(20);
        Assert.assertEquals("The credit limit should now be 20.", 20, semester.getCreditLimit());
        
        // Testing setCreditLimit with a different positive value
        semester.setCreditLimit(30);
        Assert.assertEquals("The credit limit should now be 20.", 30, semester.getCreditLimit());

        // Testing setCreditLimit with a 0
        semester.setCreditLimit(0);
        Assert.assertEquals("The credit limit should now be 20.", 0, semester.getCreditLimit());

        // Testing setCreditLimit with a negative int
        semester.setCreditLimit(-5);
        Assert.assertEquals("The credit limit should now be 20.", -5, semester.getCreditLimit());

        // Testing setSeason
        semester.setSeason("Fall");
        Assert.assertEquals("The season should now be FALL.", Season.FALL, semester.getSeason());

        // Testing setCourses with new courses
        ArrayList<Course> newCourses = new ArrayList<>();
        newCourses.add(new Course("Physics", "101", "Physics I", "Introduction to Physics", 4, offeredSeasons, prerequisites));
        semester.setCourses(newCourses);
        Assert.assertEquals("The courses list should now match the new list.", newCourses, semester.getCourses());
    }

    @Test
    public void testToString() {
        String expected = "===================================================\n" +
                          "Semester: SPRING 2021\tCredit Limit: 18\n" +
                          "===================================================\n" +
                          "Courses: \n" +
                          "--------------------------------------------------\n" +
                          "\t\n-- Name: Introduction to Computer Science\n" +
                          "-- Subject: Computer Science 101\tCredit Hours: 3\n" +
                          "-- Description: Basics of CS\n" +
                          "-- Semesters Offered: [SPRING, FALL]\n" +
                          "-- Course Prerequisites: None\n" +
                          "--------------------------------------------------\n" +
                          "--------------------------------------------------\n" +
                          "\t\n-- Name: Calculus I\n" +
                          "-- Subject: Math 201\tCredit Hours: 4\n" +
                          "-- Description: Differential Calculus\n" +
                          "-- Semesters Offered: [SPRING, FALL]\n" +
                          "-- Course Prerequisites: None\n" +
                          "--------------------------------------------------\n";
                          Assert.assertEquals("The toString output should match the expected string.", expected.trim(), semester.toString().trim());
                          // Get a null pointer for the toString part of Semester when there is nothing givin to it (Prereq only)
    }
}
