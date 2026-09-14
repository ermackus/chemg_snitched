package com.kingagroot.kingdraw.model;

public class UnReadModel
{
    int ImageRec;
    int unReadCount;
    int unReadOptionCount;
    
    public int getImageRec() {
        return this.ImageRec;
    }
    
    public int getUnReadCount() {
        return this.unReadCount;
    }
    
    public int getUnReadOptionCount() {
        return this.unReadOptionCount;
    }
    
    public boolean isEnableAi() {
        final int imageRec = this.ImageRec;
        boolean b = true;
        if (imageRec != 1) {
            b = false;
        }
        return b;
    }
    
    public void setImageRec(final int imageRec) {
        this.ImageRec = imageRec;
    }
    
    public void setUnReadCount(final int unReadCount) {
        this.unReadCount = unReadCount;
    }
    
    public void setUnReadOptionCount(final int unReadOptionCount) {
        this.unReadOptionCount = unReadOptionCount;
    }
}
