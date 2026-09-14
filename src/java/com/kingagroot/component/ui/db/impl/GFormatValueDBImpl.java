package com.kingagroot.component.ui.db.impl;

import org.xutils.common.util.KeyValue;
import java.util.List;
import org.xutils.db.sqlite.WhereBuilder;
import com.kingagroot.component.ui.view.OperationState;
import org.xutils.db.table.TableEntity;
import org.xutils.DbManager;
import com.kingagroot.component.ui.widget.richinput.GFontStyleEnum;
import com.kingagroot.component.ui.widget.richinput.GFontFamilyEnum;
import com.kingagroot.component.ui.model.GFormatValue;
import org.xutils.ex.DbException;
import com.kingagroot.component.ui.model.GDocumentTypeEnum;
import com.kingagroot.component.ui.db.GFormatValueDBI;

public class GFormatValueDBImpl extends BaseDBImpl implements GFormatValueDBI
{
    public static final String GFORMATVALUE_DB_NAME = "GFormatValueDB.db";
    private static final int GFORMATVALUE_VERSION_DB = 4;
    
    public GFormatValueDBImpl() {
        super("GFormatValueDB.db", 4);
    }
    
    private void checkDBData() {
        try {
            if (this.readerFormatForType(GDocumentTypeEnum.KingDraw\u683c\u5f0f) == null) {
                this.db.save((Object)this.getKingDoc());
            }
            if (this.readerFormatForType(GDocumentTypeEnum.ACS96\u683c\u5f0f) == null) {
                this.db.save((Object)this.get96Doc());
            }
            if (this.readerFormatForType(GDocumentTypeEnum.\u7528\u6237\u81ea\u5b9a\u4e49\u683c\u5f0f) == null) {
                final GFormatValue kingDoc = this.getKingDoc();
                kingDoc.setId(3);
                kingDoc.setDocumentType(GDocumentTypeEnum.\u7528\u6237\u81ea\u5b9a\u4e49\u683c\u5f0f.getCode());
                kingDoc.setNormal(false);
                this.db.save((Object)kingDoc);
            }
        }
        catch (final DbException ex) {
            ex.printStackTrace();
        }
    }
    
    private GFormatValue get96Doc() {
        final GFormatValue gFormatValue = new GFormatValue();
        gFormatValue.setId(2);
        gFormatValue.setDocumentType(GDocumentTypeEnum.ACS96\u683c\u5f0f.getCode());
        gFormatValue.setProof(true);
        gFormatValue.setZoomer(true);
        gFormatValue.setFixedLength("0.508");
        gFormatValue.setSpacing(18);
        gFormatValue.setLineWidth("0.021");
        gFormatValue.setBoldWidth("0.071");
        gFormatValue.setMarginWidth("0.056");
        gFormatValue.setHashSpacing("0.088");
        gFormatValue.setChainsAngle(120);
        gFormatValue.setAtomFontName(GFontFamilyEnum.Arial.getFontFamily());
        gFormatValue.setAtomFontSize(10);
        gFormatValue.setAtomFontStyle(GFontStyleEnum.Regular.getCode());
        gFormatValue.setTextFontName(GFontFamilyEnum.Arial.getFontFamily());
        gFormatValue.setTextFontSize(10);
        gFormatValue.setTextFontStyle(GFontStyleEnum.Regular.getCode());
        return gFormatValue;
    }
    
    public GFormatValue getKingDoc() {
        final GFormatValue gFormatValue = new GFormatValue();
        gFormatValue.setId(1);
        gFormatValue.setDocumentType(GDocumentTypeEnum.KingDraw\u683c\u5f0f.getCode());
        gFormatValue.setProof(true);
        gFormatValue.setZoomer(true);
        gFormatValue.setFixedLength("0.78");
        gFormatValue.setSpacing(12);
        gFormatValue.setLineWidth("0.0312");
        gFormatValue.setBoldWidth("0.0936");
        gFormatValue.setMarginWidth("0.0312");
        gFormatValue.setHashSpacing("0.0624");
        gFormatValue.setChainsAngle(120);
        gFormatValue.setAtomFontName(GFontFamilyEnum.Arial.getFontFamily());
        gFormatValue.setAtomFontSize(10);
        gFormatValue.setAtomFontStyle(GFontStyleEnum.Regular.getCode());
        gFormatValue.setTextFontName(GFontFamilyEnum.Arial.getFontFamily());
        gFormatValue.setTextFontSize(10);
        gFormatValue.setTextFontStyle(GFontStyleEnum.Regular.getCode());
        gFormatValue.setNormal(true);
        return gFormatValue;
    }
    
    public GFormatValue getUserDoc() {
        final GFormatValue kingDoc = this.getKingDoc();
        kingDoc.setId(3);
        kingDoc.setDocumentType(GDocumentTypeEnum.\u7528\u6237\u81ea\u5b9a\u4e49\u683c\u5f0f.getCode());
        kingDoc.setNormal(false);
        return kingDoc;
    }
    
    public boolean initFormatData() {
        if (!this.isTableExist((Class)GFormatValue.class)) {
            try {
                final GFormatValue get96Doc = this.get96Doc();
                final GFormatValue kingDoc = this.getKingDoc();
                this.db.save((Object)get96Doc);
                this.db.save((Object)kingDoc);
                kingDoc.setId(3);
                kingDoc.setDocumentType(GDocumentTypeEnum.\u7528\u6237\u81ea\u5b9a\u4e49\u683c\u5f0f.getCode());
                kingDoc.setNormal(false);
                this.db.save((Object)kingDoc);
            }
            catch (final DbException ex) {
                ex.printStackTrace();
                return false;
            }
        }
        return true;
    }
    
    protected void onDBUpgrade(final DbManager dbManager, final int n, final int n2) {
        super.onDBUpgrade(dbManager, n, n2);
        if (n < 4) {
            try {
                GFormatValue userDoc;
                if ((userDoc = (GFormatValue)dbManager.selector((Class)GFormatValue.class).where("documentType", "=", (Object)GDocumentTypeEnum.\u7528\u6237\u81ea\u5b9a\u4e49\u683c\u5f0f.getCode()).findFirst()) == null) {
                    userDoc = this.getUserDoc();
                }
                dbManager.delete((Class)GFormatValue.class, WhereBuilder.b("documentType", "=", (Object)GDocumentTypeEnum.\u7528\u6237\u81ea\u5b9a\u4e49\u683c\u5f0f.getCode()));
                dbManager.save((Object)userDoc);
                GFormatValue kingDoc;
                if ((kingDoc = (GFormatValue)dbManager.selector((Class)GFormatValue.class).where("documentType", "=", (Object)GDocumentTypeEnum.KingDraw\u683c\u5f0f.getCode()).findFirst()) == null) {
                    kingDoc = this.getKingDoc();
                }
                dbManager.delete((Class)GFormatValue.class, WhereBuilder.b("documentType", "=", (Object)GDocumentTypeEnum.KingDraw\u683c\u5f0f.getCode()));
                dbManager.save((Object)kingDoc);
                GFormatValue get96Doc;
                if ((get96Doc = (GFormatValue)dbManager.selector((Class)GFormatValue.class).where("documentType", "=", (Object)GDocumentTypeEnum.ACS96\u683c\u5f0f.getCode()).findFirst()) == null) {
                    get96Doc = this.get96Doc();
                }
                dbManager.delete((Class)GFormatValue.class, WhereBuilder.b("documentType", "=", (Object)GDocumentTypeEnum.ACS96\u683c\u5f0f.getCode()));
                dbManager.save((Object)get96Doc);
            }
            catch (final DbException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public List<GFormatValue> readAllFormat() {
        try {
            return (List<GFormatValue>)this.db.findAll((Class)GFormatValue.class);
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public GFormatValue readNormalFormat() {
        try {
            GFormatValue kingDoc;
            if ((kingDoc = (GFormatValue)this.db.selector((Class)GFormatValue.class).where("isNormal", "=", (Object)1).findFirst()) == null) {
                this.checkDBData();
                kingDoc = this.getKingDoc();
            }
            return kingDoc;
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public GFormatValue readerFormatForType(final GDocumentTypeEnum gDocumentTypeEnum) {
        try {
            return (GFormatValue)this.db.selector((Class)GFormatValue.class).where("documentType", "=", (Object)gDocumentTypeEnum.getCode()).findFirst();
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public boolean setNormalFormat(final GDocumentTypeEnum gDocumentTypeEnum) {
        try {
            this.db.update((Class)GFormatValue.class, WhereBuilder.b("isNormal", "=", (Object)1), new KeyValue[] { new KeyValue("isNormal", (Object)false) });
            this.db.update((Class)GFormatValue.class, WhereBuilder.b("documentType", "=", (Object)gDocumentTypeEnum.getCode()), new KeyValue[] { new KeyValue("isNormal", (Object)true) });
            return true;
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public boolean updateFormat(final GFormatValue gFormatValue) {
        try {
            this.db.saveOrUpdate((Object)gFormatValue);
            return true;
        }
        catch (final DbException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
