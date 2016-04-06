package edu.lclark.animationdemoapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnTouch;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.activity_main_checkbox)
    CheckBox mCheckBox;
    @Bind(R.id.activity_main_button)
    Button mButton;
    @Bind(R.id.activity_main_relativelayout)
    RelativeLayout mLayout;

    private float mLastX = 0, mLastY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.activity_main_button)
    public void onButtonClick() {
        Toast.makeText(MainActivity.this, "Button pressed!", Toast.LENGTH_SHORT).show();
    }

    @OnCheckedChanged(R.id.activity_main_checkbox)
    public void onCheckedChanged(CompoundButton view, boolean checked) {
        mButton.clearAnimation();
    }


    @OnTouch(R.id.activity_main_relativelayout)
    public boolean onLayoutTouched(View view, MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();

        if (mCheckBox.isChecked()) {
            TranslateAnimation translateAnimation = new TranslateAnimation(mLastX, x, mLastY, y);
            translateAnimation.setDuration(300);
//            translateAnimation.setFillAfter(true);
            mButton.startAnimation(translateAnimation);


            mLastX = x;
            mLastY = y;
            return false;
        } else {
            mButton.animate()
                    .x(x)
                    .y(y)
                    .setDuration(300)
                    .start();


            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mButton.getLayoutParams();
            layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            mButton.setLayoutParams(layoutParams);


//            ObjectAnimator xAnim = ObjectAnimator.ofFloat(mButton, View.X, x);
//            ObjectAnimator yAnim = ObjectAnimator.ofFloat(mButton, View.Y, y);
//            AnimatorSet set = new AnimatorSet();
//            set.playTogether(xAnim, yAnim);
//            set.setDuration(300);
//            set.start();


            mLastX = x;
            mLastY = y;
        }

        return true;
    }
}
