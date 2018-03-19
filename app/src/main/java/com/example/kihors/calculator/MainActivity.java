package com.example.kihors.calculator;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editText;
    private Button numberOne, numberTwo, numberThree, numberFour, numberFive, numberSix, numberSeven, numberEight, numberNine, numberZero, plus, minus, equals, divide, multiply, clear, dot;
    private Character singOperator;
    private float numberBefore = Float.NaN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        numberOne = (Button) findViewById(R.id.buttonOne);
        numberTwo = (Button) findViewById(R.id.buttonTwo);
        numberThree = (Button) findViewById(R.id.buttonThree);
        numberFour = (Button) findViewById(R.id.buttonFour);
        numberFive = (Button) findViewById(R.id.buttonFive);
        numberSix = (Button) findViewById(R.id.buttonSix);
        numberSeven = (Button) findViewById(R.id.buttonSeven);
        numberEight = (Button) findViewById(R.id.buttonEight);
        numberNine = (Button) findViewById(R.id.buttonNine);
        numberZero = (Button) findViewById(R.id.buttonZero);
        plus = (Button) findViewById(R.id.buttonPlus);
        minus = (Button) findViewById(R.id.buttonMinus);
        equals = (Button) findViewById(R.id.buttonEquals);
        divide = (Button) findViewById(R.id.buttonDiv);
        multiply = (Button) findViewById(R.id.buttonMultiply);
        clear = (Button) findViewById(R.id.buttonClear);
        dot = (Button) findViewById(R.id.buttonDot);

        numberOne.setOnClickListener(this);
        numberTwo.setOnClickListener(this);
        numberThree.setOnClickListener(this);
        numberFour.setOnClickListener(this);
        numberFive.setOnClickListener(this);
        numberSix.setOnClickListener(this);
        numberSeven.setOnClickListener(this);
        numberEight.setOnClickListener(this);
        numberNine.setOnClickListener(this);
        numberZero.setOnClickListener(this);
        plus.setOnClickListener(this);
        minus.setOnClickListener(this);
        equals.setOnClickListener(this);
        divide.setOnClickListener(this);
        multiply.setOnClickListener(this);
        clear.setOnClickListener(this);
        dot.setOnClickListener(this);
    }

    private void setNumberBefore(Character sign) {

        String numbBef = editText.getText().toString();

        if (numbBef.startsWith(".")) {
            numbBef = "0" + numbBef;
        }

        numberBefore = Float.parseFloat(numbBef);
        singOperator = sign;
        editText.setText("0");
    }

    private void getKeyboard(String string) {

        String currentString = editText.getText().toString();

        if (currentString.equals("0")) {
            currentString = "";
        }

        if (currentString.contains(".")) {
            if (string.contains(".")) {
                currentString = "0";
            }
        }
        currentString += string;

        if (!currentString.contains("0.")) {
            if (currentString.startsWith(".0")) {
                currentString = "0." + string;
            }
            editText.setText(currentString);
        }

    }

    private void setResult() {

        if (!Float.isNaN(numberBefore)) {

            String numberAft = editText.getText().toString();

            float numberAfter = Float.parseFloat(numberAft);
            float finalResult = 0;

            if (singOperator == '+') {
                finalResult = numberAfter + numberBefore;
            }
            if (singOperator == '-') {
                finalResult = numberBefore - numberAfter;
            }
            if (singOperator == '/') {
                finalResult = numberBefore / numberAfter;
            }
            if (singOperator == 'x') {
                finalResult = numberAfter * numberBefore;
            }
            if (finalResult == 0 || Float.isInfinite(finalResult) || Float.isNaN(finalResult)) {
                editText.setText("0");
            } else {
                editText.setText(String.valueOf(finalResult));
            }
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.buttonPlus:
                setNumberBefore('+');
                break;
            case R.id.buttonMinus:
                setNumberBefore('-');
                break;
            case R.id.buttonDiv:
                setNumberBefore('/');
                break;
            case R.id.buttonMultiply:
                setNumberBefore('x');
                break;
            case R.id.buttonClear:
                editText.setText("0");
                numberBefore = 0;
                break;
            case R.id.buttonEquals:
                setResult();
                numberBefore = Float.NaN;
                break;
            default:
                String defaultSing = ((Button)view).getText().toString();
                getKeyboard(defaultSing);
                break;
        }
    }
}
