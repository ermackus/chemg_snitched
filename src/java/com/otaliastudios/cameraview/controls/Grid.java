package com.otaliastudios.cameraview.controls;

public enum Grid implements Control
{
    private static final Grid[] $VALUES;
    static final Grid DEFAULT;
    
    DRAW_3X3(1), 
    DRAW_4X4(2), 
    DRAW_PHI(3), 
    OFF(0);
    
    private int value;
    
    static {
        final Grid off = Grid.OFF;
        final Grid grid;
        $VALUES = new Grid[] { off, Grid.DRAW_3X3, Grid.DRAW_4X4, grid };
        DEFAULT = off;
    }
    
    private Grid(final int value) {
        this.value = value;
    }
    
    static Grid fromValue(final int n) {
        for (final Grid grid : values()) {
            if (grid.value() == n) {
                return grid;
            }
        }
        return Grid.DEFAULT;
    }
    
    int value() {
        return this.value;
    }
}
