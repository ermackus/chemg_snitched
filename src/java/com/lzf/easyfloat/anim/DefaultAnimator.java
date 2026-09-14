package com.lzf.easyfloat.anim;

import android.graphics.Rect;
import com.lzf.easyfloat.utils.DisplayUtils;
import kotlin.jvm.internal.Intrinsics;
import kotlin.Triple;
import android.animation.ValueAnimator$AnimatorUpdateListener;
import android.animation.ValueAnimator;
import android.animation.Animator;
import com.lzf.easyfloat.enums.SidePattern;
import android.view.WindowManager;
import android.view.WindowManager$LayoutParams;
import android.view.View;
import kotlin.Metadata;
import com.lzf.easyfloat.interfaces.OnFloatAnimator;

@Metadata(bv = { 1, 0, 3 }, d1 = { "\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J*\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J*\u0010\r\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J0\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J:\u0010\u0013\u001a\u0014\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00100\u00142\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002¨\u0006\u0015" }, d2 = { "Lcom/lzf/easyfloat/anim/DefaultAnimator;", "Lcom/lzf/easyfloat/interfaces/OnFloatAnimator;", "()V", "enterAnim", "Landroid/animation/Animator;", "view", "Landroid/view/View;", "params", "Landroid/view/WindowManager$LayoutParams;", "windowManager", "Landroid/view/WindowManager;", "sidePattern", "Lcom/lzf/easyfloat/enums/SidePattern;", "exitAnim", "getAnimator", "isExit", "", "getCompensationHeight", "", "initValue", "Lkotlin/Triple;", "easyfloat_release" }, k = 1, mv = { 1, 4, 1 })
public class DefaultAnimator implements OnFloatAnimator
{
    private final Animator getAnimator(final View view, final WindowManager$LayoutParams windowManager$LayoutParams, final WindowManager windowManager, final SidePattern sidePattern, final boolean b) {
        final Triple<Integer, Integer, Boolean> initValue = this.initValue(view, windowManager$LayoutParams, windowManager, sidePattern);
        Object o;
        if (b) {
            o = initValue.getSecond();
        }
        else {
            o = initValue.getFirst();
        }
        final int intValue = ((Number)o).intValue();
        Object o2;
        if (b) {
            o2 = initValue.getFirst();
        }
        else {
            o2 = initValue.getSecond();
        }
        final ValueAnimator ofInt = ValueAnimator.ofInt(new int[] { intValue, ((Number)o2).intValue() });
        ofInt.addUpdateListener((ValueAnimator$AnimatorUpdateListener)new DefaultAnimator$getAnimator$$inlined$apply$lambda$1(ofInt, (Triple)initValue, windowManager$LayoutParams, windowManager, view));
        Intrinsics.checkNotNullExpressionValue((Object)ofInt, "ValueAnimator.ofInt(star\u2026}\n            }\n        }");
        return (Animator)ofInt;
    }
    
    private final int getCompensationHeight(final View view, final WindowManager$LayoutParams windowManager$LayoutParams) {
        final int[] array = new int[2];
        view.getLocationOnScreen(array);
        int statusBarHeight;
        if (array[1] == windowManager$LayoutParams.y) {
            statusBarHeight = DisplayUtils.INSTANCE.statusBarHeight(view);
        }
        else {
            statusBarHeight = 0;
        }
        return statusBarHeight;
    }
    
    private final Triple<Integer, Integer, Boolean> initValue(final View view, final WindowManager$LayoutParams windowManager$LayoutParams, final WindowManager windowManager, final SidePattern sidePattern) {
        final Rect rect = new Rect();
        windowManager.getDefaultDisplay().getRectSize(rect);
        final int x = windowManager$LayoutParams.x;
        final int n = rect.right - (view.getRight() + x);
        final int y = windowManager$LayoutParams.y;
        final int n2 = rect.bottom - (view.getBottom() + y);
        final int min = Math.min(x, n);
        final int min2 = Math.min(y, n2);
        final int n3 = DefaultAnimator$WhenMappings.$EnumSwitchMapping$0[sidePattern.ordinal()];
        boolean b = false;
        int n7 = 0;
        int right = 0;
        Label_0362: {
            int n8 = 0;
            Label_0325: {
                int n9 = 0;
                int n10 = 0;
                Label_0303: {
                    int n4 = 0;
                    int n6 = 0;
                    Label_0267: {
                        int n5 = 0;
                        switch (n3) {
                            default: {
                                if (min <= min2) {
                                    n4 = windowManager$LayoutParams.x;
                                    if (x < n) {
                                        n5 = view.getRight();
                                        break;
                                    }
                                    n6 = rect.right;
                                    break Label_0267;
                                }
                                else {
                                    n7 = windowManager$LayoutParams.y;
                                    if (y < n2) {
                                        n8 = view.getBottom();
                                        break Label_0325;
                                    }
                                    n9 = rect.bottom;
                                    n10 = this.getCompensationHeight(view, windowManager$LayoutParams);
                                    break Label_0303;
                                }
                                break;
                            }
                            case 12:
                            case 13: {
                                n7 = windowManager$LayoutParams.y;
                                if (y < n2) {
                                    n8 = view.getBottom();
                                    break Label_0325;
                                }
                                n9 = rect.bottom;
                                n10 = this.getCompensationHeight(view, windowManager$LayoutParams);
                                break Label_0303;
                            }
                            case 9:
                            case 10:
                            case 11: {
                                n4 = windowManager$LayoutParams.x;
                                if (x < n) {
                                    n5 = view.getRight();
                                    break;
                                }
                                n6 = rect.right;
                                break Label_0267;
                            }
                            case 7:
                            case 8: {
                                n7 = windowManager$LayoutParams.y;
                                n9 = rect.bottom;
                                n10 = this.getCompensationHeight(view, windowManager$LayoutParams);
                                break Label_0303;
                            }
                            case 5:
                            case 6: {
                                n7 = windowManager$LayoutParams.y;
                                n8 = view.getBottom();
                                break Label_0325;
                            }
                            case 3:
                            case 4: {
                                n7 = windowManager$LayoutParams.x;
                                right = rect.right;
                                break Label_0362;
                            }
                            case 1:
                            case 2: {
                                n7 = windowManager$LayoutParams.x;
                                right = -view.getRight();
                                break Label_0362;
                            }
                        }
                        n6 = -n5;
                    }
                    final int n11 = n4;
                    right = n6;
                    n7 = n11;
                    break Label_0362;
                }
                right = n10 + n9;
                return (Triple<Integer, Integer, Boolean>)new Triple((Object)right, (Object)n7, (Object)b);
            }
            right = -n8;
            return (Triple<Integer, Integer, Boolean>)new Triple((Object)right, (Object)n7, (Object)b);
        }
        b = true;
        return (Triple<Integer, Integer, Boolean>)new Triple((Object)right, (Object)n7, (Object)b);
    }
    
    public Animator enterAnim(final View view, final WindowManager$LayoutParams windowManager$LayoutParams, final WindowManager windowManager, final SidePattern sidePattern) {
        Intrinsics.checkNotNullParameter((Object)view, "view");
        Intrinsics.checkNotNullParameter((Object)windowManager$LayoutParams, "params");
        Intrinsics.checkNotNullParameter((Object)windowManager, "windowManager");
        Intrinsics.checkNotNullParameter((Object)sidePattern, "sidePattern");
        return this.getAnimator(view, windowManager$LayoutParams, windowManager, sidePattern, false);
    }
    
    public Animator exitAnim(final View view, final WindowManager$LayoutParams windowManager$LayoutParams, final WindowManager windowManager, final SidePattern sidePattern) {
        Intrinsics.checkNotNullParameter((Object)view, "view");
        Intrinsics.checkNotNullParameter((Object)windowManager$LayoutParams, "params");
        Intrinsics.checkNotNullParameter((Object)windowManager, "windowManager");
        Intrinsics.checkNotNullParameter((Object)sidePattern, "sidePattern");
        return this.getAnimator(view, windowManager$LayoutParams, windowManager, sidePattern, true);
    }
}
