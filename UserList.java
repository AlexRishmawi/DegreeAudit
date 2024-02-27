
/**
 * @author Paksh Patel
 */
import java.util.ArrayList;
import java.util.UUID;

public class UserList {
    private static UserList userList;
    private ArrayList<User> users;

    private UserList() {
        this.users = new ArrayList<User>();
        // Read a database
    }

    public static UserList getInstance() {
        return userList != null ? userList : new UserList();
    }

    public User getUser(String email, String password) {
        for (User user : this.users) {
            if (user.getEmail().equalsIgnoreCase(email) &&
                    user.getPassword().equalsIgnoreCase(password)) {
                return user;
            }
        }
        return null;
    }

    public User getUser(String firstName, String lastName, String password) {
        for (User user : this.users) {
            if (user.getFirstName().equalsIgnoreCase(firstName) &&
                user.getLastName().equalsIgnoreCase(lastName) &&
                user.getPassword().equalsIgnoreCase(password)) 
            {
                return user;
            }
        }
        return null;
    }

    public boolean createUser(String type, String firstName, String lastName, String password) {
        User user;
        if(type.equalsIgnoreCase("student")) {
            user = new Student(firstName, lastName, lastName, password);
        } else if(type.equalsIgnoreCase("advisor")) {
            user = new Advisor(firstName, lastName, lastName, password);
        } else {
            return false;
        }
        this.users.add(user);
        return true;
    }

    public boolean findUser(UUID id) {
        for (User user : users) {
            if (user.getID().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<User> getAllUsers() {
        return this.users;
    }
}