package com.mma.bank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class TransferActivity extends AppCompatActivity {

    private String accountID;

    ListHandler<Account> accounts = null;
    Type accountType = new TypeToken<ArrayList<Account>>() {}.getType();

    TextView textNumber = null;
    EditText editAmount = null;
    Spinner spinnerAccount = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        textNumber = findViewById(R.id.textNumber);
        editAmount = findViewById(R.id.editAmount);
        spinnerAccount = findViewById(R.id.spinnerAccount);

        Intent intent = getIntent();
        accountID = intent.getStringExtra(Account.ID);

        if (accountID != null) {
            textNumber.setText(intent.getStringExtra(Account.NUMBER));

            accounts = new ListHandler<>(accountType, "account_list.json");
            ArrayList<Account> data = new ArrayList<>();

            // Show only accounts other that the selected one.
            for (Account account : accounts.getList()) {
                if (!accountID.equals(account.getID())) {
                    data.add(account);
                }
            }

            ArrayAdapter<Account> accountAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data);
            accountAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerAccount.setAdapter(accountAdapter);
        }
    }

    /**
     * Transfer button action.
     * @param view View from activity.
     */
    public void transferAction(View view) {
        Account account = (Account) spinnerAccount.getSelectedItem();

        if (account != null) {
            Intent intent = new Intent();
            intent.putExtra(Account.ID + "1", accountID);
            intent.putExtra(Account.ID + "2", account.getID());
            intent.putExtra(Transaction.AMOUNT, Converter.moneyToLong(editAmount.getText().toString().trim()));
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
