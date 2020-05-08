package com.mma.bank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

public class CardActivity extends AppCompatActivity {

    private String cardID;
    private String accountID;

    EditText editNumber = null;
    EditText editType = null;
    EditText editValid = null;
    EditText editPayment = null;
    Switch switchPayment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        editNumber = findViewById(R.id.editNumber);
        editType = findViewById(R.id.editType);
        editValid = findViewById(R.id.editValid);
        editPayment = findViewById(R.id.editPayment);
        switchPayment = findViewById(R.id.switchPayment);

        Intent intent = getIntent();
        accountID = intent.getStringExtra(Account.ID);
        cardID = intent.getStringExtra(Card.ID);

        if (cardID != null) {
            editNumber.setText(intent.getStringExtra(Card.NUMBER));
            editType.setText(intent.getStringExtra(Card.TYPE));
            editValid.setText(intent.getStringExtra(Card.VALID));
            editPayment.setText(Converter.moneyToString(intent.getLongExtra(Card.LIMIT, 0)));
            switchPayment.setChecked(intent.getBooleanExtra(Card.ALLOWED, true));
        }
    }

    public void saveAction(View view) {
        Intent intent = new Intent();
        intent.putExtra(Account.ID, accountID);
        intent.putExtra(Card.ID, cardID);
        intent.putExtra(Card.NUMBER, editNumber.getText().toString().trim());
        intent.putExtra(Card.TYPE, editType.getText().toString().trim());
        intent.putExtra(Card.VALID, editValid.getText().toString().trim());
        intent.putExtra(Card.LIMIT, Converter.moneyToLong(editPayment.getText().toString().trim()));
        intent.putExtra(Card.ALLOWED, switchPayment.isChecked());
        setResult(RESULT_OK, intent);
        finish();
    }
}
