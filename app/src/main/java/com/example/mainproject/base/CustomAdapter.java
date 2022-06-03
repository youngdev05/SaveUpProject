package com.example.mainproject.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mainproject.R;
import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    ArrayList<DBViewer> DBViewerArrayList;

    public CustomAdapter(ArrayList<DBViewer> DBViewerArrayList) {
        this.DBViewerArrayList = DBViewerArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View myRow = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.my_row, viewGroup, false);

        return new MyViewHolder(myRow);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DBViewer DBViewer = DBViewerArrayList.get(position);
        holder.exp_id_txt.setText(String.valueOf(DBViewer.getId()));
        holder.exp_date_txt.setText(String.valueOf(DBViewer.getDate()));
        holder.exp_category_txt.setText(String.valueOf(DBViewer.getCategory()));
        holder.expenses_txt.setText(String.valueOf(DBViewer.getExpenses()));
    }

    @Override
    public int getItemCount() {
        return DBViewerArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView exp_id_txt, exp_date_txt, exp_category_txt, expenses_txt;

        public MyViewHolder(@NonNull View itemview){
            super(itemview);
            exp_id_txt = itemview.findViewById(R.id.exp_id_txt);
            exp_date_txt = itemview.findViewById(R.id.exp_date_txt);
            exp_category_txt = itemview.findViewById(R.id.exp_category_txt);
            expenses_txt = itemview.findViewById(R.id.expenses_txt);
        }
    }
}
