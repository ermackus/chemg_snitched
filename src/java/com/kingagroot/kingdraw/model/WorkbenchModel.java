package com.kingagroot.kingdraw.model;

public class WorkbenchModel
{
    private int background;
    private String name;
    private int pic;
    
    public int getBackground() {
        return this.background;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getPic() {
        return this.pic;
    }
    
    public void setBackground(final int background) {
        this.background = background;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public void setPic(final int pic) {
        this.pic = pic;
    }
}
