import java.util.Scanner;

public class InstructorMenu {
    //ATTRIBUTES

    //CONSTRUCTORS

    //GETTERS && SETTERS

    //METHODS

    public static void instructorMenu(Instructor instructor, ListMgmt userList){
        int opt;
        boolean exit = false;
        String courseName;
        String ans;
        Scanner scan = new Scanner(System.in);
        while (!exit){
            System.out.println("Select an option:\n");
            System.out.println("1)Add Questions to course\n");
            System.out.println("2)Enroll users into course\n");
            System.out.println("3)Remove Student from a course\n");
            System.out.println("4)Exit\n");
            opt = scan.nextInt();
            switch (opt){
                case 1:
                    System.out.println("Enter the course name\n");
                    courseName = scan.next();
                    try {
                        instructor.uploadQuestions(instructor.searchCourseByName(courseName));
                    }catch (Exception e){
                        System.out.println("The course doesn't exists");
                    }
                    break;
                case 2:
                    System.out.println("Enter the course name\n");
                    courseName = scan.next();
                    try {
                        instructor.enrollStudents(instructor.searchCourseByName(courseName), userList);
                    }catch (Exception e){
                        System.out.println("The course doesn't exists");
                    }
                    break;
                case 3:
                    try {
                        instructor.removeStudentFromCourse(userList);
                    }catch(NullPointerException e){
                    System.out.println("The course doesn't exists");
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }

                    break;
                case 4:
                    break;
            }
            System.out.println("Do you want to continue? Y/N");
            ans = scan.next().toUpperCase();
            if(ans.equals("N"))
                exit = true;


        }
    }
}
