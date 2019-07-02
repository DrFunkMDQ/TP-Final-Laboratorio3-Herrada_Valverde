import java.util.InputMismatchException;
import java.util.Scanner;

public class MultipleChoice extends Question {
    //ATTRIBUTES
    private int ansIndex;
    private String opt1;
    private String opt2;
    private String opt3;

    //CONSTRUCTORS
    public MultipleChoice(String desc, int ansIndex, String opt1, String opt2, String opt3) {
        super(desc);
        setAnsIndex(ansIndex);
        setOpt1(opt1);
        setOpt2(opt2);
        setOpt3(opt3);
    }

    //GETTERS && SETTERS
    public int getAnsIndex() {
        return ansIndex;
    }

    public void setAnsIndex(int ansIndex) {
        this.ansIndex = ansIndex;
    }

    public String getOpt1() {
        return opt1;
    }

    public void setOpt1(String opt1) {
        this.opt1 = opt1;
    }

    public String getOpt2() {
        return opt2;
    }

    public void setOpt2(String opt2) {
        this.opt2 = opt2;
    }

    public String getOpt3() {
        return opt3;
    }

    public void setOpt3(String opt3) {
        this.opt3 = opt3;
    }

    //METHODS

    //Pide que ingrese la opción correcta y compara con respuesta retornando true coinciden los valores
    //o false en su defecto
    public boolean answerQuestion(){
        int ans = 0;
        boolean valid = false;
        Scanner scan = new Scanner(System.in);
        System.out.println(toString());
        System.out.println("Enter 1, 2 or 3 to select the correct answer");
        try {
            ans = scan.nextInt();
            while ((ans != 1) && (ans != 2) && (ans != 3)) {
                System.out.println("Enter a valid option");
                ans = scan.nextInt();
            }
        }
        catch (InputMismatchException e){
                System.out.println("Error con el tipo de dato ingresado.");
                answerQuestion();
            }
        catch (Exception e){
                System.out.println("error");
            }
        if(ans == getAnsIndex())
            valid = true;
        return valid;
    }

    @Override
    public String toString() {
        return super.toString() + "Opción 1 =" + opt1 + "\n" +
                "Opción 2 =" + opt2 + "\n" +
                "Opción 3 =" + opt3 + "\n";

    }
}
