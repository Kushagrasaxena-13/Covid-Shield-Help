package com.example.covidshield;

import android.content.Context;
import android.content.Intent;
import android.view.DragAndDropPermissions;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.covidshield.R.layout;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class City_Adapter extends RecyclerView.Adapter<City_Adapter.Viewholder>{

    private Context mContext;
    private List<User> mUsers;

    private FirebaseUser firebaseUser;
    private User user1;

    RecyclerView recyclerView;

    public City_Adapter(Context mContext,List<User> mUsers){
        this.mContext= mContext;
        this.mUsers = mUsers;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.city_item, parent,false);
//      firebaseUser = FirebaseAuth.getInstance().getCurrentUser().getUid();




         return new City_Adapter.Viewholder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("City");
        final User user = mUsers.get(position);

//        holder.city.setText(reference.orderByChild(user.toString()).toString());

       reference.addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               holder.city.setText(user.getCity());
           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });


//        holder.city.setText(user.getId());





//
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                holder.city.setText( snapshot.getValue().toString());
//            for(DataSnapshot snapshot1:snapshot.getChildren()){
//                    String str = user.getCity();
//                    int k =1;
//                    for(DataSnapshot snapshot2:snapshot1.getChildren()){
//
//                        if(user.getCity().equals(str) && k>2){
//                            holder.city.setVisibility(View.GONE);
//                            k++;
//                        }
//                        else{
//                            holder.city.setVisibility(View.VISIBLE);
//                            holder.city.setText(str);
//                            k++;
//                        }
//                    }
//                }
//
//
////
////                for(DataSnapshot snapshot1 : snapshot.getChildren()){
////                    holder.city.setText(snapshot1.getKey().toString());
////                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

//        String str = holder.city.getText();
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,Type_user.class);

              DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference().child("Value");
                HashMap<String ,Object> hashMap = new HashMap<>();
                hashMap.put("selected",holder.city.getText());
                reference1.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });


                mContext.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public  class Viewholder extends RecyclerView.ViewHolder{

        public TextView city;
        public CircleImageView photo;
        public CardView cardView;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            city= itemView.findViewById(R.id.city);
            photo = itemView.findViewById(R.id.photo);
            cardView = itemView.findViewById(R.id.cardview);
        }
    }
}
