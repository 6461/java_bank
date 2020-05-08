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
    public static final int PAYMENT_REQUEST = 4;
    public static final int DEPOSIT_REQUEST = 5;
    public static final int WITHDRAW_REQUEST = 6;
    public static final int TRANSFER_REQUEST = 7;

    ListHandler<User> users = null;
    ListHandler<Account> accounts = null;
    ListHandler<Card> cards = null;
    ListHandler<Transaction> transactions = null;

    Type userType = new TypeToken<ArrayList<User>>() {}.getType();
    Type accountType = new TypeToken<ArrayList<Account>>() {}.getType();
    Type cardType = new TypeToken<ArrayList<Card>>() {}.getType();
    Type transactionType = new TypeToken<ArrayList<Transaction>>() {}.getType();

    Spinner userComponent = null;
    Spinner accountComponent = null;
    Spinner cardComponent = null;

    ArrayAdapter<User> userAdapter = null;
    ArrayAdapter<Account> accountAdapter = null;
    ArrayAdapter<Card> cardAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FileHandler.setContext(this);
        users = new ListHandler<>(userType, "user_list.json");
        accounts = new ListHandler<>(accountType, "account_list.json");
        cards = new ListHandler<>(cardType, "card_list.json");
        transactions = new ListHandler<>(transactionType, "transaction_list.json");

        userComponent = findViewById(R.id.spinnerUser);
        accountComponent = findViewById(R.id.spinnerAccount);
        cardComponent = findViewById(R.id.spinnerCard);

        userAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, users.getList());
        userAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userComponent.setAdapter(userAdapter);

        accountAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, accounts.getList());
        accountAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountComponent.setAdapter(accountAdapter);

        cardAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cards.getList());
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
            intent.putExtra(User.ID, user.getID());
            intent.putExtra(User.NAME, user.getName());
            intent.putExtra(User.DOB, Converter.dateToString(user.getDateOfBirth(), "dd.MM.yyyy"));
            intent.putExtra(User.ADDRESS, user.getAddress());
            intent.putExtra(User.PHONE, user.getPhone());
            startActivityForResult(intent, USER_REQUEST);
        }
    }

    public void accountAddAction(View view) {
        User user = (User) userComponent.getSelectedItem();

        if (user != null) {
            Intent intent = new Intent(this, AccountActivity.class);
            intent.putExtra(User.ID, user.getID());
            startActivityForResult(intent, ACCOUNT_REQUEST);
        }
    }

    public void accountEditAction(View view) {
        Account account = (Account) accountComponent.getSelectedItem();

        if (account != null) {
            Intent intent = new Intent(this, AccountActivity.class);
            intent.putExtra(User.ID, account.getUserID());
            intent.putExtra(Account.ID, account.getID());
            intent.putExtra(Account.NUMBER, account.getNumber());
            intent.putExtra(Account.TYPE, account.getType());
            intent.putExtra(Account.BALANCE, account.getBalance());
            intent.putExtra(Account.WITHDRAW_LIMIT, account.getWithdrawLimit());
            intent.putExtra(Account.TRANSFER_LIMIT, account.getTransferLimit());
            intent.putExtra(Account.WITHDRAW_ALLOWED, account.isWithdrawAllowed());
            intent.putExtra(Account.TRANSFER_ALLOWED, account.isTransferAllowed());
            startActivityForResult(intent, ACCOUNT_REQUEST);
        }
    }

    public void accountHistoryAction(View view) {
        Account account = (Account) accountComponent.getSelectedItem();

        if (account != null) {
            Intent intent = new Intent(this, TransactionActivity.class);
            intent.putExtra(Account.ID, account.getID());
            startActivity(intent);
        }
    }

    public void depositAction(View view) {
        Account account = (Account) accountComponent.getSelectedItem();

        if (account != null) {
            Intent intent = new Intent(this, DepositActivity.class);
            intent.putExtra(Account.ID, account.getID());
            intent.putExtra(Account.NUMBER, account.getNumber());
            startActivityForResult(intent, DEPOSIT_REQUEST);
        }
    }

    public void transferAction(View view) {
        Account account = (Account) accountComponent.getSelectedItem();

        if (account != null) {
            Intent intent = new Intent(this, TransferActivity.class);
            intent.putExtra(Account.ID, account.getID());
            intent.putExtra(Account.NUMBER, account.getNumber());
            startActivityForResult(intent, TRANSFER_REQUEST);
        }
    }

    public void withdrawAction(View view) {
        Account account = (Account) accountComponent.getSelectedItem();

        if (account != null) {
            Intent intent = new Intent(this, WithdrawActivity.class);
            intent.putExtra(Account.ID, account.getID());
            intent.putExtra(Account.NUMBER, account.getNumber());
            startActivityForResult(intent, WITHDRAW_REQUEST);
        }
    }

    public void cardAddAction(View view) {
        Account account = (Account) accountComponent.getSelectedItem();

        if (account != null) {
            Intent intent = new Intent(this, CardActivity.class);
            intent.putExtra(Account.ID, account.getID());
            startActivityForResult(intent, CARD_REQUEST);
        }
    }

    public void cardEditAction(View view) {
        Card card = (Card) cardComponent.getSelectedItem();

        if (card != null) {
            Intent intent = new Intent(this, CardActivity.class);
            intent.putExtra(Account.ID, card.getAccountID());
            intent.putExtra(Card.ID, card.getID());
            intent.putExtra(Card.NUMBER, card.getNumber());
            intent.putExtra(Card.TYPE, card.getType());
            intent.putExtra(Card.VALID, Converter.dateToString(card.getDateValid(), "dd.MM.yyyy"));
            intent.putExtra(Card.LIMIT, card.getPaymentLimit());
            intent.putExtra(Card.ALLOWED, card.isPaymentAllowed());
            startActivityForResult(intent, CARD_REQUEST);
        }
    }

    public void cardPayAction(View view) {
        Card card = (Card) cardComponent.getSelectedItem();

        if (card != null) {
            Intent intent = new Intent(this, PaymentActivity.class);
            intent.putExtra(Card.ID, card.getID());
            intent.putExtra(Card.NUMBER, card.getNumber());
            startActivityForResult(intent, PAYMENT_REQUEST);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == USER_REQUEST && resultCode == RESULT_OK && data != null) {
            String id = data.getStringExtra(User.ID);
            String name = data.getStringExtra(User.NAME);
            Date dateOfBirth = Converter.stringToDate(data.getStringExtra(User.DOB), "dd.MM.yyyy");
            String address = data.getStringExtra(User.ADDRESS);
            String phone = data.getStringExtra(User.PHONE);

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
            userAdapter.notifyDataSetChanged();
        }

        if (requestCode == ACCOUNT_REQUEST && resultCode == RESULT_OK && data != null) {
            String userID = data.getStringExtra(User.ID);
            String id = data.getStringExtra(Account.ID);
            String number = data.getStringExtra(Account.NUMBER);
            String type = data.getStringExtra(Account.TYPE);
            long balance = data.getLongExtra(Account.BALANCE, 0);
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
            accountAdapter.notifyDataSetChanged();
        }

        if (requestCode == CARD_REQUEST && resultCode == RESULT_OK && data != null) {
            String accountID = data.getStringExtra(Account.ID);
            String id = data.getStringExtra(Card.ID);
            String number = data.getStringExtra(Card.NUMBER);
            String type = data.getStringExtra(Card.TYPE);
            Date dateValid = Converter.stringToDate(data.getStringExtra(Card.VALID), "dd.MM.yyyy");
            long paymentLimit = data.getLongExtra(Card.LIMIT, 0);
            boolean paymentAllowed = data.getBooleanExtra(Card.ALLOWED, true);

            if (id == null) {
                // New entry.
                cards.add(new Card(accountID, number, type, dateValid, paymentLimit, paymentAllowed));
            } else {
                // Edit entry.
                for (Card card : cards.getList()) {
                    if (id.equals(card.getID())) {
                        card.setNumber(number);
                        card.setType(type);
                        card.setDateValid(dateValid);
                        card.setPaymentLimit(paymentLimit);
                        card.setPaymentAllowed(paymentAllowed);
                        break;
                    }
                }
                cards.update();
            }
            cardAdapter.notifyDataSetChanged();
        }

        if (requestCode == PAYMENT_REQUEST && resultCode == RESULT_OK && data != null) {
            String id = data.getStringExtra(Card.ID);
            long amount = data.getLongExtra(Transaction.AMOUNT, 0);

            if (id != null && amount > 0) {
                // Search for card object.
                for (Card card : cards.getList()) {
                    if (id.equals(card.getID())) {
                        String accountID = card.getAccountID();
                        // Search for account object.
                        for (Account account : accounts.getList()) {
                            if (accountID.equals(account.getID())) {
                                // Make payment.
                                if (card.isPaymentAllowed() && amount <= card.getPaymentLimit()) {
                                    if (account.payment(amount)) {
                                        transactions.add(new Transaction(accountID, new Date(), "CARD PAYMENT", -amount));
                                        accountAdapter.notifyDataSetChanged();
                                    }
                                }
                                break;
                            }
                        }
                        break;
                    }
                }
            }
        }

        if (requestCode == DEPOSIT_REQUEST && resultCode == RESULT_OK && data != null) {
            String accountID = data.getStringExtra(Account.ID);
            long amount = data.getLongExtra(Transaction.AMOUNT, 0);

            if (accountID != null && amount > 0) {
                for (Account account : accounts.getList()) {
                    if (accountID.equals(account.getID())) {
                        // Make deposit.
                        if (account.deposit(amount)) {
                            transactions.add(new Transaction(accountID, new Date(), "DEPOSIT", amount));
                            accountAdapter.notifyDataSetChanged();
                        }
                        break;
                    }
                }
            }
        }

        if (requestCode == WITHDRAW_REQUEST && resultCode == RESULT_OK && data != null) {
            String accountID = data.getStringExtra(Account.ID);
            long amount = data.getLongExtra(Transaction.AMOUNT, 0);

            if (accountID != null && amount > 0) {
                for (Account account : accounts.getList()) {
                    if (accountID.equals(account.getID())) {
                        // Make withdrawal.
                        if (account.withdraw(amount)) {
                            transactions.add(new Transaction(accountID, new Date(), "WITHDRAW", -amount));
                            accountAdapter.notifyDataSetChanged();
                        }
                        break;
                    }
                }
            }
        }

        if (requestCode == TRANSFER_REQUEST && resultCode == RESULT_OK && data != null) {
            String senderID = data.getStringExtra(Account.ID + "1");
            String receiverID = data.getStringExtra(Account.ID + "2");
            long amount = data.getLongExtra(Transaction.AMOUNT, 0);
            Account sender = null;
            Account receiver = null;

            if (senderID != null && receiverID != null && amount > 0) {
                for (Account account : accounts.getList()) {
                    if (senderID.equals(account.getID())) {
                        sender = account;
                    }
                    if (receiverID.equals(account.getID())) {
                        receiver = account;
                    }
                    if (sender != null && receiver != null) {
                        break;
                    }
                }

                if (sender != null && receiver != null) {
                    if (sender.transfer(amount)) {
                        Date date = new Date();
                        transactions.add(new Transaction(senderID, date, "TRANSFER", -amount));
                        if (receiver.receive(amount)) {
                            transactions.add(new Transaction(receiverID, date, "RECEIVE", amount));
                        }
                        accountAdapter.notifyDataSetChanged();
                    }
                }
            }
        }
    }
}
