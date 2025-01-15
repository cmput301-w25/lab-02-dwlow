package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    // Declaring the variables
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    Button add_button;
    Button delete_button;
    TextInputEditText text_in;
    int selected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);
        String []cities = {"Edmonton","Calgary","Regina","Toronto"};
        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));
        cityAdapter = new ArrayAdapter<>(this,R.layout.content,dataList);
        cityList.setAdapter(cityAdapter);
        // Gets all my newly created buttons and text input
        delete_button = findViewById(R.id.delete_button);
        add_button = findViewById(R.id.add_button);
        text_in = findViewById(R.id.city_name_input);
        // Creates a default selected item
        selected = -1;
        // Makes the program store the selected item
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selected = position;
            }
        });
        // Adds the button functionality
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Gets the hint message
                CharSequence text_string;
                text_string = text_in.getText();
                assert text_string != null;
                // Prevents empty string from being added
                if (!text_string.toString().isEmpty()) {
                    // Adds it to the list
                    dataList.add(text_string.toString());
                    // Notifies the adapter to change the screen
                    cityAdapter.notifyDataSetChanged();}
            }
        });
        // Creates the button action to remove an item
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ensures all results are valid
                if (selected != -1){
                    // Gets the item and removes it from the list
                    dataList.remove(selected);
                    selected = -1;
                    cityAdapter.notifyDataSetChanged();}
            }
        });
    }
}