package com.example.agecalc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

/*
 * MainActivity.java
 * This is the main screen of the Age Calculator app.
 * It lets the user type in their name and birthday, then shows their age.
 */
public class MainActivity extends AppCompatActivity {

    // These variables represent the input boxes and button on the screen
    EditText inputFirstName;
    EditText inputLastName;
    EditText inputBirthYear;
    EditText inputBirthMonth;
    EditText inputBirthDay;
    Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // This line connects this Java file to the layout file (activity_main.xml)
        setContentView(R.layout.activity_main);

        // Connect each variable to the matching field in the layout using its ID
        inputFirstName  = findViewById(R.id.inputFirstName);
        inputLastName   = findViewById(R.id.inputLastName);
        inputBirthYear  = findViewById(R.id.inputBirthYear);
        inputBirthMonth = findViewById(R.id.inputBirthMonth);
        inputBirthDay   = findViewById(R.id.inputBirthDay);
        calculateButton = findViewById(R.id.calculateButton);

        // When the button is clicked, run the calculateAge() method
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateAge();
            }
        });
    }

    // This method runs when the user presses "Calculate Age"
    void calculateAge() {

        // Step 1: Read what the user typed in each field
        String firstName  = inputFirstName.getText().toString().trim();
        String lastName   = inputLastName.getText().toString().trim();
        String yearText   = inputBirthYear.getText().toString().trim();
        String monthText  = inputBirthMonth.getText().toString().trim();
        String dayText    = inputBirthDay.getText().toString().trim();

        // Step 2: Make sure none of the fields are empty
        if (firstName.isEmpty() || lastName.isEmpty() ||
                yearText.isEmpty() || monthText.isEmpty() || dayText.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields!", Toast.LENGTH_LONG).show();
            return; // Stop here — don't try to calculate yet
        }

        // Step 3: Convert the text to numbers so we can do math
        int birthYear  = Integer.parseInt(yearText);
        int birthMonth = Integer.parseInt(monthText);
        int birthDay   = Integer.parseInt(dayText);

        // Step 4: Basic checks to make sure the date makes sense
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        if (birthYear < 1900 || birthYear > currentYear) {
            Toast.makeText(this, "Please enter a valid birth year.", Toast.LENGTH_LONG).show();
            return;
        }
        if (birthMonth < 1 || birthMonth > 12) {
            Toast.makeText(this, "Month must be between 1 and 12.", Toast.LENGTH_LONG).show();
            return;
        }
        if (birthDay < 1 || birthDay > 31) {
            Toast.makeText(this, "Day must be between 1 and 31.", Toast.LENGTH_LONG).show();
            return;
        }

        // Step 5: Calculate age
        // Start with the difference in years
        int age = currentYear - birthYear;

        // Check if their birthday has happened yet this year
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1; // +1 because Calendar starts at 0
        int currentDay   = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

        if (birthMonth > currentMonth || (birthMonth == currentMonth && birthDay > currentDay)) {
            age = age - 1; // Birthday hasn't happened yet this year, so subtract 1
        }

        // Step 6: Show the result in a Toast message
        String message = "Hello, " + firstName + " " + lastName + "! You are " + age + " years old.";
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
