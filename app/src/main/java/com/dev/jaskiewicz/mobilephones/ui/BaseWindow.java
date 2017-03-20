package com.dev.jaskiewicz.mobilephones.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.dev.jaskiewicz.mobilephones.R;

/**
 * Okno bazowe zapewnia pasek akcji (toolbar) oraz kontener na fragment
 * Każde okno w tej aplikacji powinno dziedziczyć po tej klasie
 *
 * Poprzez zastosowanie okna bazowego nie trzeba w każdym oknie dodawać toolbara
 * Ponadto wystarczy jeden wspólny layout (xml) dla wszystkich okien
 *
 * NIE UŻYWAC setContentView() w klasach dziedziczących
 */
public abstract class BaseWindow extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_window);
        findToolbar();
        setToolbarTitle();
        setSupportActionBar(toolbar);
        if (savedInstanceState == null) {
            createFragment();
        }
    }

    private void setToolbarTitle() {
        toolbar.setTitle(getWindowTitle());
    }

    protected abstract String getWindowTitle();

    private void findToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    private void createFragment() {
        getFragmentManager()
                .beginTransaction()
                .replace(getFragmentContainerId(), prepareFragmentForThisWindow())
                .commit();
    }

    protected int getFragmentContainerId() {
        return R.id.fragment_container;
    }

    /**
     * Metoda do nadpisania w klasie dziedziczącej
     *
     * @return fragment, który ma być dodany do okna
     */
    protected abstract Fragment prepareFragmentForThisWindow();
}
