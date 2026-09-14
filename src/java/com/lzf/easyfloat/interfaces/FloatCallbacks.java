package com.lzf.easyfloat.interfaces;

import android.view.MotionEvent;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function0;
import android.view.View;
import kotlin.jvm.functions.Function3;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.Metadata;

@Metadata(bv = { 1, 0, 3 }, d1 = { "\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\rB\u0005¢\u0006\u0002\u0010\u0002J#\u0010\t\u001a\u00020\n2\u001b\u0010\u0003\u001a\u0017\u0012\b\u0012\u00060\u0004R\u00020\u0000\u0012\u0004\u0012\u00020\n0\u000b¢\u0006\u0002\b\fR\u001e\u0010\u0003\u001a\u00060\u0004R\u00020\u0000X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u000e" }, d2 = { "Lcom/lzf/easyfloat/interfaces/FloatCallbacks;", "", "()V", "builder", "Lcom/lzf/easyfloat/interfaces/FloatCallbacks$Builder;", "getBuilder", "()Lcom/lzf/easyfloat/interfaces/FloatCallbacks$Builder;", "setBuilder", "(Lcom/lzf/easyfloat/interfaces/FloatCallbacks$Builder;)V", "registerListener", "", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "Builder", "easyfloat_release" }, k = 1, mv = { 1, 4, 1 })
public final class FloatCallbacks
{
    public Builder builder;
    
    public final Builder getBuilder() {
        final Builder builder = this.builder;
        if (builder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("builder");
        }
        return builder;
    }
    
    public final void registerListener(final Function1<? super Builder, Unit> function1) {
        Intrinsics.checkNotNullParameter((Object)function1, "builder");
        final Builder builder = new Builder();
        function1.invoke((Object)builder);
        this.builder = builder;
    }
    
    public final void setBuilder(final Builder builder) {
        Intrinsics.checkNotNullParameter((Object)builder, "<set-?>");
        this.builder = builder;
    }
    
    @Metadata(bv = { 1, 0, 3 }, d1 = { "\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J*\u0010)\u001a\u00020\b2\"\u0010*\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0004\u0012\u00020\b0\u0004J\u0014\u0010\r\u001a\u00020\b2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\b0\u000eJ \u0010\u0013\u001a\u00020\b2\u0018\u0010*\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\b0\u0014J\u001a\u0010\u001a\u001a\u00020\b2\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u001bJ\u001a\u0010 \u001a\u00020\b2\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u001bJ\u001a\u0010#\u001a\u00020\b2\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u001bJ \u0010&\u001a\u00020\b2\u0018\u0010*\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\b0\u0014R8\u0010\u0003\u001a \u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0004\u0012\u00020\b\u0018\u00010\u0004X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\"\u0010\r\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u000eX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R.\u0010\u0013\u001a\u0016\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\b\u0018\u00010\u0014X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R(\u0010\u001a\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0018\u00010\u001bX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR(\u0010 \u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0018\u00010\u001bX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u001d\"\u0004\b\"\u0010\u001fR(\u0010#\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0018\u00010\u001bX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u001d\"\u0004\b%\u0010\u001fR.\u0010&\u001a\u0016\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\b\u0018\u00010\u0014X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u0017\"\u0004\b(\u0010\u0019¨\u0006+" }, d2 = { "Lcom/lzf/easyfloat/interfaces/FloatCallbacks$Builder;", "", "(Lcom/lzf/easyfloat/interfaces/FloatCallbacks;)V", "createdResult", "Lkotlin/Function3;", "", "", "Landroid/view/View;", "", "getCreatedResult$easyfloat_release", "()Lkotlin/jvm/functions/Function3;", "setCreatedResult$easyfloat_release", "(Lkotlin/jvm/functions/Function3;)V", "dismiss", "Lkotlin/Function0;", "getDismiss$easyfloat_release", "()Lkotlin/jvm/functions/Function0;", "setDismiss$easyfloat_release", "(Lkotlin/jvm/functions/Function0;)V", "drag", "Lkotlin/Function2;", "Landroid/view/MotionEvent;", "getDrag$easyfloat_release", "()Lkotlin/jvm/functions/Function2;", "setDrag$easyfloat_release", "(Lkotlin/jvm/functions/Function2;)V", "dragEnd", "Lkotlin/Function1;", "getDragEnd$easyfloat_release", "()Lkotlin/jvm/functions/Function1;", "setDragEnd$easyfloat_release", "(Lkotlin/jvm/functions/Function1;)V", "hide", "getHide$easyfloat_release", "setHide$easyfloat_release", "show", "getShow$easyfloat_release", "setShow$easyfloat_release", "touchEvent", "getTouchEvent$easyfloat_release", "setTouchEvent$easyfloat_release", "createResult", "action", "easyfloat_release" }, k = 1, mv = { 1, 4, 1 })
    public final class Builder
    {
        private Function3<? super Boolean, ? super String, ? super View, Unit> createdResult;
        private Function0<Unit> dismiss;
        private Function2<? super View, ? super MotionEvent, Unit> drag;
        private Function1<? super View, Unit> dragEnd;
        private Function1<? super View, Unit> hide;
        private Function1<? super View, Unit> show;
        final FloatCallbacks this$0;
        private Function2<? super View, ? super MotionEvent, Unit> touchEvent;
        
        public Builder(final FloatCallbacks this$0) {
            this.this$0 = this$0;
        }
        
        public final void createResult(final Function3<? super Boolean, ? super String, ? super View, Unit> createdResult) {
            Intrinsics.checkNotNullParameter((Object)createdResult, "action");
            this.createdResult = createdResult;
        }
        
        public final void dismiss(final Function0<Unit> dismiss) {
            Intrinsics.checkNotNullParameter((Object)dismiss, "action");
            this.dismiss = dismiss;
        }
        
        public final void drag(final Function2<? super View, ? super MotionEvent, Unit> drag) {
            Intrinsics.checkNotNullParameter((Object)drag, "action");
            this.drag = drag;
        }
        
        public final void dragEnd(final Function1<? super View, Unit> dragEnd) {
            Intrinsics.checkNotNullParameter((Object)dragEnd, "action");
            this.dragEnd = dragEnd;
        }
        
        public final Function3<Boolean, String, View, Unit> getCreatedResult$easyfloat_release() {
            return (Function3<Boolean, String, View, Unit>)this.createdResult;
        }
        
        public final Function0<Unit> getDismiss$easyfloat_release() {
            return this.dismiss;
        }
        
        public final Function2<View, MotionEvent, Unit> getDrag$easyfloat_release() {
            return (Function2<View, MotionEvent, Unit>)this.drag;
        }
        
        public final Function1<View, Unit> getDragEnd$easyfloat_release() {
            return (Function1<View, Unit>)this.dragEnd;
        }
        
        public final Function1<View, Unit> getHide$easyfloat_release() {
            return (Function1<View, Unit>)this.hide;
        }
        
        public final Function1<View, Unit> getShow$easyfloat_release() {
            return (Function1<View, Unit>)this.show;
        }
        
        public final Function2<View, MotionEvent, Unit> getTouchEvent$easyfloat_release() {
            return (Function2<View, MotionEvent, Unit>)this.touchEvent;
        }
        
        public final void hide(final Function1<? super View, Unit> hide) {
            Intrinsics.checkNotNullParameter((Object)hide, "action");
            this.hide = hide;
        }
        
        public final void setCreatedResult$easyfloat_release(final Function3<? super Boolean, ? super String, ? super View, Unit> createdResult) {
            this.createdResult = createdResult;
        }
        
        public final void setDismiss$easyfloat_release(final Function0<Unit> dismiss) {
            this.dismiss = dismiss;
        }
        
        public final void setDrag$easyfloat_release(final Function2<? super View, ? super MotionEvent, Unit> drag) {
            this.drag = drag;
        }
        
        public final void setDragEnd$easyfloat_release(final Function1<? super View, Unit> dragEnd) {
            this.dragEnd = dragEnd;
        }
        
        public final void setHide$easyfloat_release(final Function1<? super View, Unit> hide) {
            this.hide = hide;
        }
        
        public final void setShow$easyfloat_release(final Function1<? super View, Unit> show) {
            this.show = show;
        }
        
        public final void setTouchEvent$easyfloat_release(final Function2<? super View, ? super MotionEvent, Unit> touchEvent) {
            this.touchEvent = touchEvent;
        }
        
        public final void show(final Function1<? super View, Unit> show) {
            Intrinsics.checkNotNullParameter((Object)show, "action");
            this.show = show;
        }
        
        public final void touchEvent(final Function2<? super View, ? super MotionEvent, Unit> touchEvent) {
            Intrinsics.checkNotNullParameter((Object)touchEvent, "action");
            this.touchEvent = touchEvent;
        }
    }
}
