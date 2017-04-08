package com.dev.jaskiewicz.mobilephones.ui.mainWindow;

import android.content.ContentUris;
import android.content.Context;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ListView;

import com.dev.jaskiewicz.mobilephones.R;

import static com.dev.jaskiewicz.mobilephones.data.MobilesContract.CONTENT_URI;

/**
 * Służy jako listener wyboru wielu elementów listy
 * Przechowuje informacje o tym ile elementów zostało zaznaczonych
 */
public class MultiChoiceListener implements AbsListView.MultiChoiceModeListener {

    private final Context context;
    private final ListView listView;
    private int numberOfCheckedItems;

    public MultiChoiceListener(Context context, ListView listView) {
        this.context = context;
        this.listView = listView;
    }

    @Override
    public void onItemCheckedStateChanged(ActionMode contextualActionBar, int position, long id, boolean itemIsNowChecked) {
        countNumberOfCheckedItemsBasedOn(itemIsNowChecked);
        displayNumberOfCheckedItemsOn(contextualActionBar);
    }

    private void countNumberOfCheckedItemsBasedOn(boolean itemIsNowChecked) {
        if (itemIsNowChecked) {
            numberOfCheckedItems++;
        } else {
            numberOfCheckedItems--;
        }
    }

    /**
     * Wyświetlam liczbę zaznaczonych elementów tylko, gdy są większe od zera.
     * Nie chcę, aby przed chowaniem paska kontekstowego widać było zmianę tytułu z 1 na 0
     */
    private void displayNumberOfCheckedItemsOn(ActionMode contextualActionBar) {
        if (anyItemChecked()) {
            contextualActionBar.setTitle(numberOfCheckedItemsTitle());
        }
    }

    private boolean anyItemChecked() {
        return numberOfCheckedItems > 0;
    }

    private String numberOfCheckedItemsTitle() {
        return String.valueOf(numberOfCheckedItems);
    }

    /**
     * Przy tworzeniu paska kontekstowego
     * określam jakie opcje menu mają się na nim znaleźć
     */
    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        initNumberOfCheckedItems();
        MenuInflater inflater = mode.getMenuInflater();
        inflater.inflate(R.menu.contextual_menu, menu);
        return true;
    }

    /**
     * Metoda używana po to, aby po obrocie ekranu wyświetlała się poprawna liczba zaznaczonych elementów listy
     */
    private void initNumberOfCheckedItems() {
        numberOfCheckedItems = listView.getCheckedItemCount();
    }

    @Override
    public boolean onPrepareActionMode(ActionMode contextualActionBar, Menu menu) {
        displayNumberOfCheckedItemsOn(contextualActionBar);
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode contextualActionBar, MenuItem item) {
        if (isActionDeleteMobiles(item)) {
            deleteCheckedMobiles();
            close(contextualActionBar);
            return true;
        }
        return false;
    }

    private void close(ActionMode mode) {
        mode.finish();
    }

    private boolean isActionDeleteMobiles(MenuItem item) {
        return item.getItemId() == R.id.action_delete_mobiles;
    }

    private void deleteCheckedMobiles() {
        long[] idsOfCheckedMobiles = listView.getCheckedItemIds();
        for (long id : idsOfCheckedMobiles) {
            deleteMobile(id);
        }
    }

    private void deleteMobile(long id) {
        context.getContentResolver()
                .delete(ContentUris.withAppendedId(CONTENT_URI, id), null, null);
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        // nie potrzebuje implementacji
    }
}
