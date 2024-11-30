package com.example.project_agence.database;

import android.util.Log;
import androidx.annotation.NonNull;
import com.example.project_agence.model.Car;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class FirebaseDatabaseHelper {
    private DatabaseReference mDatabase;
    private DatabaseReference brandsReference;

    public FirebaseDatabaseHelper() {
        mDatabase = FirebaseDatabase.getInstance().getReference("cars");
        brandsReference = FirebaseDatabase.getInstance().getReference("brands");
    }

    // Method to fetch all cars
    public void getCars(OnDataReceiveCallback callback) {
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Car> carsList = new ArrayList<>();
                for (DataSnapshot carSnapshot : snapshot.getChildren()) {
                    Car car = carSnapshot.getValue(Car.class);
                    if (car != null) {
                        carsList.add(car);
                    }
                }
                callback.onDataReceived(carsList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                callback.onError(error.getMessage());
                Log.e("FirebaseDatabaseHelper", "Error fetching cars", error.toException());
            }
        });
    }

    // Method to fetch brand logos
    public void getBrandLogos(OnBrandLogosReceiveCallback callback) {
        brandsReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<String> brandLogos = new ArrayList<>();
                for (DataSnapshot brandSnapshot : snapshot.getChildren()) {
                    String brandLogoUrl = brandSnapshot.getValue(String.class);
                    if (brandLogoUrl != null) {
                        brandLogos.add(brandLogoUrl);
                    }
                }
                callback.onBrandLogosReceived(brandLogos);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                callback.onError(error.getMessage());
                Log.e("FirebaseDatabaseHelper", "Error fetching brand logos", error.toException());
            }
        });
    }

    // Callback interface for car data
    public interface OnDataReceiveCallback {
        void onDataReceived(ArrayList<Car> carsList);
        void onError(String errorMessage);
    }

    // Callback interface for brand logos
    public interface OnBrandLogosReceiveCallback {
        void onBrandLogosReceived(ArrayList<String> brandLogos);
        void onError(String errorMessage);
    }
}