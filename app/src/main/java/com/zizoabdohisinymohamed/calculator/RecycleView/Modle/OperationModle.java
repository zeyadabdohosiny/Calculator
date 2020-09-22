package com.zizoabdohisinymohamed.calculator.RecycleView.Modle;

public class OperationModle {
    String Operation;
    String Number;

    public OperationModle(String operation, String number) {
        Operation = operation;
        Number = number;
    }

    public String getOperation() {
        return Operation;
    }

    public void setOperation(String operation) {
        Operation = operation;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }
}
