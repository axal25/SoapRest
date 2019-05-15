package model.rest;

import javax.xml.bind.annotation.*;

// @Data
// @AllArgsConstructor
// @NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "TestRestStudent")
@XmlType(name = "", propOrder = { "className", "name", "age", "courses" })
public class TestRestStudent {
    @XmlElement
    public final static String className = "TestRestStudent";
    @XmlElement
    private String name;
    @XmlElement
    private int age;
    @XmlElementWrapper(name = "courseS")
    @XmlElement(name = "course")
    private String[] courses;

    /*************************************/
    /******** SETTERS AND GETTERS ********/
    /*************************************/
    public static String getClassName() {
        return className;
    }

    public static void setClassName( String className ) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String[] getCourses() {
        return courses;
    }

    public void setCourses(String[] courses) {
        this.courses = courses;
    }

    /*************************************/
    /************ CONSTUCTORS ************/
    /*************************************/
    public TestRestStudent() {
        name = null;
        age = -2;
        courses = null;
    }

    public TestRestStudent(String newName, int newAge) {
        name = newName;
        age = newAge;
        courses = null;
    }

    public TestRestStudent(String jsonString) throws BadMatchRegExpException {
        final String functionName = "TestRestStudent(String jsonString)";
        if( jsonString.matches("\\{(\"name\")(\\s*:\\s*\")([A-z,0-9,\\s,#])*(\"\\s*,\\s*)(\"age\")(\\s*:\\s*\")(\\d*)\"}") ) {
            String newName = jsonString.replaceFirst("\\{(\"name\")(\\s*:\\s*\")","");
            newName = newName.replaceFirst("(\"\\s*,\\s*)(\"age\")(\\s*:\\s*\")(\\d*)\"}","");

            String newAgeS = jsonString.replaceFirst("\\{(\"name\")(\\s*:\\s*\")([A-z,0-9,\\s,#])*(\"\\s*,\\s*)(\"age\")(\\s*:\\s*\")","");
            newAgeS = newAgeS.replaceFirst("\"}","");
            int newAge = Integer.parseInt( newAgeS );

            name = newName;
            age = newAge;
            courses = null;
        }
        else {
            name = null;
            age = -1;
            courses = null;
            throw new BadMatchRegExpException(className + " >>> " + functionName + "\n\r" + "Wrong jsonString - doesn't match regular expression");
        }
    }

    /*************************************/
    /************ OVERRIDDEN *************/
    /*************************************/

    @Override
    public String toString() {
        final String functionName = "Overridden toString() method";
        return className + " >>> " + functionName + " >>> " + className +
                " { name: " + name + ", age: " + age + ", courses: {" + coursesToString( courses ) + "} }";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestRestStudent student = (TestRestStudent) o;
        if( !equals(name, student.getName()) ) return false;

        if( age != student.getAge()) return false;
        if( courses == student.getCourses() ) return true;
        if( courses.length != student.getCourses().length ) return false;
        for (int i = 0; i < courses.length; i++) {
            if( !equals(courses[i], student.getCourses()[i]) ) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (courses != null ? courses.hashCode() : 0);
        return result + age;
    }

    /*************************************/
    /********* HELPER FUNCTIONS **********/
    /*************************************/

    public String coursesToString( String[] courses ) {
        if( courses != null && courses.length > 0 ) {
            String sCourses = "";
            for (int i = 0; i < courses.length; i++) {
                sCourses = sCourses + "\n" + courses[i];
            }
            return sCourses;
        }
        else {
            if( courses != null && courses.length == 0 ) return " --- ";
            return null;
        }
    }

    public static boolean equals(String s1, String s2) {
        if( s1 == s2 ) return true;
        else {
            if( s1 == null && s2 != null ) return false;
            if( s1 != null && s2 == null ) return false;
            if( s1.isEmpty() && s2.isEmpty() ) return true;
            else {
                if( s1.compareTo(s2) == 0 ) return true;
                else return false;
            }
        }
    }
}
