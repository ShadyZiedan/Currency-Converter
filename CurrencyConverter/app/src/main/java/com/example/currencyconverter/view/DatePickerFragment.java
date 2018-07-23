package com.example.currencyconverter.view;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import java.util.Calendar;
import java.util.Date;

public class DatePickerFragment extends DialogFragment {

    private Calendar calendar;

    public DatePickerFragment() {
        //Empty Constructor
    }



    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

//        calendar = Calendar.getInstance();
//
//        int year = calendar.get(Calendar.YEAR);
//        int month = calendar.get(Calendar.MONTH);
//        int day = calendar.get(Calendar.DAY_OF_MONTH);

        int year = this.getArguments().getInt("Year");
        int month = this.getArguments().getInt("Month");
        int day = this.getArguments().getInt("Day");

        DatePickerDialog dialog = new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener) getActivity(), year, month, day);
        dialog.getDatePicker().setMaxDate(new Date().getTime());

        return dialog;
    }
}
