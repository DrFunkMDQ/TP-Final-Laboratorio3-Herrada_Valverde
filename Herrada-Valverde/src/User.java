import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    //ATTRIBUTES
    private int id;
    private int dni;
    private String name;
    private String surName;
    private String password;
    private ListMgmt<Course> courseList;

    //CONSTRUCTOR
    public User(int id, int dni, String name, String surName, String password) {
        setId(id);
        setDni(dni);
        setName(name);
        setPassword(password);
        setSurName(surName);
        setCourseList();
    }

    //GETTERS && SETTERS

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ListMgmt<Course> getCourseList() {
        return courseList;
    }

    private void setCourseList() {
        this.courseList = new ListMgmt<Course>();
    }

    //METHODS

    @Override
    public boolean equals(Object o) {
        boolean equals = false;
        User user = (User) o;
        if (o instanceof User) {
            equals = this.id == ((User) o).getId();
        }
        return equals;
    }

    @java.lang.Override
    public java.lang.String toString() {
        String msg = "ID: %d\nName: %s\nSurName: %s";
        return  String.format(msg, getId(), getDni(), getName(), getSurName());
    }
}
