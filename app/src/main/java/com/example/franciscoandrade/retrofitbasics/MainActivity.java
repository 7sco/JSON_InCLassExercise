package com.example.franciscoandrade.retrofitbasics;

import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.franciscoandrade.retrofitbasics.Exercise2.RootObj;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ArrayList<DogsModel> lista= new ArrayList<>();
    DogsAdapter dogsAdapter;
    RecyclerView recyclerContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Peticion().execute();
        recyclerContainer= (RecyclerView)findViewById(R.id.recyclerContainer);
        //Timer
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                dogsAdapter = new DogsAdapter(lista, getApplicationContext());
                recyclerContainer.setAdapter(dogsAdapter);

            }
        }, 1000);

        GridLayoutManager gridLayoutManager= new GridLayoutManager(this, 2);
        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerContainer.setLayoutManager(gridLayoutManager);
    }

    public class Peticion extends AsyncTask<Void, String, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            String url= "https://dog.ceo/";
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ServiceAPI service = retrofit.create(ServiceAPI.class);

            Call<RootObject> response = service.getResponseGet();

            response.enqueue(new Callback<RootObject>() {
                @Override
                public void onResponse(Call<RootObject> call, Response<RootObject> response) {
                    List<String> list= response.body().getMessage();
                    for (int i=0; i <list.size(); i++){
                        lista.add(new DogsModel(response.body().getMessage().get(i).toString()));
                    }
                }
                @Override
                public void onFailure(Call<RootObject> call, Throwable t) {
                    Log.d("FAIL==", "onFailure: ");
                }
            });
            return null;
        }
        @Override
        protected void onProgressUpdate(String... values) {
        }
        @Override
        protected void onPostExecute(Void aVoid) {
        }
    }
}
