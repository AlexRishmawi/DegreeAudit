import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class DataWriter {

    public void writeUser(User user, char mode) {
        writeFile(DataConstants.STUDENT_FILE_NAME, user.toString(), mode == 'a');
    }

    public void writeProgram(Program program, char mode) {
        writeFile(DataConstants.PROGRAM_FILE_NAME, program.toString(), mode == 'a');
    }

    public void writeCourse(String file, Course course, char mode) {
        writeFile(file, course.toString(), mode == 'a');
    }

    private void writeFile(String filePath, String content, boolean append) {
        try (FileWriter fw = new FileWriter(filePath, append);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
