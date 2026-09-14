package com.kingagroot.component.ui;

import com.kingagroot.kingdraw.core.tool.OrbitalTypeEnum;
import com.kingagroot.kingdraw.core.tool.BracketToolParmEnum;
import com.kingagroot.kingdraw.core.tool.RectTypeEnum;
import com.kingagroot.kingdraw.core.tool.CircleTypeEnum;
import com.kingagroot.kingdraw.core.tool.ArrowToolParmEnum;
import com.kingagroot.kingdraw.core.tool.AlignTypeEnum;
import com.kingagroot.kingdraw.core.tool.ToolNameEnum;

public enum ToolEnum
{
    private static final ToolEnum[] $VALUES;
    
    G3D_TOOL(R.id.id_palette_3d_show), 
    GALIGN_BOTTOM_TOOL(R.id.align_bottom, AlignTypeEnum.BOTTOM.alignType, R.string.align_bottom_edges), 
    GALIGN_HORIZONTAL_TOOL(R.id.align_horizontal, AlignTypeEnum.CENTRE_HORIZONTAL.alignType, R.string.align_top_bottom_centers), 
    GALIGN_LEFT_TOOL(R.id.align_left, AlignTypeEnum.LEFT.alignType, R.string.align_left_edges), 
    GALIGN_RIGHT_TOOL(R.id.align_right, AlignTypeEnum.RIGHT.alignType, R.string.align_right_edges), 
    GALIGN_TOP_TOOL(R.id.align_top, AlignTypeEnum.TOP.alignType, R.string.align_top_edges), 
    GALIGN_VERTICAL_TOOL(R.id.align_vertical, AlignTypeEnum.CENTER_VERTICAL.alignType, R.string.align_left_right_centers), 
    GATOM_TOOL(R.id.palette_atom, ToolNameEnum.GATOM_TOOL), 
    GBENZENE_TOOL(R.id.benzene, ToolNameEnum.GBENZENE_TOOL, R.string.benzene), 
    GBOND_ANY_TOOL(R.id.bond_any, ToolNameEnum.GBOND_ANY_TOOL, R.string.any_bond), 
    GBOND_BOLD_DOUBLE_TOOL(R.id.bond_bold_double, ToolNameEnum.GBOND_BOLDDOUBLE_TOOL, R.string.bold_double_bond), 
    GBOND_BOLD_TOOL(R.id.bond_bold, ToolNameEnum.GBOND_BOLD_TOOL, R.string.bold_bond), 
    GBOND_DASHEDDOUBLE_TOOL(R.id.bond_dashed_double_bond, ToolNameEnum.GBOND_DASHEDDOUBLE_TOOL, R.string.dashed_double_bond), 
    GBOND_DASHED_TOOL(R.id.bond_dashed, ToolNameEnum.GBOND_DASHE_TOOL, R.string.dashed_bond), 
    GBOND_DATIVE_TOOL(R.id.bond_dative, ToolNameEnum.GBOND_DATIVE_TOOL, R.string.dative_bond), 
    GBOND_DOUBLE_AROMATIC_TOOL(R.id.bond_double_aromatic, ToolNameEnum.GBOND_DOUBLEAROMATIC_TOOL, R.string.double_aromatic_bond), 
    GBOND_DOUBLE_EITHER_TOOL(R.id.bond_double_either, ToolNameEnum.GBOND_DOUBLEEITHER_TOOL, R.string.double_either_bond), 
    GBOND_DOUBLE_TOOL(R.id.bond_double, ToolNameEnum.GBOND_DOUBLE_TOOL, R.string.double_bond), 
    GBOND_HASHWEDGE_TOOL(R.id.hashed_wedged_bond, ToolNameEnum.GBOND_HASHWEDGE_TOOL, R.string.hashed_wedged_bond), 
    GBOND_HASH_TOOL(R.id.bond_hashed, ToolNameEnum.GBOND_HASH_TOOL, R.string.hashed_bond), 
    GBOND_HOLLOWWEDGE_TOOL(R.id.bond_hollow_wedge, ToolNameEnum.GBOND_HOLLOWWEDGE_TOOL, R.string.hollow_wedged_bond), 
    GBOND_HYDROGEN_TOOL(R.id.bond_hydrogen, ToolNameEnum.GBOND_HYDROGEN_TOOL, R.string.hydrogen_bond), 
    GBOND_ION_TOOL(R.id.bond_ion, ToolNameEnum.GBOND_ION_TOOL, R.string.ion_bond), 
    GBOND_SINGLE_AROMATIC_TOOL(R.id.bond_single_aromatic, ToolNameEnum.GBOND_SINGLEAROMATIC_TOOL, R.string.single_aromatic_bond), 
    GBOND_SINGLE_DOUBLE_TOOL(R.id.bond_single_double, ToolNameEnum.GBOND_SINGLEDOUBLE_TOOL, R.string.single_double_bond), 
    GBOND_SINGLE_TOOL(R.id.bond_single, ToolNameEnum.GBOND_SINGLE_TOOL, R.string.single_bond), 
    GBOND_TRIPLE_TOOL(R.id.bond_triple, ToolNameEnum.GBOND_TRIPLE_TOOL, R.string.triple_bond), 
    GBOND_WAVY_TOOL(R.id.bond_wavy, ToolNameEnum.GBOND_WAVY_TOOL, R.string.wavy_bond), 
    GBOND_WEDGE_TOOL(R.id.bond_wedge, ToolNameEnum.GBOND_WEDGE_TOOL, R.string.wedged_bond), 
    GBRACKET_BIG_TOOL(R.id.bracket_curlybraces, ToolNameEnum.GCSGROUP_TOOL, BracketToolParmEnum.G_BRACKET_CURLYBRACES.bracketTypeParm, R.string.braces), 
    GBRACKET_CURVE_TOOL(R.id.bracket_curve, ToolNameEnum.GCSGROUP_TOOL, BracketToolParmEnum.G_BRACKET_CURVE.bracketTypeParm, R.string.parentheses), 
    GBRACKET_RECT_TOOL(R.id.bracket_rect, ToolNameEnum.GCSGROUP_TOOL, BracketToolParmEnum.G_BRACKET_RECT.bracketTypeParm, R.string.brackets), 
    GBRACKET_TEXT_TOOL(R.id.bracket_text, ToolNameEnum.GCSGROUP_TOOL, BracketToolParmEnum.G_BRACKET_TEXT.bracketTypeParm, R.string.polymer_brackets), 
    GCARBONCHAIN_TOOL(R.id.carbonchain, ToolNameEnum.GCARBONCHAIN_TOOL, R.string.acyclic_chain), 
    GCHARGE_GLONEPAIR_TOOL(R.id.charge_lonepair, ToolNameEnum.GLONEPAIR_TOOL, R.string.charge_lonepair), 
    GCHARGE_GRADICALANION_TOOL(R.id.charge_radicalanion, ToolNameEnum.GRADICALANION_TOOL, R.string.charge_radicalanion), 
    GCHARGE_GRADICALCATION_TOOL(R.id.charge_radicalcation, ToolNameEnum.GRADICALCATION_TOOL, R.string.charge_radicalcation), 
    GCHARGE_GRADICAL_TOOL(R.id.charge_radical, ToolNameEnum.GRADICAL_TOOL, R.string.charge_radical), 
    GCHARGE_NEGATIVE_CIRCLE_TOOL(R.id.charge_negative_circle, ToolNameEnum.GCHARGE_CIRCLE_NEGATIVE_TOOL, R.string.charge_minus), 
    GCHARGE_NEGATIVE_TOOL(R.id.charge_negative, ToolNameEnum.GCHARGENEGATIVE_TOOL, R.string.charge_minus), 
    GCHARGE_POSITIVE_CIRCLE_TOOL(R.id.charge_positive_circle, ToolNameEnum.GCHARGE_CIRCLE_POSITIVE_TOOL, R.string.charge_plus), 
    GCHARGE_POSITIVE_TOOL(R.id.charge_positive, ToolNameEnum.GCHARGEPOSITIVE_TOOL, R.string.charge_plus), 
    GCLEAR_TOOL(R.id.palette_clear, R.string.clear), 
    GCYCLOBUTANE_TOOL(R.id.cyclobutane, ToolNameEnum.GCYCLOBUTANE_TOOL, R.string.cyclobutane_ring), 
    GCYCLOHEPTANE_TOOL(R.id.cycloheptane, ToolNameEnum.GCYCLOHEPTANE_TOOL, R.string.cycloheptane_ring), 
    GCYCLOHEXANE_TOOL(R.id.cyclohexane, ToolNameEnum.GCYCLOHEXANE_TOOL, R.string.cyclohexane_ring), 
    GCYCLOOCTANE_TOOL(R.id.cyclooctane, ToolNameEnum.GCYCLOOCTANE_TOOL, R.string.cyclooctane_ring), 
    GCYCLOPENTADIENE_TOOL(R.id.cyclopentadiene, ToolNameEnum.GCYCLOPENTADIENE_TOOL, R.string.cyclopentadiene), 
    GCYCLOPENTANE_TOOL(R.id.cyclopentane, ToolNameEnum.GCYCLOPENTANE_TOOL, R.string.cyclopentane_ring), 
    GCYCLOPROPANE_TOOL(R.id.cyclopropane, ToolNameEnum.GCYCLOPROPANE_TOOL, R.string.cyclopropane_ring), 
    GDRAG_TOOL(R.id.palette_move, ToolNameEnum.G_DRAG_TOOL, R.string.drag), 
    GDUPLICATETOOL(R.id.palette_copy, ToolNameEnum.GDUPLICATETOOL), 
    GELECTRONFF_TOOL(R.id.electron_ff, ToolNameEnum.GELECTRONFF_TOOL, R.string.electron), 
    GELECTRONFP_TOOL(R.id.electron_fp, ToolNameEnum.GELECTRONFP_TOOL, R.string.electron), 
    GELECTRONPF_TOOL(R.id.electron_pf, ToolNameEnum.GELECTRONPF_TOOL, R.string.electron), 
    GERASER_TOOL(R.id.palette_erase, ToolNameEnum.G_ERASER_TOOL, R.string.eraser), 
    GFLOW_3DCIRCLE_TOOL(R.id.flow_3dcircle, ToolNameEnum.GKDCircleTool, CircleTypeEnum.FLOW_CIRCLE_3D.circleType, R.string.circle_shaded), 
    GFLOW_3DELLIPSE_TOOL(R.id.flow_3dellipse, ToolNameEnum.GKDCircleTool, CircleTypeEnum.FLOW_ELLIPSE_3D.circleType, R.string.oval_shaded), 
    GFLOW_3DRECT_TOOL(R.id.flow_3drect, ToolNameEnum.GKDRectTool, RectTypeEnum.FLOW_RECT_3D.rectType, R.string.rectangle_shaded), 
    GFLOW_3DROUNDEDRECT_TOOL(R.id.flow_3droundedrect, ToolNameEnum.GKDRectTool, RectTypeEnum.FLOW_RECT_ROUNDED_3D.rectType, R.string.round_rectangle_shaded), 
    GFLOW_ARC120_TOOL(R.id.flow_arc120, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_LINE_ARC120.arrowTypeParm, R.string.one_third_solid), 
    GFLOW_ARC180_TOOL(R.id.flow_arc180, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_LINE_ARC180.arrowTypeParm, R.string.a_half_solid), 
    GFLOW_ARC270_TOOL(R.id.flow_arc270, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_LINE_ARC270.arrowTypeParm, R.string.three_fourths_solid), 
    GFLOW_ARC90_TOOL(R.id.flow_arc90, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_LINE_ARC90.arrowTypeParm, R.string.quarter_solid), 
    GFLOW_CIRCLE_TOOL(R.id.flow_circle, ToolNameEnum.GKDCircleTool, CircleTypeEnum.FLOW_CIRCLE.circleType, R.string.circle_hollow), 
    GFLOW_DASHARC120_TOOL(R.id.flow_dasharc120, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_LINE_DASHARC120.arrowTypeParm, R.string.one_third_dashed), 
    GFLOW_DASHARC180_TOOL(R.id.flow_dasharc180, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_LINE_DASHARC180.arrowTypeParm, R.string.a_half_dashed), 
    GFLOW_DASHARC270_TOOL(R.id.flow_dasharc270, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_LINE_DASHARC270.arrowTypeParm, R.string.three_fourths_dashed), 
    GFLOW_DASHARC90_TOOL(R.id.flow_dasharc90, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_LINE_DASHARC90.arrowTypeParm, R.string.quarter_dashed), 
    GFLOW_DASHCIRCLE_TOOL(R.id.flow_dashcircle, ToolNameEnum.GKDCircleTool, CircleTypeEnum.FLOW_CIRCLE_DASH.circleType, R.string.circle_dashed), 
    GFLOW_DASHELLIPSE_TOOL(R.id.flow_dashellipse, ToolNameEnum.GKDCircleTool, CircleTypeEnum.FLOW_ELLIPSE_DASH.circleType, R.string.oval_dashed), 
    GFLOW_DASHRECT_TOOL(R.id.flow_dashrect, ToolNameEnum.GKDRectTool, RectTypeEnum.FLOW_RECT_DASH.rectType, R.string.rectangle_dashed), 
    GFLOW_DASHROUNDEDRECT_TOOL(R.id.flow_dashroundedrect, ToolNameEnum.GKDRectTool, RectTypeEnum.FLOW_RECT_ROUNDED_DASH.rectType, R.string.round_rectangle_dashed), 
    GFLOW_ELLIPSE_TOOL(R.id.flow_ellipse, ToolNameEnum.GKDCircleTool, CircleTypeEnum.FLOW_ELLIPSE.circleType, R.string.oval_hollow), 
    GFLOW_LINE_ARC_TOOL(R.id.flow_line_arc, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_LINE_ARCLINE.arrowTypeParm, R.string.line_wavy), 
    GFLOW_LINE_BOLD_TOOL(R.id.flow_line_bold, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_LINE_BLODLINE.arrowTypeParm, R.string.line_bold), 
    GFLOW_LINE_DASH_TOOL(R.id.flow_line_dash, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_LINE_DASHLINE.arrowTypeParm, R.string.line_dashed), 
    GFLOW_LINE_TOOL(R.id.flow_line, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_LINE_SINGLE.arrowTypeParm, R.string.line), 
    GFLOW_RECTANGLE_TOOL(R.id.flow_rect, ToolNameEnum.GKDRectTool, RectTypeEnum.FLOW_RECT.rectType, R.string.rectangle_hollow), 
    GFLOW_ROUNDEDRECT_TOOL(R.id.flow_roundedrect, ToolNameEnum.GKDRectTool, RectTypeEnum.FLOW_RECT_ROUNDED.rectType, R.string.round_rectangle_hollow), 
    GFLOW_SHADOWCIRCLE_TOOL(R.id.flow_shadowcircle, ToolNameEnum.GKDCircleTool, CircleTypeEnum.FLOW_CIRCLE_SHADOW.circleType, R.string.circle_shadowed), 
    GFLOW_SHADOWELLIPSE_TOOL(R.id.flow_shadowellipse, ToolNameEnum.GKDCircleTool, CircleTypeEnum.FLOW_ELLIPSE_SHADOW.circleType, R.string.oval_shadowed), 
    GFLOW_SHADOWRECT_TOOL(R.id.flow_shadowrect, ToolNameEnum.GKDRectTool, RectTypeEnum.FLOW_RECT_SHADOW.rectType, R.string.rectangle_shadowed), 
    GFLOW_SHADOWROUNDEDRECT_TOOL(R.id.flow_shadowroundedrect, ToolNameEnum.GKDRectTool, RectTypeEnum.FLOW_RECT_ROUNDED_SHADOW.rectType, R.string.round_rectangle_shadowed), 
    GFLOW_SOLIDCIRCLE_TOOL(R.id.flow_solidcircle, ToolNameEnum.GKDCircleTool, CircleTypeEnum.FLOW_CIRCLE_SOLID.circleType, R.string.circle_solid), 
    GFLOW_SOLIDELLIPSE_TOOL(R.id.flow_solidellipse, ToolNameEnum.GKDCircleTool, CircleTypeEnum.FLOW_ELLIPSE_SOLID.circleType, R.string.oval_solid), 
    GFLOW_SOLIDRECT_TOOL(R.id.flow_solidrect, ToolNameEnum.GKDRectTool, RectTypeEnum.FLOW_RECT_SOLID.rectType, R.string.rectangle_solid), 
    GFLOW_SOLIDROUNDEDRECT_TOOL(R.id.flow_solidroundedrect, ToolNameEnum.GKDRectTool, RectTypeEnum.FLOW_RECT_ROUNDED_SOLID.rectType, R.string.round_rectangle_solid), 
    GFORMAT_96_SET(R.id.id_format_96_set), 
    GFORMAT_SET(R.id.id_format_set), 
    GGESTURE_TOOL(R.id.palette_gesture, ToolNameEnum.GGESTURE_TOOL, R.string.gesture), 
    GGRID_TOOL(R.id.id_palette_grid), 
    GHELP_TOOL(R.id.id_palette_help), 
    GLASSO_SELSCTOT_TOOL(R.id.palette_select_lasso, ToolNameEnum.GLASSO_SELECT_TOOL, R.string.lasso), 
    GLAYER_BOTTOM(R.id.layer_bottom, AlignTypeEnum.LAYER_BOTTOM.alignType, R.string.send_to_back), 
    GLAYER_TOP(R.id.layer_top, AlignTypeEnum.LAYER_TOP.alignType, R.string.bring_to_front), 
    GLEFT_CHAIR_TOOL(R.id.left_chair, ToolNameEnum.GLEFTCHAIR_TOOL, R.string.left_chair), 
    GMIRROR_LEFT_RIGHT(R.id.mirror_left_right, AlignTypeEnum.LEFT_AND_RIGHT.alignType, R.string.left_and_right), 
    GMIRROR_TOP_BOTTOM(R.id.mirror_top_bottom, AlignTypeEnum.TOP_AND_BOTTOM.alignType, R.string.top_and_bottom), 
    GMULTIBOND_TOOL(R.id.multibond, ToolNameEnum.GMULTIBOND_TOOL, R.string.snaking_chain), 
    GNAME_TO_STRUCTURE_TOOL(R.id.id_palette_name_to_structure), 
    GREC_SELECTOR_TOOL(R.id.palette_select_rectangle, ToolNameEnum.G_RECT_SELECT_TOOL, R.string.marquee), 
    GRIGHT_CHAIR_TOOL(R.id.right_chair, ToolNameEnum.GRIGHTCHAIR_TOOL, R.string.right_chair), 
    GR_TOOL(R.id.palette_r, ToolNameEnum.GR_TOOL), 
    GSHARE_TOOL(R.id.id_palette_share), 
    GSTRUCTURE_TO_NAME_TOOL(R.id.id_palette_structure_to_name), 
    GSUP_TOOL(R.id.palette_sup, ToolNameEnum.GSUP_TOOL), 
    GTEXT_TOOL(R.id.palette_text, ToolNameEnum.GTEXT_TOOL, R.string.text), 
    G_ACCW_ARROW_120_TOOL(R.id.ccw_120, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_ACCW_ARROW_120.arrowTypeParm, R.string.one_third_degree_ccw), 
    G_ACCW_ARROW_180_TOOL(R.id.ccw_180, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_ACCW_ARROW_180.arrowTypeParm, R.string.a_half_degree_ccw), 
    G_ACCW_ARROW_270_TOOL(R.id.ccw_270, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_ACCW_ARROW_270.arrowTypeParm, R.string.three_fourths_degree_ccw), 
    G_ACCW_ARROW_90_TOOL(R.id.ccw_90, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_ACCW_ARROW_90.arrowTypeParm, R.string.quarter_degree_ccw), 
    G_ACCW_SINGLE_ARROW_120_TOOL(R.id.single_ccw_120, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_ACCW_SINGLE_ARROW_120.arrowTypeParm, R.string.one_third_degree_single_ccw), 
    G_ACCW_SINGLE_ARROW_180_TOOL(R.id.single_ccw_180, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_ACCW_SINGLE_ARROW_180.arrowTypeParm, R.string.a_half_degree_single_ccw), 
    G_ACCW_SINGLE_ARROW_270_TOOL(R.id.single_ccw_270, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_ACCW_SINGLE_ARROW_270.arrowTypeParm, R.string.three_fourths_degree_single_ccw), 
    G_ACCW_SINGLE_ARROW_90_TOOL(R.id.single_ccw_90, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_ACCW_SINGLE_ARROW_90.arrowTypeParm, R.string.quarter_degree_single_ccw), 
    G_ACW_ARROW_120_TOOL(R.id.cw_120, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_ACW_ARROW_120.arrowTypeParm, R.string.one_third_degree_cw), 
    G_ACW_ARROW_180_TOOL(R.id.cw_180, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_ACW_ARROW_180.arrowTypeParm, R.string.a_half_degree_cw), 
    G_ACW_ARROW_270_TOOL(R.id.cw_270, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_ACW_ARROW_270.arrowTypeParm, R.string.three_fourths_degree_cw), 
    G_ACW_ARROW_90_TOOL(R.id.cw_90, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_ACW_ARROW_90.arrowTypeParm, R.string.quarter_degree_cw), 
    G_ACW_SINGLE_ARROW_120_TOOL(R.id.single_cw_120, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_ACW_SINGLE_ARROW_120.arrowTypeParm, R.string.one_third_degree_single_cw), 
    G_ACW_SINGLE_ARROW_180_TOOL(R.id.single_cw_180, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_ACW_SINGLE_ARROW_180.arrowTypeParm, R.string.a_half_degree_single_cw), 
    G_ACW_SINGLE_ARROW_270_TOOL(R.id.single_cw_270, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_ACW_SINGLE_ARROW_270.arrowTypeParm, R.string.three_fourths_degree_single_cw), 
    G_ACW_SINGLE_ARROW_90_TOOL(R.id.single_cw_90, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_ACW_SINGLE_ARROW_90.arrowTypeParm, R.string.quarter_degree_single_cw), 
    G_BOLD_ARROW_LARGE_TEXT_TOOL(R.id.bold_large_text, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_BOLD_ARROW_LARGE_TEXT.arrowTypeParm, R.string.bold_large), 
    G_BOLD_ARROW_LARGE_TOOL(R.id.bold_large, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_BOLD_ARROW_LARGE.arrowTypeParm, R.string.bold_large), 
    G_BOLD_ARROW_MEDIUM_TEXT_TOOL(R.id.bold_medium_text, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_BOLD_ARROW_MEDIUM_TEXT.arrowTypeParm, R.string.bold_medium), 
    G_BOLD_ARROW_MEDIUM_TOOL(R.id.bold_medium, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_BOLD_ARROW_MEDIUM.arrowTypeParm, R.string.bold_medium), 
    G_BOLD_ARROW_SMALL_TEXT_TOOL(R.id.bold_small_text, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_BOLD_ARROW_SMALL_TEXT.arrowTypeParm, R.string.bold_small), 
    G_BOLD_ARROW_SMALL_TOOL(R.id.bold_small, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_BOLD_ARROW_SMALL.arrowTypeParm, R.string.bold_small), 
    G_DASHED_ARROW_LARGE_TEXT_TOOL(R.id.dashed_large_text, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_DASHED_ARROW_LARGE_TEXT.arrowTypeParm, R.string.dashed_large), 
    G_DASHED_ARROW_LARGE_TOOL(R.id.dashed_large, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_DASHED_ARROW_LARGE.arrowTypeParm, R.string.dashed_large), 
    G_DASHED_ARROW_MEDIUM_TEXT_TOOL(R.id.dashed_medium_text, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_DASHED_ARROW_MEDIUM_TEXT.arrowTypeParm, R.string.dashed_medium), 
    G_DASHED_ARROW_MEDIUM_TOOL(R.id.dashed_medium, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_DASHED_ARROW_MEDIUM.arrowTypeParm, R.string.dashed_medium), 
    G_DASHED_ARROW_SMALL_TEXT_TOOL(R.id.dashed_small_text, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_DASHED_ARROW_SMALL_TEXT.arrowTypeParm, R.string.dashed_small), 
    G_DASHED_ARROW_SMALL_TOOL(R.id.dashed_small, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_DASHED_ARROW_SMALL.arrowTypeParm, R.string.dashed_small), 
    G_EQUILIBRIUM_ARROW_LARGE_TOOL(R.id.equilibrium_large, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_EQUILIBRIUM_ARROW_LARGE.arrowTypeParm, R.string.equilibrium_large), 
    G_EQUILIBRIUM_ARROW_MEDIUM_TOOL(R.id.equilibrium_medium, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_EQUILIBRIUM_ARROW_MEDIUM.arrowTypeParm, R.string.equilibrium_Medium), 
    G_EQUILIBRIUM_ARROW_SMALL_TOOL(R.id.equilibrium_small, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_EQUILIBRIUM_ARROW_SMALL.arrowTypeParm, R.string.equilibrium_Small), 
    G_HOLLOW_ARROW_LARGE_TOOL(R.id.hollow_large, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_HOLLOW_ARROW_LARGE.arrowTypeParm, R.string.hollow_large), 
    G_HOLLOW_ARROW_MEDIUM_TOOL(R.id.hollow_medium, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_HOLLOW_ARROW_MEDIUM.arrowTypeParm, R.string.hollow_medium), 
    G_NOGO_CROSS_ARROW_LARGE_TOOL(R.id.nogo_cross_large, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_NOGO_CROSS_ARROW_LARGE.arrowTypeParm, R.string.no_go_cross_large), 
    G_NOGO_CROSS_ARROW_MEDIUM_TOOL(R.id.nogo_cross_medium, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_NOGO_CROSS_ARROW_MEDIUM.arrowTypeParm, R.string.no_go_cross_medium), 
    G_NOGO_CROSS_ARROW_SMALL_TOOL(R.id.nogo_cross_small, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_NOGO_CROSS_ARROW_SMALL.arrowTypeParm, R.string.no_go_cross_small), 
    G_NOGO_HASH_ARROW_LARGE_TOOL(R.id.nogo_hash_large, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_NOGO_HASH_ARROW_LARGE.arrowTypeParm, R.string.no_go_hash_large), 
    G_NOGO_HASH_ARROW_MEDIUM_TOOL(R.id.nogo_hash_medium, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_NOGO_HASH_ARROW_MEDIUM.arrowTypeParm, R.string.no_go_hash_medium), 
    G_NOGO_HASH_ARROW_SMALL_TOOL(R.id.nogo_hash_small, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_NOGO_HASH_ARROW_SMALL.arrowTypeParm, R.string.no_go_hash_small), 
    G_ONE_SIDE_ARROW_LEFT_LARGE_TOOL(R.id.one_sided_left_large, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_ONE_SIDE_ARROW_LEFT_LARGE.arrowTypeParm, R.string.one_sided_left_large), 
    G_ONE_SIDE_ARROW_LEFT_MEDIUM_TOOL(R.id.one_sided_left_medium, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_ONE_SIDE_ARROW_LEFT_MEDIUM.arrowTypeParm, R.string.one_sided_left_medium), 
    G_ONE_SIDE_ARROW_LEFT_SMALL_TOOL(R.id.one_sided_left_small, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_ONE_SIDE_ARROW_LEFT_SMALL.arrowTypeParm, R.string.one_sided_left_small), 
    G_ONE_SIDE_ARROW_RIGHT_LARGE_TOOL(R.id.one_sided_right_large, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_ONE_SIDE_ARROW_RIGHT_LARGE.arrowTypeParm, R.string.one_sided_right_large), 
    G_ONE_SIDE_ARROW_RIGHT_MEDIUM_TOOL(R.id.one_sided_right_medium, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_ONE_SIDE_ARROW_RIGHT_MEDIUM.arrowTypeParm, R.string.one_sided_right_medium), 
    G_ONE_SIDE_ARROW_RIGHT_SMALL_TOOL(R.id.one_sided_right_small, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_ONE_SIDE_ARROW_RIGHT_SMALL.arrowTypeParm, R.string.one_sided_right_small), 
    G_RESONANCE_ARROW_LARGE_TOOL(R.id.resonance_large, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_RESONANCE_ARROW_LARGE.arrowTypeParm, R.string.resonance_large), 
    G_RESONANCE_ARROW_MEDIUM_TOOL(R.id.resonance_medium, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_RESONANCE_ARROW_MEDIUM.arrowTypeParm, R.string.resonance_medium), 
    G_RESONANCE_ARROW_SMALL_TOOL(R.id.resonance_small, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_RESONANCE_ARROW_SMALL.arrowTypeParm, R.string.resonance_small), 
    G_RETROSYNTHETIC_ARROW_LARGE_TOOL(R.id.retrosynthetlarge, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_RETROSYNTHETIC_ARROW_LARGE.arrowTypeParm, R.string.retrosynthetic_large), 
    G_RETROSYNTHETIC_ARROW_SMALL_TOOL(R.id.retrosynthetsmall, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_RETROSYNTHETIC_ARROW_SMALL.arrowTypeParm, R.string.retrosynthetic_small), 
    G_SOLID_ARROW_LARGE_TEXT_TOOL(R.id.solid_large_text, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_SOLID_ARROW_LARGE_TEXT.arrowTypeParm, R.string.solid_large), 
    G_SOLID_ARROW_LARGE_TOOL(R.id.solid_large, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_SOLID_ARROW_LARGE.arrowTypeParm, R.string.solid_large), 
    G_SOLID_ARROW_MEDIUM_TEXT_TOOL(R.id.solid_medium_text, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_SOLID_ARROW_MEDIUM_TEXT.arrowTypeParm, R.string.solid_medium), 
    G_SOLID_ARROW_MEDIUM_TOOL(R.id.solid_medium, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_SOLID_ARROW_MEDIUM.arrowTypeParm, R.string.solid_medium), 
    G_SOLID_ARROW_SMALL_TEXT_TOOL(R.id.solid_small_text, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_SOLID_ARROW_SMALL_TEXT.arrowTypeParm, R.string.solid_small), 
    G_SOLID_ARROW_SMALL_TOOL(R.id.solid_small, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_SOLID_ARROW_SMALL.arrowTypeParm, R.string.solid_small), 
    G_TWO_HEADED_ARROW_120_TOOL(R.id.headed_120, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_TWO_HEADED_ARROW_120.arrowTypeParm, R.string.one_third_degree_two_headed), 
    G_TWO_HEADED_ARROW_180_TOOL(R.id.headed_180, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_TWO_HEADED_ARROW_180.arrowTypeParm, R.string.a_half_degree_two_headed), 
    G_TWO_HEADED_ARROW_270_TOOL(R.id.headed_270, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_TWO_HEADED_ARROW_270.arrowTypeParm, R.string.three_fourths_degree_two_headed), 
    G_TWO_HEADED_ARROW_90_TOOL(R.id.headed_90, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_TWO_HEADED_ARROW_90.arrowTypeParm, R.string.quarter_degree_two_headed), 
    G_UNBALANCED_EQUILIBRIUM_ARROW_LARGE_TOOL(R.id.unbalanced_equilibrium_large, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_UNBALANCED_EQUILIBRIUM_ARROW_LARGE.arrowTypeParm, R.string.unbalanced_equilibrium_large), 
    G_UNBALANCED_EQUILIBRIUM_ARROW_MEDIUM_TOOL(R.id.unbalanced_equilibrium_medium, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_UNBALANCED_EQUILIBRIUM_ARROW_MEDIUM.arrowTypeParm, R.string.unbalanced_equilibrium_medium), 
    G_UNBALANCED_EQUILIBRIUM_ARROW_SMALL_TOOL(R.id.unbalanced_equilibrium_small, ToolNameEnum.GKDLineTool, ArrowToolParmEnum.G_UNBALANCED_EQUILIBRIUM_ARROW_SMALL.arrowTypeParm, R.string.unbalanced_equilibrium_small), 
    NODE_DIRECT_AUTO(R.id.node_direct_auto, R.string.node_direct_auto), 
    NODE_DIRECT_RIGHT(R.id.node_direct_right, R.string.node_direct_right), 
    ORBITAL_CIRCLE_3D(R.id.orbital_circle_3d, ToolNameEnum.GORBITAL_TOOL, OrbitalTypeEnum.ORBITAL_CIRCLE_3D.orbitalType, R.string.orbital), 
    ORBITAL_CIRCLE_BLACK(R.id.orbital_circle_black, ToolNameEnum.GORBITAL_TOOL, OrbitalTypeEnum.ORBITAL_CIRCLE_BLACK.orbitalType, R.string.orbital), 
    ORBITAL_CIRCLE_WHITE(R.id.orbital_circle_white, ToolNameEnum.GORBITAL_TOOL, OrbitalTypeEnum.ORBITAL_CIRCLE_WHITE.orbitalType, R.string.orbital), 
    ORBITAL_OVAL_3D(R.id.orbital_oval_3d, ToolNameEnum.GORBITAL_TOOL, OrbitalTypeEnum.ORBITAL_OVAL_3D.orbitalType, R.string.orbital), 
    ORBITAL_OVAL_BLACK(R.id.orbital_oval_black, ToolNameEnum.GORBITAL_TOOL, OrbitalTypeEnum.ORBITAL_OVAL_BLACK.orbitalType, R.string.orbital), 
    ORBITAL_OVAL_WHITE(R.id.orbital_oval_white, ToolNameEnum.GORBITAL_TOOL, OrbitalTypeEnum.ORBITAL_OVAL_WHITE.orbitalType, R.string.orbital), 
    ORBITAL_PETALS_3D(R.id.orbital_petals_3d, ToolNameEnum.GORBITAL_TOOL, OrbitalTypeEnum.ORBITAL_PETALS_3D.orbitalType, R.string.orbital), 
    ORBITAL_PETALS_3DS_WHITE(R.id.orbital_petals_3ds_white, ToolNameEnum.GORBITAL_TOOL, OrbitalTypeEnum.ORBITAL_PETALS_3DS_WHITE.orbitalType, R.string.orbital), 
    ORBITAL_PETALS_3DS_WHITES(R.id.orbital_petals_3ds_whites, ToolNameEnum.GORBITAL_TOOL, OrbitalTypeEnum.ORBITAL_PETALS_3DS_WHITES.orbitalType, R.string.orbital), 
    ORBITAL_PETALS_3D_SMALLWHITE(R.id.orbital_petals_3d_smallwhite, ToolNameEnum.GORBITAL_TOOL, OrbitalTypeEnum.ORBITAL_PETALS_3D_SMALLWHITE.orbitalType, R.string.orbital), 
    ORBITAL_PETALS_3D_WHITE(R.id.orbital_petals_3d_white, ToolNameEnum.GORBITAL_TOOL, OrbitalTypeEnum.ORBITAL_PETALS_3D_WHITE.orbitalType, R.string.orbital), 
    ORBITAL_PETALS_BLACK(R.id.orbital_petals_black, ToolNameEnum.GORBITAL_TOOL, OrbitalTypeEnum.ORBITAL_PETALS_BLACK.orbitalType, R.string.orbital), 
    ORBITAL_PETALS_BLACKS_WHITE(R.id.orbital_petals_blacks_white, ToolNameEnum.GORBITAL_TOOL, OrbitalTypeEnum.ORBITAL_PETALS_BLACKS_WHITE.orbitalType, R.string.orbital), 
    ORBITAL_PETALS_BLACKS_WHITES(R.id.orbital_petals_blacks_whites, ToolNameEnum.GORBITAL_TOOL, OrbitalTypeEnum.ORBITAL_PETALS_BLACKS_WHITES.orbitalType, R.string.orbital), 
    ORBITAL_PETALS_BLACK_SMALLWHITE(R.id.orbital_petals_black_smallwhite, ToolNameEnum.GORBITAL_TOOL, OrbitalTypeEnum.ORBITAL_PETALS_BLACK_SMALLWHITE.orbitalType, R.string.orbital), 
    ORBITAL_PETALS_BLACK_WHITE(R.id.orbital_petals_black_white, ToolNameEnum.GORBITAL_TOOL, OrbitalTypeEnum.ORBITAL_PETALS_BLACK_WHITE.orbitalType, R.string.orbital), 
    ORBITAL_PETALS_WHITE(R.id.orbital_petals_white, ToolNameEnum.GORBITAL_TOOL, OrbitalTypeEnum.ORBITAL_PETALS_WHITE.orbitalType, R.string.orbital), 
    ORBITAL_PETALS_WHITES_3D(R.id.orbital_petals_whites_3d, ToolNameEnum.GORBITAL_TOOL, OrbitalTypeEnum.ORBITAL_PETALS_WHITES_3D.orbitalType, R.string.orbital), 
    ORBITAL_PETALS_WHITES_BLACK(R.id.orbital_petals_whites_black, ToolNameEnum.GORBITAL_TOOL, OrbitalTypeEnum.ORBITAL_PETALS_WHITES_BLACK.orbitalType, R.string.orbital), 
    ORBITAL_PETALS_WHITE_SMALL3D(R.id.orbital_petals_white_small3d, ToolNameEnum.GORBITAL_TOOL, OrbitalTypeEnum.ORBITAL_PETALS_WHITE_SMALL3D.orbitalType, R.string.orbital), 
    ORBITAL_PETALS_WHITE_SMALLBLACK(R.id.orbital_petals_white_smallblack, ToolNameEnum.GORBITAL_TOOL, OrbitalTypeEnum.ORBITAL_PETALS_WHITE_SMALLBLACK.orbitalType, R.string.orbital);
    
    public int hintNameId;
    public int id;
    public ToolNameEnum nameEnum;
    public String toolParm;
    
    private ToolEnum() {
        this.toolParm = "";
    }
    
    private ToolEnum(final int id) {
        this.toolParm = "";
        this.id = id;
    }
    
    private ToolEnum(final int id, final int hintNameId) {
        this.toolParm = "";
        this.id = id;
        this.hintNameId = hintNameId;
    }
    
    private ToolEnum(final int id, final ToolNameEnum nameEnum) {
        this.toolParm = "";
        this.id = id;
        this.nameEnum = nameEnum;
    }
    
    private ToolEnum(final int id, final ToolNameEnum nameEnum, final int hintNameId) {
        this.toolParm = "";
        this.id = id;
        this.nameEnum = nameEnum;
        this.hintNameId = hintNameId;
    }
    
    private ToolEnum(final int id, final ToolNameEnum nameEnum, final String toolParm, final int hintNameId) {
        this.toolParm = "";
        this.id = id;
        this.nameEnum = nameEnum;
        this.toolParm = toolParm;
        this.hintNameId = hintNameId;
    }
    
    private ToolEnum(final int id, final String toolParm, final int hintNameId) {
        this.toolParm = "";
        this.id = id;
        this.toolParm = toolParm;
        this.hintNameId = hintNameId;
    }
    
    public static ToolEnum getValueById(final int n) {
        for (final ToolEnum toolEnum : values()) {
            if (toolEnum.id == n) {
                return toolEnum;
            }
        }
        return null;
    }
}
