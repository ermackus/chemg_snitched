package com.luck.picture.lib.style;

public class PictureSelectorStyle
{
    private AlbumWindowStyle albumWindowStyle;
    private BottomNavBarStyle bottomBarStyle;
    private SelectMainStyle selectMainStyle;
    private TitleBarStyle titleBarStyle;
    private PictureWindowAnimationStyle windowAnimationStyle;
    
    public AlbumWindowStyle getAlbumWindowStyle() {
        AlbumWindowStyle albumWindowStyle;
        if ((albumWindowStyle = this.albumWindowStyle) == null) {
            albumWindowStyle = new AlbumWindowStyle();
        }
        return albumWindowStyle;
    }
    
    public BottomNavBarStyle getBottomBarStyle() {
        BottomNavBarStyle bottomBarStyle;
        if ((bottomBarStyle = this.bottomBarStyle) == null) {
            bottomBarStyle = new BottomNavBarStyle();
        }
        return bottomBarStyle;
    }
    
    public SelectMainStyle getSelectMainStyle() {
        SelectMainStyle selectMainStyle;
        if ((selectMainStyle = this.selectMainStyle) == null) {
            selectMainStyle = new SelectMainStyle();
        }
        return selectMainStyle;
    }
    
    public TitleBarStyle getTitleBarStyle() {
        TitleBarStyle titleBarStyle;
        if ((titleBarStyle = this.titleBarStyle) == null) {
            titleBarStyle = new TitleBarStyle();
        }
        return titleBarStyle;
    }
    
    public PictureWindowAnimationStyle getWindowAnimationStyle() {
        if (this.windowAnimationStyle == null) {
            this.windowAnimationStyle = PictureWindowAnimationStyle.ofDefaultWindowAnimationStyle();
        }
        return this.windowAnimationStyle;
    }
    
    public void setAlbumWindowStyle(final AlbumWindowStyle albumWindowStyle) {
        this.albumWindowStyle = albumWindowStyle;
    }
    
    public void setBottomBarStyle(final BottomNavBarStyle bottomBarStyle) {
        this.bottomBarStyle = bottomBarStyle;
    }
    
    public void setSelectMainStyle(final SelectMainStyle selectMainStyle) {
        this.selectMainStyle = selectMainStyle;
    }
    
    public void setTitleBarStyle(final TitleBarStyle titleBarStyle) {
        this.titleBarStyle = titleBarStyle;
    }
    
    public void setWindowAnimationStyle(final PictureWindowAnimationStyle windowAnimationStyle) {
        this.windowAnimationStyle = windowAnimationStyle;
    }
}
