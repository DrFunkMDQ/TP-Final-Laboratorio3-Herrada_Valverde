import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class FilePersistence <T> implements Serializable {

    /*
     * Metodo que retorna un ArrayList del tipo parametrizado por la definicion, a partir de un archivo.
     *
     * objectArray
     * fileName = Nombre del archivo de donde se obtendra el Listado de Datos.
     * */
    public void WriteIntoFile(ListMgmt<T> objectArray, String fileName) throws Exception {

        if (objectArray != null && fileName != null && !fileName.isEmpty()){

            FileOutputStream fileOutputStream = null;
            ObjectOutputStream objectOutputStream = null;
            int i;

            try
            {
                //crea un fichero para persistir el objeto
                fileOutputStream = new FileOutputStream(fileName.concat(".file"));
                objectOutputStream = new ObjectOutputStream(fileOutputStream);

                //escribe el objeto serializado a un archivo
                for(i = 0; i<objectArray.size(); i++){
                    objectOutputStream.writeObject(objectArray.get(i));
                }
            }
            catch (IOException e){
                throw new IOException("Error al intentar escribir en el archivo.");
            }
            catch (Exception e){
                throw new Exception("Error, hubo un problema al intentar persistir los datos.");
            }
            finally {
                if (objectOutputStream != null){
                    objectOutputStream.flush();
                    objectOutputStream.close();
                }
            }
        }
        else {
            throw new IOException("Error, no es posible persistir una coleccion de objetos sin Inicializar.");
        }
    }

    /*
    * Metodo que retorna un ArrayList del tipo parametrizado por la definicion, a partir de un archivo.
    * Requiere Casteo ya que es de tipo generico.
    *
    * fileName = Nombre del archivo de donde se obtendra el Listado de Datos.
    * */
    public ListMgmt<T> ReadFromFile(String fileName) throws Exception {
        ListMgmt<T> objectArray = null;

        if (fileName != null && !fileName.isEmpty()){

            FileInputStream fileInputStream = null;
            ObjectInputStream objectInputStream = null;

            try
            {
                File fileCheck = new File(fileName.concat(".file"));
                if (!fileCheck.exists() || !fileCheck.isFile()){
                    throw new Exception("ERROR, el archivo no existe o no contiene el formato adecuado para realizar la lectura.");
                }

                //Abre el archivo a modo StreamInput
                fileInputStream = new FileInputStream(fileName.concat(".file"));
                objectInputStream = new ObjectInputStream(fileInputStream);

                objectArray = new ListMgmt<>();
                //Itera sobre el archivo para armar los objetos, hasta que se cae del archivo
                // y asi confirmar de que se completo la iteracion.
                T obj = null;
                do{
                    obj =  (T)objectInputStream.readObject();

                    objectArray.addToList(obj);

                }while (obj != null);

                //objectArray = (ListMgmt<T>)objectInputStream.readObject();

            }
            catch (EOFException e){
                //Dejo continuar, porque quiere decir que llegue al final del archivo.
            }
            catch (IOException e){
                throw new IOException("Error al intentar leer en el archivo, verifique que el nombre del archivo coincida con el de escritura,.");
            }
            catch (Exception e){
                throw new Exception("Error, hubo un problema al intentar leer/convertir los datos. No se persistieron correctamente o el tipo de dato no secorrecponde, vuelva a intentarlo.");
            }
            finally {
                if (objectInputStream != null) objectInputStream.close();
            }
        }
        else {
            throw new IOException("Error, no es posible abrir el archivo con el nombre ingresado.");
        }

        return objectArray;
    }
}
