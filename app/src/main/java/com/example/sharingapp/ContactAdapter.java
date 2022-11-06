package com.example.sharingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class ContactAdapter extends ArrayAdapter<Contact> {
    private LayoutInflater inflater;
    private Context context;

    public ContactAdapter(Context context, ArrayList<Contact> contacts) {
        super(context, 0, contacts);
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Contact contact = getItem(position);

        String username = "Username: " + contact.getUsername();
        String email = "Email: " + contact.getEmail();

        if (convertView == null) {
            convertView = inflater.from(context).inflate(R.layout.contactlist_contact, parent, false);
        }

        TextView username_tv = convertView.findViewById(R.id.username_tv);
        TextView email_tv = convertView.findViewById(R.id.email_tv);
        ImageView photo = convertView.findViewById(R.id.contacts_image_view);

        photo.setImageResource(android.R.drawable.ic_menu_gallery);

        username_tv.setText(username);
        email_tv.setText(email);

        return convertView;
    }
}
