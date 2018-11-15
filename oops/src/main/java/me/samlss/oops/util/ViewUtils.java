package me.samlss.oops.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author SamLeung
 * @e-mail samlssplus@gmail.com
 * @github https://github.com/samlss
 * @description utils of view.
 */
public class ViewUtils {
    private ViewUtils(){
        throw new UnsupportedOperationException("Can not be instantiated.");
    }

    public static int dp2px(float dp){
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return Math.round(px);
    }

    public static void setDrawable(ImageView image, int id, Drawable drawable) {
        if (image == null) return;
        if (id == 0) {
            if (drawable != null) image.setImageDrawable(drawable);
        } else image.setImageResource(id);
    }

    public static void setText(TextView textView, int id, String text) {
        if (textView == null) return;
        if (id == 0) {
            if (text != null && !text.equals(textView.getText())) textView.setText(text);
        } else {
            CharSequence oldText = textView.getContext().getResources().getText(id);
            if (!oldText.equals(textView.getText())) textView.setText(id);
        }
    }

    public static void setTextColor(TextView textView, int id, int color) {
        if (textView == null) return;
        if (id == 0) {
            textView.setTextColor(color);
        } else textView.setTextColor(getColor(textView.getContext(), id));
    }

    public static int getColor(Context context, int id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return context.getResources().getColor(id, null);
        } else {
            //noinspection deprecation
            return context.getResources().getColor(id);
        }
    }
}
