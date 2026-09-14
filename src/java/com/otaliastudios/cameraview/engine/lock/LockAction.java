package com.otaliastudios.cameraview.engine.lock;

import com.otaliastudios.cameraview.engine.action.Actions;
import com.otaliastudios.cameraview.engine.action.BaseAction;
import com.otaliastudios.cameraview.engine.action.ActionWrapper;

public class LockAction extends ActionWrapper
{
    private final BaseAction action;
    
    public LockAction() {
        this.action = Actions.together(new BaseAction[] { new ExposureLock(), new FocusLock(), new WhiteBalanceLock() });
    }
    
    @Override
    public BaseAction getAction() {
        return this.action;
    }
}
