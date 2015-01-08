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

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.LinearLayout;

public class EditableItemTextWatcher implements TextWatcher {

    ItemViewHolder mItemViewHolder;

    public EditableItemTextWatcher(LinearLayout pEditableItem) {
        mItemViewHolder = (ItemViewHolder) pEditableItem.getTag();
        mItemViewHolder.getText().addTextChangedListener(this);
    }

    public void beforeTextChanged(CharSequence pCharSequence, int pStart, int pCount, int pAfter) {
    }

    public void onTextChanged(CharSequence pCharSequence, int pStart, int pBefore, int pCount) {
    }

    public void afterTextChanged(Editable pEditable) {
        ((ItemData) mItemViewHolder.getData()).setText(pEditable.toString());
    }

}
