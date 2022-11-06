package com.example.sharingapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddContactActivity extends AppCompatActivity {
    private ContactList contact_list = new ContactList();
    private Context context;
    private EditText username;
    private EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);

        context = getApplicationContext();
        contact_list.loadContacts(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void saveContact(View view) {
        String username_text = username.getText().toString();
        String email_text = email.getText().toString();

        if (username_text.equals("")) {
            username.setError("Empty field!");
            return;
        }

        if (email_text.equals("")) {
            email.setError("Empty field!");
            return;
        }

        if (!email_text.contains("@")) {
            email.setError("Must be an email address!");
            return;
        }

        if (!contact_list.isUsernameAvailable(username_text)) {
            username.setError("Username already taken!");
            return;
        }

        Contact contact = new Contact(username_text, email_text, "-1");
        contact_list.addContact(contact);
        contact_list.saveContacts(context);

        finish();;
    }
}