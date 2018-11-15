package me.samlss.oops;

import android.app.Activity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.HashMap;
import java.util.Map;

import me.samlss.oops.listener.OopsListener;
import me.samlss.oops.util.Preconditions;
import me.samlss.oops.view.BackgroundView;

/**
 * @author SamLeung
 * @e-mail samlssplus@gmail.com
 * @github https://github.com/samlss
 * @description A library for android that help to show the layout of loading, error, empty etc.
 */
public class Oops {
    private final static String TAG = "Oops";
    private static Map<ViewGroup, Oops> sOops = new HashMap<>();

    private ViewGroup mViewGroup;
    private BackgroundView mBackgroundView;
    private boolean isShowing = false;
    private int mGravity = Gravity.CENTER;
    private boolean isCanceledOnTouchOutside = false;
    private OopsListener mOopsListener;
    private View mDisplayLayout;

    private Oops(ViewGroup viewGroup){
        mViewGroup = viewGroup;

        mBackgroundView = new BackgroundView(viewGroup.getContext());
        mBackgroundView.setVisibility(View.GONE);
        mBackgroundView.setOnClickListener(mBackgroundClickListener);

        viewGroup.addView(mBackgroundView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
    }

    /**
     * The layout will be attached to the content view of {@link Activity}.
     *
     * @param activity The attached activity.
     * */
    public static Oops with(Activity activity){
        Preconditions.checkNotNull(activity, "Please pass a non-null activity!");
        ViewGroup viewGroup = activity.findViewById(android.R.id.content);
        Oops oops = get(viewGroup);
        if (oops == null){
            oops = new Oops(viewGroup);
            sOops.put(oops.mViewGroup, oops);
            Log.e(TAG, "Need to create oops..");
        }else{
            Log.e(TAG, "Do not need to create oops..");
        }

        return oops;
    }

    /**
     * The layout will be attached to the ViewGroup.
     *
     * @param viewGroup The attached viewgroup.
     * */
    public static Oops with(ViewGroup viewGroup){
        Preconditions.checkNotNull(viewGroup, "Please pass a non-null view group!");
        Oops oops = get(viewGroup);
        if (oops == null){
            oops = new Oops(viewGroup);
            sOops.put(viewGroup, oops);
        }

        return oops;
    }

    /**
     * When invoke {@link #with(Activity)} or {@link #with(ViewGroup)},
     * Firstly, will call this method to get the Oops object, if not exists, will create a new Oops object.
     * */
    private static Oops get(ViewGroup viewGroup){
        if (viewGroup == null){
            return null;
        }

        return sOops.get(viewGroup);
    }

    /**
     * Set the layout position that you want to display.
     *
     * @param gravity The gravity value.
     * */
    public Oops setGravity(int gravity){
        mGravity = gravity;
        return this;
    }

    /**
     * Set the background color of layout.
     * */
    public Oops setBackgroundColor(int color){
        mBackgroundView.setBackgroundColor(color);
        return this;
    }

    /**
     * Sets whether this layout is canceled when touched outside the layout's
     * bounds. If setting to true, the layout is set to be cancelable if not
     * already set.
     *
     * @param cancel Whether the layout should be canceled when touched outside
     *            the layout.
     */
    public Oops setCanceledOnTouchOutside(boolean cancel){
        this.isCanceledOnTouchOutside = cancel;
        return this;
    }

    /**
     * Set the layout of loading, empty, error...
     *
     * @param view The layout view.
     * */
    public Oops show(View view){
        showLayout(view);
        return this;
    }


    /**
     * Set the layout resource of loading, empty, error...
     *
     * @param layoutRes The layout resource id.
     * */
    public Oops show(int layoutRes){
        showLayout(LayoutInflater.from(mViewGroup.getContext())
                .inflate(layoutRes, mBackgroundView, false));
        return this;
    }

    /**
     * Will show the previous layout.<br>
     *
     * This method is invalid if {@link #show(int)} and {@link #show(View)}.
     * has not been called before.
     * */
    public void show(){
        if (getDisplayLayout() == null){
            return;
        }

        isShowing = true;
        if (mBackgroundView.getVisibility() != View.VISIBLE) {
            mBackgroundView.setVisibility(View.VISIBLE);
        }

        if (mOopsListener != null){
            mOopsListener.onShow(mDisplayLayout);
        }
    }

    /**
     * Whether to intercept external click events.
     *
     * @param intercept If intercept is true, the method {@link #setCanceledOnTouchOutside(boolean)} will be invalid.
     * */
    public Oops setIntercept(boolean intercept) {
        mBackgroundView.setClickable(!intercept);
        return this;
    }

    /**
     * Set listener for the layout.
     * */
    public Oops setOopsListener(OopsListener oopsListener) {
        this.mOopsListener = oopsListener;
        return this;
    }

    /**
     * Set click listener for layout. <br>
     * This method needs to be called after {@link #show(View)} or {@link #show(int)} (View).
     *
     * @param clickListener The click listener.
     * */
    public Oops setLayoutClickListener(View.OnClickListener clickListener){
        if (clickListener != null
                && mDisplayLayout != null){
            mDisplayLayout.setOnClickListener(clickListener);
        }

        return this;
    }

    /**
     * Set click listener for the child of layout.
     * This method needs to be called after {@link #show(View)} or {@link #show(int)} (View)}.
     *
     * @param clickListener The click listener.
     * @param childViewId The child view id.
     * */
    public Oops setLayoutChildClickListener(View.OnClickListener clickListener, int childViewId){
        if (clickListener != null
                && mDisplayLayout != null
                && mDisplayLayout.findViewById(childViewId) != null){
            mDisplayLayout.findViewById(childViewId).setOnClickListener(clickListener);
        }

        return this;
    }

    /**
     * Set attach state change listener for the layout.
     * This method needs to be called after {@link #show(View)} or {@link #show(int)} (View)}.
     *
     * @param attachStateChangeListener The attach state change listener.
     * */
    public Oops setAttachStateChangeListener(View.OnAttachStateChangeListener attachStateChangeListener){
        if (getDisplayLayout() != null){
            getDisplayLayout().addOnAttachStateChangeListener(attachStateChangeListener);
        }

        return this;
    }

    /**
     * Get the layout of loading, empty, error, etc... you set.
     * */
    public View getDisplayLayout() {
        return mDisplayLayout;
    }

    /**
     * Check if the layout is showing.
     * */
    public boolean isShowing() {
        return isShowing;
    }

    /**
     * Dismiss the layout, different from {@link #removeLayout()}, {@link #dismiss()} sets the layout to invisible.
     * */
    public void dismiss(){
        if (!isShowing){
            return;
        }

        isShowing = false;
        mBackgroundView.setVisibility(View.GONE);

        if (mOopsListener != null){
            mOopsListener.onDismiss(mDisplayLayout);
        }
    }

    private void showLayout(View layout){
        if (layout == null){
            Log.w(TAG, "You have not specified the layout to display.");
            return;
        }

        removeRealLayout();
        mDisplayLayout = layout;

        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) mDisplayLayout.getLayoutParams();
        if (layoutParams == null){
            layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        layoutParams.gravity = mGravity;
        mBackgroundView.addView(mDisplayLayout, layoutParams);
        show();
    }

    /**
     * In this library, have a background view{@link #mBackgroundView}, your layout is attached to the background view,
     * and this method will remove the layout from the background.
     * */
    private void removeRealLayout(){
        if (getDisplayLayout() != null
                && getDisplayLayout().getParent() != null){
            mBackgroundView.removeView(getDisplayLayout());
        }
    }

    /**
     * Remove the layout, different from {@link #dismiss()} ()}, {@link #removeLayout()} ()} will remove the layout from parent
     *  {@link #with(ViewGroup)} or {@link #with(Activity)}'s "findViewById(android.R.id.content)".
     * */
    public void removeLayout(){
        if (mBackgroundView.getParent() != null){
            ((ViewGroup)mBackgroundView.getParent()).removeView(mBackgroundView);
        }
    }

    /**
     * Release the current activity or viewgroup reference. <br>
     * You can call it manually, or it will be call until the activity or viewgroup detached from the window.
     * */
    public void release(){
        removeRealLayout();
        removeLayout();

        if (mViewGroup != null) {
            sOops.remove(mViewGroup);
            mViewGroup = null;
        }

        mOopsListener = null;
        Log.e(TAG, "release : "+sOops.size());
    }

    /**
     * The listener of background view, if you set {@link #isCanceledOnTouchOutside} as true,
     * when the background view is clicked, will call {@link #dismiss()}.But, will according the
     * value {@link #setIntercept(boolean)}, if intercept is true, this click listener will be not invalid.
     * */
    private View.OnClickListener mBackgroundClickListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            if (isShowing && isCanceledOnTouchOutside) {
                dismiss();
            }
        }
    };
}
