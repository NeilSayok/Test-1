package neilsayok.github.myutils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CustomViewGroup extends ViewGroup {

    private ImageView iconView;
    private TextView titleTextView;
    private TextView subtitleTextView;



    public CustomViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }



    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        iconView = findViewById(R.id.icon);
        titleTextView = findViewById(R.id.title);
        subtitleTextView = findViewById(R.id.subtilte);
    }

    @Override
    protected boolean checkLayoutParams(LayoutParams p) {
        return p instanceof RelativeLayout.LayoutParams;
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new RelativeLayout.LayoutParams(p);
    }


    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new RelativeLayout.LayoutParams(getContext(),attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        measureChildWithMargins(iconView,widthMeasureSpec,0,heightMeasureSpec,0);

        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) iconView.getLayoutParams();
        int iconWidthUsed = iconView.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
        int iconHeightUsed = iconView.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;

        measureChildWithMargins(titleTextView, widthMeasureSpec, iconWidthUsed , heightMeasureSpec , 0);

        lp = (RelativeLayout.LayoutParams) titleTextView.getLayoutParams();
        int titleWidthUsed = titleTextView.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
        int titleHeightUsed = titleTextView.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;

        measureChildWithMargins(subtitleTextView,widthMeasureSpec,iconWidthUsed,heightMeasureSpec,titleHeightUsed);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
