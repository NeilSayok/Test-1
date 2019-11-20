package neilsayok.github.widget5;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import neilsayok.github.myutils.ChoiceTouchListener;
import neilsayok.github.myutils.CustomView;
import neilsayok.github.myutils.MathUtils;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab;
    ViewGroup rootLayout;
    List<View> viewList;    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);


        rootLayout = findViewById(R.id.root_layout);
        fab = findViewById(R.id.floatingActionButton);
        viewList = new ArrayList<>();


        fab.setOnClickListener(fabClickListener);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        Log.d("X,Y" , width + "," + height);

    }


    int c = 0;



    View.OnClickListener fabClickListener = new View.OnClickListener() {
        @SuppressLint("ClickableViewAccessibility")
        @Override
        public void onClick(View v) {
            Log.d("Fab","Clicked");
            //Button button = new Button(getApplicationContext());
            //RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            //MarkPresentElement button = new MarkPresentElement(getApplicationContext());
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            CustomView button = new CustomView(getApplicationContext());

            lp.leftMargin = 0;
            lp.topMargin = 0;
            button.setLayoutParams(lp);
            //button.setOnTouchListener(new ChoiceTouchListener(rootLayout));
            //button.setText("Button " + viewList.size());
            rootLayout.addView(button);

            viewList.add(button);

            Log.d("View List Size", String.valueOf(rootLayout.getChildCount()));





        }
    };
}
