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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ItemViewHolder {

    public static final int ITEM = 0;
    public static final int EDITABLE_ITEM = 1;
    public static final int DETAILED_ITEM = 2;

    private int mLayout;

    private LinearLayout mItem;
    private ImageView mIcon;
    private TextView mText;
    private TextView mDetails;

    private Object mData;

    public ItemViewHolder(int pLayout, LinearLayout pItem) {
        mLayout = pLayout;

        if (mLayout == ITEM || mLayout == EDITABLE_ITEM) {
            mItem = pItem;
            mIcon = (ImageView) mItem.findViewById(R.id.icon);
            mText = (TextView) mText.findViewById(R.id.text);
        } else if (mLayout == DETAILED_ITEM) {
            mItem = pItem;
            mIcon = (ImageView) mItem.findViewById(R.id.icon);
            mText = (TextView) mText.findViewById(R.id.text);
            mDetails = (TextView) mText.findViewById(R.id.details);
        }
    }

    public void setData(Object pData) {
        mData = pData;
    }

    public int getLayout() {
        return mLayout;
    }

    public LinearLayout getItem() {
        return mItem;
    }

    public ImageView getIcon() {
        return mIcon;
    }

    public TextView getText() {
        return mText;
    }

    public TextView getDetails() {
        return mDetails;
    }

    public Object getData() {
        return mData;
    }

    public void theme(Theme pTheme, boolean pSetPassive) {
        mItem.setBackgroundColor(pTheme.getBaseColor());
        if (mData instanceof ActInfo) {
            mIcon.clearColorFilter();
        } else {
            mIcon.setColorFilter(pTheme.getIconColorFilter(pSetPassive));
        }
        mText.setTextColor(pTheme.getTextColor(pSetPassive));
        if (mLayout == EDITABLE_ITEM) {
            mText.setHintTextColor(pTheme.getTextHintColor(pSetPassive));
        }
        if (mLayout == DETAILED_ITEM) {
            mDetails.setTextColor(pTheme.getTextColor(pSetPassive));
        }
    }

    public void setIconDrawable(Drawable pDrawable) {
        mIcon.setImageDrawable(pDrawable);
    }

    public void setTextText(String pText) {
        mText.setText(pText);
    }

    public void setTextHint(String pHint) {
        mText.setHint(pHint);
    }

    public void setDetailsText(String pText) {
        mDetails.setText(pText);
    }

    @Override
    public boolean equals(Object pObject) {
        if (pObject instanceof ItemViewHolder) {
            ItemViewHolder mItemViewHolder = (ItemViewHolder) pObject;

            if (mLayout == ITEM && mItemViewHolder.getLayout() == ITEM) {
                return mItem.equals(mItemViewHolder.getItem()) &&
                        mIcon.equals(mItemViewHolder.getIcon()) &&
                        mText.equals(mItemViewHolder.getText()) &&
                        mData.equals(mItemViewHolder.getData());
            } else if (mLayout == EDITABLE_ITEM && mItemViewHolder.getLayout() == EDITABLE_ITEM) {
                return mItem.equals(mItemViewHolder.getItem()) &&
                        mIcon.equals(mItemViewHolder.getIcon()) &&
                        mText.equals(mItemViewHolder.getText()) &&
                        mData.equals(mItemViewHolder.getData());
            } else if (mLayout == DETAILED_ITEM && mItemViewHolder.getLayout() == DETAILED_ITEM) {
                return mItem.equals(mItemViewHolder.getItem()) &&
                        mIcon.equals(mItemViewHolder.getIcon()) &&
                        mText.equals(mItemViewHolder.getText()) &&
                        mDetails.equals(mItemViewHolder.getDetails()) &&
                        mData.equals(mItemViewHolder.getData());
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        if (mLayout == ITEM || mLayout == EDITABLE_ITEM) {
            return mLayout + mItem.hashCode() + mIcon.hashCode() + mText.hashCode() +
                    mData.hashCode();
        } else {
            return mLayout + mItem.hashCode() + mIcon.hashCode() + mText.hashCode() +
                    mDetails.hashCode() + mData.hashCode();
        }
    }

}
