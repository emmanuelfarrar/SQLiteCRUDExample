package com.efarrar.sqlitecrudexample;

/**
 * Activity that supports the view after storing employee to the database, we also need to see all the stored employee from the database
 */

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class EmployeeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);
    }
}
