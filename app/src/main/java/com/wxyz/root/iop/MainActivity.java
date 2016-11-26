package com.wxyz.root.iop;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {


    private DatabaseReference mDatabase;
    FirebaseDatabase database;
    TextView txt1,txt2,txt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Firebase
        database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference();

        //Assigning text Objetc to my UI
        txt1 = (TextView) findViewById(R.id.luz);
        txt2 = (TextView) findViewById(R.id.humedad);
        txt3 = (TextView) findViewById(R.id.temperatura);

    }

    @Override
    protected void onResume() {
        super.onResume();

        //Reading from my db
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Setting my values in the UI
                txt1.setText(dataSnapshot.child("Temperature").getValue().toString());
                txt2.setText(dataSnapshot.child("Humidity").getValue().toString());
                txt3.setText(dataSnapshot.child("Luminosity").getValue().toString());
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
