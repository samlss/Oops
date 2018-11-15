package me.samlss.oops.view;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;

import me.samlss.oops.util.ViewUtils;

/**
 * @author SamLeung
 * @e-mail samlssplus@gmail.com
 * @github https://github.com/samlss
 * @description To help to build {@link OopsDefaultLayout}.
 */
public class OopsDefaultLayoutParameter {
    private int drawableRes;
    private Drawable drawable;
    private ImageView.ScaleType scaleType;
    private int imageWidth;
    private int imageHeight;

    private int textRes;
    private String text;
    private float textSize;
    private int textColor;
    private int textColorRes;
    private TextUtils.TruncateAt textEllipsize;
    private int textWidth;
    private int textHeight;

    public OopsDefaultLayoutParameter(){
        imageWidth = ViewUtils.dp2px(70);
        imageHeight = imageWidth;

        textWidth  = ViewUtils.dp2px(70);
        textHeight = ViewUtils.dp2px(30);

        scaleType = ImageView.ScaleType.FIT_CENTER;

        textSize = 16; //in sp
        textColor = 0xFF666666;
    }

    public int getDrawableRes() {
        return drawableRes;
    }

    public OopsDefaultLayoutParameter setDrawableRes(int drawableRes) {
        this.drawableRes = drawableRes;
        return this;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public OopsDefaultLayoutParameter setDrawable(Drawable drawable) {
        this.drawable = drawable;
        return this;
    }

    public ImageView.ScaleType getScaleType() {
        return scaleType;
    }

    public OopsDefaultLayoutParameter setScaleType(ImageView.ScaleType scaleType) {
        this.scaleType = scaleType;
        return this;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public OopsDefaultLayoutParameter setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
        return this;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public OopsDefaultLayoutParameter setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
        return this;
    }

    public int getTextRes() {
        return textRes;
    }

    public OopsDefaultLayoutParameter setTextRes(int textRes) {
        this.textRes = textRes;
        return this;
    }

    public String getText() {
        return text;
    }

    public OopsDefaultLayoutParameter setText(String text) {
        this.text = text;
        return this;
    }

    public float getTextSize() {
        return textSize;
    }

    /**
     * Set text size in sp.
     * */
    public OopsDefaultLayoutParameter setTextSize(float textSize) {
        this.textSize = textSize;
        return this;
    }

    public int getTextColor() {
        return textColor;
    }

    public OopsDefaultLayoutParameter setTextColor(int textColor) {
        this.textColor = textColor;
        return this;
    }

    public int getTextColorRes() {
        return textColorRes;
    }

    public OopsDefaultLayoutParameter setTextColorRes(int textColorRes) {
        this.textColorRes = textColorRes;
        return this;
    }

    public TextUtils.TruncateAt getTextEllipsize() {
        return textEllipsize;
    }

    public OopsDefaultLayoutParameter setTextEllipsize(TextUtils.TruncateAt textEllipsize) {
        this.textEllipsize = textEllipsize;
        return this;
    }

    public int getTextWidth() {
        return textWidth;
    }

    public OopsDefaultLayoutParameter setTextWidth(int textWidth) {
        this.textWidth = textWidth;
        return this;
    }

    public int getTextHeight() {
        return textHeight;
    }

    public OopsDefaultLayoutParameter setTextHeight(int textHeight) {
        this.textHeight = textHeight;
        return this;
    }
}
