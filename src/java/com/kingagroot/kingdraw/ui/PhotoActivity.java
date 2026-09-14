package com.kingagroot.kingdraw.ui;

import com.hjq.permissions.OnPermissionCallback;
import android.content.Context;
import com.hjq.permissions.XXPermissions;
import android.os.Bundle;
import java.util.Objects;
import com.luck.picture.lib.entity.LocalMedia;
import android.content.Intent;
import com.goodsrc.library.utils.ToastUtil;
import java.util.List;
import android.view.MotionEvent;
import android.view.View;
import android.database.Cursor;
import android.text.TextUtils;
import android.graphics.BitmapFactory;
import android.app.Activity;
import com.yalantis.ucrop.UCropImageEngine;
import com.yalantis.ucrop.UCrop;
import java.io.OutputStream;
import android.content.ContentResolver;
import java.io.IOException;
import android.provider.MediaStore$Images$Media;
import com.kingagroot.kingdraw.base.MApplication;
import android.os.Environment;
import android.os.Build$VERSION;
import android.content.ContentValues;
import android.graphics.Bitmap$CompressFormat;
import android.view.View$OnTouchListener;
import com.otaliastudios.cameraview.CameraListener;
import androidx.lifecycle.LifecycleOwner;
import android.view.animation.ScaleAnimation;
import com.luck.picture.lib.engine.CropFileEngine;
import com.luck.picture.lib.engine.ImageEngine;
import com.kingagroot.kingdraw.utils.GlideEngine;
import com.luck.picture.lib.config.SelectMimeType;
import androidx.appcompat.app.AppCompatActivity;
import com.luck.picture.lib.basic.PictureSelector;
import com.goodsrc.library.utils.LanguageTool;
import com.goodsrc.library.core.LibraryApplication;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Rect;
import android.graphics.Canvas;
import android.graphics.Bitmap$Config;
import com.kingagroot.kingdraw.utils.BitmapUtils;
import java.io.File;
import com.kingagroot.kingdraw.config.FileConfig;
import android.graphics.Matrix;
import com.yalantis.ucrop.UCrop$Options;
import com.otaliastudios.cameraview.PictureResult;
import android.net.Uri;
import android.widget.ImageButton;
import com.otaliastudios.cameraview.CameraView;
import android.widget.Button;
import android.graphics.Bitmap;
import android.view.animation.Animation;
import android.view.View$OnClickListener;
import com.goodsrc.ui.library.BaseActivity;

public class PhotoActivity extends BaseActivity implements View$OnClickListener
{
    public static String RESULT_DATA = "RESULT_DATA";
    private Animation animation;
    private Bitmap bitmap;
    private Button btnTakePhoto;
    private CameraView camera;
    int height;
    private ImageButton ibtPhotoAlbum;
    private ImageButton ibtPhotoBack;
    private String imgPath;
    private Uri picUri;
    int width;
    
    public PhotoActivity() {
        this.width = 640;
        this.height = 448;
        this.animation = null;
    }
    
    private void bitmapWhiteB(Bitmap bitmap) {
        final float min = Math.min(this.width / (float)bitmap.getWidth(), this.height / (float)bitmap.getHeight());
        final Matrix matrix = new Matrix();
        matrix.postScale(min, min);
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);
        final StringBuilder sb = new StringBuilder();
        sb.append(FileConfig.getOcrCachePath());
        sb.append(File.separator);
        sb.append(System.currentTimeMillis());
        sb.append("fitWhite.jpeg");
        final String string = sb.toString();
        BitmapUtils.saveBitmap(string, bitmap);
        this.picUri = Uri.parse(string);
    }
    
    private void bitmapWhiteS(final Bitmap bitmap) {
        final Bitmap bitmap2 = Bitmap.createBitmap(this.width, this.height, Bitmap$Config.ARGB_8888);
        final Canvas canvas = new Canvas();
        canvas.setBitmap(bitmap2);
        canvas.drawColor(-1);
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF((float)((this.width >> 1) - (bitmap.getWidth() >> 1)), (float)((this.height >> 1) - (bitmap.getHeight() >> 1)), (float)((this.width >> 1) + (bitmap.getWidth() >> 1)), (float)((this.height >> 1) + (bitmap.getHeight() >> 1)));
        final Paint paint = new Paint();
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawBitmap(bitmap, rect, rectF, paint);
        final StringBuilder sb = new StringBuilder();
        sb.append(FileConfig.getOcrCachePath());
        sb.append(File.separator);
        sb.append(System.currentTimeMillis());
        sb.append("fitWhite.jpeg");
        final String string = sb.toString();
        BitmapUtils.saveBitmap(string, bitmap2);
        this.picUri = Uri.parse(string);
    }
    
    private UCrop$Options buildOptions() {
        final UCrop$Options uCrop$Options = new UCrop$Options();
        uCrop$Options.setHideBottomControls(true);
        uCrop$Options.setFreeStyleCropEnabled(true);
        uCrop$Options.setShowCropFrame(true);
        uCrop$Options.setShowCropGrid(true);
        uCrop$Options.withAspectRatio(10.0f, 7.0f);
        uCrop$Options.setMaxScaleMultiplier(100.0f);
        uCrop$Options.setCropOutputPathDir(this.getSandboxPath());
        return uCrop$Options;
    }
    
    private void choosePic() {
        int language;
        if (LibraryApplication.getLanguage().equals((Object)LanguageTool.SER_EN)) {
            language = 2;
        }
        else {
            language = 0;
        }
        PictureSelector.create((AppCompatActivity)this).openGallery(SelectMimeType.ofImage()).setImageEngine((ImageEngine)GlideEngine.createGlideEngine()).setCropEngine((CropFileEngine)new PhotoActivity.PhotoActivity$ImageFileCropEngine(this, (PhotoActivity$1)null)).setLanguage(language).isDisplayCamera(true).setRequestedOrientation(-1).setImageSpanCount(4).setSelectionMode(1).isMaxSelectEnabledMask(true).isPageStrategy(true).isPreviewImage(true).isDirectReturnSingle(true).forResult(188);
    }
    
    private String getSandboxPath() {
        final File file = new File(this.getExternalFilesDir("").getAbsolutePath(), "Sandbox");
        if (!file.exists()) {
            file.mkdirs();
        }
        final StringBuilder sb = new StringBuilder();
        sb.append(file.getAbsolutePath());
        sb.append(File.separator);
        return sb.toString();
    }
    
    private void initAnimation() {
        (this.animation = (Animation)new ScaleAnimation(1.0f, 0.5f, 1.0f, 0.5f, this.btnTakePhoto.getWidth() / 2.0f, this.btnTakePhoto.getHeight() / 2.0f)).setDuration(200L);
        this.animation.setFillAfter(true);
    }
    
    private void initView() {
        this.camera = (CameraView)this.findViewById(2131296469);
        this.ibtPhotoBack = (ImageButton)this.findViewById(2131296817);
        this.btnTakePhoto = (Button)this.findViewById(2131296461);
        this.ibtPhotoAlbum = (ImageButton)this.findViewById(2131296816);
        this.ibtPhotoBack.setOnClickListener((View$OnClickListener)this);
        this.btnTakePhoto.setOnClickListener((View$OnClickListener)this);
        this.ibtPhotoAlbum.setOnClickListener((View$OnClickListener)this);
        this.camera.setLifecycleOwner((LifecycleOwner)this);
        this.camera.addCameraListener((CameraListener)new PhotoActivity$1(this));
        this.btnTakePhoto.setOnTouchListener((View$OnTouchListener)new _$$Lambda$PhotoActivity$A2jbc9pUzy_tIZkPdiazrfDQ28I(this));
    }
    
    private boolean saveBitmapToPicture(final Bitmap bitmap, final String s) {
        if (bitmap == null) {
            return false;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append(s);
        sb.append(".");
        sb.append(Bitmap$CompressFormat.JPEG.name());
        final String string = sb.toString();
        final ContentValues contentValues = new ContentValues();
        final long n = System.currentTimeMillis() / 1000L;
        contentValues.put("_display_name", string);
        contentValues.put("mime_type", "image/jpeg");
        contentValues.put("_size", Integer.valueOf(1));
        contentValues.put("date_added", Long.valueOf(n));
        contentValues.put("date_modified", Long.valueOf(n));
        if (Build$VERSION.SDK_INT >= 29) {
            final StringBuilder sb2 = new StringBuilder();
            sb2.append(System.currentTimeMillis());
            sb2.append("");
            contentValues.put("datetaken", sb2.toString());
            contentValues.put("relative_path", Environment.DIRECTORY_PICTURES);
        }
        else {
            final StringBuilder sb3 = new StringBuilder();
            sb3.append(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath());
            sb3.append(File.separator);
            sb3.append(string);
            contentValues.put("_data", sb3.toString());
        }
        final ContentResolver contentResolver = MApplication.getInstance().getContentResolver();
        final Uri insert = contentResolver.insert(MediaStore$Images$Media.EXTERNAL_CONTENT_URI, contentValues);
        if (insert != null) {
            this.picUri = insert;
            try {
                final OutputStream openOutputStream = contentResolver.openOutputStream(insert);
                if (openOutputStream != null) {
                    bitmap.compress(Bitmap$CompressFormat.JPEG, 100, openOutputStream);
                    openOutputStream.flush();
                    openOutputStream.close();
                }
                return true;
            }
            catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }
    
    private void startCrop() {
        if (this.bitmap != null && this.picUri != null) {
            final StringBuilder sb = new StringBuilder();
            sb.append(FileConfig.getOcrCachePath());
            sb.append(File.separator);
            sb.append("IMG_CROP_");
            sb.append(System.currentTimeMillis());
            sb.append(".jpeg");
            final String string = sb.toString();
            final File parentFile = new File(string).getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            final UCrop withOptions = UCrop.of(this.picUri, Uri.fromFile(new File(string))).withOptions(this.buildOptions());
            withOptions.setImageEngine((UCropImageEngine)new PhotoActivity$2(this));
            withOptions.start((Activity)this);
        }
    }
    
    private void takeSuccess(final PictureResult pictureResult) {
        final byte[] data = pictureResult.getData();
        if (data.length <= 0) {
            return;
        }
        final Bitmap decodeByteArray = BitmapFactory.decodeByteArray(data, 0, data.length);
        if (decodeByteArray == null) {
            return;
        }
        final Matrix matrix = new Matrix();
        matrix.postRotate((float)pictureResult.getRotation());
        this.saveImageToGallery(this.bitmap = Bitmap.createBitmap(decodeByteArray, 0, 0, decodeByteArray.getWidth(), decodeByteArray.getHeight(), matrix, true));
    }
    
    public String getPath(final Uri uri) {
        String s;
        if (!TextUtils.isEmpty((CharSequence)uri.getAuthority())) {
            final Cursor query = this.getContentResolver().query(uri, new String[] { "_data" }, (String)null, (String[])null, (String)null);
            if (query == null) {
                return null;
            }
            query.moveToFirst();
            s = query.getString(query.getColumnIndex("_data"));
            query.close();
        }
        else {
            s = uri.getPath();
        }
        return s;
    }
    
    public void onActivityResult(final int n, final int n2, Intent intent) {
        super.onActivityResult(n, n2, intent);
        if (n2 == -1 && intent != null) {
            if (n == 188) {
                this.imgPath = ((LocalMedia)((List)PictureSelector.obtainSelectorList(intent)).get(0)).getCutPath();
            }
            else if (n == 69) {
                this.imgPath = this.getPath((Uri)Objects.requireNonNull((Object)UCrop.getOutput(intent)));
            }
            if (this.imgPath != null) {
                intent = new Intent();
                intent.putExtra(PhotoActivity.RESULT_DATA, this.imgPath);
                this.setResult(-1, intent);
                this.finish();
            }
        }
    }
    
    public void onClick(final View view) {
        if (view == this.ibtPhotoBack) {
            this.finish();
        }
        else if (view == this.btnTakePhoto) {
            this.camera.takePicture();
        }
        else if (view == this.ibtPhotoAlbum) {
            this.choosePic();
        }
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.requestWindowFeature(1);
        this.getWindow().setFlags(1024, 1024);
        this.setContentView(2131492924);
        this.initView();
    }
    
    public void onWindowFocusChanged(final boolean b) {
        if (b) {
            this.initAnimation();
        }
    }
    
    public void saveImageToGallery(final Bitmap bitmap) {
        final XXPermissions with = XXPermissions.with((Context)this);
        if (Build$VERSION.SDK_INT >= 33) {
            with.permission(new String[] { "android.permission.READ_MEDIA_IMAGES" });
        }
        else {
            with.permission(new String[] { "android.permission.WRITE_EXTERNAL_STORAGE" });
        }
        with.request((OnPermissionCallback)new _$$Lambda$PhotoActivity$Fdhx7GQE5gimep0YCknfBip0ZYY(this, bitmap));
    }
}
