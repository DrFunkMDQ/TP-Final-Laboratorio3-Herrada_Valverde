import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.SortedMap;

public class Instructor extends User {
    //ATTRIBUTES

    //CONSTRUCTOR

    public Instructor(int id, int dni, String name, String surName, String password) {
        super(id, dni, name, surName, password);
    }


    //GETTERS && SETTERS

    //METHODS

    //Recibe un estudiante y un curso
    //Agrega el estudiante a la lista de estudiantes del curso
    //Agrega el curso a la lista de cursos del estudiante
    public void addStudentToCourse(Student student, Course course) {
        try {
            if (!course.isEmptyQuestionList()) {//Evalua que la lista no esté vacia
                course.getStudentSet().add(student);//Agrega el estudiante a la lista de estudiantes del curso
                student.getCourseList().addToList(course);//Agrega el curso a la lista de cursos del estudiante
            }
            else
                System.out.println("The course has not quesitions. First, add questions to enroll students.");
        } catch (NullPointerException e) {
            System.out.println("Lista no inicializada");
        } catch (Exception e) {
            System.out.println("error");
        }
    }

    //Recibe una pregunta y un curso
    //Agrega la pregunta a la lista de preguntas del curso
    public void addQuestionToCourse(Question question, Course course) {
        try {
            course.getQuestionList().addToList(question);//Agrega la pregunta a la lista de preguntas del curso
        } catch (NullPointerException e) {
            System.out.println("Lista no inicializada");
        } catch (Exception e) {
            System.out.println("error");
        }
    }

    //Pide los datos pertinentes por teclado y retorna un objeto TrueOrFalse
    public TrueOrFalse createTrueOrFalse() {
        Scanner scan = new Scanner(System.in);
        int opt = 0;
        boolean answer = false;
        String desc = null;
        try {
            System.out.println("Enter the question\n");
            desc = scan.nextLine();
            System.out.println("Enter the answer (1: True 2: False)\n");
            opt = scan.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid value");
        } catch (Exception e) {
            System.out.println("Error creating question");
        }
        if (opt == 1)
            answer = true;
        TrueOrFalse aux = new TrueOrFalse(desc, answer);
        return aux;
    }

    //Pide los datos pertinentes por teclado y retorna un objeto ByValue
    public ByValue createByValue() {
        Scanner scan = new Scanner(System.in);
        String answer = null;
        String desc = null;
        try {
            System.out.println("Enter the question\n");
            desc = scan.nextLine();
            System.out.println("Enter the answer\n");
            answer = scan.next();
        } catch (InputMismatchException e) {
            System.out.println("Invalid value");
        } catch (Exception e) {
            System.out.println("Error creating question");
        }
        ByValue aux = new ByValue(desc, answer);
        return aux;
    }

    //Pide los datos pertinentes por teclado y retorna un objeto MultipleChoice
    public MultipleChoice createMultipleChoice() {
        Scanner scan = new Scanner(System.in);
        int valid = 0;
        String op1 = null;
        String op2 = null;
        String op3 = null;
        String desc = null;
        try {
            System.out.println("Enter the question\n");
            desc = scan.nextLine();
            System.out.println("Enter the first answer\n");
            op1 = scan.nextLine();
            System.out.println("Enter the second answer\n");
            op2 = scan.nextLine();
            System.out.println("Enter the third answer\n");
            op3 = scan.nextLine();
            System.out.println("Choose the correct answer (1, 2 or 3)\n");
            valid = scan.nextInt();
            while (valid != 1 && valid != 2 && valid != 3) {
                valid = scan.nextInt();
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid value");
        } catch (Exception e) {
            System.out.println("Error creating question");
        }
        MultipleChoice aux = new MultipleChoice(desc, valid, op1, op2, op3);
        return aux;
    }

    //Menu para crear Preguntas
    public void uploadQuestions(Course course) {
        Scanner scan = new Scanner(System.in);
        int opt = 0;
        String cont;
        boolean exit = false;
        Question auxQuestion;
        if(course != null){
            while (!exit) {
                System.out.println("What Question Type do you want to create?\n");
                System.out.println("1) True Or False\n 2) Regular Question\n 3) Multiple Choice");
                opt = scan.nextInt();
                switch (opt) {//seleccioan segun tipo de pregunta a crear
                    case 1:
                        addQuestionToCourse(createTrueOrFalse(), course);
                        break;
                    case 2:
                        addQuestionToCourse(createByValue(), course);
                        break;
                    case 3:
                        addQuestionToCourse(createMultipleChoice(), course);
                        break;
                }
                System.out.println("Do you want to continue adding questions? Y/N");
                cont = scan.next().toUpperCase();
                if (cont.equals("N"))
                    exit = true;
            }
        }else
            System.out.println("The course doesn't exists.");
    }

    //Recibe un curso y la lista de usuarios
    //Pide por teclado un DNI y lo retorna de la lista (si es que existe)
    //Agrega estudiante al curso
    public void enrollStudents(Course course, ListMgmt userList) {
        Scanner scan = new Scanner(System.in);
        int dni;
        String cont;
        boolean exit = false;
        Student aux;
        Question auxQuestion;
        while (!exit) {
            System.out.println("Type the DNI of the Student\n");
            dni = scan.nextInt();//Pide por teclado un DNI
            aux = searchStudentByDNI(userList, dni);//y lo retorna de la lista (si es que existe)
            if(aux != null)
                addStudentToCourse(aux, course);//Agrega estudiante al curso
            else
                System.out.println("DNI not found or is not an Student");
            System.out.println("Do you want to add more students? Y/N");
            cont = scan.next().toUpperCase();
            if (cont.equals("N"))
                exit = true;
        }
    }

    //Busca estudiante por DNI en lista de usuarios y lo retorna
    public Student searchStudentByDNI(ListMgmt userListMgmt, int dni) {
        Student student = null;
        User actual = null;
        boolean flag = false;
        for (int i = 0; i < userListMgmt.size() && !flag; i++) {//Recorre lista
            actual = (User) userListMgmt.get(i);
            if (actual.getDni() == dni && actual instanceof Student) {//Compara si el dni corresponde al estudiante
                flag = true;
                student = (Student) actual;//almacena estudiante
            }
        }
        return student;
    }

    //Recibe lista de usuarios
    //Pide el ingreso por teclado del DNI del alumno y nombre del Curso
    //en caso de existir ambos y de que el alumno se encuentre inscripto en el curso
    //lo elimina del mismo. De habeerlo finalizado ya al curso también elimina sus calificaciones
    public void removeStudentFromCourse(ListMgmt userList){
        Scanner reader = new Scanner(System.in);
        int dni;
        String strAux;
        Student student = null;
        Course course = null;
        System.out.println("Enter the DNI of the student to be removed");
        dni = reader.nextInt();//DNI por teclado
        student = searchStudentByDNI(userList, dni);//Busca Estudiante por DNI
        if(student == null){
            System.out.println("The dni does not correspond to a student");
        }
        else {//Si existe
            System.out.println("Enter the NAME of the Course");
            strAux = reader.next();//Curso por teclado
            course = searchCourseByName(strAux);
            if(course != null) {
                if (course.getStudentSet().contains(student)) {//Si el alumno esta inscripto en el curso
                    student.getCourseList().removeFromList(course);//Elimina curso de lista de cursos del estudiante
                    course.getStudentSet().remove(student);//Elimina estudiante de lista de estudiantes del curso
                    if((course.getCalificationMap().get(student.getDni())) != null){//Si el alumno realizó el curso
                        course.getCalificationMap().remove(student.getDni());//Elimina la calificacion
                    }
                }
                else {
                    System.out.println("The student is not in the course");
                }
            }
            else {
                throw new NullPointerException("Course not exist");
            }
        }
    }

    //Busca curso por nombre reciibdo por parametro y lo retorna.
    //En su defecto retorna null
    public Course searchCourseByName(String courseName){
        Course aux = null;
        Course actual = null;
        boolean flag = false;
        for (int i = 0; i < getCourseList().size() && !flag; i++){//Recorre lista
            actual = (Course)getCourseList().get(i);
            if (actual.getName().equals(courseName)){//Si coincide el nombre del curso con el recibido
                flag = true;
                aux = actual;//almaceno curso
            }
       }
        return aux;
    }

}
