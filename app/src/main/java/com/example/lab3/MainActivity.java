package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import model.ClientOrder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener , TextWatcher{

    EditText edClNumber,edNbYears;
    RadioGroup rgProvider;
    Button btnSave ,btnQuit, btnShow;
    RadioButton rbBell,rbVideotron , rbAcanac;
    TextView tvSubtotalAmount,tvTpsAmount,tvTvqAmount,tvTotalAmount;
    String providerType = " ";
    ArrayList<ClientOrder> listOfOrders;
    ClientOrder order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {
        edClNumber = findViewById(R.id.edClNumber);
        edNbYears = findViewById(R.id.edNbYears);
        rgProvider = findViewById(R.id.rgProvider);
        btnQuit = findViewById(R.id.btnQuit);
        btnSave = findViewById(R.id.btnSave);
        btnShow = findViewById(R.id.btnShowAll);
        rbBell = findViewById(R.id.rbBell);
        rbVideotron = findViewById(R.id.rbVideotron);
        rbAcanac = findViewById(R.id.rbAcanac);
        tvSubtotalAmount = findViewById(R.id.tvSubtotalAmount);
        tvTpsAmount = findViewById(R.id.tvTpsAmount);
        tvTvqAmount = findViewById(R.id.tvTvqAmount);
        tvTotalAmount = findViewById(R.id.tvTotalAmount);
        listOfOrders = new ArrayList<ClientOrder>();
        edNbYears.addTextChangedListener(this);
        btnQuit.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btnShow.setOnClickListener(this);
        rbBell.setOnClickListener(this);
        rbVideotron.setOnClickListener(this);
        rbAcanac.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id){

            case R.id.btnSave:
                saveOrder(view);
                break;
            case R.id.btnQuit:
                quitApp();
                break;

            case R.id.btnShowAll:
                showAllOrder();
                break;
            case R.id.rbBell:
                providerType = rbBell.getText().toString();
                showAmount();
                break;
            case R.id.rbVideotron:
                providerType = rbVideotron.getText().toString();
                showAmount();
                break;
            case R.id.rbAcanac:
                providerType = rbAcanac.getText().toString();
                showAmount();
                break;

        }
}

    private void showAmount() {
        try {
            float price = getPrice(providerType);
            int nbyears =  Integer.valueOf(edNbYears.getText().toString());
            float subtotal = nbyears * price;
            float tps = subtotal * 0.06f;
            float tvq = (subtotal + tps) * 0.095f;
            float total = subtotal + tps + tvq;
            tvSubtotalAmount.setText( String.valueOf(subtotal));
            tvTpsAmount.setText( String.valueOf(tps));
            tvTvqAmount.setText( String.valueOf(tvq));
            tvTotalAmount.setText( String.valueOf(total));

        }catch (Exception e){

            Toast.makeText(this, e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
    private float getPrice(String providerType) throws  Exception {
        float price = 0f;
        int nbyears =  Integer.valueOf(edNbYears.getText().toString());
        if (providerType.equals("Bell")&& nbyears == 12){
            price = 50f;
        }
        else if (providerType.equals("Videotron") && nbyears == 6){
            price = 350/6f;
        }
        else if (providerType.equals("Videotron") && nbyears == 12){
            price = 49f;
        }
        else if(providerType.equals("Acanac") && nbyears == 12){
            price = 495/12f;
        }
        else if (providerType.equals("Videotron"))
            price = 70f;
        else if (providerType.equals("Acanac"))
            price = 45f;
        else if (providerType.equals("Bell"))
            price = 60f;
        else
            throw new Exception("Please select a provider.");
        return price;
    }
    private void showAllOrder() {
        Intent i = new Intent(this ,SecondActivity.class);
        i.putExtra("orders", listOfOrders);
        startActivity(i);
    }

    private void quitApp() {
        System.exit(0);
    }
    private void saveOrder(View view) {
        try {
            int clNumber = Integer.valueOf(edClNumber.getText().toString());
            int nbYears = Integer.valueOf(edNbYears.getText().toString());
            order = new ClientOrder(clNumber,providerType,nbYears);
            listOfOrders.add(order);
            Snackbar.make(view,"Order Created successfully", Snackbar.LENGTH_LONG).show();

            clearWidgets();
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
    private void clearWidgets() {
        edClNumber.setText(null);
        edNbYears.setText(null);
        tvSubtotalAmount.setText(null);
    }
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }
    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }
    @Override
    public void afterTextChanged(Editable editable) {
        showAmount();
    }
}