import java.util.Scanner;

public class ByValue extends Question{
    //ATTRIBUTES
    private String answer;

    //CONSTRUCTORS
    public ByValue(String desc, String answer) {
        super(desc);
        setAnswer(answer);
    }

    //GETTERS && SETTERS

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    //METHODS

    //Pide que ingrese la opción respeusta por teclado y compara con respuesta retornando true coinciden los valores
    //o false en su defecto
    public boolean answerQuestion(){
        boolean valid = false;
        String ans;
        Scanner scan = new Scanner(System.in);
        System.out.println(getDesc());
        System.out.println("Ingrese la respuesta que considere correcta");
        ans = scan.nextLine();
        if(getAnswer().equals(ans))
            valid = true;
        return valid;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
