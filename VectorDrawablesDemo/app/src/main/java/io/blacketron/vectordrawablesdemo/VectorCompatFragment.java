package io.blacketron.vectordrawablesdemo;

import android.graphics.drawable.Drawable;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.widget.ImageView;

public class VectorCompatFragment extends VectorFragment {

    @Override
    void applyIcon(ImageView icon, int resourceId) {

        Drawable d = VectorDrawableCompat.create(getResources(), resourceId, null);

        icon.setImageDrawable(d);
    }
}
