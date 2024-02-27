import java.util.ArrayList;

public class Semester {
    private Season season;
    private int year;
    private int creditLimit;
    private int totalCourseCredit;
    private ArrayList<Course> courses;

    public Semester(String season, int year, ArrayList<Course> courseList) {
        setSeason(season);
        setYear(year);
        setCourse(courseList);
    }

    public Semester(String season, int limit, int year, ArrayList<Course> courseList) {
        setSeason(season);
        setCreditLimit(limit);
        setYear(year);
        setCourse(courseList);
    }

    public void setSeason(String season) {
        if(season.equalsIgnoreCase("spring")) {
            this.season = Season.SPRING;
        } else if(season.equalsIgnoreCase("summer")) {
            this.season = Season.SUMMER;
        } else if(season.equalsIgnoreCase("fall")) {
            this.season = Season.FALL;
        } else {
            System.err.println("ERROR --- invalid season:" + season);
        }
    }

    public void setCreditLimit(int limit) {
        if( limit < 0 && limit > 21) {
            System.err.println("ERROR --- invalid credit hours limit");
            return;
        }
        this.creditLimit = limit;
    }

    public void setYear(int year) { this.year = year; }

    public void setCourse(ArrayList<Course> courses) {
        int total = courses.stream().mapToInt(course -> course.getCreditHours()).sum();
        if(total >= this.creditLimit) {
            this.totalCourseCredit = 0;
            this.courses = new ArrayList<>();
            System.err.println("ERROR --- Can't not get course more than limit. \nPlease contact Advisor");
            return;
        }
        this.totalCourseCredit = total;
        this.courses = courses;
    }

    public String getSeason() { return this.season.toString(); }
    public int getYear() { return this.year; }
    public int getCreditLimit() { return this.creditLimit; }

    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("Semester Information: ");
        string.append("\nSeason:" + this.season.toString());
        string.append("\nYear: " + this.year);
        string.append("\nCredit limit: " + this.creditLimit);
        string.append("Total course credit: " + this.totalCourseCredit);
        for(Course course: this.courses) {
            string.append(course.toString());
        }
        string.append("\n");
        return string.toString();
    }

}