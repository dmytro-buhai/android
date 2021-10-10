package com.app.simplemainactivityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.nio.charset.Charset;

public class MainActivity extends AppCompatActivity {

    TableLayout usersTableLayout;
    EditText userName, userPassword;
    Button addUserBtn;
    CheckBox isHidden;

    public String email;
    public String password;
    private int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Views
        userName = findViewById(R.id.user_name);
        userPassword = findViewById(R.id.user_password);
        addUserBtn = findViewById(R.id.add_user_btn);

        //Table
        usersTableLayout = (TableLayout) findViewById(R.id.table_main);

        //CheckBox
        isHidden = findViewById(R.id.hide_password);

        addUserBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AddUser(usersTableLayout, userName, userPassword, isHidden.isChecked());
            }
        });

        counter = 0;

        InitTable(usersTableLayout);
    }

    private void AddUser(TableLayout table, EditText userName, EditText userPassword, boolean isHidden){
        TableRow userRow = new TableRow(this);

        TextView numberFieldTV = new TextView(this);
        numberFieldTV.setText("" + counter);
        userRow.addView(numberFieldTV);

        TextView userNameTV = new TextView(this);
        userNameTV.setText(userName.getText().toString());
        userRow.addView(userNameTV);

        TextView userPasswordTV = new TextView(this);
        if(isHidden){
            Charset charset = Charset.forName("ASCII");
            byte[] byteArrray = userPassword.getText().toString().getBytes(charset);
            String password = String.valueOf(byteArrray.hashCode());
            userPasswordTV.setText(password);
        } else {
            userPasswordTV.setText(userPassword.getText().toString());
        }

        userRow.addView(userPasswordTV);

        table.addView(userRow);
        counter++;
    }

    private void InitTable(TableLayout table) {
        TableRow userTableRow = new TableRow(this);

        TextView userNumberTextView = new TextView(this);
        TextView userTextView = new TextView(this);
        TextView userPasswordTextView = new TextView(this);

        userNumberTextView.setText(" Number ");
        userTableRow.addView(userNumberTextView);

        userTextView.setText(" User name ");
        userTableRow.addView(userTextView);

        userPasswordTextView.setText(" User password ");
        userTableRow.addView(userPasswordTextView);

        table.addView(userTableRow);

        final int USERS_COUNT = 5;

        for (int i = 0; i < USERS_COUNT; i++) {
            TableRow userRow = new TableRow(this);

            TextView number = new TextView(this);
            number.setText("" + i);
            userRow.addView(number);

            TextView userName = new TextView(this);
            userName.setText("user " + i);
            userRow.addView(userName);

            TextView userPassword = new TextView(this);
            userPassword.setText("000" + i);
            userRow.addView(userPassword);

            table.addView(userRow);
            counter++;
        }
    }
}