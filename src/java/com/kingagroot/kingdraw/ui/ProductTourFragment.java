package com.kingagroot.kingdraw.ui;

import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

public class ProductTourFragment extends Fragment
{
    static final String LAYOUT_ID = "LAYOUT_ID";
    
    public static ProductTourFragment newInstance(final int n) {
        final ProductTourFragment productTourFragment = new ProductTourFragment();
        final Bundle arguments = new Bundle();
        arguments.putInt("LAYOUT_ID", n);
        productTourFragment.setArguments(arguments);
        return productTourFragment;
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        return layoutInflater.inflate(this.requireArguments().getInt("LAYOUT_ID", -1), viewGroup, false);
    }
}
