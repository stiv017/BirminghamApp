package com.example.birminghamgooglefinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddPeople extends AppCompatActivity {
    EditText id;
    EditText datum;
    EditText brOsoba;
    EditText imeRezervacije;
    String  childValue;
    DatabaseReference myRef;
    FirebaseDatabase database;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_people);
        id=(EditText)findViewById(R.id.id);
        datum=(EditText)findViewById(R.id.datum);
        brOsoba=(EditText)findViewById(R.id.brOsoba);
        imeRezervacije=(EditText)findViewById(R.id.naziv);
        button=(Button)findViewById(R.id.button2);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("gost");
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               String brojStola=id.getText().toString();
               String datumRezervacije=datum.getText().toString();
               String brojOsoba=brOsoba.getText().toString();
               String naziv=imeRezervacije.getText().toString();
               myRef.setValue(brojStola);
               myRef.child("brojOsoba").setValue(brOsoba);
               myRef.child("Datum").setValue(datumRezervacije);
               myRef.child("ime").setValue(imeRezervacije);
            }
        });
    }
}
