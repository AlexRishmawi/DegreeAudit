
import java.util.ArrayList;
/**
 * 
 * @author Aarsh Patel
 */

public class DataConstants {
    // Student Constants
    protected static final String STUDENT_FILE_NAME = "./json/students.json";
    protected static final String STUDENT_ID = "id";
    protected static final String STUDENT_TYPE = "type";
    protected static final String STUDENT_FIRST_NAME = "firstName";
    protected static final String STUDENT_LAST_NAME = "lastName";
    protected static final String STUDENT_EMAIL = "email";
    protected static final String STUDENT_PASSWORD = "password";
    protected static final String STUDENT_CLASSIFICATION = "classification";
    protected static final String STUDENT_ADVISOR_ID = "advisor-id";
    protected static final String STUDENT_NOTES = "notes";
    protected static final String STUDENT_DEGREE_ID = "degree-id";
    protected static final String STUDENT_INSTITUTE_GPA = "0.0";
    protected static final String STUDENT_PROGRAM_GPA = "0.0";

    // Course Constants
    protected static final String COURSE_FILE_NAME = "./json/courses.json";
    protected static final String COURSE_ID = "id";
    protected static final String COURSE_NAME = "courseName";
    protected static final String COURSE_DEPARTMENT = "department";
    protected static final String COURSE_CODE = "code";
    protected static final String COURSE_CREDIT_HOURS = "creditHours";
    protected static final ArrayList<String> COURSE_SEMESTER_OFFER = new ArrayList<String>();
    protected static final String COURSE_PREREQUISITE = "prerequisite";
    protected static final String COURSE_DESCRIPTION = "description";
    protected static final String COURSE_GRADE_TO_PASS = "gradeToPass";
    protected static final String COURSE_GRADE = "courseGrade";

    // Advisor Constants
    protected static final String ADVISOR_FILE_NAME = "./json/advisors.json";
    protected static final String ADVISOR_ID = "id";
    protected static final String ADVISOR_TYPE = "type";
    protected static final String ADVISOR_FIRST_NAME = "firstName";
    protected static final String ADVISOR_LAST_NAME = "lastName";
    protected static final String ADVISOR_EMAIL = "email";
    protected static final String ADVISOR_PASSWORD = "password";
    protected static final ArrayList<String> ADVISOR_STUDENT_LIST = new ArrayList<String>();

    // Program Constants
    protected static final String PROGRAM_FILE_NAME = "./json/programs.json";
    protected static final String PROGRAM_ID = "id";
    protected static final String PROGRAM_TYPE = "Type";
    protected static final String PROGRAM_SUBJECT = "subject";
    protected static final ArrayList<String> PROGRAM_COURSE_LIST = new ArrayList<String>();
    protected static final int PROGRAM_CREDITS_REQUIRED = 0;
}