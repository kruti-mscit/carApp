package com.example.carapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CarPage extends AppCompatActivity {

    Dbo obj=new Dbo(this,null,null,1);

    TextView tx1,tx2;
    RadioGroup rg2;
    RadioButton ry2,rn2;

    Button be,bd;
    Spinner sp2;
    String[] carTypes={"SUV","MUV","Sedan","Hatchback"};

    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_page);

        tx1=findViewById(R.id.comp);
        tx2=findViewById(R.id.mdl);
        be=findViewById(R.id.btnEdit);
        bd=findViewById(R.id.btnDelete);
        rg2=findViewById(R.id.rd2);
        sp2=findViewById(R.id.sp2);
        ry2=findViewById(R.id.rdYes);
        rn2=findViewById(R.id.rdNo);

//        ArrayAdapter<CharSequence> adp=ArrayAdapter.createFromResource(this,R.array.car_types, android.R.layout.simple_spinner_item);
//        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        sp.setAdapter(adp);

        ArrayAdapter<String> aa=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,carTypes);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp2.setAdapter(aa);

        Intent i=this.getIntent();
        int carId=Integer.parseInt(i.getStringExtra("id").toString());
        tx1.setText(i.getStringExtra("company").toString());
        tx2.setText(i.getStringExtra("model").toString());
        String sunroof=i.getStringExtra("sunroof").toString();
        String type=i.getStringExtra("type").toString();

        //Toast.makeText(this,sunroof,Toast.LENGTH_SHORT).show();
        if(sunroof.equals("1")){
            ry2.setChecked(true);
        }else{
            rn2.setChecked(true);
        }

        int pos=0;
        for(int j=0;j<carTypes.length;j++){
            if(carTypes[j].equals(type)){
                pos=j;
                break;
            }
        }

        sp2.setSelection(pos);      //very very important

        be.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Check conditions
                //int radioStr=rg.getCheckedRadioButtonId();
                Short roof=0;

                if(ry2.isChecked()){
                    roof=1;
                }

                obj.edit_car(carId,tx2.getText().toString(),tx1.getText().toString(),sp2.getSelectedItem().toString(),roof);

                Toast.makeText(getApplicationContext(),"Updated",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        bd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                obj.delete_car(carId);

                Toast.makeText(getApplicationContext(),"Deleted",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}