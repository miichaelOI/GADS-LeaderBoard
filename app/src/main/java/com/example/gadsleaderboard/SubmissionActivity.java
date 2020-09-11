package com.example.gadsleaderboard;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gadsleaderboard.adapters.MainRecyclerViewAdapter;
import com.example.gadsleaderboard.adapters.SubmissionRecyclerViewAdapter;
import com.example.gadsleaderboard.models.UserDetails;
import com.example.gadsleaderboard.services.ServiceBuilder;
import com.example.gadsleaderboard.services.SubmitService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmissionActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "SubmissionActivity";

    EditText userFirstName ;
    EditText userLastName;
    EditText userEmail;
    EditText userLink;
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.submit_toolbar);

        Button createUser = findViewById(R.id.submit_button1);
         userFirstName = findViewById(R.id.first_name);
         userLastName = findViewById(R.id.last_name);
         userEmail = findViewById(R.id.email);
         userLink = findViewById(R.id.github_link);

        createUser.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        UserDetails newUser = new UserDetails();
        newUser.setFirstName(userFirstName.getText().toString());
        newUser.setLastName(userLastName.getText().toString());
        newUser.setEmail(userEmail.getText().toString());
        newUser.setLinkToProject(userLink.getText().toString());

        SubmitService submitService = ServiceBuilder.buildService(SubmitService.class);
        Call<UserDetails> createRequest = submitService.createUserEntry(newUser.firstName, newUser.lastName, newUser.email, newUser.linkToProject);

        createRequest.enqueue(new Callback<UserDetails>() {
            @Override
            public void onResponse(Call<UserDetails> call, Response<UserDetails> response) {
                Toast.makeText(mContext, "Successful Creation", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<UserDetails> call, Throwable t) {
                Toast.makeText(mContext, "Failed to create item", Toast.LENGTH_SHORT).show();

            }
        });







    }
}
