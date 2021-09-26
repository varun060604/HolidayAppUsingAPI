package com.varun.holidayapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainPage extends AppCompatActivity {

    TextView textViewResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        textViewResults = findViewById(R.id.TextViewResult);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://date.nager.at/api/v3/PublicHolidays/2017/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonHolder jsonHolder = retrofit.create(JsonHolder.class);

        Call<List<HolidayPost>> call = jsonHolder.getComments();

        call.enqueue(new Callback<List<HolidayPost>>() {
            @Override
            public void onResponse(Call<List<HolidayPost>> call, Response<List<HolidayPost>> response) {
                if(!response.isSuccessful()){
                    textViewResults.setText("Code"+response.code());
                    return;
                }

                List<HolidayPost> comments = response.body();
                for (HolidayPost comment: comments){
                    String abc = "";
                    abc+= "\n\n" + "Date:   " + comment.getdate() + "\n";
                    abc+= "Local Name:   " + comment.getlocalName() + "\n";
                    abc+= "Global Name:   " + comment.getglobalName() + "\n";
                    abc+= "Country Code:   " + comment.getcountryCode() + "\n";
                    abc+= "Launch Year:   " + comment.getlaunchYear() + "\n";

                    textViewResults.append(abc);
                }

            }

            @Override
            public void onFailure(Call<List<HolidayPost>> call, Throwable t) {
                textViewResults.setText(t.getMessage());
            }
        });

    }
}