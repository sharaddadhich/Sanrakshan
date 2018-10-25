package com.example.android.sanrakshan.Activities;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.sanrakshan.R;

public class EmergencyActivity extends AppCompatActivity {

    TextView tvName,tvmsg;
    Button btnClose;
    public static MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);

        String msg = getIntent().getStringExtra("msg");
//minor change
        tvmsg = (TextView) findViewById(R.id.tv_msg);
        tvmsg.setText(msg);
        btnClose = (Button) findViewById(R.id.btn_Close);

        mp = MediaPlayer.create(this,R.raw.siren);
        mp.start();
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.stop();
                finish();
            }
        });


    }
}
