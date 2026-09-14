package com.kingagroot.kingdraw.utils;

import com.kingagroot.kingdraw.core.data.ProtocolReader$Builer;
import java.io.IOException;
import com.kingagroot.kingdraw.core.data.ProtocolReader;

public class SmilesUtils
{
    public static String getSmiles(String smiles) {
        final ProtocolReader$Builer setNeedSmiles = ProtocolReader.builder().setNeedSmiles(true);
        try {
            smiles = setNeedSmiles.readByPath(smiles).smiles;
            return smiles;
        }
        catch (final IOException ex) {
            ex.printStackTrace();
            return "";
        }
    }
}
