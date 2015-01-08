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

import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;

public class Theme implements Comparable<Theme> {

    private static final transient int BASE_ALPHA = 0x40;
    private static final transient int ITEM_ALPHA = 0xFF;
    private static final transient int ICON_ALPHA = 0xFF;
    private static final transient int TEXT_ALPHA = 0xFF;
    private static final transient int TEXT_HINT_ALPHA = 0x40;

    private String mName;
    private int mColor;
    private int mAccentColor;

    private transient int mBaseColor;
    private transient PorterDuffColorFilter mBaseColorFilter;
    private transient int mPassiveItemColor;
    private transient int mActiveItemColor;
    private transient PorterDuffColorFilter mPassiveItemColorFilter;
    private transient PorterDuffColorFilter mActiveItemColorFilter;
    private transient PorterDuffColorFilter mPassiveIconColorFilter;
    private transient PorterDuffColorFilter mActiveIconColorFilter;
    private transient int mPassiveTextColor;
    private transient int mActiveTextColor;
    private transient int mPassiveTextHintColor;
    private transient int mActiveTextHintColor;

    public Theme(String pName, int pColor, int pAccentColor) {
        mName = pName;
        mColor = pColor;
        mAccentColor = pAccentColor;
    }

    public boolean setName(String pName) {
        if (mName.equals(pName)) {
            return false;
        } else {
            mName = pName;

            return true;
        }
    }

    public boolean setColor(int pColor) {
        if (mColor == pColor) {
            return false;
        } else {
            mColor = pColor;

            return true;
        }
    }

    public boolean setAccentColor(int pAccentColor) {
        if (mAccentColor == pAccentColor) {
            return false;
        } else {
            mAccentColor = pAccentColor;

            return true;
        }
    }

    public String getName() {
        return mName;
    }

    public int getColor() {
        return mColor;
    }

    public int getAccentColor() {
        return mAccentColor;
    }

    private int setAlpha(int pColor, int pAlpha) {
        return (pAlpha << 24) | (0xFFFFFF & pColor);
    }

    public void setColors() {
        int mComplementaryColor = ~ mColor;

        mBaseColor = setAlpha(mColor, BASE_ALPHA);
        mBaseColorFilter = new PorterDuffColorFilter(mBaseColor, PorterDuff.Mode.MULTIPLY);
        mPassiveItemColor = setAlpha(mColor, ITEM_ALPHA);
        mActiveItemColor = setAlpha(mAccentColor, ITEM_ALPHA);
        mPassiveItemColorFilter = new PorterDuffColorFilter(mPassiveItemColor,
                PorterDuff.Mode.MULTIPLY);
        mActiveItemColorFilter = new PorterDuffColorFilter(mActiveItemColor,
                PorterDuff.Mode.MULTIPLY);
        mPassiveIconColorFilter = new PorterDuffColorFilter(
                setAlpha(mComplementaryColor, ICON_ALPHA), PorterDuff.Mode.MULTIPLY);
        mActiveIconColorFilter = new PorterDuffColorFilter(setAlpha(mColor, ICON_ALPHA),
                PorterDuff.Mode.MULTIPLY);
        mPassiveTextColor = setAlpha(mComplementaryColor, TEXT_ALPHA);
        mActiveTextColor = setAlpha(mColor, TEXT_ALPHA);
        mPassiveTextHintColor = setAlpha(mComplementaryColor, TEXT_HINT_ALPHA);
        mActiveTextHintColor = setAlpha(mColor, TEXT_HINT_ALPHA);
    }

    public int getBaseColor() {
        return mBaseColor;
    }

    public PorterDuffColorFilter getBaseColorFilter() {
        return mBaseColorFilter;
    }

    public int getItemColor(boolean pGetPassive) {
        if (pGetPassive) {
            return mPassiveItemColor;
        } else {
            return mActiveItemColor;
        }
    }

    public PorterDuffColorFilter getItemColorFilter(boolean pGetPassive) {
        if (pGetPassive) {
            return mPassiveItemColorFilter;
        } else {
            return mActiveItemColorFilter;
        }
    }

    public PorterDuffColorFilter getIconColorFilter(boolean pGetPassive) {
        if (pGetPassive) {
            return mPassiveIconColorFilter;
        } else {
            return mActiveIconColorFilter;
        }
    }

    public int getTextColor(boolean pGetPassive) {
        if (pGetPassive) {
            return mPassiveTextColor;
        } else {
            return mActiveTextColor;
        }
    }

    public int getTextHintColor(boolean pGetPassive) {
        if (pGetPassive) {
            return mPassiveTextHintColor;
        } else {
            return mActiveTextHintColor;
        }
    }

    @Override
    public boolean equals(Object pObject) {
        if (pObject instanceof Theme) {
            Theme mTheme = (Theme) pObject;

            return mName.equals(mTheme.getName()) && (mColor == mTheme.getColor()) &&
                    (mAccentColor == mTheme.getAccentColor());

        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return mName.hashCode() + mColor + mAccentColor;
    }

    @Override
    public String toString() {
        return "Theme( Name: " + mName + " | Color: " + Integer.toString(mColor) +
                " | Accent Color: " + Integer.toString(mAccentColor) + " )";
    }

    public int compareTo(Theme pTheme) {
        return mName.compareTo(pTheme.getName());
    }

}
