# carApp


package com.example.json_demo;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    public static  final String TAG=MainActivity.class.getCanonicalName();
    Button btn;
    AssetManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        btn=findViewById( R.id.b1 );
        manager=getAssets();
        btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    JSONObject root=new JSONObject( json() );
                    Log.i( TAG, "Department Name= "+root.getString( "name" ) );
                    JSONArray arr=root.getJSONArray( "Subjects" );
                    for (int i=0; i<arr.length();i++)
                    {
                        Log.i( TAG, arr.getString( i ) );
                    }

                    JSONObject inner=root.getJSONObject( "Computers" );
                    Boolean server=inner.getBoolean( "Server" );
                    Log.i( TAG, "Server ="+server );
                    Integer desktop=inner.getInt( "desktop" );
                    Log.i( TAG, "desktop machines= "+desktop );
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        } );
    }

    private  String json()
    {
        StringBuilder sb=new StringBuilder(  );
        try {
            InputStream is=manager.open( "myjson.json" );
          while(true)
          {
              int ch=is.read();
              if (ch== -1) break;
              else sb.append( (char) ch );
          }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}



myjson.json


{
  "name":"DICT",
  "Students":650,
  "Subjects":[
    "Java",
    "Android",
    "iOS",l
    "PHP",
    ".NET"
  ],
  "Computers":{
    "Server":true,
    "desktop":250
  }


}

