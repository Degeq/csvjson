import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;  

public class Main {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {

        List<Employee> staff = read("staff.xml");
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

    public static List<Employee> read(String filePath) throws IOException, SAXException, ParserConfigurationException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File(filePath));

        Node root = doc.getDocumentElement();
        NodeList nodeList = doc.getElementsByTagName("employee");
        List<Employee> staff = new ArrayList<>();

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            System.out.println("{");
            if (Node.ELEMENT_NODE == node.getNodeType()) {
                Element fstElmnt = (Element) node;

                //Получим ID из текущего элемента
                NodeList fstNmElmntLst = fstElmnt.getElementsByTagName("id");
                Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
                NodeList fstNm = fstNmElmnt.getChildNodes();
                String id = fstNm.item(0).getNodeValue();
                System.out.println("id : " + id);

                //Получим Имя из текущего элемента
                NodeList sndNmElmntLst = fstElmnt.getElementsByTagName("firstName");
                Element sndNmElmnt = (Element) sndNmElmntLst.item(0);
                NodeList sndNm = sndNmElmnt.getChildNodes();
                String firstName = sndNm.item(0).getNodeValue();
                System.out.println("firstName : " + firstName);

                //Получим Фамилию из текущего элемента
                NodeList thrdNmElmntLst = fstElmnt.getElementsByTagName("lastName");
                Element thrdNmElmnt = (Element) thrdNmElmntLst.item(0);
                NodeList thrdNm = thrdNmElmnt.getChildNodes();
                String lastName = thrdNm.item(0).getNodeValue();
                System.out.println("lastName : " + lastName);

                //Получим Страну из текущего элемента
                NodeList frthNmElmntLst = fstElmnt.getElementsByTagName("country");
                Element frthNmElmnt = (Element) frthNmElmntLst.item(0);
                NodeList frthNm = frthNmElmnt.getChildNodes();
                String country = frthNm.item(0).getNodeValue();
                System.out.println("country : " + country);

                //Получим Возраст из текущего элемента
                NodeList ffthNmElmntLst = fstElmnt.getElementsByTagName("age");
                Element ffthNmElmnt = (Element) ffthNmElmntLst.item(0);
                NodeList ffthNm = ffthNmElmnt.getChildNodes();
                String age = ffthNm.item(0).getNodeValue();
                System.out.println("age : " + age);

                System.out.println("}");

                Employee employee = new Employee(Long.parseLong(id), firstName,
                        lastName, country, Integer.parseInt(age));

                staff.add(employee);
            }
        }
        return staff;
    }

    public static void JSONParser () {

    }
}
