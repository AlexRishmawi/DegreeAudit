import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Array;

import java.util.ArrayList;
import java.util.UUID;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class testing {
    public static void main(String[] args) {
        ArrayList<User> user = DataReader.loadUser();
        for(User ok: user) {
            System.out.println(ok);
        }
    }
}
