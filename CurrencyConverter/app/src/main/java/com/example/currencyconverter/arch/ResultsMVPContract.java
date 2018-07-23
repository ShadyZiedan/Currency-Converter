package com.example.currencyconverter.arch;

import com.example.currencyconverter.http.ValCurs;
import com.example.currencyconverter.http.Valute;

import java.util.List;

public interface ResultsMVPContract {

    interface ResultView{
        void showProgress(boolean showProgress);
        void showResults(List<Valute> valuteList);
        void showErrorLoading();
        String getDate();
    }

    interface ResultPresenter{
        void sendRequest(String date);
    }

    interface ResultModel {

        interface OnRequestFinishedListener{
            void onFailed();
            void onCanceled();
            void onSuccess(List<Valute> valuteList);
        }

        void requestResults(String date, OnRequestFinishedListener listener);
    }
}
