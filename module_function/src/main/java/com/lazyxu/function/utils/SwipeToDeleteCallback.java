//package com.lazyxu.function.utils;
//
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.graphics.RectF;
//import android.view.MotionEvent;
//import android.view.View;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.ItemTouchHelper;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.lazyxu.function.R;
//
//public class SwipeToDeleteCallback extends ItemTouchHelper.SimpleCallback {
//
//    private final RecyclerView.Adapter mAdapter;
//    private final Paint mPaint = new Paint();
//    private final RectF mDeleteButton = new RectF();
//    private final int mButtonWidth = 300;
//    private boolean mSwipeBack;
//    private int mTouchPosition;
//
//    public SwipeToDeleteCallback(RecyclerView.Adapter adapter) {
//        super(0, ItemTouchHelper.LEFT);
//        mAdapter = adapter;
//        mPaint.setColor(Color.RED);
//    }
//
//    @Override
//    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//        return false;
//    }
//
//    @Override
//    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//        int position = viewHolder.getAdapterPosition();
//        mAdapter.notifyItemRemoved(position);
//        // 在这里执行删除操作
//    }
//
//    @Override
//    public void onChildDraw(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
//        View itemView = viewHolder.itemView;
//        int itemHeight = itemView.getBottom() - itemView.getTop();
//
//        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
//            drawDeleteButton(canvas, itemView, dX);
//        }
//
//        super.onChildDraw(canvas, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
//    }
//
//    private void drawDeleteButton(Canvas canvas, View itemView, float dX) {
//        float buttonWidthWithoutPadding = mButtonWidth - 20;
//        float corners = 16;
//
//        View itemViewLeft = itemView.findViewById(R.id.item_view_left);
//
//        mDeleteButton.set(itemView.getLeft(), itemView.getTop(), itemView.getLeft() + buttonWidthWithoutPadding, itemView.getBottom());
//        canvas.drawRoundRect(mDeleteButton, corners, corners, mPaint);
//        drawText("Delete", canvas, mDeleteButton, mPaint);
//
//        mSwipeBack = dX < 0;
//
//        if (mSwipeBack) {
//            itemViewLeft.setVisibility(View.VISIBLE);
//        } else {
//            itemViewLeft.setVisibility(View.GONE);
//        }
//    }
//
//    private void drawText(String text, Canvas canvas, RectF button, Paint paint) {
//        float textSize = 50;
//        paint.setColor(Color.WHITE);
//        paint.setAntiAlias(true);
//        paint.setTextSize(textSize);
//
//        float textWidth = paint.measureText(text);
//        canvas.drawText(text, button.centerX() - (textWidth / 2), button.centerY() + (textSize / 2), paint);
//    }
//
//    @Override
//    public int convertToAbsoluteDirection(int flags, int layoutDirection) {
//        if (mSwipeBack) {
//            mSwipeBack = false;
//            return 0;
//        }
//        return super.convertToAbsoluteDirection(flags, layoutDirection);
//    }
//
//    @Override
//    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
//        if (viewHolder != null) {
//            final View itemView = viewHolder.itemView;
//            mTouchPosition = viewHolder.getAdapterPosition();
//        }
//        super.onSelectedChanged(viewHolder, actionState);
//    }
//
//    @Override
//    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
//        super.clearView(recyclerView, viewHolder);
//        mTouchPosition = -1;
//    }
//
//    @Override
//    public void onChildDrawOver(@NonNull Canvas c, @NonNull RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
//        super.onChildDrawOver(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
//    }
//}
//
