package com.efarrar.sqlitecrudexample;

/**
 * URL: https://www.simplifiedcoding.net/android-sqlite-database-example/
 *
 *
 * NOTES:
 * We don’t need a separate design for deleting the employee as we will be doing it from the button that we created on the List
 */


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
