import java.util.UUID;

public interface editableUser {
    private User findUser(UUID id);
    public  boolean editUserFirstName(UUID id, String name);
    public  boolean editUserLastName(UUID id, String name);
    public  boolean editUserEmail(UUID id, String name);
    public  boolean editUserUSCID(UUID id, String name);
    public  boolean editUserPassword(UUID id, String name);
    public  boolean deleteUser(UUID id);
}
