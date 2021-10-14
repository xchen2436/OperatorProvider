package com.example.lab3;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import model.ClientOrder;

public class SecondActivity extends AppCompatActivity {
    TextView tvCLOrder,tvTotal;
    ArrayList<ClientOrder> listOfOrders;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        initialize();
    }

    private void initialize() {
        tvCLOrder = findViewById(R.id.txtCLOrder);
        tvTotal = findViewById(R.id.txtTotalOrders);

        listOfOrders= (ArrayList<ClientOrder>) getIntent().getExtras().getSerializable("orders");
        StringBuilder list = new StringBuilder("");
        float totalOrders = 0;
        for (ClientOrder oneOrder: listOfOrders){
            list.append(oneOrder + "\n");
            totalOrders+=oneOrder.getSubtotal();
        }
        tvCLOrder.setText(list);
        tvTotal.append(totalOrders + " ");

    }
}
