package com.example.carapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Dbo obj=new Dbo(this,null,null,1);

    TextView t1,t2;
    RadioGroup rg;
    RadioButton ry,rn;
    ListView list;
    Button b1;
    Spinner sp;
    String[] carTypes={"SUV","MUV","Sedan","Hatchback"};

    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;

    ArrayAdapter<car> carAdapter;
    ArrayList<car> carArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t1=findViewById(R.id.t1);
        t2=findViewById(R.id.t2);
        b1=findViewById(R.id.btnAdd);
        rg=findViewById(R.id.rdGroup);
        list=findViewById(R.id.list1);
        sp=findViewById(R.id.spinner);
        ry=findViewById(R.id.yes1);
        rn=findViewById(R.id.no1);

//        ArrayAdapter<CharSequence> adp=ArrayAdapter.createFromResource(this,R.array.car_types, android.R.layout.simple_spinner_item);
//        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        sp.setAdapter(adp);

        ArrayAdapter<String> aa=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,carTypes);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(aa);

        showData();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Check conditions
                //int radioStr=rg.getCheckedRadioButtonId();
//                Short roof=0;
//
//                if(ry.isChecked()){
//                    roof=1;
//                }
//
//                obj.add_car(t2.getText().toString(),t1.getText().toString(),sp.getSelectedItem().toString(),roof);
//
//                showData();
//                //Reset Values
//                Toast.makeText(getApplicationContext(),"Car Added",Toast.LENGTH_SHORT).show();

                Intent isearch=new Intent(getApplicationContext(),SearchPage.class);
                startActivity(isearch);
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                car c= carAdapter.getItem(i);       //Very very important

                Intent i1=new Intent(getApplicationContext(),CarPage.class);
                i1.putExtra("id",c.id);
                i1.putExtra("company",c.company);
                i1.putExtra("model",c.model);
                i1.putExtra("type",c.type);
                i1.putExtra("sunroof",c.sunroof);
                startActivity(i1);

            }
        });

    }

    public void showData(){
//        arrayList=new ArrayList<String>();
//        adapter=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayList);
//        list.setAdapter(adapter);

        carArrayList=new ArrayList<car>();
        carAdapter=new ArrayAdapter<car>(getApplicationContext(), android.R.layout.simple_spinner_item,carArrayList);
        list.setAdapter(carAdapter);

//        arrayList.add("ID COMPANY MODEL TYPE SUNPROOF");
//        arrayList.add("==============================");
        Cursor res=obj.get_all_cars();

        if(res.moveToFirst()){
            do{
                //arrayList.add(res.getString(0)+" "+res.getString(1)+" "+res.getString(2)+" "+res.getString(3)+" "+res.getString(4));
                carArrayList.add(new car(res.getString(0),res.getString(1),res.getString(2),res.getString(3),res.getString(4)));
            }while (res.moveToNext());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        showData();
    }
}