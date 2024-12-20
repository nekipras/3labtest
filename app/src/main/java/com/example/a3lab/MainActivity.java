package com.example.a3lab;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText txtResult;
    private double value1 = Double.NaN;
    private double value2;
    private char currentAction;
    private double memoryValue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtResult = findViewById(R.id.txtResult);
        txtResult.setText(""); //pradeda tusciu laukeliu

        //mygtuku prijungimas pagal id
        Button btnDel = findViewById(R.id.btnDel);
        Button btnClear = findViewById(R.id.btnClear);
        Button btnCE = findViewById(R.id.btnCE);
        Button btnEquals = findViewById(R.id.btnEquals);
        Button btnPlus = findViewById(R.id.btnPlus);
        Button btnMinus = findViewById(R.id.btnMinus);
        Button btnMultiply = findViewById(R.id.btnMultiply);
        Button btnDivide = findViewById(R.id.btnDivide);
        Button btnReciprocal = findViewById(R.id.btnReciprocal);
        Button btnSquare = findViewById(R.id.btnSquare);
        Button btnSquareRoot = findViewById(R.id.btnSquareRoot);
        Button btnSign = findViewById(R.id.btnSign);
        Button btn0 = findViewById(R.id.btn0);
        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);
        Button btn4 = findViewById(R.id.btn4);
        Button btn5 = findViewById(R.id.btn5);
        Button btn6 = findViewById(R.id.btn6);
        Button btn7 = findViewById(R.id.btn7);
        Button btn8 = findViewById(R.id.btn8);
        Button btn9 = findViewById(R.id.btn9);
        Button btnDecimal = findViewById(R.id.btnDecimal);
        Button btnPercentage = findViewById(R.id.btnPercentage);

        //atminties mygtukai
        Button btnMC = findViewById(R.id.btnMC);
        Button btnMR = findViewById(R.id.btnMR);
        Button btnMS = findViewById(R.id.btnMS);
        Button btnMPlus = findViewById(R.id.btnMPlus);
        Button btnMMinus = findViewById(R.id.btnMMinus);

        //del funkcija
        btnDel.setOnClickListener(v -> {
            String currentText = txtResult.getText().toString();
            if (!currentText.isEmpty()) {
                txtResult.setText(currentText.substring(0, currentText.length() - 1));
            }
        });

        //c funkcija
        btnClear.setOnClickListener(v -> {
            txtResult.setText("");
            value1 = Double.NaN;
            value2 = Double.NaN;
        });

        //ce funkicija
        btnCE.setOnClickListener(v -> txtResult.setText(""));

        //skaiciu mygtukai
        btn0.setOnClickListener(v -> txtResult.append("0"));
        btn1.setOnClickListener(v -> txtResult.append("1"));
        btn2.setOnClickListener(v -> txtResult.append("2"));
        btn3.setOnClickListener(v -> txtResult.append("3"));
        btn4.setOnClickListener(v -> txtResult.append("4"));
        btn5.setOnClickListener(v -> txtResult.append("5"));
        btn6.setOnClickListener(v -> txtResult.append("6"));
        btn7.setOnClickListener(v -> txtResult.append("7"));
        btn8.setOnClickListener(v -> txtResult.append("8"));
        btn9.setOnClickListener(v -> txtResult.append("9"));
        btnDecimal.setOnClickListener(v -> {
            if (!txtResult.getText().toString().contains(".")) {
                txtResult.append(".");
            }
        });

        //operacijos mygtukai
        btnPlus.setOnClickListener(v -> {
            compute();
            currentAction = '+';
            txtResult.setText(null);
        });

        btnMinus.setOnClickListener(v -> {
            compute();
            currentAction = '-';
            txtResult.setText(null);
        });

        btnMultiply.setOnClickListener(v -> {
            compute();
            currentAction = '*';
            txtResult.setText(null);
        });

        btnDivide.setOnClickListener(v -> {
            compute();
            currentAction = '/';
            txtResult.setText(null);
        });

        btnPercentage.setOnClickListener(v -> {
            if (!txtResult.getText().toString().isEmpty()) {
                double value = Double.parseDouble(txtResult.getText().toString());
                txtResult.setText(String.valueOf(value / 100));
            }
        });

        //= mygtukas
        btnEquals.setOnClickListener(v -> {
            compute();
            currentAction = ' ';
            if (!Double.isNaN(value1)) {
                txtResult.setText(String.valueOf(value1));
            }
            value1 = Double.NaN;
        });

        //1/x funkcija
        btnReciprocal.setOnClickListener(v -> {
            if (!txtResult.getText().toString().isEmpty()) {
                double value = Double.parseDouble(txtResult.getText().toString());
                if (value != 0) {
                    txtResult.setText(String.valueOf(1 / value));
                } else {
                    txtResult.setText("Error"); // Dalyba iš nulio
                }
            }
        });

        //x2 funkcija
        btnSquare.setOnClickListener(v -> {
            if (!txtResult.getText().toString().isEmpty()) {
                double value = Double.parseDouble(txtResult.getText().toString());
                txtResult.setText(String.valueOf(value * value));
            }
        });

        //saknies funkcija
        btnSquareRoot.setOnClickListener(v -> {
            if (!txtResult.getText().toString().isEmpty()) {
                double value = Double.parseDouble(txtResult.getText().toString());
                if (value >= 0) {
                    txtResult.setText(String.valueOf(Math.sqrt(value)));
                } else {
                    txtResult.setText("Error");
                }
            }
        });

        //+/- funkcija
        btnSign.setOnClickListener(v -> {
            if (!txtResult.getText().toString().isEmpty()) {
                double value = Double.parseDouble(txtResult.getText().toString());
                value = value * -1;
                txtResult.setText(String.valueOf(value));
            }
        });

        //atminties mygtuku funkcijos
        btnMC.setOnClickListener(v -> memoryValue = 0); // MC (Memory Clear)
        btnMR.setOnClickListener(v -> txtResult.setText(String.valueOf(memoryValue))); // MR (Memory Recall)
        btnMS.setOnClickListener(v -> memoryValue = Double.parseDouble(txtResult.getText().toString())); // MS (Memory Store)
        btnMPlus.setOnClickListener(v -> memoryValue += Double.parseDouble(txtResult.getText().toString())); // M+ (Memory Add)
        btnMMinus.setOnClickListener(v -> memoryValue -= Double.parseDouble(txtResult.getText().toString())); // M- (Memory Subtract)
    }

    //skaiciavimo logika
    private void compute() {
        if (!Double.isNaN(value1)) {
            value2 = Double.parseDouble(txtResult.getText().toString());

            switch (currentAction) {
                case '+':
                    value1 = value1 + value2;
                    break;
                case '-':
                    value1 = value1 - value2;
                    break;
                case '*':
                    value1 = value1 * value2;
                    break;
                case '/':
                    if (value2 != 0) {
                        value1 = value1 / value2;
                    } else {
                        txtResult.setText("Error"); // Dalyba iš nulio
                        value1 = Double.NaN;
                        return;
                    }
                    break;
            }
        } else {
            value1 = Double.parseDouble(txtResult.getText().toString());
        }
    }
}
