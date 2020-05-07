package com.mma.bank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.Date;

public class UserActivity extends AppCompatActivity {

    EditText editName = null;
    EditText editDateOfBirth = null;
    EditText editAddress = null;
    EditText editPhone = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        editName = findViewById(R.id.editName);
        editDateOfBirth = findViewById(R.id.editDateOfBirth);
        editAddress = findViewById(R.id.editAddress);
        editPhone = findViewById(R.id.editPhone);

        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra(MainActivity.USER_EXTRA);

        if (user != null) {
            editName.setText(user.getName());
            editDateOfBirth.setText(DateHandler.getString(user.getDateOfBirth(), "dd.MM.yyyy"));
            editAddress.setText(user.getAddress());
            editPhone.setText(user.getPhone());
        }
    }

    public void saveAction(View view) {
        String name = editName.getText().toString().trim();
        Date dateOfBirth = DateHandler.parseDate(editDateOfBirth.getText().toString().trim(), "dd.MM.yyyy");
        String address = editAddress.getText().toString().trim();
        String phone = editPhone.getText().toString().trim();
        User user = new User(name, dateOfBirth, address, phone);

        Intent intent = new Intent();
        intent.putExtra(MainActivity.USER_EXTRA, user);
        setResult(RESULT_OK, intent);
        finish();
    }
}
