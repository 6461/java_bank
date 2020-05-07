package com.mma.bank;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String USER_EXTRA = "bank.USER";
    public static final int USER_REQUEST = 1;

    ListHandler<User> users = null;
    ListHandler<Account> accounts = null;
    ListHandler<Card> cards = null;
//    ListHandler<Transaction> transactions = null;

    Type userType = new TypeToken<ArrayList<User>>() {}.getType();
    Type accountType = new TypeToken<ArrayList<Account>>() {}.getType();
    Type cardType = new TypeToken<ArrayList<Card>>() {}.getType();
//    Type transactionType = new TypeToken<ArrayList<Transaction>>() {}.getType();

    Spinner userComponent = null;
    Spinner accountComponent = null;
    Spinner cardComponent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FileHandler.setContext(this);
        users = new ListHandler<>(userType, "user_list.json");
        accounts = new ListHandler<>(accountType, "account_list.json");
        cards = new ListHandler<>(cardType, "card_list.json");
//        transactions = new ListHandler<>(transactionType, "transaction_list.json");

//        users.add(new User("Testi Henkilö", new Date(), "Helsingintie 1", "0400123123"));
//        accounts.add(new Account(null, "FI123", "SAVINGS", 10000, 1000, 1000, true, true));
//        cards.add(new Card(null, "NUM123", "DEBIT", new Date(), true, 1000));

        userComponent = findViewById(R.id.spinnerUser);
        accountComponent = findViewById(R.id.spinnerAccount);
        cardComponent = findViewById(R.id.spinnerCard);

        ArrayAdapter<User> userAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, users.getList());
        userAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userComponent.setAdapter(userAdapter);

        ArrayAdapter<Account> accountAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, accounts.getList());
        accountAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountComponent.setAdapter(accountAdapter);

        ArrayAdapter<Card> cardAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cards.getList());
        cardAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cardComponent.setAdapter(cardAdapter);
    }

    public void userAddAction(View view) {}

    public void userEditAction(View view) {
        User user = (User) userComponent.getSelectedItem();

        if (user != null) {
            Intent intent = new Intent(this, UserActivity.class);
            intent.putExtra(USER_EXTRA, user);
            startActivityForResult(intent, USER_REQUEST);
        }
    }

    public void accountAddAction(View view) {}

    public void accountEditAction(View view) {}

    public void accountHistoryAction(View view) {}

    public void depositAction(View view) {}

    public void transferAction(View view) {}

    public void withdrawAction(View view) {}

    public void cardAddAction(View view) {}

    public void cardEditAction(View view) {}

    public void cardPayAction(View view) {}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == USER_REQUEST && resultCode == RESULT_OK) {
            User user = (User) data.getSerializableExtra(USER_EXTRA);

            //TODO process user data
            System.out.println(user.toString());
        }
    }
}
