package com.abhi41.dagger2examle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.abhi41.dagger2examle.Adapter.HeroAdapter;
import com.abhi41.dagger2examle.Common.SharedPrefModule;
import com.abhi41.dagger2examle.Interfaces.Api;
import com.abhi41.dagger2examle.Interfaces.DaggerMyComponent;
import com.abhi41.dagger2examle.Interfaces.MyComponent;
import com.abhi41.dagger2examle.Model.HeroModel;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    EditText inUsername, inNumber;
    Button btnSave, btnGet;
    @Inject
    SharedPreferences sharedPreferences;
    @Inject
    Retrofit retrofit;

    private RecyclerView recycler_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGet = findViewById(R.id.btnGet);
        btnSave = findViewById(R.id.btnSave);
        inUsername = findViewById(R.id.inUsername);
        inNumber = findViewById(R.id.inNumber);

        recycler_view = findViewById(R.id.recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycler_view.setLayoutManager(layoutManager);
        recycler_view.setHasFixedSize(true);


        ((MyApplication)getApplication()).getnetworkModule().inject(this);


        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inUsername.setText(sharedPreferences.getString("username", "default"));
                inNumber.setText(sharedPreferences.getString("number", "12345"));
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor =sharedPreferences.edit();
                editor.putString("username", inUsername.getText().toString().trim());
                editor.putString("number", inNumber.getText().toString().trim());
                editor.apply();
            }
        });


        Api api = retrofit.create(Api.class);
        Call<List<HeroModel>> listCall = api.getHeroes();
        listCall.enqueue(new Callback<List<HeroModel>>() {
            @Override
            public void onResponse(Call<List<HeroModel>> call, Response<List<HeroModel>> response) {
                HeroAdapter adapter = new HeroAdapter(MainActivity.this,response.body());
                recycler_view.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<HeroModel>> call, Throwable t) {

            }
        });

    }
}
