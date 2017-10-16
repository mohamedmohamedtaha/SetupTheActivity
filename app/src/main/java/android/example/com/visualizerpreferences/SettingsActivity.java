package android.example.com.visualizerpreferences;

import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null){
           actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int getId= item.getItemId();
        if (getId == android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }

    // TODO (2) Create a class called SettingsFragment that extends PreferenceFragmentCompat
    // TODO (3) In res->xml create a file called pref_visualizer
    // TODO (4) In pref_visualizer create a preference screen containing a single check box preference
    // This check box preference should have a default value of true, the key 'show_bass', a
    // summaryOff of Hidden, a summaryOn of Shown and a title of 'Show Bass'

    // TODO (5) In SettingsFragment's onCreatePreferences method add the preference file using the
    // addPreferencesFromResource method


    // TODO (7) Set the root layout of activity_settings to our newly created SettingsFragment
    // and remove the padding.
    // [HINT] Use a <fragment> element in xml
}
