package com.example.calculatorapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnp, btns, btnm, btnd, btne, btnc;
    EditText editText;
    double num1, num2, solution;
    char operator = ' ';

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize buttons and text field
        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btnp = findViewById(R.id.btnp);
        btns = findViewById(R.id.btns);
        btnm = findViewById(R.id.btnm);
        btnd = findViewById(R.id.btnd);
        btne = findViewById(R.id.btne);
        btnc = findViewById(R.id.btnc);
        editText = findViewById(R.id.e);

        // Set click listeners
        btn0.setOnClickListener(view -> appendDigitToEditText("0"));
        btn1.setOnClickListener(view -> appendDigitToEditText("1"));
        btn2.setOnClickListener(view -> appendDigitToEditText("2"));
        btn3.setOnClickListener(view -> appendDigitToEditText("3"));
        btn4.setOnClickListener(view -> appendDigitToEditText("4"));
        btn5.setOnClickListener(view -> appendDigitToEditText("5"));
        btn6.setOnClickListener(view -> appendDigitToEditText("6"));
        btn7.setOnClickListener(view -> appendDigitToEditText("7"));
        btn8.setOnClickListener(view -> appendDigitToEditText("8"));
        btn9.setOnClickListener(view -> appendDigitToEditText("9"));

        btnp.setOnClickListener(view -> setOperator('+'));
        btns.setOnClickListener(view -> setOperator('-'));
        btnm.setOnClickListener(view -> setOperator('*'));
        btnd.setOnClickListener(view -> setOperator('/'));
        btne.setOnClickListener(view -> calculateAndDisplayResult());
        btnc.setOnClickListener(view -> clearCalculatorState());
    }

    private void appendDigitToEditText(String digit) {
        editText.setText(editText.getText().toString() + digit);
    }

    private void setOperator(char operator) {
        this.operator = operator;
        num1 = Double.parseDouble(editText.getText().toString());
        editText.setText("");
    }

    private void calculateAndDisplayResult() {
        if (operator == ' ') {
            Toast.makeText(getApplicationContext(), "Please Select an Operator", Toast.LENGTH_SHORT).show();
            return;
        }

        if (editText.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please Enter a Valid Number", Toast.LENGTH_SHORT).show();
            return;
        }

        num2 = Double.parseDouble(editText.getText().toString());
        solution = 0.0;

        switch (operator) {
            case '+':
                solution = num1 + num2;
                break;
            case '-':
                solution = num1 - num2;
                break;
            case '*':
                solution = num1 * num2;
                break;
            case '/':
                if (num2 == 0) {
                    Toast.makeText(getApplicationContext(), "Division by zero is not allowed", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    solution = num1 / num2;
                }
                break;
        }

        if (Double.isInfinite(solution) || Double.isNaN(solution)) {
            Toast.makeText(getApplicationContext(), "Result is too large or invalid", Toast.LENGTH_SHORT).show();
            return;
        }

        if (solution == (long) solution) {
            editText.setText(String.format("%.0f", solution));
        } else {
            editText.setText(String.format("%.4f", solution));
        }
    }

    private void clearCalculatorState() {
        editText.setText("");
        num1 = 0.0;
        num2 = 0.0;
        operator = ' ';
    }
}