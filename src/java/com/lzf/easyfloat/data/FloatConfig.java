package com.lzf.easyfloat.data;

import java.util.LinkedHashSet;
import com.lzf.easyfloat.utils.DefaultDisplayHeight;
import com.lzf.easyfloat.anim.DefaultAnimator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.DefaultConstructorMarker;
import com.lzf.easyfloat.enums.SidePattern;
import com.lzf.easyfloat.enums.ShowPattern;
import kotlin.Pair;
import android.view.View;
import com.lzf.easyfloat.interfaces.OnInvokeView;
import com.lzf.easyfloat.interfaces.FloatCallbacks;
import com.lzf.easyfloat.interfaces.OnFloatAnimator;
import java.util.Set;
import com.lzf.easyfloat.interfaces.OnDisplayHeight;
import com.lzf.easyfloat.interfaces.OnFloatCallbacks;
import kotlin.Metadata;

@Metadata(bv = { 1, 0, 3 }, d1 = { "\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\b|\b\u0086\b\u0018\u00002\u00020\u0001B\u00d3\u0002\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\t\u0012\b\b\u0002\u0010\f\u001a\u00020\t\u0012\b\b\u0002\u0010\r\u001a\u00020\t\u0012\b\b\u0002\u0010\u000e\u001a\u00020\t\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0013\u001a\u00020\t\u0012\b\b\u0002\u0010\u0014\u001a\u00020\t\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0003\u0012\u0014\b\u0002\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0017\u0012\u0014\b\u0002\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0017\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u001a\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u001c\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001e\u0012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 \u0012\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\"\u0012\n\b\u0002\u0010#\u001a\u0004\u0018\u00010$\u0012\b\b\u0002\u0010%\u001a\u00020&\u0012\u000e\b\u0002\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00070(\u0012\b\b\u0002\u0010)\u001a\u00020\t\u0012\b\b\u0002\u0010*\u001a\u00020\t\u0012\b\b\u0002\u0010+\u001a\u00020\u0003¢\u0006\u0002\u0010,J\u0010\u0010\u007f\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003¢\u0006\u0002\u0010_J\n\u0010\u0080\u0001\u001a\u00020\u0010H\u00c6\u0003J\n\u0010\u0081\u0001\u001a\u00020\u0012H\u00c6\u0003J\n\u0010\u0082\u0001\u001a\u00020\tH\u00c6\u0003J\n\u0010\u0083\u0001\u001a\u00020\tH\u00c6\u0003J\n\u0010\u0084\u0001\u001a\u00020\u0003H\u00c6\u0003J\u0016\u0010\u0085\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0017H\u00c6\u0003J\u0016\u0010\u0086\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0017H\u00c6\u0003J\n\u0010\u0087\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u0088\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u0089\u0001\u001a\u00020\u0003H\u00c6\u0003J\f\u0010\u008a\u0001\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\n\u0010\u008b\u0001\u001a\u00020\u0003H\u00c6\u0003J\f\u0010\u008c\u0001\u001a\u0004\u0018\u00010\u001eH\u00c6\u0003J\f\u0010\u008d\u0001\u001a\u0004\u0018\u00010 H\u00c6\u0003J\f\u0010\u008e\u0001\u001a\u0004\u0018\u00010\"H\u00c6\u0003J\f\u0010\u008f\u0001\u001a\u0004\u0018\u00010$H\u00c6\u0003J\n\u0010\u0090\u0001\u001a\u00020&H\u00c6\u0003J\u0010\u0010\u0091\u0001\u001a\b\u0012\u0004\u0012\u00020\u00070(H\u00c6\u0003J\u0010\u0010\u0092\u0001\u001a\u00020\tH\u00c0\u0003¢\u0006\u0003\b\u0093\u0001J\u0010\u0010\u0094\u0001\u001a\u00020\tH\u00c0\u0003¢\u0006\u0003\b\u0095\u0001J\n\u0010\u0096\u0001\u001a\u00020\u0003H\u00c6\u0003J\f\u0010\u0097\u0001\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\n\u0010\u0098\u0001\u001a\u00020\tH\u00c6\u0003J\n\u0010\u0099\u0001\u001a\u00020\tH\u00c6\u0003J\n\u0010\u009a\u0001\u001a\u00020\tH\u00c6\u0003J\n\u0010\u009b\u0001\u001a\u00020\tH\u00c6\u0003J\n\u0010\u009c\u0001\u001a\u00020\tH\u00c6\u0003J\n\u0010\u009d\u0001\u001a\u00020\tH\u00c6\u0003J\u00de\u0002\u0010\u009e\u0001\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\t2\b\b\u0002\u0010\r\u001a\u00020\t2\b\b\u0002\u0010\u000e\u001a\u00020\t2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\t2\b\b\u0002\u0010\u0014\u001a\u00020\t2\b\b\u0002\u0010\u0015\u001a\u00020\u00032\u0014\b\u0002\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00172\u0014\b\u0002\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00172\b\b\u0002\u0010\u0019\u001a\u00020\u00032\b\b\u0002\u0010\u001a\u001a\u00020\u00032\b\b\u0002\u0010\u001b\u001a\u00020\u00032\b\b\u0002\u0010\u001c\u001a\u00020\u00032\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\"2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010$2\b\b\u0002\u0010%\u001a\u00020&2\u000e\b\u0002\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00070(2\b\b\u0002\u0010)\u001a\u00020\t2\b\b\u0002\u0010*\u001a\u00020\t2\b\b\u0002\u0010+\u001a\u00020\u0003H\u00c6\u0001¢\u0006\u0003\u0010\u009f\u0001J\u0015\u0010 \u0001\u001a\u00020\t2\t\u0010¡\u0001\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\n\u0010¢\u0001\u001a\u00020\u0003H\u00d6\u0001J\n\u0010£\u0001\u001a\u00020\u0007H\u00d6\u0001R\u001a\u0010\u001c\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u001c\u0010\u001f\u001a\u0004\u0018\u00010 X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u00102\"\u0004\b3\u00104R\u001a\u0010%\u001a\u00020&X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u00106\"\u0004\b7\u00108R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<R\u001a\u0010)\u001a\u00020\tX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010:\"\u0004\b>\u0010<R\u0017\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00070(¢\u0006\b\n\u0000\u001a\u0004\b?\u0010@R\u001c\u0010#\u001a\u0004\u0018\u00010$X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010B\"\u0004\bC\u0010DR\u001c\u0010!\u001a\u0004\u0018\u00010\"X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bE\u0010F\"\u0004\bG\u0010HR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bI\u0010J\"\u0004\bK\u0010LR\u001a\u0010\u0015\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bM\u0010.\"\u0004\bN\u00100R\u001a\u0010\r\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bO\u0010:\"\u0004\bP\u0010<R\u001a\u0010\u0014\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bQ\u0010:\"\u0004\bR\u0010<R\u001a\u0010\u000e\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bS\u0010:\"\u0004\bT\u0010<R\u001c\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bU\u0010V\"\u0004\bW\u0010XR\u001a\u0010\u000b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010:\"\u0004\bY\u0010<R\u001a\u0010\n\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010:\"\u0004\bZ\u0010<R\u001a\u0010\f\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010:\"\u0004\b[\u0010<R\u001a\u0010+\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\\\u0010.\"\u0004\b]\u00100R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u0010\n\u0002\u0010b\u001a\u0004\b^\u0010_\"\u0004\b`\u0010aR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bc\u0010d\"\u0004\be\u0010fR\u001a\u0010\u0019\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bg\u0010.\"\u0004\bh\u00100R&\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bi\u0010j\"\u0004\bk\u0010lR\u001a\u0010*\u001a\u00020\tX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bm\u0010:\"\u0004\bn\u0010<R&\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bo\u0010j\"\u0004\bp\u0010lR\u001a\u0010\u001b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bq\u0010.\"\u0004\br\u00100R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bs\u0010t\"\u0004\bu\u0010vR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bw\u0010x\"\u0004\by\u0010zR\u001a\u0010\u001a\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b{\u0010.\"\u0004\b|\u00100R\u001a\u0010\u0013\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b}\u0010:\"\u0004\b~\u0010<¨\u0006¤\u0001" }, d2 = { "Lcom/lzf/easyfloat/data/FloatConfig;", "", "layoutId", "", "layoutView", "Landroid/view/View;", "floatTag", "", "dragEnable", "", "isDrag", "isAnim", "isShow", "hasEditText", "immersionStatusBar", "sidePattern", "Lcom/lzf/easyfloat/enums/SidePattern;", "showPattern", "Lcom/lzf/easyfloat/enums/ShowPattern;", "widthMatch", "heightMatch", "gravity", "offsetPair", "Lkotlin/Pair;", "locationPair", "leftBorder", "topBorder", "rightBorder", "bottomBorder", "invokeView", "Lcom/lzf/easyfloat/interfaces/OnInvokeView;", "callbacks", "Lcom/lzf/easyfloat/interfaces/OnFloatCallbacks;", "floatCallbacks", "Lcom/lzf/easyfloat/interfaces/FloatCallbacks;", "floatAnimator", "Lcom/lzf/easyfloat/interfaces/OnFloatAnimator;", "displayHeight", "Lcom/lzf/easyfloat/interfaces/OnDisplayHeight;", "filterSet", "", "filterSelf", "needShow", "layoutChangedGravity", "(Ljava/lang/Integer;Landroid/view/View;Ljava/lang/String;ZZZZZZLcom/lzf/easyfloat/enums/SidePattern;Lcom/lzf/easyfloat/enums/ShowPattern;ZZILkotlin/Pair;Lkotlin/Pair;IIIILcom/lzf/easyfloat/interfaces/OnInvokeView;Lcom/lzf/easyfloat/interfaces/OnFloatCallbacks;Lcom/lzf/easyfloat/interfaces/FloatCallbacks;Lcom/lzf/easyfloat/interfaces/OnFloatAnimator;Lcom/lzf/easyfloat/interfaces/OnDisplayHeight;Ljava/util/Set;ZZI)V", "getBottomBorder", "()I", "setBottomBorder", "(I)V", "getCallbacks", "()Lcom/lzf/easyfloat/interfaces/OnFloatCallbacks;", "setCallbacks", "(Lcom/lzf/easyfloat/interfaces/OnFloatCallbacks;)V", "getDisplayHeight", "()Lcom/lzf/easyfloat/interfaces/OnDisplayHeight;", "setDisplayHeight", "(Lcom/lzf/easyfloat/interfaces/OnDisplayHeight;)V", "getDragEnable", "()Z", "setDragEnable", "(Z)V", "getFilterSelf$easyfloat_release", "setFilterSelf$easyfloat_release", "getFilterSet", "()Ljava/util/Set;", "getFloatAnimator", "()Lcom/lzf/easyfloat/interfaces/OnFloatAnimator;", "setFloatAnimator", "(Lcom/lzf/easyfloat/interfaces/OnFloatAnimator;)V", "getFloatCallbacks", "()Lcom/lzf/easyfloat/interfaces/FloatCallbacks;", "setFloatCallbacks", "(Lcom/lzf/easyfloat/interfaces/FloatCallbacks;)V", "getFloatTag", "()Ljava/lang/String;", "setFloatTag", "(Ljava/lang/String;)V", "getGravity", "setGravity", "getHasEditText", "setHasEditText", "getHeightMatch", "setHeightMatch", "getImmersionStatusBar", "setImmersionStatusBar", "getInvokeView", "()Lcom/lzf/easyfloat/interfaces/OnInvokeView;", "setInvokeView", "(Lcom/lzf/easyfloat/interfaces/OnInvokeView;)V", "setAnim", "setDrag", "setShow", "getLayoutChangedGravity", "setLayoutChangedGravity", "getLayoutId", "()Ljava/lang/Integer;", "setLayoutId", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getLayoutView", "()Landroid/view/View;", "setLayoutView", "(Landroid/view/View;)V", "getLeftBorder", "setLeftBorder", "getLocationPair", "()Lkotlin/Pair;", "setLocationPair", "(Lkotlin/Pair;)V", "getNeedShow$easyfloat_release", "setNeedShow$easyfloat_release", "getOffsetPair", "setOffsetPair", "getRightBorder", "setRightBorder", "getShowPattern", "()Lcom/lzf/easyfloat/enums/ShowPattern;", "setShowPattern", "(Lcom/lzf/easyfloat/enums/ShowPattern;)V", "getSidePattern", "()Lcom/lzf/easyfloat/enums/SidePattern;", "setSidePattern", "(Lcom/lzf/easyfloat/enums/SidePattern;)V", "getTopBorder", "setTopBorder", "getWidthMatch", "setWidthMatch", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component27$easyfloat_release", "component28", "component28$easyfloat_release", "component29", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/Integer;Landroid/view/View;Ljava/lang/String;ZZZZZZLcom/lzf/easyfloat/enums/SidePattern;Lcom/lzf/easyfloat/enums/ShowPattern;ZZILkotlin/Pair;Lkotlin/Pair;IIIILcom/lzf/easyfloat/interfaces/OnInvokeView;Lcom/lzf/easyfloat/interfaces/OnFloatCallbacks;Lcom/lzf/easyfloat/interfaces/FloatCallbacks;Lcom/lzf/easyfloat/interfaces/OnFloatAnimator;Lcom/lzf/easyfloat/interfaces/OnDisplayHeight;Ljava/util/Set;ZZI)Lcom/lzf/easyfloat/data/FloatConfig;", "equals", "other", "hashCode", "toString", "easyfloat_release" }, k = 1, mv = { 1, 4, 1 })
public final class FloatConfig
{
    private int bottomBorder;
    private OnFloatCallbacks callbacks;
    private OnDisplayHeight displayHeight;
    private boolean dragEnable;
    private boolean filterSelf;
    private final Set<String> filterSet;
    private OnFloatAnimator floatAnimator;
    private FloatCallbacks floatCallbacks;
    private String floatTag;
    private int gravity;
    private boolean hasEditText;
    private boolean heightMatch;
    private boolean immersionStatusBar;
    private OnInvokeView invokeView;
    private boolean isAnim;
    private boolean isDrag;
    private boolean isShow;
    private int layoutChangedGravity;
    private Integer layoutId;
    private View layoutView;
    private int leftBorder;
    private Pair<Integer, Integer> locationPair;
    private boolean needShow;
    private Pair<Integer, Integer> offsetPair;
    private int rightBorder;
    private ShowPattern showPattern;
    private SidePattern sidePattern;
    private int topBorder;
    private boolean widthMatch;
    
    public FloatConfig() {
        this(null, null, null, false, false, false, false, false, false, null, null, false, false, 0, null, null, 0, 0, 0, 0, null, null, null, null, null, null, false, false, 0, 536870911, null);
    }
    
    public FloatConfig(final Integer layoutId, final View layoutView, final String floatTag, final boolean dragEnable, final boolean isDrag, final boolean isAnim, final boolean isShow, final boolean hasEditText, final boolean immersionStatusBar, final SidePattern sidePattern, final ShowPattern showPattern, final boolean widthMatch, final boolean heightMatch, final int gravity, final Pair<Integer, Integer> offsetPair, final Pair<Integer, Integer> locationPair, final int leftBorder, final int topBorder, final int rightBorder, final int bottomBorder, final OnInvokeView invokeView, final OnFloatCallbacks callbacks, final FloatCallbacks floatCallbacks, final OnFloatAnimator floatAnimator, final OnDisplayHeight displayHeight, final Set<String> filterSet, final boolean filterSelf, final boolean needShow, final int layoutChangedGravity) {
        Intrinsics.checkNotNullParameter((Object)sidePattern, "sidePattern");
        Intrinsics.checkNotNullParameter((Object)showPattern, "showPattern");
        Intrinsics.checkNotNullParameter((Object)offsetPair, "offsetPair");
        Intrinsics.checkNotNullParameter((Object)locationPair, "locationPair");
        Intrinsics.checkNotNullParameter((Object)displayHeight, "displayHeight");
        Intrinsics.checkNotNullParameter((Object)filterSet, "filterSet");
        this.layoutId = layoutId;
        this.layoutView = layoutView;
        this.floatTag = floatTag;
        this.dragEnable = dragEnable;
        this.isDrag = isDrag;
        this.isAnim = isAnim;
        this.isShow = isShow;
        this.hasEditText = hasEditText;
        this.immersionStatusBar = immersionStatusBar;
        this.sidePattern = sidePattern;
        this.showPattern = showPattern;
        this.widthMatch = widthMatch;
        this.heightMatch = heightMatch;
        this.gravity = gravity;
        this.offsetPair = offsetPair;
        this.locationPair = locationPair;
        this.leftBorder = leftBorder;
        this.topBorder = topBorder;
        this.rightBorder = rightBorder;
        this.bottomBorder = bottomBorder;
        this.invokeView = invokeView;
        this.callbacks = callbacks;
        this.floatCallbacks = floatCallbacks;
        this.floatAnimator = floatAnimator;
        this.displayHeight = displayHeight;
        this.filterSet = filterSet;
        this.filterSelf = filterSelf;
        this.needShow = needShow;
        this.layoutChangedGravity = layoutChangedGravity;
    }
    
    public final Integer component1() {
        return this.layoutId;
    }
    
    public final SidePattern component10() {
        return this.sidePattern;
    }
    
    public final ShowPattern component11() {
        return this.showPattern;
    }
    
    public final boolean component12() {
        return this.widthMatch;
    }
    
    public final boolean component13() {
        return this.heightMatch;
    }
    
    public final int component14() {
        return this.gravity;
    }
    
    public final Pair<Integer, Integer> component15() {
        return this.offsetPair;
    }
    
    public final Pair<Integer, Integer> component16() {
        return this.locationPair;
    }
    
    public final int component17() {
        return this.leftBorder;
    }
    
    public final int component18() {
        return this.topBorder;
    }
    
    public final int component19() {
        return this.rightBorder;
    }
    
    public final View component2() {
        return this.layoutView;
    }
    
    public final int component20() {
        return this.bottomBorder;
    }
    
    public final OnInvokeView component21() {
        return this.invokeView;
    }
    
    public final OnFloatCallbacks component22() {
        return this.callbacks;
    }
    
    public final FloatCallbacks component23() {
        return this.floatCallbacks;
    }
    
    public final OnFloatAnimator component24() {
        return this.floatAnimator;
    }
    
    public final OnDisplayHeight component25() {
        return this.displayHeight;
    }
    
    public final Set<String> component26() {
        return this.filterSet;
    }
    
    public final boolean component27$easyfloat_release() {
        return this.filterSelf;
    }
    
    public final boolean component28$easyfloat_release() {
        return this.needShow;
    }
    
    public final int component29() {
        return this.layoutChangedGravity;
    }
    
    public final String component3() {
        return this.floatTag;
    }
    
    public final boolean component4() {
        return this.dragEnable;
    }
    
    public final boolean component5() {
        return this.isDrag;
    }
    
    public final boolean component6() {
        return this.isAnim;
    }
    
    public final boolean component7() {
        return this.isShow;
    }
    
    public final boolean component8() {
        return this.hasEditText;
    }
    
    public final boolean component9() {
        return this.immersionStatusBar;
    }
    
    public final FloatConfig copy(final Integer n, final View view, final String s, final boolean b, final boolean b2, final boolean b3, final boolean b4, final boolean b5, final boolean b6, final SidePattern sidePattern, final ShowPattern showPattern, final boolean b7, final boolean b8, final int n2, final Pair<Integer, Integer> pair, final Pair<Integer, Integer> pair2, final int n3, final int n4, final int n5, final int n6, final OnInvokeView onInvokeView, final OnFloatCallbacks onFloatCallbacks, final FloatCallbacks floatCallbacks, final OnFloatAnimator onFloatAnimator, final OnDisplayHeight onDisplayHeight, final Set<String> set, final boolean b9, final boolean b10, final int n7) {
        Intrinsics.checkNotNullParameter((Object)sidePattern, "sidePattern");
        Intrinsics.checkNotNullParameter((Object)showPattern, "showPattern");
        Intrinsics.checkNotNullParameter((Object)pair, "offsetPair");
        Intrinsics.checkNotNullParameter((Object)pair2, "locationPair");
        Intrinsics.checkNotNullParameter((Object)onDisplayHeight, "displayHeight");
        Intrinsics.checkNotNullParameter((Object)set, "filterSet");
        return new FloatConfig(n, view, s, b, b2, b3, b4, b5, b6, sidePattern, showPattern, b7, b8, n2, pair, pair2, n3, n4, n5, n6, onInvokeView, onFloatCallbacks, floatCallbacks, onFloatAnimator, onDisplayHeight, set, b9, b10, n7);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o instanceof FloatConfig) {
                final FloatConfig floatConfig = (FloatConfig)o;
                if (Intrinsics.areEqual((Object)this.layoutId, (Object)floatConfig.layoutId) && Intrinsics.areEqual((Object)this.layoutView, (Object)floatConfig.layoutView) && Intrinsics.areEqual((Object)this.floatTag, (Object)floatConfig.floatTag) && this.dragEnable == floatConfig.dragEnable && this.isDrag == floatConfig.isDrag && this.isAnim == floatConfig.isAnim && this.isShow == floatConfig.isShow && this.hasEditText == floatConfig.hasEditText && this.immersionStatusBar == floatConfig.immersionStatusBar && Intrinsics.areEqual((Object)this.sidePattern, (Object)floatConfig.sidePattern) && Intrinsics.areEqual((Object)this.showPattern, (Object)floatConfig.showPattern) && this.widthMatch == floatConfig.widthMatch && this.heightMatch == floatConfig.heightMatch && this.gravity == floatConfig.gravity && Intrinsics.areEqual((Object)this.offsetPair, (Object)floatConfig.offsetPair) && Intrinsics.areEqual((Object)this.locationPair, (Object)floatConfig.locationPair) && this.leftBorder == floatConfig.leftBorder && this.topBorder == floatConfig.topBorder && this.rightBorder == floatConfig.rightBorder && this.bottomBorder == floatConfig.bottomBorder && Intrinsics.areEqual((Object)this.invokeView, (Object)floatConfig.invokeView) && Intrinsics.areEqual((Object)this.callbacks, (Object)floatConfig.callbacks) && Intrinsics.areEqual((Object)this.floatCallbacks, (Object)floatConfig.floatCallbacks) && Intrinsics.areEqual((Object)this.floatAnimator, (Object)floatConfig.floatAnimator) && Intrinsics.areEqual((Object)this.displayHeight, (Object)floatConfig.displayHeight) && Intrinsics.areEqual((Object)this.filterSet, (Object)floatConfig.filterSet) && this.filterSelf == floatConfig.filterSelf && this.needShow == floatConfig.needShow && this.layoutChangedGravity == floatConfig.layoutChangedGravity) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }
    
    public final int getBottomBorder() {
        return this.bottomBorder;
    }
    
    public final OnFloatCallbacks getCallbacks() {
        return this.callbacks;
    }
    
    public final OnDisplayHeight getDisplayHeight() {
        return this.displayHeight;
    }
    
    public final boolean getDragEnable() {
        return this.dragEnable;
    }
    
    public final boolean getFilterSelf$easyfloat_release() {
        return this.filterSelf;
    }
    
    public final Set<String> getFilterSet() {
        return this.filterSet;
    }
    
    public final OnFloatAnimator getFloatAnimator() {
        return this.floatAnimator;
    }
    
    public final FloatCallbacks getFloatCallbacks() {
        return this.floatCallbacks;
    }
    
    public final String getFloatTag() {
        return this.floatTag;
    }
    
    public final int getGravity() {
        return this.gravity;
    }
    
    public final boolean getHasEditText() {
        return this.hasEditText;
    }
    
    public final boolean getHeightMatch() {
        return this.heightMatch;
    }
    
    public final boolean getImmersionStatusBar() {
        return this.immersionStatusBar;
    }
    
    public final OnInvokeView getInvokeView() {
        return this.invokeView;
    }
    
    public final int getLayoutChangedGravity() {
        return this.layoutChangedGravity;
    }
    
    public final Integer getLayoutId() {
        return this.layoutId;
    }
    
    public final View getLayoutView() {
        return this.layoutView;
    }
    
    public final int getLeftBorder() {
        return this.leftBorder;
    }
    
    public final Pair<Integer, Integer> getLocationPair() {
        return this.locationPair;
    }
    
    public final boolean getNeedShow$easyfloat_release() {
        return this.needShow;
    }
    
    public final Pair<Integer, Integer> getOffsetPair() {
        return this.offsetPair;
    }
    
    public final int getRightBorder() {
        return this.rightBorder;
    }
    
    public final ShowPattern getShowPattern() {
        return this.showPattern;
    }
    
    public final SidePattern getSidePattern() {
        return this.sidePattern;
    }
    
    public final int getTopBorder() {
        return this.topBorder;
    }
    
    public final boolean getWidthMatch() {
        return this.widthMatch;
    }
    
    @Override
    public int hashCode() {
        final Integer layoutId = this.layoutId;
        int hashCode = 0;
        int hashCode2;
        if (layoutId != null) {
            hashCode2 = layoutId.hashCode();
        }
        else {
            hashCode2 = 0;
        }
        final View layoutView = this.layoutView;
        int hashCode3;
        if (layoutView != null) {
            hashCode3 = layoutView.hashCode();
        }
        else {
            hashCode3 = 0;
        }
        final String floatTag = this.floatTag;
        int hashCode4;
        if (floatTag != null) {
            hashCode4 = floatTag.hashCode();
        }
        else {
            hashCode4 = 0;
        }
        final int dragEnable = this.dragEnable ? 1 : 0;
        int n = 1;
        int n2 = dragEnable;
        if (dragEnable != 0) {
            n2 = 1;
        }
        int isDrag;
        if ((isDrag = (this.isDrag ? 1 : 0)) != 0) {
            isDrag = 1;
        }
        int isAnim;
        if ((isAnim = (this.isAnim ? 1 : 0)) != 0) {
            isAnim = 1;
        }
        int isShow;
        if ((isShow = (this.isShow ? 1 : 0)) != 0) {
            isShow = 1;
        }
        int hasEditText;
        if ((hasEditText = (this.hasEditText ? 1 : 0)) != 0) {
            hasEditText = 1;
        }
        int immersionStatusBar;
        if ((immersionStatusBar = (this.immersionStatusBar ? 1 : 0)) != 0) {
            immersionStatusBar = 1;
        }
        final SidePattern sidePattern = this.sidePattern;
        int hashCode5;
        if (sidePattern != null) {
            hashCode5 = sidePattern.hashCode();
        }
        else {
            hashCode5 = 0;
        }
        final ShowPattern showPattern = this.showPattern;
        int hashCode6;
        if (showPattern != null) {
            hashCode6 = showPattern.hashCode();
        }
        else {
            hashCode6 = 0;
        }
        int widthMatch;
        if ((widthMatch = (this.widthMatch ? 1 : 0)) != 0) {
            widthMatch = 1;
        }
        int heightMatch;
        if ((heightMatch = (this.heightMatch ? 1 : 0)) != 0) {
            heightMatch = 1;
        }
        final int gravity = this.gravity;
        final Pair<Integer, Integer> offsetPair = this.offsetPair;
        int hashCode7;
        if (offsetPair != null) {
            hashCode7 = offsetPair.hashCode();
        }
        else {
            hashCode7 = 0;
        }
        final Pair<Integer, Integer> locationPair = this.locationPair;
        int hashCode8;
        if (locationPair != null) {
            hashCode8 = locationPair.hashCode();
        }
        else {
            hashCode8 = 0;
        }
        final int leftBorder = this.leftBorder;
        final int topBorder = this.topBorder;
        final int rightBorder = this.rightBorder;
        final int bottomBorder = this.bottomBorder;
        final OnInvokeView invokeView = this.invokeView;
        int hashCode9;
        if (invokeView != null) {
            hashCode9 = invokeView.hashCode();
        }
        else {
            hashCode9 = 0;
        }
        final OnFloatCallbacks callbacks = this.callbacks;
        int hashCode10;
        if (callbacks != null) {
            hashCode10 = callbacks.hashCode();
        }
        else {
            hashCode10 = 0;
        }
        final FloatCallbacks floatCallbacks = this.floatCallbacks;
        int hashCode11;
        if (floatCallbacks != null) {
            hashCode11 = floatCallbacks.hashCode();
        }
        else {
            hashCode11 = 0;
        }
        final OnFloatAnimator floatAnimator = this.floatAnimator;
        int hashCode12;
        if (floatAnimator != null) {
            hashCode12 = floatAnimator.hashCode();
        }
        else {
            hashCode12 = 0;
        }
        final OnDisplayHeight displayHeight = this.displayHeight;
        int hashCode13;
        if (displayHeight != null) {
            hashCode13 = displayHeight.hashCode();
        }
        else {
            hashCode13 = 0;
        }
        final Set<String> filterSet = this.filterSet;
        if (filterSet != null) {
            hashCode = filterSet.hashCode();
        }
        int filterSelf;
        if ((filterSelf = (this.filterSelf ? 1 : 0)) != 0) {
            filterSelf = 1;
        }
        final int needShow = this.needShow ? 1 : 0;
        if (needShow == 0) {
            n = needShow;
        }
        return (((((((((((((((((((((((((((hashCode2 * 31 + hashCode3) * 31 + hashCode4) * 31 + n2) * 31 + isDrag) * 31 + isAnim) * 31 + isShow) * 31 + hasEditText) * 31 + immersionStatusBar) * 31 + hashCode5) * 31 + hashCode6) * 31 + widthMatch) * 31 + heightMatch) * 31 + gravity) * 31 + hashCode7) * 31 + hashCode8) * 31 + leftBorder) * 31 + topBorder) * 31 + rightBorder) * 31 + bottomBorder) * 31 + hashCode9) * 31 + hashCode10) * 31 + hashCode11) * 31 + hashCode12) * 31 + hashCode13) * 31 + hashCode) * 31 + filterSelf) * 31 + n) * 31 + this.layoutChangedGravity;
    }
    
    public final boolean isAnim() {
        return this.isAnim;
    }
    
    public final boolean isDrag() {
        return this.isDrag;
    }
    
    public final boolean isShow() {
        return this.isShow;
    }
    
    public final void setAnim(final boolean isAnim) {
        this.isAnim = isAnim;
    }
    
    public final void setBottomBorder(final int bottomBorder) {
        this.bottomBorder = bottomBorder;
    }
    
    public final void setCallbacks(final OnFloatCallbacks callbacks) {
        this.callbacks = callbacks;
    }
    
    public final void setDisplayHeight(final OnDisplayHeight displayHeight) {
        Intrinsics.checkNotNullParameter((Object)displayHeight, "<set-?>");
        this.displayHeight = displayHeight;
    }
    
    public final void setDrag(final boolean isDrag) {
        this.isDrag = isDrag;
    }
    
    public final void setDragEnable(final boolean dragEnable) {
        this.dragEnable = dragEnable;
    }
    
    public final void setFilterSelf$easyfloat_release(final boolean filterSelf) {
        this.filterSelf = filterSelf;
    }
    
    public final void setFloatAnimator(final OnFloatAnimator floatAnimator) {
        this.floatAnimator = floatAnimator;
    }
    
    public final void setFloatCallbacks(final FloatCallbacks floatCallbacks) {
        this.floatCallbacks = floatCallbacks;
    }
    
    public final void setFloatTag(final String floatTag) {
        this.floatTag = floatTag;
    }
    
    public final void setGravity(final int gravity) {
        this.gravity = gravity;
    }
    
    public final void setHasEditText(final boolean hasEditText) {
        this.hasEditText = hasEditText;
    }
    
    public final void setHeightMatch(final boolean heightMatch) {
        this.heightMatch = heightMatch;
    }
    
    public final void setImmersionStatusBar(final boolean immersionStatusBar) {
        this.immersionStatusBar = immersionStatusBar;
    }
    
    public final void setInvokeView(final OnInvokeView invokeView) {
        this.invokeView = invokeView;
    }
    
    public final void setLayoutChangedGravity(final int layoutChangedGravity) {
        this.layoutChangedGravity = layoutChangedGravity;
    }
    
    public final void setLayoutId(final Integer layoutId) {
        this.layoutId = layoutId;
    }
    
    public final void setLayoutView(final View layoutView) {
        this.layoutView = layoutView;
    }
    
    public final void setLeftBorder(final int leftBorder) {
        this.leftBorder = leftBorder;
    }
    
    public final void setLocationPair(final Pair<Integer, Integer> locationPair) {
        Intrinsics.checkNotNullParameter((Object)locationPair, "<set-?>");
        this.locationPair = locationPair;
    }
    
    public final void setNeedShow$easyfloat_release(final boolean needShow) {
        this.needShow = needShow;
    }
    
    public final void setOffsetPair(final Pair<Integer, Integer> offsetPair) {
        Intrinsics.checkNotNullParameter((Object)offsetPair, "<set-?>");
        this.offsetPair = offsetPair;
    }
    
    public final void setRightBorder(final int rightBorder) {
        this.rightBorder = rightBorder;
    }
    
    public final void setShow(final boolean isShow) {
        this.isShow = isShow;
    }
    
    public final void setShowPattern(final ShowPattern showPattern) {
        Intrinsics.checkNotNullParameter((Object)showPattern, "<set-?>");
        this.showPattern = showPattern;
    }
    
    public final void setSidePattern(final SidePattern sidePattern) {
        Intrinsics.checkNotNullParameter((Object)sidePattern, "<set-?>");
        this.sidePattern = sidePattern;
    }
    
    public final void setTopBorder(final int topBorder) {
        this.topBorder = topBorder;
    }
    
    public final void setWidthMatch(final boolean widthMatch) {
        this.widthMatch = widthMatch;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("FloatConfig(layoutId=");
        sb.append((Object)this.layoutId);
        sb.append(", layoutView=");
        sb.append((Object)this.layoutView);
        sb.append(", floatTag=");
        sb.append(this.floatTag);
        sb.append(", dragEnable=");
        sb.append(this.dragEnable);
        sb.append(", isDrag=");
        sb.append(this.isDrag);
        sb.append(", isAnim=");
        sb.append(this.isAnim);
        sb.append(", isShow=");
        sb.append(this.isShow);
        sb.append(", hasEditText=");
        sb.append(this.hasEditText);
        sb.append(", immersionStatusBar=");
        sb.append(this.immersionStatusBar);
        sb.append(", sidePattern=");
        sb.append((Object)this.sidePattern);
        sb.append(", showPattern=");
        sb.append((Object)this.showPattern);
        sb.append(", widthMatch=");
        sb.append(this.widthMatch);
        sb.append(", heightMatch=");
        sb.append(this.heightMatch);
        sb.append(", gravity=");
        sb.append(this.gravity);
        sb.append(", offsetPair=");
        sb.append((Object)this.offsetPair);
        sb.append(", locationPair=");
        sb.append((Object)this.locationPair);
        sb.append(", leftBorder=");
        sb.append(this.leftBorder);
        sb.append(", topBorder=");
        sb.append(this.topBorder);
        sb.append(", rightBorder=");
        sb.append(this.rightBorder);
        sb.append(", bottomBorder=");
        sb.append(this.bottomBorder);
        sb.append(", invokeView=");
        sb.append((Object)this.invokeView);
        sb.append(", callbacks=");
        sb.append((Object)this.callbacks);
        sb.append(", floatCallbacks=");
        sb.append((Object)this.floatCallbacks);
        sb.append(", floatAnimator=");
        sb.append((Object)this.floatAnimator);
        sb.append(", displayHeight=");
        sb.append((Object)this.displayHeight);
        sb.append(", filterSet=");
        sb.append((Object)this.filterSet);
        sb.append(", filterSelf=");
        sb.append(this.filterSelf);
        sb.append(", needShow=");
        sb.append(this.needShow);
        sb.append(", layoutChangedGravity=");
        sb.append(this.layoutChangedGravity);
        sb.append(")");
        return sb.toString();
    }
}
