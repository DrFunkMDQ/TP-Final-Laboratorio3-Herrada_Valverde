import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MenuOptions {
    //ATTRIBUTES

    //CONSTRUCTORS

    //GETTERS && SETTERS

    //METHODS
    public static void mainMenu(ListMgmt userList, ListMgmt courseList){
        User loggedUser = null;
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to 'Laboratorio 3 Learning System'\n");
        System.out.println("1) Login\n");
        if(scan.nextInt()==1){
            loggedUser = login(userList);
        }
        if(loggedUser instanceof Admin)
            AdminMenu.adminMenu((Admin)loggedUser, userList, courseList);
        else
            if(loggedUser instanceof Student)
                StudentMenu.studentMenu((Student)loggedUser);
            else
                if(loggedUser instanceof Instructor)
                    InstructorMenu.instructorMenu((Instructor)loggedUser, userList);
                else
                    mainMenu(userList,courseList);
    }

    public static User login(ListMgmt userList){
        String pswd;
        int dni;
        User validLogin = null;
        Scanner scan = new Scanner(System.in);
        try {
            System.out.println("Ingrese DNI");
            dni = scan.nextInt();
            System.out.println("Ingrese contraseña");
            pswd = scan.next();
            validLogin = validateUser(dni, pswd, userList);
            if (validLogin == null) {
                System.out.println("Usuario/Contraseña incorrecto");
                validLogin = login(userList);
            }
        }catch (InputMismatchException e){
            System.out.println("Wrong data on the fields");
        }catch (Exception e){
            System.out.println("Login error");
        }
        return validLogin;
    }

    public static User validateUser(int dni, String pswd, ListMgmt userList){
        int i;
        User aux = null;
        boolean valid = false;
        for(i = 0; i < userList.size() && !valid; i++){
            aux = (User)userList.get(i);
            if(aux.getDni() == dni && aux.getPassword().equals(pswd))
                valid = true;
        }
        if(!valid)
            aux = null;
        return aux;
    }
}
