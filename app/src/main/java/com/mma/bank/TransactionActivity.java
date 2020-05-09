package com.mma.bank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class TransactionActivity extends AppCompatActivity {

    private String accountID;

    ListHandler<Transaction> transactions = null;
    Type transactionType = new TypeToken<ArrayList<Transaction>>() {}.getType();

    ListView listView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        Intent intent = getIntent();
        accountID = intent.getStringExtra(Account.ID);

        if (accountID != null) {
            transactions = new ListHandler<>(transactionType, "transaction_list.json");
            ArrayList<Transaction> data = new ArrayList<>();

            // Show only transactions of the current account.
            for (Transaction transaction : transactions.getList()) {
                if (accountID.equals(transaction.getAccountID())) {
                    data.add(transaction);
                }
            }

            listView = findViewById(R.id.listTransaction);
            ArrayAdapter<Transaction> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
            listView.setAdapter(adapter);
        }
    }
}
