import java.util.ArrayList;

/**
 * The Semester class represents a semester in an academic calendar.
 * It contains information about the season, year, credit limit, and courses offered during the semester.
 */
public class Semester {
    private Season season;
    private int year;
    private int creditLimit;
    private ArrayList<Course> courses;

    /**
     * Constructs a Semester object with the specified season, year, credit limit, and list of courses.
     * @param season The season of the semester (e.g., "spring", "fall", "summer").
     * @param year The year of the semester.
     * @param creditLimit The maximum credit limit for the semester.
     * @param courseList The list of courses offered during the semester.
     */
    public Semester(String season, int year, int creditLimit, ArrayList<Course> courseList) {
        setSeason(season);
        setYear(year);
        setCreditLimit(creditLimit);
        setCourses(courseList);
    }
    
    // Accessor
    /**
     * Retrieves the list of courses offered during the semester.
     * @return ArrayList containing the courses offered during the semester.
     */
    public ArrayList<Course> getCourses() {
        return this.courses;
    }

    /**
     * Retrieves the season of the semester.
     * @return The season of the semester (e.g., "SPRING", "FALL", "SUMMER").
     */
    public Season getSeason() { return this.season; }

    /**
     * Retrieves the year of the semester.
     * @return The year of the semester.
     */
    public int getYear() { return this.year; }

    /**
     * Retrieves the maximum credit limit for the semester.
     * @return The maximum credit limit for the semester.
     */
    public int getCreditLimit() { return this.creditLimit; }

    // Mutator
    /**
     * Sets the list of courses offered during the semester.
     * @param courses ArrayList containing the courses offered during the semester.
     */
    public void setCourses(ArrayList<Course> courses) 
    {
        this.courses = courses;
    }

    /**
     * Sets the season of the semester based on the provided string.
     * @param season The season of the semester (e.g., "spring", "fall", "summer").
     */
    public void setSeason(String season) 
    {
        if (season.equalsIgnoreCase("spring")) {
            this.season = Season.SPRING;   
        } else if (season.equalsIgnoreCase("fall")) {
            this.season = Season.FALL;   
        } else if (season.equalsIgnoreCase("summer")) {
            this.season = Season.SUMMER;   
        }
        return;
    }

    /**
     * Sets the year of the semester.
     * @param year The year of the semester.
     */
    public void setYear(int year) 
    {
        this.year = year;
    }

    /**
     * Sets the maximum credit limit for the semester.
     * @param credit The maximum credit limit for the semester.
     */
    public void setCreditLimit(int credit) 
    {
        this.creditLimit = credit;
    }

    /**
     * Returns a string representation of the Semester object.
     * @return A string containing information about the semester, including season, year, credit limit, and courses offered.
     */
    @Override
    public String toString() 
    {
        StringBuilder result = new StringBuilder();
        result.append("Semester: ");
        result.append("Season: ");
        result.append("Year: ");
        result.append("Credit Limit: ");
        result.append("Semester: ");
        result.append("Courses: ");
        for (Course course : courses) {
            result.append("    [" + course.toString() + "]\n");
        }
        return result.toString();
    }


}
