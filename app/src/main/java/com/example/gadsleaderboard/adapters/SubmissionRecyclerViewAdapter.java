package com.example.gadsleaderboard.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gadsleaderboard.R;
import com.example.gadsleaderboard.models.UserDetails;

import java.util.ArrayList;

public class SubmissionRecyclerViewAdapter extends RecyclerView.Adapter<SubmissionRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "SubmissionRecyclerViewA";

    private Context mContext;
    private ArrayList<UserDetails> mUserDetails ;

    public SubmissionRecyclerViewAdapter(Context context, ArrayList<UserDetails> mUserDetails) {
        this.mContext = context;
        this.mUserDetails = mUserDetails;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_submission, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Log.d(TAG, "onBindViewHolder: called");

        UserDetails userDetails = mUserDetails.get(position);
        holder.bind(userDetails);


    }

    @Override
    public int getItemCount() {
        return mUserDetails != null? mUserDetails.size():0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        EditText firstName ;
        EditText lastName ;
        EditText email;
        EditText githubLink;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            firstName = itemView.findViewById(R.id.first_name);
            lastName = itemView.findViewById(R.id.last_name);
            email = itemView.findViewById(R.id.email);
            githubLink = itemView.findViewById(R.id.github_link);
        }

        public void bind (UserDetails userDetails){
            firstName.setText(userDetails.firstName);
            lastName.setText(userDetails.lastName);
            email.setText(userDetails.email);
            githubLink.setText(userDetails.linkToProject);


        }
    }
}
