package android.example.com.visualizerpreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;

/**
 * Created by MANASATT on 16/10/17.
 */
// TODO (23) Implement OnSharedPreferenceChangeListener

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {
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

        }    }
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
}
























