package com.seva_buddy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class accType extends AppCompatActivity {

    private CardView card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acc_type);

        card = (CardView) findViewById(R.id.cardcus);
        card.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openAccCreate();
            }
        });
    }

    public void openAccCreate() {
        Intent intent = new Intent(this, AccountCreation.class);
        startActivity(intent);    }
}