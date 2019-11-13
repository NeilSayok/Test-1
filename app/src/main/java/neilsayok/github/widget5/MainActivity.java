package neilsayok.github.widget5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import neilsayok.github.myutils.MathUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MathUtils.Multiply(10,20);
    }
}
