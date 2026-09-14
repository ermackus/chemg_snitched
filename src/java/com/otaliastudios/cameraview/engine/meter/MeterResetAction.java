package com.otaliastudios.cameraview.engine.meter;

import com.otaliastudios.cameraview.engine.action.Actions;
import com.otaliastudios.cameraview.engine.action.BaseAction;
import com.otaliastudios.cameraview.engine.action.ActionWrapper;

public class MeterResetAction extends ActionWrapper
{
    private final BaseAction action;
    
    public MeterResetAction() {
        this.action = Actions.together(new BaseAction[] { new ExposureReset(), new FocusReset(), new WhiteBalanceReset() });
    }
    
    @Override
    public BaseAction getAction() {
        return this.action;
    }
}
