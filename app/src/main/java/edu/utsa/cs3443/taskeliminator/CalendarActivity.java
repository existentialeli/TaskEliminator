/**
 * Represents the Calendar view
 */

package edu.utsa.cs3443.taskeliminator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;

import edu.utsa.cs3443.taskeliminator.controller.CalendarController;

public class CalendarActivity extends AppCompatActivity {

    private CalendarController controller;
    private CalendarView calendarView;
    private static final String TAG = "CalendarActivity";

    /**
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        controller = new CalendarController(this);

        calendarView = (CalendarView)findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int month, int dayOfMonth, int year) {
                String date = month + "/" + dayOfMonth + "/"+ year;
                Log.d(TAG, "onSelectedDayChange: yyyy/mm/dd:" + date);
                Intent intent = new Intent(CalendarActivity.this, TaskActivity.class);
                intent.putExtra("date",date);
                startActivity(intent);
            }
        });
    }

    /**
     * @param i Intent
     * @return String
     */
    public static String getDateString(Intent i) {
        return i.getStringExtra("date");
    }
}