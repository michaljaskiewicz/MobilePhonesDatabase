package com.dev.jaskiewicz.mobilephones.ui;

import android.app.ListFragment;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

import com.dev.jaskiewicz.mobilephones.R;
import com.dev.jaskiewicz.mobilephones.data.MobilesContract;
import com.dev.jaskiewicz.mobilephones.data.database.MobilesTable;

public class MobilesFragment extends ListFragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListAdapter(createCursorAdapter());
        setRetainInstance(true);
    }

    private SimpleCursorAdapter createCursorAdapter() {
        return new SimpleCursorAdapter(
                getActivity(),
                R.layout.list_item,
                queryForResult(),
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

    private Cursor queryForResult() {
        return getActivity().getContentResolver().query(
                MobilesContract.CONTENT_URI,
                MobilesTable.namesOfColumns(),
                null,
                null,
                null);
    }
}
