/**
 * Controller for the activity_task view
 */


package edu.utsa.cs3443.taskeliminator.controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import edu.utsa.cs3443.taskeliminator.TaskActivity;

public class TaskController extends SQLiteOpenHelper {

    public TaskController(Context context) {
        super(context, TaskActivity.DB_NAME, null, TaskActivity.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TaskActivity.TaskEntry.TABLE + " ( " +
                TaskActivity.TaskEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TaskActivity.TaskEntry.COL_TASK_TITLE + " TEXT NOT NULL);";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TaskActivity.TaskEntry.TABLE);
        onCreate(db);
    }
}
