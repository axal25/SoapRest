package rest.connector.client;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.rest.BadMatchRegExpException;
import model.rest.TestRestStudent;

import java.io.IOException;

public class JacksonTesting {
    public final static String className = "JacksonTesting";

    public static void jacksonTesting() {
        String functionName = "JacksonTesting()";
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = "{\"name\":\"Jack\", \"age\":\"26\"}";

        System.out.println("\t" + className + " >>> " + functionName + " \\/\\/\\/\\/ \n\r");
        System.out.println("jsonString: " + jsonString + "\n\r");

        try {
            TestRestStudent testRestStudent = new TestRestStudent();
            System.out.println("testRestStudent = new TestRestStudent(): \n\r" + testRestStudent + "\n\r");

            testRestStudent = new TestRestStudent("Jack", 26);
            System.out.println("testRestStudent = new TestRestStudent(\"Jack\", 26): \n\r" + testRestStudent + "\n\r");

            testRestStudent = new TestRestStudent(jsonString);
            System.out.println("testRestStudent = new TestRestStudent( jsonString ): \n\r" + testRestStudent + "\n\r");

            testRestStudent = objectMapper.readValue(jsonString, TestRestStudent.class);
            System.out.println("testRestStudent = objectMapper.readValue( jsonString, TestRestStudent.class ): \n\r" + testRestStudent + "\n\r");

            testRestStudent = TestRestStudent.getNewTestRestStudent(jsonString);
            System.out.println("testRestStudent = TestRestStudent.getNewTestRestStudent( jsonString ): \n\r" + testRestStudent + "\n\r");

            jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(testRestStudent);
            System.out.println("jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString( testRestStudent ): \n\r"
                    + jsonString + "\n\r");

            jsonString = TestRestStudent.toJson(testRestStudent);
            System.out.println("jsonString = TestRestStudent.getJsonString( testRestStudent ): \n\r" + jsonString + "\n\r");
        } catch (JsonParseException jsonParseE) {
            jsonParseE.printStackTrace();
        } catch (JsonMappingException jsonMapE) {
            jsonMapE.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (BadMatchRegExpException badMatchRegExpE) {
            badMatchRegExpE.printStackTrace();
        } finally {
            System.out.println("\t" + className + " >>> " + functionName + " /\\/\\/\\/\\ \n\r");
        }
    }
}
