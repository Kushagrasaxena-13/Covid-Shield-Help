package com.example.covidshield;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.renderscript.Sampler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.List;

public class Type_user_adapter extends RecyclerView.Adapter<Type_user_adapter.viewholder> {


    private Context mContext;
    private List<User> mUsers;
    private  Values value;




    DatabaseReference reference;


    public Type_user_adapter(Context mContext,List<User> mUsers){
        this.mContext= mContext;
        this.mUsers = mUsers;

    }



    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.type_user_items, parent,false);





        return new Type_user_adapter.viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {

        reference = FirebaseDatabase.getInstance().getReference().child("City");

        final  User user1 = mUsers.get(position);



        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("City").child(user1.getCity());
//                child(user1.getCity());

        String str = user1.getCity();


//        holder.username.setText(user1.getUsername());


        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference().child("Value");





        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String str3 =user1.getCity();

                reference1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {


                        if(str3.equals(snapshot.child("selected").getValue().toString())) {


//                            holder.username.setText(str3);
                    holder.username.setText(user1.getUsername());
                            String str2 = user1.getUsername();


                            holder.username.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(mContext, Info.class);
                                    intent.putExtra("1", str);
                                    intent.putExtra("2", str2);
                                    mContext.startActivity(intent);
                                }
                            });
                        }

                else {
                    holder.cardview.setVisibility(View.GONE);
                }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{

        public TextView username;
        public CardView cardview;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            cardview = itemView.findViewById(R.id.cardview);
        }
    }


}
