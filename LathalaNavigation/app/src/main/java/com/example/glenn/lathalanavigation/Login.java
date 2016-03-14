package com.example.glenn.lathalanavigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    public static int ACCOUNT_NUMBER;
    public static String ACCOUNT_NAME = "User's name";
    //Nica eto
    DatabaseOpenHelper db;
    EditText etUsername, etPassword;
    Button login;
    TextView createAccount, warning;
    //end of Nica eto

    Button btnLogin;
    TextView tvCreateAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Nica eto
        db = new DatabaseOpenHelper(getBaseContext());
        etUsername = (EditText) findViewById(R.id.et_Username);
        etPassword = (EditText) findViewById(R.id.et_Password);
        warning = (TextView) findViewById(R.id.Logo);
        //end of Nica eto


        btnLogin = (Button) findViewById(R.id.bt_Login);

        tvCreateAccount = (TextView) findViewById(R.id.tv_CreateAccount);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homepage = new Intent();
                User u = new User();
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                u = db.checkifUserExists(username);
                if(u == null){
                    warning.setText("Incorrect Username or Password");
                }
                else {
                    String checkPassword = u.getPassword();
                    if (password.equals(checkPassword) == true) {
                        //added account number para mag sync
                        ACCOUNT_NUMBER = u.getId();
                        ACCOUNT_NAME = u.getName();

                        Intent explicit = new Intent();
                        homepage.setClass(getBaseContext(), Publish.class);
                        startActivity(homepage);
                    } else warning.setText("Incorrect Username or Password");
                }
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

