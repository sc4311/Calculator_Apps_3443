package com.example.calculator;

import java.util.Stack;

/**
 * Calculator is a class that performs basic arithmetic operations.
 * It maintains a stack of numbers and performs operations on them.
 */
public class Calculator {
    /**
     * The first operand in an operation.
     */
    double firstNum = 0;

    /**
     * The operator in the current operation.
     */
    String operator = "";

    /**
     * The second operand in an operation.
     */
    double secondNum = 0;

    /**
     * The result of the current operation.
     */
    double result = 0;

    /**
     * A stack of numbers for performing operations.
     */
    Stack<Double> numStack = new Stack<>();

    /**
     * Resets the calculator to its initial state.
     */
    public void resetCalculator() {
        firstNum = 0;
        secondNum = 0;
        operator = "";
        numStack.clear();
    }

    /**
     * Performs an operation on two numbers.
     *
     * @param firstNum the first operand
     * @param secondNum the second operand
     * @param operator the operator to apply
     * @return the result of the operation
     * @throws ArithmeticException if an attempt is made to divide by zero
     */
    public double performOperation(double firstNum, double secondNum, String operator) {
        switch (operator){
            case "/" :
                if (secondNum == 0) {
                    throw new ArithmeticException("Cannot divide by zero");
                }
                result = firstNum / secondNum;
                break;
            case "*" :
                result = firstNum * secondNum;
                break;
            case "+" :
                result = firstNum + secondNum;
                break;
            case "-" :
                result = firstNum - secondNum;
                break;
            default:
                result = 0;
        }
        return result;
    }
}