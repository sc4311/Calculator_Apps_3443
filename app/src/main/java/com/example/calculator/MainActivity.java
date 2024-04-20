package com.example.calculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * MainActivity is the primary UI class for the calculator application.
 * It extends AppCompatActivity, which is a base class for activities that use the support library action bar features.
 *
 * It initializes the display, state, and stack TextViews, as well as the base TextView.
 * It also initializes the buttons for the numbers 0-9, the operators, and the special function buttons.
 *
 * It creates instances of the Calculator, DisplayManager, and ButtonManager classes, and sets the click listeners for the buttons.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * TextView to display the current state of the calculator.
     */
    TextView state;
    /**
     * TextView to display the current stack of the calculator.
     */
    TextView stack;
    /**
     * TextView to display the current input or result.
     */
    TextView display;



    /**
     * Called when the activity is starting.
     * This is where most initialization should go: calling setContentView(int) to inflate the activity's UI,
     * using findViewById(int) to programmatically interact with widgets in the UI, calling managedQuery(android.net.Uri, String[], String, String[], String) to retrieve cursors for data being displayed, etc.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle). Note: Otherwise it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);
        state = findViewById(R.id.state);
        stack = findViewById(R.id.stack);
        TextView base = findViewById(R.id.base);
        display.setText("0");
        state.setText("");
        stack.setText("");
        base.setText("Base: 10");


        Button numplusminus = findViewById(R.id.plusminus);
        Button numC = findViewById(R.id.clr);
        Button numequal = findViewById(R.id.equ);
        Button numBin = findViewById(R.id.bin);
        Button numDec = findViewById(R.id.dec);
        ArrayList<Button> numbs = new ArrayList<>();
        numbs.add(findViewById(R.id.zero));
        numbs.add(findViewById(R.id.one));
        numbs.add(findViewById(R.id.two));
        numbs.add(findViewById(R.id.three));
        numbs.add(findViewById(R.id.four));
        numbs.add(findViewById(R.id.five));
        numbs.add(findViewById(R.id.six));
        numbs.add(findViewById(R.id.seven));
        numbs.add(findViewById(R.id.eight));
        numbs.add(findViewById(R.id.nine));

        ArrayList<Button> operators = new ArrayList<>();
        operators.add(findViewById(R.id.add));
        operators.add(findViewById(R.id.sub));
        operators.add(findViewById(R.id.mul));
        operators.add(findViewById(R.id.div));
        operators.add(findViewById(R.id.equ));
        operators.add(findViewById(R.id.clr));
        operators.add(findViewById(R.id.bin));
        operators.add(findViewById(R.id.dec));
        operators.add(findViewById(R.id.plusminus));

        Calculator calculator = new Calculator();
        DisplayManager displayManager = new DisplayManager(display, base);
        ButtonManager buttonManager = new ButtonManager( state,  stack,  display,  calculator,  displayManager, numbs,  operators,  numplusminus,  numC,  numequal,  numBin,  numDec);

        buttonManager.setNumClickListener();
        buttonManager.setOperatorClickListener();
        buttonManager.setPlusMinusClickListener();
        buttonManager.setClearClickListener();
        buttonManager.setEqualClickListener();
        buttonManager.setBinaryClickListener();
        buttonManager.setDecimalClickListener();
    }
}