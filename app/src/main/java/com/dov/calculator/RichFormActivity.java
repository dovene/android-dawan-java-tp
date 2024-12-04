package com.dov.calculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.dov.calculator.preferences.SettingsActivity;
import com.dov.calculator.property.Property;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.textfield.TextInputEditText;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class RichFormActivity extends AppCompatActivity {

    private TextInputEditText typeEditText, surfaceEditText, constructionYearEditText, priceEditText, addressEditText, cityEditText;
    private MaterialCheckBox furnishedCheckBox, parkingCheckBox;
    private RadioGroup typeRadio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rich_form);
        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setViewItems();
    }

    private void setViewItems() {
        typeEditText = findViewById(R.id.type_edit);
        surfaceEditText = findViewById(R.id.surface_edit);
        constructionYearEditText = findViewById(R.id.construction_year_edit);
        priceEditText = findViewById(R.id.price_edit);
        addressEditText = findViewById(R.id.address_edit);
        cityEditText = findViewById(R.id.city_edit);
        furnishedCheckBox = findViewById(R.id.furnished_check);
        parkingCheckBox = findViewById(R.id.parking_check);
        typeRadio = findViewById(R.id.type_radio);
        loadPropertyFromSharedPreferences();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_property, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        } else if (item.getItemId() == R.id.action_save) {
            storePropertyInSharedPreferences();
            Toast.makeText(this, "Bien enregistré", Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.action_settings) {
            addPropertyToList();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void storePropertyInSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("type", typeEditText.getText().toString());
        editor.putString("surface", surfaceEditText.getText().toString());
        editor.putString("constructionYear", constructionYearEditText.getText().toString());
        editor.putString("price", priceEditText.getText().toString());
        editor.putString("address", addressEditText.getText().toString());
        editor.putString("city", cityEditText.getText().toString());
        editor.putBoolean("furnished", furnishedCheckBox.isChecked());
        editor.putBoolean("parking", parkingCheckBox.isChecked());
        editor.putInt("typeRadio", typeRadio.getCheckedRadioButtonId());
        editor.apply();
    }

    private void loadPropertyFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        typeEditText.setText(sharedPreferences.getString("type", ""));
        surfaceEditText.setText(sharedPreferences.getString("surface", ""));
        constructionYearEditText.setText(sharedPreferences.getString("constructionYear", ""));
        priceEditText.setText(sharedPreferences.getString("price", ""));
        addressEditText.setText(sharedPreferences.getString("address", ""));
        cityEditText.setText(sharedPreferences.getString("city", ""));
        furnishedCheckBox.setChecked(sharedPreferences.getBoolean("furnished", false));
        parkingCheckBox.setChecked(sharedPreferences.getBoolean("parking", false));
        typeRadio.check(sharedPreferences.getInt("typeRadio", -1));
    }

    private void addPropertyToList() {
        String type = typeEditText.getText().toString();
        String surface = surfaceEditText.getText().toString();
        String constructionYear = constructionYearEditText.getText().toString();
        String price = priceEditText.getText().toString();
        String address = addressEditText.getText().toString();
        String city = cityEditText.getText().toString();
        Boolean furnished = furnishedCheckBox.isChecked();
        Boolean parking = parkingCheckBox.isChecked();
        int typeRadio = this.typeRadio.getCheckedRadioButtonId();
        Property property = new Property(type, surface, constructionYear, price, address, city, furnished, parking, typeRadio);
        List<Property> properties = getProperties();
        properties.add(property);
        saveProperties(properties);
        Toast.makeText(this, "Nombre de biens enregistrés :" + properties.size() + " Le type du dernier bien stocké est : " + property.getType() , Toast.LENGTH_SHORT).show();
    }

    public void saveProperties(List<Property> properties) {
        Gson gson = new Gson();
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String jsonProperties = gson.toJson(properties);
        editor.putString("PROPERTIES_KEY", jsonProperties);
        editor.apply();
    }

    public List<Property> getProperties() {
        Gson gson = new Gson();
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String jsonProperties = sharedPreferences.getString("PROPERTIES_KEY", null);
        if (jsonProperties == null) {
            return new ArrayList<>();
        }
        Type propertyListType = new TypeToken<List<Property>>(){}.getType();
        return gson.fromJson(jsonProperties, propertyListType);
    }
}