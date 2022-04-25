package com.example.carapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Dbo extends SQLiteOpenHelper {

    public Dbo(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "CarPracticeDb", factory, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table cars(carid integer primary key autoincrement,model string,company string,type string,sunroof short)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists cars");
        onCreate(sqLiteDatabase);
    }

    public void add_car(String model,String company,String type,Short sunroof){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("model",model);
        cv.put("company",company);
        cv.put("type",type);
        cv.put("sunroof",sunroof);
        db.insert("cars",null,cv);
        db.close();
    }

    public void edit_car(Integer id,String model,String company,String type,Short sunroof){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("model",model);
        cv.put("company",company);
        cv.put("type",type);
        cv.put("sunroof",sunroof);
        db.update("cars",cv,"carid=?",new String[]{id.toString()});
        db.close();
    }

    public void delete_car(Integer id){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete("cars","carid=?",new String[]{id.toString()});
        db.close();
    }

    public Cursor get_all_cars(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=db.rawQuery("select *from cars",null);
        return res;
    }

    public Cursor get_car_by_id(Integer id){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=db.rawQuery("select *from cars where carid="+id.toString(),null);
        return res;
    }

    public Cursor search_car(String str,String type){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res;
        if(type.equals("All") && !str.equals("")){
            res=db.rawQuery("select *from cars where (company LIKE '%"+str+"%' or model LIKE '%"+str+"%')",null);

        }else if(!str.equals("")){
            res=db.rawQuery("select *from cars where type='"+type+"' and (company LIKE '%"+str+"%' or model LIKE '%"+str+"%')",null);

        }else if(str.equals("") && !type.equals("All")) {
            res=db.rawQuery("select *from cars where type='"+type+"'",null);
        }else{
            res=db.rawQuery("select *from cars",null);
        }
        return res;
    }
}
