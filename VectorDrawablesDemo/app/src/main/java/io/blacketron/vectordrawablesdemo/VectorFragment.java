package io.blacketron.vectordrawablesdemo;

import android.app.ListFragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

public class VectorFragment extends ListFragment {

    private static final Integer[] VECTORS={
            R.drawable.ic_account_circle,
            R.drawable.ic_check_circle_24px,
            R.drawable.ic_corp_badge,
            R.drawable.ic_corp_icon_badge,
            R.drawable.ic_corp_statusbar_icon,
            R.drawable.ic_eject_24dp,
            R.drawable.ic_expand_more_48dp,
            R.drawable.ic_folder_24dp,
            R.drawable.ic_more_items,
            R.drawable.ic_perm_device_info,
            R.drawable.ic_sd_card_48dp,
            R.drawable.ic_settings_24dp,
            R.drawable.ic_storage_48dp,
            R.drawable.ic_usb_48dp
    };

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setListAdapter(new VectorAdapter());
    }

    void applyIcon(ImageView icon, int resourceId){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){

            icon.setImageResource(resourceId);
        }
    }

    private class VectorAdapter extends ArrayAdapter<Integer>{

        VectorAdapter(){

            super(getActivity(), R.layout.row, R.id.title, VECTORS);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            View row = super.getView(position, convertView, parent);
            ImageView icon = row.findViewById(R.id.icon);
            TextView title = row.findViewById(R.id.title);

            applyIcon(icon, getItem(position));

            title.setText(getResources().getResourceName(getItem(position)));

            return (row);
        }
    }
}

