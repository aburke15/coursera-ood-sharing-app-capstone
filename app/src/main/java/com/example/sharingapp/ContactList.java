package com.example.sharingapp;

import android.content.Context;
import android.os.Build;
import android.renderscript.ScriptIntrinsicYuvToRGB;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ContactList {
    private ArrayList<Contact> contacts;
    private final String FILENAME = "contacts.sav";

    public ContactList() {
        contacts = new ArrayList<>();
    }

    public void loadContacts(Context context) {
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Contact>>() {}.getType();
            contacts = gson.fromJson(isr, listType); // temporary
            fis.close();
        } catch (FileNotFoundException e) {
            contacts = new ArrayList<>();
        } catch (IOException e) {
            contacts = new ArrayList<>();
        }
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

    public boolean hasContact(Contact contact) {
        return contacts.contains(contact);
    }

    public int getSize() {
        return contacts.size();
    }

    public int getIndex(Contact contact) {
        int position = 0;
        for (Contact c : contacts) {
            if (contact.getId().equals(c.getId())) {
                return position;
            }
            position++;
        }

        return -1;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean isUsernameAvailable(String username) {
        return contacts.stream().noneMatch(contact -> contact.getUsername().equals(username));
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void saveContacts(Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, 0);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(contacts, osw);
            osw.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Contact getContact(int position) {
        return contacts.get(position);
    }

    public void deleteContact(Contact contact) {
        contacts.remove(contact);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<String> getAllUsernames() {
        return contacts.stream().map(contact -> contact.getUsername()).collect(Collectors.toList());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Contact getContactByUsername(String username) {
        return contacts.stream().filter(contact -> contact.getUsername().equals(username)).findFirst().get();
    }
}
