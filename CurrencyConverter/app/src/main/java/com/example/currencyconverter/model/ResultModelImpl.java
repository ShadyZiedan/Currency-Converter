package com.example.currencyconverter.model;

import android.util.Log;

import com.example.currencyconverter.arch.ResultsMVPContract;
import com.example.currencyconverter.http.CBRService;
import com.example.currencyconverter.http.ValCurs;
import com.example.currencyconverter.http.Valute;

import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class ResultModelImpl implements ResultsMVPContract.ResultModel {

    private OnRequestFinishedListener listener;

    private List<Valute> valuteList;



    @Override
    public void requestResults(String date, final OnRequestFinishedListener listener) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.cbr.ru/")
                .addConverterFactory(SimpleXmlConverterFactory.createNonStrict(
                        new Persister(new AnnotationStrategy())
                ))
                .build();

        CBRService service = retrofit.create(CBRService.class);


        Call<ValCurs> call = service.getValCurs(date);
        call.enqueue(new Callback<ValCurs>() {
            @Override
            public void onResponse(Call<ValCurs> call, Response<ValCurs> response) {

                valuteList = response.body().getValute();

                listener.onSuccess(valuteList);

            }

            @Override
            public void onFailure(Call<ValCurs> call, Throwable t) {
                listener.onFailed();

            }
        });

    }

    public String getTestResultUSD(String date){

        requestResults(date, null);
        for (Valute valute: valuteList) {
            if (valute.getCharCode().equals("USD")){
                return valute.getValue();
            }

        }

        return "";

    }


}
