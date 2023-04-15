package com.example.assignment3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.assignment3.databinding.ActivityMainBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private static final String TAG = "Covid19TrackerActivity";
    int data = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        findCovid19();
    }

    private void findCovid19() {
        showLoading(true);

        Call <Covid19> client = API_Config.service().getDataCovid();
        client.enqueue(new Callback<Covid19>() {
            @Override
            public void onResponse(@NonNull Call<Covid19> call, @NonNull Response<Covid19> response) {
                showLoading(false);
                if (response.isSuccessful()) {
                    Covid19 result = response.body();
                    assert result != null;
                    Log.d(TAG, result.toString());
                    data = result.getCases();
                    setCovidData(result);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Covid19> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: " + t);
                showLoading(true);

            }
        });
    }

    private void setCovidData(Covid19 covid19Data) {
        binding.countCases.setText(String.valueOf(covid19Data.getCases()));
        binding.countRecoverCovid.setText(String.valueOf(covid19Data.getRecovered()));
        binding.countDeathCovid.setText(String.valueOf(covid19Data.getDeaths()));
    }

    private void showLoading(boolean isLoading) {
        if (isLoading) {
            binding.progressBar.setVisibility(View.VISIBLE);
        } else {
            binding.progressBar.setVisibility(View.GONE);
        }
    }
}