import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListMgmt <T>  implements Serializable {
    //ATTRIBUTES
    private List<T> lista;

    //CONSTRUCTORS
    ListMgmt(){
        lista = new ArrayList<T>();
    }

    //GETTERS && SETTERS

    //METHODS
    public void addToList(T t){
        lista.add(t);
    }

    public void removeFromList(T t){
        lista.remove(t);
    }

    public int size(){
        return lista.size();
    }

    public Object get(int index){
        return lista.get(index);
    }

    public void showList(){
            System.out.println(toString());
    }

    public boolean isEmpty(){
        return lista.isEmpty();
    }

    @Override
    public String toString() {
        return "\tLista: " + lista;
    }
}

