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

public class LapCount extends AppCompatActivity implements  AdapterView.OnItemSelectedListener {

    Spinner spinner1, spinner2;
    Button done;
    boolean lapLenth=false;
    boolean lapActivity=false;
    String lapL="";
    String lapA="";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lap_count);

        spinner1 = findViewById(R.id.lap_length_spinner);
        spinner2 = findViewById(R.id.lap_Activity_spinner);
        done=findViewById(R.id.done);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.laplength, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(new LapLengthClass());

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.lapactivity, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(new LapActivityClass());

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LapCount.this,GPS_Logging.class);
                intent.putExtra("Length",lapL);
                intent.putExtra("Activity",lapA);
                if(lapActivity){
                    if(lapLenth)
                        startActivity(intent);
                    else
                        Toast.makeText(getApplicationContext(),"Lap Length",Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(getApplicationContext(),"Lap Activity",Toast.LENGTH_LONG).show();

            }
        });




    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    class LapLengthClass implements AdapterView.OnItemSelectedListener
    {
        public void onItemSelected(AdapterView<?> parent, View v, int position, long id)
        {
            String text = parent.getItemAtPosition(position).toString();
            Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
            lapL=text;
            if(position!=0)
                lapLenth=true;
            else
                lapLenth=false;
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    class LapActivityClass implements AdapterView.OnItemSelectedListener
    {
        public void onItemSelected(AdapterView<?> parent, View v, int position, long id)
        {
            String text = parent.getItemAtPosition(position).toString();
            Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
            lapA=text;
            if(position!=0)
                lapActivity=true;
            else
                lapActivity=false;
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

}