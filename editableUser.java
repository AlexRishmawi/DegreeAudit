
import java.util.UUID;

public abstract interface editableUser {
    public User findUser(UUID id);
    public boolean editUserFirstName(UUID id, String name);
    public boolean editUserLastName(UUID id, String name);
    public boolean editUserEmail(UUID id, String name);
    public boolean editUserUSCID(UUID id, UUID newID);
    public boolean editUserPassword(UUID id, String name);
    public boolean deleteUser(UUID id);
}
