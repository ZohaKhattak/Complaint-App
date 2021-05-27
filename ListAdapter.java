package com.example.complaint_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends ArrayAdapter {
    List list = new ArrayList();

    public ListAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    static class LayoutHandler
    {
        TextInputEditText fullname, username, email, phone, password;
    }

    @Override
    public void add(Object object) {
        super.add(object);
        list.add(object);

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row = convertView;
        LayoutHandler layoutHandler;
        if (row==null){
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layoutHandler = new LayoutHandler();
            layoutHandler.fullname=(TextInputEditText)row.findViewById(R.id.fullname);
            layoutHandler.username=(TextInputEditText)row.findViewById(R.id.username);
            layoutHandler.email=(TextInputEditText)row.findViewById(R.id.email);
            layoutHandler.phone=(TextInputEditText)row.findViewById(R.id.phone);
            layoutHandler.password=(TextInputEditText)row.findViewById(R.id.password);
            row.setTag(layoutHandler);
        }
        else {
            layoutHandler = (LayoutHandler)row.getTag();
        }

        DataProvider dataProvider = (DataProvider)this.getItem(position);
        layoutHandler.fullname.setText(dataProvider.getFullname());
        layoutHandler.username.setText(dataProvider.getUsername());
        layoutHandler.email.setText(dataProvider.getEmail());
        layoutHandler.phone.setText(dataProvider.getPhone());
        layoutHandler.password.setText(dataProvider.getPassword());
        return row;
    }
}
