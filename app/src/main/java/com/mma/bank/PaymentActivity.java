package com.mma.bank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class PaymentActivity extends AppCompatActivity {

    private String cardID;

    TextView textNumber = null;
    EditText editAmount = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        textNumber = findViewById(R.id.textNumber);
        editAmount = findViewById(R.id.editAmount);

        Intent intent = getIntent();
        cardID = intent.getStringExtra(Card.ID);

        if (cardID != null) {
            textNumber.setText(intent.getStringExtra(Card.NUMBER));
        }
    }

    /**
     * Pay button action.
     * @param view View from activity.
     */
    public void payAction(View view) {
        Intent intent = new Intent();
        intent.putExtra(Card.ID, cardID);
        intent.putExtra(Transaction.AMOUNT, Converter.moneyToLong(editAmount.getText().toString().trim()));
        setResult(RESULT_OK, intent);
        finish();
    }
}
