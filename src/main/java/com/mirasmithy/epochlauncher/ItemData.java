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

public class ItemData {

    public static final int ITEM = 0;
    public static final int EDITABLE_ITEM = 1;
    public static final int DETAILED_ITEM = 2;

    private int mLayout;
    private Drawable mIcon;
    private String mText;
    private String mTextHint;
    private String mDetails;

    private ItemData(int pLayout, Drawable pIcon, String pText, String pTextHint, String pDetails) {
        mLayout = pLayout;
        mIcon = pIcon;
        mText = pText;
        mTextHint = pTextHint;
        mDetails = pDetails;
    }

    public static ItemData newItemData(Drawable pIcon, String pText) {
        return new ItemData(ITEM, pIcon, pText, null, null);
    }

    public static ItemData newEditableItemData(Drawable pIcon, String pText, String pTextHint) {
        return new ItemData(EDITABLE_ITEM, pIcon, pText, pTextHint, null);
    }

    public static ItemData newDetailedItemData(Drawable pIcon, String pText, String pDetails) {
        return new ItemData(DETAILED_ITEM, pIcon, pText, null, pDetails);
    }

    public void setIcon(Drawable pIcon) {
        mIcon = pIcon;
    }

    public void setText(String pText) {
        mText = pText;
    }

    public void setTextHint(String pTextHint) {
        mTextHint = pTextHint;
    }

    public void setDetails(String pDetails) {
        mDetails = pDetails;
    }

    public int getLayout() {
        return mLayout;
    }

    public Drawable getIcon() {
        return mIcon;
    }

    public String getText() {
        return mText;
    }

    public String getTextHint() {
        return mTextHint;
    }

    public String getDetails() {
        return mDetails;
    }

    @Override
    public boolean equals(Object pObject) {
        if (pObject instanceof ItemData) {
            ItemData mItemData = (ItemData) pObject;

            if (mLayout == ITEM && mItemData.getLayout() == ITEM) {
                return mIcon.equals(mItemData.getIcon()) && mText.equals(mItemData.getText());
            } else if (mLayout == EDITABLE_ITEM && mItemData.getLayout() == EDITABLE_ITEM) {
                return mIcon.equals(mItemData.getIcon()) && mText.equals(mItemData.getText()) &&
                        mTextHint.equals(mItemData.getTextHint());
            } else if (mLayout == DETAILED_ITEM && mItemData.getLayout() == DETAILED_ITEM) {
                return mIcon.equals(mItemData.getIcon()) && mText.equals(mItemData.getText()) &&
                        mDetails.equals(mItemData.getDetails());
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        if (mLayout == ITEM) {
            return mIcon.hashCode() + mText.hashCode();
        } else if (mLayout == EDITABLE_ITEM) {
            return mIcon.hashCode() + mText.hashCode() + mTextHint.hashCode();
        } else {
            return mIcon.hashCode() + mText.hashCode() + mDetails.hashCode();
        }
    }

    @Override
    public String toString() {
        if (mLayout == ITEM) {
            return "ItemData( Layout: " + Integer.toString(mLayout) + " | Text: " + mText + " )";
        } else if (mLayout == EDITABLE_ITEM) {
            return "ItemData( Layout: " + Integer.toString(mLayout) + " | Text: " + mText +
                    " | Text Hint: " + mTextHint + " )";
        } else {
            return "ItemData( Layout: " + Integer.toString(mLayout) + " | Text: " + mText +
                    " | Details: " + mDetails + " )";
        }
    }

}
