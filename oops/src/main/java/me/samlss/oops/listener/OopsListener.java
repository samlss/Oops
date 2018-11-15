package me.samlss.oops.listener;

import android.view.View;

/**
 * @author SamLeung
 * @e-mail samlssplus@gmail.com
 * @github https://github.com/samlss
 * @description Oops listener.
 */
public interface OopsListener {
    /**
     * Will call this method when the layout is showed.
     *
     * @param layout The displaying layout, loading, error or empty...
     * */
    void onShow(View layout);

    /**
     * Will call this method when the layout is dismissed.
     *
     * @param layout The displaying layout, loading, error or empty...
     * */
    void onDismiss(View layout);
}
