package com.example.calculator;

/**
 * ControllerState is an enumeration representing the possible states of the calculator controller.
 * It can be in one of three states: NUMBER, OPERATOR, or EQUALS.
 */
public enum ControllerState {
    /**
     * State when a number is being input or displayed.
     */
    NUMBER,

    /**
     * State when an operator is being input or displayed.
     */
    OPERATOR,

    /**
     * State when the equals operator has been input and the result is being displayed.
     */
    EQUALS
}