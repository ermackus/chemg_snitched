package com.kingagroot.kingdraw.adapter;

import java.util.Iterator;
import androidx.core.view.ViewCompat;
import java.util.Collection;
import android.animation.ValueAnimator;
import java.util.List;
import android.view.ViewPropertyAnimator;
import android.view.View;
import android.animation.Animator$AnimatorListener;
import androidx.recyclerview.widget.RecyclerView$ViewHolder;
import java.util.ArrayList;
import android.animation.TimeInterpolator;
import androidx.recyclerview.widget.SimpleItemAnimator;

public class SynAdapterAnim extends SimpleItemAnimator
{
    private static final boolean DEBUG = false;
    private static TimeInterpolator sDefaultInterpolator;
    ArrayList<RecyclerView$ViewHolder> mAddAnimations;
    ArrayList<ArrayList<RecyclerView$ViewHolder>> mAdditionsList;
    ArrayList<RecyclerView$ViewHolder> mChangeAnimations;
    ArrayList<ArrayList<SynAdapterAnim.SynAdapterAnim$ChangeInfo>> mChangesList;
    ArrayList<RecyclerView$ViewHolder> mMoveAnimations;
    ArrayList<ArrayList<SynAdapterAnim.SynAdapterAnim$MoveInfo>> mMovesList;
    private final ArrayList<RecyclerView$ViewHolder> mPendingAdditions;
    private final ArrayList<SynAdapterAnim.SynAdapterAnim$ChangeInfo> mPendingChanges;
    private final ArrayList<SynAdapterAnim.SynAdapterAnim$MoveInfo> mPendingMoves;
    private final ArrayList<RecyclerView$ViewHolder> mPendingRemovals;
    ArrayList<RecyclerView$ViewHolder> mRemoveAnimations;
    
    public SynAdapterAnim() {
        this.mPendingRemovals = (ArrayList<RecyclerView$ViewHolder>)new ArrayList();
        this.mPendingAdditions = (ArrayList<RecyclerView$ViewHolder>)new ArrayList();
        this.mPendingMoves = (ArrayList<SynAdapterAnim.SynAdapterAnim$MoveInfo>)new ArrayList();
        this.mPendingChanges = (ArrayList<SynAdapterAnim.SynAdapterAnim$ChangeInfo>)new ArrayList();
        this.mAdditionsList = (ArrayList<ArrayList<RecyclerView$ViewHolder>>)new ArrayList();
        this.mMovesList = (ArrayList<ArrayList<SynAdapterAnim.SynAdapterAnim$MoveInfo>>)new ArrayList();
        this.mChangesList = (ArrayList<ArrayList<SynAdapterAnim.SynAdapterAnim$ChangeInfo>>)new ArrayList();
        this.mAddAnimations = (ArrayList<RecyclerView$ViewHolder>)new ArrayList();
        this.mMoveAnimations = (ArrayList<RecyclerView$ViewHolder>)new ArrayList();
        this.mRemoveAnimations = (ArrayList<RecyclerView$ViewHolder>)new ArrayList();
        this.mChangeAnimations = (ArrayList<RecyclerView$ViewHolder>)new ArrayList();
    }
    
    private void animateRemoveImpl(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        final View itemView = recyclerView$ViewHolder.itemView;
        final ViewPropertyAnimator animate = itemView.animate();
        this.mRemoveAnimations.add((Object)recyclerView$ViewHolder);
        animate.setDuration(this.getRemoveDuration()).translationX((float)(-itemView.getWidth())).setListener((Animator$AnimatorListener)new SynAdapterAnim$4(this, recyclerView$ViewHolder, animate, itemView)).start();
    }
    
    private void endChangeAnimation(final List<SynAdapterAnim.SynAdapterAnim$ChangeInfo> list, final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        for (int i = list.size() - 1; i >= 0; --i) {
            final SynAdapterAnim.SynAdapterAnim$ChangeInfo synAdapterAnim$ChangeInfo = (SynAdapterAnim.SynAdapterAnim$ChangeInfo)list.get(i);
            if (this.endChangeAnimationIfNecessary(synAdapterAnim$ChangeInfo, recyclerView$ViewHolder) && synAdapterAnim$ChangeInfo.oldHolder == null && synAdapterAnim$ChangeInfo.newHolder == null) {
                list.remove((Object)synAdapterAnim$ChangeInfo);
            }
        }
    }
    
    private void endChangeAnimationIfNecessary(final SynAdapterAnim.SynAdapterAnim$ChangeInfo synAdapterAnim$ChangeInfo) {
        if (synAdapterAnim$ChangeInfo.oldHolder != null) {
            this.endChangeAnimationIfNecessary(synAdapterAnim$ChangeInfo, synAdapterAnim$ChangeInfo.oldHolder);
        }
        if (synAdapterAnim$ChangeInfo.newHolder != null) {
            this.endChangeAnimationIfNecessary(synAdapterAnim$ChangeInfo, synAdapterAnim$ChangeInfo.newHolder);
        }
    }
    
    private boolean endChangeAnimationIfNecessary(final SynAdapterAnim.SynAdapterAnim$ChangeInfo synAdapterAnim$ChangeInfo, final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        final RecyclerView$ViewHolder newHolder = synAdapterAnim$ChangeInfo.newHolder;
        boolean b = false;
        if (newHolder == recyclerView$ViewHolder) {
            synAdapterAnim$ChangeInfo.newHolder = null;
        }
        else {
            if (synAdapterAnim$ChangeInfo.oldHolder != recyclerView$ViewHolder) {
                return false;
            }
            synAdapterAnim$ChangeInfo.oldHolder = null;
            b = true;
        }
        recyclerView$ViewHolder.itemView.setAlpha(1.0f);
        recyclerView$ViewHolder.itemView.setTranslationX(0.0f);
        recyclerView$ViewHolder.itemView.setTranslationY(0.0f);
        this.dispatchChangeFinished(recyclerView$ViewHolder, b);
        return true;
    }
    
    private void resetAnimation(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        if (SynAdapterAnim.sDefaultInterpolator == null) {
            SynAdapterAnim.sDefaultInterpolator = new ValueAnimator().getInterpolator();
        }
        recyclerView$ViewHolder.itemView.animate().setInterpolator(SynAdapterAnim.sDefaultInterpolator);
        this.endAnimation(recyclerView$ViewHolder);
    }
    
    public boolean animateAdd(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        this.resetAnimation(recyclerView$ViewHolder);
        recyclerView$ViewHolder.itemView.setAlpha(0.0f);
        this.mPendingAdditions.add((Object)recyclerView$ViewHolder);
        return true;
    }
    
    void animateAddImpl(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        final View itemView = recyclerView$ViewHolder.itemView;
        final ViewPropertyAnimator animate = itemView.animate();
        this.mAddAnimations.add((Object)recyclerView$ViewHolder);
        animate.alpha(1.0f).setDuration(this.getAddDuration()).setListener((Animator$AnimatorListener)new SynAdapterAnim$5(this, recyclerView$ViewHolder, itemView, animate)).start();
    }
    
    public boolean animateChange(final RecyclerView$ViewHolder recyclerView$ViewHolder, final RecyclerView$ViewHolder recyclerView$ViewHolder2, final int n, final int n2, final int n3, final int n4) {
        if (recyclerView$ViewHolder == recyclerView$ViewHolder2) {
            return this.animateMove(recyclerView$ViewHolder, n, n2, n3, n4);
        }
        final float translationX = recyclerView$ViewHolder.itemView.getTranslationX();
        final float translationY = recyclerView$ViewHolder.itemView.getTranslationY();
        final float alpha = recyclerView$ViewHolder.itemView.getAlpha();
        this.resetAnimation(recyclerView$ViewHolder);
        final int n5 = (int)(n3 - n - translationX);
        final int n6 = (int)(n4 - n2 - translationY);
        recyclerView$ViewHolder.itemView.setTranslationX(translationX);
        recyclerView$ViewHolder.itemView.setTranslationY(translationY);
        recyclerView$ViewHolder.itemView.setAlpha(alpha);
        if (recyclerView$ViewHolder2 != null) {
            this.resetAnimation(recyclerView$ViewHolder2);
            recyclerView$ViewHolder2.itemView.setTranslationX((float)(-n5));
            recyclerView$ViewHolder2.itemView.setTranslationY((float)(-n6));
            recyclerView$ViewHolder2.itemView.setAlpha(0.0f);
        }
        this.mPendingChanges.add((Object)new SynAdapterAnim.SynAdapterAnim$ChangeInfo(recyclerView$ViewHolder, recyclerView$ViewHolder2, n, n2, n3, n4));
        return true;
    }
    
    void animateChangeImpl(final SynAdapterAnim.SynAdapterAnim$ChangeInfo synAdapterAnim$ChangeInfo) {
        final RecyclerView$ViewHolder oldHolder = synAdapterAnim$ChangeInfo.oldHolder;
        View itemView = null;
        View itemView2;
        if (oldHolder == null) {
            itemView2 = null;
        }
        else {
            itemView2 = oldHolder.itemView;
        }
        final RecyclerView$ViewHolder newHolder = synAdapterAnim$ChangeInfo.newHolder;
        if (newHolder != null) {
            itemView = newHolder.itemView;
        }
        if (itemView2 != null) {
            final ViewPropertyAnimator setDuration = itemView2.animate().setDuration(this.getChangeDuration());
            this.mChangeAnimations.add((Object)synAdapterAnim$ChangeInfo.oldHolder);
            setDuration.translationX((float)(synAdapterAnim$ChangeInfo.toX - synAdapterAnim$ChangeInfo.fromX));
            setDuration.translationY((float)(synAdapterAnim$ChangeInfo.toY - synAdapterAnim$ChangeInfo.fromY));
            setDuration.alpha(0.0f).setListener((Animator$AnimatorListener)new SynAdapterAnim$7(this, synAdapterAnim$ChangeInfo, setDuration, itemView2)).start();
        }
        if (itemView != null) {
            final ViewPropertyAnimator animate = itemView.animate();
            this.mChangeAnimations.add((Object)synAdapterAnim$ChangeInfo.newHolder);
            animate.translationX(0.0f).translationY(0.0f).setDuration(this.getChangeDuration()).alpha(1.0f).setListener((Animator$AnimatorListener)new SynAdapterAnim$8(this, synAdapterAnim$ChangeInfo, animate, itemView)).start();
        }
    }
    
    public boolean animateMove(final RecyclerView$ViewHolder recyclerView$ViewHolder, int n, int n2, final int n3, final int n4) {
        final View itemView = recyclerView$ViewHolder.itemView;
        n += (int)recyclerView$ViewHolder.itemView.getTranslationX();
        final int n5 = n2 + (int)recyclerView$ViewHolder.itemView.getTranslationY();
        this.resetAnimation(recyclerView$ViewHolder);
        final int n6 = n3 - n;
        n2 = n4 - n5;
        if (n6 == 0 && n2 == 0) {
            this.dispatchMoveFinished(recyclerView$ViewHolder);
            return false;
        }
        if (n6 != 0) {
            itemView.setTranslationX((float)(-n6));
        }
        if (n2 != 0) {
            itemView.setTranslationY((float)(-n2));
        }
        this.mPendingMoves.add((Object)new SynAdapterAnim.SynAdapterAnim$MoveInfo(recyclerView$ViewHolder, n, n5, n3, n4));
        return true;
    }
    
    void animateMoveImpl(final RecyclerView$ViewHolder recyclerView$ViewHolder, int n, int n2, final int n3, final int n4) {
        final View itemView = recyclerView$ViewHolder.itemView;
        n = n3 - n;
        n2 = n4 - n2;
        if (n != 0) {
            itemView.animate().translationX(0.0f);
        }
        if (n2 != 0) {
            itemView.animate().translationY(0.0f);
        }
        final ViewPropertyAnimator animate = itemView.animate();
        this.mMoveAnimations.add((Object)recyclerView$ViewHolder);
        animate.setDuration(this.getMoveDuration()).setListener((Animator$AnimatorListener)new SynAdapterAnim$6(this, recyclerView$ViewHolder, n, itemView, n2, animate)).start();
    }
    
    public boolean animateRemove(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        this.resetAnimation(recyclerView$ViewHolder);
        this.mPendingRemovals.add((Object)recyclerView$ViewHolder);
        return true;
    }
    
    public boolean canReuseUpdatedViewHolder(final RecyclerView$ViewHolder recyclerView$ViewHolder, final List<Object> list) {
        return !list.isEmpty() || super.canReuseUpdatedViewHolder(recyclerView$ViewHolder, (List)list);
    }
    
    void cancelAll(final List<RecyclerView$ViewHolder> list) {
        for (int i = list.size() - 1; i >= 0; --i) {
            ((RecyclerView$ViewHolder)list.get(i)).itemView.animate().cancel();
        }
    }
    
    void dispatchFinishedWhenDone() {
        if (!this.isRunning()) {
            this.dispatchAnimationsFinished();
        }
    }
    
    public void endAnimation(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        final View itemView = recyclerView$ViewHolder.itemView;
        itemView.animate().cancel();
        for (int i = this.mPendingMoves.size() - 1; i >= 0; --i) {
            if (((SynAdapterAnim.SynAdapterAnim$MoveInfo)this.mPendingMoves.get(i)).holder == recyclerView$ViewHolder) {
                itemView.setTranslationY(0.0f);
                itemView.setTranslationX(0.0f);
                this.dispatchMoveFinished(recyclerView$ViewHolder);
                this.mPendingMoves.remove(i);
            }
        }
        this.endChangeAnimation((List<SynAdapterAnim.SynAdapterAnim$ChangeInfo>)this.mPendingChanges, recyclerView$ViewHolder);
        if (this.mPendingRemovals.remove((Object)recyclerView$ViewHolder)) {
            itemView.setAlpha(1.0f);
            this.dispatchRemoveFinished(recyclerView$ViewHolder);
        }
        if (this.mPendingAdditions.remove((Object)recyclerView$ViewHolder)) {
            itemView.setAlpha(1.0f);
            this.dispatchAddFinished(recyclerView$ViewHolder);
        }
        for (int j = this.mChangesList.size() - 1; j >= 0; --j) {
            final ArrayList list = (ArrayList)this.mChangesList.get(j);
            this.endChangeAnimation((List<SynAdapterAnim.SynAdapterAnim$ChangeInfo>)list, recyclerView$ViewHolder);
            if (list.isEmpty()) {
                this.mChangesList.remove(j);
            }
        }
        for (int k = this.mMovesList.size() - 1; k >= 0; --k) {
            final ArrayList list2 = (ArrayList)this.mMovesList.get(k);
            int l = list2.size() - 1;
            while (l >= 0) {
                if (((SynAdapterAnim.SynAdapterAnim$MoveInfo)list2.get(l)).holder == recyclerView$ViewHolder) {
                    itemView.setTranslationY(0.0f);
                    itemView.setTranslationX(0.0f);
                    this.dispatchMoveFinished(recyclerView$ViewHolder);
                    list2.remove(l);
                    if (list2.isEmpty()) {
                        this.mMovesList.remove(k);
                        break;
                    }
                    break;
                }
                else {
                    --l;
                }
            }
        }
        for (int n = this.mAdditionsList.size() - 1; n >= 0; --n) {
            final ArrayList list3 = (ArrayList)this.mAdditionsList.get(n);
            if (list3.remove((Object)recyclerView$ViewHolder)) {
                itemView.setAlpha(1.0f);
                this.dispatchAddFinished(recyclerView$ViewHolder);
                if (list3.isEmpty()) {
                    this.mAdditionsList.remove(n);
                }
            }
        }
        this.mRemoveAnimations.remove((Object)recyclerView$ViewHolder);
        this.mAddAnimations.remove((Object)recyclerView$ViewHolder);
        this.mChangeAnimations.remove((Object)recyclerView$ViewHolder);
        this.mMoveAnimations.remove((Object)recyclerView$ViewHolder);
        this.dispatchFinishedWhenDone();
    }
    
    public void endAnimations() {
        for (int i = this.mPendingMoves.size() - 1; i >= 0; --i) {
            final SynAdapterAnim.SynAdapterAnim$MoveInfo synAdapterAnim$MoveInfo = (SynAdapterAnim.SynAdapterAnim$MoveInfo)this.mPendingMoves.get(i);
            final View itemView = synAdapterAnim$MoveInfo.holder.itemView;
            itemView.setTranslationY(0.0f);
            itemView.setTranslationX(0.0f);
            this.dispatchMoveFinished(synAdapterAnim$MoveInfo.holder);
            this.mPendingMoves.remove(i);
        }
        for (int j = this.mPendingRemovals.size() - 1; j >= 0; --j) {
            this.dispatchRemoveFinished((RecyclerView$ViewHolder)this.mPendingRemovals.get(j));
            this.mPendingRemovals.remove(j);
        }
        for (int k = this.mPendingAdditions.size() - 1; k >= 0; --k) {
            final RecyclerView$ViewHolder recyclerView$ViewHolder = (RecyclerView$ViewHolder)this.mPendingAdditions.get(k);
            recyclerView$ViewHolder.itemView.setAlpha(1.0f);
            this.dispatchAddFinished(recyclerView$ViewHolder);
            this.mPendingAdditions.remove(k);
        }
        for (int l = this.mPendingChanges.size() - 1; l >= 0; --l) {
            this.endChangeAnimationIfNecessary((SynAdapterAnim.SynAdapterAnim$ChangeInfo)this.mPendingChanges.get(l));
        }
        this.mPendingChanges.clear();
        if (!this.isRunning()) {
            return;
        }
        for (int n = this.mMovesList.size() - 1; n >= 0; --n) {
            final ArrayList list = (ArrayList)this.mMovesList.get(n);
            for (int n2 = list.size() - 1; n2 >= 0; --n2) {
                final SynAdapterAnim.SynAdapterAnim$MoveInfo synAdapterAnim$MoveInfo2 = (SynAdapterAnim.SynAdapterAnim$MoveInfo)list.get(n2);
                final View itemView2 = synAdapterAnim$MoveInfo2.holder.itemView;
                itemView2.setTranslationY(0.0f);
                itemView2.setTranslationX(0.0f);
                this.dispatchMoveFinished(synAdapterAnim$MoveInfo2.holder);
                list.remove(n2);
                if (list.isEmpty()) {
                    this.mMovesList.remove((Object)list);
                }
            }
        }
        for (int n3 = this.mAdditionsList.size() - 1; n3 >= 0; --n3) {
            final ArrayList list2 = (ArrayList)this.mAdditionsList.get(n3);
            for (int n4 = list2.size() - 1; n4 >= 0; --n4) {
                final RecyclerView$ViewHolder recyclerView$ViewHolder2 = (RecyclerView$ViewHolder)list2.get(n4);
                recyclerView$ViewHolder2.itemView.setAlpha(1.0f);
                this.dispatchAddFinished(recyclerView$ViewHolder2);
                list2.remove(n4);
                if (list2.isEmpty()) {
                    this.mAdditionsList.remove((Object)list2);
                }
            }
        }
        for (int n5 = this.mChangesList.size() - 1; n5 >= 0; --n5) {
            final ArrayList list3 = (ArrayList)this.mChangesList.get(n5);
            for (int n6 = list3.size() - 1; n6 >= 0; --n6) {
                this.endChangeAnimationIfNecessary((SynAdapterAnim.SynAdapterAnim$ChangeInfo)list3.get(n6));
                if (list3.isEmpty()) {
                    this.mChangesList.remove((Object)list3);
                }
            }
        }
        this.cancelAll((List<RecyclerView$ViewHolder>)this.mRemoveAnimations);
        this.cancelAll((List<RecyclerView$ViewHolder>)this.mMoveAnimations);
        this.cancelAll((List<RecyclerView$ViewHolder>)this.mAddAnimations);
        this.cancelAll((List<RecyclerView$ViewHolder>)this.mChangeAnimations);
        this.dispatchAnimationsFinished();
    }
    
    public boolean isRunning() {
        return !this.mPendingAdditions.isEmpty() || !this.mPendingChanges.isEmpty() || !this.mPendingMoves.isEmpty() || !this.mPendingRemovals.isEmpty() || !this.mMoveAnimations.isEmpty() || !this.mRemoveAnimations.isEmpty() || !this.mAddAnimations.isEmpty() || !this.mChangeAnimations.isEmpty() || !this.mMovesList.isEmpty() || !this.mAdditionsList.isEmpty() || !this.mChangesList.isEmpty();
    }
    
    public void runPendingAnimations() {
        final boolean b = this.mPendingRemovals.isEmpty() ^ true;
        final boolean b2 = this.mPendingMoves.isEmpty() ^ true;
        final boolean b3 = this.mPendingChanges.isEmpty() ^ true;
        final boolean b4 = this.mPendingAdditions.isEmpty() ^ true;
        if (!b && !b2 && !b4 && !b3) {
            return;
        }
        final Iterator iterator = this.mPendingRemovals.iterator();
        while (iterator.hasNext()) {
            this.animateRemoveImpl((RecyclerView$ViewHolder)iterator.next());
        }
        this.mPendingRemovals.clear();
        if (b2) {
            final ArrayList list = new ArrayList();
            list.addAll((Collection)this.mPendingMoves);
            this.mMovesList.add((Object)list);
            this.mPendingMoves.clear();
            final SynAdapterAnim$1 synAdapterAnim$1 = new SynAdapterAnim$1(this, list);
            if (b) {
                ViewCompat.postOnAnimationDelayed(((SynAdapterAnim.SynAdapterAnim$MoveInfo)list.get(0)).holder.itemView, (Runnable)synAdapterAnim$1, this.getRemoveDuration());
            }
            else {
                ((Runnable)synAdapterAnim$1).run();
            }
        }
        if (b3) {
            final ArrayList list2 = new ArrayList();
            list2.addAll((Collection)this.mPendingChanges);
            this.mChangesList.add((Object)list2);
            this.mPendingChanges.clear();
            final SynAdapterAnim$2 synAdapterAnim$2 = new SynAdapterAnim$2(this, list2);
            if (b) {
                ViewCompat.postOnAnimationDelayed(((SynAdapterAnim.SynAdapterAnim$ChangeInfo)list2.get(0)).oldHolder.itemView, (Runnable)synAdapterAnim$2, this.getRemoveDuration());
            }
            else {
                ((Runnable)synAdapterAnim$2).run();
            }
        }
        if (b4) {
            final ArrayList list3 = new ArrayList();
            list3.addAll((Collection)this.mPendingAdditions);
            this.mAdditionsList.add((Object)list3);
            this.mPendingAdditions.clear();
            final SynAdapterAnim$3 synAdapterAnim$3 = new SynAdapterAnim$3(this, list3);
            if (!b && !b2 && !b3) {
                ((Runnable)synAdapterAnim$3).run();
            }
            else {
                long changeDuration = 0L;
                long removeDuration;
                if (b) {
                    removeDuration = this.getRemoveDuration();
                }
                else {
                    removeDuration = 0L;
                }
                long moveDuration;
                if (b2) {
                    moveDuration = this.getMoveDuration();
                }
                else {
                    moveDuration = 0L;
                }
                if (b3) {
                    changeDuration = this.getChangeDuration();
                }
                ViewCompat.postOnAnimationDelayed(((RecyclerView$ViewHolder)list3.get(0)).itemView, (Runnable)synAdapterAnim$3, removeDuration + Math.max(moveDuration, changeDuration));
            }
        }
    }
}
