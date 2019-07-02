import java.util.InputMismatchException;
import java.util.Scanner;

public class Admin extends AdminRoles {
    //ATTRIBUTES

    //CONSTRUCTOR

    public Admin(int id, int dni, String name, String surName, String password) {
        super(id, dni, name, surName, password);
    }

    //GETTERS && SETTERS

    //METHODS
    //Pide nombre de curso por teclado, lo crea y lo retorna.
    public Course createCourse(ListMgmt<User> userList, int lastCourseID){
        Course course = null;
        int aux = 0;
        Scanner reader = new Scanner(System.in);
        System.out.println("Type the course name: ");
        String name = reader.nextLine();
        course = registerCourse(new Course(name, lastCourseID), userList);
        return course;
    }

    //Recibe por parámetro un curso ya creado (sin instructor), busca intructor por ID y lo asigna al curso.
    //Retorna el curso dado de alta (con instructor)
    public Course registerCourse(Course course, ListMgmt<User> userList){
        if(!course.isReady()){
            int auxInt = 0;
            int id = 0;
            String auxStr;
            Scanner reader = new Scanner(System.in);
            System.out.println("Enter the instructor ID");
            id = reader.nextInt();
            Instructor instructor = searchInstructorById(userList, id);
            if(instructor != null) {
                course.setInstructor(instructor);
                addCourseToInstructorList(instructor, course);
            }
            else{
                System.out.println("The id does not correspond to an instructor");
            }
        }
        return  course;
    }

    public void addCourseToInstructorList(Instructor instructor, Course course){
        if(course != null) {
            instructor.getCourseList().addToList(course);
        }

    }

    //Recibe lista de cursos y retorna el ID del último o en su defecto 0.
    public int getLastCourseId(ListMgmt courseList){
        int lastId = 0;
        try {
            Course course = (Course) courseList.get(courseList.size() - 1);
            lastId = course.getId();
        }catch (Exception e){
            lastId = 1;
        }
        if (courseList == null)
            lastId = 0;
        return lastId;
    }

    //Recibe lista de users y retorna el ID del último o en su defecto 0.
    public int getLastUserId(ListMgmt userList){
        int lastId = 0;
        try {
            User user = (User) userList.get(userList.size() - 1);
            lastId = user.getId();
        }catch (Exception e){
            System.out.println("Null List");
        }
        return lastId;
    }

    //Recibe por parámetro la lista de usuarios(admin, intructor y estudiantes) y un id.
    //Retorna el instructor que posea dicho ID, en su defecto null;
    public Instructor searchInstructorById(ListMgmt<User> userListMgmt, int id){
        Instructor instructor = null;
        User actual = null;
        boolean flag = false;
        for (int i = 0; i < userListMgmt.size() && !flag; i++){
            actual = (User)userListMgmt.get(i);
            if (actual.getId()== id && actual instanceof Instructor){
                flag = true;
                instructor = (Instructor)actual;
            }
        }
        return  instructor;
    }

    //Recibe ID a asignar.
    //Pide datos de usuario por teclado
    //retorna un User con dichos datos
    public User fillBasicData(int id){
        Scanner scan = new Scanner(System.in);
        int dni;
        User newUser = null;
        String name, surname, password;
        try {
            System.out.println("DNI :\n");
            dni = scan.nextInt();
            System.out.println("Name:\n");
            name = scan.next();
            System.out.println("Surname: \n");
            surname = scan.next();
            System.out.println("Password: \n");
            password = scan.next();
            scan.nextLine();
            newUser = new User(id, dni, name, surname, password);
        }catch (InputMismatchException e) {
            System.out.println("Wrong type");
        }catch (Exception e){
            System.out.println("Error filling basic data");
        }
        return newUser;
    }

    //Recibe id a asignar
    //Retorna estudiante, con datos obtenidos de fillBasicData()
    public Student createNewStudent(int id){
        int dni;
        Student newStudent = null;
        User basicData = null;
        basicData = fillBasicData(id);
        newStudent = new Student(basicData.getId(), basicData.getDni(), basicData.getName(),basicData.getSurName(), basicData.getPassword());
        return newStudent;
    }

    //Idem que createNewStudent() pero retorna un Instructor
    public Instructor createNewInstructor(int id){
        int dni;
        Instructor newInstructor = null;
        User basicData = null;
        basicData = fillBasicData(id);
        newInstructor = new Instructor(basicData.getId(), basicData.getDni(), basicData.getName(),basicData.getSurName(), basicData.getPassword());
        return newInstructor;
    }

    @Override
    public String toString() {
        return "\tAdmin " + super.toString();
    }
}



