package me.samlss.oops_demo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.iigo.library.WhirlLoadingView;

import java.util.ArrayList;
import java.util.List;

import me.samlss.oops.Oops;
import me.samlss.oops.listener.OopsListener;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init
        //When you decide which activity or viewgroup to display the layout,
        //you can perform some global initialization behavior. Of course, these settings can be modified later.
        Oops.with(this)
                .setGravity(Gravity.CENTER)
                .setOopsListener(mOopsListener);

        showLoading();
        initListView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //When you do not need to show the layout(empty, error, loading,etc.) anymore
        //Please invoke the method - release.
        Oops.with(this).release();
    }

    private void initListView(){
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++){
            list.add("item"+i+"   \uD83D\uDD2B \uD83D\uDD2B");
        }

        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, list));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.loading:
                showLoading();
                break;

            case R.id.empty:
                showEmpty();
                break;

            case R.id.error:
                showError();
                break;

            case R.id.data:
                showData();
                break;

            case R.id.dismiss:
                Oops.with(this).dismiss();
                break;

            /**
             * You can start the activity again to test for memory leaks.
             * */
            case R.id.again:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Show the loading layout.
     * */
    private void showLoading(){
        Oops.with(this)
                .setIntercept(false)
                .setBackgroundColor(Color.WHITE)
                .show(R.layout.layout_loading)
                .setLayoutClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "on loading click...", Toast.LENGTH_SHORT).show();
                    }
                })
                .setAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                    @Override
                    public void onViewAttachedToWindow(View v) {
                    }

                    @Override
                    public void onViewDetachedFromWindow(View v) {
                        //Here allows you to do some release actions when the view is detached.
                        v.removeOnAttachStateChangeListener(this);
                        ((WhirlLoadingView)v.findViewById(R.id.wlv_loading)).release();
                    }
                });
    }

    /**
     * Show the empty layout.
     * */
    private void showEmpty(){
        Oops.with(this)
                .setIntercept(false)
                .setBackgroundColor(Color.WHITE)
                .show(R.layout.layout_empty)
                .setLayoutClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "on empty click...", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    /**
     * Show the error layout.
     * */
    private void showError(){
        View errorView = getLayoutInflater().inflate(R.layout.layout_no_net_error, null);
        Oops.with(this)
                .setBackgroundColor(Color.WHITE)
                .show(errorView)
                .setLayoutChildClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "You click retry...", Toast.LENGTH_SHORT).show();
                    }
                }, R.id.btn_retry)
                .setLayoutChildClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "You click check network...", Toast.LENGTH_SHORT).show();
                    }
                    }, R.id.btn_check);
    }

    private void showData() {
        Oops.with(this)
                .setIntercept(true)
                .setBackgroundColor(0x9A000000)
                .show(R.layout.layout_loading2);
    }

    private OopsListener mOopsListener = new OopsListener() {
        @Override
        public void onShow(View layout) {
            Toast.makeText(getApplicationContext(), "oops show...", Toast.LENGTH_SHORT).show();

            if (layout != null &&
                    layout.findViewById(R.id.wlv_loading) != null){
                ((WhirlLoadingView)layout.findViewById(R.id.wlv_loading)).start();
            }
        }

        @Override
        public void onDismiss(View layout) {
            Toast.makeText(getApplicationContext(), "oops dismiss...", Toast.LENGTH_SHORT).show();

            if (layout != null &&
                    layout.findViewById(R.id.wlv_loading) != null){
                ((WhirlLoadingView)layout.findViewById(R.id.wlv_loading)).stop();
            }
        }
    };
}

