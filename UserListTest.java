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
    public void getUser_Test() {
        User userActual = userList.getUser("BWest@email.sc.edu", "password");
        assertEquals(user1, userActual);
    }



}
