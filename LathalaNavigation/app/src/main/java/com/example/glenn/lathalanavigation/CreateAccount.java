package com.example.glenn.lathalanavigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class CreateAccount extends AppCompatActivity {

    Button btnCreateAccount;
    EditText etUsername;
    EditText etPassword;
    EditText etName;
    EditText etRetypePassword;

    DatabaseOpenHelper db;

    TextView tvStatus;
    CheckBox cbxTermsAndAgreement;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        db = new DatabaseOpenHelper(getBaseContext());
        etUsername = (EditText) findViewById(R.id.et_Password);
        etPassword = (EditText) findViewById(R.id.et_Password);
        etName = (EditText) findViewById(R.id.et_Username);
        etRetypePassword = (EditText) findViewById(R.id.et_Retype);
        tvStatus = (TextView) findViewById(R.id.tv_Status);
        cbxTermsAndAgreement = (CheckBox) findViewById(R.id.cbx_TermsAndAgreement);
        tvStatus.setVisibility(View.INVISIBLE);



        btnCreateAccount = (Button) findViewById(R.id.bt_CreateAccount);

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homepage = new Intent();

                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                String name = etName.getText().toString();
                String retype = etRetypePassword.getText().toString();


                if(cbxTermsAndAgreement.isChecked()) {
                    tvStatus.setVisibility(View.INVISIBLE);
                    if (retype.equals(password) == true) {
                        User u = new User(username, password, name);

                        db.addUser(u);

                        finish();
                    } else {
                        tvStatus.setText("Passwords don't match.");
                        tvStatus.setVisibility(View.VISIBLE);
                    }
                }else{
                    tvStatus.setText("You have not agreed with our Terms and Agreement");
                    tvStatus.setVisibility(View.VISIBLE);
                }

            }
        });

    }

}
