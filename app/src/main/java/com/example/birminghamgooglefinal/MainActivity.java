package com.example.birminghamgooglefinal;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {



    EditText datumEdit;
    EditText ime;
    String  childValue;
    DatabaseReference myRef;
    FirebaseDatabase database;
    Button button;
    ImageView stol,stol1,stol2,stol3,stol4,stol5,stol6,stol7,stol8
            ,stol9,stol10,stol11,stol12;
    int id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        datumEdit=(EditText)findViewById(R.id.editDatum);
        ime=(EditText)findViewById(R.id.editIme);
        button=(Button)findViewById(R.id.button);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("gost");
        Calendar calendar= Calendar.getInstance();
        stol=(ImageView)findViewById(R.id.textView);
        stol1=(ImageView)findViewById(R.id.textView1);
        stol2=(ImageView)findViewById(R.id.textView2);
        stol3=(ImageView)findViewById(R.id.textView3);
        stol4=(ImageView)findViewById(R.id.textView4);
        stol5=(ImageView)findViewById(R.id.textView5);
        stol6=(ImageView)findViewById(R.id.textView6);
        stol7=(ImageView)findViewById(R.id.textView7);
        stol8=(ImageView)findViewById(R.id.textView8);
        stol9=(ImageView)findViewById(R.id.textView9);
        stol10=(ImageView)findViewById(R.id.textView10);
        stol11=(ImageView)findViewById(R.id.textView11);
        stol12=(ImageView)findViewById(R.id.textView12);

        final String currentDate= DateFormat.getDateInstance().format(calendar.getTime());

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                id++;
                if(id==11)
                {
                    id=0;
                }
                myRef.addValueEventListener(new ValueEventListener() {
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {


                        String i = String.valueOf(id);
                        childValue = String.valueOf(dataSnapshot.getValue());
                        String passc = String.valueOf(dataSnapshot.child(i).child("ime").getValue());
                        String datum = String.valueOf(dataSnapshot.child(i).child("Datum").getValue());

                            //datumEdit.getText().toString()
                            if (datumEdit.getText().toString().equalsIgnoreCase(datum)) {
                                if (ime.getText().toString().equalsIgnoreCase(passc)) {
                                    stol.setImageDrawable(getDrawable(R.drawable.check));
                                } else {
                                    Toast.makeText(MainActivity.this, "Nema rezervaciju", Toast.LENGTH_SHORT).show();
                                }


                            } else {
                                /* Toast.makeText(MainActivity.this, "Nema rezervaciju za danas", Toast.LENGTH_SHORT).show();*/
                            }

                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w("baza", "Failed to read value.", error.toException());
                    }
                });

            }

        });

    }
    @Override
 public boolean onCreateOptionsMenu (Menu menu)
 {
     MenuInflater inflater=getMenuInflater();
     inflater.inflate(R.menu.menu,menu);
     return true;
 }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.menu_add){
            startActivity(new Intent(MainActivity.this,AddPeople.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
