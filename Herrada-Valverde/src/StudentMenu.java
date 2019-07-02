import java.util.Scanner;

public class StudentMenu {
    //ATTRIBUTES

    //CONSTRUCTORS

    //GETTERS && SETTERS

    //METHODS
    public static void studentMenu(Student student) {
        boolean exit = false;
        String ans;
        int opt;
        Scanner scan = new Scanner(System.in);
        while (!exit) {
            System.out.println("Select an option:\n");
            System.out.println("1)Courses to be completed\n");
            System.out.println("2)Start course\n");
            System.out.println("3)View your average\n");
            System.out.println("4)Exit\n");
            opt = scan.nextInt();
            switch (opt) {
                case 1:
                    student.showCourseToBeCompleted();
                    break;
                case 2:
                    completeCourse(student);
                    break;
                case 3:
                    try {
                        System.out.println(student.getAverage());
                    }catch (NullPointerException e){
                        System.out.println("Student doesn't have any grade");
                    }catch (Exception e){
                        System.out.println("Error");
                    }
                    break;
                case 4:
                    exit = true;
                    break;
            }
            System.out.println("Do you want to continue? Y/N");
            ans = scan.next().toUpperCase();
            if (ans.equals("N"))
                exit = true;
        }

    }

    public static void completeCourse(Student student) {
        Course course = null;
        Question question = null;
        boolean ans;
        int correct = 0;
        Scanner reader = new Scanner(System.in);
        System.out.println("Ingrese el ID del curso:");
        int id = reader.nextInt();
        try {
            course = student.searchCourseById(id);
            if (!course.getCalificationMap().containsKey(student.getDni())) {
                for (int i = 0; i < course.getQuestionList().size(); i++) {
                    question = (Question) course.getQuestionList().get(i);
                    if (question instanceof TrueOrFalse)
                        ans = ((TrueOrFalse) question).answerQuestion();
                    else if (question instanceof ByValue)
                        ans = ((ByValue) question).answerQuestion();
                    else
                        ans = ((MultipleChoice) question).answerQuestion();
                    if (ans)
                        correct++;
                }
                course.addGrade(student, course.calculateGrade(correct));
            } else
                System.out.println("The course is already finished.");
        }catch (NullPointerException e){
            System.out.println("Course ID not found");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}

