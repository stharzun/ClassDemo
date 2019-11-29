package com.stharzun.demoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText name, address, contact, email, password, confirmPassword, username;
    Button register;
    RadioGroup genderGroup;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.name);
        address = findViewById(R.id.address);
        contact = findViewById(R.id.contact);
        email = findViewById(R.id.email_address);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirm_password);
        register = findViewById(R.id.register);
        genderGroup = findViewById(R.id.gender_group);

        dbHelper = new DBHelper(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String gender;
                RadioButton rb = findViewById(genderGroup.getCheckedRadioButtonId());
                gender = rb.getText().toString();

                dbHelper.insertUser(name.getText().toString(),
                        address.getText().toString(),
                        email.getText().toString(),
                        contact.getText().toString(),
                        username.getText().toString(),
                        password.getText().toString(),
                        gender);

                Toast.makeText(RegisterActivity.this, "Register Successful", Toast.LENGTH_SHORT).show();
                finish();
            }
//            }
        });
    }

   /* private boolean validateAllItems() {
        return validateSingleItems(name)
                && validateSingleItems(address)
                && validateSingleItems(email)
                && validateSingleItems(contact)
                && validateSingleItems(username)
                && validatePassword(password, confirmPassword);
    }

    private boolean validateSingleItems(EditText editText) {
        if (editText.getText().toString().isEmpty()) {
            editText.setError("Required");
            return false;
        } else {
            return true;
        }
    }

    private boolean validatePassword(EditText password, EditText confirmPassword) {
        if (validateSingleItems(password) && validateSingleItems(confirmPassword)) {
            if (password.getText().toString().equals(confirmPassword.getText().toString()))
                return true;
            else
                return false;
        } else {
            return false;
        }
    }*/
}
