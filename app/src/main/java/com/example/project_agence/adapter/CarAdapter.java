package com.example.project_agence.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.project_agence.R;
import com.example.project_agence.model.Car;
import java.util.ArrayList;

public class CarAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Car> carsList;

    public CarAdapter(Context context, ArrayList<Car> carsList) {
        this.context = context;
        this.carsList = carsList;
    }

    @Override
    public int getCount() {
        return carsList.size();
    }

    @Override
    public Object getItem(int position) {
        return carsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.car_item, null);
        }

        Car car = carsList.get(position);

        TextView brandTextView = convertView.findViewById(R.id.car_name);
        brandTextView.setText(car.getBrand() + " " + car.getModel());

        TextView priceTextView = convertView.findViewById(R.id.car_price);
        priceTextView.setText("$" + car.getPrice());

        ImageView carImageView = convertView.findViewById(R.id.car_image);
        Glide.with(context).load(car.getImageUrl()).into(carImageView);

        return convertView;
    }
}