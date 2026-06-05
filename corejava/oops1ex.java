public class oops1ex {
    public int add(int a,int b){
        return a+b;
    }
    public double add(double a,double b){
        return a+b;
    }
    public int add(int a,int b,int c){
        return a+b+c;
    }
    public static void main(String[] args) {
        oops1ex op = new oops1ex();
        System.out.println(op.add(10,20));
        System.out.println(op.add(10.5,20.5));
        System.out.println(op.add(10,20,30));

    }
}
