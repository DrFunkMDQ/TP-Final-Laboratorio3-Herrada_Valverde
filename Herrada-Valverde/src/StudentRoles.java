import java.util.List;

public abstract class  StudentRoles extends User{
    //ATTRIBUTES

    //CONSTRUCTOR

    public StudentRoles(int id, int dni, String name, String surName, String password) {
        super(id, dni, name, surName, password);
    }

    //GETTERS && SETTERS

    //METHODS

    //Calcula el promedio de notas del estudiante y retorna el valor.
    public abstract Double  getAverage() throws NullPointerException;

}
