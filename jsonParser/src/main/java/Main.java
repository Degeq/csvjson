import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        JSONParser parser = new JSONParser();
        List<Employee> staff = new ArrayList<>();
        try {
            Object obj = parser.parse(new BufferedReader(new FileReader("data.json")));
            JSONArray array = (JSONArray) obj;

            for (Object employee : array) {
                JSONObject jsonObject = (JSONObject) employee;
                Employee emp = gson.fromJson(String.valueOf(jsonObject), Employee.class);
                staff.add(emp);
                System.out.println(emp);
            }

        } catch (IOException | ParseException exception) {
            exception.printStackTrace();
        }

    }
}
