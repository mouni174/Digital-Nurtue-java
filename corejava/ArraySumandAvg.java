public class ArraySumAndAvg {

    int arr[] = {5, 4, 3, 2, 1};

    int sum = 0;

    double avg = 0;

    public int calculateSum() {

        for (int i = 0; i < arr.length; i++) {

            sum = sum + arr[i];
        }

        avg = (double) sum / arr.length;

        return sum;
    }

    public double calculateAvg() {

        return avg;
    }

    public static void main(String[] args) {

        ArraySumAndAvg obj = new ArraySumAndAvg();

        System.out.println("Sum = " + obj.calculateSum());

        System.out.println("Average = " + obj.calculateAvg());
    }
}