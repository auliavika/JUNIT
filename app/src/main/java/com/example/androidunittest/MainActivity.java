package com.example.androidunittest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText edtName;
    private Button btnAdd;
    private RecyclerView recyclerView;
    private GuestAdapter adapter;
    private ArrayList<String> guestList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtName = findViewById(R.id.edtName);
        btnAdd = findViewById(R.id.btnAdd);
        recyclerView = findViewById(R.id.recyclerView);

        guestList = new ArrayList<>();
        adapter = new GuestAdapter(guestList, new GuestAdapter.OnGuestDeleteListener() {
            @Override
            public void onDeleteGuest(String guestName) {
                // Menghapus tamu dari daftar
                guestList.remove(guestName);
                adapter.notifyDataSetChanged();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        btnAdd.setOnClickListener(v -> {
            String name = edtName.getText().toString().trim();
            if (!name.isEmpty()) {
                guestList.add(name);
                adapter.notifyDataSetChanged();
                edtName.setText("");
            }
        });
    }
}
