package com.mma.bank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.Date;

public class UserActivity extends AppCompatActivity {

    private String userID;

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
        userID = intent.getStringExtra(User.USER_ID);

        if (userID != null) {
            editName.setText(intent.getStringExtra(User.USER_NAME));
            editDateOfBirth.setText(intent.getStringExtra(User.USER_DOB));
            editAddress.setText(intent.getStringExtra(User.USER_ADDRESS));
            editPhone.setText(intent.getStringExtra(User.USER_PHONE));
        }
    }

    public void saveAction(View view) {
        Intent intent = new Intent();
        intent.putExtra(User.USER_ID, userID);
        intent.putExtra(User.USER_NAME, editName.getText().toString().trim());
        intent.putExtra(User.USER_DOB, editDateOfBirth.getText().toString().trim());
        intent.putExtra(User.USER_ADDRESS, editAddress.getText().toString().trim());
        intent.putExtra(User.USER_PHONE, editPhone.getText().toString().trim());
        setResult(RESULT_OK, intent);
        finish();
    }
}
