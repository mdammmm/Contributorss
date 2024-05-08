package com.example.contributorss;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ProgressBar mProgressBar;
    GridView mGridView;
    private UsersAdapter usersAdapter;

    List<users> mUsers;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mProgressBar.setVisibility(View.INVISIBLE);

        mUsers = new ArrayList<>();

        mGridView = (GridView) findViewById(R.id.gridView);


        mProgressBar.setVisibility(View.VISIBLE);
        GitHubService gitHubService = GitHubService.retrofit.create(GitHubService.class);
        final Call<List<users>> call = gitHubService.getData();
        call.enqueue(new Callback<List<users>>() {
                         @Override
                         public void onResponse(Call<List<users>> call, Response<List<users>> response) {
                             if (response.isSuccessful()) {
                                 UsersAdapter usersAdapter = new UsersAdapter(MainActivity.this, R.layout.item_users, mUsers);
                                 mGridView.setAdapter(usersAdapter);
                                 mUsers.addAll(response.body());
                                 //mGridView.getAdapter().notifyAll();
                                 mProgressBar.setVisibility(View.INVISIBLE);
                             } else {
                                 ResponseBody errorBody = response.errorBody();
                                 try {
                                     Toast.makeText(MainActivity.this, errorBody.string(),
                                             Toast.LENGTH_SHORT).show();
                                     mProgressBar.setVisibility(View.INVISIBLE);
                                 } catch (IOException e) {
                                     e.printStackTrace();
                                 }
                             }
                         }

                         @Override
                         public void onFailure(Call<List<users>> call, Throwable throwable) {
                             Toast.makeText(MainActivity.this, "Что-то пошло не так " + throwable.getMessage(),
                                     Toast.LENGTH_SHORT).show();
                             mProgressBar.setVisibility(View.INVISIBLE);
                         }
                     }
        );

    }}
