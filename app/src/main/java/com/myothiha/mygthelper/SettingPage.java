package com.myothiha.mygthelper;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class SettingPage extends AppCompatActivity {
CheckBox cb;
    Button button;
    final static String MYPERFS = "myGTHelper.SetupData";
    final static int mode = Activity.MODE_PRIVATE;
    static SharedPreferences setup1Data;
    static SharedPreferences.Editor myEditor;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       intent = new Intent(getApplicationContext(), MainActivity.class);
        setContentView(R.layout.activity_setting_page);
        setup1Data = getSharedPreferences(MYPERFS, mode);
        myEditor = setup1Data.edit();
        cb = (CheckBox)findViewById(R.id.checkBox);
        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cb.isChecked()){
                    myEditor.putString("home", "schedule").apply();
                    finishAffinity();
                    startActivity(intent);

                }
                else{
                    myEditor.putString("home", "default").apply();
                    finishAffinity();
                    startActivity(intent);
                }
            }
        });
    }
}
