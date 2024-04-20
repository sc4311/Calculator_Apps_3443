package com.example.calculator;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Stack;

/**
 * ButtonManager is a class responsible for managing the buttons of the calculator application.
 * It handles the click events for the number buttons, operator buttons, and special function buttons.
 * It also maintains the current state of the calculator and updates the display and stack views accordingly.
 */
public class ButtonManager {
    /**
     * The DisplayManager instance used to manage the display views.
     */
    private final DisplayManager displayManager;

    /**
     * The Calculator instance used to perform calculations.
     */
    private final Calculator calculator;

    /**
     * The list of number buttons.
     */
    private final ArrayList<Button> nums;

    /**
     * The list of operator buttons.
     */
    private final ArrayList<Button> operators;

    /**
     * The plus/minus button.
     */
    private final Button numplusminus;

    /**
     * The clear button.
     */
    private final Button numC;

    /**
     * The equals button.
     */
    private final Button numequal;

    /**
     * The binary mode button.
     */
    private final Button numBin;

    /**
     * The decimal mode button.
     */
    private final Button numDec;

    /**
     * The TextView for displaying the current state of the calculator.
     */
    TextView state;

    /**
     * The TextView for displaying the current stack of the calculator.
     */
    TextView stack;

    /**
     * The TextView for displaying the current input or result.
     */
    TextView display;

    /**
     * A stack of numbers for performing operations.
     */
    Stack<Double> numStack = new Stack<>();

    /**
     * A Calculator instance for performing operations.
     */
    Calculator calc = new Calculator();

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
     * A flag indicating whether the calculator is in binary mode.
     */
    boolean isBinaryMode = false;

    /**
     * The current state of the calculator.
     */
    private ControllerState currentState = ControllerState.NUMBER;


    /**
     * Constructs a new ButtonManager with the specified views and calculator instances.
     *
     * @param state the TextView for displaying the current state of the calculator
     * @param stack the TextView for displaying the current stack of the calculator
     * @param display the TextView for displaying the current input or result
     * @param calculator the Calculator instance used to perform calculations
     * @param displayManager the DisplayManager instance used to manage the display views
     * @param nums the list of number buttons
     * @param operators the list of operator buttons
     * @param numplusminus the plus/minus button
     * @param numC the clear button
     * @param numequal the equals button
     * @param numBin the binary mode button
     * @param numDec the decimal mode button
     */
    public ButtonManager(TextView state, TextView stack, TextView display, Calculator calculator, DisplayManager displayManager, ArrayList<Button> nums, ArrayList<Button> operators, Button numplusminus, Button numC, Button numequal, Button numBin, Button numDec) {
        this.state = state;
        this.stack = stack;
        this.display = display;
        this.calculator = calculator;
        this.displayManager = displayManager;
        this.nums = nums;
        this.operators = operators;
        this.numplusminus = numplusminus;
        this.numC = numC;
        this.numequal = numequal;
        this.numBin = numBin;
        this.numDec = numDec;
    }


    /**
     * Sets the click listeners for the number buttons.
     * It updates the display with the clicked number.
     * It also updates the current state to NUMBER.
     * It also updates the stack view with the current stack of numbers.
     */
    public void setNumClickListener() {
        for (Button b : nums) {
            b.setOnClickListener(view -> {
                if (display.getText().toString().equals("0")) {
                    display.setText(b.getText());
                } else {
                    display.append(b.getText());
                }
                updateState(ControllerState.NUMBER);
            });
        }
    }


    /**
     * Sets the click listeners for the operator buttons.
     * It performs the operation based on the current operator and operands.
     * It also updates the stack view with the result of the operation.
     * If an arithmetic exception occurs (e.g., division by zero), it displays an error message.
     */
    public void setOperatorClickListener() {
        for (Button b : operators) {
            b.setOnClickListener(view -> {
                if (currentState == ControllerState.OPERATOR) {
                    displayManager.showError();
                    calc.resetCalculator();
                    return;
                }
                firstNum = Double.parseDouble(display.getText().toString());
                numStack.push(firstNum); // push the number into the stack
                operator = b.getText().toString();
                display.setText("0");
                updateState(ControllerState.OPERATOR);
                updateStackView(); // update the stack view
                stack.append(operator + "\n"); // show the clicked operator
            });
        }
    }


    /**
     * Sets the click listener for the plus/minus button.
     * It toggles the sign of the current number.
     * If the current number is invalid, it displays an error message.
     * If the current number is valid, it updates the display with the new number.
     */
    public void setPlusMinusClickListener() {
        numplusminus.setOnClickListener(view -> {
            String displayText = display.getText().toString();
            try {
                double currentNumber = Double.parseDouble(displayText);
                currentNumber *= -1;
                display.setText(String.valueOf(currentNumber));
            } catch (NumberFormatException e) {
                Toast.makeText(view.getContext(), "Invalid number", Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * Sets the click listener for the clear button.
     * It resets the calculator to its initial state and updates the stack view.
     * It also updates the current state to NUMBER.
     * It also updates the stack view with the current stack of numbers.
     * It also updates the stack view with the current stack of numbers.
     */
    public void setClearClickListener() {
        numC.setOnClickListener(view -> {
            display.setText("0");
            firstNum = 0;
            secondNum = 0;
            operator = "";
            numStack.clear(); // clear the stack
            updateStackView(); // update the stack view
        });
    }


    /**
     * Sets the click listener for the equals button.
     * It performs the operation based on the current operator and operands.
     * It also updates the stack view with the result of the operation.
     * If an arithmetic exception occurs (e.g., division by zero), it displays an error message.
     * If the operation is successful, it updates the current state to EQUALS.
     */
    public void setEqualClickListener() {
    numequal.setOnClickListener(view -> {
        try {
            secondNum = Double.parseDouble(display.getText().toString());
            numStack.push(secondNum); // push the number into the stack
            result = calculator.performOperation(firstNum, secondNum, operator);
            display.setText(String.valueOf(result));
            updateState(ControllerState.EQUALS);
            updateStackView(); // update the stack view
        } catch (ArithmeticException e) {
            displayManager.showError();
        }
    });
}


    /**
     * Sets the click listener for the binary mode button.
     */
    public void setBinaryClickListener() {
        numBin.setOnClickListener(view -> {
            isBinaryMode = true;
            String displayText = display.getText().toString();
            try {
                int decimalNumber = result == 0 ? Integer.parseInt(displayText) : (int) result;
                String binaryNumber = Integer.toBinaryString(decimalNumber);
                display.setText(binaryNumber);
                displayManager.updateBase("Base: 2");
            } catch (NumberFormatException e) {
                Toast.makeText(view.getContext(), "Invalid decimal number", Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * Sets the click listener for the decimal mode button.
     */
    public void setDecimalClickListener() {
        numDec.setOnClickListener(view -> {
            isBinaryMode = false;
            String displayText = display.getText().toString();
            if (displayText.matches("[01]+")) {
                int binaryNumber = Integer.parseInt(displayText, 2);
                display.setText(String.valueOf(binaryNumber));
                displayManager.updateBase("Base: 10");
            } else {
                Toast.makeText(view.getContext(), "Invalid binary number", Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * Updates the current state of the calculator and the state view.
     *
     * @param newState the new state of the calculator
     */
    private void updateState(ControllerState newState) {
        currentState = newState;
        state.setText(newState.toString());
    }


    /**
     * Updates the stack view with the current stack of numbers.
     */
    private void updateStackView() {
        StringBuilder stackText = new StringBuilder();
        for (Double num : numStack) {
            stackText.append(num).append("\n");
        }
        stack.setText(stackText.toString());
    }
}