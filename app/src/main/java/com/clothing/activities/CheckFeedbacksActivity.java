package com.clothing.activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.clothing.R;
import com.clothing.adapters.CheckFeedbackAdapter;
import com.clothing.models.FeedbackPojo;

import java.util.ArrayList;
import java.util.List;

public class CheckFeedbacksActivity extends AppCompatActivity {
    RecyclerView rv_check_feedback;
    List<FeedbackPojo> a1;
    CheckFeedbackAdapter checkFeedbackAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_feedbacks);

        getSupportActionBar().setTitle("Check Feedbacks");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rv_check_feedback = (RecyclerView) findViewById(R.id.rv_check_feedback);

        a1 = new ArrayList<>();
        a1.add(new FeedbackPojo("Rameswar Rao", "rames@gmail.com", "Good Application"));
        a1.add(new FeedbackPojo("Rameswar Rao", "rames@gmail.com", "Good Application"));
        a1.add(new FeedbackPojo("Rameswar Rao", "rames@gmail.com", "Good Application"));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rv_check_feedback.setLayoutManager(linearLayoutManager);

        checkFeedbackAdapter = new CheckFeedbackAdapter(CheckFeedbacksActivity.this, a1);  //attach adapter class with therecyclerview
        rv_check_feedback.setAdapter(checkFeedbackAdapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}