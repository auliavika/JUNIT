package com.example.androidunittest;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.view.View;
import android.view.ViewGroup;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)

public class MainActivityTest {

    @Before
    public void setUp() {
        // Menjalankan MainActivity sebelum setiap test case dijalankan
        ActivityScenario.launch(MainActivity.class);
    }

    @Test
    public void tambahTamu_tamuTampilDiRecyclerView() {
        /*
         * Test ini memverifikasi bahwa setelah tamu baru (Aulia) ditambahkan,
         * tamu tersebut muncul dalam RecyclerView.
         */
        // Masukkan nama tamu
        onView(withId(R.id.edtName)).perform(replaceText("Aulia"));

        // Klik tombol Tambah
        onView(withId(R.id.btnAdd)).perform(click());

        // Pastikan nama "Aulia" muncul di RecyclerView
        onView(withText("Aulia")).check(matches(withText("Aulia")));
    }

    @Test
    public void hapusTamu_namaTamuHilangDariRecyclerView() {
        /*
         * Test ini memverifikasi bahwa jika tamu yang ada (Aulia) dihapus,
         * tamu tersebut benar-benar hilang dari RecyclerView.
         */
        // Tambah tamu "Aulia"
        onView(withId(R.id.edtName)).perform(replaceText("Aulia"));
        onView(withId(R.id.btnAdd)).perform(click());

        // Pastikan tamu "Aulia" muncul di RecyclerView
        onView(withText("Aulia")).check(matches(withText("Aulia")));

        // Klik tombol Hapus untuk "Aulia"
        onView(withText("Aulia")).perform(scrollTo(), click());

        // Klik tombol Hapus yang ada di dalam item guest
        onView(withId(R.id.btnDelete)).perform(click());

        // Pastikan nama "Aulia" sudah tidak ada di RecyclerView
        onView(withText("Aulia")).check(doesNotExist());
    }

    @Test
    public void tambahTamu_namaKosong_tamuTidakDitambahkan() {
        /*
         * Test ini memverifikasi bahwa jika nama tamu kosong dimasukkan,
         * tamu tidak akan ditambahkan ke RecyclerView.
         */
        // Masukkan nama kosong
        onView(withId(R.id.edtName)).perform(replaceText(""));

        // Klik tombol Tambah
        onView(withId(R.id.btnAdd)).perform(click());

        // Pastikan RecyclerView masih kosong
        onView(withId(R.id.recyclerView)).check(matches(hasChildCount(0)));
    }

    @Test
    public void tambahDanHapusTamu_urutannyaAcak() {
        /*
         * Test ini memverifikasi bahwa tamu ditambahkan dan dihapus dalam urutan yang benar,
         * meskipun penghapusan dilakukan dalam urutan yang tidak berurutan.
         */
        // Tambah tamu "Aulia"
        onView(withId(R.id.edtName)).perform(replaceText("Aulia"));
        onView(withId(R.id.btnAdd)).perform(click());

        // Tambah tamu "Budi"
        onView(withId(R.id.edtName)).perform(replaceText("Budi"));
        onView(withId(R.id.btnAdd)).perform(click());

        // Tambah tamu "Riski"
        onView(withId(R.id.edtName)).perform(replaceText("Riski"));
        onView(withId(R.id.btnAdd)).perform(click());

        // Pastikan tamu "Aulia", "Budi", dan "Riski" muncul di RecyclerView
        onView(withText("Aulia")).check(matches(isDisplayed()));
        onView(withText("Budi")).check(matches(isDisplayed()));
        onView(withText("Riski")).check(matches(isDisplayed()));

        // Hapus tamu "Budi" (posisi 1)
        onView(withId(R.id.recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, clickChildViewWithId(R.id.btnDelete)));

        // Pastikan "Budi" sudah tidak ada di RecyclerView
        onView(withText("Budi")).check(doesNotExist());

        // Hapus tamu "Riski" (posisi 1 setelah "Budi" dihapus)
        onView(withId(R.id.recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, clickChildViewWithId(R.id.btnDelete)));

        // Pastikan "Riski" sudah tidak ada di RecyclerView
        onView(withText("Riski")).check(doesNotExist());

        // Hapus tamu "Aulia" (posisi 0 setelah "Budi" dan "Riski" dihapus)
        onView(withId(R.id.recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.btnDelete)));

        // Pastikan "Aulia" sudah tidak ada di RecyclerView
        onView(withText("Aulia")).check(doesNotExist());
    }

    /*
     * clickChildViewWithId adalah helper method untuk melakukan klik pada elemen child tertentu
     * dalam RecyclerView berdasarkan ID child view yang diberikan (misalnya tombol hapus).
     */

    public static ViewAction clickChildViewWithId(final int childViewId) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(ViewGroup.class);
            }

            @Override
            public String getDescription() {
                return "click child view with id: " + childViewId;
            }

            @Override
            public void perform(UiController uiController, View view) {
                ViewGroup viewGroup = (ViewGroup) view;
                View targetView = viewGroup.findViewById(childViewId);
                targetView.performClick();
            }
        };
    }
}
