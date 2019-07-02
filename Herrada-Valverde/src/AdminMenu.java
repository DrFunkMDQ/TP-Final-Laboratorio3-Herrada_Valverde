import java.util.List;
import java.util.Scanner;

public class AdminMenu {
    //ATTRIBUTES

    //CONSTRUCTORS

    //GETTERS && SETTERS

    //METHODS
    public static void adminMenu(Admin admin, ListMgmt userList, ListMgmt courseList){
        int opt;
        Student newStudent;
        Instructor newInstructor;
        Admin newAdmin;
        Course newCourse;
        boolean exit = false;
        char ans;
        Scanner scan = new Scanner(System.in);
        while (!exit){
            System.out.println("Select an option:\n");
            System.out.println("1)Create a Student\n");
            System.out.println("2)Create an Instuctor \n");
            System.out.println("3)Create a Course\n");
            System.out.println("4)Exit\n");
            opt = scan.nextInt();
            switch (opt){
                case 1:
                    newStudent = admin.createNewStudent(admin.getLastUserId(userList) + 1);
                    userList.addToList(newStudent);
                    break;
                case 2:
                    newInstructor = admin.createNewInstructor(admin.getLastUserId(userList) + 1);
                    userList.addToList(newInstructor);
                    break;
                case 3:
                    newCourse = admin.createCourse(userList ,admin.getLastCourseId(courseList) + 1);
                    courseList.addToList(newCourse);
                    break;
                case 4:
                    exit = true;
                    break;
            }
            System.out.println("Do you want to continue? Y/N");
            ans = scan.next().toUpperCase().charAt(0);
            if(ans == 'N')
                exit = true;


        }
    }

}
