package rest.connector.client;

/**
 * Run configurations > TestRestWSClient > Edit Configurations > Configuration >
 * {
 * Use classpath of module: rest-connector-web
 * Include dependencies with 'Provided' scope
 * }
 **/

/**
 * RESTEasy is bundled with WildFly, but you may want to upgrade RESTEasy in WildFly to
 * the latest version. The RESTEasy distribution comes with a zip file called resteasy-jboss-mod-
 * ules-<version>.zip. Unzip this file within the modules/system/layers/base/ directory of the WildFly
 * distribution. This will configure WildFly to use new versions of the modules listed in Section 3.1,
 * “RESTEasy modules in WildFly”.
 *
 * https://resteasy.github.io/downloads.html
 *
 * https://stackoverflow.com/questions/14458450/what-to-use-instead-of-org-jboss-resteasy-client-clientrequest
 *
 * https://dzone.com/articles/jboss-modules-suck-it%E2%80%99s
 *
 * <!-- deployment structure -->
 * https://developer.jboss.org/thread/271667
 *
 * add to classpath: org.jboss.spec.javax.ws.rs : jboss-jaxrs-api_2.1_spec : 1.0.2.Final
 * convert to Project Structure > Libraries >  > Convert to Repository Library: javax.ws.rs:jsr311-api:1.1.1.jar
 *
 * https://resteasy.github.io/docs.html
 *
 * <!-- DEPENDENCY CONFLICT - with: jsr311-api -->
 * javax.ws.rs:jsr311-api:1.1.1.jar
 * https://github.com/kongchen/swagger-maven-plugin
 * <!-- Finding conflicting dependency -->
 * mvn install -> mvn dependency tree -U -X -->
 * mvn compile dependency:tree
 *
 * If in location:
 * .../IdeaProjects/MavenProjects/SoapRest/soap-rest-ear/target/soap-rest-ear/lib
 * there's jar called:
 * jsr311-api.jar
 * it's still added by some dependency
 *
 * To exclude sub-dependency from dependency use something like:
 * <dependency>
 * <groupId>io.swagger</groupId>
 * <artifactId>swagger-jaxrs</artifactId>
 * <version>1.5.16</version>
 * <!-- EXCLUDE BAD LIBRARY -->
 * <exclusions>
 * <exclusion>
 * <groupId>javax.ws.rs</groupId>
 * <artifactId>jsr311-api</artifactId>
 * </exclusion>
 * </exclusions>
 * </dependency>
 */

public class TestRestWSClient {
    public static final String className = "TestRestWSClient";
    public static final String host = "http://localhost:8080";
    public static final String webContext = "/rest-api-web";
    public static final String appUrl = host + webContext + "/TestRestApp";
    public static final String webServiceUrl = appUrl + "/TestRestWS";

    public static void main(String[] argv) throws Exception {
        // Please, do not remove this line from file template, here invocation of web service will be inserted
        String functionName = "main()";
        System.out.println("\t" + className + " >>> " + functionName + " \\/\\/\\/\\/");
        JacksonTesting.jacksonTesting();
        // jaxb - XML <-> Java // Java Architecture for XML Binding
        RestClientTesting.restClientTesting();
        ProtoBuffTesting.protoBuffTesting();
        System.out.println("\t" + className + " >>> " + functionName + " /\\/\\/\\/\\");
    }

}
