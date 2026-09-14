package com.kingagroot.kingdraw.interfaces.impl;

import com.kingagroot.kingdraw.core.FileWriter$Builder;
import java.io.FileNotFoundException;
import java.io.IOException;
import com.kingagroot.kingdraw.base.MApplication;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.ByteArrayOutputStream;
import com.kingagroot.kingdraw.core.image.DrawOption;
import com.kingagroot.kingdraw.core.image.ImageDrawBuilder;
import com.kingagroot.component.ui.utils.MThumbImageDrawOption;
import android.graphics.Bitmap$CompressFormat;
import com.kingagroot.kingdraw.config.FileConfig;
import com.kingagroot.kingdraw.core.FileWriter;
import com.kingagroot.component.ui.view.OperationState;
import com.kingagroot.kingdraw.core.view.KingDrawView;
import android.database.Cursor;
import org.xutils.common.util.IOUtil;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import com.kingagroot.kingdraw.core.data.ProtocolTypeEnum;
import com.kingagroot.component.ui.UIComponentHelper;
import java.util.Iterator;
import java.util.List;
import org.xutils.ex.DbException;
import java.io.File;
import com.kingagroot.kingdraw.model.GFileSearchModel;
import com.kingagroot.kingdraw.interfaces.DrawSearchHisDBI;

public class DrawSearchHisDBImpl extends BaseDBImpl implements DrawSearchHisDBI
{
    long maxNum;
    
    public DrawSearchHisDBImpl() {
        this.maxNum = 10L;
    }
    
    private void delete() {
        try {
            final long count = this.db.selector((Class)GFileSearchModel.class).count();
            if (count > this.maxNum) {
                final List all = this.db.selector((Class)GFileSearchModel.class).offset(0).limit((int)(count - this.maxNum)).orderBy("CreatTime", false).findAll();
                if (all != null) {
                    for (final GFileSearchModel gFileSearchModel : all) {
                        final File file = new File(gFileSearchModel.getPicPath());
                        if (file.exists()) {
                            file.delete();
                        }
                        this.db.deleteById((Class)GFileSearchModel.class, (Object)gFileSearchModel.getId());
                    }
                }
            }
        }
        catch (final DbException ex) {
            ex.printStackTrace();
        }
    }
    
    public boolean clear() {
        try {
            final List all = this.db.findAll((Class)GFileSearchModel.class);
            if (all != null) {
                final Iterator iterator = all.iterator();
                while (iterator.hasNext()) {
                    final File file = new File(((GFileSearchModel)iterator.next()).getPicPath());
                    if (file.exists()) {
                        file.delete();
                    }
                }
            }
            this.db.delete((Class)GFileSearchModel.class);
            return true;
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public List<GFileSearchModel> getHisData() {
        try {
            return (List<GFileSearchModel>)this.findAllBySql((Class)GFileSearchModel.class, "SELECT Id, picPath, smiles, formola, CreatTime, kingContent FROM \"GFileSearchModel\" ORDER BY \"CreatTime\" DESC LIMIT 10 OFFSET 0");
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    protected String getKdxFile(final int n) {
        try {
            final StringBuilder sb = new StringBuilder();
            sb.append("SELECT fileStream FROM \"GFileSearchModel\" WHERE Id = ");
            sb.append(n);
            final Cursor execQuery = this.db.execQuery(sb.toString());
            if (execQuery != null) {
                try {
                    if (execQuery.moveToNext()) {
                        final byte[] blob = execQuery.getBlob(0);
                        final String fileCachePath = UIComponentHelper.getFileCachePath();
                        final StringBuilder sb2 = new StringBuilder();
                        sb2.append(System.currentTimeMillis());
                        sb2.append("");
                        final String string = sb2.toString();
                        final StringBuilder sb3 = new StringBuilder();
                        sb3.append(fileCachePath);
                        sb3.append(File.separator);
                        sb3.append(string);
                        sb3.append(ProtocolTypeEnum.KDX.extension);
                        final String string2 = sb3.toString();
                        final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream((OutputStream)new FileOutputStream(new File(string2)));
                        bufferedOutputStream.write(blob, 0, blob.length);
                        bufferedOutputStream.close();
                        IOUtil.closeQuietly(execQuery);
                        return string2;
                    }
                    IOUtil.closeQuietly(execQuery);
                    return null;
                }
                finally {
                    try {
                        final Throwable t;
                        throw new DbException(t);
                    }
                    finally {
                        IOUtil.closeQuietly(execQuery);
                    }
                }
            }
        }
        catch (final DbException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public String getSearchFile(final int n) {
        return this.getKdxFile(n);
    }
    
    public OperationState save(final KingDrawView drawWithView) {
        try {
            this.delete();
            final FileWriter$Builder setNeedFormula = FileWriter.getBuilder().withView(drawWithView).setNeedKDJson(false).setNeedSmiles(true).setNeedFormula(true);
            final String fileCachePath = FileConfig.getFileCachePath();
            final StringBuilder sb = new StringBuilder();
            sb.append(System.currentTimeMillis());
            sb.append("");
            final String string = sb.toString();
            if (!setNeedFormula.writeToFile(fileCachePath, string, ProtocolTypeEnum.KDX)) {
                return this.error();
            }
            final String smiles = setNeedFormula.getSmiles();
            final String formula = setNeedFormula.getFormula();
            final StringBuilder sb2 = new StringBuilder();
            sb2.append(System.currentTimeMillis());
            sb2.append("");
            final String string2 = sb2.toString();
            final StringBuilder sb3 = new StringBuilder();
            sb3.append(string2);
            sb3.append(".");
            sb3.append((Object)Bitmap$CompressFormat.JPEG);
            final String string3 = sb3.toString();
            final StringBuilder sb4 = new StringBuilder();
            sb4.append(FileConfig.DRAW_FILE_PIC_PATH);
            sb4.append(string3);
            final String string4 = sb4.toString();
            final MThumbImageDrawOption drawOption = new MThumbImageDrawOption();
            drawOption.setMark(false);
            final ImageDrawBuilder imageDrawBuilder = new ImageDrawBuilder();
            imageDrawBuilder.setBackgroundColor(-1).setDrawWithView(drawWithView).setCompressFormat(Bitmap$CompressFormat.JPEG, 100).setDrawOption((DrawOption)drawOption);
            imageDrawBuilder.saveToFile(FileConfig.DRAW_FILE_PIC_PATH, string2);
            imageDrawBuilder.onDestroy();
            final StringBuilder sb5 = new StringBuilder();
            sb5.append(fileCachePath);
            sb5.append(File.separator);
            sb5.append(string);
            sb5.append(ProtocolTypeEnum.KDX.extension);
            final File file = new File(sb5.toString());
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final BufferedInputStream bufferedInputStream = new BufferedInputStream((InputStream)new FileInputStream(file));
            final byte[] array = new byte[1024];
            while (true) {
                final int read = bufferedInputStream.read(array, 0, 1024);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(array, 0, read);
            }
            bufferedInputStream.close();
            GFileSearchModel gFileSearchModel = null;
            if (this.db.selector((Class)GFileSearchModel.class).where("smiles", "=", (Object)smiles).count() > 0L) {
                gFileSearchModel = (GFileSearchModel)this.db.selector((Class)GFileSearchModel.class).where("smiles", "=", (Object)smiles).findFirst();
            }
            GFileSearchModel data;
            if ((data = gFileSearchModel) == null) {
                data = new GFileSearchModel();
            }
            data.setSmiles(smiles);
            data.setFormola(formula);
            data.setCreatTime(System.currentTimeMillis());
            data.setPicPath(string4);
            data.setFileStream(byteArrayOutputStream.toByteArray());
            this.db.saveOrUpdate((Object)data);
            data.setFileStream(new byte[0]);
            file.delete();
            byteArrayOutputStream.close();
            final OperationState success = this.success(MApplication.getInstance().getString(2131820858));
            success.data = data;
            return success;
        }
        catch (final IOException ex) {
            ex.printStackTrace();
        }
        catch (final FileNotFoundException ex2) {
            ex2.printStackTrace();
        }
        catch (final DbException ex3) {
            ex3.printStackTrace();
        }
        return this.error();
    }
}
