/**
 * Represents the Task view
 */

package edu.utsa.cs3443.taskeliminator;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import edu.utsa.cs3443.taskeliminator.controller.MainController;
import edu.utsa.cs3443.taskeliminator.controller.TaskController;
import edu.utsa.cs3443.taskeliminator.model.TaskList;

public class TaskActivity extends AppCompatActivity {
    public static final String DB_NAME = "edu.utsa.android.taskeliminator.db";
    public static final int DB_VERSION = 1;

    private TaskController taskHelper;
    private ListView TasksList;
    private ArrayAdapter<String> arrAdapter;

    /**
     * @param savedInstanceState
     */

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        taskHelper = new TaskController(this);
        TasksList = (ListView)findViewById(R.id.list_todo);

        updateUI();

    }

    public class TaskEntry implements BaseColumns {
        public static final String TABLE = "tasks";

        public static final String COL_TASK_TITLE = "title";
    }


    @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.main_menu, menu);
            return super.onCreateOptionsMenu(menu);
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_add_task:
                    final EditText taskEdit = new EditText(this);
                    AlertDialog dialog = new AlertDialog.Builder(this)
                            .setTitle("Add a new task").setMessage("What do you want to do next?").setView(taskEdit)
                            .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String task = String.valueOf(taskEdit.getText());
                                    SQLiteDatabase db = taskHelper.getWritableDatabase();
                                    ContentValues values = new ContentValues();
                                    values.put(TaskEntry.COL_TASK_TITLE, task);
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
                                        db.insertWithOnConflict(TaskEntry.TABLE, null, values, SQLiteDatabase.CONFLICT_REPLACE);
                                    }
                                    db.close();
                                    updateUI();

                                    //updating arraylist for badges
                                    //Date date = new Date();
                                    //DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
                                    //String dateStr = dateFormat.format(date);
                                    String dateStr = CalendarActivity.getDateString(getIntent());
                                    TaskList.addTask(task, dateStr, false);
                                }
                            })
                            .setNegativeButton("Cancel", null).create();
                    dialog.show();
                    return true;

                default:
                    return super.onOptionsItemSelected(item);
            }
        }

        public void deleteTask(View view) {
            View parent = (View) view.getParent();
            TextView taskTextView = (TextView) parent.findViewById(R.id.title_task);
            String task = String.valueOf(taskTextView.getText());
            SQLiteDatabase db = taskHelper.getWritableDatabase();
            db.delete(TaskActivity.TaskEntry.TABLE, TaskActivity.TaskEntry.COL_TASK_TITLE + " = ?", new String[]{task});
            db.close();
            updateUI();

            //updating arraylist for badges
            TaskList.taskComplete(task);
        }

        private void updateUI() {
            ArrayList<String> taskList = new ArrayList<>();
            SQLiteDatabase db = taskHelper.getReadableDatabase();
            Cursor cursor = db.query(TaskActivity.TaskEntry.TABLE,
                    new String[]{TaskActivity.TaskEntry._ID, TaskActivity.TaskEntry.COL_TASK_TITLE},
                    null, null, null, null, null);
            while (cursor.moveToNext()) {
                int idx = cursor.getColumnIndex(TaskActivity.TaskEntry.COL_TASK_TITLE);
                taskList.add(cursor.getString(idx));
            }

            if (arrAdapter == null) {
                arrAdapter = new ArrayAdapter<>(this, R.layout.activity_task, R.id.title_task, taskList);
                TasksList.setAdapter(arrAdapter);
            } else {
                arrAdapter.clear();
                arrAdapter.addAll(taskList);
                arrAdapter.notifyDataSetChanged();
            }

            cursor.close();
            db.close();
        }
}