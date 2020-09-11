package com.example.gadsleaderboard.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gadsleaderboard.R;
import com.example.gadsleaderboard.models.SkillLeader;

import java.util.ArrayList;

public class SkillRecyclerViewAdapter  extends RecyclerView.Adapter<SkillRecyclerViewAdapter.ViewHolder>{


    private static final String TAG = "SkillRecyclerViewAdapte";

    private Context mContext;
    private ArrayList<SkillLeader> mSkillLeaders;

    public SkillRecyclerViewAdapter(Context context, ArrayList<SkillLeader> mSkillLeaders) {
        this.mContext = context;
        this.mSkillLeaders = mSkillLeaders;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_skill_feed, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Log.d(TAG, "onBindViewHolder: called");

        SkillLeader skillLeader = mSkillLeaders.get(position);
        holder.bind(skillLeader);

    }

    @Override
    public int getItemCount() {
        return mSkillLeaders != null? mSkillLeaders.size(): 0 ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView mImageView;
        TextView name;
        TextView score;
        TextView country;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mImageView = itemView.findViewById(R.id.skill_image);
            name = itemView.findViewById(R.id.skill_name);
            score = itemView.findViewById(R.id.skill_score);
            country = itemView.findViewById(R.id.skill_country);
        }

        public void bind (SkillLeader skill){
            name.setText(skill.name);

            score.setText(String.valueOf(skill.score));
            country.setText(skill.country);
    }
}}
