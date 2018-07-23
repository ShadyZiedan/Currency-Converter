package com.example.currencyconverter.view;

import com.example.currencyconverter.R;
import com.example.currencyconverter.arch.ResultsMVPContract;
import com.example.currencyconverter.http.Valute;
import com.example.currencyconverter.presenters.ResultPresenterImpl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultsActivity extends AppCompatActivity implements ResultsMVPContract.ResultView{

    private ResultsMVPContract.ResultPresenter presenter;

    @BindView(R.id.progress_bar) ProgressBar progressBar;
    @BindView(R.id.results_layout) View resultsForm;
    @BindView(R.id.tv_intro_date) TextView introDateTextView;
    @BindView(R.id.tv_eur_value) TextView eurValueTextView;
    @BindView(R.id.tv_usd_value) TextView usdValueTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        ButterKnife.bind(this);



    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter = new ResultPresenterImpl(this);
        presenter.sendRequest(getDate());

    }

    @Override
    public void showProgress(boolean showProgress) {
        if (showProgress){
            progressBar.setVisibility(View.VISIBLE);
            resultsForm.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            resultsForm.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showResults(List<Valute> valuteList) {

        for (Valute valute: valuteList) {
            if (valute.getCharCode().equals("USD")){
                setUSDValue(valute.getValue());
            } else if (valute.getCharCode().equals("EUR")){
                setEURValue(valute.getValue());
            }
        }

        String intro = "Currency values in Rubles for the date: " + getDate();
        introDateTextView.setText(intro);
    }

    private void setEURValue(String value) {
        eurValueTextView.setText(value + " руб");
    }

    private void setUSDValue(String value) {
        usdValueTextView.setText(value + " руб");
    }

    @Override
    public void showErrorLoading() {
        Toast.makeText(this, "Error loading!", Toast.LENGTH_LONG).show();
    }

    @Override
    public String getDate() {
        return getIntent().getStringExtra("date");

    }
}
