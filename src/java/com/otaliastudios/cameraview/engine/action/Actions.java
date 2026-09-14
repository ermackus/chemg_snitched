package com.otaliastudios.cameraview.engine.action;

import java.util.Arrays;

public class Actions
{
    public static BaseAction sequence(final BaseAction... array) {
        return (BaseAction)new SequenceAction(Arrays.asList((Object[])array));
    }
    
    public static BaseAction timeout(final long n, final BaseAction baseAction) {
        return (BaseAction)new TimeoutAction(n, baseAction);
    }
    
    public static BaseAction together(final BaseAction... array) {
        return (BaseAction)new TogetherAction(Arrays.asList((Object[])array));
    }
}
