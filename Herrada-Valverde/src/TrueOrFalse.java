import java.util.InputMismatchException;
import java.util.Scanner;

public class TrueOrFalse extends Question{
    //ATTRIBUTES
    private boolean answer;

    //CONSTRUCTORS
    public TrueOrFalse(String desc, boolean answer) {
        super(desc);
        setAnswer(answer);
    }

    //GETTERS && SETTERS
    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    //METHODS

    //Pide que ingrese T o F y compara con respuesta retornando true coinciden los valores
    //o false en su defecto
    public boolean answerQuestion(){
        String ans = null;
        Boolean valid = false;
        Scanner scan = new Scanner(System.in);
        System.out.println(toString());
        System.out.println("Enter T / F");
        try{
            ans = scan.nextLine().toUpperCase();
            while(!ans.equals("T") && !ans.equals("F")){
                System.out.println("Enter T / F");
                ans = scan.nextLine().toUpperCase();
            }
        }
        catch (InputMismatchException e){
            System.out.println("Wrong type0");
        }
        catch (Exception e){
            System.out.println("error");
        }
        if(ans.equals("T")){
            if(isAnswer())
                valid = true;
        }
        else
            if(!isAnswer())
                valid=true;
        return valid;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
