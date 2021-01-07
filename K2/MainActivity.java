package com.example.kontrolinis2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        showSearchGroup1();
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.context_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.option1:
                showSearchGroup1();
                break;
            case R.id.option2:
            case R.id.option3:
                showSearchGroup2();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showSearchGroup2() {
        FragmentManager fm = getSupportFragmentManager();

        Fragment fragment = new NavigationFragment();
        Bundle bundle = new Bundle();
        bundle.putString("text1", getString(R.string.firstAddressGroup2));
        bundle.putString("text2", getString(R.string.secondAddressGroup2));
        bundle.putString("text3", getString(R.string.thirdAddressGroup2));
        fragment.setArguments(bundle);

        this.openFragment(fragment, R.id.navFragment, true);
    }

    private void showSearchGroup1() {
        FragmentManager fm = getSupportFragmentManager();

        Fragment fragment = new NavigationFragment();
        Bundle bundle = new Bundle();
        bundle.putString("text1", getString(R.string.firstAddress));
        bundle.putString("text2", getString(R.string.secondAddress));
        bundle.putString("text3", getString(R.string.thirdAddress));
        fragment.setArguments(bundle);

        this.openFragment(fragment, R.id.navFragment, true);
    }

    private void openFragment(Fragment fragment, int replaceId, boolean addToBackStack) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(replaceId, fragment);
        if(addToBackStack)
            ft.addToBackStack("back");

        ft.commit();
    }
}