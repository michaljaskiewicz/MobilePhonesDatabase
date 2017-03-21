package com.dev.jaskiewicz.mobilephones.ui;

import android.app.ListFragment;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.dev.jaskiewicz.mobilephones.R;
import com.dev.jaskiewicz.mobilephones.data.MobilesContract;
import com.dev.jaskiewicz.mobilephones.data.database.MobilesTable;

import static android.widget.AbsListView.CHOICE_MODE_MULTIPLE_MODAL;

public class MobilesFragment extends ListFragment implements AbsListView.MultiChoiceModeListener {

    private ListView listView;
    private SimpleCursorAdapter adapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initListView();
        initAdapter();
        listView.setAdapter(adapter);
        listView.setChoiceMode(CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(this);
        setRetainInstance(true);
    }

    private void initAdapter() {
        adapter = createCursorAdapter();
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

    private void initListView() {
        listView = getListView();
    }

    @Override
    public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
        if (checked) {

        }
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        MenuInflater inflater = mode.getMenuInflater();
        inflater.inflate(R.menu.contextual_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        if (isActionDeleteMobiles(item)) {
            deleteCheckedMobiles();
            return true;
        }
        return false;
    }

    private boolean isActionDeleteMobiles(MenuItem item) {
        return item.getItemId() == R.id.action_delete_mobiles;
    }

    private void deleteCheckedMobiles() {
        Toast.makeText(getActivity(), "Zaznaczylem: " + listView.getCheckedItemCount() + " elementow listy", Toast.LENGTH_SHORT).show();
//        Toast.makeText(getActivity(), "We wanna delete this!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {

    }
}
