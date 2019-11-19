package neilsayok.github.myutils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.res.ResourcesCompat;

import static neilsayok.github.myutils.DisplayMerticsConverters.dp;
import static neilsayok.github.myutils.DisplayMerticsConverters.dp2px;
import static neilsayok.github.myutils.DisplayMerticsConverters.sp;

public class CustomView extends View {

    float viewWidth;
    float viewHeight;
    float viewHeight75percent;
    float viewHeight90percent;
    float borderRadius;
    float nodeRadius;


    int baseColor;
    int textXPos,textYPos;

    Paint paint;
    Paint p;
    TextPaint textPaint;
    Typeface typeface;



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

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int desiredWidth = (int) (dp2px(getContext(),200) + getPaddingLeft() + getPaddingRight());
        int desiredHeight = (int) (dp2px(getContext(),100) + getPaddingTop() + getPaddingBottom());

        setMeasuredDimension(measureDimension(desiredWidth, widthMeasureSpec),
                measureDimension(desiredHeight, heightMeasureSpec));
    }


    private int measureDimension(int desiredSize, int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = desiredSize;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }


        return result;
    }

    private void init(@Nullable AttributeSet attr, Context context){
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        p = new Paint(Paint.ANTI_ALIAS_FLAG);

        borderRadius = dp(getContext(),8);
        baseColor = Color.rgb(92,177,214);

        textPaint = new TextPaint(TextPaint.ANTI_ALIAS_FLAG);

        if (!isInEditMode())
            typeface = ResourcesCompat.getFont(getContext(),R.font.roboto);
        else
            typeface = Typeface.create("Arial", Typeface.BOLD);

        this.setOnLongClickListener(longClickListener);

    }

    OnLongClickListener longClickListener = new OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            Toast.makeText(getContext(),"Long Clicked",Toast.LENGTH_SHORT).show();
            return true;
        }
    };

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
//        paint.setColor(Color.WHITE);
//        paint.setTextSize(sp(getContext(),16));
//        canvas.drawText("Detect Face",20,viewHeight/2,paint);

        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(sp(getContext(),18));
        textPaint.setTypeface(typeface);
        textPaint.setTextAlign(Paint.Align.CENTER);


        textXPos = (int) (viewWidth/2);

        textYPos = (int) ((viewHeight75percent/2) - ((textPaint.descent() + textPaint.ascent())/2));


        canvas.drawText("Detect Face",textXPos,textYPos,textPaint);

    }


}
