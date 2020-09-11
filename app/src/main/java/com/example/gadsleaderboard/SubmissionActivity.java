package com.example.gadsleaderboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gadsleaderboard.models.UserDetails;
import com.example.gadsleaderboard.services.ServiceBuilder;
import com.example.gadsleaderboard.services.SubmitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SubmissionActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "SubmissionActivity";

    private static Retrofit.Builder sBuilder = new Retrofit.Builder().baseUrl("https://docs.google.com/forms/d/e/")
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit sRetrofit = sBuilder.build();

    private EditText userFirstName ;
    private EditText userLastName;
    private EditText userEmail;
    private EditText userLink;
    private Context mContext = this;

    private Toolbar mToolbar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission);

        mToolbar = findViewById(R.id.toolbar2);

        setSupportActionBar(mToolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

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
       // SubmitService submitService = sRetrofit.create(SubmitService.class);


        Call<Void> createRequest = submitService.createUserEntry(newUser.firstName, newUser.lastName, newUser.email, newUser.linkToProject);

        createRequest.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> request, Response<Void> response) {
                Toast.makeText(mContext, "Successful Creation", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> request, Throwable t) {
                Toast.makeText(mContext, "Failed to create item", Toast.LENGTH_SHORT).show();

            }
        });







    }


}
