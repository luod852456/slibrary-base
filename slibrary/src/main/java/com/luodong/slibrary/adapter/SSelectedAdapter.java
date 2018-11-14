package com.luodong.slibrary.adapter;

import android.app.Activity;

import com.luodong.slibrary.model.SSelectable;

import java.util.List;

/**
 * SSelectedAdapter
 *
 * @author luodong 2018.10.30
 */
public abstract class SSelectedAdapter<T extends SSelectable> extends SSimpleAdapter<T> {

    public enum SelectedMode {
        /**
         * 单选，必须选中一项
         */
        SINGLE_MUST_ONE_SELECTED,
        /**
         * 单选，可以一项都没选中
         */
        SINGLE,
        /**
         * 多选，必须选中一项
         */
        MULTI_MUST_ONE_SELECTED,
        /**
         * 多选，可以一项都没选中
         */
        MULTI
    }

    protected SelectedMode mode = SelectedMode.SINGLE;

    public SSelectedAdapter(Activity activity) {
        super(activity);
    }

    @Override
    public void setData(List<T> arr, int page) {
        onMustOneSeleced(arr);
        super.setData(arr, page);
    }

    @Override
    public void setData(List<T> arr) {
        onMustOneSeleced(arr);
        super.setData(arr);
    }

    private void onMustOneSeleced(List<T> arr) {
        if (arr != null && arr.size() != 0) {
            switch (mode) {
                case SINGLE_MUST_ONE_SELECTED:
                    arr.get(0).setSelected(true);
                    break;
                case MULTI_MUST_ONE_SELECTED:
                    arr.get(0).setSelected(true);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 设置选中模式
     *
     * @param mode
     */
    public void setMode(SelectedMode mode) {
        this.mode = mode;
    }

    /**
     * 选中
     *
     * @param position
     */
    public void selectItem(int position) {
        switch (mode) {
            case SINGLE_MUST_ONE_SELECTED:
                selectItemSingleMustOne(position);
                break;
            case SINGLE:
                selectItemSingle(position);
                break;
            case MULTI_MUST_ONE_SELECTED:
                selectItemMultiMustOne(position);
                break;
            case MULTI:
                selectItemMulti(position);
                break;
            default:
                break;
        }
    }

    /**
     * 多选必选
     *
     * @param position
     */
    private void selectItemMultiMustOne(int position) {
        int m = 0, j = 0;
        for (int i = 0; i < getCount(); i++) {
            if (getItem(i).isSelected()) {
                j = i;
                m++;
            }
        }
        /**
         * 如果只有一个选中的item，并且选中的就是这个item
         */
        if (m == 1 && position == j) {
            return;
        }
        /**
         * 重复点击反选
         */
        getItem(position).setSelected(!getItem(position).isSelected());
        notifyDataSetChanged();
    }

    /**
     * 多选
     *
     * @param position
     */
    private void selectItemMulti(int position) {
        /**
         * 重复点击反选
         */
        getItem(position).setSelected(!getItem(position).isSelected());
        notifyDataSetChanged();
    }

    /**
     * 单选必选
     *
     * @param position
     */
    private void selectItemSingleMustOne(int position) {
        /**
         * 如果选中的已经是 true 直接返回
         */
        if (getItem(position).isSelected()) {
            return;
        }
        cleanAllSelected();
        /**
         * 选中的true
         */
        getItem(position).setSelected(true);
        notifyDataSetChanged();
    }

    /**
     * 单选
     *
     * @param position
     */
    private void selectItemSingle(int position) {
        for (int i = 0; i < getCount(); i++) {
            if (i != position) {
                getItem(i).setSelected(false);
            } else {
                getItem(i).setSelected(!getItem(i).isSelected());
            }
        }
        notifyDataSetChanged();
    }

    /**
     * 清空所有选项
     */
    private void cleanAllSelected() {
        for (T model : getList()) {
            model.setSelected(false);
        }
    }


    /**
     * 全选
     */
    private void selectAllSelected() {
        for (T model : getList()) {
            model.setSelected(true);
        }
    }

}
