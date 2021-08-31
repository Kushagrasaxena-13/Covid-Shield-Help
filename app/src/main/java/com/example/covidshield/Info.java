package com.example.covidshield;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class Info extends AppCompatActivity {

    User user;

    ImageView close;


    TextView name,address,contact_no,city;
    CheckBox oxygen,bed,injection;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        name = findViewById(R.id.name);
        address = findViewById(R.id.address);
        contact_no = findViewById(R.id.contact_no);
        city= findViewById(R.id.city);
        oxygen = findViewById(R.id.oxygen);
        bed = findViewById(R.id.bed);
        injection = findViewById(R.id.injection);
        close =findViewById(R.id.close);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Info.this,MainActivity.class));
            }
        });



        Intent intent = getIntent();
        String str = intent.getStringExtra("1");
        String str2 = intent.getStringExtra("2");
        reference = FirebaseDatabase.getInstance().getReference("City").child(str).child(str2);
//        name.setText(user.getId());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//             name.setText(user.getFullname());
                String name1 = snapshot.child("fullname").getValue().toString();
                String contact_no1 = snapshot.child("contact_no").getValue().toString();
                String city1 = snapshot.child("city").getValue().toString();
                String address1 = snapshot.child("address").getValue().toString();
                String oxgyen1 = snapshot.child("oxygen").getValue().toString();
                String bed1 = snapshot.child("bed").getValue().toString();
                String injection1 = snapshot.child("injection").getValue().toString();


                name.setText(name1);
                contact_no.setText(contact_no1);
                city.setText(city1);
                address.setText(address1);



                if(oxgyen1.equals("1")){

                    oxygen.setVisibility(View.VISIBLE);
                    oxygen.setChecked(true);


                }

                else{
                    oxygen.setChecked(false);
                }

                if(bed1.equals("1")){
                    bed.setVisibility(View.VISIBLE);
                    bed.setChecked(true);
                }
                else{
                    bed.setChecked(false);
                }

                if(injection1.equals("1")){
                    injection.setVisibility(View.VISIBLE);
                    injection.setChecked(true);
                }
                else{
                    injection.setChecked(false);
                }



            //                if(user.getOxygen().equals("1")){
//                    oxygen.isChecked();
//                }
//                for(DataSnapshot snapshot1: snapshot.getChildren()){
//
//                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }




    }

