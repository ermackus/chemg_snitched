package com.kingagroot.component.ui.db.impl;

import android.database.Cursor;
import org.xutils.common.util.IOUtil;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import com.kingagroot.kingdraw.core.data.ProtocolTypeEnum;
import java.io.File;
import com.kingagroot.component.ui.UIComponentHelper;
import android.util.Base64;
import com.kingagroot.component.ui.view.OperationState;
import org.xutils.ex.DbException;
import org.xutils.db.table.TableEntity;
import org.xutils.DbManager;

public class GSGroupModeBaseDB extends BaseDBImpl
{
    protected String getKdxFile(final int n) {
        try {
            final StringBuilder sb = new StringBuilder();
            sb.append("SELECT kdxFileBytes FROM \"GSGroupModel\" WHERE rowid = ");
            sb.append(n);
            final Cursor execQuery = this.db.execQuery(sb.toString());
            if (execQuery != null) {
                try {
                    if (execQuery.moveToNext()) {
                        final String string = execQuery.getString(0);
                        try {
                            final byte[] decode = Base64.decode(string, 2);
                            final String fileCachePath = UIComponentHelper.getFileCachePath();
                            final StringBuilder sb2 = new StringBuilder();
                            sb2.append(System.currentTimeMillis());
                            sb2.append("");
                            final String string2 = sb2.toString();
                            final StringBuilder sb3 = new StringBuilder();
                            sb3.append(fileCachePath);
                            sb3.append(File.separator);
                            sb3.append(string2);
                            sb3.append(ProtocolTypeEnum.KDX.extension);
                            final String string3 = sb3.toString();
                            final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream((OutputStream)new FileOutputStream(new File(string3)));
                            bufferedOutputStream.write(decode, 0, decode.length);
                            bufferedOutputStream.close();
                            IOUtil.closeQuietly(execQuery);
                            return string3;
                        }
                        catch (final Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    IOUtil.closeQuietly(execQuery);
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
        catch (final DbException ex2) {
            ex2.printStackTrace();
        }
        return null;
    }
}
