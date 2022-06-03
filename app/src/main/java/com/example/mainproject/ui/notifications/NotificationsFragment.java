package com.example.mainproject.ui.notifications;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
/*import androidx.lifecycle.ViewModelProvider;*/
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mainproject.R;
import com.example.mainproject.base.DBViewer;
import com.example.mainproject.base.Base;
import com.example.mainproject.base.CustomAdapter;
import com.example.mainproject.databinding.FragmentNotificationsBinding;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    Base myDB;
    ArrayList<DBViewer> DBViewerArrayList;
    public SQLiteDatabase mDb;
    CustomAdapter customAdapter;
    RecyclerView recyclerView;
    Context context;
    int SpinnerPosition;
    TextView emptyText;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //notificationsViewModel = new ViewModelProvider(this).get(NotificationsViewModel.class);
        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);
        myDB = new Base(getContext());
        emptyText = (TextView) view.findViewById(R.id.empty_view2);
        recyclerView = view.findViewById(R.id.recyclerView);
        try {
            DBViewerArrayList = myDB.getAllData();
            CustomAdapter objDbAdapter = new CustomAdapter(DBViewerArrayList);
            if (DBViewerArrayList.isEmpty()) {
                recyclerView.setVisibility(View.GONE);
                emptyText.setVisibility(View.VISIBLE);
            } else {
                recyclerView.setVisibility(View.VISIBLE);
                emptyText.setVisibility(View.GONE);
            }
            recyclerView.hasFixedSize();
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(objDbAdapter);
        } catch (Exception e) {
            Toast.makeText(getContext(), "showData:-" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }



        return view;
    }

    public int getSpinnerPosition(){
        Spinner spinner = getView().findViewById(R.id.spinner);
        SpinnerPosition = spinner.getSelectedItemPosition();
        return SpinnerPosition;
    }



}