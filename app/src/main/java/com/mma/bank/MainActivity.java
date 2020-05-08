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
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    public static final int USER_REQUEST = 1;
    public static final int ACCOUNT_REQUEST = 2;
    public static final int CARD_REQUEST = 3;

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

    public void userAddAction(View view) {
        Intent intent = new Intent(this, UserActivity.class);
        startActivityForResult(intent, USER_REQUEST);
    }

    public void userEditAction(View view) {
        User user = (User) userComponent.getSelectedItem();

        if (user != null) {
            Intent intent = new Intent(this, UserActivity.class);
            intent.putExtra(User.USER_ID, user.getID());
            intent.putExtra(User.USER_NAME, user.getName());
            intent.putExtra(User.USER_DOB, DateHandler.getString(user.getDateOfBirth(), "dd.MM.yyyy"));
            intent.putExtra(User.USER_ADDRESS, user.getAddress());
            intent.putExtra(User.USER_PHONE, user.getPhone());
            startActivityForResult(intent, USER_REQUEST);
        }
    }

    public void accountAddAction(View view) {
        User user = (User) userComponent.getSelectedItem();

        if (user != null) {
            Intent intent = new Intent(this, AccountActivity.class);
            intent.putExtra(User.USER_ID, user.getID());
            startActivityForResult(intent, ACCOUNT_REQUEST);
        }
    }

    public void accountEditAction(View view) {
        User user = (User) userComponent.getSelectedItem();
        Account account = (Account) accountComponent.getSelectedItem();

        if (user != null && account != null) {
            Intent intent = new Intent(this, AccountActivity.class);
            intent.putExtra(User.USER_ID, user.getID());
            intent.putExtra(Account.ACCOUNT_ID, account.getID());
            intent.putExtra(Account.ACCOUNT_NUMBER, account.getNumber());
            intent.putExtra(Account.ACCOUNT_TYPE, account.getType());
            intent.putExtra(Account.ACCOUNT_BALANCE, account.getBalance());
            intent.putExtra(Account.WITHDRAW_LIMIT, account.getWithdrawLimit());
            intent.putExtra(Account.TRANSFER_LIMIT, account.getTransferLimit());
            intent.putExtra(Account.WITHDRAW_ALLOWED, account.isWithdrawAllowed());
            intent.putExtra(Account.TRANSFER_ALLOWED, account.isTransferAllowed());
            startActivityForResult(intent, ACCOUNT_REQUEST);
        }
    }

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

        if (requestCode == USER_REQUEST && resultCode == RESULT_OK && data != null) {
            String id = data.getStringExtra(User.USER_ID);
            String name = data.getStringExtra(User.USER_NAME);
            Date dateOfBirth = DateHandler.parseDate(data.getStringExtra(User.USER_DOB), "dd.MM.yyyy");
            String address = data.getStringExtra(User.USER_ADDRESS);
            String phone = data.getStringExtra(User.USER_PHONE);

            if (id == null) {
                // New entry.
                users.add(new User(name, dateOfBirth, address, phone));
            } else {
                // Edit entry.
                for (User user : users.getList()) {
                    if (id.equals(user.getID())) {
                        user.setName(name);
                        user.setDateOfBirth(dateOfBirth);
                        user.setAddress(address);
                        user.setPhone(phone);
                        break;
                    }
                }
                users.update();
            }
        }

        if (requestCode == ACCOUNT_REQUEST && resultCode == RESULT_OK && data != null) {
            String userID = data.getStringExtra(User.USER_ID);
            String id = data.getStringExtra(Account.ACCOUNT_ID);
            String number = data.getStringExtra(Account.ACCOUNT_NUMBER);
            String type = data.getStringExtra(Account.ACCOUNT_TYPE);
            long balance = data.getLongExtra(Account.ACCOUNT_BALANCE, 0);
            long withdrawLimit = data.getLongExtra(Account.WITHDRAW_LIMIT, 0);
            long transferLimit = data.getLongExtra(Account.TRANSFER_LIMIT, 0);
            boolean withdrawAllowed = data.getBooleanExtra(Account.WITHDRAW_ALLOWED, true);
            boolean transferAllowed = data.getBooleanExtra(Account.TRANSFER_ALLOWED, true);

            if (id == null) {
                // New entry.
                accounts.add(new Account(userID, number, type, balance, withdrawLimit, transferLimit, withdrawAllowed, transferAllowed));
            } else {
                // Edit entry.
                for (Account account : accounts.getList()) {
                    if (id.equals(account.getID())) {
                        account.setNumber(number);
                        account.setType(type);
                        account.setBalance(balance);
                        account.setWithdrawLimit(withdrawLimit);
                        account.setTransferLimit(transferLimit);
                        account.setWithdrawAllowed(withdrawAllowed);
                        account.setTransferAllowed(transferAllowed);
                        break;
                    }
                }
                accounts.update();
            }
        }
    }
}
