package com.aitivity.enterprise.wallet.ui.widget.entity;

import com.wallet.crypto.trustapp.ui.widget.entity.SortedItem;

public abstract class IdSortedItem<T> extends SortedItem<T> {

    public static final int ADC = 1;
    public static final int DESC = -1;

    private static final int IS_ID_TAG = 1;

    private final int order;


    public IdSortedItem(int viewType, T value, int weight, int order) {
        super(viewType, value, weight);
        tags.add(IS_ID_TAG);
        this.order = order;
    }

    public abstract long getId();

    @Override
    public int compare(SortedItem other) {
        if (other.tags.contains(IS_ID_TAG)) {
            IdSortedItem otherId = (IdSortedItem) other;
            if (getId() > otherId.getId()) {
                return order * 1;
            } else if (getId() == otherId.getId()) {
                return 0;
            }
            return order * -1;
        }
        return Integer.MIN_VALUE;
    }
}
