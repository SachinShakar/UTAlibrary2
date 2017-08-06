package com.sachinshankar.utalibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;

public class UserLogin extends AppCompatActivity {
    private Button login;
    private EditText netid;
    private EditText password;
    private TextView error;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View view){
                checkLogin();
            }
        });

    }

    public void checkLogin(){
        netid = (EditText) findViewById(R.id.netid);
        password = (EditText) findViewById(R.id.password);
        error = (TextView) findViewById(R.id.error);

        if(netid.getText().toString().trim().length() <= 0)
            error.setText("NetID field is empty");

        else if(password.getText().toString().trim().length() <= 0)
            error.setText("Password field is empty");

        else{
           new SigninActivity(this,error).execute(netid.getText().toString(),password.getText().toString());

        }

    }
}
