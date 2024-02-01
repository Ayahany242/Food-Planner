package com.example.myapplication;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class HorizontalItemDecoration extends RecyclerView.ItemDecoration {

    private final int space;

    public HorizontalItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(
            Rect outRect,
            View view,
            RecyclerView parent,
            RecyclerView.State state
    ) {
        // Apply spacing to all items except the first one
        if (parent.getChildAdapterPosition(view) != 0) {
            outRect.left = space;
        }
    }
}
