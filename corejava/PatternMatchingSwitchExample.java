public class PatternMatchingSwitchExample {

    public static void printType(Object obj) {

        switch (obj) {

            case Integer i ->
                System.out.println("Integer value: " + i);

            case String s ->
                System.out.println("String value: " + s);

            case Double d ->
                System.out.println("Double value: " + d);

            case null ->
                System.out.println("Null value");

            default ->
                System.out.println("Unknown type");
        }
    }

    public static void main(String[] args) {

        printType(100);
        printType("Mounika");
        printType(99.99);
        printType(null);
        printType(true);
    }
}