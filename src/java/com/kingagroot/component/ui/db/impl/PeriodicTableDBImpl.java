package com.kingagroot.component.ui.db.impl;

import org.xutils.common.util.KeyValue;
import com.kingagroot.component.ui.model.GAtom3D;
import org.xutils.db.Selector;
import org.xutils.db.sqlite.WhereBuilder;
import com.kingagroot.component.ui.model.GAtom;
import com.kingagroot.component.ui.view.OperationState;
import org.xutils.db.table.TableEntity;
import org.xutils.ex.DbException;
import com.goodsrc.library.utils.GsonUtil;
import com.kingagroot.component.ui.model.GAtomData;
import java.util.ArrayList;
import java.util.List;
import org.xutils.DbManager;
import com.kingagroot.component.ui.db.PeriodicTableDBI;

public class PeriodicTableDBImpl extends BaseDBImpl implements PeriodicTableDBI
{
    public static final String GATOM_DB_NAME = "GatomDBV210118.db";
    private static final int GATOM_VERSION_DB = 1;
    
    public PeriodicTableDBImpl() {
        super("GatomDBV210118.db", 1);
    }
    
    public List<String> atomDatas() {
        try {
            final ArrayList list = new ArrayList();
            final List all = this.db.findAll((Class)GAtomData.class);
            final int n = all.size() / 8;
            for (int i = 0; i < 8; ++i) {
                if (i == 7) {
                    ((List)list).add((Object)GsonUtil.toJson((Object)all.subList(i * n, all.size())));
                }
                else {
                    final int n2 = i * n;
                    ((List)list).add((Object)GsonUtil.toJson((Object)all.subList(n2, n2 + n)));
                }
            }
            return (List<String>)list;
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public GAtom findAtomByName(final String s) {
        try {
            return (GAtom)this.db.selector((Class)GAtom.class).where(WhereBuilder.b("name", "=", (Object)s)).findFirst();
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public List<GAtom> findPeriodicTable(final String s) {
        try {
            final Selector selector = this.db.selector((Class)GAtom.class);
            final StringBuilder sb = new StringBuilder();
            sb.append("%");
            sb.append(s);
            sb.append("%");
            final Selector where = selector.where("nameZh", "LIKE", (Object)sb.toString());
            final StringBuilder sb2 = new StringBuilder();
            sb2.append("%");
            sb2.append(s);
            sb2.append("%");
            final Selector or = where.or("name", "LIKE", (Object)sb2.toString());
            final StringBuilder sb3 = new StringBuilder();
            sb3.append("%");
            sb3.append(s);
            sb3.append("%");
            Object all;
            if ((all = or.or("gIndex", "LIKE", (Object)sb3.toString()).findAll()) == null) {
                all = new ArrayList();
            }
            return (List<GAtom>)all;
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return (List<GAtom>)new ArrayList();
        }
    }
    
    public String get3Djson() {
        try {
            return GsonUtil.toJson((Object)this.db.findAll((Class)GAtom3D.class));
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return "";
        }
    }
    
    public List<GAtom> getCollectPeriodicTable() {
        try {
            this.db.findAll((Class)GAtom.class);
            this.isTableExist((Class)GAtom.class);
            Object all;
            if ((all = this.db.selector((Class)GAtom.class).where("isCollect", "=", (Object)1).orderBy("gIndex", false).findAll()) == null) {
                all = new ArrayList();
            }
            return (List<GAtom>)all;
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return (List<GAtom>)new ArrayList();
        }
    }
    
    public boolean setCollect(int n, final boolean b) {
        try {
            final DbManager db = this.db;
            final WhereBuilder b2 = WhereBuilder.b("gIndex", "=", (Object)n);
            if (b) {
                n = 1;
            }
            else {
                n = 0;
            }
            db.update((Class)GAtom.class, b2, new KeyValue[] { new KeyValue("isCollect", (Object)n) });
            return true;
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
