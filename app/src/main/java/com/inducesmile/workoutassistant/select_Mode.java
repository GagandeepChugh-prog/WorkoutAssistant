package com.inducesmile.workoutassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class select_Mode extends AppCompatActivity implements  AdapterView.OnItemSelectedListener{

    Spinner spinner2;
    Button done;
    //boolean mode=false;
    boolean modeSelect=false;
    String lapL="";
    String lapA="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select__mode);
        spinner2 = findViewById(R.id.lap_Activity_spinner);
        done=findViewById(R.id.done);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.mode, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter);
        spinner2.setOnItemSelectedListener(this);




    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();

        if(parent.getItemAtPosition(position).equals("Normal Mode"))
        {
            Intent i = new Intent(select_Mode.this, GPS_Logger_Normal.class);
            startActivity(i);
        }
        else if(parent.getItemAtPosition(position).equals("Athletic Mode"))
        {
            Intent i = new Intent(select_Mode.this, LapCount.class);
            startActivity(i);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


}
