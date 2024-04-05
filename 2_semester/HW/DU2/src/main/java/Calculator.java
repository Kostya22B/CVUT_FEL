public class Calculator {
    double a;
    double b;

    public Calculator(){}

    public Calculator(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double addition(double a, double b) {
        return a + b;
    }
    public double subtraction(double a, double b) {
        return a - b;
    }
    public double multiplication(double a, double b) {
        return a * b;
    }
    public double division(double a, double b){
        double out = 0;
        if (b==0){
            throw new ArithmeticException("Dividing by 0");
        }else {
            out = a / b;
        }
        return out;
    }
}
