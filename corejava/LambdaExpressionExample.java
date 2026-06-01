import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LambdaExpressionExample {

    public static void main(String[] args) {

        List<String> names = new ArrayList<>();

        names.add("Mounika");
        names.add("Ravi");
        names.add("Anil");
        names.add("Sai");

        Collections.sort(names, (a, b) -> a.compareTo(b));

        System.out.println("Sorted Names:");

        for (String name : names) {
            System.out.println(name);
        }
    }
}
