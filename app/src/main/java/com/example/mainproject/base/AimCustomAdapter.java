package com.example.mainproject.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mainproject.ui.aims.AdvancedAimActivity;
import com.example.mainproject.R;
import java.util.ArrayList;

public class AimCustomAdapter extends RecyclerView.Adapter<AimCustomAdapter.MyAimViewHolder>{
    ArrayList<AimDbViewer> AimDBViewerArrayList;
    ImageButton imageButton;
    Context context;


    public AimCustomAdapter(Context context, ArrayList<AimDbViewer> AimDBViewerArrayList) {
        this.AimDBViewerArrayList = AimDBViewerArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyAimViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View aimRow = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.aim_row, viewGroup, false);

        return new MyAimViewHolder(aimRow);
    }



    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull MyAimViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        AimDbViewer aimDbViewer = AimDBViewerArrayList.get(position);
        holder.IDText.setText(String.valueOf(aimDbViewer.getId()));
        holder.aimTypeText.setText(String.valueOf(aimDbViewer.getAim()));
        holder.neededMoney.setText(String.valueOf(aimDbViewer.getNeededMoney()));
        holder.currentMoney.setText(String.valueOf(aimDbViewer.getCurrentMoney()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AdvancedAimActivity.class);
                intent.putExtra(AdvancedAimActivity.EXTRA_POS, position);
                intent.putExtra("aim", aimDbViewer.getAim());
                intent.putExtra("need", String.valueOf(aimDbViewer.getNeededMoney()));
                intent.putExtra("curr", String.valueOf(aimDbViewer.getCurrentMoney()));
                intent.putExtra("id", String.valueOf(aimDbViewer.getId()));
                view.getContext().startActivity(intent);
            }
        });
        holder.rowBar.setMax(aimDbViewer.getNeededMoney());
        holder.rowBar.setProgress(aimDbViewer.getCurrentMoney());

    }

    @Override
    public int getItemCount() {
        return AimDBViewerArrayList.size();
    }

    public class MyAimViewHolder extends RecyclerView.ViewHolder {

        TextView aimTypeText, neededMoney, IDText, currentMoney;
        ImageButton imageButton;
        ProgressBar rowBar;


        public MyAimViewHolder(@NonNull View itemview){
            super(itemview);
            aimTypeText = itemview.findViewById(R.id.aimTypeText);
            neededMoney = itemview.findViewById(R.id.neededMoney);
            IDText = itemview.findViewById(R.id.IDTextView);
            currentMoney = itemview.findViewById(R.id.current);
            imageButton = itemview.findViewById(R.id.AdvancedInfButton);
            rowBar = itemview.findViewById(R.id.rowProgressBar);
        }
    }
}



