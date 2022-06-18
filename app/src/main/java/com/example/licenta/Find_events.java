package com.example.licenta;

import static com.example.licenta.MainActivity.getCurrentUser;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.autofill.AutofillValue;
import android.widget.TableRow;
import android.widget.Toast;
import android.content.Context;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

//import com.delaroystudios.swipecards.R;
import com.bumptech.glide.Glide;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;



import java.util.ArrayList;

public class Find_events extends AppCompatActivity { public static MyAppAdapter myAppAdapter;
    public static ViewHolder viewHolder;
    private ArrayList<Event> array;
    private SwipeFlingAdapterView flingContainer;
    private DBEventHandler dbEventHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_events);

        flingContainer = findViewById(R.id.frame);

        array = new ArrayList<>();
        ArrayList<String> test=new ArrayList<>();

        dbEventHandler= new DBEventHandler(Find_events.this);
        array = dbEventHandler.readEvent();
        myAppAdapter = new MyAppAdapter(array, Find_events.this);
        flingContainer.setAdapter(myAppAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {

            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                array.remove(0);
                myAppAdapter.notifyDataSetChanged();

            }

            @Override
            public void onRightCardExit(Object dataObject) {
                String user=getCurrentUser();
                ArrayList<String> participants=array.get(0).getParticipants();
                String token=array.get(0).getToken();
                String currUser=getCurrentUser();

                participants.add(currUser);
                dbEventHandler.updateParticipants(participants,token);
                array.remove(0);
                myAppAdapter.notifyDataSetChanged();
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

                myAppAdapter.notifyDataSetChanged();
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

    public class MyAppAdapter extends BaseAdapter {


        public List<Event> parkingList;
        public Context context;

        private MyAppAdapter(List<Event> apps, Context context) {
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
                viewHolder = new ViewHolder();
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
                viewHolder = (ViewHolder) convertView.getTag();
            }


            viewHolder.DataText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            viewHolder.DataText.setText(parkingList.get(position).getTitle());
            viewHolder.DataText2.setText(parkingList.get(position).getOwner());
            viewHolder.DataText3.setText(parkingList.get(position).getDate());
            viewHolder.DataText4.setText(parkingList.get(position).getHour());
            viewHolder.DataText5.setText(parkingList.get(position).getPlace());
            //viewHolder.DataText6.setText(parkingList.get(position).getDescription());
            viewHolder.DataText6.setText("dsalkjdaslkdhahduiwahduiwahduiawhfuefgahgruighuisrehgurieshguirenguirnugnrteugregnuerz");



            return rowView;
        }
    }
}
