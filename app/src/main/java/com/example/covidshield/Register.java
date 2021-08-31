package com.example.covidshield;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Register extends AppCompatActivity {

    EditText username,name,email,password,city,phone_no,address;
    TextView register;
    ImageView close;
    TextView login;
    CheckBox oxygen,bed,injection;

    FirebaseAuth auth;
    DatabaseReference reference;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        close= findViewById(R.id.close);

        username = findViewById(R.id.username);
        name = findViewById(R.id.name);
        email= findViewById(R.id.email);
        password= findViewById(R.id.password);
        oxygen = findViewById(R.id.oxygen);
        bed = findViewById(R.id.bed);
        injection = findViewById(R.id.remdesivir);
        city = findViewById(R.id.city);
        phone_no = findViewById(R.id.contact);
        address = findViewById(R.id.Address);

        register = findViewById(R.id.sign_up);
        login = findViewById(R.id.link);

        auth= FirebaseAuth.getInstance();

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this,MainActivity.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this,Login.class);
                startActivity(intent);
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd = new ProgressDialog(Register.this);
                pd.setMessage("Please wait...");
                pd.show();

                String str_username = username.getText().toString();
                String str_name = name.getText().toString();
                String str_email = email.getText().toString();
                String str_password = password.getText().toString();
                String str_city = city.getText().toString().toUpperCase();
                String str_phone_no = phone_no.getText().toString();
                String str_address = address.getText().toString();

                if(TextUtils.isEmpty(str_username) || TextUtils.isEmpty(str_name) || TextUtils.isEmpty(str_email) || TextUtils.isEmpty(str_password) || TextUtils.isEmpty(str_city) || TextUtils.isEmpty(str_phone_no) || TextUtils.isEmpty(str_address)){
                    Toast.makeText(Register.this,"All fileds are required!!",Toast.LENGTH_SHORT).show();
                }
                else {
                    register(str_username,str_name,str_email,str_password,str_city,str_phone_no,str_address);

                }

            }
        });
    }

    private void register(String username, String name, String email, String password, String city, String contact_no , String address){
        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser firebaseUser= auth.getCurrentUser();
                            String userid = firebaseUser.getUid();

                            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("City").child(city).child(username);
                            reference = FirebaseDatabase.getInstance().getReference().child("User").child(userid);
                            DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference().child("City").child(city);

                            HashMap<String, Object> hashmap = new HashMap<>();
                            hashmap.put("id",userid);
                            hashmap.put("city",city);
//                            if(oxygen.isChecked()){
//                                hashmap.put("oxygen","oxygen");
//                            }
//                            if(bed.isChecked()){
//                                hashmap.put("bed","bed");
//                            }
//                            if(injection.isChecked()){
//                                hashmap.put("injection","injection");
//                            }
//                            hashmap.put("contact_no",contact_no);
                            hashmap.put("username",username);
                            hashmap.put("fullname",name);
//                            hashmap.put("bio","");
//                            hashmap.put("address",address);



                                hashmap.put("imageurl", "https://firebasestorage.googleapis.com/v0/b/meet-us-1313.appspot.com/o/anarchy-android-computer-cyber-wallpaper-preview.png?alt=media&token=a2bb6a9f-f418-4f6b-9184-7f856cc7a67c");





                                reference.setValue(hashmap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            pd.dismiss();

                                        }
                                    }
                                });




                            HashMap<String,Object> hashmap2= new HashMap<>();
                            hashmap2.put("id",userid);
                            hashmap2.put("city",city);
                            if(oxygen.isChecked()){
                                hashmap2.put("oxygen","1");
                            }
                            else{
                                hashmap2.put("oxygen","0");
                            }
                            if(bed.isChecked()){
                                hashmap2.put("bed","1");
                            }
                            else{
                                hashmap2.put("bed","0");
                            }
                            if(injection.isChecked()){
                                hashmap2.put("injection","1");

                            }

                            else{
                                hashmap2.put("injection","0");
                            }
                            hashmap2.put("contact_no",contact_no);
                            hashmap2.put("username",username);
                            hashmap2.put("fullname",name);
//                            hashmap2.put("bio","");
                            hashmap2.put("address",address);

                            ref.setValue(hashmap2).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Intent intent = new Intent(Register.this,Tick.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                    }
                                }
                            });

                            DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Cities").child(city);

                            HashMap<String,Object> hashMap3 = new HashMap<>();
                            hashMap3.put("city",city);
                            db.setValue(hashMap3).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                }
                            });

                        }
                        else{
                            pd.dismiss();
                            Toast.makeText(Register.this,"You can't register with this email or password..",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
