package com.example.androidunittest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GuestAdapter extends RecyclerView.Adapter<GuestAdapter.ViewHolder> {
    private List<String> guestList;
    private OnGuestDeleteListener deleteListener;

    // Konstruktor untuk menerima daftar tamu
    public GuestAdapter(List<String> guestList, OnGuestDeleteListener deleteListener) {
        this.guestList = guestList;
        this.deleteListener = deleteListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate layout item_guest untuk setiap item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_guest, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Mengatur nama tamu pada TextView
        holder.txtGuestName.setText(guestList.get(position));

        // Set listener untuk tombol Hapus
        holder.btnDelete.setOnClickListener(v -> {
            // Menghapus tamu dengan memanggil listener
            deleteListener.onDeleteGuest(guestList.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return guestList.size();
    }

    // ViewHolder untuk setiap item
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtGuestName;
        Button btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtGuestName = itemView.findViewById(R.id.txtGuestName);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }

    // Interface untuk menangani aksi delete tamu
    public interface OnGuestDeleteListener {
        void onDeleteGuest(String guestName);
    }
}
