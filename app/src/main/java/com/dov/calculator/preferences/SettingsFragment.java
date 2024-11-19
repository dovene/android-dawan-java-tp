package com.dov.calculator.preferences;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.dov.calculator.R;

public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
        setUsername();
    }

    private void setUsername() {
        // Find the EditTextPreference
        EditTextPreference usernamePref = findPreference("username");
        if (usernamePref != null) {
            // Update summary when preference changes
            usernamePref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(@NonNull Preference preference, Object newValue) {
                    preference.setSummary(newValue.toString());
                    return true;
                }
            });

            // Set initial summary
            String currentValue = usernamePref.getText();
            usernamePref.setSummary(currentValue != null ? currentValue : "Non défini");
        }
    }
}

