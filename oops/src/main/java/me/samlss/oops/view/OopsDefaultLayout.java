package me.samlss.oops.view;

import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import me.samlss.oops.util.ViewUtils;

/**
 * @author SamLeung
 * @e-mail samlssplus@gmail.com
 * @github https://github.com/samlss
 * @description The default layout only contains {@link android.widget.ImageView} and {@link android.widget.TextView}
 */
public class OopsDefaultLayout extends LinearLayout {
    private ImageView mImageView;
    private TextView mTextView;

    public OopsDefaultLayout(Context context, OopsDefaultLayoutParameter parameter) {
        super(context);

        init(parameter);
    }

    private void init(OopsDefaultLayoutParameter parameter){
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);

        mImageView = new ImageView(getContext());
        mImageView.setScaleType(parameter.getScaleType());
        ViewUtils.setDrawable(mImageView, parameter.getDrawableRes(), parameter.getDrawable());
        addView(mImageView, new LinearLayout.LayoutParams(parameter.getImageWidth(), parameter.getImageHeight()));

        mTextView = new TextView(getContext());
        mTextView.setGravity(Gravity.CENTER);
        ViewUtils.setText(mTextView, parameter.getTextRes(), parameter.getText());
        ViewUtils.setTextColor(mTextView, parameter.getTextColorRes(), parameter.getTextColor());
        mTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, parameter.getTextSize());
        mTextView.setEllipsize(parameter.getTextEllipsize());
        addView(mTextView, new LinearLayout.LayoutParams(parameter.getTextWidth(), parameter.getTextHeight()));
    }

    public ImageView getImageView() {
        return mImageView;
    }

    public TextView getTextView() {
        return mTextView;
    }


}
