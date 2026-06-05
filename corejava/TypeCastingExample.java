public class TypeCastingExample {

    public static void main(String[] args) {

        double d = 99.99;

        int a = (int) d;

        System.out.println("Double value: " + d);

        System.out.println("Converted to int: " + a);

        int b = 10;

        double c = (double) b;

        System.out.println("Integer value: " + b);

        System.out.println("Converted to double: " + c);
    }
}