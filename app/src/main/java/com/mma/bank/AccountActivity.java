package com.mma.bank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.Switch;

public class AccountActivity extends AppCompatActivity {

    private String accountID;
    private String userID;

    EditText editNumber = null;
    EditText editType = null;
    EditText editBalance = null;
    EditText editWithdraw = null;
    EditText editTransfer = null;
    Switch switchWithdraw = null;
    Switch switchTransfer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        editNumber = findViewById(R.id.editNumber);
        editType = findViewById(R.id.editType);
        editBalance = findViewById(R.id.editBalance);
        editWithdraw = findViewById(R.id.editWithdraw);
        editTransfer = findViewById(R.id.editTransfer);
        switchWithdraw = findViewById(R.id.switchWithdraw);
        switchTransfer = findViewById(R.id.switchTransfer);

        Intent intent = getIntent();
        userID = intent.getStringExtra(User.USER_ID);
        accountID = intent.getStringExtra(Account.ACCOUNT_ID);

        if (accountID != null) {
            editNumber.setText(intent.getStringExtra(Account.ACCOUNT_NUMBER));
            editType.setText(intent.getStringExtra(Account.ACCOUNT_TYPE));
            editBalance.setText(Validator.moneyToString(intent.getLongExtra(Account.ACCOUNT_BALANCE, 0)));
            editWithdraw.setText(Validator.moneyToString(intent.getLongExtra(Account.WITHDRAW_LIMIT, 0)));
            editTransfer.setText(Validator.moneyToString(intent.getLongExtra(Account.TRANSFER_LIMIT, 0)));
            switchWithdraw.setChecked(intent.getBooleanExtra(Account.WITHDRAW_ALLOWED, true));
            switchTransfer.setChecked(intent.getBooleanExtra(Account.TRANSFER_ALLOWED, true));
        }
    }

    public void saveAction(View view) {
        Intent intent = new Intent();
        intent.putExtra(User.USER_ID, userID);
        intent.putExtra(Account.ACCOUNT_ID, accountID);
        intent.putExtra(Account.ACCOUNT_NUMBER, editNumber.getText().toString().trim());
        intent.putExtra(Account.ACCOUNT_TYPE, editType.getText().toString().trim());
        intent.putExtra(Account.ACCOUNT_BALANCE, Validator.moneyToLong(editBalance.getText().toString().trim()));
        intent.putExtra(Account.WITHDRAW_LIMIT, Validator.moneyToLong(editWithdraw.getText().toString().trim()));
        intent.putExtra(Account.TRANSFER_LIMIT, Validator.moneyToLong(editTransfer.getText().toString().trim()));
        intent.putExtra(Account.WITHDRAW_ALLOWED, switchWithdraw.isChecked());
        intent.putExtra(Account.TRANSFER_ALLOWED, switchTransfer.isChecked());
        setResult(RESULT_OK, intent);
        finish();
    }
}
