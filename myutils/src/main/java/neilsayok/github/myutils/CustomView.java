package neilsayok.github.myutils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Build;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.Display;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.res.ResourcesCompat;

import java.util.Random;

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
    float textSize;

    int[] center;
    int[][] points;
    int[] pt;
    int[] screenCenter;



    final boolean debug = true;

    ViewGroup parent;


    int baseColor;
    int textXPos,textYPos;

    Paint paint;
    Paint p;
    TextPaint textPaint;
    Typeface typeface;
    DisplayMetrics displayMetrics;



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

        points = new int[4][2];
        pt = new int[2];
        center = new int[2];

        displayMetrics = new DisplayMetrics();

        borderRadius = dp(getContext(),8);
        baseColor = Color.rgb(92,177,214);

        textPaint = new TextPaint(TextPaint.ANTI_ALIAS_FLAG);

        if (!isInEditMode())
            typeface = ResourcesCompat.getFont(getContext(),R.font.roboto);
        else
            typeface = Typeface.create("Arial", Typeface.BOLD);

        this.setOnLongClickListener(longClickListener);
        if(debug)
            Log.e("Parent","hello");

//        ((Activity)getContext().getApplicationContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        displayMetrics = context.getResources().getDisplayMetrics();



        screenCenter = new int[2];
        screenCenter[1] = displayMetrics.heightPixels/2;
        screenCenter[0] = displayMetrics.widthPixels/2;

        if (debug)
            Log.d("X,Y" , screenCenter[0]*2 + "," + screenCenter[1]*2);


    }




    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        parent = (ViewGroup) this.getParent();
        this.setOnTouchListener(new ChoiceTouchListener(parent));


        parent.getLocationOnScreen(pt);


        screenCenter[0] += pt[0];
        screenCenter[1] += pt[1];



    }

    OnLongClickListener longClickListener = new OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            if (debug)
                Toast.makeText(getContext(),"Long Clicked",Toast.LENGTH_SHORT).show();

//            if (center[0] < screenCenter[0]){
//                if (center[1] < screenCenter[1]){
//                    //top left
//                    displayPopup(points[2]);
//                    Log.d("Quadrant","1");
//                }else if (center[1] > screenCenter[1]){
//                    //bottom left
//                    displayPopup(points[1]);
//                    Log.d("Quadrant","4");
//                }else {
//                    // y center
//                    displayPopup(points[2]);
//                    Log.d("Quadrant","1,4");
//                }
//
//            }else if (center[0] > screenCenter[0]){
//                if (center[1] < screenCenter[1]){
//                    //top right
//                    Log.d("Quadrant","2");
//                }else if (center[1] > screenCenter[1]){
//                    //bottom right
//                    Log.d("Quadrant","3");
//                }else {
//                    // y center
//                    Log.d("Quadrant","2,3");
//                }
//            }else {
//                if (center[1] < screenCenter[1]){
//                    //top right
//                    Log.d("Quadrant","2");
//                }else if (center[1] > screenCenter[1]){
//                    //bottom right
//                    Log.d("Quadrant","3");
//                }else {
//                    // y center
//                    Log.d("Quadrant","2,3");
//                }
//
//            }
            displayPopup(getContext());
            return true;
        }
    };




    private void displayPopup(Context context){
        PopupMenu popupMenu = new PopupMenu(context, this);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.popup_menu,popupMenu.getMenu());
        popupMenu.show();


    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawDetails(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);


        update_pts();

        viewWidth = w;
        viewHeight = h;
        viewHeight75percent = h*0.75f;
        viewHeight90percent = h*0.90f;
        nodeRadius = h*0.05f;
        if (nodeRadius >= 20.0f){
            nodeRadius = 20.0f;
        }

        textSize = w/h * 9;
        if (textSize >= 20.0f){
            textSize = 20.0f;
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

        canvas.drawCircle(screenCenter[0],screenCenter[1],nodeRadius,paint);
//        paint.setColor(Color.WHITE);
//        paint.setTextSize(sp(getContext(),16));
//        canvas.drawText("Detect Face",20,viewHeight/2,paint);

        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(sp(getContext(),textSize));
        textPaint.setTypeface(typeface);
        textPaint.setTextAlign(Paint.Align.CENTER);


        textXPos = (int) (viewWidth/2);

        textYPos = (int) ((viewHeight75percent/2) - ((textPaint.descent() + textPaint.ascent())/2));


        canvas.drawText("Detect Face",textXPos,textYPos,textPaint);

    }

    public  void update_pts(){
        this.getLocationInWindow(pt);
        //Top left Corner
        points[0][0] = pt[0];
        points[0][1] = pt[1];
        //Top Right Corner
        points[1][0] = (int) (pt[0] + viewWidth);
        points[1][1] = pt[1];
        //Bottom Right Corner
        points[2][0] = (int) (pt[0] + viewWidth);
        points[2][1] = (int) (pt[1] + viewHeight);
        //Bottom Left Corner
        points[3][0] = pt[0];
        points[3][1] = (int) (pt[1] + viewHeight);
        //Center
        center[0] = (int) (pt[0] + (viewWidth/2));
        center[1] = (int) (pt[1] + (viewHeight/2));

        if (debug)
            Log.d("Point x,y", String.valueOf(points[0][0])+","+String.valueOf(points[0][1]));




    }


}
