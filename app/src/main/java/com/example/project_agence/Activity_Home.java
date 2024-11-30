package com.example.project_agence;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.bumptech.glide.Glide;
import com.example.project_agence.adapter.CarAdapter;
import com.example.project_agence.database.FirebaseDatabaseHelper;
import com.example.project_agence.model.Car;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class Activity_Home extends AppCompatActivity {
    private TextView usernameTextView;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private ListView carsListView;
    private ArrayList<Car> carsList;
    private CarAdapter adapter;
    private FirebaseDatabaseHelper dbHelper;
    private LinearLayout brandLogoContainer;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private ImageView menuIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        // Initialize components
        initializeComponents();

        // Check authentication
        checkAuthentication();

        // Fetch cars and brand logos
        fetchCarsAndLogos();

        // Setup drawer layout and toggle
        setupDrawerLayout();
    }

    private void initializeComponents() {
        carsListView = findViewById(R.id.cars_list_view);
        auth = FirebaseAuth.getInstance();
        usernameTextView = findViewById(R.id.username);
        user = auth.getCurrentUser();
        brandLogoContainer = findViewById(R.id.brand_logo_container);

        // Initialize DrawerLayout
        drawerLayout = findViewById(R.id.drawer_layout);
        menuIcon = findViewById(R.id.menu_icon);

        // Initialize cars list and adapter
        carsList = new ArrayList<>();
        adapter = new CarAdapter(this, carsList);
        carsListView.setAdapter(adapter);

        // Initialize database helper
        dbHelper = new FirebaseDatabaseHelper();
    }

    private void setupDrawerLayout() {
        // Set up menu icon click listener to open/close drawer
        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(findViewById(R.id.sidebar))) {
                    drawerLayout.closeDrawer(findViewById(R.id.sidebar));
                } else {
                    drawerLayout.openDrawer(findViewById(R.id.sidebar));
                }
            }
        });
    }

    private void checkAuthentication() {
        if (user == null) {
            Intent i = new Intent(Activity_Home.this, Activity_Login.class);
            startActivity(i);
            finish();
        } else {

            String welcomeText = "Welcome, " + (user.getDisplayName() != null ? user.getDisplayName() : user.getEmail());
            usernameTextView.setText(welcomeText);
        }
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(Activity_Home.this, Activity_Login.class);
        startActivity(intent);
        finish();
    }

    private void fetchCarsAndLogos() {
        // Fetch cars
        dbHelper.getCars(new FirebaseDatabaseHelper.OnDataReceiveCallback() {
            @Override
            public void onDataReceived(ArrayList<Car> cars) {
                carsList.clear();
                carsList.addAll(cars);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(Activity_Home.this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });

        // Fetch brand logos
        dbHelper.getBrandLogos(new FirebaseDatabaseHelper.OnBrandLogosReceiveCallback() {
            @Override
            public void onBrandLogosReceived(ArrayList<String> brandLogos) {
                brandLogoContainer.removeAllViews();
                for (String brandLogoUrl : brandLogos) {
                    ImageView logoImageView = new ImageView(Activity_Home.this);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(250, 250);
                    params.setMargins(10, 10, 15, 10);
                    logoImageView.setLayoutParams(params);
                    logoImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

                    Glide.with(Activity_Home.this)
                            .load(brandLogoUrl)
                            .into(logoImageView);

                    brandLogoContainer.addView(logoImageView);
                }
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(Activity_Home.this, "Error loading brand logos: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void goToAvis(View view) {
        Intent intent = new Intent(Activity_Home.this, Activity_Avis.class);
        startActivity(intent);
    }
}