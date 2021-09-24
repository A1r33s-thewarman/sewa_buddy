package com.seva_buddyv2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class accType extends AppCompatActivity {

    private CardView card,card_provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acc_type);

        card = (CardView) findViewById(R.id.cardcus);
        card_provider = (CardView) findViewById(R.id.cardprovider);
        card.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openAccCreate();
            }
        });
        card_provider.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openAccCreate_provider();
            }
        });
    }

    public void openAccCreate() {
        Intent intent = new Intent(this, AccountCreation.class);
        startActivity(intent);    }

    public void openAccCreate_provider() {
        Intent intent = new Intent(this, provider_AccountCreation.class);
        startActivity(intent);    }
}