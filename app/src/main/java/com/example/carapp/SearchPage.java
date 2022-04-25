package com.example.carapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class SearchPage extends AppCompatActivity {

    Dbo obj=new Dbo(this,null,null,1);

    TextView ts;
    Spinner spn;
    ListView list;

    Button bn;

    String[] carTypes={"All","SUV","MUV","Sedan","Hatchback"};

    ArrayAdapter<car> carArrayAdapter;
    ArrayList<car> carArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);

        ts=findViewById(R.id.txtSch);
        spn=findViewById(R.id.sp3);
        bn=findViewById(R.id.btnSearch);
        list=findViewById(R.id.list3);

        ArrayAdapter<String> adp=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,carTypes);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn.setAdapter(adp);

        showdata();

        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                carArrayList=new ArrayList<car>();
                carArrayAdapter=new ArrayAdapter<car>(getApplicationContext(),android.R.layout.simple_spinner_item,carArrayList);
                list.setAdapter(carArrayAdapter);

                Cursor c=obj.search_car(ts.getText().toString(),spn.getSelectedItem().toString());
                if(c.moveToFirst()){
                    do{
                        carArrayList.add(new car(c.getString(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4)));
                    }while(c.moveToNext());
                }
            }
        });

    }

    public void showdata(){

        carArrayList=new ArrayList<car>();
        carArrayAdapter=new ArrayAdapter<car>(this, android.R.layout.simple_spinner_item,carArrayList);
        list.setAdapter(carArrayAdapter);

        Cursor c=obj.get_all_cars();
        if(c.moveToFirst()){
            do{
              carArrayList.add(new car(c.getString(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4)));
            }while(c.moveToNext());
        }

    }
}