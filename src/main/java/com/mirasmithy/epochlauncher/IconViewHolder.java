/**
 * Epoch Launcher, a free software launcher for Android™, inspired by Sword Art Online.
 * Copyright (C) 2015  Miras Absar
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.mirasmithy.epochlauncher;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

public class IconViewHolder {

    public static final int ICON = 0;
    public static final int ACTIVITY_ICON = 1;

    private int mLayout;

    private ImageView mBase;
    private ImageView mItem;
    private ImageView mIcon;

    private Object mData;

    public IconViewHolder(int pLayout, View pView) {
        mLayout = pLayout;

        if (mLayout == ICON) {
            mBase = (ImageView) pView.findViewById(R.id.base);
            mItem = (ImageView) pView.findViewById(R.id.item);
            mIcon = (ImageView) pView.findViewById(R.id.icon);
        } else if (mLayout == ACTIVITY_ICON) {
            mIcon = (ImageView) pView;
        }
    }

    public void setData(Object pData) {
        mData = pData;
    }

    public Object getData() {
        return mData;
    }

    public void theme(Theme pTheme, boolean pSetPassive) {
        if (mLayout == ICON) {
            mBase.setColorFilter(pTheme.getBaseColorFilter());
            mItem.setColorFilter(pTheme.getItemColorFilter(pSetPassive));
            mIcon.setColorFilter(pTheme.getIconColorFilter(pSetPassive));
        }
    }

    public void setIconDrawable(Drawable pDrawable) {
        mIcon.setImageDrawable(pDrawable);
    }

}
