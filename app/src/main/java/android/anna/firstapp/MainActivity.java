package android.anna.firstapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    Logger log = Logger.getLogger("Main");
    Long firstValue;
    Long secondValue;
    String operation;
    TextView editText;
    TextView firstValueText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (TextView) findViewById(R.id.currentValue);
        firstValueText = (TextView) findViewById(R.id.firstValue);
    }

    public void clickOperation(String operation) {
        if ((!editText.getText().toString().isEmpty()) && (firstValueText.getText().toString().isEmpty())) {
            firstValue = Long.valueOf(editText.getText().toString());
            this.operation = operation;
            firstValueText.setText(editText.getText() + operation);
            editText.setText("");
        }
    }

    public void buttonClick(View view) {
        log.info("Button click.");
        Button button = (Button) view;
        String buttonText = button.getText().toString();
        if (buttonText.equals(getString(R.string.clearSymbol))) {
            firstValue = null;
            secondValue = null;
            operation = null;
            editText.setText("");
            firstValueText.setText("");
        } else if (buttonText.equals(getString(R.string.backspaceSymbol))) {
            if (!editText.getText().toString().isEmpty()) {
                log.info("EditText: " + editText.getText() + ". Backspace it.");
                editText.setText(editText.getText().toString().toCharArray(), 0, editText.getText().toString().length() - 1);
            }
        } else if (buttonText.equals(getString(R.string.addSymbol)))
            clickOperation("+");
        else if (buttonText.equals(getString(R.string.deductSymbol)))
            clickOperation("-");
        else if (buttonText.equals(getString(R.string.multiplySymbol)))
            clickOperation("*");
        else if (buttonText.equals(getString(R.string.divideSymbol)))
            clickOperation("/");
        else if (buttonText.equals(getString(R.string.equalsSymbol))) {
            if ((operation != null) && (!editText.getText().toString().isEmpty())) {
                secondValue = Long.valueOf(editText.getText().toString());
                switch (operation) {
                    case "+":
                        firstValueText.setText(String.valueOf((firstValue + secondValue)));
                        break;
                    case "-":
                        firstValueText.setText(String.valueOf((firstValue - secondValue)));
                        break;
                    case "*":
                        firstValueText.setText(String.valueOf((firstValue * secondValue)));
                        break;
                    case "/":
                        firstValueText.setText(String.valueOf(((double) firstValue / secondValue)));
                        break;
                    default:
                        break;
                }
                firstValue = null;
                secondValue = null;
                operation = null;
                editText.setText("");
            }
        } else {
            if (editText.getText().length() <= 8)
                editText.setText(editText.getText().toString() + button.getText().toString());
        }

    }
}
