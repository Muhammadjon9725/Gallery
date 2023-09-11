package com.example.gallery.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.gallery.Mytype

class MyDbHelper(context: Context):SQLiteOpenHelper(context, DB_NAME,null, VERSION),MyDbInterface {
    companion object{
        const val DB_NAME = "my_images_db"
        const val TABLE_NAME = "images_table"
        const val VERSION = 1
        const val ID = "id"
        const val NAME = "name"
        const val IMAGE = "image_uri"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query =
            "create table $TABLE_NAME($ID integer not null primary key autoincrement unique,$NAME text not null, $IMAGE text not null ) "
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    override fun getAllImages(): ArrayList<Mytype> {
        val list = ArrayList<Mytype>()
        val database = readableDatabase
        val cursor = database.rawQuery("select * from $TABLE_NAME",null)

        if (cursor.moveToFirst()){
            do {
                list.add(Mytype(cursor.getInt(1),
                    cursor.getString(2),
                    cursor.getString(3))
                )
            }while (cursor.moveToNext())
        }
       return list
    }

    override fun addImage(mytype: Mytype) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(NAME,mytype.name)
        contentValues.put(IMAGE,mytype.imageUri)
        database.insert(TABLE_NAME,null,contentValues)
        database.close()
    }
}