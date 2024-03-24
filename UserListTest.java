import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.UUID;

public class UserListTest {
    private User user1;
    private User user2;
    private UUID id1;
    private UUID id2;
    private UUID id3;
    private UserList userList;

    @Test
    public void getInstance_Test() {
        userList = UserList.getInstance();
        assertNotNull(userList);
    }

    @Before
    public void setUp() {
        userList = UserList.getInstance();
        id1 = UUID.randomUUID();
        id2 = UUID.randomUUID();
        user1 = new User(id1, "Brax", "West", "BWest@email.sc.edu", "password");
        user2 = new User(id2, "Jane", "Doe", "Jdoe@email.sc.edu", "password");
        user1.setUserType(UserType.STUDENT);
        user2.setUserType(UserType.ADVISOR);
        ((Student) user1).setStudentID("X12345678");
        userList.addUser(user1);
        userList.addUser(user2);
    }

    @Test
    public void getUserEmailAndPassword_Test() {
        User userActual = userList.getUser("BWest@email.sc.edu", "password");
        assertEquals(user1, userActual);
    }

    @Test
    public void getUserStudentID_Test() {
        User userActual = userList.getUser("X12345678");
        assertEquals(user1, userActual);
    }

    @Test
    public void getUserNameAndPassword_Test() {
        User userActual = userList.getUser("Brax", "West", "password");
        assertEquals(user1, userActual);
    }

    @Test
    public void getUserID_Test() {
        User userActual = userList.getUser(id1);
        assertEquals(user1, userActual);
    }

    @Test
    public void createUser_Test() {
        userList.createUser("Advisor", "John", "Smith", "password", "Jsmith@email.sc.edu", null);
        id3 = UUID.randomUUID();
        User userExpected = new User(id3, "John", "Smith", "Jsmith@email.sc.edu", "password");
        assertEquals(userExpected, userList.getUser(id3));
    }





}
