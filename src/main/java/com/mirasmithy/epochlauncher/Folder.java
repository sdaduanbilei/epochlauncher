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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class Folder implements Comparable<Folder> {

    private String mName;
    private ArrayList<ActInfo> mContents;

    public Folder(String pName, ArrayList<ActInfo> pContents) {
        mName = pName;
        mContents = pContents;
    }

    public boolean setName(String pName) {
        if (mName.equals(pName)) {
            return false;
        } else {
            mName = pName;
            return true;
        }
    }

    public boolean setContents(ArrayList<ActInfo> pContents) {
        if (mContents.equals(pContents)) {
            return false;
        } else {
            mContents = pContents;
            return true;
        }
    }

    public String getName() {
        return mName;
    }

    public ArrayList<ActInfo> getContents() {
        return mContents;
    }

    public boolean clean(HashSet<ActInfo> pActInfoDatabase) {
        boolean mModified = false;

        for (int pIndex = mContents.size() - 1; pIndex >= 0; pIndex--) {
            if (!mContents.get(pIndex).isEnabled(pActInfoDatabase)) {
                mContents.remove(pIndex);
                mModified = true;
            }
        }

        return mModified;
    }

    public void setIconsAndLabels(HashMap<ActInfo, Drawable> pActInfoIconDatabase,
                                  HashMap<ActInfo, String> pActInfoLabelDatabase) {

        for (int pIndex = mContents.size() - 1; pIndex >= 0; pIndex--) {
            mContents.get(pIndex).setIconAndLabel(pActInfoIconDatabase, pActInfoLabelDatabase);
        }
    }

    public boolean add(ActInfo pActInfo) {
        if (mContents.contains(pActInfo)) {
            return false;
        } else {
            mContents.add(pActInfo);
            Collections.sort(mContents);
            return true;
        }
    }

    public boolean remove(ActInfo pActInfo) {
        return mContents.remove(pActInfo);
    }

    public boolean isEmpty() {
        return mContents.isEmpty();
    }

    @Override
    public boolean equals(Object pObject) {
        if (pObject instanceof Folder) {
            Folder mFolder = (Folder) pObject;

            return mName.equals(mFolder.getName()) && mContents.equals(mFolder.getContents());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return mName.hashCode() + mContents.hashCode();
    }

    @Override
    public String toString() {
        return "Folder( Name: " + mName + " | Contents: " + mContents.toString() + " )";
    }

    public int compareTo(Folder pFolder) {
        return mName.compareTo(pFolder.getName());
    }

}
