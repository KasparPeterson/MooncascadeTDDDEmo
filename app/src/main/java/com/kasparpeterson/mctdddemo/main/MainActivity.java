package com.kasparpeterson.mctdddemo.main;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by kaspar on 02/04/2017.
 */

public class MainActivity extends AppCompatActivity {

    public static Intent getIntent(@NonNull Context context) {
        return new Intent(context, MainActivity.class);
    }

}
