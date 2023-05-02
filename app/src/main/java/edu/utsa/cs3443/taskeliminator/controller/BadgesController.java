/**
 * Controller for the activity_badges view
 */
package edu.utsa.cs3443.taskeliminator.controller;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

import edu.utsa.cs3443.taskeliminator.R;
import edu.utsa.cs3443.taskeliminator.model.Task;
import edu.utsa.cs3443.taskeliminator.model.TaskList;

public class BadgesController implements View.OnClickListener {
    Activity activity;

    /**
     * @param view
     */
    @Override
    public void onClick(View view) {
        activity.finish(); // go back to mainactivity
    }

    /**
     * @param a Activity
     */
    public BadgesController(Activity a) {
        activity = a;
    }

    /**
     * Iterates through ArrayList of tasks and displays the ones that are marked as complete
     */
    public void showBadges() {
        // go through arraylist of tasks; tasks that are marked complete will show up in the list

        //find linearlayout instance in activity_badges
        LinearLayout linLay = activity.findViewById(R.id.badgesLayout);

        if (TaskList.getTasks() == null) {
            return;
        }

        int count = 0;
        for (Task t : TaskList.getTasks()) {
            if (t.isComplete()) {
                //create horizontal layout
                LinearLayout hLay = new LinearLayout(linLay.getContext());
                hLay.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                hLay.setOrientation(LinearLayout.HORIZONTAL);
                linLay.addView(hLay);

                //create vertical layout to store task name and task date
                LinearLayout vLay = new LinearLayout(hLay.getContext());
                vLay.setLayoutParams(new LinearLayout.LayoutParams(845, LinearLayout.LayoutParams.WRAP_CONTENT));
                vLay.setOrientation(LinearLayout.VERTICAL);
                vLay.setGravity(Gravity.CENTER_VERTICAL);
                hLay.addView(vLay);

                TextView taskName = new TextView(vLay.getContext());
                taskName.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                taskName.setText(t.getName());
                taskName.setTextColor(Color.parseColor("#000000"));
                taskName.setTextSize(24);
                vLay.addView(taskName);

                TextView taskDate = new TextView(vLay.getContext());
                taskDate.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                taskDate.setText(t.getDate());
                taskDate.setTextColor(Color.parseColor("#000000"));
                taskDate.setTextSize(18);
                vLay.addView(taskDate);

                ImageView badge = new ImageView(hLay.getContext());
                badge.setLayoutParams(new LinearLayout.LayoutParams(130,130));
                int imageRes = activity.getResources().getIdentifier("badge", "drawable", activity.getPackageName());
                badge.setImageResource(imageRes);
                hLay.addView(badge);

                Space s = new Space(linLay.getContext());
                s.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 15));
                linLay.addView(s);

                //adding extra badge for every 5 tasks complete
                count++;
                if (count % 5 == 0) {
                    LinearLayout extra = new LinearLayout(linLay.getContext());
                    extra.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    extra.setOrientation(LinearLayout.HORIZONTAL);
                    linLay.addView(extra);

                    TextView congrats = new TextView(vLay.getContext());
                    congrats.setLayoutParams(new LinearLayout.LayoutParams(845, LinearLayout.LayoutParams.WRAP_CONTENT));
                    String congratsStr = "You've completed " + count + " tasks.\nKeep it up!";
                    congrats.setText(congratsStr);
                    congrats.setTextColor(Color.parseColor("#000000"));
                    congrats.setGravity(Gravity.CENTER);
                    congrats.setTextSize(24);
                    extra.addView(congrats);

                    ImageView badge5 = new ImageView(hLay.getContext());
                    badge5.setLayoutParams(new LinearLayout.LayoutParams(130,130));
                    int imgRes = activity.getResources().getIdentifier("partypopper", "drawable", activity.getPackageName());
                    badge5.setImageResource(imgRes);
                    extra.addView(badge5);
                }
            }
        }
    }
}

