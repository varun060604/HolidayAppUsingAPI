package com.varun.holidayapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JsonHolder {

    @GET("AT")
    Call<List<HolidayPost>> getComments();

}
