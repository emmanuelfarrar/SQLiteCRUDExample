package com.efarrar.sqlitecrudexample;

/**
 * URL: https://www.simplifiedcoding.net/android-sqlite-database-example/
 *
 *
 * NOTES:
 * We don’t need a separate design for deleting the employee as we will be doing it from the button that we created on the List
 */


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String DATABASE_NAME = "myemployeedatabase";

    TextView textViewViewEmployees;
    EditText editTextName, editTextSalary;
    Spinner spinnerDepartment;

    SQLiteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewViewEmployees = (TextView) findViewById(R.id.textViewViewEmployees);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextSalary = (EditText) findViewById(R.id.editTextSalary);
        spinnerDepartment = (Spinner) findViewById(R.id.spinnerDepartment);

        findViewById(R.id.buttonAddEmployee).setOnClickListener(this);
        textViewViewEmployees.setOnClickListener(this);

        //creating a database
        mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);

    }

    /**
     * method openOrCreateDatabase().
     * This method takes 3 parameters.
     *
     * First parameter is the database name as String, that we need to open. Lets assume we
     * passed some name “xyz” as the first parameter then if there exist a database named “xyz”
     * it will open it, if no database found with the specified name it will create a new
     * database named “xyz” and will open it.
     *
     * Second Parameter is the open mode as int. We have some predefined values for it, right
     * now we are using MODE_PRIVATE and it simply means that only this application can access
     * this database.
     */

        /*
    this method will create the table
    as we are going to call this method everytime we will launch the application
    I have added IF NOT EXISTS to the SQL
    so it will only create the table when the table is not already created
     */
    private void createEmployeeTable() {
        mDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS employees (\n" +
                        "    id int NOT NULL CONSTRAINT employees_pk PRIMARY KEY,\n" +
                        "    name varchar(200) NOT NULL,\n" +
                        "    department varchar(200) NOT NULL,\n" +
                        "    joiningdate datetime NOT NULL,\n" +
                        "    salary double NOT NULL\n" +
                        ");"
        );
    }


    //this method will validate the name and salary
    //dept does not need validation as it is a spinner and it cannot be empty
    private boolean inputsAreCorrect(String name, String salary){
        if (name.isEmpty()) {
            editTextName.setError("Please enter a name");
            editTextName.requestFocus();
            return false;
        }

        if (salary.isEmpty() || Integer.parseInt(salary) <= 0){
            editTextSalary.setError("Please Enter salary");
            editTextSalary.requestFocus();
            return false;
        }
        return true;
    }

    //In this method we will do the create operation

    /**
     * Now just call this method just after calling the openOrCreateDatabase() method. In the above
     * method we are just calling the method execSQL() to create our database table. The execSQL()
     * method takes String as a parameter and the String is actually the SQL query that we need to
     * execute.
     * Remember we use execSQL() method only for creating table, inserting or updating records.
     * We cannot use it to retrieve values.
     * Till now we have the database, the table now we need to insert the employee in the table and
     * we need to do this inside addEmployee() method.
     */
    private void addEmployee() {
        String name = editTextName.getText().toString().trim();
        String salary = editTextSalary.getText().toString().trim();
        String dept = spinnerDepartment.getSelectedItem().toString();

        //getting the current time for joining date
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yy-mm-dd hh:mm:ss");
        String joiningDate = sdf.format(cal.getTime());

        //validating the inputs
        if (inputsAreCorrect(name, salary)) {

            String insertSQL = "INSERT INTO employees \n" +
                    "(name, department, joiningdate, salary)\n" +
                    "VALUES \n" +
                    "(?, ?, ?, ?);";

            /*
                using the same method execsql for inserting values
                this time it has two parameters
                first is the sql string and second is the parameters that is to be binded with
                the query
             */
            mDatabase.execSQL(insertSQL, new String[]{name, dept, joiningDate, salary});

            Toast.makeText(this, "Employee Added Successfully", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonAddEmployee:
                addEmployee();
                break;
            case R.id.textViewViewEmployees:
                startActivity(new Intent(this, EmployeeActivity.class));
                break;
        }
    }


} //End Activity
