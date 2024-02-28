
/**
 * 
 * @author Aarsh Patel
 */

public abstract class DataConstants {

    //Degree Constants
    protected static final String DEGREE_FILE_NAME = "./json/degree.json";
    protected static final String DEGREE_ID = "id";
    protected static final String DEGREE_TOTAL_CREDIT_REQUIRED = "totalCreditRequired";
    protected static final String DEGREE_MAJOR_COURSES = "majorCourses";
    protected static final String DEGREE_SUBJECT_NAME = "subjectName";
    protected static final String DEGREE_ELECTIVE_LIST = "electiveList";

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
    protected static final String STUDENT_ADVISOR_ID = "advisorID";
    protected static final String STUDENT_NOTES = "notes";
    protected static final String STUDENT_DEGREE_ID = "degreeID";
    protected static final String STUDENT_INSTITUTE_GPA = "instituteGPA";
    protected static final String STUDENT_PROGRAM_GPA = "programGPA";
    protected static final String STUDENT_COMPLETED_COURSES = "completeCourses";
    protected static final String STUDENT_ALL_SEMESTERS = "allSemesters";
    protected static final String STUDENT_CURRENT_SEMESTER = "currentSemester";

    // Advisor Constants
    protected static final String ADVISOR_FILE_NAME = "./json/advisor.json";
    protected static final String ADVISOR_STUDENT_LIST = "studentList";
    protected static final String ADVISOR_IS_ADMIN = "isAdmin";
}