package com.kingagroot.kingdraw.ui.user;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import android.view.MenuItem;
import android.os.Bundle;
import com.goodsrc.library.utils.ToastUtil;
import android.text.TextUtils;
import android.view.View;
import android.net.Uri;
import com.luck.picture.lib.entity.LocalMedia;
import android.content.Intent;
import org.xutils.http.body.RequestBody;
import java.util.List;
import org.xutils.http.body.MultipartBody;
import org.xutils.common.util.KeyValue;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;
import com.kingagroot.kingdraw.config.NetConfig$Account;
import android.content.DialogInterface$OnDismissListener;
import android.content.DialogInterface$OnClickListener;
import androidx.appcompat.app.AlertDialog$Builder;
import android.widget.ImageView;
import com.kingagroot.kingdraw.utils.ImageLoader;
import org.xutils.image.ImageOptions;
import com.kingagroot.kingdraw.base.MApplication;
import android.content.DialogInterface;
import java.io.File;
import org.xutils.http.RequestParams;
import com.kingagroot.kingdraw.http.NewHttpManager;
import com.goodsrc.library.http.RequestCallBack;
import com.kingagroot.kingdraw.config.NetConfig$BaseData;
import org.xutils.http.HttpMethod;
import com.kingagroot.kingdraw.http.NewHttpManager$Builder;
import com.kingagroot.kingdraw.ui.account.model.AccountUserModel;
import android.content.Context;
import com.luck.picture.lib.manager.PictureCacheManager;
import com.luck.picture.lib.engine.CropFileEngine;
import com.luck.picture.lib.engine.ImageEngine;
import com.kingagroot.kingdraw.utils.GlideEngine;
import com.luck.picture.lib.config.SelectMimeType;
import androidx.appcompat.app.AppCompatActivity;
import com.luck.picture.lib.basic.PictureSelector;
import com.goodsrc.library.utils.LanguageTool;
import com.goodsrc.library.core.LibraryApplication;
import com.yalantis.ucrop.UCrop$Options;
import android.widget.TextView;
import com.goodsrc.ui.library.widget.RoundAndCircleImageView;
import android.widget.EditText;
import androidx.appcompat.widget.AppCompatButton;
import android.view.View$OnClickListener;
import com.goodsrc.ui.library.ToolBarActivity;

public class UserInfoEditActivity extends ToolBarActivity implements View$OnClickListener
{
    private AppCompatButton btnUserInfo;
    int choice;
    private EditText etUserNickname;
    private String imgPath;
    private String imgUrl;
    private RoundAndCircleImageView ivUserHead;
    private String[] occupationList;
    private String[] sexList;
    private TextView tvUserCountry;
    private TextView tvUserOccupation;
    private TextView tvUserSex;
    
    public UserInfoEditActivity() {
        this.choice = 0;
    }
    
    private UCrop$Options buildOptions() {
        final UCrop$Options uCrop$Options = new UCrop$Options();
        uCrop$Options.setHideBottomControls(true);
        uCrop$Options.setFreeStyleCropEnabled(true);
        uCrop$Options.setShowCropFrame(true);
        uCrop$Options.setShowCropGrid(true);
        uCrop$Options.setCircleDimmedLayer(true);
        uCrop$Options.withAspectRatio(1.0f, 1.0f);
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
        PictureSelector.create((AppCompatActivity)this).openGallery(SelectMimeType.ofImage()).setImageEngine((ImageEngine)GlideEngine.createGlideEngine()).setCropEngine((CropFileEngine)new UserInfoEditActivity.UserInfoEditActivity$ImageFileCropEngine(this, (UserInfoEditActivity$1)null)).setLanguage(language).isDisplayCamera(true).setRequestedOrientation(-1).setImageSpanCount(4).setSelectionMode(1).isMaxSelectEnabledMask(true).isPageStrategy(true).isPreviewImage(true).isDirectReturnSingle(true).forResult(188);
    }
    
    private void clearCache() {
        PictureCacheManager.deleteAllCacheDirRefreshFile((Context)this);
        this.imgPath = "";
    }
    
    private AccountUserModel getNewUserModel() {
        final AccountUserModel accountUserModel = new AccountUserModel();
        accountUserModel.setNickName(this.etUserNickname.getText().toString());
        accountUserModel.setSex(this.tvUserSex.getText().toString());
        accountUserModel.setIndustry(this.tvUserOccupation.getText().toString());
        return accountUserModel;
    }
    
    private void getOccupationData() {
        final NewHttpManager$Builder newHttpManager$Builder = new NewHttpManager$Builder();
        newHttpManager$Builder.setHttpMethod(HttpMethod.GET);
        final NewHttpManager build = newHttpManager$Builder.build();
        final RequestParams params = build.params(NetConfig$BaseData.getApplyData());
        params.addBodyParameter("applyKey", "\u6240\u5c5e\u804c\u4e1a");
        build.request(params, (RequestCallBack)new UserInfoEditActivity$1(this));
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
    
    private void initView() {
        this.ivUserHead = (RoundAndCircleImageView)this.findViewById(2131296940);
        this.etUserNickname = (EditText)this.findViewById(2131296660);
        this.tvUserSex = (TextView)this.findViewById(2131297684);
        this.tvUserOccupation = (TextView)this.findViewById(2131297682);
        this.tvUserCountry = (TextView)this.findViewById(2131297680);
        this.btnUserInfo = (AppCompatButton)this.findViewById(2131296463);
        this.sexList = new String[] { this.getString(2131820934), this.getString(2131821013), this.getString(2131821530) };
        this.occupationList = new String[] { this.getString(2131820934), this.getString(2131821317), this.getString(2131821460), this.getString(2131821407) };
        this.ivUserHead.setOnClickListener((View$OnClickListener)this);
        this.tvUserSex.setOnClickListener((View$OnClickListener)this);
        this.tvUserOccupation.setOnClickListener((View$OnClickListener)this);
        this.btnUserInfo.setOnClickListener((View$OnClickListener)this);
        this.getOccupationData();
    }
    
    private void setUserInfoData() {
        final AccountUserModel accountUserModel = MApplication.getInstance().getAccountUserModel();
        ImageLoader.bind((ImageView)this.ivUserHead, accountUserModel.getHeadImgs(), new ImageOptions.Builder().setFailureDrawableId(2131231517).build());
        this.etUserNickname.setText((CharSequence)accountUserModel.getNickName());
        this.tvUserSex.setText((CharSequence)accountUserModel.getSex());
        this.tvUserOccupation.setText((CharSequence)accountUserModel.getIndustry());
        this.tvUserCountry.setText((CharSequence)accountUserModel.getCountryName());
        this.imgUrl = accountUserModel.getHeadImgs();
    }
    
    private void showDialog(final TextView textView, final String[] array) {
        for (int i = 0; i < array.length; ++i) {
            if (array[i].equals((Object)textView.getText().toString())) {
                this.choice = i;
            }
        }
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this);
        alertDialog$Builder.setSingleChoiceItems((CharSequence[])array, this.choice, (DialogInterface$OnClickListener)new _$$Lambda$UserInfoEditActivity$hFNnhm2s6IH_Ace8qz5dXpHST38(this, textView, array));
        alertDialog$Builder.setOnDismissListener((DialogInterface$OnDismissListener)_$$Lambda$UserInfoEditActivity$VAS30OpHCeCP2immDrR6IlhLjyo.INSTANCE);
        alertDialog$Builder.show();
    }
    
    private void updateUserInfo(final String s, final String s2, final String s3, final String s4) {
        final NewHttpManager build = new NewHttpManager$Builder().build();
        final RequestParams params = build.params(NetConfig$Account.userInfoUpdate());
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("nickName", (Object)s);
            jsonObject.put("headImgs", (Object)s2);
            jsonObject.put("sex", (Object)s3);
            jsonObject.put("industry", (Object)s4);
        }
        catch (final JSONException ex) {
            ex.printStackTrace();
        }
        params.addBodyParameter("", jsonObject.toString());
        build.request(params, (RequestCallBack)new UserInfoEditActivity$3(this));
    }
    
    private void uploadHead() {
        final NewHttpManager build = new NewHttpManager$Builder().build();
        final RequestParams params = build.params(NetConfig$Account.uploadHeadImg());
        final ArrayList list = new ArrayList();
        if (this.imgPath != null) {
            ((List)list).add((Object)new KeyValue("files", new File(this.imgPath)));
        }
        params.setRequestBody((RequestBody)new MultipartBody((List)list, "UTF-8"));
        build.request(params, (RequestCallBack)new UserInfoEditActivity$2(this));
    }
    
    protected void onActivityResult(final int n, final int n2, final Intent intent) {
        super.onActivityResult(n, n2, intent);
        if (n2 == -1 && intent != null && n == 188) {
            final ArrayList obtainSelectorList = PictureSelector.obtainSelectorList(intent);
            this.ivUserHead.setImageURI(Uri.parse(((LocalMedia)((List)obtainSelectorList).get(0)).getCutPath()));
            this.imgPath = ((LocalMedia)((List)obtainSelectorList).get(0)).getCutPath();
        }
    }
    
    public void onClick(final View view) {
        final TextView tvUserSex = this.tvUserSex;
        if (view == tvUserSex) {
            this.showDialog(tvUserSex, this.sexList);
        }
        else {
            final TextView tvUserOccupation = this.tvUserOccupation;
            if (view == tvUserOccupation) {
                this.showDialog(tvUserOccupation, this.occupationList);
            }
            else if (view == this.ivUserHead) {
                this.choosePic();
            }
            else if (view == this.btnUserInfo) {
                if (!TextUtils.isEmpty((CharSequence)this.etUserNickname.getText())) {
                    if (this.imgPath != null) {
                        this.uploadHead();
                    }
                    else {
                        this.updateUserInfo(this.etUserNickname.getText().toString(), this.imgUrl, this.tvUserSex.getText().toString(), this.tvUserOccupation.getText().toString());
                    }
                }
                else {
                    ToastUtil.showShort((CharSequence)this.getString(2131820804));
                }
            }
        }
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2131492937);
        this.setTitle((CharSequence)this.getString(2131821176));
        this.initView();
        this.setUserInfoData();
    }
    
    public boolean onOptionsItemSelected(final MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            if (MApplication.getInstance().getAccountUserModel().isEqual(this.getNewUserModel()) && TextUtils.isEmpty((CharSequence)this.imgPath)) {
                this.finish();
            }
            else {
                new MaterialAlertDialogBuilder((Context)this, 2131886086).setTitle(2131820936).setMessage(2131821500).setPositiveButton(2131820731, (DialogInterface$OnClickListener)new _$$Lambda$UserInfoEditActivity$7dhB2w_01_nDs_75W7VyDKRcRBA(this)).setNegativeButton(2131820661, (DialogInterface$OnClickListener)null).show();
            }
        }
        return false;
    }
}
