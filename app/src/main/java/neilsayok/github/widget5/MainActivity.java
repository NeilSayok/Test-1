package neilsayok.github.widget5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import neilsayok.github.myutils.CustomView;
import neilsayok.github.myutils.MathUtils;

public class MainActivity extends AppCompatActivity {

    ViewGroup root;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b = findViewById(R.id.button);
        root = findViewById(R.id.root);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomView customView = new CustomView(getApplicationContext());
                root.addView(customView);

            }
        });

    }
}
