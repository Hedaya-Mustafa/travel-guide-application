package com.example.travel_guide_app_1181390_1182126;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
public class ConnectActivity extends AppCompatActivity {
    Button button;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);
        setProgress(false);
        button = (Button) findViewById(R.id.connect_Button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            ConnectionAsyncTask connectionAsyncTask = new ConnectionAsyncTask(ConnectActivity.this);
            connectionAsyncTask.execute("https://run.mocky.io/v3/d1a9c002-6e88-4d1e-9f39-930615876bca");
            }
        });
        linearLayout = (LinearLayout)  findViewById(R.id.layout);
    }
    public void setButtonText(String text) {
        button.setText(text);
    }
    public void fillDestination(List<Destination> destinations) {
        LinearLayout linearLayout = (LinearLayout)  findViewById(R.id.layout);
        linearLayout.removeAllViews();
        for (int i = 0; i < destinations.size(); i++) {
            TextView textView = new TextView(this);
            textView.setText(destinations.get(i).toString());
            linearLayout.addView(textView);
        }
    }
    public void setProgress(boolean progress) {
        ProgressBar progressBar = (ProgressBar)
                findViewById(R.id.progressBar);
        if (progress) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}

