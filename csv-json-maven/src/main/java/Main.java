import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args){

        String fileName = "data.csv";

        File csvWorkers = new File(fileName);
        creationFile(csvWorkers);

        String[][] employees = {
                "1,John,Smith,USA,25".split(","),
                "2,Inav,Petrov,RU,23".split(",")
        };

       WriterCSV(csvWorkers,employees);

       String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};

       List<Employee> staff = ReaderCSVToList(csvWorkers, columnMapping);
       staff.forEach(System.out::println);

       GsonBuilder builder = new GsonBuilder();
       Gson gson = builder.create();

       String jsonFileName = "data.json";
       File workersJson = new File(jsonFileName);
       creationFile(workersJson);

       try (FileWriter writer = new FileWriter(workersJson)) {
           for (Employee employee : staff) {
               writer.write(gson.toJson(employee));
               writer.flush();
           }
       } catch (IOException exception) {
           exception.printStackTrace();
       }

    }

    public static void creationFile(File myFile) {
        try {
            if (myFile.createNewFile()) {
                System.out.println("Файл" + myFile.getName() + " создан");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void WriterCSV(File file, String[][] lines) {
        try(CSVWriter writer = new CSVWriter(new FileWriter(file, false))) {
            for (String[] employee : lines) {
                writer.writeNext(employee);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Employee> ReaderCSVToList(File file, String[] columnMapping) {
        List <Employee> staff = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            ColumnPositionMappingStrategy<Employee> strategy =
                    new ColumnPositionMappingStrategy<>();
            strategy.setType(Employee.class);
            strategy.setColumnMapping(columnMapping);

            CsvToBean<Employee> csv = new CsvToBeanBuilder<Employee>(reader)
                    .withMappingStrategy(strategy)
                    .build();
            staff = csv.parse();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return staff;
    }
}
