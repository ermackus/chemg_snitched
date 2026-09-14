package com.kingagroot.kingdraw.dialog;

import java.util.Iterator;
import java.lang.reflect.Field;
import android.content.DialogInterface$OnDismissListener;
import android.os.Message;
import android.app.Dialog;
import android.content.DialogInterface;
import java.util.ArrayList;
import java.util.List;

public class DialogQueue implements DialogBaseQueue
{
    int DialogCount;
    List<DialogQueue.DialogQueue$GDialogManagerModel> dialogQueue;
    boolean isShowDialog;
    
    public DialogQueue() {
        this.DialogCount = 0;
        this.dialogQueue = (List<DialogQueue.DialogQueue$GDialogManagerModel>)new ArrayList();
    }
    
    private GDialogManagerListener addDismissListner(final Dialog dialog) {
        try {
            Class<? extends Dialog> class1 = dialog.getClass();
            Object superclass;
            while ((superclass = class1) != Dialog.class) {
                superclass = class1.getSuperclass();
                if ((class1 = (Class<? extends Dialog>)superclass) == null) {
                    break;
                }
            }
            if (superclass == null) {
                return null;
            }
            final Field declaredField = ((Class)superclass).getDeclaredField("mDismissMessage");
            declaredField.setAccessible(true);
            final Message message = (Message)declaredField.get((Object)dialog);
            GDialogManagerListener gDialogManagerListener;
            if (message == null) {
                final GDialogManagerListener onDismissListener = new GDialogManagerListener(this, null) {
                    final DialogQueue this$0;
                    
                    public void onDismiss(final DialogInterface dialogInterface) {
                        this.this$0.isShowDialog = false;
                        this.this$0.removeDialog(dialogInterface);
                        this.this$0.showNext();
                    }
                };
                dialog.setOnDismissListener((DialogInterface$OnDismissListener)onDismissListener);
                gDialogManagerListener = onDismissListener;
            }
            else {
                final GDialogManagerListener obj = new GDialogManagerListener(this, (DialogInterface$OnDismissListener)message.obj) {
                    final DialogQueue this$0;
                    
                    public void onDismiss(final DialogInterface dialogInterface) {
                        if (this.getOnDismissListener() != null) {
                            this.getOnDismissListener().onDismiss(dialogInterface);
                        }
                        this.this$0.isShowDialog = false;
                        this.this$0.removeDialog(dialogInterface);
                        this.this$0.showNext();
                    }
                };
                message.obj = obj;
                declaredField.set((Object)dialog, (Object)message);
                gDialogManagerListener = obj;
            }
            return gDialogManagerListener;
        }
        catch (final IllegalAccessException ex) {
            ex.printStackTrace();
        }
        catch (final NoSuchFieldException ex2) {
            ex2.printStackTrace();
        }
        return null;
    }
    
    private boolean removeDialog(final DialogInterface dialogInterface) {
        for (final DialogQueue.DialogQueue$GDialogManagerModel dialogQueue$GDialogManagerModel : this.dialogQueue) {
            if (dialogQueue$GDialogManagerModel.dialog == dialogInterface) {
                this.restLisenter(dialogQueue$GDialogManagerModel);
                final boolean remove = this.dialogQueue.remove((Object)dialogQueue$GDialogManagerModel);
                if (remove) {
                    --this.DialogCount;
                }
                return remove;
            }
        }
        return false;
    }
    
    private void restLisenter(final DialogQueue.DialogQueue$GDialogManagerModel dialogQueue$GDialogManagerModel) {
        try {
            final Dialog dialog = dialogQueue$GDialogManagerModel.dialog;
            Class<? extends Dialog> class1 = dialog.getClass();
            Object superclass;
            while ((superclass = class1) != Dialog.class) {
                superclass = class1.getSuperclass();
                if ((class1 = (Class<? extends Dialog>)superclass) == null) {
                    break;
                }
            }
            if (superclass == null) {
                return;
            }
            final Field declaredField = ((Class)superclass).getDeclaredField("mDismissMessage");
            declaredField.setAccessible(true);
            final Message message = (Message)declaredField.get((Object)dialog);
            Object o;
            if (dialogQueue$GDialogManagerModel.gDialogManagerListener.getOnDismissListener() == null) {
                o = null;
            }
            else {
                message.obj = dialogQueue$GDialogManagerModel.gDialogManagerListener.getOnDismissListener();
                o = message;
            }
            declaredField.set((Object)dialog, o);
        }
        catch (final IllegalAccessException ex) {
            ex.printStackTrace();
        }
        catch (final NoSuchFieldException ex2) {
            ex2.printStackTrace();
        }
    }
    
    public void addDailog(final Dialog dialog) {
        if (dialog != null) {
            final GDialogManagerListener addDismissListner = this.addDismissListner(dialog);
            if (addDismissListner != null) {
                this.dialogQueue.add((Object)new DialogQueue.DialogQueue$GDialogManagerModel(this, dialog, addDismissListner));
                ++this.DialogCount;
                if (!this.isShowDialog) {
                    this.showNext();
                }
            }
            else {
                dialog.show();
            }
        }
    }
    
    public boolean idleShowDialog(final Dialog dialog) {
        if (this.DialogCount <= 0) {
            final GDialogManagerListener addDismissListner = this.addDismissListner(dialog);
            if (addDismissListner != null) {
                this.dialogQueue.add(0, (Object)new DialogQueue.DialogQueue$GDialogManagerModel(this, dialog, addDismissListner));
                ++this.DialogCount;
                this.showNext();
            }
            else {
                dialog.show();
            }
            return true;
        }
        return false;
    }
    
    public void onDestory() {
        this.dialogQueue.clear();
        this.DialogCount = 0;
    }
    
    public Dialog popup() {
        if (!this.dialogQueue.isEmpty()) {
            final DialogQueue.DialogQueue$GDialogManagerModel dialogQueue$GDialogManagerModel = (DialogQueue.DialogQueue$GDialogManagerModel)this.dialogQueue.get(0);
            --this.DialogCount;
            return dialogQueue$GDialogManagerModel.dialog;
        }
        return null;
    }
    
    public void showNext() {
        if (!this.dialogQueue.isEmpty()) {
            ((DialogQueue.DialogQueue$GDialogManagerModel)this.dialogQueue.get(0)).dialog.show();
            this.isShowDialog = true;
        }
    }
}
