package com.ulul.carebuddies.ui.activity;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ulul.carebuddies.ui.fragment.History;
import com.ulul.carebuddies.ui.fragment.Patient;
import com.ulul.carebuddies.ui.fragment.Profile;
import com.ulul.carebuddies.R;
import com.ulul.carebuddies.ui.fragment.Schedule;
import com.ulul.carebuddies.util.LocalStorage;

public class NavBottomActivity extends AppCompatActivity {
    FirebaseUser mAut;

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

                case R.id.navigation_patient:

                    Patient fragment4 = new Patient();
                    FragmentTransaction fragmentTransaction4 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction4.replace(R.id.content, fragment4, "FragmentName");
                    fragmentTransaction4.commit();

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_bottom);

        mAut = FirebaseAuth.getInstance().getCurrentUser();
        int role = getIntent().getIntExtra("role", -1);
        LocalStorage local = new LocalStorage(this, "role");
        local.setInt("role", role);

        BottomNavigationView navView = findViewById(R.id.nav_view);

        if (role == 1){
            navView.getMenu().clear();
            navView.inflateMenu(R.menu.bottom_nav_menu_patient);
        }


        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Schedule fragment = new Schedule();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, fragment, "FragmentName");
        fragmentTransaction.commit();
    }

}
