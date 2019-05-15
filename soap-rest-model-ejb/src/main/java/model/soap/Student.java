package model.soap;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(namespace = "student")
public class Student {
    String name = "";
    String surname = "";
    List<String> courses = null;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    public Student() {
        courses = new java.util.ArrayList<String>();
    }

    @XmlElementWrapper(name = "courseS")
    @XmlElement(name = "course")
    public List<String> getCourses() {
        return courses;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }

    public boolean addCourse( String course ) {
        return courses.add( course );
    }
}
