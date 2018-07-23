package com.example.currencyconverter.presenters;

import android.util.Log;
import android.widget.Toast;

import com.example.currencyconverter.arch.ResultsMVPContract;
import com.example.currencyconverter.http.Valute;
import com.example.currencyconverter.model.ResultModelImpl;

import java.util.ArrayList;
import java.util.List;


public class ResultPresenterImpl implements ResultsMVPContract.ResultPresenter, ResultsMVPContract.ResultModel.OnRequestFinishedListener {

    private ResultsMVPContract.ResultView resultView;
    private ResultsMVPContract.ResultModel resultModel;

    public ResultPresenterImpl(ResultsMVPContract.ResultView resultView) {
        this.resultView = resultView;
        this.resultModel = new ResultModelImpl();
    }

    @Override
    public void sendRequest(String date) {
        resultModel.requestResults(date, this);

        resultView.showProgress(true);
    }

    @Override
    public void onFailed() {
        resultView.showErrorLoading();
    }

    @Override
    public void onCanceled() {

    }

    @Override
    public void onSuccess(List<Valute> valuteList) {

        resultView.showProgress(false);

        resultView.showResults(valuteList);
    }



}
