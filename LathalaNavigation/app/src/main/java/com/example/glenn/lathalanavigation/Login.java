package com.example.glenn.lathalanavigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Login extends AppCompatActivity {


    Button btnLogin;
    TextView tvCreateAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.bt_Login);

        tvCreateAccount = (TextView) findViewById(R.id.tv_CreateAccount);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homepage = new Intent();

                homepage.setClass(getBaseContext(), Publish.class);
                startActivity(homepage);
            }
        });

        tvCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homepage = new Intent();

                homepage.setClass(getBaseContext(), CreateAccount.class);
                startActivity(homepage);
            }
        });



    }

}
