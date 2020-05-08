package com.mma.bank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class DepositActivity extends AppCompatActivity {

    private String accountID;

    TextView textNumber = null;
    EditText editAmount = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);

        textNumber = findViewById(R.id.textNumber);
        editAmount = findViewById(R.id.editAmount);

        Intent intent = getIntent();
        accountID = intent.getStringExtra(Account.ID);

        if (accountID != null) {
            textNumber.setText(intent.getStringExtra(Account.NUMBER));
        }
    }

    public void depositAction(View view) {
        Intent intent = new Intent();
        intent.putExtra(Account.ID, accountID);
        intent.putExtra(Transaction.AMOUNT, Converter.moneyToLong(editAmount.getText().toString().trim()));
        setResult(RESULT_OK, intent);
        finish();
    }
}
