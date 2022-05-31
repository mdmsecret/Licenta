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

        // creating a new dbhandler class
        // and passing our context to it.
        dbHandler = new DBHandler(MainPage.this);
        /*findEventButton=findViewById(R.id.idFindEventButton);

        findEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPage.this, Find_events.class);// New activity
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

            }
        });*/

        createButton=findViewById(R.id.idCreateEventButton);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPage.this, CreateEventActivity.class);// New activity
                //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                //finish();

            }
        });

        settingsAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPage.this, EventSettingsActivity.class);// New activity
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

            }
        });
        flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);

        array = new ArrayList<>();
        ArrayList<String> test=new ArrayList<>();
       /* array.add(new Data("https://www.google.com/url?sa=i&url=https%3A%2F%2Fen.wikipedia.org%2Fwiki%2FDuck_test&psig=AOvVaw0xK5HyVaq_hzeJvl9Xlh3Z&ust=1646561677444000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCPDP2LDervYCFQAAAAAdAAAAABAD", "Alexis Sanchez, Arsenal forward. Wanna chat with me ?. \n" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."));
        array.add(new Data("http://www.delaroystudios.com/images/2.jpg", "Christano Ronaldo, Real Madrid star. Wanna chat with me ? \n" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."));
        array.add(new Data("http://www.delaroystudios.com/images/3.jpg", "Lionel Messi, Barcelona Best player. Wanna chat with me ? \n" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."));
        array.add(new Data("http://www.delaroystudios.com/4.jpg", "David Beckham. Wanna chat with me ? \n" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."));
        array.add(new Data("http://www.delaroystudios.com/images/5.jpg", "Sergio Arguerio. Wanna chat with me ? \n" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."));
        array.add(new Data("http://www.delaroystudios.com/images/6.jpg", "Sergio Ramos. Wanna chat with me ? \n" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."));
        array.add(new Data("http://www.delaroystudios.com/images/7.jpg", "Robert Lewandoski. Wanna chat with me ? \n" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."));
*/
        dbEventHandler= new DBEventHandler(MainPage.this);
        array = dbEventHandler.readEvent();
        //myAppAdapter = new Find_events.MyAppAdapter(array, Find_events.this);
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
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
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


        // Optionally add an OnItemClickListener
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
                // configure view holder
                viewHolder = new MainPage.ViewHolder();
                viewHolder.DataText = (TextView) rowView.findViewById(R.id.title);
                viewHolder.DataText2 = (TextView) rowView.findViewById(R.id.owner);
                viewHolder.DataText3 = (TextView) rowView.findViewById(R.id.time);
                viewHolder.DataText4 = (TextView) rowView.findViewById(R.id.hour);
                viewHolder.DataText5 = (TextView) rowView.findViewById(R.id.place);
                viewHolder.DataText6 = (TextView) rowView.findViewById(R.id.description);

                viewHolder.background = (FrameLayout) rowView.findViewById(R.id.background);
                viewHolder.cardImage = (ImageView) rowView.findViewById(R.id.cardImage);
                rowView.setTag(viewHolder);

            } else {
                viewHolder = (MainPage.ViewHolder) convertView.getTag();
            }
            String l1=new String("afasd \n");
            String l2=new String("afadialsd");

            viewHolder.DataText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            viewHolder.DataText.setText(parkingList.get(position).getTitle());
            viewHolder.DataText2.setText(parkingList.get(position).getOwner());
            viewHolder.DataText3.setText(parkingList.get(position).getDate());
            viewHolder.DataText4.setText(parkingList.get(position).getHour());
            viewHolder.DataText5.setText(parkingList.get(position).getPlace());
            //viewHolder.DataText6.setText(parkingList.get(position).getDescription());
            viewHolder.DataText6.setText("dsalkjdaslkdhahduiwahduiwahduiawhfuefgahgruighuisrehgurieshguirenguirnugnrteugregnuerz");

            //viewHolder.cardImage.setImageResource(R.drawable.shape_for_image);

            //viewHolder.DataText.autofill((SparseArray<AutofillValue>) parkingList);

            //Glide.with(Find_events.this).load("http://via.placeholder.com/300.png").into(viewHolder.cardImage);

            return rowView;
        }


    }
}
