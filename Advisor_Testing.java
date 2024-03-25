import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.Test;

public class Advisor_Testing {
    @Test
    public void firstConstructor() {
        Advisor advisor_1 = new Advisor("T", "Le", "@gmail", "password", false);
        Advisor advisor_2 = new Advisor("T", "Le", "@gmail", "password", true);

        assertNotEquals(advisor_1, advisor_2);
    }

    @Test
    public void AdvisorStudentList() {
        UserList userList = UserList.getInstance();
        Student student_1 = (Student) userList.getUser(UUID.fromString("465ff326-776a-4d98-af63-3b57551bb3b4"));
        ArrayList<Student> student_list = new ArrayList<>();
        student_list.add(student_1);

        Advisor advisor_1 = new Advisor("T", "Le", "@gmail", "password", false);
        advisor_1.setStudentList(student_list);

        Advisor advisor_2 = new Advisor("T", "Le", "@gmail", "password", false);
        advisor_2.setStudentList(student_list);

        assertEquals(advisor_1.getStudentList(), advisor_2.getStudentList());
    }
}
