package neilsayok.github.widget5.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class CustomView extends View {

    float viewWidth;
    float viewHeight;
    float viewHeight75percent;
    float viewHeight90percent;
    float borderRadius;
    float nodeRadius;

    int baseColor;

    Paint paint;
    Paint p;

    TextView text;


    public CustomView(Context context) {
        super(context);
        init(null,context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs,context);

    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs,context);

    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs,context);

    }

    private void init(@Nullable AttributeSet attr,Context context){
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        p = new Paint(Paint.ANTI_ALIAS_FLAG);
        borderRadius = 20.0f;
        baseColor = Color.rgb(92,177,214);
        //nodeRadius = 20.0f;

        text = new TextView(context);
        text.setText("Detect Face");
        text.setTextColor(Color.WHITE);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawDetails(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        viewWidth = w;
        viewHeight = h;
        viewHeight75percent = h*0.75f;
        viewHeight90percent = h*0.90f;
        nodeRadius = h*0.05f;
        if (nodeRadius >= 20.0f){
            nodeRadius = 20.0f;
        }

    }

    private void drawDetails(Canvas canvas){


        paint.setColor(baseColor);
        canvas.drawRect(0,0,viewWidth,viewHeight/3,paint);
        canvas.drawRoundRect(0.0f,0.0f,viewWidth, viewHeight75percent,borderRadius,borderRadius,paint);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(10.0f);
        canvas.drawLine(viewWidth/2, viewHeight75percent,viewWidth/2,viewHeight90percent-15.0f,paint);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(10.0f);
        canvas.drawCircle(viewWidth/2,viewHeight90percent,nodeRadius,p);


    }
}
