/**
 * Represents the Main view
 */

package edu.utsa.cs3443.taskeliminator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import edu.utsa.cs3443.taskeliminator.controller.MainController;
import edu.utsa.cs3443.taskeliminator.controller.TaskController;
import edu.utsa.cs3443.taskeliminator.model.TaskList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private TaskController taskHelper;
    private ListView TasksList;
    private ArrayAdapter<String> arrAdapter;

    private MainController controller;

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = new MainController(this);

        // button to go to calendar
        findViewById(R.id.badges_button).setOnClickListener(controller);

        // button to go to badges
        findViewById(R.id.calendar_button).setOnClickListener(controller);
    }
}