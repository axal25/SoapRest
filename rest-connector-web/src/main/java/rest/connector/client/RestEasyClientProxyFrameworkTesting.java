package rest.connector.client;

import model.rest.MessageCollection;
import model.rest.TestRestStudent;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import rest.api.forms.FileUploadForm;
import rest.connector.client.exceptions.TestsFailedException;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.io.File;

public class RestEasyClientProxyFrameworkTesting {
    public static final String className = "RestEasyClientProxyFrameworkTesting";

    public static void restEasyClientProxyFrameworkTesting(int numberOfTests,
                                                           TestRestStudent newStudent,
                                                           TestRestStudent existingStudent) throws TestsFailedException {
        final String functionName = "restEasyProxyFrameworkTesting()";
        System.out.println("\t" + className + " >>> " + functionName + " \\/\\/\\/\\/ \n\r");

        String[][] responses = new String[numberOfTests][];
        for (int i = 0; i < numberOfTests; i++) {
            responses[i] = new String[]{null, null};
        }
        try {
            // Request Creation with Connection Pool
            // https://www.baeldung.com/resteasy-client-tutorial

            // https://stackoverflow.com/questions/39861900/resteasy003145-unable-to-find-a-messagebodyreader-of-content-type-application-j/49865649?fbclid=IwAR0XvKuypMzuTEni8FtQQUIKYYDtaaFsWOGH-L3xyHXK1W1Pph53lMEIcYo#49865649
            /**********************************************************************************************************
            **** Can be use instead of dependency org.jboss.resteasy:resteasy-jackson2-provider:${resteasy.version} ***
            ***********************************************************************************************************
            ResteasyProviderFactory resteasyProviderFactoryInstace = ResteasyProviderFactory.getInstance();
            RegisterBuiltin.register( resteasyProviderFactoryInstace );
            resteasyProviderFactoryInstace.registerProvider( ResteasyJackson2Provider.class );
            ***********************************************************************************************************/

            ResteasyClient resteasyClient = new ResteasyClientBuilder().build();
            ResteasyWebTarget resteasyWebTarget = resteasyClient.target(UriBuilder.fromPath(TestRestWSClient.appUrl));
            ClientServiceInterface proxy = resteasyWebTarget.proxy(ClientServiceInterface.class);

            int testNumber = 0;
            responses[testNumber] = new String[]{"/getClichedMessage", null};
            responses[testNumber][1] = proxy.getClichedMessage();
            // getClichedMessage method returns String, so we can't verify response status code
            // So we can't do:
            // tmpResponse = proxy.getClichedMessage();
            // responses[testNumber][1] = checkResponseStatusCode( tmpResponse, responses[testNumber][0] );

            testNumber++;
            responses[testNumber] = new String[]{"/getHelloWorldMessage", null};
            Response tmpResponse = proxy.getHelloWorldMessage();
            responses[testNumber][1] = RestClientTesting.checkResponseStatusCode( tmpResponse, responses[testNumber][0] ) .readEntity(String.class);

            testNumber++;
            responses[testNumber] = new String[]{"/getPaths", null};
            responses[testNumber][1] = proxy.getPaths();
            // getPaths method returns String, so we can't verify response status code
            // So we can't do:
            // tmpResponse = proxy.getPaths();
            // responses[testNumber][1] = checkResponseStatusCode( tmpResponse, responses[testNumber][0] );

            testNumber++;
            responses[testNumber] = new String[]{"/ (methodName = getDefault)", proxy.getDefault().readEntity(String.class)};
            tmpResponse = proxy.getDefault();
            responses[testNumber][1] = RestClientTesting.checkResponseStatusCode( tmpResponse, responses[testNumber][0] ) .readEntity(String.class);

            testNumber++;
            responses[testNumber] = new String[]{"/getHelloWorld/aPathName", null};
            tmpResponse = proxy.getHelloWorld("aPathName", null);
            responses[testNumber][1] = RestClientTesting.checkResponseStatusCode( tmpResponse, responses[testNumber][0] ) .readEntity(String.class);

            /**   404 code - checkResponseStatusCode throws Exception and returns response = null
            testNumber++;
            responses[testNumber] = new String[]{"/getHelloWorld/{?QueryName=aQueryName}", null};
            tmpResponse = proxy.getHelloWorld("", "aQueryName");
            responses[testNumber][1] = checkResponseStatusCode( tmpResponse, responses[testNumber][0] ) .readEntity(String.class);
            responses[testNumber][0] = responses[testNumber][0] + "\n\r" + "Without method \"checkResponseStatusCode\"" +  "\n\r"
                    "Predicted response will be: " +
                    "RESTEASY003210: Could not find resource for full path: " +
                    "http://localhost:8080/rest-api-web/TestRestApp/TestRestWS/getHelloWorld/?QueryName=aQueryName";
            **/

            testNumber++;
            responses[testNumber] = new String[]{"/getHelloWorld/{aPathName}?{QueryName=aQueryName}", null};
            tmpResponse = proxy.getHelloWorld("aPathName", "aQueryName");
            responses[testNumber][1] = RestClientTesting.checkResponseStatusCode( tmpResponse, responses[testNumber][0] ) .readEntity(String.class);

            testNumber++;
            responses[testNumber] = new String[]{"/getStudent/{pathStudentKey}?{QueryStudentKey=aQueryStudentKey}", null};
            TestRestStudent tmpStudent = proxy.getStudent("JacekObjectMapper", null);
            responses[testNumber][1] = tmpStudent.toJson();
            // getStudent method returns TestRestStudent, so we can't verify response status code
            // So we can't do:
            // tmpResponse = proxy.getStudent("aPathStudentKey", "aQueryStudentKey");
            // responses[testNumber][1] = checkResponseStatusCode( tmpResponse, responses[testNumber][0] );

            testNumber++;
            responses[testNumber] = new String[]{"/getStudent/{pathStudentKey}?{QueryStudentKey=aQueryStudentKey}", null};
            tmpStudent = proxy.getStudent("JacekObjectMapper", "Jack #9");
            responses[testNumber][1] = tmpStudent.toJson();

            testNumber++;
            responses[testNumber] = new String[]{"/getListOfStudents", null};
            java.util.List<TestRestStudent> tmpListOfStudents = proxy.getListOfStudents();
            responses[testNumber][1] = TestRestStudent.toJson( tmpListOfStudents );
            // getListOfStudents method returns java.util.List<TestRestStudent>, so we can't verify response status code
            // So we can't do:
            // tmpResponse = proxy.getListOfStudents();
            // responses[testNumber][1] = checkResponseStatusCode( tmpResponse, responses[testNumber][0] );

            testNumber++;
            responses[testNumber] = new String[]{"/addStudent - new student", null};
            tmpResponse = proxy.addStudent(newStudent);
            responses[testNumber][1] = RestClientTesting.checkResponseStatusCode( tmpResponse, responses[testNumber][0] ) .readEntity(String.class);

            /****************************************************************************************************/
            /****************** Already existing students - status 304 ******************************************/
            /****************************************************************************************************
             **A 304 response cannot contain a message-body; it is always terminated  ***************************
             * by the first empty line after the header fields.                       ***************************
             * Source: https://tools.ietf.org/html/rfc7232#section-4.1                ***************************
             ****************************************************************************************************/
            /**
            testNumber++;
            responses[testNumber] = new String[]{"/addStudent - already existing whole student", null};
            tmpResponse = proxy.addStudent(existingStudent);
            tmpResponse = checkResponseStatusCode( tmpResponse, responses[testNumber][0] );
            responses[testNumber][1] = tmpResponse.readEntity(String.class);
            // checkResponseStatusCode( tmpResponse, responses[testNumber][0] ) .readEntity(String.class);

            testNumber++;
            TestRestStudent existingStudentNullCourses = new TestRestStudent("Jack #6",-1);
            responses[testNumber] = new String[]{"/addStudent - already existing student without courses (courses = null)", null};
            tmpResponse = proxy.addStudent(existingStudentNullCourses);
            tmpResponse = checkResponseStatusCode( tmpResponse, responses[testNumber][0] );
            responses[testNumber][1] = tmpResponse.readEntity(String.class);
            // checkResponseStatusCode( tmpResponse, responses[testNumber][0] ) .readEntity(String.class);
            **/

            testNumber++;
            existingStudent.setAge(10);
            responses[testNumber] = new String[]{"/updateStudent - existing Student but with age = " + existingStudent.getAge(), null};
            tmpResponse = proxy.updateStudent(existingStudent);
            responses[testNumber][1] = RestClientTesting.checkResponseStatusCode( tmpResponse, responses[testNumber][0] ) .readEntity(String.class);

            testNumber++;
            responses[testNumber] = new String[]{"/deleteStudent - existing student (pathParam) = " + existingStudent.getName(), null};
            tmpResponse = proxy.deleteStudent(existingStudent.getName(), null, null);
            responses[testNumber][1] = RestClientTesting.checkResponseStatusCode( tmpResponse, responses[testNumber][0] ) .readEntity(String.class);

            testNumber++;
            responses[testNumber] = new String[]{"/deleteStudent - existing student (queryParam) = " + existingStudent.getName(), null};
            tmpResponse = proxy.deleteStudent("obligatoryPathParam", existingStudent.getName(), null);
            responses[testNumber][1] = RestClientTesting.checkResponseStatusCode( tmpResponse, responses[testNumber][0] ) .readEntity(String.class);

            testNumber++;
            responses[testNumber] = new String[]{"/deleteStudent - existing student (TestRestStudent - json) = " + existingStudent.getName(), null};
            tmpResponse = proxy.deleteStudent("obligatoryPathParam", null, existingStudent);
            responses[testNumber][1] = RestClientTesting.checkResponseStatusCode( tmpResponse, responses[testNumber][0]) .readEntity(String.class);

            testNumber++;
            String pathImageName = "doggo.jpg";
            responses[testNumber] = new String[]{"/uploadImageV2" + File.separator + pathImageName +
                    " | image = " + pathImageName, null};
            byte[] bytes = ImageFetcher.getImageNameToBytes( pathImageName );
            if( bytes == null ) System.err.println(className + " >>> " + functionName + ": bytes == null");
            else System.out.println(className + " >>> " + functionName + ": bytes != null");
            FileUploadForm fileUploadForm = new FileUploadForm();
            fileUploadForm.setData( bytes );
            bytes = fileUploadForm.getData();
            if( bytes == null ) System.err.println(className + " >>> " + functionName + ": bytes = fileUploadForm.getData() == null");
            else System.out.println(className + " >>> " + functionName + ": bytes = fileUploadForm.getData() != null");
            tmpResponse = proxy.uploadImageV2( pathImageName, fileUploadForm );
            responses[testNumber][1] = RestClientTesting.checkResponseStatusCode( tmpResponse, responses[testNumber][0]) .readEntity(String.class);

        } catch (Exception e) {
            System.out.println( MessageCollection.getException(className, functionName, e) );
        } finally {
            RestClientTesting.finallyCheckTestsArray(className, functionName, responses);
        }

        System.out.println("\t" + className + " >>> " + functionName + " /\\/\\/\\/\\ \n\r");
    }
}
