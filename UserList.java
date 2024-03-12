
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

    // ----- Accessor -----
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

    public User getUser(UUID id) {
        for(User user: this.users) {
            if(user.getID().equals(id)) {
                return user;
            }
        }
        return null;
    }

    // ----- Other user mthod -----
    public Boolean createUser(String type, String firstName, String lastName, String password, String email) {
        User user;
        if(type.equalsIgnoreCase("student")) {
            user = new Student(firstName, lastName, email, password);
        } else if(type.equalsIgnoreCase("advisor")) {
            user = new Advisor(firstName, lastName, email, password, null);
        } else {
            return false;
        }
        this.users.add(user);
        return true;
    }

    public Boolean removeUser(UUID id) {
        for (User user : users) {
            if (user.getID().equals(id)) {
                this.users.remove(user);
                return true;
            }
        }
        return false;
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