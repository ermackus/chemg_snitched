package com.kingagroot.kingdraw.interfaces.impl;

import java.util.List;
import com.kingagroot.kingdraw.base.MApplication;
import com.kingagroot.component.ui.view.OperationState;
import org.xutils.ex.DbException;
import com.kingagroot.kingdraw.ui.workstation.WorkStationModel;
import com.kingagroot.kingdraw.interfaces.WorkWindowDbi;

public class WorkWindowDbiMpl extends BaseDBImpl implements WorkWindowDbi
{
    private WorkStationModel getModelByKey(final int n) {
        try {
            return (WorkStationModel)this.db.selector((Class)WorkStationModel.class).where("AppID", "=", (Object)n).findFirst();
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public OperationState addWorkItem(final WorkStationModel workStationModel) {
        if (this.getModelByKey(workStationModel.getAppID()) == null) {
            try {
                this.db.save((Object)workStationModel);
                return new OperationState(true, MApplication.getInstance().getString(2131821454));
            }
            catch (final DbException ex) {
                ex.printStackTrace();
            }
        }
        return this.error();
    }
    
    public boolean cleanAllWorkItems() {
        try {
            this.db.delete((Class)WorkStationModel.class);
            return true;
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public boolean deleteWorkItem(final WorkStationModel workStationModel) {
        if (workStationModel != null) {
            try {
                this.db.delete((Object)workStationModel);
            }
            catch (final DbException ex) {
                ex.printStackTrace();
                return false;
            }
        }
        return true;
    }
    
    public List<WorkStationModel> getAllWorkItems() {
        try {
            return (List<WorkStationModel>)this.db.findAll((Class)WorkStationModel.class);
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
