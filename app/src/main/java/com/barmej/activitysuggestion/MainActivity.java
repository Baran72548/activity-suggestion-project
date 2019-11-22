package com.barmej.activitysuggestion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String BUNDLE_CURRENT_INDEX = "BUNDLE_CURRENT_INDEX";
    private static final String TAG = MainActivity.class.getSimpleName();

    ImageView mActivityImageView;
    TextView mActivityTextView;
    int[] mActivitiesPictures = {
            R.drawable.resturant,
            R.drawable.trip,
            R.drawable.shopping,
            R.drawable.fishing,
            R.drawable.riding,
            R.drawable.biking
    };
    String[] mActivitiesNames = {
            "تناول وجبة في أحد المطاعم",
            "القيام برحلة استكشافية",
            "التسوق",
            "الصيد",
            "ركوب الخيل",
            "ركوب الدراجة الهوائية"
    };
    private Random mRandom;
    int mCurrentIndex = 0;
    String activityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActivityImageView = findViewById(R.id.activity_image_view);
        mActivityTextView = findViewById(R.id.activity_text_view);
        mRandom = new Random();
        showImage();
    }

    //"Random Button" to show activity suggestion randomly.
    public void displayRandomActivity(View view){
        Log.i(TAG, "Current Index= " + mCurrentIndex);
        if (mCurrentIndex < 6){
            mCurrentIndex = mRandom.nextInt(mActivitiesPictures.length);
            showImage();
            Log.i(TAG, "Current Index= " + mCurrentIndex);
        }
    }

    public void displayNextActivity(View view){
        Log.i(TAG, "Current Index= " + mCurrentIndex);
        if (mCurrentIndex < 5) {
            mCurrentIndex++;
                showImage();
                Log.i(TAG, "Current Index= " + mCurrentIndex);
        } else {
            Toast.makeText(this, "لا يوجد المزيد من الأنشطة لعرضها.", Toast.LENGTH_SHORT).show();
        }
    }

    public void displayPreviousActivity(View view) {
        Log.i(TAG, "Current Index= " + mCurrentIndex);
        if (mCurrentIndex > 0) {
            mCurrentIndex--;
            if (mCurrentIndex < 6 && mCurrentIndex > -1) {
                showImage();
                Log.i(TAG, "Current Index= " + mCurrentIndex);
            }
        } else {
            Toast.makeText(this, "هذا أول الأنشطة. قم باستعراض بقية الأنشطة والاختيار منها", Toast.LENGTH_LONG).show();
        }
    }

    private void showImage(){
        Drawable activityDrawable = ContextCompat.getDrawable(this, mActivitiesPictures[mCurrentIndex]);
        mActivityImageView.setImageDrawable(activityDrawable);
        activityName = mActivitiesNames[mCurrentIndex];
        mActivityTextView.setText(activityName);
    }

    //Saving current state of activity for rotation case.
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(BUNDLE_CURRENT_INDEX, mCurrentIndex);
        Log.i(TAG, "onSaveInstanceState");
    }

    //Restore the saved state of activity after rotation.
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt(BUNDLE_CURRENT_INDEX);
            if (mCurrentIndex != 0){
                showImage();
            }
        }
    }
}
