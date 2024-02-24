/**
 * @author Paksh Patel
 */
import java.util.ArrayList;
import java.util.UUID;

public class UserList {
    private static UserList userList;
    private ArrayList<User> users;

    private UserList(){
        users = new ArrayList<User>();
    }

    public UserList getInstance(){
        if(userList == null){
            userList = new UserList();
        }
        return userList;
    }

    public User getUser(UUID id, String password){
        for(User user : users){
            if(user.getID().equals(id)){
                return user;
            }
        }
        return null;
    }

    public boolean findUser(UUID id, String password){
        for(User user : users){
            if(user.getID().equals(id)){
                return true;
            }
        }
        return false;
    }

    public ArrayList<User> getAllUsers(){
        return users;
    }
}