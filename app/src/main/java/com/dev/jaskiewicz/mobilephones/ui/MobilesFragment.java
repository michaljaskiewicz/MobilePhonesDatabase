package com.dev.jaskiewicz.mobilephones.ui;

import android.app.ListFragment;
import android.content.ContentUris;
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

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListAdapter(createCursorAdapter());
        setRetainInstance(true);

        initListView();
        listView.setChoiceMode(CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(this);
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
        long[] idsOfCheckedItems = listView.getCheckedItemIds();

        //TODO
        // delete this String Builder and Toasts
        // for test only
        StringBuilder builder = new StringBuilder();
        builder.append("Zaznaczono: ");
        for (int i = 0; i < idsOfCheckedItems.length; i++) {
            builder.append(idsOfCheckedItems[i]);
            if (i < idsOfCheckedItems.length - 1) {
                builder.append((", "));
            }
        }


        Toast.makeText(getActivity(), "Zaznaczylem: " + listView.getCheckedItemCount() + " elementow listy", Toast.LENGTH_SHORT).show();
        Toast.makeText(getActivity(), builder.toString(), Toast.LENGTH_SHORT).show();
//        Toast.makeText(getActivity(), "We wanna delete this!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {

    }
}
