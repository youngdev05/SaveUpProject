package com.example.mainproject.ui.aims;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mainproject.MainActivity;
import com.example.mainproject.R;
import com.example.mainproject.base.AimBase;
import com.kofigyan.stateprogressbar.StateProgressBar;

public class AdvancedAimActivity extends AppCompatActivity {

    AimBase ab;
    Button decrease, increase, delete;
    public static final String EXTRA_POS = "my_item_position";
    private AimBase aimBase;
    TextView MainAim, current, needed, ideaText;
    String aimTxt, currentString, neededString;
    String[] descriptionData = {"  Постановка\n        Цели", "25%", "50%", "75%" , "100%"};
    ProgressBar updateProgressbar;
    String currentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_aim);

        int my_item_position = (int) getIntent().getExtras().get(EXTRA_POS);
        {
            aimBase = new AimBase(AdvancedAimActivity.this);
            decrease = findViewById(R.id.decrease_button);
            increase = findViewById(R.id.increase_button);
            delete = findViewById(R.id.delete_btn);
            MainAim = findViewById(R.id.textView11);
            current = findViewById(R.id.current_in_activity);
            needed = findViewById(R.id.needed_in_activity);
            ideaText = findViewById(R.id.idea_text);

        }
        Intent intent = getIntent();

        aimTxt = intent.getStringExtra("aim");
        currentString = getIntent().getStringExtra("curr");
        neededString = getIntent().getStringExtra("need");
        currentId = getIntent().getStringExtra("id");


        MainAim.setText(aimTxt);
        current.setText(currentString);
        needed.setText("из " + neededString);

        int x = Integer.parseInt(neededString) / 365;
        int y = Integer.parseInt(neededString) / 52;

        StateProgressBar stateProgressBar = (StateProgressBar) findViewById(R.id.your_state_progress_bar_id);
        stateProgressBar.setStateDescriptionData(descriptionData);


        updateProgressbar = findViewById(R.id.update_progressBar);



        ideaText.setText(" Чтобы достичь цели Вам нужно откладывать примерно " + x + "  рублей каждый день в течение года или " + y + " каждую неделю");


        increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater li_others = LayoutInflater.from(AdvancedAimActivity.this);
                View others_dialogue = li_others.inflate(R.layout.others_dialogue, null);
                AlertDialog.Builder othDialogueBuilder = new AlertDialog.Builder(AdvancedAimActivity.this);
                final EditText userInput = (EditText) others_dialogue.findViewById(R.id.others_text);
                othDialogueBuilder.setView(others_dialogue);
                othDialogueBuilder
                        .setCancelable(false)
                        .setPositiveButton("ОК",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        aimBase.updateAimBase(currentId, Integer.valueOf(userInput.getText().toString().trim()) +
                                                Integer.valueOf(current.getText().toString().trim()));
                                        Toast.makeText(AdvancedAimActivity.this, "DB Updated..", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(AdvancedAimActivity.this, MainActivity.class);
                                        startActivity(intent);
//


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



        decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater li_others = LayoutInflater.from(AdvancedAimActivity.this);
                View others_dialogue = li_others.inflate(R.layout.others_dialogue, null);
                AlertDialog.Builder othDialogueBuilder = new AlertDialog.Builder(AdvancedAimActivity.this);
                final EditText userInput = (EditText) others_dialogue.findViewById(R.id.others_text);
                othDialogueBuilder.setView(others_dialogue);
                othDialogueBuilder
                        .setCancelable(false)
                        .setPositiveButton("ОК",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        aimBase.updateAimBase(currentId,Integer.valueOf(current.getText().toString().trim()) -
                                                Integer.valueOf(userInput.getText().toString().trim()));
                                        Toast.makeText(AdvancedAimActivity.this, "DB Updated..", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(AdvancedAimActivity.this, MainActivity.class);
                                        startActivity(intent);
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
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater li_others = LayoutInflater.from(AdvancedAimActivity.this);
                View others_dialogue = li_others.inflate(R.layout.delete_dialogue, null);
                AlertDialog.Builder othDialogueBuilder = new AlertDialog.Builder(AdvancedAimActivity.this);
                othDialogueBuilder.setView(others_dialogue);
                othDialogueBuilder
                        .setCancelable(false)
                        .setPositiveButton("ОК",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        aimBase.deleteFromAimBase(currentId);
                                        Toast.makeText(AdvancedAimActivity.this, "DB Updated..", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(AdvancedAimActivity.this, MainActivity.class);
                                        startActivity(intent);
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


        updateProgressbar.setMax(Integer.parseInt(neededString));
        updateProgressbar.setProgress(Integer.parseInt(currentString));

        stateProgressBar.setCurrentStateNumber(stateProgress(Integer.parseInt(currentString), Integer.parseInt(neededString)));

    }




    public StateProgressBar.StateNumber stateProgress(int current, int needed){
        float x = (float) current * 100 / needed;
        if (x >= 25 && x < 50){
            return StateProgressBar.StateNumber.TWO;
        } else if (x >= 50 && x < 75){
            return StateProgressBar.StateNumber.THREE;
        }else if (x >= 75 && x < 100){
            return StateProgressBar.StateNumber.FOUR;
        }else if (x >= 100){
            return StateProgressBar.StateNumber.FIVE;
        }else {
            return StateProgressBar.StateNumber.ONE;
        }
    }


}