package com.example.easytransportation.easyriderapp;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.example.easytransportation.easyriderapp.Model.User;

public class ListOnline extends AppCompatActivity {


    private EditText mSearchField;
    private Button mSearchBtn;

    private RecyclerView mResultList;

    private DatabaseReference mUserDatabase;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_online);

        mUserDatabase = FirebaseDatabase.getInstance().getReference("Users");


        mSearchField = (EditText) findViewById(R.id.search_field);
        mSearchBtn = (Button) findViewById(R.id.search_btn);

        mResultList = (RecyclerView) findViewById(R.id.result_list);
        mResultList.setHasFixedSize(true);
        mResultList.setLayoutManager(new LinearLayoutManager(this));




        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String searchText = mSearchField.getText().toString();

                firebaseUserSearch(searchText);

            }
        });
    }

    private void firebaseUserSearch(String searchText) {

        Query firebaseSearchQuery = mUserDatabase.orderByChild("name").startAt(searchText).endAt(searchText + "\uf8ff");

        FirebaseRecyclerAdapter<User, UsersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<User, UsersViewHolder>(

                User.class,
                R.layout.list_layout,
                UsersViewHolder.class,
                firebaseSearchQuery

        ) {


            @Override
            protected void populateViewHolder(UsersViewHolder viewHolder, User model, int position) {

                viewHolder.setDetails(getApplicationContext(), model.getName(), model.getStatus(), model.getImage(), model.getTrucknumber()
                        ,model.getDrivername(),model.getFromcity(),model.getTocity());

            }
        };

        mResultList.setAdapter(firebaseRecyclerAdapter);

    }


    // View Holder Class

    public static class UsersViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public UsersViewHolder(View itemView) {
            super(itemView);

            mView = itemView;

        }

        public void setDetails(Context ctx, String userName, String userStatus, String userImage, String truckNumber,String driverName,String fromCity,String toCity){

            TextView user_name = (TextView) mView.findViewById(R.id.name_text);
            TextView user_status = (TextView) mView.findViewById(R.id.status_text);
            ImageView user_image = (ImageView) mView.findViewById(R.id.profile_image);
            TextView truck_number = (TextView) mView.findViewById(R.id.truck_number_text);
            TextView driver_name = (TextView) mView.findViewById(R.id.driver_name_text);
            TextView from_city = (TextView) mView.findViewById(R.id.from_city_text);
            TextView to_city = (TextView) mView.findViewById(R.id.to_city_text);



            user_name.setText(userName);
            user_status.setText(userStatus);
            truck_number.setText(truckNumber);
            driver_name.setText(driverName);
            from_city.setText(fromCity);
            to_city.setText(toCity);

            Glide.with(ctx).load(userImage).into(user_image);
        }




    }



}

