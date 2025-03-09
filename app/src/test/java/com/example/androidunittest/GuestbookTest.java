package com.example.androidunittest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class GuestbookTest {
    private Guestbook guestbook;

    @Before
    public void setUp() {
        /*
         * Metode setUp ini akan dijalankan sebelum setiap test case untuk mempersiapkan objek guestbook.
         * Di sini kita menginisialisasi objek Guestbook yang akan digunakan dalam test case.
         */
        guestbook = new Guestbook();
    }

    @Test
    public void tambahTamu_namaValid_tamuBerhasilDitambahkan() {
        /*
         * Test ini memverifikasi bahwa jika nama tamu yang valid ditambahkan,
         * metode addGuest() akan mengembalikan nilai true (berhasil menambahkan tamu).
         */
        assertTrue(guestbook.addGuest("Aulia"));
    }

    @Test
    public void tambahTamu_namaKosong_tamuGagalDitambahkan() {
        /*
         * Test ini memverifikasi bahwa jika nama tamu kosong ditambahkan,
         * metode addGuest() akan mengembalikan nilai false (gagal menambahkan tamu).
         */
        assertFalse(guestbook.addGuest(""));
    }

    @Test
    public void hapusTamu_namaTamuAda_tamuBerhasilDihapus() {
        /*
         * Test ini memverifikasi bahwa jika tamu dengan nama yang sudah ada (Aulia) dihapus,
         * metode removeGuest() akan mengembalikan nilai true (berhasil menghapus tamu).
         */
        guestbook.addGuest("Aulia"); // Tambah tamu "Aulia"
        assertTrue(guestbook.removeGuest("Aulia")); // Hapus tamu "Aulia" dan pastikan berhasil
    }

    @Test
    public void hapusTamu_namaTamuTidakAda_tamuGagalDihapus() {
        /*
         * Test ini memverifikasi bahwa jika mencoba menghapus tamu dengan nama yang tidak ada dalam guestbook,
         * metode removeGuest() akan mengembalikan nilai false (gagal menghapus tamu).
         */
        assertFalse(guestbook.removeGuest("Budi"));
    }
}
