package com.example.myapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.classes.ModelClass;
import com.example.myapplication.classes.RecyclerClass;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class OnBoardingActivity extends AppCompatActivity {

    ArrayList<ModelClass> list;
    ViewPager2 pager2;
    RecyclerClass recyclerClass;
    TabLayout tabLayout;
    ImageView nextBtn;
    TextView skip;

    Button getStartBtn;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor myEdit;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_on_boarding);
        pager2 = findViewById(R.id.viewPager2);
        tabLayout = findViewById(R.id.tabs);
        skip = findViewById(R.id.textViewSkip);
        nextBtn = findViewById(R.id.next);
        getStartBtn = findViewById(R.id.getStart);
        list = new ArrayList<>();
        list.add(new ModelClass(R.drawable.tab_one, "Online Shopping", "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual. "));
        list.add(new ModelClass(R.drawable.tab_three, "Strong Connection", "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual. "));
        list.add(new ModelClass(R.drawable.tab_two, "Free Delivery", "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual."));
        recyclerClass = new RecyclerClass(list, this);
        pager2.setAdapter(recyclerClass);
        sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        myEdit = sharedPreferences.edit();
        TabLayoutMediator mediator = new TabLayoutMediator(tabLayout, pager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

            }
        });
        mediator.attach();

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tabLayout.getSelectedTabPosition() != 2) {
                    pager2.setCurrentItem(pager2.getCurrentItem() + 1);
                } else if (tabLayout.getSelectedTabPosition() == 2) {

                    getStartBtn.setVisibility(View.VISIBLE);
                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                            R.anim.anim);
                    getStartBtn.startAnimation(animation);
                    nextBtn.setVisibility(View.INVISIBLE);
                    tabLayout.setVisibility(View.INVISIBLE);
                    pager2.setUserInputEnabled(false);
                }
            }
        });

        getStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OnBoardingActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (tab.getPosition() == 2) {
                    getStartBtn.setVisibility(View.VISIBLE);

                    getStartBtn.setVisibility(View.VISIBLE);
                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                            R.anim.anim);
                    getStartBtn.startAnimation(animation);
                    nextBtn.setVisibility(View.INVISIBLE);
                    tabLayout.setVisibility(View.INVISIBLE);
                    pager2.setUserInputEnabled(false);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (user!=null)
        {
            Intent i = new Intent(OnBoardingActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
        }


}
}