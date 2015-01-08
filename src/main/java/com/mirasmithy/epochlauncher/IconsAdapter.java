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

import java.util.ArrayList;
import java.util.HashSet;

public class IconsAdapter extends ArrayAdapter {

    LayoutInflater mLayoutInflater;
    ArrayList mIconsData;
    View.OnTouchListener mIconsOnTouchListener;
    Theme mTheme;
    HashSet mActiveIconsData;

    public IconsAdapter(Context pContext, ArrayList pIconsData,
                        View.OnTouchListener pIconsOnTouchListener, Theme pTheme,
                        HashSet pActiveIconsData) {
        super(pContext, R.layout.icon, pIconsData);

        mLayoutInflater =
                (LayoutInflater) pContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mIconsData = pIconsData;
        mIconsOnTouchListener = pIconsOnTouchListener;
        mTheme = pTheme;
        mActiveIconsData = pActiveIconsData;
    }

    public void setTheme(Theme pTheme) {
        mTheme = pTheme;
    }

    public void addToIconsData(ArrayList pData) {
        mIconsData.addAll(pData);
    }

    public Object getFromIconsData(int pIndex) {
        return mIconsData.get(pIndex);
    }

    public void clearIconsData() {
        mIconsData.clear();
    }

    public void addToActiveIconsData(HashSet pData) {
        mActiveIconsData.addAll(pData);
    }

    public void clearActiveIconsData() {
        mActiveIconsData.clear();
    }

    @Override
    public int getItemViewType(int pPosition) {
        if (mIconsData.get(pPosition) instanceof Drawable) {
            return IconViewHolder.ICON;
        } else {
            return IconViewHolder.ACTIVITY_ICON;
        }
    }

    @Override
    public View getView(int pPosition, View pView, ViewGroup pViewGroup) {
        IconViewHolder mIconViewHolder;
        Object mData = mIconsData.get(pPosition);

        if (pView == null) {
            int mItemViewType = getItemViewType(pPosition);

            if (mItemViewType == IconViewHolder.ICON) {
                pView = mLayoutInflater.inflate(R.layout.icon, pViewGroup, false);
            } else if (mItemViewType == IconViewHolder.ACTIVITY_ICON) {
                pView = mLayoutInflater.inflate(R.layout.activity_icon, pViewGroup, false);
            }

            pView.setOnTouchListener(mIconsOnTouchListener);

            mIconViewHolder = new IconViewHolder(mItemViewType, pView);

            pView.setTag(mIconViewHolder);
        } else {
            mIconViewHolder = (IconViewHolder) pView.getTag();
        }

        mIconViewHolder.setData(mData);

        mIconViewHolder.theme(mTheme, !mActiveIconsData.contains(mData));

        if (mData instanceof Drawable) {
            mIconViewHolder.setIconDrawable((Drawable) mData);
        } else if (mData instanceof ActInfo) {
            mIconViewHolder.setIconDrawable(((ActInfo) mData).getIcon());
        }

        return pView;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

}
