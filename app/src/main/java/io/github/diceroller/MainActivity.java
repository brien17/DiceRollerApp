package io.github.diceroller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void roll(View view) {
        // Creating a string to hold the message for the user
        String msg;

        // Using try catch to ensure that all of the fields are filled in
        try {
            // Getting the user input values
            int diceMin = Integer.parseInt(((EditText) findViewById(R.id.smallFace)).getText().toString());
            int diceMax = Integer.parseInt(((EditText) findViewById(R.id.largeFace)).getText().toString());
            int numDice = Integer.parseInt(((EditText) findViewById(R.id.numDice)).getText().toString());

            // Checking if the user inputs an invalid max and min pair
            if (diceMin >= diceMax) {
                msg = "The number on the small face must be less than the number on the large face";
                Toast myToast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG);
                myToast.show();
            } else if (numDice > 150) { // Checking the number of dice rolled is not over 300
                msg = "You may not roll more than 150 dice at once";
                Toast myToast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG);
                myToast.show();
            } else if (diceMax > 999) { // Checking the number of the large face not larger than 999
                msg = "You may not roll dice with a large face higher than 99";
                Toast myToast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG);
                myToast.show();
            } else {
                // Creating the TextView objects
                TextView highestRollText = findViewById(R.id.highestRoll);
                TextView diceText = findViewById(R.id.diceView);
                TextView totalRollText = findViewById(R.id.totalRoll);
                // clearing the TextViews
                highestRollText.setText("");
                totalRollText.setText("");
                diceText.setText("");
                diceText.append("Your rolls are: ");
                // Creating variables to hold highest roll and the total
                int highestRoll = 0;
                int totalRoll = 0;
                Random rand = new Random();
                // Looping to roll dice
                for (int i = 0; i < numDice; i++) {
                    // Rolling a dice
                    int roll = diceMin + rand.nextInt(diceMax - diceMin + 1);
                    // Checking if it is the current highest roll
                    highestRoll = roll > highestRoll ? roll : highestRoll;
                    // Adding to the total
                    totalRoll += roll;
                    // Displaying roll in the TextView
                    diceText.append(roll + " ");
                }
                // Displaying highest roll and total
                highestRollText.append("Your highest roll is: " + highestRoll);
                totalRollText.append("Your total is: " + totalRoll);
            }
        } catch (Exception e) {
            msg = "You must complete all of the fields";
            Toast myToast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG);
            myToast.show();
        }

    }
}
