package com.example.licenta;

import static com.example.licenta.Find_events.myAppAdapter;
import static com.example.licenta.MainActivity.getCurrentUser;
import static com.example.licenta.MainActivity.setCurrentUser;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.List;

public class MainPage extends AppCompatActivity {
    private Button settingsAccountButton,createButton,findEventButton;
    private DBHandler dbHandler;
    public static MainPage.MyAppAdapterNew myAppAdapterNew;
    public static MainPage.ViewHolder viewHolder;
    private ArrayList<Event> array;
    private SwipeFlingAdapterView flingContainer;
    private DBEventHandler dbEventHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);

        settingsAccountButton = findViewById(R.id.idSettingsButton);


        dbHandler = new DBHandler(MainPage.this);


        createButton=findViewById(R.id.idCreateEventButton);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPage.this, CreateEventActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                //finish();

            }
        });

        settingsAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPage.this, OptionsActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                //finish();

            }
        });
        flingContainer = findViewById(R.id.frame);

        array = new ArrayList<>();
        ArrayList<String> test=new ArrayList<>();

        dbEventHandler= new DBEventHandler(MainPage.this);
        array = dbEventHandler.readEvent();

        myAppAdapterNew = new MainPage.MyAppAdapterNew(array, MainPage.this);
        flingContainer.setAdapter(myAppAdapterNew);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {

            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                array.remove(0);
                myAppAdapterNew.notifyDataSetChanged();

            }

            @Override
            public void onRightCardExit(Object dataObject) {

                ArrayList<String> participants=array.get(0).getParticipants();
                String token=array.get(0).getToken();
                String currUser=getCurrentUser();

                participants.add(currUser);
                dbEventHandler.updateParticipants(participants,token);
                array.remove(0);
                myAppAdapterNew.notifyDataSetChanged();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {

            }

            @Override
            public void onScroll(float scrollProgressPercent) {

                View view = flingContainer.getSelectedView();
                view.findViewById(R.id.background).setAlpha(0);
                view.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
                view.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
            }
        });



        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {

                View view = flingContainer.getSelectedView();
                view.findViewById(R.id.background).setAlpha(0);

                myAppAdapterNew.notifyDataSetChanged();
            }
        });
    }

    public static class ViewHolder {
        public static FrameLayout background;
        public TextView DataText;
        public TextView DataText2;
        public TextView DataText3;
        public TextView DataText4;
        public TextView DataText5;
        public TextView DataText6;
        public ImageView cardImage;



    }

    public class MyAppAdapterNew extends BaseAdapter {


        public List<Event> parkingList;
        public Context context;

        private MyAppAdapterNew(List<Event> apps, Context context) {
            this.parkingList = apps;
            this.context = context;
        }

        @Override
        public int getCount() {
            return parkingList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @SuppressLint("CutPasteId")
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            View rowView = convertView;


            if (rowView == null) {

                LayoutInflater inflater = getLayoutInflater();
                rowView = inflater.inflate(R.layout.item, parent, false);

                viewHolder = new MainPage.ViewHolder();
                viewHolder.DataText = rowView.findViewById(R.id.title);
                viewHolder.DataText2 = rowView.findViewById(R.id.owner);
                viewHolder.DataText3 = rowView.findViewById(R.id.time);
                viewHolder.DataText4 = rowView.findViewById(R.id.hour);
                viewHolder.DataText5 = rowView.findViewById(R.id.place);
                viewHolder.DataText6 = rowView.findViewById(R.id.description);

                ViewHolder.background = rowView.findViewById(R.id.background);
                viewHolder.cardImage = rowView.findViewById(R.id.cardImage);
                rowView.setTag(viewHolder);

            } else {
                viewHolder = (MainPage.ViewHolder) convertView.getTag();
            }


            viewHolder.DataText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            viewHolder.DataText.setText(parkingList.get(position).getTitle());
            viewHolder.DataText2.setText(parkingList.get(position).getOwner());
            viewHolder.DataText3.setText(parkingList.get(position).getDate());
            viewHolder.DataText4.setText(parkingList.get(position).getHour());
            viewHolder.DataText5.setText(parkingList.get(position).getPlace());

            viewHolder.DataText6.setText(parkingList.get(position).getDescription());



            return rowView;
        }


    }
}
