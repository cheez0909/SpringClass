package exam02;

public class CalculatorMain {
    public static void main(String[] args) {
        Calculator cal = (a, b)-> a+b;
        add(cal);
        add((a, b)->a+b);
        add(new Calculator() {
            @Override
            public int add(int a, int b) {
                return a+b;
            }
        });
    }
    public static void add(Calculator cal){
        int i = cal.add(10, 20);
        System.out.println(i);
    }
}
