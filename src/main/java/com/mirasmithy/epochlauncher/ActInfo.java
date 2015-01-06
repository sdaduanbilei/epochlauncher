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

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import java.util.HashMap;
import java.util.HashSet;

public class ActInfo implements Comparable<ActInfo> {

    private String mPackageName;
    private String mClassName;

    private transient Drawable mIcon;
    private transient String mLabel;

    private transient ComponentName mComponentName;
    private transient Intent mLaunchIntent;
    private transient Intent mUninstallIntent;

    public ActInfo(String pPackageName, String pClassName) {
        mPackageName = pPackageName;
        mClassName = pClassName;
    }

    public String getPackageName() {
        return mPackageName;
    }

    public String getClassName() {
        return mClassName;
    }

    public boolean isEnabled(HashSet<ActInfo> pActInfoDatabase) {
        return pActInfoDatabase.contains(this);
    }

    public void setIconAndLabel(HashMap<ActInfo, Drawable> pActInfoIconDatabase,
                                HashMap<ActInfo, String> pActInfoLabelDatabase) {

        mIcon = pActInfoIconDatabase.get(this);
        mLabel = pActInfoLabelDatabase.get(this);
    }

    public Drawable getIcon() {
        return mIcon;
    }

    public String getLabel() {
        return mLabel;
    }

    public boolean launch(Context pContext) {
        if (mComponentName == null) {
            mComponentName = new ComponentName(mPackageName, mClassName);
        }

        if (mLaunchIntent == null) {
            mLaunchIntent = new Intent(Intent.ACTION_MAIN);
            mLaunchIntent.setComponent(mComponentName);
            mLaunchIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                    Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        }

        try {
            pContext.startActivity(mLaunchIntent);

            return true;
        } catch (ActivityNotFoundException pException) {
            return false;
        }
    }

    public void uninstall(Context pContext) {
        if (mUninstallIntent == null) {
            mUninstallIntent = new Intent(Intent.ACTION_DELETE,
                    Uri.parse("package:" + mPackageName));
        }

        pContext.startActivity(mUninstallIntent);
    }

    @Override
    public boolean equals(Object pObject) {
        if (pObject instanceof ActInfo) {
            ActInfo mActInfo = (ActInfo) pObject;

            return mPackageName.equals(mActInfo.getPackageName()) &&
                    mClassName.equals(mActInfo.getClassName());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return mPackageName.hashCode() + mClassName.hashCode();
    }

    @Override
    public String toString() {
        return "ActInfo( Package Name: " + mPackageName + " | Class Name: " + mClassName + " )";
    }

    public int compareTo(ActInfo pActInfo) {
        return mLabel.compareTo(pActInfo.getLabel());
    }

}
