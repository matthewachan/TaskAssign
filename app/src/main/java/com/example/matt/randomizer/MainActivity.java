package com.example.matt.randomizer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private String[] member_list = {"Matt", "Natalie", "Irene"};    // List of group members
    private int counter = 0;                                        // Counts button presses
    private int rand_num = randomRange(0, 2);                       // Init random number between 0 and 2
    private int firstPath;                                          // Caches first random number drawn
    TextView member;


    // RANDOM NUMBER FUNCTIONS
    private int randomRange(int min, int max) {
        int range = max - min + 1;
        return (int)(Math.random()*range) + min;
    }

    public int randomOption(int option1, int option2, int option3) {
        int rand = randomRange(0, 2);
        switch(rand) {
            case 0:
                return option1;
            case 1:
                return option2;
            case 2:
                return option3;
            default:
                return -1;
        }
    }

    public int randomOption(int option1, int option2) {
        int rand = randomRange(0,1);
        switch(rand) {
            case 0:
                return option1;
            case 1:
                return option2;
            default:
                return -1;
        }
    }

    // START FUNCTION
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        member = (TextView) findViewById(R.id.member_name);
    }

    // CHOOSE RANDOM MEMBER
    public void OnRandomizeClick(View v) {
        switch (counter) {
            // Choose a random member from the group, all members are available
            case 0:
                member.setText(member_list[rand_num]);
                if (rand_num == 0) {
                    rand_num = randomOption(1, 2);
                    firstPath = 0;
                }
                else if (rand_num == 1) {
                    rand_num = randomOption(0, 2);
                    firstPath = 1;
                }
                else if (rand_num == 2) {
                    rand_num = randomOption(0, 1);
                    firstPath = 2;
                }
                counter++;
                break;
            // Choose a random member from the group, only 2 members available
            case 1:
                member.setText(member_list[rand_num]);
                if ((rand_num == 1 && firstPath == 2) || (rand_num == 2 && firstPath == 1)) {
                    rand_num = 0;
                }
                else if ((rand_num == 0 && firstPath == 2) || (rand_num == 2 && firstPath == 0)) {
                    rand_num = 1;
                }
                else if ((rand_num == 0 && firstPath == 1) || (rand_num == 1 && firstPath == 0)) {
                    rand_num = 2;
                }
                counter++;
                break;
            // Choose last member of the group
            case 2:
                member.setText(member_list[rand_num]);
                rand_num = randomRange(0, 2);
                counter = 0;
                break;
            default:
                break;
        }
    }

    // Reset variables
    public void OnResetClick(View v) {
        member.setText("Member");
        rand_num = randomRange(0, 2);
        counter = 0;
    }
}
