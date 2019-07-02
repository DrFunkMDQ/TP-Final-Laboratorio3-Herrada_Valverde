import java.io.Serializable;
public abstract class Question implements Serializable, IAnswer {
    //ATTRIBUTES
    private int id;
    private String desc;

    private static int count = 0;

    //CONSTRUCTORS
    public Question(String desc) {
        this.setId(incrementCount());
        this.setDesc(desc);
    }

    //GETTERS && SETTERS
    public String getDesc() {
        return desc;
    }

    private void setDesc(String desc) {
        this.desc = desc;
    }

    private static int incrementCount() {
        count++;
        return count;
    }

    public static int getCount() {
        return count;
    }


    //METHODS
    public abstract boolean answerQuestion();

    @Override
    public String toString() {
        String msg = "\tQuestion " + getDesc();
        return msg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
