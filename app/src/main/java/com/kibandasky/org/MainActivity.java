package com.kibandasky.org;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Switch;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private AllFragment allFragment;
    private VegFragment vegFragment;
    private FruitFragment fruitFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        allFragment = new AllFragment();
        vegFragment = new VegFragment();
        fruitFragment = new FruitFragment();

        setFragment(allFragment);

        bottomNavigationView = findViewById(R.id.bottomBar);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.all:
                        bottomNavigationView.setItemBackgroundResource(R.color.allBottom);

                        setFragment(allFragment);

                        return true;
                    case R.id.vegone:
                        bottomNavigationView.setItemBackgroundResource(R.color.vegone);

                        setFragment(vegFragment);

                        return true;
                    case R.id.fruitone:
                        bottomNavigationView.setItemBackgroundResource(R.color.fruitone);

                        setFragment(fruitFragment);

                        return true;
                    default:
                        return false;
                }


            }
        });
    }

    public void setFragment(Fragment fragment){

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutMain, fragment);
        fragmentTransaction.commit();

    }
}