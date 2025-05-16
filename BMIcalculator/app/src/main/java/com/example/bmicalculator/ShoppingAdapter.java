package com.example.bmicalculator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Adapter dla RecyclerView w ShoppingActivity. Wyświetla listę
 * modeli ShoppingItem i pozwala na odznaczanie zakupionych pozycji.
 */
public class ShoppingAdapter extends RecyclerView.Adapter<ShoppingAdapter.VH> {

    private final List<ShoppingItem> items;

    public ShoppingAdapter(List<ShoppingItem> items) {
        this.items = items;
    }

    /**
     * Klasa przechowująca referencje do widoków
     * pojedynczego wiersza (checkbox + nazwa).
     */
    public static class VH extends RecyclerView.ViewHolder {
        final CheckBox cb;
        final TextView name;

        VH(View itemView) {
            super(itemView);
            cb = itemView.findViewById(R.id.checkbox);
            name = itemView.findViewById(R.id.name);
        }
    }

    @NotNull
    @Override
    public VH onCreateViewHolder(ViewGroup p, int viewType) {
        View v = LayoutInflater.from(p.getContext())
                .inflate(R.layout.item_shopping, p, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(VH h, int pos) {
        ShoppingItem it = items.get(pos);
        h.name.setText(it.getName());
        h.cb.setChecked(it.isBought());
        h.cb.setOnCheckedChangeListener((b, checked) -> it.setBought(checked));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
