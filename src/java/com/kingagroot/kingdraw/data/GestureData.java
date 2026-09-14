package com.kingagroot.kingdraw.data;

import java.util.Comparator;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import com.kingagroot.kingdraw.model.GestureGroupModel;

public class GestureData
{
    private static GestureGroupModel gestureGroupModel1() {
        final GestureGroupModel gestureGroupModel = new GestureGroupModel();
        gestureGroupModel.setBondId(1);
        gestureGroupModel.setGestureId(1);
        gestureGroupModel.setGestureCoreId(1);
        gestureGroupModel.setBondRes("ic_bond_single");
        gestureGroupModel.setGestureRes("ic_single_bond");
        gestureGroupModel.setToolName("GSingleBondTool");
        gestureGroupModel.setParam("");
        gestureGroupModel.setBind(true);
        return gestureGroupModel;
    }
    
    private static GestureGroupModel gestureGroupModel10() {
        final GestureGroupModel gestureGroupModel = new GestureGroupModel();
        gestureGroupModel.setBondId(10);
        gestureGroupModel.setGestureId(0);
        gestureGroupModel.setGestureCoreId(0);
        gestureGroupModel.setBondRes("ic_cyclopropane");
        gestureGroupModel.setGestureRes("");
        gestureGroupModel.setToolName("GCyclopropaneTool");
        gestureGroupModel.setParam("");
        gestureGroupModel.setBind(false);
        return gestureGroupModel;
    }
    
    private static GestureGroupModel gestureGroupModel11() {
        final GestureGroupModel gestureGroupModel = new GestureGroupModel();
        gestureGroupModel.setBondId(11);
        gestureGroupModel.setGestureId(0);
        gestureGroupModel.setGestureCoreId(0);
        gestureGroupModel.setBondRes("ic_cyclobutane");
        gestureGroupModel.setGestureRes("");
        gestureGroupModel.setToolName("GCyclobutaneTool");
        gestureGroupModel.setParam("");
        gestureGroupModel.setBind(false);
        return gestureGroupModel;
    }
    
    private static GestureGroupModel gestureGroupModel12() {
        final GestureGroupModel gestureGroupModel = new GestureGroupModel();
        gestureGroupModel.setBondId(12);
        gestureGroupModel.setGestureId(0);
        gestureGroupModel.setGestureCoreId(0);
        gestureGroupModel.setBondRes("ic_cyclopentane");
        gestureGroupModel.setGestureRes("");
        gestureGroupModel.setToolName("GCyclopentaneTool");
        gestureGroupModel.setParam("");
        gestureGroupModel.setBind(false);
        return gestureGroupModel;
    }
    
    private static GestureGroupModel gestureGroupModel13() {
        final GestureGroupModel gestureGroupModel = new GestureGroupModel();
        gestureGroupModel.setBondId(13);
        gestureGroupModel.setGestureId(5);
        gestureGroupModel.setGestureCoreId(5);
        gestureGroupModel.setBondRes("ic_cyclohexane");
        gestureGroupModel.setGestureRes("ic_6");
        gestureGroupModel.setToolName("GCyclohexaneTool");
        gestureGroupModel.setParam("");
        gestureGroupModel.setBind(true);
        return gestureGroupModel;
    }
    
    private static GestureGroupModel gestureGroupModel14() {
        final GestureGroupModel gestureGroupModel = new GestureGroupModel();
        gestureGroupModel.setBondId(14);
        gestureGroupModel.setGestureId(0);
        gestureGroupModel.setGestureCoreId(0);
        gestureGroupModel.setBondRes("ic_cycloheptane");
        gestureGroupModel.setGestureRes("");
        gestureGroupModel.setToolName("GCycloheptaneTool");
        gestureGroupModel.setParam("");
        gestureGroupModel.setBind(false);
        return gestureGroupModel;
    }
    
    private static GestureGroupModel gestureGroupModel15() {
        final GestureGroupModel gestureGroupModel = new GestureGroupModel();
        gestureGroupModel.setBondId(15);
        gestureGroupModel.setGestureId(0);
        gestureGroupModel.setGestureCoreId(0);
        gestureGroupModel.setBondRes("ic_cyclooctane");
        gestureGroupModel.setGestureRes("");
        gestureGroupModel.setToolName("GCyclooctaneTool");
        gestureGroupModel.setParam("");
        gestureGroupModel.setBind(false);
        return gestureGroupModel;
    }
    
    private static GestureGroupModel gestureGroupModel16() {
        final GestureGroupModel gestureGroupModel = new GestureGroupModel();
        gestureGroupModel.setBondId(16);
        gestureGroupModel.setGestureId(0);
        gestureGroupModel.setGestureCoreId(0);
        gestureGroupModel.setBondRes("ic_gesture_h");
        gestureGroupModel.setGestureRes("");
        gestureGroupModel.setToolName("GAtomTool");
        gestureGroupModel.setParam("H");
        gestureGroupModel.setBind(false);
        return gestureGroupModel;
    }
    
    private static GestureGroupModel gestureGroupModel17() {
        final GestureGroupModel gestureGroupModel = new GestureGroupModel();
        gestureGroupModel.setBondId(17);
        gestureGroupModel.setGestureId(7);
        gestureGroupModel.setGestureCoreId(11);
        gestureGroupModel.setBondRes("ic_c_atom");
        gestureGroupModel.setGestureRes("ic_c");
        gestureGroupModel.setToolName("GAtomTool");
        gestureGroupModel.setParam("C");
        gestureGroupModel.setBind(true);
        return gestureGroupModel;
    }
    
    private static GestureGroupModel gestureGroupModel18() {
        final GestureGroupModel gestureGroupModel = new GestureGroupModel();
        gestureGroupModel.setBondId(18);
        gestureGroupModel.setGestureId(13);
        gestureGroupModel.setGestureCoreId(12);
        gestureGroupModel.setBondRes("ic_n_atom");
        gestureGroupModel.setGestureRes("ic_n");
        gestureGroupModel.setToolName("GAtomTool");
        gestureGroupModel.setParam("N");
        gestureGroupModel.setBind(true);
        return gestureGroupModel;
    }
    
    private static GestureGroupModel gestureGroupModel19() {
        final GestureGroupModel gestureGroupModel = new GestureGroupModel();
        gestureGroupModel.setBondId(19);
        gestureGroupModel.setGestureId(12);
        gestureGroupModel.setGestureCoreId(9);
        gestureGroupModel.setBondRes("ic_o_atom");
        gestureGroupModel.setGestureRes("ic_o");
        gestureGroupModel.setToolName("GAtomTool");
        gestureGroupModel.setParam("O");
        gestureGroupModel.setBind(true);
        return gestureGroupModel;
    }
    
    private static GestureGroupModel gestureGroupModel2() {
        final GestureGroupModel gestureGroupModel = new GestureGroupModel();
        gestureGroupModel.setBondId(2);
        gestureGroupModel.setGestureId(2);
        gestureGroupModel.setGestureCoreId(2);
        gestureGroupModel.setBondRes("ic_bond_double");
        gestureGroupModel.setGestureRes("ic_double_bond");
        gestureGroupModel.setToolName("GDoubleBondTool");
        gestureGroupModel.setParam("");
        gestureGroupModel.setBind(true);
        return gestureGroupModel;
    }
    
    private static GestureGroupModel gestureGroupModel20() {
        final GestureGroupModel gestureGroupModel = new GestureGroupModel();
        gestureGroupModel.setBondId(20);
        gestureGroupModel.setGestureId(0);
        gestureGroupModel.setGestureCoreId(0);
        gestureGroupModel.setBondRes("ic_gesture_f");
        gestureGroupModel.setGestureRes("");
        gestureGroupModel.setToolName("GAtomTool");
        gestureGroupModel.setParam("F");
        gestureGroupModel.setBind(false);
        return gestureGroupModel;
    }
    
    private static GestureGroupModel gestureGroupModel21() {
        final GestureGroupModel gestureGroupModel = new GestureGroupModel();
        gestureGroupModel.setBondId(21);
        gestureGroupModel.setGestureId(0);
        gestureGroupModel.setGestureCoreId(0);
        gestureGroupModel.setBondRes("ic_gesture_p");
        gestureGroupModel.setGestureRes("");
        gestureGroupModel.setToolName("GAtomTool");
        gestureGroupModel.setParam("P");
        gestureGroupModel.setBind(false);
        return gestureGroupModel;
    }
    
    private static GestureGroupModel gestureGroupModel22() {
        final GestureGroupModel gestureGroupModel = new GestureGroupModel();
        gestureGroupModel.setBondId(22);
        gestureGroupModel.setGestureId(8);
        gestureGroupModel.setGestureCoreId(8);
        gestureGroupModel.setBondRes("ic_s_atom");
        gestureGroupModel.setGestureRes("ic_s");
        gestureGroupModel.setToolName("GAtomTool");
        gestureGroupModel.setParam("S");
        gestureGroupModel.setBind(true);
        return gestureGroupModel;
    }
    
    private static GestureGroupModel gestureGroupModel23() {
        final GestureGroupModel gestureGroupModel = new GestureGroupModel();
        gestureGroupModel.setBondId(23);
        gestureGroupModel.setGestureId(6);
        gestureGroupModel.setGestureCoreId(13);
        gestureGroupModel.setBondRes("ic_cl_atom");
        gestureGroupModel.setGestureRes("ic_cl");
        gestureGroupModel.setToolName("GAtomTool");
        gestureGroupModel.setParam("Cl");
        gestureGroupModel.setBind(true);
        return gestureGroupModel;
    }
    
    private static GestureGroupModel gestureGroupModel24() {
        final GestureGroupModel gestureGroupModel = new GestureGroupModel();
        gestureGroupModel.setBondId(24);
        gestureGroupModel.setGestureId(11);
        gestureGroupModel.setGestureCoreId(10);
        gestureGroupModel.setBondRes("ic_br_atom");
        gestureGroupModel.setGestureRes("ic_br");
        gestureGroupModel.setToolName("GAtomTool");
        gestureGroupModel.setParam("Br");
        gestureGroupModel.setBind(true);
        return gestureGroupModel;
    }
    
    private static GestureGroupModel gestureGroupModel25() {
        final GestureGroupModel gestureGroupModel = new GestureGroupModel();
        gestureGroupModel.setBondId(25);
        gestureGroupModel.setGestureId(0);
        gestureGroupModel.setGestureCoreId(0);
        gestureGroupModel.setBondRes("ic_gesture_i");
        gestureGroupModel.setGestureRes("");
        gestureGroupModel.setToolName("GAtomTool");
        gestureGroupModel.setParam("I");
        gestureGroupModel.setBind(false);
        return gestureGroupModel;
    }
    
    private static GestureGroupModel gestureGroupModel3() {
        final GestureGroupModel gestureGroupModel = new GestureGroupModel();
        gestureGroupModel.setBondId(3);
        gestureGroupModel.setGestureId(3);
        gestureGroupModel.setGestureCoreId(3);
        gestureGroupModel.setBondRes("ic_bond_triple");
        gestureGroupModel.setGestureRes("ic_triple_bond");
        gestureGroupModel.setToolName("GTripleBondTool");
        gestureGroupModel.setParam("");
        gestureGroupModel.setBind(true);
        return gestureGroupModel;
    }
    
    private static GestureGroupModel gestureGroupModel4() {
        final GestureGroupModel gestureGroupModel = new GestureGroupModel();
        gestureGroupModel.setBondId(4);
        gestureGroupModel.setGestureId(10);
        gestureGroupModel.setGestureCoreId(6);
        gestureGroupModel.setBondRes("ic_bond_wedge");
        gestureGroupModel.setGestureRes("ic_v_2");
        gestureGroupModel.setToolName("GWedgeBondTool");
        gestureGroupModel.setParam("");
        gestureGroupModel.setBind(true);
        return gestureGroupModel;
    }
    
    private static GestureGroupModel gestureGroupModel5() {
        final GestureGroupModel gestureGroupModel = new GestureGroupModel();
        gestureGroupModel.setBondId(5);
        gestureGroupModel.setGestureId(9);
        gestureGroupModel.setGestureCoreId(7);
        gestureGroupModel.setBondRes("ic_bond_hash");
        gestureGroupModel.setGestureRes("ic_v");
        gestureGroupModel.setToolName("GHashWedgeBondTool");
        gestureGroupModel.setParam("");
        gestureGroupModel.setBind(true);
        return gestureGroupModel;
    }
    
    private static GestureGroupModel gestureGroupModel6() {
        final GestureGroupModel gestureGroupModel = new GestureGroupModel();
        gestureGroupModel.setBondId(6);
        gestureGroupModel.setGestureId(0);
        gestureGroupModel.setGestureCoreId(0);
        gestureGroupModel.setBondRes("ic_bond_up");
        gestureGroupModel.setGestureRes("");
        gestureGroupModel.setToolName("GBoldBondTool");
        gestureGroupModel.setParam("");
        gestureGroupModel.setBind(false);
        return gestureGroupModel;
    }
    
    private static GestureGroupModel gestureGroupModel7() {
        final GestureGroupModel gestureGroupModel = new GestureGroupModel();
        gestureGroupModel.setBondId(7);
        gestureGroupModel.setGestureId(0);
        gestureGroupModel.setGestureCoreId(0);
        gestureGroupModel.setBondRes("ic_bond_down");
        gestureGroupModel.setGestureRes("");
        gestureGroupModel.setToolName("GHashBondTool");
        gestureGroupModel.setParam("");
        gestureGroupModel.setBind(false);
        return gestureGroupModel;
    }
    
    private static GestureGroupModel gestureGroupModel8() {
        final GestureGroupModel gestureGroupModel = new GestureGroupModel();
        gestureGroupModel.setBondId(8);
        gestureGroupModel.setGestureId(0);
        gestureGroupModel.setGestureCoreId(0);
        gestureGroupModel.setBondRes("ic_cyclopentadiene");
        gestureGroupModel.setGestureRes("");
        gestureGroupModel.setToolName("GCyclopentadieneTool");
        gestureGroupModel.setParam("");
        gestureGroupModel.setBind(false);
        return gestureGroupModel;
    }
    
    private static GestureGroupModel gestureGroupModel9() {
        final GestureGroupModel gestureGroupModel = new GestureGroupModel();
        gestureGroupModel.setBondId(9);
        gestureGroupModel.setGestureId(4);
        gestureGroupModel.setGestureCoreId(4);
        gestureGroupModel.setBondRes("ic_benzene");
        gestureGroupModel.setGestureRes("ic_e");
        gestureGroupModel.setToolName("GBenzeneTool");
        gestureGroupModel.setParam("");
        gestureGroupModel.setBind(true);
        return gestureGroupModel;
    }
    
    public static List<GestureGroupModel> gestureListData() {
        final ArrayList list = new ArrayList();
        ((List)list).add((Object)gestureGroupModel1());
        ((List)list).add((Object)gestureGroupModel2());
        ((List)list).add((Object)gestureGroupModel3());
        ((List)list).add((Object)gestureGroupModel4());
        ((List)list).add((Object)gestureGroupModel5());
        ((List)list).add((Object)gestureGroupModel6());
        ((List)list).add((Object)gestureGroupModel7());
        ((List)list).add((Object)gestureGroupModel8());
        ((List)list).add((Object)gestureGroupModel9());
        ((List)list).add((Object)gestureGroupModel10());
        ((List)list).add((Object)gestureGroupModel11());
        ((List)list).add((Object)gestureGroupModel12());
        ((List)list).add((Object)gestureGroupModel13());
        ((List)list).add((Object)gestureGroupModel14());
        ((List)list).add((Object)gestureGroupModel15());
        ((List)list).add((Object)gestureGroupModel16());
        ((List)list).add((Object)gestureGroupModel17());
        ((List)list).add((Object)gestureGroupModel18());
        ((List)list).add((Object)gestureGroupModel19());
        ((List)list).add((Object)gestureGroupModel20());
        ((List)list).add((Object)gestureGroupModel21());
        ((List)list).add((Object)gestureGroupModel22());
        ((List)list).add((Object)gestureGroupModel23());
        ((List)list).add((Object)gestureGroupModel24());
        ((List)list).add((Object)gestureGroupModel25());
        return (List<GestureGroupModel>)list;
    }
    
    public static List<GestureGroupModel> listSortByBondId(final List<GestureGroupModel> list) {
        Collections.sort((List)list, (Comparator)_$$Lambda$GestureData$X8vTSx7apWZ9QzOLwehwGJzLoSo.INSTANCE);
        return list;
    }
    
    public static List<GestureGroupModel> listSortByGestureId(final List<GestureGroupModel> list) {
        Collections.sort((List)list, (Comparator)_$$Lambda$GestureData$ONZ_Wrl0df_QfJl3fLfMtZ0i0m0.INSTANCE);
        return list;
    }
}
