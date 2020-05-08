package com.mma.bank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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
        userID = intent.getStringExtra(User.ID);

        if (userID != null) {
            editName.setText(intent.getStringExtra(User.NAME));
            editDateOfBirth.setText(intent.getStringExtra(User.DOB));
            editAddress.setText(intent.getStringExtra(User.ADDRESS));
            editPhone.setText(intent.getStringExtra(User.PHONE));
        }
    }

    public void saveAction(View view) {
        Intent intent = new Intent();
        intent.putExtra(User.ID, userID);
        intent.putExtra(User.NAME, editName.getText().toString().trim());
        intent.putExtra(User.DOB, editDateOfBirth.getText().toString().trim());
        intent.putExtra(User.ADDRESS, editAddress.getText().toString().trim());
        intent.putExtra(User.PHONE, editPhone.getText().toString().trim());
        setResult(RESULT_OK, intent);
        finish();
    }
}
