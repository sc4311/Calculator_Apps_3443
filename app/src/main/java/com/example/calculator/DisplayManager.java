package com.example.calculator;

import android.widget.TextView;

/**
 * DisplayManager is a class responsible for managing the display of the calculator application.
 * It handles the display and base TextViews.
 */
public class DisplayManager {

    /**
     * TextView to display the current input or result.
     */
    TextView display;

    /**
     * TextView to display the current base of the calculator.
     */
    TextView base;

    /**
     * Constructs a new DisplayManager with the specified display and base TextViews.
     *
     * @param display the TextView for displaying the current input or result
     * @param base the TextView for displaying the current base of the calculator
     */
    public DisplayManager(TextView display, TextView base) {
        this.display = display;
        this.base = base;
    }

    /**
     * Updates the text in the display TextView.
     *
     * @param text the new text to be displayed
     */
    public void updateDisplay(String text) {
        display.setText(text);
    }

    /**
     * Updates the text in the base TextView.
     *
     * @param text the new base to be displayed
     */
    public void updateBase(String text) {
        base.setText(text);
    }



    /**
     * Displays an error message in the display TextView.
     */
    public void showError() {
        display.setText("ERROR");
    }

}