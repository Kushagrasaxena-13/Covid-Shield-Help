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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Login extends AppCompatActivity {

    EditText email,password,city , username;
    TextView login;
    CheckBox oxygen,bed,injection;
    TextView register;

    FirebaseAuth auth;
    ImageView close;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        close = findViewById(R.id.close);
        city = findViewById(R.id.city);
        username = findViewById(R.id.username);
        oxygen = findViewById(R.id.oxygen);
        bed = findViewById(R.id.bed);
        injection = findViewById(R.id.injection);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        register = findViewById(R.id.Register);


        auth = FirebaseAuth.getInstance();

        register.setOnClickListener(v -> startActivity(new Intent(Login.this,Register.class)));
//
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,MainActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(v -> {
            ProgressDialog pd = new ProgressDialog(Login.this);
            pd.setMessage("Please wait...");
            pd.show();

            String str_email = email.getText().toString();
            String str_password = password.getText().toString();
            String str_city = city.getText().toString().toUpperCase();
            String str_username = username.getText().toString();

            if (TextUtils.isEmpty(str_email) || TextUtils.isEmpty(str_password) || TextUtils.isEmpty(str_city) || TextUtils.isEmpty(str_username)) {
                Toast.makeText(Login.this, "All fields are required!!", Toast.LENGTH_SHORT).show();
            } else {

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("City").child(str_city).child(str_username);

                HashMap<String,Object> hashMap = new HashMap<>();

                if(oxygen.isChecked()){
                    ref.child("oxygen").setValue("1");
                }

                else{
                    ref.child("oxygen").setValue("0");
                }

                if(bed.isChecked()){
                    hashMap.put("bed","1");
                }

                else{
                    hashMap.put("bed","0");
                }

                if(injection.isChecked()){
                    hashMap.put("injection","1");
                }

                else{
                    hashMap.put("injection","0");
                }

                ref.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        pd.dismiss();
                    }
                });
                
                auth.signInWithEmailAndPassword(str_email, str_password)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {


                                if (task.isSuccessful()) {
                                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users").child(auth.getCurrentUser().getUid());

                                    reference.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                                            Intent intent = new Intent(Login.this, MainActivity.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);
                                            finish();
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {
                                            pd.setMessage("authentication failed!!");
                                            pd.dismiss();
                                        }
                                    });





                                } else {
                                    pd.dismiss();
                                    Toast.makeText(Login.this, "Authentication failed!!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });



    }
}
