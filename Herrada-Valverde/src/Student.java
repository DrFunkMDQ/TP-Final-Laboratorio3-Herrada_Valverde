import java.util.List;

public class Student extends StudentRoles{
    //ATTRIBUTES

    //CONSTRUCTOR

    public Student(int id, int dni, String name, String surName, String password) {
        super(id, dni, name, surName, password);
    }

    //GETTERS && SETTERS


    //METHODS

    //Retorna el promedio de notas del estudiante.
    //Si la lista de cursos es null eleva una excepción de tipo NUllPointer
    public Double getAverage() throws NullPointerException{
        Double average = 0.0;
        Course course;
        int count = 0, i;
        if(getCourseList() != null) { //Valida que no sea null la lista de cursos
            count = 0;
            for (i = 0; i < getCourseList().size(); i++){ //Recorre lista de cursos
                course = (Course)getCourseList().get(i);
                if(course.getCalificationMap().containsKey(getDni())) {
                    average += (Double) course.getCalificationMap().get(this.getDni()); //Toma la calificacion que tenga como key su dni
                    count++;
                }
            }
            if(count != 0)
                average = average / count; //Calcula promedio
            else
                average = 0.0;
        }
        else{
            throw new NullPointerException("Course List is Empty.");
        }
        return average;
    }

    //Permite resolver una Question.
    //Recibe el curso al que pertenecen las preguntas
    public int startCourse(Course cursito) {
        int i, correct = 0;
        boolean ans;
        try{
            for (i = 0; i < cursito.getQuestionList().size(); i++) {//recorre lista de preguntas
                if (cursito.getQuestionList().get(i) instanceof ByValue)//Evelua si es instancia de ByValue
                    ans = ((ByValue) cursito.getQuestionList().get(i)).answerQuestion();
                //Se caste ByValue y se ejecuta el método answeQuestion
                else {
                    if (cursito.getQuestionList().get(i) instanceof TrueOrFalse)//Evelua si es instancia de TrueOrFalse
                        ans = ((TrueOrFalse) cursito.getQuestionList().get(i)).answerQuestion();//
                        // Se caste TrueOrFalse y se ejecuta el método answeQuestion
                    else//Si no es instancia de TrueOrFalse ni de ByValue es de MultipleChoice
                        ans = ((MultipleChoice) cursito.getQuestionList().get(i)).answerQuestion();
                    //Se caste MultipleChoice y se ejecuta el método answeQuestion
                }
                if (ans)
                    correct++;
            }
        }catch (NullPointerException e){
            System.out.println("Lista no inicializada");
        }catch (Exception e){
            System.out.println("Error");
        }
        return correct;
    }

    //Recibe por parámetro un id.
    //Retorna el Course que posea dicho ID, en su defecto null;
    public Course searchCourseById(int id) throws Exception{
        Course aux = null;
        Course actual = null;
        boolean flag = false;
        try{
            for (int i = 0; i < getCourseList().size() && !flag; i++){//Recorre lista de cursos
                actual = (Course)getCourseList().get(i);
                if (actual.getId()== id){//evalua si el curso actual posee el id indicado
                    flag = true;
                    aux = actual;
                }
            }
        }catch (Exception e){
            throw new Exception("Course ID not found");
        }
        return aux;
    }

    //Recibe un id, busca y ejecuta el curso con dicho ID.
    //Calcula la nota y se la agrega al HashMap de calificaciones
    public void executeCourseById(int id) throws Exception{
        Course course = null;
        try {
            course = searchCourseById(id);
            course.addGrade(this,course.calculateGrade(startCourse(course)));
        }catch (Exception e){
            throw new Exception("Course ID not found");
        }
    }

    //Muestra por pantalla los cursos a completar
    public void showCourseToBeCompleted(){
        Course actual = null;
        int cont = 0;
        for (int i = 0; i < getCourseList().size(); i++) {//Recorre lista de cursos
            actual = (Course) getCourseList().get(i);
            if(!actual.getCalificationMap().containsKey(getDni())){
                //Si no se enuentra su DNI en el map de calificaciones no realizó el curso aún
                System.out.println(getCourseList().get(i));
                cont++;
            }
        }
        if(cont == 0){
            System.out.println("You don't have courses to complete");
        }
    }

    @Override
    public String toString() {
        return "\tStudent " + super.toString();
    }
}
