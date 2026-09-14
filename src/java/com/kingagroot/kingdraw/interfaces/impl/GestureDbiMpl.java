package com.kingagroot.kingdraw.interfaces.impl;

import com.kingagroot.kingdraw.config.ShareData;
import java.util.List;
import org.xutils.ex.DbException;
import com.kingagroot.kingdraw.model.GestureGroupModel;
import com.kingagroot.kingdraw.interfaces.GestureDbi;

public class GestureDbiMpl extends BaseDBImpl implements GestureDbi
{
    public void deleteModel(final GestureGroupModel gestureGroupModel) {
        try {
            this.db.delete((Object)gestureGroupModel);
        }
        catch (final DbException ex) {
            ex.printStackTrace();
        }
    }
    
    public List<GestureGroupModel> getAllData() {
        try {
            return (List<GestureGroupModel>)this.db.findAll((Class)GestureGroupModel.class);
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public GestureGroupModel getBondData(final int n) {
        try {
            return (GestureGroupModel)this.db.selector((Class)GestureGroupModel.class).where("gestureCoreId", "=", (Object)n).and("isBind", "=", (Object)true).findFirst();
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public List<GestureGroupModel> getDataByKey(final boolean b) {
        try {
            return (List<GestureGroupModel>)this.db.selector((Class)GestureGroupModel.class).where("isBind", "=", (Object)b).findAll();
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public List<GestureGroupModel> getGestureNoBindData() {
        try {
            return (List<GestureGroupModel>)this.db.selector((Class)GestureGroupModel.class).where("isBind", "=", (Object)false).and("gestureId", "!=", (Object)0).findAll();
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public void save(final List<GestureGroupModel> list) {
        try {
            this.db.saveOrUpdate((Object)list);
            ShareData.setGestureSaveState(true);
        }
        catch (final DbException ex) {
            ex.printStackTrace();
        }
    }
    
    public boolean upData(final GestureGroupModel gestureGroupModel) {
        try {
            this.db.saveOrUpdate((Object)gestureGroupModel);
            return true;
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
