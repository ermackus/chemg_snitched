package com.kingagroot.kingdraw.interfaces.impl;

import org.xutils.db.Selector;
import java.util.Iterator;
import org.xutils.ex.DbException;
import com.kingagroot.kingdraw.utils.FileTagUtils;
import com.kingagroot.kingdraw.model.FolderFileModel;
import java.util.ArrayList;
import com.kingagroot.kingdraw.model.GTagModel;
import java.util.List;
import com.kingagroot.kingdraw.interfaces.KingDrawFileDataI;

public class KingDrawFileDataImpl extends BaseDBImpl implements KingDrawFileDataI
{
    public List<Long> getDrawFileCountByTags(final List<GTagModel> list) {
        final ArrayList list2 = new ArrayList();
        for (final GTagModel gTagModel : list) {
            Long value = null;
            try {
                try {
                    final Selector selector = this.db.selector((Class)FolderFileModel.class);
                    final StringBuilder sb = new StringBuilder();
                    sb.append("%");
                    sb.append(FileTagUtils.tagFormat(gTagModel.getName()));
                    sb.append("%");
                    selector.where("Tags", "LIKE", (Object)sb.toString()).count();
                }
                finally {}
            }
            catch (final DbException ex) {
                ex.printStackTrace();
                value = 0L;
            }
            ((List)list2).add((Object)value);
            continue;
            ((List)list2).add((Object)0L);
        }
        return (List<Long>)list2;
    }
}
