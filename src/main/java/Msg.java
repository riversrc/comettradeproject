import java.util.*;

public class Msg {
    long time;
    String message;
    String owner;


    public Msg(String m, String o){
        message = m;
        owner = o;
        time = System.nanoTime();
    }
}
