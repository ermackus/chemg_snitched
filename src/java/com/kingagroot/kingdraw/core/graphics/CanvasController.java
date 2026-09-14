package com.kingagroot.kingdraw.core.graphics;

import com.kingagroot.kingdraw.core.Html.BaseText;
import com.kingagroot.kingdraw.core.Html.cache.TextGroupCache;
import com.kingagroot.kingdraw.core.Html.TextGroup;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;

public final class CanvasController
{
    private Map<String, BaseCanvasHolder> canvasList;
    
    public CanvasController() {
        this.canvasList = (Map<String, BaseCanvasHolder>)new HashMap();
    }
    
    public void addCanvas(final BaseCanvasHolder baseCanvasHolder) {
        if (!this.canvasList.containsKey((Object)baseCanvasHolder.getTag())) {
            this.canvasList.put((Object)baseCanvasHolder.getTag(), (Object)baseCanvasHolder);
        }
    }
    
    public void drawEnd() {
        final Iterator iterator = this.canvasList.keySet().iterator();
        while (iterator.hasNext()) {
            final BaseCanvasHolder baseCanvasHolder = (BaseCanvasHolder)this.canvasList.get((Object)iterator.next());
            if (baseCanvasHolder != null) {
                baseCanvasHolder.drawEnd();
            }
        }
    }
    
    public void drawStart() {
        final Iterator iterator = this.canvasList.keySet().iterator();
        while (iterator.hasNext()) {
            final BaseCanvasHolder baseCanvasHolder = (BaseCanvasHolder)this.canvasList.get((Object)iterator.next());
            if (baseCanvasHolder != null) {
                baseCanvasHolder.drawStart();
            }
        }
    }
    
    public BaseCanvasHolder getCanvasByTag(final String s) {
        return (BaseCanvasHolder)this.canvasList.get((Object)s);
    }
    
    public void matrixChanged(final KDMatrix kdMatrix) {
        final Iterator iterator = this.canvasList.keySet().iterator();
        while (iterator.hasNext()) {
            final BaseCanvasHolder baseCanvasHolder = (BaseCanvasHolder)this.canvasList.get((Object)iterator.next());
            if (baseCanvasHolder != null) {
                baseCanvasHolder.matrixChanged(kdMatrix);
            }
        }
    }
    
    public float[] measureTextBounds(final String s, final boolean b) {
        if (s == null) {
            return new float[] { 0.0f, 0.0f };
        }
        TextGroup textGroup;
        if (b) {
            if ((textGroup = (TextGroup)TextGroupCache.getNodeTextCacheInstance().getTextLine(s)) == null) {
                textGroup = new TextGroup(s);
                TextGroupCache.getNodeTextCacheInstance().addCache(s, (BaseText)textGroup);
            }
            textGroup.measure(b);
        }
        else {
            if ((textGroup = (TextGroup)TextGroupCache.getTextCacheInstance().getTextLine(s)) == null) {
                textGroup = new TextGroup(s);
                TextGroupCache.getTextCacheInstance().addCache(s, (BaseText)textGroup);
            }
            textGroup.measure();
        }
        return new float[] { textGroup.getWidth(), textGroup.getHeigth() };
    }
    
    public void onDestroy() {
        final Iterator iterator = this.canvasList.keySet().iterator();
        while (iterator.hasNext()) {
            final BaseCanvasHolder baseCanvasHolder = (BaseCanvasHolder)this.canvasList.get((Object)iterator.next());
            if (baseCanvasHolder != null) {
                baseCanvasHolder.onDestroy();
            }
        }
        this.canvasList.clear();
    }
    
    public void removeCanvas(final BaseCanvasHolder baseCanvasHolder) {
        if (this.canvasList.containsKey((Object)baseCanvasHolder.getTag())) {
            this.canvasList.remove((Object)baseCanvasHolder.getTag());
        }
    }
}
