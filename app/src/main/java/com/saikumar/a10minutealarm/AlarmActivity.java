package com.saikumar.a10minutealarm;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.widget.Toast;
import java.util.Calendar;

public class AlarmActivity extends Activity {

    private static final String PREFS_NAME = "AlarmPrefs";
    private static final String LAST_SET_TIME = "last_set_time";
    private static final int ALARM_OFFSET_MINUTES = 11;
    private static final int DEBOUNCE_MS = 8000; // 8 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        long lastTime = prefs.getLong(LAST_SET_TIME, 0);
        long currentTime = System.currentTimeMillis();

        if (currentTime - lastTime < DEBOUNCE_MS) {
            Toast.makeText(this, "Already set", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        setAlarm();
        
        prefs.edit().putLong(LAST_SET_TIME, currentTime).apply();
        Toast.makeText(this, "11-minute alarm set", Toast.LENGTH_SHORT).show();
        
        finish();
    }

    private void setAlarm() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, ALARM_OFFSET_MINUTES);

        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_HOUR, calendar.get(Calendar.HOUR_OF_DAY))
                .putExtra(AlarmClock.EXTRA_MINUTES, calendar.get(Calendar.MINUTE))
                .putExtra(AlarmClock.EXTRA_MESSAGE, "11 min")
                .putExtra(AlarmClock.EXTRA_SKIP_UI, true);
        
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
