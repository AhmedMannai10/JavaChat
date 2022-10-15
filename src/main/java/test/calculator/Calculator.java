package test.calculator;

import java.io.Serializable;

public class Calculator implements Serializable {

    double v1 ,v2;
    String operator;

    double result;

    public Calculator(){}

    public Calculator(double v1, String operator, double v2){
        this.v1 = v1;
        this.v2 = v2;
        this.operator = operator;
    }

    public double getResult(){
        switch (operator){
            case "+":
                this.result = v1 + v2;
                return result;

            case "*":
                result = v1 * v2;
                return result;
            case "/":
                 result = v2 == 0? 0 : v1 / v2;
                 return result;
            case "-" :
                 result = v1 - v2;
                 return result;
            default:
                return result = 0;

        }
    };

    public double getV1() {
        return v1;
    }

    public void setV1(double v1) {
        this.v1 = v1;
    }

    public double getV2() {
        return v2;
    }

    public void setV2(double v2) {
        this.v2 = v2;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return "Calculator{" +
                "v1=" + v1 +
                ", v2=" + v2 +
                ", operator='" + operator + '\'' +
                '}';
    }

}
