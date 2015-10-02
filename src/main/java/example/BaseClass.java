package example;

/**
 * Created by mmadhusoodan on 7/23/15.
 */
public class BaseClass {

    BaseClass() {
        System.out.println("In BaseClass");

        System.out.println(this.getClass().getSimpleName());
    }

    public static void main(String[] args) {

    }
}
