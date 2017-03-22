package com.dev.jaskiewicz.mobilephones.ui;

import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.dev.jaskiewicz.mobilephones.R;
import com.dev.jaskiewicz.mobilephones.data.database.MobilesTable;

import static android.widget.AbsListView.CHOICE_MODE_MULTIPLE_MODAL;
import static com.dev.jaskiewicz.mobilephones.data.MobilesContract.CONTENT_URI;

public class MobilesFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int LOADER_FIRST_ID = 0;
    private ListView listView;
    private SimpleCursorAdapter adapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setUpListView();
        createCursorAdapter();
        setListAdapter(adapter);
        initLoader();
    }

    private void setUpListView() {
        initListView();
        listView.setChoiceMode(CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(createMultiChoiceModeListener());
    }

    private void initListView() {
        listView = getListView();
    }

    private MultiChoiceListener createMultiChoiceModeListener() {
        return new MultiChoiceListener(getActivity(), listView);
    }

    private void createCursorAdapter() {
        adapter = new SimpleCursorAdapter(
                getActivity(),
                R.layout.mobile_list_item,
                null,
                producerAndModelColumns(),
                labelsIDsForProducerAndModel()
        );
    }

    private String[] producerAndModelColumns() {
        return new String[] {MobilesTable.COLUMN_PRODUCER, MobilesTable.COLUMN_MODEL};
    }

    private int[] labelsIDsForProducerAndModel() {
        return new int[] {R.id.list_item_producer_label, R.id.list_item_model_label};
    }

    private void initLoader() {
        getLoaderManager().initLoader(LOADER_FIRST_ID, null, this);
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
