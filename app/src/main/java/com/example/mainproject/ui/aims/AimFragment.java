package com.example.mainproject.ui.aims;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mainproject.R;
import com.example.mainproject.base.AimBase;
import com.example.mainproject.base.AimCustomAdapter;
import com.example.mainproject.base.AimDbViewer;
import com.example.mainproject.databinding.FragmentAimsBinding;

import java.util.ArrayList;

public class AimFragment extends Fragment {

    private AimViewModel aimViewModel;
    ImageButton newAim, advancedInf;
    private FragmentAimsBinding binding;
    AimBase myDB;
    ArrayList<AimDbViewer> AimDBViewerArrayList;
    RecyclerView recyclerView;
    Context context;
    TextView emptyView;
//    EditText new_aim_text, neededMoney;



    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        aimViewModel = new ViewModelProvider(this).get(AimViewModel.class);
        binding = FragmentAimsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        View view = inflater.inflate(R.layout.fragment_aims, container, false);
        newAim = view.findViewById(R.id.newAimImageButton);
        myDB = new AimBase(getContext());
        emptyView = (TextView) view.findViewById(R.id.empty_view);
        recyclerView = view.findViewById(R.id.aim_recycler);
        try {
            AimDBViewerArrayList = myDB.getAllAimData();
            AimCustomAdapter objDbAdapter = new AimCustomAdapter(context, AimDBViewerArrayList);
            if (AimDBViewerArrayList.isEmpty()) {
                recyclerView.setVisibility(View.GONE);
                emptyView.setVisibility(View.VISIBLE);
            } else {
                recyclerView.setVisibility(View.VISIBLE);
                emptyView.setVisibility(View.GONE);
            }
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(objDbAdapter);
        } catch (Exception e) {
            Toast.makeText(getContext(), "showData:-" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        newAim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater li_aim = LayoutInflater.from(view.getContext());
                View aim_dialogue = li_aim.inflate(R.layout.new_aim_dialogue, null);
                AlertDialog.Builder fDialogBuilder = new AlertDialog.Builder(view.getContext());
                final EditText new_aim_text = aim_dialogue.findViewById(R.id.new_aim_text);
                final EditText neededMoney = aim_dialogue.findViewById(R.id.neededMoneyText);
                fDialogBuilder.setView(aim_dialogue);
                fDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("ОК",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        AimBase aimBase = new AimBase(AimFragment.this.getContext());
                                        aimBase.addAimToBase(String.valueOf(new_aim_text.getText()), Integer.valueOf(neededMoney.getText().toString().trim()), 0);
                                    }
                                })
                        .setNegativeButton("Отмена",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                });
                AlertDialog alertDialog = fDialogBuilder.create();
                alertDialog.show();

            }
        });




        return view;
    }
}
