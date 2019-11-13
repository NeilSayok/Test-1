package neilsayok.github.myutils;

public class MathUtils {


    public static int Plus(int a, int b){
        return a+b;
    }

    public static int Minus(int a, int b){
        return a-b;
    }

    public static double Multiply(int a, int b){
        return a*b;
    }

    public static int Div(int a, int b){
        if (b == 0){
            throw new IllegalArgumentException("Divition by 0");
        }else
            return a/b;
    }


}
