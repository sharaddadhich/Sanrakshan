package com.example.android.sanrakshan.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.android.sanrakshan.Fragments.ContactsFragment;
import com.example.android.sanrakshan.Fragments.PoliceFragment;
import com.example.android.sanrakshan.Fragments.ReportFragment;
import com.example.android.sanrakshan.Fragments.TipsFragment;
import com.example.android.sanrakshan.R;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    FragmentManager fragManager;
    FragmentTransaction fragTxn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        fragManager = getSupportFragmentManager();
        final ContactsFragment contactsFragment = new ContactsFragment(this);
        fragTxn = fragManager.beginTransaction();
        fragTxn.replace(R.id.fragFrame, contactsFragment);
        fragTxn.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragTxn.commit();
        //done 

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.contacts){
                    fragManager = getSupportFragmentManager();
                    fragTxn = fragManager.beginTransaction();
                    fragTxn.replace(R.id.fragFrame, contactsFragment);
                    fragTxn.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    fragTxn.commit();
                }

                if (item.getItemId() == R.id.police){
                    fragManager = getSupportFragmentManager();
                    PoliceFragment policeFragment = new PoliceFragment(MainActivity.this);
                    fragTxn = fragManager.beginTransaction();
                    fragTxn.replace(R.id.fragFrame, policeFragment);
                    fragTxn.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    fragTxn.commit();
                }

                if (item.getItemId() == R.id.tips){
                    fragManager = getSupportFragmentManager();
                    TipsFragment tipsFragment = new TipsFragment(MainActivity.this);
                    fragTxn = fragManager.beginTransaction();
                    fragTxn.replace(R.id.fragFrame,tipsFragment );
                    fragTxn.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    fragTxn.commit();
                }
                if (item.getItemId() == R.id.reportabuse){
                    fragManager = getSupportFragmentManager();
                    ReportFragment reportFragment = new ReportFragment(MainActivity.this);
                    fragTxn = fragManager.beginTransaction();
                    fragTxn.replace(R.id.fragFrame,reportFragment );
                    fragTxn.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    fragTxn.commit();
                }



                return true;
            }
        });

    }
}
