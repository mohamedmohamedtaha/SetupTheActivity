package android.example.com.visualizerpreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;
import android.widget.Toast;

/**
 * Created by MANASATT on 16/10/17.
 */
// TODO (23) Implement OnSharedPreferenceChangeListener
// TODO (30) Implement OnPreferenceChangeListener

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener,
      Preference.OnPreferenceChangeListener{
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.pref_visualizer);
        // TODO (25) Get the preference screen, get the number of preferences and iterate through
        // all of the preferences if it is not a checkbox preference, call the setSummary method
        // passing in a preference and the value of the preference
        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        int count = preferenceScreen.getPreferenceCount();
        for (int i= 0; i< count; i++){
            Preference preference = preferenceScreen.getPreference(i);
            if (!(preference instanceof CheckBoxPreference)){
                String value = sharedPreferences.getString(preference.getKey(),"");
                setPreferenceSummary(preference,value);
            }

        }
         Preference preference = findPreference(getString(R.string.size_key));
        preference.setOnPreferenceChangeListener(this);
    }
    // TODO (29) Don't forget to add code here to properly set the summary for an EditTextPreference

    private void setPreferenceSummary(Preference preference, String value){
        if (preference instanceof ListPreference){
            ListPreference listPreference = (ListPreference)preference;
            int prefIndex = listPreference.findIndexOfValue(value);
            if (prefIndex >= 0){
                //set the summary to that label
                listPreference.setSummary(listPreference.getEntries()[prefIndex]);
            }
        }
        else if (preference instanceof EditTextPreference){
            // For EditTextPreferences, set the summary to the value's simple string representation.
            preference.setSummary(value);
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preference p=findPreference(key);
        if (null != p){
            if (!(p instanceof CheckBoxPreference)){
                String value = sharedPreferences.getString(p.getKey(), "");
                setPreferenceSummary(p, value);
            }
        }

    }


// TODO (26) Override onSharedPreferenceChanged and, if it is not a checkbox preference,
// call setPreferenceSummary on the changed preference

// TODO (24) Create a setPreferenceSummary which takes a Preference and String value as parameters.
// This method should check if the preference is a ListPreference and, if so, find the label
// associated with the value. You can do this by using the findIndexOfValue and getEntries methods
// of Preference.

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }


    // TODO (31) Override onPreferenceChange. This method should try to convert the new preference value
// to a float; if it cannot, show a helpful error message and return false. If it can be converted
    // to a float check that that float is between 0 (exclusive) and 3 (inclusive). If it isn't, show
    // an error message and return false. If it is a valid number, return true.
    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
       Toast error = Toast.makeText( getContext(), "Please select a number between 0.1 and 3", Toast.LENGTH_SHORT);

        String sizeKey = getString(R.string.size_key);
        if (preference.getKey().equals(sizeKey)){
            String stringSize = ((String)(newValue)).trim();
            if (stringSize.equals("")) stringSize = "1";
            try{
                float size = Float.parseFloat(stringSize);
                if (size >3 || size <= 0){
                    error.show();
                    return false;
                }
            }catch (NumberFormatException e){
                error.show();
                return false;
            }
        }
        return true;
    }
}
























