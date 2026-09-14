package com.luck.picture.lib.widget;

import java.util.Set;
import java.util.Collection;
import java.util.HashSet;

public class SlideSelectionHandler implements SlideSelectTouchListener$OnAdvancedSlideSelectListener
{
    private HashSet<Integer> mOriginalSelection;
    private final SlideSelectionHandler.SlideSelectionHandler$ISelectionHandler mSelectionHandler;
    private SlideSelectionHandler.SlideSelectionHandler$ISelectionStartFinishedListener mStartFinishedListener;
    
    public SlideSelectionHandler(final SlideSelectionHandler.SlideSelectionHandler$ISelectionHandler mSelectionHandler) {
        this.mSelectionHandler = mSelectionHandler;
        this.mStartFinishedListener = null;
    }
    
    private void checkedChangeSelection(final int n, final int n2, final boolean b) {
        this.mSelectionHandler.changeSelection(n, n2, b, false);
    }
    
    public void onSelectChange(int i, final int n, final boolean b) {
        while (i <= n) {
            this.checkedChangeSelection(i, i, b != this.mOriginalSelection.contains((Object)i));
            ++i;
        }
    }
    
    public void onSelectionFinished(final int n) {
        this.mOriginalSelection = null;
        final SlideSelectionHandler.SlideSelectionHandler$ISelectionStartFinishedListener mStartFinishedListener = this.mStartFinishedListener;
        if (mStartFinishedListener != null) {
            mStartFinishedListener.onSelectionFinished(n);
        }
    }
    
    public void onSelectionStarted(final int n) {
        this.mOriginalSelection = (HashSet<Integer>)new HashSet();
        final Set selection = this.mSelectionHandler.getSelection();
        if (selection != null) {
            this.mOriginalSelection.addAll((Collection)selection);
        }
        final boolean contains = this.mOriginalSelection.contains((Object)n);
        this.mSelectionHandler.changeSelection(n, n, this.mOriginalSelection.contains((Object)n) ^ true, true);
        final SlideSelectionHandler.SlideSelectionHandler$ISelectionStartFinishedListener mStartFinishedListener = this.mStartFinishedListener;
        if (mStartFinishedListener != null) {
            mStartFinishedListener.onSelectionStarted(n, contains);
        }
    }
    
    public SlideSelectionHandler withStartFinishedListener(final SlideSelectionHandler.SlideSelectionHandler$ISelectionStartFinishedListener mStartFinishedListener) {
        this.mStartFinishedListener = mStartFinishedListener;
        return this;
    }
}
