package com.kingagroot.kingdraw.core.view;

import com.kingagroot.kingdraw.core.tool.AlignTypeEnum;
import com.kingagroot.kingdraw.core.model.NodeDirectEnum;
import com.kingagroot.kingdraw.core.OnKingDrawViewListener;
import com.kingagroot.kingdraw.core.tool.ToolNameEnum;
import com.kingagroot.kingdraw.core.tool.GestureTool;
import android.graphics.Canvas;
import android.view.MotionEvent;
import com.kingagroot.kingdraw.core.tool.gesture.BaseGestureAction;
import com.kingagroot.kingdraw.core.OnCoreAvailableListener;
import com.kingagroot.kingdraw.core.model.ChemPropertyConfig;
import com.kingagroot.kingdraw.core.KingDrawConfig;
import android.view.TextureView$SurfaceTextureListener;
import android.view.View;
import android.view.ViewConfiguration;
import com.kingagroot.kingdraw.core.graphics.MagnifierCanvasHolder;
import com.kingagroot.kingdraw.core.graphics.CanvasHolder;
import android.text.TextUtils;
import com.kingagroot.kingdraw.core.graphics.BaseCanvasHolder;
import android.graphics.RectF;
import com.kingagroot.kingdraw.core.tool.ChirlityTool;
import com.kingagroot.kingdraw.core.data.ProtocolUtils;
import com.kingagroot.kingdraw.core.StructConvertListener;
import com.kingagroot.kingdraw.core.tool.Struct2NameTool;
import com.kingagroot.kingdraw.core.model.IUPACConvertModel;
import java.util.Iterator;
import java.util.List;
import com.kingagroot.kingdraw.core.model.ChirlityConvertModel;
import java.util.Map$Entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import android.util.AttributeSet;
import android.content.Context;
import com.kingagroot.kingdraw.core.tool.ToolController;
import com.kingagroot.kingdraw.core.model.PaletteConfigModel;
import com.kingagroot.kingdraw.core.OnCoreCallBack;
import com.kingagroot.kingdraw.core.OnBridgeCallBack;
import com.kingagroot.kingdraw.core.model.FormatValue;
import com.kingagroot.kingdraw.core.graphics.CanvasController;

public final class KingDrawView extends BasePaletteView implements DrawBaseView
{
    private CanvasController canvasController;
    private TextureDrawView drawView;
    private GridLineView gridLineView;
    private FormatValue initFormatValue;
    private MagnifierView magnifierView;
    private OnBridgeCallBack onBridgeCallBack;
    private OnCoreCallBack onCoreCallBack;
    private PaletteConfigModel paletteConfigModel;
    private ToolController toolController;
    private float touchDownX;
    private float touchDownY;
    private float touchSlop;
    
    public KingDrawView(final Context context) {
        this(context, null);
    }
    
    public KingDrawView(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public KingDrawView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.onCoreCallBack = new OnCoreCallBack();
        this.onBridgeCallBack = (OnBridgeCallBack)new OnBridgeCallBack() {
            private Map<String, String> findChirlityCache;
            private boolean isFindChirlitying;
            private boolean isRequesting;
            private Map<String, String> struct2NameCache;
            final KingDrawView this$0;
            
            {
                this.struct2NameCache = (Map<String, String>)new HashMap();
                this.isRequesting = false;
                this.isFindChirlitying = false;
                this.findChirlityCache = (Map<String, String>)new HashMap();
            }
            
            private void checkChirlityCache() {
                if (this.findChirlityCache.size() > 0) {
                    final ArrayList list = new ArrayList();
                    for (final Map$Entry map$Entry : this.findChirlityCache.entrySet()) {
                        final ChirlityConvertModel chirlityConvertModel = new ChirlityConvertModel();
                        chirlityConvertModel.Kid = (String)map$Entry.getKey();
                        chirlityConvertModel.StructJson = (String)map$Entry.getValue();
                        ((List)list).add((Object)chirlityConvertModel);
                    }
                    this.findChirlityCache.clear();
                    this.isFindChirlitying = true;
                    this.findChirlityAsync((List<ChirlityConvertModel>)list);
                }
            }
            
            private void findChirlityAsync(final List<ChirlityConvertModel> list) {
                new Thread((Runnable)new KingDrawView$2$2(this, (List)list)).start();
            }
            
            private void next() {
                if (!this.struct2NameCache.isEmpty()) {
                    this.isRequesting = true;
                    final ArrayList list = new ArrayList();
                    for (final Map$Entry map$Entry : this.struct2NameCache.entrySet()) {
                        final IUPACConvertModel iupacConvertModel = new IUPACConvertModel();
                        iupacConvertModel.kid = (String)map$Entry.getKey();
                        iupacConvertModel.json = (String)map$Entry.getValue();
                        ((List)list).add((Object)iupacConvertModel);
                    }
                    this.struct2NameCache.clear();
                    this.requestStruct2Name(IUPACConvertModel.parseConvertModels((List)list));
                }
            }
            
            private void requestStruct2Name(final String s) {
                new Struct2NameTool(this.this$0.getPaletteId()).dynamicStructToName(s, (StructConvertListener)new StructConvertListener(this) {
                    final KingDrawView$2 this$1;
                    
                    public void onError(final String s) {
                        this.this$1.isRequesting = false;
                        this.this$1.next();
                    }
                    
                    public void onSuccess() {
                        this.this$1.isRequesting = false;
                        this.this$1.next();
                    }
                });
            }
            
            public void SelectMolecule(final float n, final float n2) {
                this.this$0.setSelectMolecule(n, n2);
            }
            
            public String chemFormulaParse(String chemFormulaParse) {
                try {
                    if (Class.forName("com.kingagroot.kingdraw.core.data.ProtocolUtils") != null) {
                        chemFormulaParse = ProtocolUtils.chemFormulaParse(chemFormulaParse);
                        return chemFormulaParse;
                    }
                    return "";
                }
                catch (final ClassNotFoundException ex) {
                    return "";
                }
            }
            
            public String findChirlity(final String s) {
                return ChirlityTool.findChirlity(s);
            }
            
            public void findChirlityAsync(final String s) {
                final List convertJson = ChirlityConvertModel.parseConvertJson(s);
                if (convertJson != null) {
                    if (this.isFindChirlitying) {
                        for (final ChirlityConvertModel chirlityConvertModel : convertJson) {
                            this.findChirlityCache.put((Object)chirlityConvertModel.Kid, (Object)chirlityConvertModel.StructJson);
                        }
                    }
                    else {
                        this.isFindChirlitying = true;
                        this.findChirlityAsync((List<ChirlityConvertModel>)convertJson);
                    }
                }
            }
            
            public void struct2Name(final String s) {
                if (this.isRequesting) {
                    for (final IUPACConvertModel iupacConvertModel : IUPACConvertModel.parseConvertJson(s)) {
                        this.struct2NameCache.put((Object)iupacConvertModel.kid, (Object)iupacConvertModel.json);
                    }
                }
                else {
                    this.isRequesting = true;
                    this.requestStruct2Name(s);
                }
            }
        };
        this.initView();
    }
    
    public KingDrawView(final Context context, final AttributeSet set, final int n, final int n2) {
        super(context, set, n, n2);
        this.onCoreCallBack = new OnCoreCallBack();
        this.onBridgeCallBack = (OnBridgeCallBack)new OnBridgeCallBack() {
            private Map<String, String> findChirlityCache = new HashMap();
            private boolean isFindChirlitying = false;
            private boolean isRequesting = false;
            private Map<String, String> struct2NameCache = new HashMap();
            final KingDrawView this$0;
            
            private void checkChirlityCache() {
                if (this.findChirlityCache.size() > 0) {
                    final ArrayList list = new ArrayList();
                    for (final Map$Entry map$Entry : this.findChirlityCache.entrySet()) {
                        final ChirlityConvertModel chirlityConvertModel = new ChirlityConvertModel();
                        chirlityConvertModel.Kid = (String)map$Entry.getKey();
                        chirlityConvertModel.StructJson = (String)map$Entry.getValue();
                        ((List)list).add((Object)chirlityConvertModel);
                    }
                    this.findChirlityCache.clear();
                    this.isFindChirlitying = true;
                    this.findChirlityAsync((List<ChirlityConvertModel>)list);
                }
            }
            
            private void findChirlityAsync(final List<ChirlityConvertModel> list) {
                new Thread((Runnable)new KingDrawView$2$2(this, (List)list)).start();
            }
            
            private void next() {
                if (!this.struct2NameCache.isEmpty()) {
                    this.isRequesting = true;
                    final ArrayList list = new ArrayList();
                    for (final Map$Entry map$Entry : this.struct2NameCache.entrySet()) {
                        final IUPACConvertModel iupacConvertModel = new IUPACConvertModel();
                        iupacConvertModel.kid = (String)map$Entry.getKey();
                        iupacConvertModel.json = (String)map$Entry.getValue();
                        ((List)list).add((Object)iupacConvertModel);
                    }
                    this.struct2NameCache.clear();
                    this.requestStruct2Name(IUPACConvertModel.parseConvertModels((List)list));
                }
            }
            
            private void requestStruct2Name(final String s) {
                new Struct2NameTool(this.this$0.getPaletteId()).dynamicStructToName(s, (StructConvertListener)new StructConvertListener(this) {
                    final KingDrawView$2 this$1;
                    
                    public void onError(final String s) {
                        this.this$1.isRequesting = false;
                        this.this$1.next();
                    }
                    
                    public void onSuccess() {
                        this.this$1.isRequesting = false;
                        this.this$1.next();
                    }
                });
            }
            
            public void SelectMolecule(final float n, final float n2) {
                this.this$0.setSelectMolecule(n, n2);
            }
            
            public String chemFormulaParse(String chemFormulaParse) {
                try {
                    if (Class.forName("com.kingagroot.kingdraw.core.data.ProtocolUtils") != null) {
                        chemFormulaParse = ProtocolUtils.chemFormulaParse(chemFormulaParse);
                        return chemFormulaParse;
                    }
                    return "";
                }
                catch (final ClassNotFoundException ex) {
                    return "";
                }
            }
            
            public String findChirlity(final String s) {
                return ChirlityTool.findChirlity(s);
            }
            
            public void findChirlityAsync(final String s) {
                final List convertJson = ChirlityConvertModel.parseConvertJson(s);
                if (convertJson != null) {
                    if (this.isFindChirlitying) {
                        for (final ChirlityConvertModel chirlityConvertModel : convertJson) {
                            this.findChirlityCache.put((Object)chirlityConvertModel.Kid, (Object)chirlityConvertModel.StructJson);
                        }
                    }
                    else {
                        this.isFindChirlitying = true;
                        this.findChirlityAsync((List<ChirlityConvertModel>)convertJson);
                    }
                }
            }
            
            public void struct2Name(final String s) {
                if (this.isRequesting) {
                    for (final IUPACConvertModel iupacConvertModel : IUPACConvertModel.parseConvertJson(s)) {
                        this.struct2NameCache.put((Object)iupacConvertModel.kid, (Object)iupacConvertModel.json);
                    }
                }
                else {
                    this.isRequesting = true;
                    this.requestStruct2Name(s);
                }
            }
        };
        this.initView();
    }
    
    private void init() {
        final RectF bounds = new RectF(0.0f, 0.0f, (float)this.getMeasuredWidth(), (float)this.getMeasuredHeight());
        final CanvasHolder canvasHolder = this.drawView.getCanvasHolder();
        canvasHolder.setBounds(bounds);
        canvasHolder.setDrawView((DrawBaseView)this);
        this.canvasController.addCanvas((BaseCanvasHolder)canvasHolder);
        final MagnifierView magnifierView = this.magnifierView;
        if (magnifierView != null) {
            final MagnifierCanvasHolder canvas = magnifierView.getCanvas();
            canvas.setPaletteCanvas(this.drawView.getCanvasHolder());
            this.canvasController.addCanvas((BaseCanvasHolder)canvas);
        }
        if (TextUtils.isEmpty((CharSequence)this.paletteId)) {
            this.createPalette(this.canvasController, this.initFormatValue, this.paletteConfigModel);
            this.onCoreCallBack.setOnBridgeCallBack(this.onBridgeCallBack);
            this.setActionBack(this.onCoreCallBack);
        }
        if (this.toolController.getToolNameEnum() != null) {
            this.setTool(this.toolController.getToolNameEnum(), this.toolController.getToolParam());
        }
        final GridLineView gridLineView = this.gridLineView;
        if (gridLineView != null) {
            gridLineView.setCanvasHolder(this.drawView.getCanvasHolder());
            this.gridLineView.updata();
        }
    }
    
    private void initView() {
        final float n = (float)ViewConfiguration.get(this.getContext()).getScaledTouchSlop();
        this.touchSlop = n * n;
        this.gridLineView = new GridLineView(this.getContext());
        this.drawView = new TextureDrawView(this.getContext());
        this.addView((View)this.gridLineView);
        this.addView((View)this.drawView);
        this.canvasController = new CanvasController();
        this.toolController = new ToolController(this);
        this.drawView.setSurfaceTextureListener((TextureView$SurfaceTextureListener)new KingDrawView$1(this));
        KingDrawConfig.setMagnifierStatus(true);
        this.setShowGridLine(false);
    }
    
    public void addCoreAvailableListener(final OnCoreAvailableListener onCoreAvailableListener) {
        this.onCoreCallBack.addCoreAvailableListener(onCoreAvailableListener);
    }
    
    public void addGestureAction(final BaseGestureAction baseGestureAction) {
        this.toolController.addGestureAction(baseGestureAction);
    }
    
    public void closeMagnifierView() {
        final MagnifierView magnifierView = this.magnifierView;
        if (magnifierView != null) {
            this.canvasController.removeCanvas((BaseCanvasHolder)magnifierView.getCanvas());
            this.magnifierView.dismiss();
        }
        this.magnifierView = null;
        KingDrawConfig.setMagnifierStatus(false);
    }
    
    public void contactMagnifierView(final MagnifierView magnifierView) {
        if (magnifierView != null) {
            (this.magnifierView = magnifierView).contactPaletteView(this);
            final MagnifierCanvasHolder canvas = magnifierView.getCanvas();
            canvas.setPaletteCanvas(this.drawView.getCanvasHolder());
            this.canvasController.addCanvas((BaseCanvasHolder)canvas);
            KingDrawConfig.setMagnifierStatus(true);
        }
    }
    
    public void createPalette(final FormatValue formatValue, final PaletteConfigModel paletteConfigModel) {
        FormatValue initFormatValue = formatValue;
        if (formatValue == null) {
            initFormatValue = new FormatValue();
        }
        this.initFormatValue = initFormatValue;
        this.paletteConfigModel = paletteConfigModel;
        this.init();
    }
    
    public boolean dispatchTouchEvent(final MotionEvent motionEvent) {
        if (this.onInterceptTouchEvent(motionEvent)) {
            this.toolController.onTouchEvent(motionEvent);
        }
        else {
            this.onTouchEvent(motionEvent);
        }
        return true;
    }
    
    public void endDraw(final Canvas canvas) {
        this.drawView.endDraw(canvas);
    }
    
    public GestureTool getGestureTool() {
        if (this.toolController.getToolNameEnum() == ToolNameEnum.GGESTURE_TOOL && this.toolController.getTool() instanceof GestureTool) {
            return (GestureTool)this.toolController.getTool();
        }
        return null;
    }
    
    public OnKingDrawViewListener getOnKingDrawViewListener() {
        return this.onCoreCallBack.getKingDrawViewListener();
    }
    
    public ToolNameEnum getToolNameEnum() {
        return this.toolController.getToolNameEnum();
    }
    
    public boolean isAvailable() {
        return this.drawView.isAvailable();
    }
    
    public void onDestroy() {
        super.onDestroy();
        final CanvasController canvasController = this.canvasController;
        if (canvasController != null) {
            canvasController.onDestroy();
        }
        final GridLineView gridLineView = this.gridLineView;
        if (gridLineView != null) {
            gridLineView.onDestroy();
        }
    }
    
    public boolean onInterceptTouchEvent(final MotionEvent motionEvent) {
        return this.toolController.onInterceptTouchEvent(motionEvent);
    }
    
    public void onResume() {
        super.onResume();
        if (!TextUtils.isEmpty((CharSequence)this.paletteId)) {
            final GridLineView gridLineView = this.gridLineView;
            if (gridLineView != null && gridLineView.isEnable()) {
                this.gridLineView.updata();
            }
        }
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.touchDownX = motionEvent.getX();
            this.touchDownY = motionEvent.getY();
        }
        else if (motionEvent.getAction() == 2) {
            final float n = motionEvent.getX() - this.touchDownX;
            final float n2 = motionEvent.getY() - this.touchDownY;
            if (n * n + n2 * n2 < (double)this.touchSlop) {
                return true;
            }
        }
        if (this.magnifierView != null && this.toolController.getToolNameEnum() != ToolNameEnum.G_DRAG_TOOL && this.toolController.getToolNameEnum() != ToolNameEnum.GDUPLICATETOOL) {
            this.magnifierView.onTouchEvent(motionEvent);
        }
        else {
            final MagnifierView magnifierView = this.magnifierView;
            if (magnifierView != null && magnifierView.isShow()) {
                this.magnifierView.dismiss();
            }
        }
        return super.onTouchEvent(motionEvent);
    }
    
    public void setGridlinesStatus(final boolean enable) {
        this.gridLineView.setEnable(enable);
    }
    
    public void setOnKingDrawViewListener(final OnKingDrawViewListener kingDrawViewListener) {
        this.onCoreCallBack.setKingDrawViewListener(kingDrawViewListener);
    }
    
    public void setShowGridLine(final boolean enable) {
        final GridLineView gridLineView = this.gridLineView;
        if (gridLineView != null) {
            gridLineView.setEnable(enable);
        }
    }
    
    public boolean setTool(final ToolNameEnum toolNameEnum, final String s) {
        this.toolController.setTool(toolNameEnum, s);
        if (toolNameEnum == ToolNameEnum.GDUPLICATETOOL) {
            return super.setTool(ToolNameEnum.G_DRAG_TOOL, (String)null);
        }
        return super.setTool(toolNameEnum, s);
    }
    
    public Canvas startDraw() {
        return this.drawView.startDraw();
    }
    
    public void updataNotification() {
        final GridLineView gridLineView = this.gridLineView;
        if (gridLineView != null) {
            gridLineView.updata();
        }
    }
}
