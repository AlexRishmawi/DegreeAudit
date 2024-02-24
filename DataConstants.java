
/**
 * 
 * @author Aarsh Patel
 */

public abstract class DataConstants {

    // Course Constants
    protected static final String COURSE_FILE_NAME = "./json/course.json";
    protected static final String COURSE_ID = "id";
    protected static final String COURSE_NAME = "courseName";
    protected static final String COURSE_DEPARTMENT = "department";
    protected static final String COURSE_CODE = "code";
    protected static final String COURSE_CREDIT_HOURS = "creditHours";
    protected static final String COURSE_SEMESTER_OFFER = "semesterOffer";
    protected static final String COURSE_PREREQUISITE = "prerequisite";
    protected static final String COURSE_DESCRIPTION = "description";
    protected static final String COURSE_GRADE_TO_PASS = "gradeToPass";
    protected static final String COURSE_GRADE = "courseGrade";

    // Program Constants
    protected static final String PROGRAM_FILE_NAME = "./json/program.json";
    protected static final String PROGRAM_ID = "id";
    protected static final String PROGRAM_TYPE = "type";
    protected static final String PROGRAM_SUBJECT = "subject";
    protected static final String PROGRAM_COURSE_LIST = "courses";
    protected static final String PROGRAM_CREDITS_REQUIRED = "creditsRequired";

    // User Constants share among the user
    protected static final String USER_ID = "id";
    protected static final String USER_TYPE = "type";
    protected static final String USER_FIRST_NAME = "firstName";
    protected static final String USER_LAST_NAME = "lastName";
    protected static final String USER_EMAIL = "email";
    protected static final String USER_PASSWORD = "password";

    // Student Constants
    protected static final String STUDENT_FILE_NAME = "./json/student.json";
    protected static final String STUDENT_CLASSIFICATION = "classification";
    protected static final String STUDENT_ADVISOR_ID = "advisor-id";
    protected static final String STUDENT_NOTES = "notes";
    protected static final String STUDENT_DEGREE_ID = "degree-id";
    protected static final String STUDENT_INSTITUTE_GPA = "instituteGPA";
    protected static final String STUDENT_PROGRAM_GPA = "programGPA";

    // Advisor Constants
    protected static final String ADVISOR_FILE_NAME = "./json/advisor.json";
    protected static final String ADVISOR_STUDENT_LIST = "studentList";

    // Admin Constants
    protected static final String ADMIN_FILE_NAME = "./json/admin.json";
    protected static final String ADMIN_ADVISOR_LIST = "advisorList";
}