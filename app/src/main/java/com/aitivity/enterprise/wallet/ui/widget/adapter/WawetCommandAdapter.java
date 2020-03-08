package com.aitivity.enterprise.wallet.ui.widget.adapter;

import android.os.Bundle;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.aitivity.enterprise.wallet.entity.WawetCommand;
import com.aitivity.enterprise.wallet.ui.widget.OnWawetCommandClickListener;
import com.aitivity.enterprise.wallet.ui.widget.entity.WawetCommandSortedItem;
import com.aitivity.enterprise.wallet.ui.widget.holder.WawetCommandDateHolder;
import com.aitivity.enterprise.wallet.ui.widget.holder.WawetCommandHolder;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.entity.NetworkInfo;
import com.wallet.crypto.trustapp.entity.Wallet;
import com.wallet.crypto.trustapp.ui.widget.entity.SortedItem;
import com.wallet.crypto.trustapp.ui.widget.holder.BinderViewHolder;

public class WawetCommandAdapter extends RecyclerView.Adapter<BinderViewHolder> {

    private final SortedList<SortedItem> items = new SortedList<>(SortedItem.class, new SortedList.Callback<SortedItem>() {
        @Override
        public int compare(SortedItem left, SortedItem right) {
            return left.compare(right);
        }

        @Override
        public boolean areContentsTheSame(SortedItem oldItem, SortedItem newItem) {
            return oldItem.areContentsTheSame(newItem);
        }

        @Override
        public boolean areItemsTheSame(SortedItem left, SortedItem right) {
            return left.areItemsTheSame(right);
        }

        @Override
        public void onChanged(int position, int count) {
            notifyItemRangeChanged(position, count);
        }

        @Override
        public void onInserted(int position, int count) {
            notifyItemRangeInserted(position, count);
        }

        @Override
        public void onRemoved(int position, int count) {
            notifyItemRangeRemoved(position, count);
        }

        @Override
        public void onMoved(int fromPosition, int toPosition) {
            notifyItemMoved(fromPosition, toPosition);
        }
    });
    private final OnWawetCommandClickListener onWawetCommandClickListener;

    private Wallet wallet;
    private NetworkInfo network;

    public WawetCommandAdapter(OnWawetCommandClickListener onWawetCommandClickListener) {
        this.onWawetCommandClickListener = onWawetCommandClickListener;
    }

    @Override
    public BinderViewHolder<?> onCreateViewHolder(ViewGroup parent, int viewType) {
        BinderViewHolder holder = null;
        switch (viewType) {
            case WawetCommandHolder.VIEW_TYPE: {
                WawetCommandHolder wawetCommandHolder
                        = new WawetCommandHolder(R.layout.item_wawetcommand, parent);
                wawetCommandHolder.setOnWawetCommandClickListener(onWawetCommandClickListener);
                holder = wawetCommandHolder;
            } break;
            case WawetCommandDateHolder.VIEW_TYPE: {
                holder = new WawetCommandDateHolder(R.layout.item_wawetcommands_date_head, parent);
            }
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(BinderViewHolder holder, int position) {
        Bundle addition = new Bundle();
        //addition.putString(WawetCommandHolder.DEFAULT_ADDRESS_ADDITIONAL, wallet.address);
        //addition.putString(WawetCommandHolder.DEFAULT_SYMBOL_ADDITIONAL, network.symbol);
        holder.bind(items.get(position).value, addition);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).viewType;
    }

    public void setDefaultWallet(Wallet wallet) {
        this.wallet = wallet;
        notifyDataSetChanged();
    }

    public void setDefaultNetwork(NetworkInfo network) {
        this.network = network;
        notifyDataSetChanged();
    }

    public void addWawetCommands(WawetCommand[] wawetCommands) {
        items.beginBatchedUpdates();
        for (WawetCommand wawetCommand : wawetCommands) {
            WawetCommandSortedItem sortedItem = new WawetCommandSortedItem(
                    WawetCommandHolder.VIEW_TYPE, wawetCommand, WawetCommandSortedItem.DESC);
            items.add(sortedItem);
            //items.add(DateSortedItem.round(wawetCommand.timeStamp));
        }
        items.endBatchedUpdates();
    }

    public void clear() {
        items.clear();
    }
}
