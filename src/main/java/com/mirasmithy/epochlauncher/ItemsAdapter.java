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

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.HashSet;

public class ItemsAdapter extends ArrayAdapter {

    LayoutInflater mLayoutInflater;
    ArrayList mItemsData;
    View.OnTouchListener mItemsOnTouchListener;
    Theme mTheme;
    TypefaceInfo mTypefaceInfo;
    Drawable mFolder;
    Drawable mPalette;
    HashSet mActiveItemsData;

    public ItemsAdapter(Context pContext, ArrayList pItemsData,
                        View.OnTouchListener pItemsOnTouchListener, Theme pTheme,
                        TypefaceInfo pTypefaceInfo, HashSet pActiveItemsData) {

        super(pContext, R.layout.item, R.id.text, pItemsData);

        mLayoutInflater =
                (LayoutInflater) pContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        mItemsData = pItemsData;
        mItemsOnTouchListener = pItemsOnTouchListener;
        mTheme = pTheme;
        mTypefaceInfo = pTypefaceInfo;
        mFolder = pContext.getResources().getDrawable(R.drawable.folder);
        mPalette = pContext.getResources().getDrawable(R.drawable.palette);
        mActiveItemsData = pActiveItemsData;
    }

    public void setTheme(Theme pTheme) {
        mTheme = pTheme;
    }

    public void setTypefaceInfo(TypefaceInfo pTypefaceInfo) {
        mTypefaceInfo = pTypefaceInfo;
    }

    public void addToItemsData(ArrayList pData) {
        mItemsData.addAll(pData);
    }

    public Object getFromItemsData(int pIndex) {
        return mItemsData.get(pIndex);
    }

    public void clearItemsData() {
        mItemsData.clear();
    }

    public void addToActiveItemsData(HashSet pData) {
        mActiveItemsData.addAll(pData);
    }

    public void clearActiveItemsData() {
        mActiveItemsData.clear();
    }

    @Override
    public int getItemViewType(int pPosition) {
        Object mData = mItemsData.get(pPosition);

        if (mData instanceof ItemData) {
            return ((ItemData) mData).getLayout();
        } else {
            return ItemViewHolder.ITEM;
        }
    }

    @Override
    public View getView(int pPosition, View pView, ViewGroup pViewGroup) {
        ItemViewHolder mItemViewHolder;
        Object mData = mItemsData.get(pPosition);

        if (pView == null) {
            int mItemViewType = getItemViewType(pPosition);
            if (mItemViewType == ItemViewHolder.ITEM) {
                pView = mLayoutInflater.inflate(R.layout.item, pViewGroup, false);
            } else if (mItemViewType == ItemViewHolder.EDITABLE_ITEM) {
                pView = mLayoutInflater.inflate(R.layout.editable_item, pViewGroup, false);
            } else if (mItemViewType == ItemViewHolder.DETAILED_ITEM) {
                pView = mLayoutInflater.inflate(R.layout.detailed_item, pViewGroup, false);
            }

            pView.setOnTouchListener(mItemsOnTouchListener);

            mItemViewHolder = new ItemViewHolder(mItemViewType, (LinearLayout) pView);
            pView.setTag(mItemViewHolder);
        } else {
            mItemViewHolder = (ItemViewHolder) pView.getTag();
        }

        mItemViewHolder.setData(mData);

        mItemViewHolder.theme(mTheme, !mActiveItemsData.contains(mData));
        mItemViewHolder.setTypefaceInfo(mTypefaceInfo);

        if (mData instanceof ActInfo) {
            ActInfo mActInfo = (ActInfo) mData;

            mItemViewHolder.setIconDrawable(mActInfo.getIcon());
            mItemViewHolder.setTextText(mActInfo.getLabel());
        } else if (mData instanceof Folder) {
            mItemViewHolder.setIconDrawable(mFolder);
            mItemViewHolder.setTextText(((Folder) mData).getName());
        } else if (mData instanceof Theme) {
            mItemViewHolder.setIconDrawable(mPalette);
            mItemViewHolder.setTextText(((Theme) mData).getName());
        } else if (mData instanceof TypefaceInfo) {
            mItemViewHolder.setIconDrawable(mPalette);
            mItemViewHolder.setTextText(((TypefaceInfo) mData).getName());
        } else if (mData instanceof ItemData) {
            ItemData mItemData = (ItemData) mData;

            mItemViewHolder.setIconDrawable(mItemData.getIcon());
            mItemViewHolder.setTextText(mItemData.getText());
            if (mItemData.getLayout() == ItemViewHolder.EDITABLE_ITEM) {
                mItemViewHolder.setTextHint(mItemData.getTextHint());
            } else if (mItemData.getLayout() == ItemViewHolder.DETAILED_ITEM) {
                mItemViewHolder.setDetailsText(mItemData.getDetails());
            }
        }

        return pView;
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

}
