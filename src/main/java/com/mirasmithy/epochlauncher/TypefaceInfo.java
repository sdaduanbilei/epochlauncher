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

import android.content.res.AssetManager;
import android.graphics.Typeface;

public class TypefaceInfo implements Comparable<TypefaceInfo> {

    public static final transient String ASSETS_PATH = "assets://";

    private String mName;
    private String mPath;

    private transient Typeface mTypeface;

    public TypefaceInfo(String pName, String pPath) {
        mName = pName;
        mPath = pPath;
    }

    public boolean setName(String pName) {
        if (mName.equals(pName)) {
            return false;
        } else {
            mName = pName;
            return true;
        }
    }

    public boolean setPath(String pPath) {
        if (mPath.equals(pPath)) {
            return false;
        } else {
            mPath = pPath;
            return true;
        }
    }

    public String getName() {
        return mName;
    }

    public String getPath() {
        return mPath;
    }

    public boolean setTypeface(AssetManager pAssetManager) {
        if (mPath.substring(0,9).equals(ASSETS_PATH)) {
            try {
                mTypeface = Typeface.createFromAsset(pAssetManager, mPath.substring(9));

                return true;
            } catch (Exception pException) {
                return false;
            }
        } else {
            try {
                mTypeface = Typeface.createFromFile(mPath);

                return true;
            } catch (Exception pException) {
                return false;
            }
        }
    }

    public Typeface getTypeface() {
        return mTypeface;
    }

    @Override
    public boolean equals(Object pObject) {
        if (pObject instanceof TypefaceInfo) {
            TypefaceInfo mTypefaceInfo = (TypefaceInfo) pObject;

            return mName.equals(mTypefaceInfo.getName()) && mPath.equals(mTypefaceInfo.getPath());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return mName.hashCode() + mPath.hashCode();
    }

    @Override
    public String toString() {
        return "TypefaceInfo( Name: " + mName + " | Path: " + mPath + " )";
    }

    public int compareTo(TypefaceInfo pTypefaceInfo) {
        return mName.compareTo(pTypefaceInfo.getName());
    }

}
