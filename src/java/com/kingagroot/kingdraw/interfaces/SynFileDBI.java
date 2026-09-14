package com.kingagroot.kingdraw.interfaces;

import java.util.List;
import com.kingagroot.kingdraw.model.SynFileModel;

public interface SynFileDBI
{
    boolean addSynFileModel(final SynFileModel p0);
    
    boolean addSynFileModels(final List<SynFileModel> p0);
    
    boolean deleteSynFileByFolderFileId(final String p0);
    
    boolean deleteSynFileModel(final String p0);
    
    List<SynFileModel> getDownLoadingFileModels();
    
    List<SynFileModel> getDownLodFileModels();
    
    SynFileModel getSynFileModelByFolderFileModelId(final String p0);
    
    List<SynFileModel> getUpLoadFileModels();
    
    void restartSynFile();
    
    boolean setSynFileModelStatus(final String p0, final int p1);
    
    boolean stopAllSynModel(final int p0);
}
