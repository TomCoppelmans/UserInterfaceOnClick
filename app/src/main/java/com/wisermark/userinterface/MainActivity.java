package com.wisermark.userinterface;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    int quantity = 0;
    double price = 0;
    TextView quantityTV;
    TextView priceTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quantityTV = findViewById(R.id.quantity_text_view);
        priceTV = findViewById(R.id.price_text_view);
    }

    public void increase(View view){
        quantity++;
        price = price + 1.20;
        DecimalFormat df = new DecimalFormat("#.###");
        df.setRoundingMode(RoundingMode.CEILING);
        priceTV.setText("€" + df.format(price));
        quantityTV.setText("" + quantity);
        if (quantity==0) {
          priceTV.setText("€0,-");
        }
    }

    public void decrease(View view){
        if (quantity>0) {
            quantity--;
            price = price - 1.20;
            DecimalFormat df = new DecimalFormat("#.###");
            df.setRoundingMode(RoundingMode.CEILING);
            priceTV.setText("€" + df.format(price));
        }
        if (quantity==0) {
            priceTV.setText("€0,-");
        }
        quantityTV.setText("" + quantity);
    }

    public void submitOrder(View view){
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:tomcjc@gmail.com"));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Request for coffee");

        DecimalFormat df = new DecimalFormat("#.###");
        df.setRoundingMode(RoundingMode.CEILING);

        emailIntent.putExtra(Intent.EXTRA_TEXT, "Hi,\n\nI would like " + quantity + " coffee(s). Thank you! \n\n I will bring €" + df.format(price) + " to pay for my coffee.");
        startActivity(emailIntent);
    }
}
