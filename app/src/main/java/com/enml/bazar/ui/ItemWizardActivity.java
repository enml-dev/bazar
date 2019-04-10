package com.enml.bazar.ui;

import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import com.enml.bazar.ui.fragments.CelularesFragment;
import com.enml.bazar.R;
import com.enml.bazar.ui.fragments.SelectCategoryFragment;

import androidx.appcompat.app.AppCompatActivity;

public class ItemWizardActivity extends AppCompatActivity implements SelectCategoryFragment.OnFragmentInteractionListener,
        CelularesFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_item_wizard);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        } else {
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // finish();
    }
}
