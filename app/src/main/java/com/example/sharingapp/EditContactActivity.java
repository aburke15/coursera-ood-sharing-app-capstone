package com.example.sharingapp;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class EditContactActivity extends AppCompatActivity {
    private ContactList contact_list = new ContactList();
    private Contact contact;
    private EditText email;
    private EditText username;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        context = getApplicationContext();
        contact_list.loadContacts(context);

        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);

        contact = contact_list.getContact(position);

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);

        username.setText(contact.getUsername());
        email.setText(contact.getEmail());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void saveContact(View view) {
        String email_text = email.getText().toString();

        if (StringHelper.isNullOrWhiteSpace(email_text)) {
            email.setError("Empty field!");
            return;
        }

        if (!email_text.contains("@")) {
            email.setError("Must be an email address!");
            return;
        }

        String username_text = username.getText().toString();
        String id = contact.getId();

        if (!contact_list.isUsernameAvailable(username_text) &&
                !contact.getUsername().equals(username_text)) {
            username.setError("Username is already taken!");
            return;
        }

        Contact updated = new Contact(username_text, email_text, id);

        contact_list.deleteContact(contact);
        contact_list.addContact(updated);
        contact_list.saveContacts(context);

        finish();
    }

    public void deleteContact(View view) {
        contact_list.deleteContact(contact);
        contact_list.saveContacts(context);

        finish();
    }
}