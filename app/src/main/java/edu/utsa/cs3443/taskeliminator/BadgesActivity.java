/**
 * Represents the Badges view
 */
package edu.utsa.cs3443.taskeliminator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import edu.utsa.cs3443.taskeliminator.controller.BadgesController;

public class BadgesActivity extends AppCompatActivity {
    BadgesController badgesCon;

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badges);

        badgesCon = new BadgesController(this);
        findViewById(R.id.imageButton).setOnClickListener(badgesCon);

        badgesCon.showBadges();
    }
}