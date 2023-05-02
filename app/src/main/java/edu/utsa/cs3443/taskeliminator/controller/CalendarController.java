/**
 * Controller for the activity_calendar view
 */

package edu.utsa.cs3443.taskeliminator.controller;

import android.app.Activity;

public class CalendarController {

    private final Activity activity;
    private static String key = "date";

    public CalendarController(Activity activity){
        this.activity = activity;
    }
}
