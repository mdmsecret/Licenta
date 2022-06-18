package com.example.licenta;


import static com.example.licenta.MainActivity.getCurrentUser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.Viewholder> implements SwipeFlingAdapterView.OnItemClickListener {

    private final Context context;
    private final ArrayList<ListModel> courseModelArrayList;
    private DBEventHandler dbEventHandler;
    // Constructor
    public ListAdapter(Context context, ArrayList<ListModel> courseModelArrayList) {
        this.context = context;
        this.courseModelArrayList = courseModelArrayList;
    }

    @NonNull
    @Override
    public ListAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.Viewholder holder, int position) {

        ListModel model = courseModelArrayList.get(position);
        holder.courseNameTV.setText(model.getCardTitle());
        holder.courseRatingTV.setText("" + model.getCardTime());
        holder.courseIV.setText(model.getCardHour());
        holder.notInterestedAnymore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<String> participants_new=model.getEvent().getParticipants();
                String token=model.getEvent().getToken();
                String currUser=getCurrentUser();
                dbEventHandler= new DBEventHandler(context);
                Toast.makeText(context, "Eveniment elemininat", Toast.LENGTH_SHORT).show();
                participants_new.remove(currUser
                );
                dbEventHandler.updateParticipants(participants_new,token);


            }


        });

    }

    @Override
    public int getItemCount() {

        return courseModelArrayList.size();
    }

    @Override
    public void onItemClicked(int i, Object o) {

    }


    public class Viewholder extends RecyclerView.ViewHolder {

        private final TextView courseNameTV;
        private final TextView courseRatingTV;
        private final TextView courseIV;
        private final Button notInterestedAnymore;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            courseIV = itemView.findViewById(R.id.idIVCourseImage);
            courseNameTV = itemView.findViewById(R.id.idTVCourseName);
            courseRatingTV = itemView.findViewById(R.id.idCardviewTiltle);
            notInterestedAnymore=itemView.findViewById(R.id.notInterestedAnymoreButton);

        }
    }
}