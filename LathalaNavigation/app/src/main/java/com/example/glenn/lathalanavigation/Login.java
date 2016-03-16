package com.example.glenn.lathalanavigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    public static int ACCOUNT_NUMBER;
    public static String ACCOUNT_NAME = "User's name";
    //Nica eto
    DatabaseOpenHelper db;
    EditText etUsername, etPassword;
    Button login;
    TextView createAccount;
    //end of Nica eto

    Button btnLogin;
    TextView tvCreateAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

//        getSupportActionBar().hide();




        setContentView(R.layout.activity_login);

        //Nica eto
        db = new DatabaseOpenHelper(getBaseContext());
        etUsername = (EditText) findViewById(R.id.et_Name);
        etPassword = (EditText) findViewById(R.id.et_Username);
        //end of Nica eto


        btnLogin = (Button) findViewById(R.id.bt_Login);


        tvCreateAccount = (TextView) findViewById(R.id.tv_CreateAccount);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homepage = new Intent();

                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                User u = db.checkifUserExists(username);
                if(u == null){
                    Toast.makeText(v.getContext(), "Incorrent Username or Password", Toast.LENGTH_LONG).show();

                }
                else {
                    String checkPassword = u.getPassword();
                    if (password.equals(checkPassword) == true) {
                        ACCOUNT_NUMBER = u.getId();
                        ACCOUNT_NAME = u.getName();

                        Toast.makeText(v.getContext(), u.getName(), Toast.LENGTH_LONG).show();
                        Intent explicit = new Intent();
                        homepage.setClass(getBaseContext(), Publish.class);
                        startActivity(homepage);


                    } else  Toast.makeText(v.getContext(), "Incorrent Username or Password", Toast.LENGTH_LONG).show();
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

