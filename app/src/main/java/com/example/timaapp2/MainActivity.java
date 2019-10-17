package com.example.timaapp2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ConstraintLayout.LayoutParams timeBlockParams;
    private ConstraintSet constraintSet;
    private ConstraintLayout constraintLayout;
    private Resources r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        constraintLayout = findViewById(R.id.scrollViewLayout);
        r = constraintLayout.getResources();
        timeBlockParams = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        timeBlockParams.height = (int) dipToPixels(60, r);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTimeBlock();
            }
        });
    }

    private void addTimeBlock() {
        View timeBlock = new View(this);
        timeBlock.setId(View.generateViewId());
        timeBlock.setBackgroundResource(R.drawable.time_block);
        timeBlock.setOnTouchListener(new View.OnTouchListener() {
            float dY;

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        dY = view.getY() - event.getRawY();
                        break;

                    case MotionEvent.ACTION_MOVE:
                        view.setY(event.getRawY() + dY);
                        break;

                    default:
                        return false;
                }
                return true;
            }
        });
        timeBlock.setLayoutParams(timeBlockParams);
        constraintLayout.addView(timeBlock);
        constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);
        constraintSet.connect(timeBlock.getId(), ConstraintSet.TOP, constraintLayout.getId(), ConstraintSet.TOP, (int) dipToPixels(40, r));
        constraintSet.connect(timeBlock.getId(), ConstraintSet.START, constraintLayout.getId(), ConstraintSet.START, (int) dipToPixels(40, r));
        constraintSet.connect(timeBlock.getId(), ConstraintSet.END, constraintLayout.getId(), ConstraintSet.END, 0);
        constraintSet.applyTo(constraintLayout);
    }

    private float dipToPixels(float dip, Resources r) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, r.getDisplayMetrics());
    }
}
