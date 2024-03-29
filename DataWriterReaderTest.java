import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class DataWriterReaderTest {

    private static final String TEST_USER_FILE_PATH = "./json/test_users.json";

    @Test
    public void testWriteAndReadNewUser() {
        UserList userList = UserList.getInstance();
        System.out.println(userList.getAllUsers().size());
        Student sample_Student = (Student) userList.getUser(UUID.fromString("465ff326-776a-4d98-af63-3b57551bb3b4"));
        String uniqueEmail = "uniqueUser_" + System.currentTimeMillis() + "@example.com";

        Student student_1 = new Student("T", "Le", uniqueEmail, "password", "studentID", sample_Student.getLevel().toString(),
                                                    sample_Student.getAdvisor(),
                                                    sample_Student.getNotes(),
                                                    sample_Student.getDegree(),
                                                    sample_Student.getInstituteGPA(),
                                                    sample_Student.getProgramGPA(),
                                                    sample_Student.getStatus(),
                                                    sample_Student.getCompletedCourse(),
                                                    sample_Student.getCurrentSemester(),
                                                    sample_Student.getAllSemester());

        // This should return true if the student was successfully added to the program
        // This park of the porgram should rewrite the json file with the newly added student
        
        Assert.assertEquals("The number of users in the userList before adding the new user should be 4", 4, userList.getAllUsers().size());
        Assert.assertEquals("The number of users in the userList before adding the new user should be 4 but using the DataReader", 4, DataReader.loadUser().size());

        userList.getInstance().addUser(student_1);
        DataWriter.writeUser();

        // This should print out the number of users and the point is its supposed to be whtever
        // the number was originally + 1
        System.out.println(userList.getAllUsers().size());
        Assert.assertEquals("The Number of users in the userList should now be 5", 5, userList.getAllUsers().size());

        // Testing failed because DataWriter does not work correctly 
        Assert.assertEquals("The Number of users in the userList should now be 5 but using the DataReader", 5, DataReader.loadUser().size());

        User foundUser = findUserByEmail("tHill@email.sc.edu");
        System.out.println(foundUser);
        
        if(foundUser != null) {
            Assert.assertEquals("First name should match", "Tawnie", foundUser.getFirstName());
            Assert.assertEquals("Last name should match", "Hill", foundUser.getLastName());
            Assert.assertEquals("Email should match", "tHill@email.sc.edu", foundUser.getEmail());
        } else {
            System.out.println("The user was not found in the JSON file therefore the DataWriter did not add the new user to the JSON file");
        }


        
        
    }

    /**
     * Helper method to find a user by email in the UserList.
     * @param email The email address of the user to find.
     * @return The found User, or null if not found.
     */
    private User findUserByEmail(String email) {
        for (User user : UserList.getInstance().getAllUsers()) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return user;
            }
        }
        return null;
    }

    @Test
    public void testLoadUser() {
        ArrayList<User> bigUsers = DataReader.loadUser();

        int usersAMT = DataReader.loadUser().size();
        UserList userList = UserList.getInstance(); 

        Assert.assertNotNull("Testing to see if the loadUser method of DataReader does not return null", bigUsers);
        Assert.assertEquals("Testng to see if the size of the original userList size is correct when coming from the DataReader", userList.getAllUsers().size(), usersAMT);
        
    }

    //public static void main(String[] args) {
    //    testWriteAndReadNewUser();
    //}

    @Test
    public void testLoadCourse() {
        
        Assert.assertNotNull("Testing to see if the return value for the method LoadCourse does not return null", DataReader.loadCourse());
        Assert.assertEquals("Testing to see if the DataReader's method loadCourse is loading the correct Course", "225",DataReader.loadCourse().get(0).getCode());
        Assert.assertEquals("Testing to see if the DataReader's method loadCourse is loading the correct Course", "ACCT",DataReader.loadCourse().get(0).getSubject());        
        Assert.assertEquals("Testing to see if the DataReader's method loadCourse is loading the correct Course", "User-oriented approach to the study of financial accounting and reporting topics related to business decisions.   FS: 12/02/2015.    CL: 2020.",DataReader.loadCourse().get(0).getDescription());        
        
        ArrayList<Season> semestersOff = new ArrayList<>();
        semestersOff.add(Season.SPRING);
        semestersOff.add(Season.SUMMER);
        semestersOff.add(Season.FALL);

        Assert.assertEquals("Testing to see if the DataReader's method loadCourse is loading the correct Course", semestersOff, DataReader.loadCourse().get(0).getSemesterOffer());
        
        Assert.assertEquals("Testing to see if the DataReader's method loadCourse is loading the correct Course", "145a02ba-0a74-4eb3-87fb-8c3ecf91f240", DataReader.loadCourse().get(0).getID().toString());
        Assert.assertEquals("Testing to see if the DataReader's method loadCourse is loading the correct Course", 3, DataReader.loadCourse().get(0).getCreditHours());
        
    }

    @Test
    public void testLoadDegree() {
        Assert.assertNotNull("Testing to see if the return value of LoadDegree is correct", DataReader.loadDegree());
        Assert.assertEquals("Testing to see if the degree returned from the DataLoader is correct", );
    }
}
