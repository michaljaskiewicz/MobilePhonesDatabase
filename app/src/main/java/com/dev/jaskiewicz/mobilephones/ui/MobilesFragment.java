package com.dev.jaskiewicz.mobilephones.ui;

import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.dev.jaskiewicz.mobilephones.R;
import com.dev.jaskiewicz.mobilephones.data.database.MobilesTable;

import static android.widget.AbsListView.CHOICE_MODE_MULTIPLE_MODAL;
import static com.dev.jaskiewicz.mobilephones.data.MobilesContract.CONTENT_URI;

public class MobilesFragment extends ListFragment implements AbsListView.MultiChoiceModeListener, LoaderManager.LoaderCallbacks<Cursor> {

    private static final int LOADER_FIRST_ID = 0;
    private ListView listView;
    private SimpleCursorAdapter adapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setUpListView();
        createCursorAdapter();
        setListAdapter(adapter);
        getLoaderManager().initLoader(LOADER_FIRST_ID, null, this);
    }

    private void setUpListView() {
        initListView();
        listView.setChoiceMode(CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(this);
    }

    private void createCursorAdapter() {
        adapter = new SimpleCursorAdapter(
                getActivity(),
                R.layout.list_item,
                null,
                producerAndModelColumns(),
                labelsIDsForProducerAndModel()
        );
    }
    private int[] labelsIDsForProducerAndModel() {
        return new int[] {R.id.list_item_producer_label, R.id.list_item_model_label};
    }

    private String[] producerAndModelColumns() {
        return new String[] {MobilesTable.COLUMN_PRODUCER, MobilesTable.COLUMN_MODEL};
    }

    private void initListView() {
        listView = getListView();
    }

    @Override
    public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {

    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        MenuInflater inflater = mode.getMenuInflater();
        inflater.inflate(R.menu.contextual_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        mode.setTitle(getString(R.string.delete));
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        if (isActionDeleteMobiles(item)) {
            deleteCheckedMobiles();
            mode.finish();
            return true;
        }
        return false;
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
        getActivity().getContentResolver()
                .delete(ContentUris.withAppendedId(CONTENT_URI, id), null, null);
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(
                getActivity(),
                CONTENT_URI,
                MobilesTable.namesOfColumns(),
                null,
                null,
                null
        );

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }
}
