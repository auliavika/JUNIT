package com.example.androidunittest;

import java.util.ArrayList;
import java.util.List;

public class Guestbook {
    private final List<String> guests = new ArrayList<>();

    // Menambahkan tamu
    public boolean addGuest(String name) {
        if (name.isEmpty()) {
            return false;
        }
        guests.add(name);
        return true;
    }

    // Menghapus tamu berdasarkan nama
    public boolean removeGuest(String name) {
        return guests.remove(name);
    }

    // Mengembalikan daftar tamu
    public List<String> getGuests() {
        return guests;
    }
}
