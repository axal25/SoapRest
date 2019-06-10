package rest.api.proto;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.rest.MessageCollection;

import java.util.Arrays;
import java.util.List;

// DATA CLASS - to represent protocol buffers
public class ProtoStudent {
    public static final String className = "ProtoStudent";
    private final String name;
    private final int age;
    private final String[] courses;

    /*************************************/
    /******** SETTERS AND GETTERS ********/
    /*************************************/
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String[] getCourses() {
        return courses;
    }

    public List<String> getCoursesAsList() {
        return Arrays.asList( getCourses() );
    }

    @Override
    public String toString() {
        final String functionName = "@Override toString()";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString( this );
            return jsonString;
        } catch (Exception e) {
            System.out.println(MessageCollection.getException(className, functionName, e));
            return null;
        }
    }

    /*************************************/
    /************ CONSTUCTORS ************/
    /*************************************/
    private ProtoStudent(final String name,final int age,final String[] courses) {
        this.name = name;
        this.age = age;
        this.courses = courses;
    }

    /*************************************/
    /*********** PROTO BUILDER ***********/
    /*************************************/
    /**

     * Builder class for instantiating an instance of

     * enclosing Album class.

     */

    public static class Builder {
        public static final String className = "Builder";
        private String name;
        private int age;
        private String[] courses = new String[]{};

        public Builder() { /** do nothing **/ }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public Builder setCourses(String[] courses) {
            this.courses = courses;
            return this;
        }

        public Builder setCourses( List<String> courses ) {
            this.courses = courses.toArray( new String[ courses.size() ] );
            return this;
        }

        public ProtoStudent build() {
            return new ProtoStudent( this.name, this.age, this.courses);
        }
    }
}