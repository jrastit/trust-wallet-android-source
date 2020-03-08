package com.aitivity.enterprise.wallet.ui.widget.entity;

import com.aitivity.enterprise.wallet.entity.WawetCommand;
import com.wallet.crypto.trustapp.ui.widget.entity.SortedItem;


public class WawetCommandSortedItem extends IdSortedItem<WawetCommand> {

    public WawetCommandSortedItem(int viewType, WawetCommand value, int order) {
        super(viewType, value, 0, order);
    }

    public long getId(){
        return value.id;
    }

    @Override
    public int compare(SortedItem other) {
        return super.compare(other);
//        return other.viewType == WawetCommandHolder.VIEW_TYPE ||
//                ? super.compare(other)
//                : weight - other.weight;
    }

    @Override
    public boolean areContentsTheSame(SortedItem newItem) {
        if (viewType == newItem.viewType) {
            WawetCommand wawetCommand = (WawetCommand) newItem.value;
            return value.id == wawetCommand.id;
        }
        return false;
    }

    @Override
    public boolean areItemsTheSame(SortedItem other) {
        return viewType == other.viewType;
    }


}
