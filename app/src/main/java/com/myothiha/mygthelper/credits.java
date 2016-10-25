package com.myothiha.mygthelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class credits extends Activity {
TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
        tv = (TextView)findViewById(R.id.creditsText);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
