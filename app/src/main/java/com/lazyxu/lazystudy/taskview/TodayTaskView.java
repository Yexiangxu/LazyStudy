//package com.lazyxu.lazystudy.taskview;
//
//
//
//import android.content.Context;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.graphics.RectF;
//import android.text.TextPaint;
//import android.util.AttributeSet;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import androidx.appcompat.widget.LinearLayoutCompat;
//import androidx.constraintlayout.widget.ConstraintLayout;
//
//import com.lazyxu.lazystudy.R;
//
//import java.util.ArrayList;
//import java.util.List;
//import kotlin.Metadata;
//import org.jetbrains.annotations.NotNull;
//import org.jetbrains.annotations.Nullable;
//
//@Metadata(bv = {}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u00017B\u0011\b\u0016\u0012\u0006\u0010/\u001a\u00020.¢\u0006\u0004\b0\u00101B\u001b\b\u0016\u0012\u0006\u0010/\u001a\u00020.\u0012\b\u00103\u001a\u0004\u0018\u000102¢\u0006\u0004\b0\u00104B#\b\u0016\u0012\u0006\u0010/\u001a\u00020.\u0012\b\u00103\u001a\u0004\u0018\u000102\u0012\u0006\u00105\u001a\u00020\u0002¢\u0006\u0004\b0\u00106J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u0006\u0010\u0007\u001a\u00020\u0006J\u0006\u0010\b\u001a\u00020\u0006R\u001b\u0010\u000e\u001a\u00020\t8BX\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u001b\u0010\u0012\u001a\u00020\u00028BX\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u000b\u001a\u0004\b\u0010\u0010\u0011R\u001b\u0010\u0015\u001a\u00020\u00028BX\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u000b\u001a\u0004\b\u0014\u0010\u0011R\u001b\u0010\u001a\u001a\u00020\u00168BX\u0002¢\u0006\f\n\u0004\b\u0017\u0010\u000b\u001a\u0004\b\u0018\u0010\u0019R\u001b\u0010\u001d\u001a\u00020\u00168BX\u0002¢\u0006\f\n\u0004\b\u001b\u0010\u000b\u001a\u0004\b\u001c\u0010\u0019R\u001b\u0010 \u001a\u00020\u00028BX\u0002¢\u0006\f\n\u0004\b\u001e\u0010\u000b\u001a\u0004\b\u001f\u0010\u0011R\u001b\u0010#\u001a\u00020\u00028BX\u0002¢\u0006\f\n\u0004\b!\u0010\u000b\u001a\u0004\b\"\u0010\u0011R\u001b\u0010&\u001a\u00020\u00028BX\u0002¢\u0006\f\n\u0004\b$\u0010\u000b\u001a\u0004\b%\u0010\u0011R+\u0010-\u001a\u0012\u0012\u0004\u0012\u00020(0'j\b\u0012\u0004\u0012\u00020(`)8BX\u0002¢\u0006\f\n\u0004\b*\u0010\u000b\u001a\u0004\b+\u0010,¨\u00068"}, d2 = {"Lcom/jz/jzdj/ui/view/todaytaskview/TodayTaskView;", "Landroid/widget/LinearLayout;", "", "coin", "Lib/f;", "setBubblesCoin", "", "getProgress", "getStartProgress", "Landroid/graphics/Paint;", "mPaint$delegate", "Lib/c;", "getMPaint", "()Landroid/graphics/Paint;", "mPaint", "colorBasic$delegate", "getColorBasic", "()I", "colorBasic", "colorTaskOK$delegate", "getColorTaskOK", "colorTaskOK", "Landroid/graphics/RectF;", "taskProgressBgRectF$delegate", "getTaskProgressBgRectF", "()Landroid/graphics/RectF;", "taskProgressBgRectF", "taskProgressRectF$delegate", "getTaskProgressRectF", "taskProgressRectF", "mWidth$delegate", "getMWidth", "mWidth", "SCALE_VIEW_WIDTH$delegate", "getSCALE_VIEW_WIDTH", "SCALE_VIEW_WIDTH", "HALF_SCALE_VIEW_WIDTH$delegate", "getHALF_SCALE_VIEW_WIDTH", "HALF_SCALE_VIEW_WIDTH", "Ljava/util/ArrayList;", "Lcom/jz/jzdj/ui/view/todaytaskview/TodayTaskView$a;", "Lkotlin/collections/ArrayList;", "mTasks$delegate", "getMTasks", "()Ljava/util/ArrayList;", "mTasks", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Landroid/util/AttributeSet;", "attrs", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "a", "app_release"}, k = 1, mv = {1, 7, 1})
///* compiled from: TodayTaskView.kt */
//public final class TodayTaskView extends LinearLayout {
//    @NotNull
//
//    /* renamed from: c  reason: collision with root package name */
//    public final c f21260c;
//    @Nullable
//
//    /* renamed from: d  reason: collision with root package name */
//    public LayoutInflater f21261d;
//    @Nullable
//
//    /* renamed from: e  reason: collision with root package name */
//    public LinearLayoutCompat f21262e;
//    @NotNull
//
//    /* renamed from: f  reason: collision with root package name */
//    public final c f21263f;
//    @NotNull
//
//    /* renamed from: g  reason: collision with root package name */
//    public final c f21264g;
//
//    /* renamed from: h  reason: collision with root package name */
//    public float f21265h;
//
//    /* renamed from: i  reason: collision with root package name */
//    public float f21266i;
//    @NotNull
//
//    /* renamed from: j  reason: collision with root package name */
//    public final c f21267j;
//    @NotNull
//
//    /* renamed from: k  reason: collision with root package name */
//    public final c f21268k;
//
//    /* renamed from: l  reason: collision with root package name */
//    public int f21269l;
//
//    /* renamed from: m  reason: collision with root package name */
//    public int f21270m;
//    public int n;
//    @NotNull
//
//    /* renamed from: o  reason: collision with root package name */
//    public final c f21271o;
//    @Nullable
//
//    /* renamed from: p  reason: collision with root package name */
//    public ConstraintLayout f21272p;
//    @Nullable
//    public TextView q;
//    public int r;
//    @NotNull
//    public final c s;
//    @NotNull
//    public final c t;
//    @NotNull
//    public final c u;
//
//    /* compiled from: TodayTaskView.kt */
//    public static final class a {
//
//        /* renamed from: a  reason: collision with root package name */
//        public int f21275a;
//
//        /* renamed from: b  reason: collision with root package name */
//        public int f21276b;
//
//        /* renamed from: c  reason: collision with root package name */
//        public int f21277c;
//
//        /* renamed from: d  reason: collision with root package name */
//        public int f21278d;
//        @NotNull
//
//        /* renamed from: e  reason: collision with root package name */
//        public String f21279e = "";
//
//        /* renamed from: f  reason: collision with root package name */
//        public int f21280f = 1;
//
//        /* renamed from: g  reason: collision with root package name */
//        public float f21281g;
//
//        /* renamed from: h  reason: collision with root package name */
//        public float f21282h;
//    }
//
//    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
//    public TodayTaskView(@NotNull Context context) {
//        this(context, null);
//        h.f(context, "context");
//    }
//
//    private final int getColorBasic() {
//        return ((Number) this.f21263f.getValue()).intValue();
//    }
//
//    private final int getColorTaskOK() {
//        return ((Number) this.f21264g.getValue()).intValue();
//    }
//
//    private final int getHALF_SCALE_VIEW_WIDTH() {
//        return ((Number) this.t.getValue()).intValue();
//    }
//
//    private final Paint getMPaint() {
//        return (Paint) this.f21260c.getValue();
//    }
//
//    private final ArrayList<a> getMTasks() {
//        return (ArrayList) this.u.getValue();
//    }
//
//    private final int getMWidth() {
//        return ((Number) this.f21271o.getValue()).intValue();
//    }
//
//    private final int getSCALE_VIEW_WIDTH() {
//        return ((Number) this.s.getValue()).intValue();
//    }
//
//    private final RectF getTaskProgressBgRectF() {
//        return (RectF) this.f21267j.getValue();
//    }
//
//    private final RectF getTaskProgressRectF() {
//        return (RectF) this.f21268k.getValue();
//    }
//
//    public final void a(int i3) {
//        int size = getMTasks().size();
//        int i10 = 0;
//        while (true) {
//            if (i10 >= size) {
//                i10 = -1;
//                break;
//            } else if (getMTasks().get(i10).f21280f == 1) {
//                break;
//            } else {
//                i10++;
//            }
//        }
//        float f10 = (((float) this.f21269l) / 2.0f) + (getTaskProgressBgRectF().right - getTaskProgressBgRectF().left);
//        if (i10 == -1) {
//            this.f21265h = f10;
//        } else if (i10 != 0) {
//            float f11 = getMTasks().get(i10).f21282h - getMTasks().get(i10).f21281g;
//            if (getMTasks().get(i10).f21276b != 0) {
//                this.f21265h = (((((float) i3) - ((float) getMTasks().get(i10 - 1).f21277c)) / ((float) getMTasks().get(i10).f21276b)) * f11) + getMTasks().get(i10).f21281g;
//            } else {
//                this.f21265h = getMTasks().get(i10).f21281g;
//            }
//            if (this.f21265h >= f10) {
//                this.f21265h = f10;
//            }
//        } else {
//            float f12 = getMTasks().get(0).f21282h - getMTasks().get(0).f21281g;
//            if (getMTasks().get(0).f21276b != 0) {
//                this.f21265h = ((((float) i3) / ((float) getMTasks().get(0).f21276b)) * f12) + getMTasks().get(0).f21281g;
//            } else {
//                this.f21265h = getMTasks().get(0).f21281g;
//            }
//        }
//        k.b("time：" + i3, "zdg");
//        k.b("progress：" + this.f21265h, "zdg");
//        this.f21266i = this.f21265h;
//        invalidate();
//    }
//
//    public final void b() {
//        ConstraintLayout constraintLayout = this.f21272p;
//        if (constraintLayout != null && (constraintLayout.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
//            ViewGroup.LayoutParams layoutParams = constraintLayout.getLayoutParams();
//            h.d(layoutParams, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
//            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
//            float f10 = this.f21266i;
//            if (f10 >= ((float) this.f21269l) / 2.0f) {
//                float f11 = getTaskProgressBgRectF().right - getTaskProgressBgRectF().left;
//                int i3 = this.f21269l;
//                if (f10 <= (((float) i3) / 2.0f) + f11) {
//                    marginLayoutParams.leftMargin = (int) (this.f21266i - (((float) i3) / 2.0f));
//                    constraintLayout.setLayoutParams(marginLayoutParams);
//                }
//            }
//        }
//    }
//
//    public final void c(@NotNull List<a> list) {
//        int i3;
//        boolean z9;
//        int i10;
//        int i11;
//        float f10;
//        h.f(list, "tasks");
//        getMTasks().clear();
//        getMTasks().addAll(list);
//        int size = getMTasks().size();
//        int i12 = 0;
//        int i13 = 0;
//        for (int i14 = 0; i14 < size; i14++) {
//            if (i14 == 0) {
//                i12 = getMTasks().get(i14).f21278d;
//            }
//            i13 += getMTasks().get(i14).f21278d;
//        }
//        ConstraintLayout constraintLayout = this.f21272p;
//        if (constraintLayout != null) {
//            String a10 = androidx.constraintlayout.core.a.a("已攒", i12, "金币");
//            TextView textView = this.q;
//            if (textView != null) {
//                textView.setText(a10);
//            }
//            TextView textView2 = this.q;
//            TextPaint paint = textView2 != null ? textView2.getPaint() : null;
//            if (paint != null) {
//                i10 = (int) paint.measureText(a10, 0, a10.length());
//            } else {
//                i10 = 0;
//            }
//            this.f21269l = e.b(12) + i10;
//            String a11 = androidx.constraintlayout.core.a.a("已攒", i13, "金币");
//            TextView textView3 = this.q;
//            if (textView3 != null) {
//                textView3.setText(a11);
//            }
//            if (paint != null) {
//                i11 = (int) paint.measureText(a11, 0, a11.length());
//            } else {
//                i11 = 0;
//            }
//            this.f21270m = e.b(12) + i11;
//            TextView textView4 = this.q;
//            if (textView4 != null) {
//                StringBuilder a12 = android.support.v4.media.h.a("已攒");
//                a12.append(this.r);
//                a12.append("金币");
//                textView4.setText(a12.toString());
//            }
//            if (size <= 4) {
//                f10 = ((float) getMWidth()) - (((float) (this.f21269l + this.f21270m)) / 2.0f);
//            } else {
//                f10 = ((((((float) getMWidth()) - (((float) (this.f21269l + this.f21270m)) / 2.0f)) + ((float) getHALF_SCALE_VIEW_WIDTH())) / 4.0f) * ((float) size)) - ((float) getHALF_SCALE_VIEW_WIDTH());
//            }
//            this.n = constraintLayout.getHeight();
//            RectF taskProgressBgRectF = getTaskProgressBgRectF();
//            taskProgressBgRectF.left = ((float) this.f21269l) / 2.0f;
//            taskProgressBgRectF.top = e.a(10.0f) + ((float) this.n);
//            taskProgressBgRectF.right = (((float) this.f21270m) / 2.0f) + f10;
//            taskProgressBgRectF.bottom = e.a(14.0f) + ((float) this.n);
//        }
//        int mWidth = (getMWidth() - this.f21269l) - getHALF_SCALE_VIEW_WIDTH();
//        int size2 = getMTasks().size();
//        LinearLayoutCompat linearLayoutCompat = this.f21262e;
//        h.c(linearLayoutCompat);
//        linearLayoutCompat.removeAllViews();
//        if (size2 < 1) {
//            i3 = 0;
//        } else {
//            if (1 > size2 || size2 >= 5) {
//                z9 = false;
//            } else {
//                z9 = true;
//            }
//            if (z9) {
//                i3 = (mWidth - ((size2 - 1) * getSCALE_VIEW_WIDTH())) / size2;
//            } else {
//                i3 = (mWidth - (getSCALE_VIEW_WIDTH() * 3)) / 4;
//            }
//        }
//        for (int i15 = 0; i15 < size2; i15++) {
//            LayoutInflater layoutInflater = this.f21261d;
//            h.c(layoutInflater);
//            View inflate = layoutInflater.inflate(R.layout.item_today_task, (ViewGroup) null);
//            LinearLayoutCompat.LayoutParams layoutParams = new LinearLayoutCompat.LayoutParams(e.b(60), -2);
//            if (i15 == 0) {
//                layoutParams.setMargins(((this.f21269l / 2) + i3) - e.b(8), 0, 0, 0);
//            } else {
//                layoutParams.setMargins(i3 - e.b(16), 0, 0, 0);
//            }
//            inflate.setLayoutParams(layoutParams);
//            LinearLayoutCompat linearLayoutCompat2 = this.f21262e;
//            h.c(linearLayoutCompat2);
//            linearLayoutCompat2.addView(inflate);
//            a aVar = getMTasks().get(i15);
//            aVar.f21281g = (((float) this.f21269l) / 2.0f) + ((float) ((getSCALE_VIEW_WIDTH() + i3) * i15));
//            aVar.f21282h = (((float) this.f21269l) / 2.0f) + ((float) i3) + ((float) ((getSCALE_VIEW_WIDTH() + i3) * i15));
//            TextView textView5 = (TextView) inflate.findViewById(R.id.today_task_name);
//            ((TextView) inflate.findViewById(R.id.coin_number)).setText(String.valueOf(aVar.f21278d));
//            int i16 = aVar.f21280f;
//            if (i16 == 1) {
//                textView5.setBackgroundResource(R.drawable.shape_today_task_scale);
//                textView5.setTextColor(Color.parseColor("#FF0055"));
//                textView5.setText((aVar.f21277c / 60) + "分钟");
//            } else if (i16 == 2) {
//                textView5.setBackgroundResource(R.drawable.shape_today_task_scale_arrive);
//                textView5.setTextColor(Color.parseColor("#FFFFFF"));
//                textView5.setText((aVar.f21277c / 60) + "分钟");
//            } else if (i16 == 3) {
//                textView5.setBackgroundResource(R.drawable.shape_today_task_scale_collected);
//                textView5.setTextColor(Color.parseColor("#FF0055"));
//                textView5.setText("已领取");
//            }
//        }
//        invalidate();
//    }
//
//    public final void d() {
//        int size = getMTasks().size();
//        for (int i3 = 0; i3 < size; i3++) {
//            LinearLayoutCompat linearLayoutCompat = this.f21262e;
//            h.c(linearLayoutCompat);
//            View childAt = linearLayoutCompat.getChildAt(i3);
//            TextView textView = (TextView) childAt.findViewById(R.id.today_task_name);
//            a aVar = getMTasks().get(i3);
//            ((TextView) childAt.findViewById(R.id.coin_number)).setText(String.valueOf(aVar.f21278d));
//            int i10 = aVar.f21280f;
//            if (i10 == 1) {
//                textView.setBackgroundResource(R.drawable.shape_today_task_scale);
//                textView.setTextColor(Color.parseColor("#FF0055"));
//                textView.setText((aVar.f21277c / 60) + "分钟");
//            } else if (i10 == 2) {
//                textView.setBackgroundResource(R.drawable.shape_today_task_scale_arrive);
//                textView.setTextColor(Color.parseColor("#FFFFFF"));
//                textView.setText((aVar.f21277c / 60) + "分钟");
//            } else if (i10 == 3) {
//                textView.setBackgroundResource(R.drawable.shape_today_task_scale_collected);
//                textView.setTextColor(Color.parseColor("#FF0055"));
//                textView.setText("已领取");
//            }
//        }
//        invalidate();
//    }
//
//    public final float getProgress() {
//        return this.f21265h;
//    }
//
//    public final float getStartProgress() {
//        return ((float) this.f21269l) / 2.0f;
//    }
//
//    public final void onDraw(@NotNull Canvas canvas) {
//        super.onDraw(canvas);
//        getMPaint().setColor(getColorBasic());
//        getMPaint().setStyle(Paint.Style.FILL);
//        getMPaint().setAntiAlias(true);
//        canvas.drawRoundRect(getTaskProgressBgRectF(), e.a(2.0f), e.a(2.0f), getMPaint());
//        getMPaint().setColor(getColorTaskOK());
//        getMPaint().setStyle(Paint.Style.FILL);
//        getMPaint().setAntiAlias(true);
//        RectF taskProgressRectF = getTaskProgressRectF();
//        taskProgressRectF.left = ((float) this.f21269l) / 2.0f;
//        taskProgressRectF.top = e.a(10.0f) + ((float) this.n);
//        taskProgressRectF.right = this.f21266i;
//        taskProgressRectF.bottom = e.a(14.0f) + ((float) this.n);
//        canvas.drawRoundRect(getTaskProgressRectF(), e.a(2.0f), e.a(2.0f), getMPaint());
//    }
//
//    public final void setBubblesCoin(int i3) {
//        this.r = i3;
//        TextView textView = this.q;
//        if (textView != null) {
//            StringBuilder a10 = android.support.v4.media.h.a("已攒");
//            a10.append(this.r);
//            a10.append("金币");
//            textView.setText(a10.toString());
//        }
//        TextView textView2 = this.q;
//        if (textView2 != null) {
//            textView2.setMinWidth(this.f21269l);
//        }
//    }
//
//
//
//    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
//    public TodayTaskView(@NotNull Context context, @Nullable AttributeSet attributeSet, int i3) {
//        super(context, attributeSet, i3);
//        this.f21260c = kotlin.a.b(TodayTaskView$mPaint$2.f21285d);
//        this.f21263f = kotlin.a.b(TodayTaskView$colorBasic$2.f21283d);
//        this.f21264g = kotlin.a.b(TodayTaskView$colorTaskOK$2.f21284d);
//        this.f21267j = kotlin.a.b(TodayTaskView$taskProgressBgRectF$2.f21288d);
//        this.f21268k = kotlin.a.b(TodayTaskView$taskProgressRectF$2.f21289d);
//        this.f21271o = kotlin.a.b(TodayTaskView$mWidth$2.f21287d);
//        this.s = kotlin.a.b(TodayTaskView$SCALE_VIEW_WIDTH$2.f21274d);
//        this.t = kotlin.a.b(TodayTaskView$HALF_SCALE_VIEW_WIDTH$2.f21273d);
//        this.u = kotlin.a.b(TodayTaskView$mTasks$2.f21286d);
//        getMPaint().setAntiAlias(true);
//        getMPaint().setDither(true);
//        setOrientation(1);
//        setWillNotDraw(false);
//        this.f21261d = LayoutInflater.from(context);
//        addView(LayoutInflater.from(context).inflate(R.layout.today_task_layout, (ViewGroup) null));
//        this.f21262e = (LinearLayoutCompat) findViewById(R.id.task_layout);
//        this.f21272p = (ConstraintLayout) findViewById(R.id.task_bubbles_layout);
//        this.q = (TextView) findViewById(R.id.task_bubbles_coin);
//    }
//}