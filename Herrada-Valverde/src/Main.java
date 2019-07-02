import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // CARGA DE DATOS //
        ListMgmt<Course> coursesList = new ListMgmt<Course>();
        PersistenceCollecion<User> userPersistance = new PersistenceCollecion<User>();
        PersistenceCollecion<Course> coursePersistance = new PersistenceCollecion<Course>();
        try {
            coursesList = coursePersistance.ReadFromFile("courses.dat");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // GENERACIÓN DE ADMIN PARA SISTEMA VACIO //
        /*Admin adm = new Admin(1,99, "Admin", "Admin", "12345");
        ListMgmt<User> usersList = new ListMgmt<User>();
        usersList.addToList(adm);
        try {
            usersList = userPersistance.ReadFromFile("users.dat");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }*/
        // GENERACIÓN DE ADMIN PARA SISTEMA VACIO //

        ListMgmt<User> usersList = new ListMgmt<User>();
        try {
            usersList = userPersistance.ReadFromFile("users.dat");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // CARGA DE DATOS //

        MenuOptions.mainMenu(usersList, coursesList);


        // PERSISTENCIA DE DATOS //
        try {
            coursePersistance.WriteIntoFile(coursesList, "courses.dat");
        } catch (Exception w) {
            System.out.println("Error writing the file");
        }

        try {
            userPersistance.WriteIntoFile(usersList, "users.dat");
        } catch (Exception w) {
            System.out.println("Error writing the file");
        }
        // PERSISTENCIA DE DATOS //

    }
}




