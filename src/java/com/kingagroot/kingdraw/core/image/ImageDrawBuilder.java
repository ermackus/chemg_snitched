package com.kingagroot.kingdraw.core.image;

import com.kingagroot.kingdraw.core.data.DataOutModel;
import com.kingagroot.kingdraw.core.data.ProtocolReader;
import com.kingagroot.kingdraw.core.utils.KDJsonUtil;
import android.text.TextUtils;
import java.io.IOException;
import java.io.OutputStream;
import java.io.FileOutputStream;
import android.graphics.Bitmap;
import java.io.File;
import com.kingagroot.kingdraw.core.KingDrawConfig;
import com.kingagroot.kingdraw.core.view.KingDrawView;
import com.kingagroot.kingdraw.core.model.FormatValue;
import android.graphics.Bitmap$CompressFormat;
import android.graphics.Bitmap$Config;

public class ImageDrawBuilder
{
    private int backgroundColor;
    private Bitmap$Config config;
    private DrawOption drawOption;
    private BaseImageDrawer drawer;
    private String fileContent;
    private String fileJson;
    private String fileName;
    private String filePath;
    private Bitmap$CompressFormat format;
    private FormatValue formatValue;
    private boolean isProof;
    private KingDrawView kingDrawView;
    private String protocolFilePath;
    private int quality;
    
    public ImageDrawBuilder() {
        this.config = Bitmap$Config.ARGB_8888;
        this.format = Bitmap$CompressFormat.PNG;
        this.backgroundColor = -1;
        this.formatValue = new FormatValue();
        this.quality = 100;
        this.isProof = false;
        this.drawOption = (DrawOption)new ImageFileDrawOption();
    }
    
    private static String createKDXImg(final Bitmap$CompressFormat bitmap$CompressFormat) {
        final File externalCacheDir = KingDrawConfig.getContext().getExternalCacheDir();
        if (!externalCacheDir.exists()) {
            externalCacheDir.mkdir();
        }
        final String absolutePath = externalCacheDir.getAbsolutePath();
        final StringBuilder sb = new StringBuilder();
        sb.append(System.currentTimeMillis());
        sb.append("");
        final String string = sb.toString();
        final StringBuilder sb2 = new StringBuilder();
        sb2.append(absolutePath);
        sb2.append(File.separator);
        sb2.append(string);
        sb2.append(".");
        sb2.append((Object)bitmap$CompressFormat);
        final String string2 = sb2.toString();
        final ImageFileDrawOption drawOption = new ImageFileDrawOption();
        final ImageDrawBuilder imageDrawBuilder = new ImageDrawBuilder();
        if (bitmap$CompressFormat == Bitmap$CompressFormat.PNG) {
            imageDrawBuilder.setBackgroundColor(0);
        }
        else {
            imageDrawBuilder.setBackgroundColor(-1);
        }
        imageDrawBuilder.setConfig(Bitmap$Config.ARGB_8888).setCompressFormat(bitmap$CompressFormat, 100).setProofStatus(false).setDrawOption((DrawOption)drawOption).createKDXImageDrawer();
        imageDrawBuilder.saveToFile(absolutePath, string);
        imageDrawBuilder.onDestroy();
        return string2;
    }
    
    public static String createKDXJpegImage() {
        return createKDXImg(Bitmap$CompressFormat.JPEG);
    }
    
    public static String createKDXPngImage() {
        return createKDXImg(Bitmap$CompressFormat.PNG);
    }
    
    public static ImageDrawBuilder createThumbImage(final float width, final float height) {
        final File externalCacheDir = KingDrawConfig.getContext().getExternalCacheDir();
        if (!externalCacheDir.exists()) {
            externalCacheDir.mkdir();
        }
        final String absolutePath = externalCacheDir.getAbsolutePath();
        final StringBuilder sb = new StringBuilder();
        sb.append(System.currentTimeMillis());
        sb.append("");
        final String string = sb.toString();
        final ThumbImageDrawOption drawOption = new ThumbImageDrawOption();
        drawOption.setMark(false);
        drawOption.setWidth(width);
        drawOption.setHeight(height);
        final ImageDrawBuilder imageDrawBuilder = new ImageDrawBuilder();
        imageDrawBuilder.setBackgroundColor(-1).setConfig(Bitmap$Config.ARGB_8888).setCompressFormat(Bitmap$CompressFormat.PNG, 100).setProofStatus(false).setDrawOption((DrawOption)drawOption).setFilePath(absolutePath, string).createKDXImageDrawer();
        return imageDrawBuilder;
    }
    
    private void saveBitmap(final String s, final Bitmap bitmap) throws IOException {
        final File file = new File(s);
        final File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        final FileOutputStream fileOutputStream = new FileOutputStream(file);
        bitmap.compress(this.format, this.quality, (OutputStream)fileOutputStream);
        fileOutputStream.close();
    }
    
    public KDXImgDrawer createKDXImageDrawer() {
        (this.drawer = (BaseImageDrawer)new KDXImgDrawer()).setDrawOption(this.drawOption);
        this.drawer.setConfig(this.config);
        this.drawer.setBackgroundColor(this.backgroundColor);
        return (KDXImgDrawer)this.drawer;
    }
    
    public Bitmap getDrawBitmap() {
        String s = null;
        Label_0174: {
            if (!TextUtils.isEmpty((CharSequence)this.fileJson)) {
                final FormatValue formatValue = KDJsonUtil.getFormatValue(this.fileJson);
                if (formatValue != null) {
                    this.formatValue = formatValue;
                }
                s = this.fileJson;
            }
            else {
                final KingDrawView kingDrawView = this.kingDrawView;
                if (kingDrawView != null) {
                    s = kingDrawView.getAllElementsJson();
                    this.formatValue = this.kingDrawView.getFormatValue();
                }
                else {
                    Label_0171: {
                        if (!TextUtils.isEmpty((CharSequence)this.protocolFilePath)) {
                            try {
                                final DataOutModel byPath = ProtocolReader.builder().setNeedKDJson(true).readByPath(this.protocolFilePath);
                                final FormatValue formatValue2 = KDJsonUtil.getFormatValue(byPath.josn);
                                if (formatValue2 != null) {
                                    this.formatValue = formatValue2;
                                }
                                s = byPath.josn;
                                break Label_0174;
                            }
                            catch (final IOException ex) {
                                ex.printStackTrace();
                                break Label_0171;
                            }
                        }
                        if (!TextUtils.isEmpty((CharSequence)this.fileContent)) {
                            final DataOutModel byString = ProtocolReader.builder().setNeedKDJson(true).readByString(this.fileContent);
                            final FormatValue formatValue3 = KDJsonUtil.getFormatValue(byString.josn);
                            if (formatValue3 != null) {
                                this.formatValue = formatValue3;
                            }
                            s = byString.josn;
                            break Label_0174;
                        }
                    }
                    s = "";
                }
            }
        }
        if (this.drawer == null) {
            (this.drawer = (BaseImageDrawer)new ImageDrawer(this.formatValue)).setDrawOption(this.drawOption);
            this.drawer.setConfig(this.config);
            this.drawer.setBackgroundColor(this.backgroundColor);
            this.drawer.setFileContent(s, this.formatValue);
        }
        return this.drawer.getDrawBitmap();
    }
    
    public BaseImageDrawer getDrawer() {
        return this.drawer;
    }
    
    public String getFilePath() {
        String s;
        if (this.filePath.endsWith(File.separator)) {
            final StringBuilder sb = new StringBuilder();
            sb.append(this.filePath);
            sb.append(this.fileName);
            sb.append(".");
            sb.append((Object)this.format);
            s = sb.toString();
        }
        else {
            final StringBuilder sb2 = new StringBuilder();
            sb2.append(this.filePath);
            sb2.append(File.separator);
            sb2.append(this.fileName);
            sb2.append(".");
            sb2.append((Object)this.format);
            s = sb2.toString();
        }
        return s;
    }
    
    public String getPaletteId() {
        final BaseImageDrawer drawer = this.drawer;
        if (drawer != null) {
            return drawer.getPaletteId();
        }
        return "";
    }
    
    public void onDestroy() {
        this.drawer.onDestroy();
        this.drawer = null;
    }
    
    public boolean save() {
        final boolean empty = TextUtils.isEmpty((CharSequence)this.filePath);
        boolean b2;
        final boolean b = b2 = false;
        if (!empty) {
            if (TextUtils.isEmpty((CharSequence)this.fileName)) {
                b2 = b;
            }
            else {
                final Bitmap drawBitmap = this.getDrawBitmap();
                if (drawBitmap == null) {
                    return false;
                }
                try {
                    this.saveBitmap(this.getFilePath(), drawBitmap);
                    if (drawBitmap != null && !drawBitmap.isRecycled()) {
                        drawBitmap.recycle();
                    }
                    b2 = true;
                }
                catch (final IOException ex) {
                    ex.printStackTrace();
                    b2 = b;
                }
            }
        }
        return b2;
    }
    
    public boolean saveToFile(final String s, final String s2) {
        this.setFilePath(s, s2);
        return this.save();
    }
    
    public ImageDrawBuilder setBackgroundColor(final int backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }
    
    public ImageDrawBuilder setCompressFormat(final Bitmap$CompressFormat format, final int quality) {
        this.format = format;
        this.quality = quality;
        return this;
    }
    
    public ImageDrawBuilder setConfig(final Bitmap$Config config) {
        this.config = config;
        return this;
    }
    
    public ImageDrawBuilder setDrawOption(final DrawOption drawOption) {
        this.drawOption = drawOption;
        return this;
    }
    
    public ImageDrawBuilder setDrawWidthFilePath(final String protocolFilePath) {
        this.protocolFilePath = protocolFilePath;
        this.fileContent = "";
        this.kingDrawView = null;
        return this;
    }
    
    public ImageDrawBuilder setDrawWithContent(final String fileContent) {
        this.fileContent = fileContent;
        this.kingDrawView = null;
        this.protocolFilePath = null;
        return this;
    }
    
    public ImageDrawBuilder setDrawWithJson(final String fileJson) {
        this.fileJson = fileJson;
        this.kingDrawView = null;
        this.protocolFilePath = null;
        return this;
    }
    
    public ImageDrawBuilder setDrawWithView(final KingDrawView kingDrawView) {
        this.kingDrawView = kingDrawView;
        this.fileContent = null;
        this.protocolFilePath = null;
        return this;
    }
    
    public ImageDrawBuilder setFilePath(final String filePath, final String fileName) {
        this.filePath = filePath;
        this.fileName = fileName;
        return this;
    }
    
    public ImageDrawBuilder setFormatValue(final FormatValue formatValue) {
        this.formatValue = formatValue;
        return this;
    }
    
    public ImageDrawBuilder setProofStatus(final boolean isProof) {
        this.isProof = isProof;
        return this;
    }
}
