package com.example.currencyconverter.view;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.currencyconverter.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private String dateForApi;
    private Calendar c = Calendar.getInstance();


    @BindView(R.id.tv_date) TextView dateTextView;
    @BindView(R.id.btn_pick_date) Button pickDateButton;
    @BindView(R.id.btn_get_result) Button getResultButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        getResultButton.setEnabled(false);


    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String pickedDate = DateFormat.getDateInstance().format(c.getTime());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        dateForApi = dateFormat.format(c.getTime());


        dateTextView.setText(pickedDate);
        getResultButton.setEnabled(true);
    }

//    @Override
//    public String getDate() {
//        return dateForApi;
//    }


    @OnClick(R.id.btn_pick_date)
    public void showDatePicker() {
        DatePickerFragment datePicker = new DatePickerFragment();
        Bundle args = new Bundle();
        args.putInt("Year", c.get(Calendar.YEAR));
        args.putInt("Month", c.get(Calendar.MONTH));
        args.putInt("Day", c.get(Calendar.DAY_OF_MONTH));

        datePicker.setArguments(args);

        datePicker.show(getSupportFragmentManager(), "Date Picker");
    }


    @OnClick(R.id.btn_get_result)
    public void showResultsActivity() {

        Intent intent = new Intent(this, ResultsActivity.class);
        intent.putExtra("date",dateForApi);
        startActivity(intent);

    }


}
