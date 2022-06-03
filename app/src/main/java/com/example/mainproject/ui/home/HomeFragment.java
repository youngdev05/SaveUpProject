package com.example.mainproject.ui.home;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;


import com.example.mainproject.R;
import com.example.mainproject.base.Base;
import com.example.mainproject.base.DailyBase;
import com.example.mainproject.databinding.FragmentHomeBinding;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    Context context = this.getContext();
    ProgressBar dayProgressBar, weekProgressBar, monthProgressBar;
    TextView textView;
    ImageButton addDayAim, addWeekAim;
    DailyBase dailyBase;

    public HomeFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


//        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        Base myDb = new Base(getContext());
        dailyBase = new DailyBase(getContext());
        com.example.mainproject.databinding.FragmentHomeBinding binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ImageButton food_button = (ImageButton) view.findViewById(R.id.food_btn);
        food_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater li_food = LayoutInflater.from(view.getContext());
                View food_dialogue = li_food.inflate(R.layout.food_dialogue, null);
                AlertDialog.Builder fDialogBuilder = new AlertDialog.Builder(view.getContext());
                final EditText userInput = (EditText) food_dialogue.findViewById(R.id.food_text);
                fDialogBuilder.setView(food_dialogue);
                fDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("ОК",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Base base = new Base(HomeFragment.this.getContext());
                                        base.addC(getDate(), "Питание", Integer.valueOf(userInput.getText().toString().trim()));
                                        dailyBase.dayExpenses(getDate(), Integer.valueOf(userInput.getText().toString().trim()));
                                        dayProgressBar.setMax(myDb.sumExpenses());


//                                        centreTextView.setText(userInput.getText());
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

        ImageButton transport_button = (ImageButton) view.findViewById(R.id.transport_btn);
        transport_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                LayoutInflater li_transport = LayoutInflater.from(view.getContext());
                View transport_dialogue = li_transport.inflate(R.layout.transport_dialogue, null);
                AlertDialog.Builder tDialogBuilder = new AlertDialog.Builder(view.getContext());
                final EditText userInput = (EditText) transport_dialogue.findViewById(R.id.transport_text);

                tDialogBuilder.setView(transport_dialogue);
                tDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("ОК",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Base base = new Base(HomeFragment.this.getContext());
                                        base.addC(getDate(), "Транспорт", Integer.valueOf(userInput.getText().toString().trim()));
                                        dailyBase.dayExpenses(getDate(), Integer.valueOf(userInput.getText().toString().trim()));
//                                        centreTextView.setText(userInput.getText());
                                    }
                                })
                        .setNegativeButton("Отмена",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                });
                //Возможно тут фейл, при вылете проверить строку ниже!
                AlertDialog alertDialog = tDialogBuilder.create();
                alertDialog.show();
            }
        });

        ImageButton entertainment_button = (ImageButton) view.findViewById(R.id.entertainment_btn);
        entertainment_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                LayoutInflater li_enter = LayoutInflater.from(view.getContext());
                View entertainment_dialogue = li_enter.inflate(R.layout.entertainment_dialogue, null);
                AlertDialog.Builder eDialogBuilder = new AlertDialog.Builder(view.getContext());
                final EditText userInput = (EditText) entertainment_dialogue.findViewById(R.id.entertainment_text);

                eDialogBuilder.setView(entertainment_dialogue);
                eDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("ОК",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Base base = new Base(HomeFragment.this.getContext());
                                        base.addC(getDate(), "Развлечения", Integer.valueOf(userInput.getText().toString().trim()));
                                        dailyBase.dayExpenses(getDate(), Integer.valueOf(userInput.getText().toString().trim()));
//                                        centreTextView.setText(userInput.getText());
                                    }
                                })
                        .setNegativeButton("Отмена",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                });

                AlertDialog alertDialog = eDialogBuilder.create();
                alertDialog.show();
            }
        });

        ImageButton clothes_button = (ImageButton) view.findViewById(R.id.clothes_btn);
        clothes_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                LayoutInflater li_clothes = LayoutInflater.from(view.getContext());
                View clothes_dialogue = li_clothes.inflate(R.layout.clothes_dialogue, null);
                AlertDialog.Builder cDialogBuilder = new AlertDialog.Builder(view.getContext());
                final EditText userInput = (EditText) clothes_dialogue.findViewById(R.id.clothes_text);
                cDialogBuilder.setView(clothes_dialogue);
                cDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("ОК",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Base base = new Base(HomeFragment.this.getContext());
                                        base.addC(getDate(), "Одежда", Integer.valueOf(userInput.getText().toString().trim()));
                                        monthProgressBar.setMax(myDb.sumExpenses());
                                        monthProgressBar.setProgress(myDb.getClothExpenses());
                                        dailyBase.dayExpenses(getDate(), Integer.valueOf(userInput.getText().toString().trim()));
                                    }
                                })
                        .setNegativeButton("Отмена",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                });

                AlertDialog alertDialog = cDialogBuilder.create();
                alertDialog.show();
            }
        });

        ImageButton house_button = (ImageButton) view.findViewById(R.id.house_btn);
        house_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                LayoutInflater li_house = LayoutInflater.from(view.getContext());
                View house_dialogue = li_house.inflate(R.layout.house_dialogue, null);
                AlertDialog.Builder hDialogBuilder = new AlertDialog.Builder(view.getContext());
                final EditText userInput = (EditText) house_dialogue.findViewById(R.id.house_text);
                hDialogBuilder.setView(house_dialogue);
                hDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("ОК",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Base base = new Base(HomeFragment.this.getContext());
                                        base.addC(getDate(), "Дом", Integer.valueOf(userInput.getText().toString().trim()));
                                        dailyBase.dayExpenses(getDate(), Integer.valueOf(userInput.getText().toString().trim()));
//                                        centreTextView.setText(userInput.getText());
                                    }
                                })
                        .setNegativeButton("Отмена",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                });

                AlertDialog alertDialog = hDialogBuilder.create();
                alertDialog.show();
            }
        });

        ImageButton education_button = (ImageButton) view.findViewById(R.id.education_btn);
        education_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                LayoutInflater li_edu = LayoutInflater.from(view.getContext());
                View education_dialogue = li_edu.inflate(R.layout.education_dialogue, null);
                AlertDialog.Builder eduDialogBuilder = new AlertDialog.Builder(view.getContext());
                final EditText userInput = (EditText) education_dialogue.findViewById(R.id.education_text);
                eduDialogBuilder.setView(education_dialogue);
                eduDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("ОК",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Base base = new Base(HomeFragment.this.getContext());
                                        base.addC(getDate(), "Учеба", Integer.valueOf(userInput.getText().toString().trim()));
                                        dailyBase.dayExpenses(getDate(), Integer.valueOf(userInput.getText().toString().trim()));
//                                        centreTextView.setText(userInput.getText());
                                    }
                                })
                        .setNegativeButton("Отмена",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                });

                AlertDialog alertDialog = eduDialogBuilder.create();
                alertDialog.show();
            }
        });

        ImageButton purchases_button = (ImageButton) view.findViewById(R.id.purchases_btn);
        purchases_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                LayoutInflater li_pur = LayoutInflater.from(view.getContext());
                View purchases_dialogue = li_pur.inflate(R.layout.purchase_dialogue, null);
                AlertDialog.Builder pDialogBuilder = new AlertDialog.Builder(view.getContext());
                final EditText userInput = (EditText) purchases_dialogue.findViewById(R.id.purchases_text);
                pDialogBuilder.setView(purchases_dialogue);
                pDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("ОК",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Base base = new Base(HomeFragment.this.getContext());
                                        base.addC(getDate(), "Непредвиденные расходы", Integer.valueOf(userInput.getText().toString().trim()));
                                        dailyBase.dayExpenses(getDate(), Integer.valueOf(userInput.getText().toString().trim()));
//                                        centreTextView.setText(userInput.getText());
                                    }
                                })
                        .setNegativeButton("Отмена",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                });

                AlertDialog alertDialog = pDialogBuilder.create();
                alertDialog.show();
            }
        });

        ImageButton others_button = (ImageButton) view.findViewById(R.id.others_btn);
        others_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                LayoutInflater li_others = LayoutInflater.from(view.getContext());
                View others_dialogue = li_others.inflate(R.layout.others_dialogue, null);
                AlertDialog.Builder othDialogueBuilder = new AlertDialog.Builder(view.getContext());
                final EditText userInput = (EditText) others_dialogue.findViewById(R.id.others_text);
                othDialogueBuilder.setView(others_dialogue);
                othDialogueBuilder
                        .setCancelable(false)
                        .setPositiveButton("ОК",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Base base = new Base(HomeFragment.this.getContext());
                                        base.addC(getDate(), "Другое", Integer.valueOf(userInput.getText().toString().trim()));
                                        dailyBase.dayExpenses(getDate(), Integer.valueOf(userInput.getText().toString().trim()));
//                                        centreTextView.setText(userInput.getText());
                                    }
                                })
                        .setNegativeButton("Отмена",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                });

                AlertDialog alertDialog = othDialogueBuilder.create();
                alertDialog.show();
            }
        });
        dayProgressBar = view.findViewById(R.id.dayAim);
        weekProgressBar = view.findViewById(R.id.weekAim);
        monthProgressBar = view.findViewById(R.id.MonthAim);


        textView = view.findViewById(R.id.others_text);

        addDayAim = view.findViewById(R.id.add_food_aim);

        addDayAim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater li_add_new_food_aim = LayoutInflater.from(view.getContext());
                View food_progress_dialogue = li_add_new_food_aim.inflate(R.layout.food_progress_dialogue, null);
                AlertDialog.Builder fProgressBuilder = new AlertDialog.Builder(view.getContext());
                final EditText userInput = (EditText) food_progress_dialogue.findViewById(R.id.food_max_text);
                final EditText userInput2 = (EditText) food_progress_dialogue.findViewById(R.id.food_aim_text);
                fProgressBuilder.setView(food_progress_dialogue);
                fProgressBuilder
                        .setCancelable(false)
                        .setPositiveButton("ОК",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dayProgressBar.setMax(Integer.parseInt(userInput.getText().toString()));
                                        Toast.makeText(getContext(), "Максимальная цель теперь: " + dayProgressBar.getMax(), Toast.LENGTH_SHORT).show();
                                    }
                                })
                        .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog alertDialog = fProgressBuilder.create();
                alertDialog.show();
            }
        });

        dayProgressBar.setProgress(100);
        addWeekAim = view.findViewById(R.id.add_trans_aim);
        addWeekAim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



        return view;
    }
    public String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String cd = dateFormat.format(date);
        return cd;
    }




}