package com.kingagroot.kingdraw.core.tool;

public enum ToolNameEnum
{
    private static final ToolNameEnum[] $VALUES;
    
    GARROW_TOOL("GArrowTool"), 
    GATOM_TOOL("GAtomTool"), 
    GBENZENE_TOOL("GBenzeneTool"), 
    GBOND_ANY_TOOL("GAnyBondTool"), 
    GBOND_BOLDDOUBLE_TOOL("GBoldDoubleBondTool"), 
    GBOND_BOLD_TOOL("GBoldBondTool"), 
    GBOND_DASHEDDOUBLE_TOOL("GDashedDoubleBondTool"), 
    GBOND_DASHE_TOOL("GDashedBondTool"), 
    GBOND_DATIVE_TOOL("GDativeBondTool"), 
    GBOND_DOUBLEAROMATIC_TOOL("GDoubleAromaticBondTool"), 
    GBOND_DOUBLEEITHER_TOOL("GDoubleEitherBondTool"), 
    GBOND_DOUBLE_TOOL("GDoubleBondTool"), 
    GBOND_HASHWEDGE_TOOL("GHashWedgeBondTool"), 
    GBOND_HASH_TOOL("GHashBondTool"), 
    GBOND_HOLLOWWEDGE_TOOL("GHollowWedgeBondTool"), 
    GBOND_HYDROGEN_TOOL("GHydrogenBondTool"), 
    GBOND_ION_TOOL("GIonBondTool"), 
    GBOND_SINGLEAROMATIC_TOOL("GSingleAromaticBondTool"), 
    GBOND_SINGLEDOUBLE_TOOL("GSingleDoubleBondTool"), 
    GBOND_SINGLE_TOOL("GSingleBondTool"), 
    GBOND_TRIPLE_TOOL("GTripleBondTool"), 
    GBOND_WAVY_TOOL("GWavyBondTool"), 
    GBOND_WEDGE_TOOL("GWedgeBondTool"), 
    GCARBONCHAIN_TOOL("GCarbonChainTool"), 
    GCHARGENEGATIVE_TOOL("GChargeNegativeTool"), 
    GCHARGEPOSITIVE_TOOL("GChargePositiveTool"), 
    GCHARGE_CIRCLE_NEGATIVE_TOOL("GChgCircleNegativeTool"), 
    GCHARGE_CIRCLE_POSITIVE_TOOL("GChgCirclePositiveTool"), 
    GCSGROUP_TOOL("GKDBracketTool"), 
    GCYCLOBUTANE_TOOL("GCyclobutaneTool"), 
    GCYCLOHEPTANE_TOOL("GCycloheptaneTool"), 
    GCYCLOHEXANE_TOOL("GCyclohexaneTool"), 
    GCYCLOOCTANE_TOOL("GCyclooctaneTool"), 
    GCYCLOPENTADIENE_TOOL("GCyclopentadieneTool"), 
    GCYCLOPENTANE_TOOL("GCyclopentaneTool"), 
    GCYCLOPROPANE_TOOL("GCyclopropaneTool"), 
    GDUPLICATETOOL("GDuplicateTool"), 
    GELECTRONFF_TOOL("GElectronFFTool"), 
    GELECTRONFP_TOOL("GElectronFPTool"), 
    GELECTRONPF_TOOL("GElectronPFTool"), 
    GFLOW_TOOL("GFlowTool"), 
    GGESTURE_TOOL("GGestureTool"), 
    GIMAGE_TOOL("GImageTool"), 
    GKDCircleTool("GKDCircleTool"), 
    GKDLineTool("GKDLineTool"), 
    GKDRectTool("GKDRectTool"), 
    GLASSO_SELECT_TOOL("GLassoSelectTool"), 
    GLEFTCHAIR_TOOL("GLeftChairTool"), 
    GLONEPAIR_TOOL("GLonePairTool"), 
    GMULTIBOND_TOOL("GMultiBondTool"), 
    GORBITAL_TOOL("GKDOrbitalTool"), 
    GRADICALANION_TOOL("GRadicalAnionTool"), 
    GRADICALCATION_TOOL("GRadicalCationTool"), 
    GRADICAL_TOOL("GRadicalTool"), 
    GRIGHTCHAIR_TOOL("GRightChairTool"), 
    GR_TOOL("GRTool"), 
    GSUP_TOOL("GSUPTool"), 
    GTEXT_TOOL("GTextTool"), 
    G_DRAG_TOOL("GDragTool"), 
    G_ERASER_TOOL("GEraserTool"), 
    G_RECT_SELECT_TOOL("GRecSelectTool");
    
    public String name;
    
    private ToolNameEnum(final String name) {
        this.name = name;
    }
    
    public static ToolNameEnum getToolEnum(final String s) {
        for (final ToolNameEnum toolNameEnum : values()) {
            if (toolNameEnum.name.equals((Object)s)) {
                return toolNameEnum;
            }
        }
        return null;
    }
}
