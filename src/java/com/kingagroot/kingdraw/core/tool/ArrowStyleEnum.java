package com.kingagroot.kingdraw.core.tool;

@Deprecated
public enum ArrowStyleEnum
{
    private static final ArrowStyleEnum[] $VALUES;
    
    G_ACCW_ARROW_120("34"), 
    G_ACCW_ARROW_180("35"), 
    G_ACCW_ARROW_270("36"), 
    G_ACCW_ARROW_90("33"), 
    G_ACCW_SINGLE_ARROW_120("42"), 
    G_ACCW_SINGLE_ARROW_180("43"), 
    G_ACCW_SINGLE_ARROW_270("44"), 
    G_ACCW_SINGLE_ARROW_90("41"), 
    G_ACW_ARROW_120("30"), 
    G_ACW_ARROW_180("31"), 
    G_ACW_ARROW_270("32"), 
    G_ACW_ARROW_90("29"), 
    G_ACW_SINGLE_ARROW_120("38"), 
    G_ACW_SINGLE_ARROW_180("39"), 
    G_ACW_SINGLE_ARROW_270("40"), 
    G_ACW_SINGLE_ARROW_90("37"), 
    G_BOLD_ARROW_LARGE("6"), 
    G_BOLD_ARROW_MEDIUM("5"), 
    G_BOLD_ARROW_SMALL("4"), 
    G_DASHED_ARROW_LARGE("9"), 
    G_DASHED_ARROW_MEDIUM("8"), 
    G_DASHED_ARROW_SMAL("7"), 
    G_EQUILIBRIUM_ARROW_LARGE("51"), 
    G_EQUILIBRIUM_ARROW_MEDIUM("50"), 
    G_EQUILIBRIUM_ARROW_SMALL("49"), 
    G_HOLLOW_ARROW_LARGE("26"), 
    G_HOLLOW_ARROW_MEDIUM("25"), 
    G_NOGO_CROSS_ARROW_LARGE("21"), 
    G_NOGO_CROSS_ARROW_MEDIUM("20"), 
    G_NOGO_CROSS_ARROW_SMALL("19"), 
    G_NOGO_HASH_ARROW_LARGE("24"), 
    G_NOGO_HASH_ARROW_MEDIUM("23"), 
    G_NOGO_HASH_ARROW_SMALL("22"), 
    G_ONE_SIDE_ARROW_LEFT_LARGE("12"), 
    G_ONE_SIDE_ARROW_LEFT_MEDIUM("11"), 
    G_ONE_SIDE_ARROW_LEFT_SMALL("10"), 
    G_ONE_SIDE_ARROW_RIGHT_LARGE("15"), 
    G_ONE_SIDE_ARROW_RIGHT_MEDIUM("14"), 
    G_ONE_SIDE_ARROW_RIGHT_SMALL("13"), 
    G_RESONANCE_ARROW_LARGE("18"), 
    G_RESONANCE_ARROW_MEDIUM("17"), 
    G_RESONANCE_ARROW_SMALL("16"), 
    G_RETROSYNTHETIC_ARROW_LARGE("28"), 
    G_RETROSYNTHETIC_ARROW_SMALL("27"), 
    G_SOLID_ARROW_LARGE("3"), 
    G_SOLID_ARROW_MEDIUM("2"), 
    G_SOLID_ARROW_SMALL("1"), 
    G_TWO_HEADED_ARROW_120("46"), 
    G_TWO_HEADED_ARROW_180("47"), 
    G_TWO_HEADED_ARROW_270("48"), 
    G_TWO_HEADED_ARROW_90("45"), 
    G_UNBALANCED_EQUILIBRIUM_ARROW_LARGE("54"), 
    G_UNBALANCED_EQUILIBRIUM_ARROW_MEDIUM("53"), 
    G_UNBALANCED_EQUILIBRIUM_ARROW_SMALL("52");
    
    public String arrowType;
    
    private ArrowStyleEnum(final String arrowType) {
        this.arrowType = arrowType;
    }
    
    public static ArrowStyleEnum valueOfTypee(final String s) {
        for (final ArrowStyleEnum arrowStyleEnum : values()) {
            if (arrowStyleEnum.arrowType.equals((Object)s)) {
                return arrowStyleEnum;
            }
        }
        return ArrowStyleEnum.G_SOLID_ARROW_SMALL;
    }
}
