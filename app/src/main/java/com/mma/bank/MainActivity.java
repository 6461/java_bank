package com.mma.bank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    UserList users = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FileHandler.setContext(this);
        users = UserList.getInstance();
    }

//    public void testAction(View view) {
//        users.add(new User("Testi Henkilö", new Date(), "Helsingintie 1", "0400123123"));
//    }
}
