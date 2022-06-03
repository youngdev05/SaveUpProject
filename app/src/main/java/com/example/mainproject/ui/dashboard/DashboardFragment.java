package com.example.mainproject.ui.dashboard;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.mainproject.R;
import com.example.mainproject.base.Base;
import com.example.mainproject.base.DailyBase;
import com.example.mainproject.databinding.FragmentDashboardBinding;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;
    private PieChart pieChart;
    private BarChart barChart;
    private RadarChart radarChart;
    Base myBase;
    DailyBase dailyBase;
    ArrayList<PieEntry> pie;
    ArrayList<BarEntry> bar;
    ArrayList<RadarEntry> radar;
    Legend pieChartLegend;
    Legend barChartLegend;
    ArrayList<BarExpenses> barExpensesArrayList;
    ArrayList<String> dates;



    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

            myBase = new Base(getContext());
            dailyBase = new DailyBase(getContext());
            barExpensesArrayList = dailyBase.getDailyExpenses();
        { //Pie chart is below
            pieChart = root.findViewById(R.id.pieChart);
            pie = myBase.getAllPieExpenses();
            PieDataSet pieDataSet = new PieDataSet(pie, "pie");
            pieDataSet.setColors(myBase.getColors());
            PieData pieData = new PieData(pieDataSet);
            pieChart.setData(pieData);
//            pieChartLegend.setEnabled(false);

            pieChart.animateY(600, Easing.EaseInOutQuad);
            pieChart.getDescription().setEnabled(true);
            pieChart.setCenterText(String.valueOf(myBase.sumExpenses()));
            pieChart.setCenterTextSize(24);
            pieChart.invalidate();


        }

        { //Bar chart is below
            dates = new ArrayList<>();
            barChart = root.findViewById(R.id.barChart);
            bar = new ArrayList<>();

            for (int i = 0; i < barExpensesArrayList.size(); i++) {
                String date = barExpensesArrayList.get(i).getDate();
                int expenses= barExpensesArrayList.get(i).getExpenses();
                bar.add(new BarEntry(i, expenses));
                dates.add(date);
            }


            BarDataSet barDataSet = new BarDataSet(bar, "Ответы");
            barDataSet.setColors(Color.BLUE);
            barDataSet.setValueTextColor(Color.BLACK);
            barDataSet.setValueTextSize(16f);

            BarData barData = new BarData(barDataSet);
            ValueFormatter formatter = new ValueFormatter() {
                @Override
                public String getFormattedValue(float value) {
                    return "" + ((int) value);
                }
            };
            barData.setValueFormatter(formatter);
            barChart.setFitBars(true);
            barChart.setData(barData);
            barChart.getDescription().setText("");


            XAxis xAxis = barChart.getXAxis();
            xAxis.setValueFormatter(new IndexAxisValueFormatter(dates));
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setDrawAxisLine(false);
            xAxis.setDrawGridLines(false);
            xAxis.setGranularity(1f);
            xAxis.setLabelRotationAngle(300);
            barChart.getAxisRight().setEnabled(false);


            YAxis left = barChart.getAxisLeft();
            left.setGranularity(1);
            barChart.animateY(500);


        }


        return root;
    }


}