/**
 * Controller for the activity_main view
 */

package edu.utsa.cs3443.taskeliminator.controller;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import edu.utsa.cs3443.taskeliminator.BadgesActivity;
import edu.utsa.cs3443.taskeliminator.CalendarActivity;

public class MainController implements View.OnClickListener {

    private final Activity activity;
    private static String key = "book_name";

    public MainController(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View view) {
        // based on a button clicked we show the calendar
        // or we show the badges
        Button clickedButton = (Button) view;
        if (clickedButton.getText().equals("Calendar")) {
            System.out.println("Clicked on Calendar");
            // starts the calendar View activity
            Intent intent = new Intent(activity, CalendarActivity.class);
            activity.startActivity(intent);
        } else if (clickedButton.getText().equals("Badges")) {
            System.out.println("Clicked on Badges");
            Intent intent = new Intent(activity, BadgesActivity.class);
            activity.startActivity(intent);
        }
    }
}
