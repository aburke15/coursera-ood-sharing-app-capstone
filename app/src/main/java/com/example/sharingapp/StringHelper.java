package com.example.sharingapp;

public class StringHelper {
    public static boolean isNullOrWhiteSpace(String value) {
        final String emptyString = "";

        if (value.equals(null) || value == null) return true;

        if (value.trim().equals(emptyString) || value.equals(emptyString)) return true;

        return false;
    }
}
