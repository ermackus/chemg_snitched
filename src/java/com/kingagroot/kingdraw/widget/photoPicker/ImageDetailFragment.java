package com.kingagroot.kingdraw.widget.photoPicker;

import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.os.Bundle;
import android.view.View;
import org.xutils.image.ImageOptions;
import com.github.chrisbanes.photoview.OnPhotoTapListener;
import com.bumptech.glide.request.target.Target;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.kingagroot.kingdraw.utils.ImageLoader;
import org.xutils.image.ImageOptions$Builder;
import android.widget.ProgressBar;
import com.github.chrisbanes.photoview.PhotoView;
import com.kingagroot.kingdraw.ui.BaseFragment;

public class ImageDetailFragment extends BaseFragment
{
    public static final String PATH_URL = "intent_path_url";
    private static int urlType;
    private PhotoView imge;
    private ProgressBar loading;
    private String pathUrl;
    
    private void initData() {
        final ImageOptions build = new ImageOptions$Builder().setFailureDrawableId(2131231528).build();
        String s;
        if (ImageDetailFragment.urlType == 0) {
            s = ImageLoader.getFormatUrl(this.pathUrl);
        }
        else {
            s = this.pathUrl;
        }
        ((RequestBuilder)Glide.with(this.requireActivity()).load(s).error(build.getFailureDrawable((ImageView)this.imge))).into((Target)new ImageDetailFragment$1(this, (ImageView)this.imge));
        this.imge.setOnPhotoTapListener((OnPhotoTapListener)new ImageDetailFragment$2(this));
    }
    
    private void initView(final View view) {
        this.imge = (PhotoView)view.findViewById(2131296893);
        this.loading = (ProgressBar)view.findViewById(2131297040);
    }
    
    public static ImageDetailFragment newInstance(final String s, final int urlType) {
        ImageDetailFragment.urlType = urlType;
        final ImageDetailFragment imageDetailFragment = new ImageDetailFragment();
        final Bundle arguments = new Bundle();
        arguments.putString("intent_path_url", s);
        imageDetailFragment.setArguments(arguments);
        return imageDetailFragment;
    }
    
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.pathUrl = this.getArguments().getString("intent_path_url", "");
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        final View inflate = layoutInflater.inflate(2131493049, (ViewGroup)null);
        this.initView(inflate);
        this.initData();
        return inflate;
    }
}
