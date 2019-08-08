package com.jasonrippel.guessanumber;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView mNumberOfTries;
    private EditText mEditText;
    private Button mButton;

    private int mRandomNumber = 0;
    private int mNumOfTries = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNumberOfTries = findViewById(R.id.numOfTries);
        mEditText = findViewById(R.id.edittext);
        mButton = findViewById(R.id.button);

        //generates random number when app opens
        generateRandomNumber();

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check button text
                if(mButton.getText().toString().equals("PLAY AGAIN")){
                    //reset the game so the user can play again
                    playAgain();
                }else {
                    //submit new guess and check if it's correct
                    submit();
                }
            }
        });
    }

    private void submit(){
        String CORRECT = "Congrats! You guessed the right number!";
        String TOO_LOW = "Try again. Your guess was too low!";
        String TOO_HIGH = "Try again. Your guess was too high!";

        String editTextString = mEditText.getText().toString();

        //check if edittext is empty and return
        if(editTextString.isEmpty()){
            return;
        }

        int currentNum = Integer.parseInt(editTextString);

        String message = "";

        if(currentNum == mRandomNumber) {
            //acknowledge that the user got the number correct
            message = CORRECT;
            //change the text of the button to play again
            mButton.setText("PLAY AGAIN");
        }else if (currentNum > mRandomNumber){
            //acknowledge that the user guessed a number that was too high
            message = TOO_HIGH;
            //increment number of tries by 1
            setNumOfTries(mNumOfTries + 1);
            }else{
                //acknowledge that the user guessed a number that was too low
                message = TOO_LOW;
                //increment number of tries by 1
                setNumOfTries(mNumOfTries + 1);
            }
        //show user a message
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0 , 0);
        toast.show();

        //clear out text from the Edittext
        mEditText.setText("");
        }

    private void setNumOfTries(int number){
        mNumOfTries = number;
        String numberString = String.valueOf(number);
        mNumberOfTries.setText("# of tries: " + numberString);
    }

    private void playAgain(){
        //generate new random number
        generateRandomNumber();

        //change button text to SUBMIT
        mButton.setText("SUBMIT");

        //reset tries back to zero
        setNumOfTries(0);

        //clear text out from Edittext
        mEditText.setText("");
    }

    private void generateRandomNumber(){
        //generates a random number between 1-100
        mRandomNumber = new Random().nextInt(100) + 1;
    }
}
