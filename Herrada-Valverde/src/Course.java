import java.io.Serializable;
import java.util.*;

public class Course implements Serializable {
    //ATTRIBUTES
    private int id;
    private String name;
    private Instructor instructor = null;
    private Map <Integer, Double>calificationMap;
    private Set<Student> studentSet;
    private ListMgmt<Question> questionList;

    //CONSTRUCTOR
    public Course(String name, int lastID) {
        setId(lastID);
        setCalificationMap();
        setStudentSet();
        setName(name);
        setQuestionList();
    }

    public Course(String name, Instructor instructor, int lastID) {
        setId(lastID);
        setCalificationMap();
        setStudentSet();
        setName(name);
        setInstructor(instructor);
        setQuestionList();
    }

    //GETTERS && SETTERS


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Student> getStudentSet() {
        return studentSet;
    }

    private void setStudentSet() {
        this.studentSet = new HashSet<Student>();
    }

    public Map getCalificationMap() {
        return calificationMap;
    }

    private void setCalificationMap() {
        this.calificationMap = new HashMap<Integer, Double>();
    }

    public ListMgmt<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList() {
        this.questionList = new ListMgmt<Question>();
    }


    //METHODS

    //SE DEBE REALIZAR EL METODO Double calculateGrades(); --> Chequea la cantidad de preguntas correctas e incorrectas y retrna una nota

    public double calculateGrade(int correct){
        float grade = -1;
        try{
            grade = (float)correct/getQuestionList().size()*10;
        }
        catch (ArithmeticException e){
            System.out.println("La lista esta vacia");
        }
        return grade;
    }

    public void addGrade(Student student, double grade){
        getCalificationMap().put(student.getDni(),grade);
    }

    //Retorna true si el curso tiene intructor dado de alta), false si no lo posee.
    public boolean isReady(){
        return (this.getInstructor() != null);
    }

    //Retorna true si la lista de preguntas está vacía, sino false.
    public boolean isEmptyQuestionList(){
        return getQuestionList().isEmpty();
    }

    public String getStrSet(){
        String str1 = null;
        Set <Student>set = getStudentSet();
        for(Student std:set){
            //System.out.println(std.toString());
            str1 = std.toString();
        }
        return str1;
    }

    @Override
    public String toString() {
        String msg = "ID: %s\n\nNombre: %s\nInstructor: \n\tID: %d\n\tApellido y Nombre: %s\nAlumnos: ";
        return  String.format(msg,getId(),getName(),getInstructor().getId(),getInstructor().getSurName()+" "+getInstructor().getName()) + getStudentSet();
    }


}
