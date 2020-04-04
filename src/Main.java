
import System.*;
public class Main {

    public static void main(String[] args) {
        Controller controller = Controller.getInstance();
        try {
            controller.login("aa", "aa");
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }
}
