package com.example.currencyconverter.http;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CBRService {
    @GET("scripts/XML_daily.asp")
    Call<ValCurs> getValCurs(@Query("date_req") String date);
}
