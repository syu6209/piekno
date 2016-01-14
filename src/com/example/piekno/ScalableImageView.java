package com.example.piekno;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.ImageView;
 
public class ScalableImageView extends ImageView {
    public boolean isMeasured = true; 
 
    public ScalableImageView(Context context) {
        super(context);
    }
 
    public ScalableImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
 
    public ScalableImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
 
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        try
        {
            Drawable drawable = getDrawable();
 
            if (drawable == null)
            {
                setMeasuredDimension(0, 0);
            }
            else
            {
                int width = MeasureSpec.getSize(widthMeasureSpec);
                int height = width * drawable.getIntrinsicHeight() / drawable.getIntrinsicWidth();
                setMeasuredDimension(width, height);
            }
        }
        catch (Exception e)
        {
            isMeasured = false;
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}