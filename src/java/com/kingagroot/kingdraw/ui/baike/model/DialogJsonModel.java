package com.kingagroot.kingdraw.ui.baike.model;

import java.util.List;

public class DialogJsonModel
{
    private List<Btns> btns;
    private String dialogMsg;
    private String dialogTitile;
    private int dialogType;
    
    public List<Btns> getBtns() {
        return this.btns;
    }
    
    public String getDialogMsg() {
        return this.dialogMsg;
    }
    
    public String getDialogTitile() {
        return this.dialogTitile;
    }
    
    public int getDialogType() {
        return this.dialogType;
    }
    
    public void setBtns(final List<Btns> btns) {
        this.btns = btns;
    }
    
    public void setDialogMsg(final String dialogMsg) {
        this.dialogMsg = dialogMsg;
    }
    
    public void setDialogTitile(final String dialogTitile) {
        this.dialogTitile = dialogTitile;
    }
    
    public void setDialogType(final int dialogType) {
        this.dialogType = dialogType;
    }
    
    public class Btns
    {
        private String btnName;
        private int btnType;
        private String getData;
        private int id;
        final DialogJsonModel this$0;
        
        public Btns(final DialogJsonModel this$0) {
            this.this$0 = this$0;
        }
        
        public String getBtnName() {
            return this.btnName;
        }
        
        public int getBtnType() {
            return this.btnType;
        }
        
        public String getGetData() {
            return this.getData;
        }
        
        public int getId() {
            return this.id;
        }
        
        public void setBtnName(final String btnName) {
            this.btnName = btnName;
        }
        
        public void setBtnType(final int btnType) {
            this.btnType = btnType;
        }
        
        public void setGetData(final String getData) {
            this.getData = getData;
        }
        
        public void setId(final int id) {
            this.id = id;
        }
    }
}
