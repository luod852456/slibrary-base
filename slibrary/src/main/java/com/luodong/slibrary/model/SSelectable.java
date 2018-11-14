package com.luodong.slibrary.model;

import java.io.Serializable;

public class SSelectable implements Serializable {
    protected boolean selected;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}