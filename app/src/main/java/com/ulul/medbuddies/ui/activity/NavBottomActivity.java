package com.ulul.medbuddies.ui.activity;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.ulul.medbuddies.ui.fragment.History;
import com.ulul.medbuddies.ui.fragment.Profile;
import com.ulul.medbuddies.R;
import com.ulul.medbuddies.ui.fragment.Schedule;

public class NavBottomActivity extends AppCompatActivity {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_schedule:

                    Schedule fragment = new Schedule();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content, fragment, "FragmentName");
                    fragmentTransaction.commit();

                    return true;
                case R.id.navigation_history:
                    History fragment2 = new History();
                    FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction2.replace(R.id.content, fragment2, "FragmentName");
                    fragmentTransaction2.commit();


                    return true;
                case R.id.navigation_profile:

                    Profile fragment3 = new Profile();
                    FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction3.replace(R.id.content, fragment3, "FragmentName");
                    fragmentTransaction3.commit();

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_bottom);


        BottomNavigationView navView = findViewById(R.id.nav_view);


        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Schedule fragment = new Schedule();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, fragment, "FragmentName");
        fragmentTransaction.commit();
    }

}
