package com.kingagroot.kingdraw.core.graphics.svg.utils;

import android.graphics.Paint$Style;
import android.graphics.DashPathEffect;
import android.graphics.PathEffect;
import android.graphics.Paint$Join;
import android.graphics.Paint$Cap;
import android.graphics.BlendMode;
import android.graphics.PathMeasure;
import java.util.Set;
import java.util.Collection;
import java.util.Locale;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.Xfermode;
import android.graphics.PorterDuffXfermode;
import android.graphics.PorterDuff$Mode;
import android.graphics.RadialGradient;
import android.graphics.LinearGradient;
import android.graphics.Shader$TileMode;
import android.graphics.Path$FillType;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.util.Log;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.graphics.Bitmap;
import com.kingagroot.kingdraw.core.graphics.svg.PreserveAspectRatio;
import android.graphics.RectF;
import java.util.ArrayList;
import java.util.Iterator;
import android.graphics.Path$Op;
import java.util.List;
import android.graphics.Path;
import android.graphics.Paint;
import android.os.Build$VERSION;
import android.graphics.Matrix;
import java.util.Stack;
import com.kingagroot.kingdraw.core.graphics.svg.SVGExternalFileResolver;
import android.graphics.Canvas;
import java.util.HashSet;
import java.util.regex.Pattern;

public class SVGAndroidRenderer
{
    private static final float BEZIER_ARC_FACTOR = 0.5522848f;
    private static final String DEFAULT_FONT_FAMILY = "serif";
    public static final float LUMINANCE_TO_ALPHA_BLUE = 0.0722f;
    public static final float LUMINANCE_TO_ALPHA_GREEN = 0.7151f;
    public static final float LUMINANCE_TO_ALPHA_RED = 0.2127f;
    private static final Pattern PATTERN_DOUBLE_SPACES;
    private static final Pattern PATTERN_END_SPACES;
    private static final Pattern PATTERN_LINE_BREAKS;
    private static final Pattern PATTERN_START_SPACES;
    private static final Pattern PATTERN_TABS;
    private static final Pattern PATTERN_TABS_OR_LINE_BREAKS;
    private static final boolean SUPPORTS_BLEND_MODE;
    private static final boolean SUPPORTS_FONT_HINTING;
    private static final boolean SUPPORTS_PAINT_FONT_FEATURE_SETTINGS;
    private static final boolean SUPPORTS_PAINT_FONT_VARIATION_SETTINGS;
    private static final boolean SUPPORTS_PAINT_LETTER_SPACING;
    private static final boolean SUPPORTS_PAINT_WORD_SPACING;
    private static final boolean SUPPORTS_PATH_OP;
    private static final boolean SUPPORTS_STROKED_UNDERLINES;
    private static final String TAG = "SVGAndroidRenderer";
    private static HashSet<String> supportedFeatures;
    private final Canvas canvas;
    private SVGBase document;
    private final float dpi;
    private SVGExternalFileResolver externalFileResolver;
    private Stack<Matrix> matrixStack;
    private Stack<SVGBase.SvgContainer> parentStack;
    private CSSParser.RuleMatchContext ruleMatchContext;
    private RendererState state;
    private Stack<RendererState> stateStack;
    
    static {
        final int sdk_INT = Build$VERSION.SDK_INT;
        final boolean b = true;
        SUPPORTS_FONT_HINTING = (sdk_INT >= 14);
        SUPPORTS_STROKED_UNDERLINES = (Build$VERSION.SDK_INT >= 17);
        SUPPORTS_PATH_OP = (Build$VERSION.SDK_INT >= 19);
        SUPPORTS_PAINT_FONT_FEATURE_SETTINGS = (Build$VERSION.SDK_INT >= 21);
        SUPPORTS_PAINT_LETTER_SPACING = (Build$VERSION.SDK_INT >= 21);
        SUPPORTS_PAINT_FONT_VARIATION_SETTINGS = (Build$VERSION.SDK_INT >= 26);
        SUPPORTS_BLEND_MODE = (Build$VERSION.SDK_INT >= 29);
        SUPPORTS_PAINT_WORD_SPACING = (Build$VERSION.SDK_INT >= 29 && b);
        PATTERN_TABS_OR_LINE_BREAKS = Pattern.compile("[\\n\\t]");
        PATTERN_TABS = Pattern.compile("\\t");
        PATTERN_LINE_BREAKS = Pattern.compile("\\n");
        PATTERN_START_SPACES = Pattern.compile("^\\s+");
        PATTERN_END_SPACES = Pattern.compile("\\s+$");
        PATTERN_DOUBLE_SPACES = Pattern.compile("\\s{2,}");
        SVGAndroidRenderer.supportedFeatures = null;
    }
    
    SVGAndroidRenderer(final Canvas canvas, final float dpi, final SVGExternalFileResolver externalFileResolver) {
        this.ruleMatchContext = null;
        this.canvas = canvas;
        this.dpi = dpi;
        this.externalFileResolver = externalFileResolver;
    }
    
    private void addObjectToClip(final SVGBase$GraphicsElement svgBase$GraphicsElement, final Path path, final Matrix matrix) {
        this.updateStyleForElement(this.state, (SVGBase$SvgElementBase)svgBase$GraphicsElement);
        if (!this.display()) {
            return;
        }
        if (!this.visible()) {
            return;
        }
        if (svgBase$GraphicsElement.transform != null) {
            matrix.preConcat(svgBase$GraphicsElement.transform);
        }
        Path path2;
        if (svgBase$GraphicsElement instanceof SVGBase$Rect) {
            path2 = this.makePathAndBoundingBox((SVGBase$Rect)svgBase$GraphicsElement);
        }
        else if (svgBase$GraphicsElement instanceof SVGBase$Circle) {
            path2 = this.makePathAndBoundingBox((SVGBase$Circle)svgBase$GraphicsElement);
        }
        else if (svgBase$GraphicsElement instanceof SVGBase$Ellipse) {
            path2 = this.makePathAndBoundingBox((SVGBase$Ellipse)svgBase$GraphicsElement);
        }
        else {
            if (!(svgBase$GraphicsElement instanceof SVGBase$PolyLine)) {
                return;
            }
            path2 = this.makePathAndBoundingBox((SVGBase$PolyLine)svgBase$GraphicsElement);
        }
        if (path2 == null) {
            return;
        }
        this.checkForClipPath((SVGBase$SvgElement)svgBase$GraphicsElement);
        path.setFillType(this.getClipRuleFromState());
        path.addPath(path2, matrix);
    }
    
    private void addObjectToClip(final SVGBase$Path svgBase$Path, final Path path, final Matrix matrix) {
        this.updateStyleForElement(this.state, (SVGBase$SvgElementBase)svgBase$Path);
        if (!this.display()) {
            return;
        }
        if (!this.visible()) {
            return;
        }
        if (svgBase$Path.transform != null) {
            matrix.preConcat(svgBase$Path.transform);
        }
        final Path path2 = new SVGAndroidRenderer.SVGAndroidRenderer$PathConverter(svgBase$Path.d).getPath();
        if (svgBase$Path.boundingBox == null) {
            svgBase$Path.boundingBox = this.calculatePathBounds(path2);
        }
        this.checkForClipPath((SVGBase$SvgElement)svgBase$Path);
        path.setFillType(this.getClipRuleFromState());
        path.addPath(path2, matrix);
    }
    
    private void addObjectToClip(final SVGBase.SvgObject svgObject, final boolean b, final Path path, final Matrix matrix) {
        if (!this.display()) {
            return;
        }
        this.clipStatePush();
        if (svgObject instanceof SVGBase$Use) {
            if (b) {
                this.addObjectToClip((SVGBase$Use)svgObject, path, matrix);
            }
            else {
                error("<use> elements inside a <clipPath> cannot reference another <use>", new Object[0]);
            }
        }
        else if (svgObject instanceof SVGBase$Path) {
            this.addObjectToClip((SVGBase$Path)svgObject, path, matrix);
        }
        else if (svgObject instanceof SVGBase$Text) {
            this.addObjectToClip((SVGBase$Text)svgObject, path, matrix);
        }
        else if (svgObject instanceof SVGBase$GraphicsElement) {
            this.addObjectToClip((SVGBase$GraphicsElement)svgObject, path, matrix);
        }
        else {
            error("Invalid %s element found in clipPath definition", svgObject.toString());
        }
        this.clipStatePop();
    }
    
    private void addObjectToClip(final SVGBase$Text svgBase$Text, final Path path, final Matrix matrix) {
        this.updateStyleForElement(this.state, (SVGBase$SvgElementBase)svgBase$Text);
        if (!this.display()) {
            return;
        }
        if (svgBase$Text.transform != null) {
            matrix.preConcat(svgBase$Text.transform);
        }
        final List x = svgBase$Text.x;
        final float n = 0.0f;
        float floatValueX;
        if (x != null && svgBase$Text.x.size() != 0) {
            floatValueX = ((SVGBase.Length)svgBase$Text.x.get(0)).floatValueX(this);
        }
        else {
            floatValueX = 0.0f;
        }
        float floatValueY;
        if (svgBase$Text.y != null && svgBase$Text.y.size() != 0) {
            floatValueY = ((SVGBase.Length)svgBase$Text.y.get(0)).floatValueY(this);
        }
        else {
            floatValueY = 0.0f;
        }
        float floatValueX2;
        if (svgBase$Text.dx != null && svgBase$Text.dx.size() != 0) {
            floatValueX2 = ((SVGBase.Length)svgBase$Text.dx.get(0)).floatValueX(this);
        }
        else {
            floatValueX2 = 0.0f;
        }
        float floatValueY2 = n;
        if (svgBase$Text.dy != null) {
            if (svgBase$Text.dy.size() == 0) {
                floatValueY2 = n;
            }
            else {
                floatValueY2 = ((SVGBase.Length)svgBase$Text.dy.get(0)).floatValueY(this);
            }
        }
        float n2 = floatValueX;
        if (this.state.style.textAnchor != Style.TextAnchor.Start) {
            float calculateTextWidth = this.calculateTextWidth((SVGBase$TextContainer)svgBase$Text);
            if (this.state.style.textAnchor == Style.TextAnchor.Middle) {
                calculateTextWidth /= 2.0f;
            }
            n2 = floatValueX - calculateTextWidth;
        }
        if (svgBase$Text.boundingBox == null) {
            final SVGAndroidRenderer.SVGAndroidRenderer$TextBoundsCalculator svgAndroidRenderer$TextBoundsCalculator = new SVGAndroidRenderer.SVGAndroidRenderer$TextBoundsCalculator(this, n2, floatValueY);
            this.enumerateTextSpans((SVGBase$TextContainer)svgBase$Text, (TextProcessor)svgAndroidRenderer$TextBoundsCalculator);
            svgBase$Text.boundingBox = new SVGBase.Box(svgAndroidRenderer$TextBoundsCalculator.bbox.left, svgAndroidRenderer$TextBoundsCalculator.bbox.top, svgAndroidRenderer$TextBoundsCalculator.bbox.width(), svgAndroidRenderer$TextBoundsCalculator.bbox.height());
        }
        this.checkForClipPath((SVGBase$SvgElement)svgBase$Text);
        final Path path2 = new Path();
        this.enumerateTextSpans((SVGBase$TextContainer)svgBase$Text, (TextProcessor)new SVGAndroidRenderer.SVGAndroidRenderer$PlainTextToPath(this, n2 + floatValueX2, floatValueY + floatValueY2, path2));
        path.setFillType(this.getClipRuleFromState());
        path.addPath(path2, matrix);
    }
    
    private void addObjectToClip(final SVGBase$Use svgBase$Use, final Path path, final Matrix matrix) {
        this.updateStyleForElement(this.state, (SVGBase$SvgElementBase)svgBase$Use);
        if (!this.display()) {
            return;
        }
        if (!this.visible()) {
            return;
        }
        if (svgBase$Use.transform != null) {
            matrix.preConcat(svgBase$Use.transform);
        }
        final SVGBase.SvgObject resolveIRI = svgBase$Use.document.resolveIRI(svgBase$Use.href);
        if (resolveIRI == null) {
            error("Use reference '%s' not found", svgBase$Use.href);
            return;
        }
        this.checkForClipPath((SVGBase$SvgElement)svgBase$Use);
        this.addObjectToClip(resolveIRI, false, path, matrix);
    }
    
    private static void arcTo(final float n, final float n2, float n3, float n4, final float n5, final boolean b, final boolean b2, final float n6, final float n7, final SVGBase.PathInterface pathInterface) {
        if (n == n6 && n2 == n7) {
            return;
        }
        if (n3 == 0.0f || n4 == 0.0f) {
            pathInterface.lineTo(n6, n7);
            return;
        }
        final float abs = Math.abs(n3);
        final float abs2 = Math.abs(n4);
        final double radians = Math.toRadians(n5 % 360.0);
        final double cos = Math.cos(radians);
        final double sin = Math.sin(radians);
        final double n8 = (n - n6) / 2.0;
        final double n9 = (n2 - n7) / 2.0;
        final double n10 = cos * n8 + sin * n9;
        final double n11 = -sin * n8 + n9 * cos;
        double n12 = abs * abs;
        double n13 = abs2 * abs2;
        final double n14 = n10 * n10;
        final double n15 = n11 * n11;
        final double n16 = n14 / n12 + n15 / n13;
        n4 = abs;
        n3 = abs2;
        if (n16 > 0.99999) {
            final double n17 = Math.sqrt(n16) * 1.00001;
            n4 = (float)(abs * n17);
            n3 = (float)(n17 * abs2);
            n12 = n4 * n4;
            n13 = n3 * n3;
        }
        final double n18 = -1.0;
        double n19;
        if (b == b2) {
            n19 = -1.0;
        }
        else {
            n19 = 1.0;
        }
        final double n20 = n12 * n15;
        final double n21 = n13 * n14;
        double n22;
        if ((n22 = (n12 * n13 - n20 - n21) / (n20 + n21)) < 0.0) {
            n22 = 0.0;
        }
        final double n23 = n19 * Math.sqrt(n22);
        final double n24 = n4;
        final double n25 = n3;
        final double n26 = n24 * n11 / n25 * n23;
        final double n27 = n23 * -(n25 * n10 / n24);
        final double n28 = (n + n6) / 2.0;
        final double n29 = (n2 + n7) / 2.0;
        final double n30 = (n10 - n26) / n24;
        final double n31 = (n11 - n27) / n25;
        final double n32 = (-n10 - n26) / n24;
        final double n33 = (-n11 - n27) / n25;
        final double n34 = n30 * n30 + n31 * n31;
        final double sqrt = Math.sqrt(n34);
        double n35;
        if (n31 < 0.0) {
            n35 = -1.0;
        }
        else {
            n35 = 1.0;
        }
        final double acos = Math.acos(n30 / sqrt);
        final double sqrt2 = Math.sqrt(n34 * (n32 * n32 + n33 * n33));
        double n36;
        if (n30 * n33 - n31 * n32 < 0.0) {
            n36 = n18;
        }
        else {
            n36 = 1.0;
        }
        final double n37 = n36 * checkedArcCos((n30 * n32 + n31 * n33) / sqrt2);
        final double n38 = dcmpl(n37, 0.0);
        if (n38 == 0) {
            pathInterface.lineTo(n6, n7);
            return;
        }
        double n39;
        if (!b2 && n38 > 0) {
            n39 = n37 - 6.283185307179586;
        }
        else {
            n39 = n37;
            if (b2) {
                n39 = n37;
                if (n37 < 0.0) {
                    n39 = n37 + 6.283185307179586;
                }
            }
        }
        final float[] arcToBeziers = arcToBeziers(n35 * acos % 6.283185307179586, n39 % 6.283185307179586);
        final Matrix matrix = new Matrix();
        matrix.postScale(n4, n3);
        matrix.postRotate(n5);
        matrix.postTranslate((float)(n28 + (cos * n26 - sin * n27)), (float)(n29 + (sin * n26 + cos * n27)));
        matrix.mapPoints(arcToBeziers);
        arcToBeziers[arcToBeziers.length - 2] = n6;
        arcToBeziers[arcToBeziers.length - 1] = n7;
        for (int i = 0; i < arcToBeziers.length; i += 6) {
            pathInterface.cubicTo(arcToBeziers[i], arcToBeziers[i + 1], arcToBeziers[i + 2], arcToBeziers[i + 3], arcToBeziers[i + 4], arcToBeziers[i + 5]);
        }
    }
    
    private static float[] arcToBeziers(final double n, double n2) {
        final int n3 = (int)Math.ceil(Math.abs(n2) * 2.0 / 3.141592653589793);
        n2 /= n3;
        final double n4 = n2 / 2.0;
        final double n5 = Math.sin(n4) * 1.3333333333333333 / (Math.cos(n4) + 1.0);
        final float[] array = new float[n3 * 6];
        int i = 0;
        int n6 = 0;
        while (i < n3) {
            final double n7 = n + i * n2;
            final double cos = Math.cos(n7);
            final double sin = Math.sin(n7);
            final int n8 = n6 + 1;
            array[n6] = (float)(cos - n5 * sin);
            final int n9 = n8 + 1;
            array[n8] = (float)(sin + cos * n5);
            final double n10 = n7 + n2;
            final double cos2 = Math.cos(n10);
            final double sin2 = Math.sin(n10);
            final int n11 = n9 + 1;
            array[n9] = (float)(n5 * sin2 + cos2);
            final int n12 = n11 + 1;
            array[n11] = (float)(sin2 - n5 * cos2);
            final int n13 = n12 + 1;
            array[n12] = (float)cos2;
            n6 = n13 + 1;
            array[n13] = (float)sin2;
            ++i;
        }
        return array;
    }
    
    private Path calculateClipPath(final SVGBase$SvgElement svgBase$SvgElement, final SVGBase.Box box) {
        final SVGBase.SvgObject resolveIRI = svgBase$SvgElement.document.resolveIRI(this.state.style.clipPath);
        boolean b = false;
        if (resolveIRI == null) {
            error("ClipPath reference '%s' not found", this.state.style.clipPath);
            return null;
        }
        final SVGBase$ClipPath svgBase$ClipPath = (SVGBase$ClipPath)resolveIRI;
        this.stateStack.push((Object)this.state);
        this.state = this.findInheritFromAncestorState((SVGBase.SvgObject)svgBase$ClipPath);
        if (svgBase$ClipPath.clipPathUnitsAreUser == null || svgBase$ClipPath.clipPathUnitsAreUser) {
            b = true;
        }
        final Matrix matrix = new Matrix();
        if (!b) {
            matrix.preTranslate(box.minX, box.minY);
            matrix.preScale(box.width, box.height);
        }
        if (svgBase$ClipPath.transform != null) {
            matrix.preConcat(svgBase$ClipPath.transform);
        }
        final Path path = new Path();
        for (final SVGBase.SvgObject svgObject : svgBase$ClipPath.children) {
            if (!(svgObject instanceof SVGBase$SvgElement)) {
                continue;
            }
            final Path objectToPath = this.objectToPath((SVGBase$SvgElement)svgObject, true);
            if (objectToPath == null) {
                continue;
            }
            path.op(objectToPath, Path$Op.UNION);
        }
        if (this.state.style.clipPath != null) {
            if (svgBase$ClipPath.boundingBox == null) {
                svgBase$ClipPath.boundingBox = this.calculatePathBounds(path);
            }
            final Path calculateClipPath = this.calculateClipPath((SVGBase$SvgElement)svgBase$ClipPath, svgBase$ClipPath.boundingBox);
            if (calculateClipPath != null) {
                path.op(calculateClipPath, Path$Op.INTERSECT);
            }
        }
        path.transform(matrix);
        this.state = (RendererState)this.stateStack.pop();
        return path;
    }
    
    private List<MarkerVector> calculateMarkerPositions(final SVGBase$Line svgBase$Line) {
        final SVGBase.Length x1 = svgBase$Line.x1;
        float floatValueY = 0.0f;
        float floatValueX;
        if (x1 != null) {
            floatValueX = svgBase$Line.x1.floatValueX(this);
        }
        else {
            floatValueX = 0.0f;
        }
        float floatValueY2;
        if (svgBase$Line.y1 != null) {
            floatValueY2 = svgBase$Line.y1.floatValueY(this);
        }
        else {
            floatValueY2 = 0.0f;
        }
        float floatValueX2;
        if (svgBase$Line.x2 != null) {
            floatValueX2 = svgBase$Line.x2.floatValueX(this);
        }
        else {
            floatValueX2 = 0.0f;
        }
        if (svgBase$Line.y2 != null) {
            floatValueY = svgBase$Line.y2.floatValueY(this);
        }
        final ArrayList list = new ArrayList(2);
        final float n = floatValueX2 - floatValueX;
        final float n2 = floatValueY - floatValueY2;
        ((List)list).add((Object)new MarkerVector(floatValueX, floatValueY2, n, n2));
        ((List)list).add((Object)new MarkerVector(floatValueX2, floatValueY, n, n2));
        return (List<MarkerVector>)list;
    }
    
    private List<MarkerVector> calculateMarkerPositions(final SVGBase$PolyLine svgBase$PolyLine) {
        int length;
        if (svgBase$PolyLine.points != null) {
            length = svgBase$PolyLine.points.length;
        }
        else {
            length = 0;
        }
        int i = 2;
        if (length < 2) {
            return null;
        }
        final ArrayList list = new ArrayList();
        final float n = svgBase$PolyLine.points[0];
        final float n2 = svgBase$PolyLine.points[1];
        float n3 = 0.0f;
        MarkerVector markerVector = new MarkerVector(n, n2, 0.0f, 0.0f);
        float n4 = 0.0f;
        while (i < length) {
            n3 = svgBase$PolyLine.points[i];
            n4 = svgBase$PolyLine.points[i + 1];
            markerVector.add(n3, n4);
            ((List)list).add((Object)markerVector);
            markerVector = new MarkerVector(n3, n4, n3 - markerVector.x, n4 - markerVector.y);
            i += 2;
        }
        if (svgBase$PolyLine instanceof SVGBase$Polygon) {
            if (n3 != svgBase$PolyLine.points[0] && n4 != svgBase$PolyLine.points[1]) {
                final float n5 = svgBase$PolyLine.points[0];
                final float n6 = svgBase$PolyLine.points[1];
                markerVector.add(n5, n6);
                ((List)list).add((Object)markerVector);
                final MarkerVector markerVector2 = new MarkerVector(n5, n6, n5 - markerVector.x, n6 - markerVector.y);
                markerVector2.add((MarkerVector)((List)list).get(0));
                ((List)list).add((Object)markerVector2);
                ((List)list).set(0, (Object)markerVector2);
            }
        }
        else {
            ((List)list).add((Object)markerVector);
        }
        return (List<MarkerVector>)list;
    }
    
    private SVGBase.Box calculatePathBounds(final Path path) {
        final RectF rectF = new RectF();
        path.computeBounds(rectF, true);
        return new SVGBase.Box(rectF.left, rectF.top, rectF.width(), rectF.height());
    }
    
    private float calculateTextWidth(final SVGBase$TextContainer svgBase$TextContainer) {
        final SVGAndroidRenderer.SVGAndroidRenderer$TextWidthCalculator svgAndroidRenderer$TextWidthCalculator = new SVGAndroidRenderer.SVGAndroidRenderer$TextWidthCalculator(this, (SVGAndroidRenderer$1)null);
        this.enumerateTextSpans(svgBase$TextContainer, (TextProcessor)svgAndroidRenderer$TextWidthCalculator);
        return svgAndroidRenderer$TextWidthCalculator.x;
    }
    
    private Matrix calculateViewBoxTransform(final SVGBase.Box box, final SVGBase.Box box2, final PreserveAspectRatio preserveAspectRatio) {
        final Matrix matrix = new Matrix();
        if (preserveAspectRatio != null) {
            if (preserveAspectRatio.getAlignment() != null) {
                final float n = box.width / box2.width;
                final float n2 = box.height / box2.height;
                final float n3 = -box2.minX;
                final float n4 = -box2.minY;
                if (preserveAspectRatio.equals(PreserveAspectRatio.STRETCH)) {
                    matrix.preTranslate(box.minX, box.minY);
                    matrix.preScale(n, n2);
                    matrix.preTranslate(n3, n4);
                    return matrix;
                }
                float n5;
                if (preserveAspectRatio.getScale() == PreserveAspectRatio.Scale.slice) {
                    n5 = Math.max(n, n2);
                }
                else {
                    n5 = Math.min(n, n2);
                }
                final float n6 = box.width / n5;
                final float n7 = box.height / n5;
                float n8 = 0.0f;
                Label_0241: {
                    float n9 = 0.0f;
                    switch (SVGAndroidRenderer$1.$SwitchMap$com$kingagroot$kingdraw$core$graphics$svg$PreserveAspectRatio$Alignment[preserveAspectRatio.getAlignment().ordinal()]) {
                        default: {
                            n8 = n3;
                            break Label_0241;
                        }
                        case 4:
                        case 5:
                        case 6: {
                            n9 = box2.width - n6;
                            break;
                        }
                        case 1:
                        case 2:
                        case 3: {
                            n9 = (box2.width - n6) / 2.0f;
                            break;
                        }
                    }
                    n8 = n3 - n9;
                }
                final int n10 = SVGAndroidRenderer$1.$SwitchMap$com$kingagroot$kingdraw$core$graphics$svg$PreserveAspectRatio$Alignment[preserveAspectRatio.getAlignment().ordinal()];
                float n11 = 0.0f;
                Label_0330: {
                    float n12 = 0.0f;
                    Label_0323: {
                        Label_0312: {
                            if (n10 != 2) {
                                if (n10 != 3) {
                                    if (n10 == 5) {
                                        break Label_0312;
                                    }
                                    if (n10 != 6) {
                                        if (n10 == 7) {
                                            break Label_0312;
                                        }
                                        if (n10 != 8) {
                                            n11 = n4;
                                            break Label_0330;
                                        }
                                    }
                                }
                                n12 = box2.height - n7;
                                break Label_0323;
                            }
                        }
                        n12 = (box2.height - n7) / 2.0f;
                    }
                    n11 = n4 - n12;
                }
                matrix.preTranslate(box.minX, box.minY);
                matrix.preScale(n5, n5);
                matrix.preTranslate(n8, n11);
            }
        }
        return matrix;
    }
    
    private void checkForClipPath(final SVGBase$SvgElement svgBase$SvgElement) {
        this.checkForClipPath(svgBase$SvgElement, svgBase$SvgElement.boundingBox);
    }
    
    private void checkForClipPath(final SVGBase$SvgElement svgBase$SvgElement, final SVGBase.Box box) {
        if (this.state.style.clipPath == null) {
            return;
        }
        if (SVGAndroidRenderer.SUPPORTS_PATH_OP) {
            final Path calculateClipPath = this.calculateClipPath(svgBase$SvgElement, box);
            if (calculateClipPath != null) {
                this.canvas.clipPath(calculateClipPath);
            }
        }
        else {
            this.checkForClipPath_OldStyle(svgBase$SvgElement, box);
        }
    }
    
    private void checkForClipPath_OldStyle(final SVGBase$SvgElement svgBase$SvgElement, final SVGBase.Box box) {
        final SVGBase.SvgObject resolveIRI = svgBase$SvgElement.document.resolveIRI(this.state.style.clipPath);
        if (resolveIRI == null) {
            error("ClipPath reference '%s' not found", this.state.style.clipPath);
            return;
        }
        final SVGBase$ClipPath svgBase$ClipPath = (SVGBase$ClipPath)resolveIRI;
        if (svgBase$ClipPath.children.isEmpty()) {
            this.canvas.clipRect(0, 0, 0, 0);
            return;
        }
        final boolean b = svgBase$ClipPath.clipPathUnitsAreUser == null || svgBase$ClipPath.clipPathUnitsAreUser;
        if (svgBase$SvgElement instanceof SVGBase$Group && !b) {
            warn("<clipPath clipPathUnits=\"objectBoundingBox\"> is not supported when referenced from container elements (like %s)", svgBase$SvgElement.getNodeName());
            return;
        }
        this.clipStatePush();
        if (!b) {
            final Matrix matrix = new Matrix();
            matrix.preTranslate(box.minX, box.minY);
            matrix.preScale(box.width, box.height);
            this.canvas.concat(matrix);
        }
        if (svgBase$ClipPath.transform != null) {
            this.canvas.concat(svgBase$ClipPath.transform);
        }
        this.state = this.findInheritFromAncestorState((SVGBase.SvgObject)svgBase$ClipPath);
        this.checkForClipPath((SVGBase$SvgElement)svgBase$ClipPath);
        final Path path = new Path();
        final Iterator iterator = svgBase$ClipPath.children.iterator();
        while (iterator.hasNext()) {
            this.addObjectToClip((SVGBase.SvgObject)iterator.next(), true, path, new Matrix());
        }
        this.canvas.clipPath(path);
        this.clipStatePop();
    }
    
    private void checkForGradientsAndPatterns(final SVGBase$SvgElement svgBase$SvgElement) {
        if (this.state.style.fill instanceof SVGBase$PaintReference) {
            this.decodePaintReference(true, svgBase$SvgElement.boundingBox, (SVGBase$PaintReference)this.state.style.fill);
        }
        if (this.state.style.stroke instanceof SVGBase$PaintReference) {
            this.decodePaintReference(false, svgBase$SvgElement.boundingBox, (SVGBase$PaintReference)this.state.style.stroke);
        }
    }
    
    private Bitmap checkForImageDataURL(final String s) {
        if (!s.startsWith("data:")) {
            return null;
        }
        if (s.length() < 14) {
            return null;
        }
        final int index = s.indexOf(44);
        if (index < 12) {
            return null;
        }
        if (!";base64".equals((Object)s.substring(index - 7, index))) {
            return null;
        }
        try {
            final byte[] decode = Base64.decode(s.substring(index + 1), 0);
            return BitmapFactory.decodeByteArray(decode, 0, decode.length);
        }
        catch (final Exception ex) {
            Log.e("SVGAndroidRenderer", "Could not decode bad Data URL", (Throwable)ex);
            return null;
        }
    }
    
    private Typeface checkGenericFont(final String s, final Float n, final Style.FontStyle fontStyle) {
        final Style.FontStyle italic = Style.FontStyle.italic;
        final int n2 = 0;
        final boolean b = fontStyle == italic;
        int n3;
        if (n >= 700.0f) {
            if (b) {
                n3 = 3;
            }
            else {
                n3 = 1;
            }
        }
        else if (b) {
            n3 = 2;
        }
        else {
            n3 = 0;
        }
        int n4 = 0;
        Label_0206: {
            switch (s.hashCode()) {
                case 1126973893: {
                    if (s.equals((Object)"cursive")) {
                        n4 = 2;
                        break Label_0206;
                    }
                    break;
                }
                case 109326717: {
                    if (s.equals((Object)"serif")) {
                        n4 = n2;
                        break Label_0206;
                    }
                    break;
                }
                case -1081737434: {
                    if (s.equals((Object)"fantasy")) {
                        n4 = 3;
                        break Label_0206;
                    }
                    break;
                }
                case -1431958525: {
                    if (s.equals((Object)"monospace")) {
                        n4 = 4;
                        break Label_0206;
                    }
                    break;
                }
                case -1536685117: {
                    if (s.equals((Object)"sans-serif")) {
                        n4 = 1;
                        break Label_0206;
                    }
                    break;
                }
            }
            n4 = -1;
        }
        Typeface typeface;
        if (n4 != 0) {
            if (n4 != 1 && n4 != 2 && n4 != 3) {
                if (n4 != 4) {
                    typeface = null;
                }
                else {
                    typeface = Typeface.create(Typeface.MONOSPACE, n3);
                }
            }
            else {
                typeface = Typeface.create(Typeface.SANS_SERIF, n3);
            }
        }
        else {
            typeface = Typeface.create(Typeface.SERIF, n3);
        }
        return typeface;
    }
    
    private void checkXMLSpaceAttribute(final SVGBase.SvgObject svgObject) {
        if (!(svgObject instanceof SVGBase$SvgElementBase)) {
            return;
        }
        final SVGBase$SvgElementBase svgBase$SvgElementBase = (SVGBase$SvgElementBase)svgObject;
        if (svgBase$SvgElementBase.spacePreserve != null) {
            this.state.spacePreserve = svgBase$SvgElementBase.spacePreserve;
        }
    }
    
    private static double checkedArcCos(double acos) {
        if (acos < -1.0) {
            acos = 3.141592653589793;
        }
        else if (acos > 1.0) {
            acos = 0.0;
        }
        else {
            acos = Math.acos(acos);
        }
        return acos;
    }
    
    private static int clamp255(final float n) {
        final int n2 = (int)(n * 256.0f);
        int min;
        if (n2 < 0) {
            min = 0;
        }
        else {
            min = Math.min(n2, 255);
        }
        return min;
    }
    
    private void clipStatePop() {
        this.canvas.restore();
        this.state = (RendererState)this.stateStack.pop();
    }
    
    private void clipStatePush() {
        CanvasLegacy.save(this.canvas, CanvasLegacy.MATRIX_SAVE_FLAG);
        this.stateStack.push((Object)this.state);
        this.state = new RendererState(this.state);
    }
    
    private static int colourWithOpacity(final int n, final float n2) {
        final int round = Math.round((n >> 24 & 0xFF) * n2);
        int min;
        if (round < 0) {
            min = 0;
        }
        else {
            min = Math.min(round, 255);
        }
        return (n & 0xFFFFFF) | min << 24;
    }
    
    private static void debug(final String s, final Object... array) {
    }
    
    private void decodePaintReference(final boolean b, final SVGBase.Box box, final SVGBase$PaintReference svgBase$PaintReference) {
        final SVGBase.SvgObject resolveIRI = this.document.resolveIRI(svgBase$PaintReference.href);
        if (resolveIRI == null) {
            String s;
            if (b) {
                s = "Fill";
            }
            else {
                s = "Stroke";
            }
            error("%s reference '%s' not found", s, svgBase$PaintReference.href);
            if (svgBase$PaintReference.fallback != null) {
                this.setPaintColour(this.state, b, svgBase$PaintReference.fallback);
            }
            else if (b) {
                this.state.hasFill = false;
            }
            else {
                this.state.hasStroke = false;
            }
            return;
        }
        if (resolveIRI instanceof SVGBase$SvgLinearGradient) {
            this.makeLinearGradient(b, box, (SVGBase$SvgLinearGradient)resolveIRI);
        }
        else if (resolveIRI instanceof SVGBase$SvgRadialGradient) {
            this.makeRadialGradient(b, box, (SVGBase$SvgRadialGradient)resolveIRI);
        }
        else if (resolveIRI instanceof SVGBase$SolidColor) {
            this.setSolidColor(b, (SVGBase$SolidColor)resolveIRI);
        }
    }
    
    private boolean display() {
        return this.state.style.display == null || this.state.style.display;
    }
    
    private void doFilledPath(final SVGBase$SvgElement svgBase$SvgElement, final Path path) {
        if (this.state.style.fill instanceof SVGBase$PaintReference) {
            final SVGBase.SvgObject resolveIRI = this.document.resolveIRI(((SVGBase$PaintReference)this.state.style.fill).href);
            if (resolveIRI instanceof SVGBase$Pattern) {
                this.fillWithPattern(svgBase$SvgElement, path, (SVGBase$Pattern)resolveIRI);
                return;
            }
        }
        this.canvas.drawPath(path, this.state.fillPaint);
    }
    
    private void doStroke(final Path path) {
        if (this.state.style.vectorEffect == Style.VectorEffect.NonScalingStroke) {
            final Matrix matrix = this.canvas.getMatrix();
            final Path path2 = new Path();
            path.transform(matrix, path2);
            this.canvas.setMatrix(new Matrix());
            final Shader shader = this.state.strokePaint.getShader();
            final Matrix localMatrix = new Matrix();
            if (shader != null) {
                shader.getLocalMatrix(localMatrix);
                final Matrix localMatrix2 = new Matrix(localMatrix);
                localMatrix2.postConcat(matrix);
                shader.setLocalMatrix(localMatrix2);
            }
            this.canvas.drawPath(path2, this.state.strokePaint);
            this.canvas.setMatrix(matrix);
            if (shader != null) {
                shader.setLocalMatrix(localMatrix);
            }
        }
        else {
            this.canvas.drawPath(path, this.state.strokePaint);
        }
    }
    
    private float dotProduct(final float n, final float n2, final float n3, final float n4) {
        return n * n3 + n2 * n4;
    }
    
    private void enumerateTextSpans(final SVGBase$TextContainer svgBase$TextContainer, final TextProcessor textProcessor) {
        if (!this.display()) {
            return;
        }
        final Iterator iterator = svgBase$TextContainer.children.iterator();
        boolean b = true;
        while (iterator.hasNext()) {
            final SVGBase.SvgObject svgObject = (SVGBase.SvgObject)iterator.next();
            if (svgObject instanceof SVGBase$TextSequence) {
                textProcessor.processText(this.textXMLSpaceTransform(((SVGBase$TextSequence)svgObject).text, b, iterator.hasNext() ^ true));
            }
            else {
                this.processTextChild(svgObject, textProcessor);
            }
            b = false;
        }
    }
    
    private static void error(final String s, final Object... array) {
        Log.e("SVGAndroidRenderer", String.format(s, array));
    }
    
    private void extractRawText(final SVGBase$TextContainer svgBase$TextContainer, final StringBuilder sb) {
        final Iterator iterator = svgBase$TextContainer.children.iterator();
        boolean b = true;
        while (iterator.hasNext()) {
            final SVGBase.SvgObject svgObject = (SVGBase.SvgObject)iterator.next();
            if (svgObject instanceof SVGBase$TextContainer) {
                this.extractRawText((SVGBase$TextContainer)svgObject, sb);
            }
            else if (svgObject instanceof SVGBase$TextSequence) {
                sb.append(this.textXMLSpaceTransform(((SVGBase$TextSequence)svgObject).text, b, iterator.hasNext() ^ true));
            }
            b = false;
        }
    }
    
    private void fillInChainedGradientFields(final SVGBase$GradientElement svgBase$GradientElement, String s) {
        final SVGBase.SvgObject resolveIRI = svgBase$GradientElement.document.resolveIRI(s);
        if (resolveIRI == null) {
            warn("Gradient reference '%s' not found", s);
            return;
        }
        if (!(resolveIRI instanceof SVGBase$GradientElement)) {
            error("Gradient href attributes must point to other gradient elements", new Object[0]);
            return;
        }
        if (resolveIRI == svgBase$GradientElement) {
            error("Circular reference in gradient href attribute '%s'", s);
            return;
        }
        s = (String)resolveIRI;
        if (svgBase$GradientElement.gradientUnitsAreUser == null) {
            svgBase$GradientElement.gradientUnitsAreUser = ((SVGBase$GradientElement)s).gradientUnitsAreUser;
        }
        if (svgBase$GradientElement.gradientTransform == null) {
            svgBase$GradientElement.gradientTransform = ((SVGBase$GradientElement)s).gradientTransform;
        }
        if (svgBase$GradientElement.spreadMethod == null) {
            svgBase$GradientElement.spreadMethod = ((SVGBase$GradientElement)s).spreadMethod;
        }
        if (svgBase$GradientElement.children.isEmpty()) {
            svgBase$GradientElement.children = ((SVGBase$GradientElement)s).children;
        }
        try {
            if (svgBase$GradientElement instanceof SVGBase$SvgLinearGradient) {
                this.fillInChainedGradientFields((SVGBase$SvgLinearGradient)svgBase$GradientElement, (SVGBase$SvgLinearGradient)resolveIRI);
            }
            else {
                this.fillInChainedGradientFields((SVGBase$SvgRadialGradient)svgBase$GradientElement, (SVGBase$SvgRadialGradient)resolveIRI);
            }
        }
        catch (final ClassCastException ex) {}
        if (((SVGBase$GradientElement)s).href != null) {
            this.fillInChainedGradientFields(svgBase$GradientElement, ((SVGBase$GradientElement)s).href);
        }
    }
    
    private void fillInChainedGradientFields(final SVGBase$SvgLinearGradient svgBase$SvgLinearGradient, final SVGBase$SvgLinearGradient svgBase$SvgLinearGradient2) {
        if (svgBase$SvgLinearGradient.x1 == null) {
            svgBase$SvgLinearGradient.x1 = svgBase$SvgLinearGradient2.x1;
        }
        if (svgBase$SvgLinearGradient.y1 == null) {
            svgBase$SvgLinearGradient.y1 = svgBase$SvgLinearGradient2.y1;
        }
        if (svgBase$SvgLinearGradient.x2 == null) {
            svgBase$SvgLinearGradient.x2 = svgBase$SvgLinearGradient2.x2;
        }
        if (svgBase$SvgLinearGradient.y2 == null) {
            svgBase$SvgLinearGradient.y2 = svgBase$SvgLinearGradient2.y2;
        }
    }
    
    private void fillInChainedGradientFields(final SVGBase$SvgRadialGradient svgBase$SvgRadialGradient, final SVGBase$SvgRadialGradient svgBase$SvgRadialGradient2) {
        if (svgBase$SvgRadialGradient.cx == null) {
            svgBase$SvgRadialGradient.cx = svgBase$SvgRadialGradient2.cx;
        }
        if (svgBase$SvgRadialGradient.cy == null) {
            svgBase$SvgRadialGradient.cy = svgBase$SvgRadialGradient2.cy;
        }
        if (svgBase$SvgRadialGradient.r == null) {
            svgBase$SvgRadialGradient.r = svgBase$SvgRadialGradient2.r;
        }
        if (svgBase$SvgRadialGradient.fx == null) {
            svgBase$SvgRadialGradient.fx = svgBase$SvgRadialGradient2.fx;
        }
        if (svgBase$SvgRadialGradient.fy == null) {
            svgBase$SvgRadialGradient.fy = svgBase$SvgRadialGradient2.fy;
        }
    }
    
    private void fillInChainedPatternFields(final SVGBase$Pattern svgBase$Pattern, final String s) {
        final SVGBase.SvgObject resolveIRI = svgBase$Pattern.document.resolveIRI(s);
        if (resolveIRI == null) {
            warn("Pattern reference '%s' not found", s);
            return;
        }
        if (!(resolveIRI instanceof SVGBase$Pattern)) {
            error("Pattern href attributes must point to other pattern elements", new Object[0]);
            return;
        }
        if (resolveIRI == svgBase$Pattern) {
            error("Circular reference in pattern href attribute '%s'", s);
            return;
        }
        final SVGBase$Pattern svgBase$Pattern2 = (SVGBase$Pattern)resolveIRI;
        if (svgBase$Pattern.patternUnitsAreUser == null) {
            svgBase$Pattern.patternUnitsAreUser = svgBase$Pattern2.patternUnitsAreUser;
        }
        if (svgBase$Pattern.patternContentUnitsAreUser == null) {
            svgBase$Pattern.patternContentUnitsAreUser = svgBase$Pattern2.patternContentUnitsAreUser;
        }
        if (svgBase$Pattern.patternTransform == null) {
            svgBase$Pattern.patternTransform = svgBase$Pattern2.patternTransform;
        }
        if (svgBase$Pattern.x == null) {
            svgBase$Pattern.x = svgBase$Pattern2.x;
        }
        if (svgBase$Pattern.y == null) {
            svgBase$Pattern.y = svgBase$Pattern2.y;
        }
        if (svgBase$Pattern.width == null) {
            svgBase$Pattern.width = svgBase$Pattern2.width;
        }
        if (svgBase$Pattern.height == null) {
            svgBase$Pattern.height = svgBase$Pattern2.height;
        }
        if (svgBase$Pattern.children.isEmpty()) {
            svgBase$Pattern.children = svgBase$Pattern2.children;
        }
        if (svgBase$Pattern.viewBox == null) {
            svgBase$Pattern.viewBox = svgBase$Pattern2.viewBox;
        }
        if (svgBase$Pattern.preserveAspectRatio == null) {
            svgBase$Pattern.preserveAspectRatio = svgBase$Pattern2.preserveAspectRatio;
        }
        if (svgBase$Pattern2.href != null) {
            this.fillInChainedPatternFields(svgBase$Pattern, svgBase$Pattern2.href);
        }
    }
    
    private void fillWithPattern(final SVGBase$SvgElement svgBase$SvgElement, final Path path, final SVGBase$Pattern svgBase$Pattern) {
        final boolean b = svgBase$Pattern.patternUnitsAreUser != null && svgBase$Pattern.patternUnitsAreUser;
        final float floatValue = this.state.style.fillOpacity;
        if (svgBase$Pattern.href != null) {
            this.fillInChainedPatternFields(svgBase$Pattern, svgBase$Pattern.href);
        }
        float floatValueY;
        float floatValueX2;
        float n;
        float n2;
        if (b) {
            float floatValueX;
            if (svgBase$Pattern.x != null) {
                floatValueX = svgBase$Pattern.x.floatValueX(this);
            }
            else {
                floatValueX = 0.0f;
            }
            if (svgBase$Pattern.y != null) {
                floatValueY = svgBase$Pattern.y.floatValueY(this);
            }
            else {
                floatValueY = 0.0f;
            }
            if (svgBase$Pattern.width != null) {
                floatValueX2 = svgBase$Pattern.width.floatValueX(this);
            }
            else {
                floatValueX2 = 0.0f;
            }
            if (svgBase$Pattern.height != null) {
                final float floatValueY2 = svgBase$Pattern.height.floatValueY(this);
                n = floatValueX;
                n2 = floatValueY2;
            }
            else {
                final float n3 = 0.0f;
                n = floatValueX;
                n2 = n3;
            }
        }
        else {
            float floatValue2;
            if (svgBase$Pattern.x != null) {
                floatValue2 = svgBase$Pattern.x.floatValue(this, 1.0f);
            }
            else {
                floatValue2 = 0.0f;
            }
            float floatValue3;
            if (svgBase$Pattern.y != null) {
                floatValue3 = svgBase$Pattern.y.floatValue(this, 1.0f);
            }
            else {
                floatValue3 = 0.0f;
            }
            float floatValue4;
            if (svgBase$Pattern.width != null) {
                floatValue4 = svgBase$Pattern.width.floatValue(this, 1.0f);
            }
            else {
                floatValue4 = 0.0f;
            }
            float floatValue5;
            if (svgBase$Pattern.height != null) {
                floatValue5 = svgBase$Pattern.height.floatValue(this, 1.0f);
            }
            else {
                floatValue5 = 0.0f;
            }
            final float n4 = floatValue2 * svgBase$SvgElement.boundingBox.width + svgBase$SvgElement.boundingBox.minX;
            final float minY = svgBase$SvgElement.boundingBox.minY;
            final float height = svgBase$SvgElement.boundingBox.height;
            final float width = svgBase$SvgElement.boundingBox.width;
            final float n5 = floatValue5 * svgBase$SvgElement.boundingBox.height;
            final float n6 = floatValue3 * height + minY;
            final float n7 = floatValue4 * width;
            n2 = n5;
            floatValueX2 = n7;
            floatValueY = n6;
            n = n4;
        }
        if (floatValueX2 != 0.0f) {
            if (n2 != 0.0f) {
                PreserveAspectRatio preserveAspectRatio;
                if (svgBase$Pattern.preserveAspectRatio != null) {
                    preserveAspectRatio = svgBase$Pattern.preserveAspectRatio;
                }
                else {
                    preserveAspectRatio = PreserveAspectRatio.LETTERBOX;
                }
                this.statePush();
                this.canvas.clipPath(path);
                final RendererState rendererState = new RendererState();
                this.updateStyle(rendererState, Style.getDefaultStyle());
                rendererState.style.overflow = false;
                this.state = this.findInheritFromAncestorState((SVGBase.SvgObject)svgBase$Pattern, rendererState);
                Object boundingBox;
                final SVGBase.Box box = (SVGBase.Box)(boundingBox = svgBase$SvgElement.boundingBox);
                if (svgBase$Pattern.patternTransform != null) {
                    this.canvas.concat(svgBase$Pattern.patternTransform);
                    final Matrix matrix = new Matrix();
                    boundingBox = box;
                    if (svgBase$Pattern.patternTransform.invert(matrix)) {
                        final float[] array = { svgBase$SvgElement.boundingBox.minX, svgBase$SvgElement.boundingBox.minY, svgBase$SvgElement.boundingBox.maxX(), svgBase$SvgElement.boundingBox.minY, svgBase$SvgElement.boundingBox.maxX(), svgBase$SvgElement.boundingBox.maxY(), svgBase$SvgElement.boundingBox.minX, svgBase$SvgElement.boundingBox.maxY() };
                        matrix.mapPoints(array);
                        final RectF rectF = new RectF(array[0], array[1], array[0], array[1]);
                        for (int i = 2; i <= 6; i += 2) {
                            if (array[i] < rectF.left) {
                                rectF.left = array[i];
                            }
                            if (array[i] > rectF.right) {
                                rectF.right = array[i];
                            }
                            final int n8 = i + 1;
                            if (array[n8] < rectF.top) {
                                rectF.top = array[n8];
                            }
                            if (array[n8] > rectF.bottom) {
                                rectF.bottom = array[n8];
                            }
                        }
                        boundingBox = new SVGBase.Box(rectF.left, rectF.top, rectF.right - rectF.left, rectF.bottom - rectF.top);
                    }
                }
                final float n9 = n + (float)Math.floor((double)((((SVGBase.Box)boundingBox).minX - n) / floatValueX2)) * floatValueX2;
                float minY2 = floatValueY + (float)Math.floor((double)((((SVGBase.Box)boundingBox).minY - floatValueY) / n2)) * n2;
                final float maxX = ((SVGBase.Box)boundingBox).maxX();
                final float maxY = ((SVGBase.Box)boundingBox).maxY();
                final SVGBase.Box box2 = new SVGBase.Box(0.0f, 0.0f, floatValueX2, n2);
                final boolean pushLayer = this.pushLayer(floatValue);
                final float n10 = n9;
                while (minY2 < maxY) {
                    for (float minX = n10; minX < maxX; minX += floatValueX2) {
                        box2.minX = minX;
                        box2.minY = minY2;
                        this.statePush();
                        if (!this.state.style.overflow) {
                            this.setClipRect(box2.minX, box2.minY, box2.width, box2.height);
                        }
                        if (svgBase$Pattern.viewBox != null) {
                            this.canvas.concat(this.calculateViewBoxTransform(box2, svgBase$Pattern.viewBox, preserveAspectRatio));
                        }
                        else {
                            final boolean b2 = svgBase$Pattern.patternContentUnitsAreUser == null || svgBase$Pattern.patternContentUnitsAreUser;
                            this.canvas.translate(minX, minY2);
                            if (!b2) {
                                this.canvas.scale(svgBase$SvgElement.boundingBox.width, svgBase$SvgElement.boundingBox.height);
                            }
                        }
                        final Iterator iterator = svgBase$Pattern.children.iterator();
                        while (iterator.hasNext()) {
                            this.render((SVGBase.SvgObject)iterator.next());
                        }
                        this.statePop();
                    }
                    minY2 += n2;
                }
                if (pushLayer) {
                    this.popLayer((SVGBase$SvgElement)svgBase$Pattern);
                }
                this.statePop();
            }
        }
    }
    
    private RendererState findInheritFromAncestorState(final SVGBase.SvgObject svgObject) {
        final RendererState rendererState = new RendererState();
        this.updateStyle(rendererState, Style.getDefaultStyle());
        return this.findInheritFromAncestorState(svgObject, rendererState);
    }
    
    private RendererState findInheritFromAncestorState(SVGBase.SvgObject svgObject, final RendererState rendererState) {
        final ArrayList list = new ArrayList();
        while (true) {
            if (svgObject instanceof SVGBase$SvgElementBase) {
                ((List)list).add(0, (Object)svgObject);
            }
            if (svgObject.parent == null) {
                break;
            }
            svgObject = (SVGBase.SvgObject)svgObject.parent;
        }
        final Iterator iterator = ((List)list).iterator();
        while (iterator.hasNext()) {
            this.updateStyleForElement(rendererState, (SVGBase$SvgElementBase)iterator.next());
        }
        rendererState.viewBox = this.state.viewBox;
        rendererState.viewPort = this.state.viewPort;
        return rendererState;
    }
    
    private Style.TextAnchor getAnchorPosition() {
        if (this.state.style.direction != Style.TextDirection.LTR && this.state.style.textAnchor != Style.TextAnchor.Middle) {
            Style.TextAnchor textAnchor;
            if (this.state.style.textAnchor == Style.TextAnchor.Start) {
                textAnchor = Style.TextAnchor.End;
            }
            else {
                textAnchor = Style.TextAnchor.Start;
            }
            return textAnchor;
        }
        return this.state.style.textAnchor;
    }
    
    private Path$FillType getClipRuleFromState() {
        if (this.state.style.clipRule != null && this.state.style.clipRule == Style.FillRule.EvenOdd) {
            return Path$FillType.EVEN_ODD;
        }
        return Path$FillType.WINDING;
    }
    
    private Path$FillType getFillTypeFromState() {
        if (this.state.style.fillRule != null && this.state.style.fillRule == Style.FillRule.EvenOdd) {
            return Path$FillType.EVEN_ODD;
        }
        return Path$FillType.WINDING;
    }
    
    private static void initialiseSupportedFeaturesMap() {
        synchronized (SVGAndroidRenderer.class) {
            (SVGAndroidRenderer.supportedFeatures = (HashSet<String>)new HashSet()).add((Object)"Structure");
            SVGAndroidRenderer.supportedFeatures.add((Object)"BasicStructure");
            SVGAndroidRenderer.supportedFeatures.add((Object)"ConditionalProcessing");
            SVGAndroidRenderer.supportedFeatures.add((Object)"Image");
            SVGAndroidRenderer.supportedFeatures.add((Object)"Style");
            SVGAndroidRenderer.supportedFeatures.add((Object)"ViewportAttribute");
            SVGAndroidRenderer.supportedFeatures.add((Object)"Shape");
            SVGAndroidRenderer.supportedFeatures.add((Object)"BasicText");
            SVGAndroidRenderer.supportedFeatures.add((Object)"PaintAttribute");
            SVGAndroidRenderer.supportedFeatures.add((Object)"BasicPaintAttribute");
            SVGAndroidRenderer.supportedFeatures.add((Object)"OpacityAttribute");
            SVGAndroidRenderer.supportedFeatures.add((Object)"BasicGraphicsAttribute");
            SVGAndroidRenderer.supportedFeatures.add((Object)"Marker");
            SVGAndroidRenderer.supportedFeatures.add((Object)"Gradient");
            SVGAndroidRenderer.supportedFeatures.add((Object)"Pattern");
            SVGAndroidRenderer.supportedFeatures.add((Object)"Clip");
            SVGAndroidRenderer.supportedFeatures.add((Object)"BasicClip");
            SVGAndroidRenderer.supportedFeatures.add((Object)"Mask");
            SVGAndroidRenderer.supportedFeatures.add((Object)"View");
        }
    }
    
    private boolean isSpecified(final Style style, final long n) {
        return (style.specifiedFlags & n) != 0x0L;
    }
    
    private void makeLinearGradient(final boolean b, final SVGBase.Box box, final SVGBase$SvgLinearGradient svgBase$SvgLinearGradient) {
        if (svgBase$SvgLinearGradient.href != null) {
            this.fillInChainedGradientFields((SVGBase$GradientElement)svgBase$SvgLinearGradient, svgBase$SvgLinearGradient.href);
        }
        final Boolean gradientUnitsAreUser = svgBase$SvgLinearGradient.gradientUnitsAreUser;
        final int n = 0;
        final boolean b2 = gradientUnitsAreUser != null && svgBase$SvgLinearGradient.gradientUnitsAreUser;
        final RendererState state = this.state;
        Paint paint;
        if (b) {
            paint = state.fillPaint;
        }
        else {
            paint = state.strokePaint;
        }
        float n4;
        float n6;
        float n7;
        float n8;
        if (b2) {
            final SVGBase.Box currentViewPortInUserUnits = this.getCurrentViewPortInUserUnits();
            float floatValueX;
            if (svgBase$SvgLinearGradient.x1 != null) {
                floatValueX = svgBase$SvgLinearGradient.x1.floatValueX(this);
            }
            else {
                floatValueX = 0.0f;
            }
            float floatValueY;
            if (svgBase$SvgLinearGradient.y1 != null) {
                floatValueY = svgBase$SvgLinearGradient.y1.floatValueY(this);
            }
            else {
                floatValueY = 0.0f;
            }
            float n2;
            if (svgBase$SvgLinearGradient.x2 != null) {
                n2 = svgBase$SvgLinearGradient.x2.floatValueX(this);
            }
            else {
                n2 = currentViewPortInUserUnits.width;
            }
            float floatValueY2;
            if (svgBase$SvgLinearGradient.y2 != null) {
                floatValueY2 = svgBase$SvgLinearGradient.y2.floatValueY(this);
            }
            else {
                floatValueY2 = 0.0f;
            }
            final float n3 = n2;
            n4 = floatValueX;
            final float n5 = floatValueY2;
            n6 = floatValueY;
            n7 = n3;
            n8 = n5;
        }
        else {
            float floatValue;
            if (svgBase$SvgLinearGradient.x1 != null) {
                floatValue = svgBase$SvgLinearGradient.x1.floatValue(this, 1.0f);
            }
            else {
                floatValue = 0.0f;
            }
            float floatValue2;
            if (svgBase$SvgLinearGradient.y1 != null) {
                floatValue2 = svgBase$SvgLinearGradient.y1.floatValue(this, 1.0f);
            }
            else {
                floatValue2 = 0.0f;
            }
            float floatValue3;
            if (svgBase$SvgLinearGradient.x2 != null) {
                floatValue3 = svgBase$SvgLinearGradient.x2.floatValue(this, 1.0f);
            }
            else {
                floatValue3 = 1.0f;
            }
            float floatValue4;
            if (svgBase$SvgLinearGradient.y2 != null) {
                floatValue4 = svgBase$SvgLinearGradient.y2.floatValue(this, 1.0f);
            }
            else {
                floatValue4 = 0.0f;
            }
            n8 = floatValue4;
            final float n9 = floatValue2;
            n7 = floatValue3;
            n6 = n9;
            n4 = floatValue;
        }
        this.statePush();
        this.state = this.findInheritFromAncestorState((SVGBase.SvgObject)svgBase$SvgLinearGradient);
        final Matrix localMatrix = new Matrix();
        if (!b2) {
            localMatrix.preTranslate(box.minX, box.minY);
            localMatrix.preScale(box.width, box.height);
        }
        if (svgBase$SvgLinearGradient.gradientTransform != null) {
            localMatrix.preConcat(svgBase$SvgLinearGradient.gradientTransform);
        }
        final int size = svgBase$SvgLinearGradient.children.size();
        if (size == 0) {
            this.statePop();
            if (b) {
                this.state.hasFill = false;
            }
            else {
                this.state.hasStroke = false;
            }
            return;
        }
        final int[] array = new int[size];
        final float[] array2 = new float[size];
        float n10 = -1.0f;
        final Iterator iterator = svgBase$SvgLinearGradient.children.iterator();
        int n11 = n;
        while (iterator.hasNext()) {
            final SVGBase$Stop svgBase$Stop = (SVGBase$Stop)iterator.next();
            float floatValue5;
            if (svgBase$Stop.offset != null) {
                floatValue5 = svgBase$Stop.offset;
            }
            else {
                floatValue5 = 0.0f;
            }
            if (n11 != 0 && floatValue5 < n10) {
                array2[n11] = n10;
            }
            else {
                array2[n11] = floatValue5;
                n10 = floatValue5;
            }
            this.statePush();
            this.updateStyleForElement(this.state, (SVGBase$SvgElementBase)svgBase$Stop);
            SVGBase$Colour black;
            if ((black = (SVGBase$Colour)this.state.style.stopColor) == null) {
                black = SVGBase$Colour.BLACK;
            }
            array[n11] = colourWithOpacity(black.colour, this.state.style.stopOpacity);
            ++n11;
            this.statePop();
        }
        if ((n4 == n7 && n6 == n8) || size == 1) {
            this.statePop();
            paint.setColor(array[size - 1]);
            return;
        }
        Shader$TileMode shader$TileMode = Shader$TileMode.CLAMP;
        if (svgBase$SvgLinearGradient.spreadMethod != null) {
            if (svgBase$SvgLinearGradient.spreadMethod == SVGBase.GradientSpread.reflect) {
                shader$TileMode = Shader$TileMode.MIRROR;
            }
            else {
                shader$TileMode = shader$TileMode;
                if (svgBase$SvgLinearGradient.spreadMethod == SVGBase.GradientSpread.repeat) {
                    shader$TileMode = Shader$TileMode.REPEAT;
                }
            }
        }
        this.statePop();
        final LinearGradient shader = new LinearGradient(n4, n6, n7, n8, array, array2, shader$TileMode);
        shader.setLocalMatrix(localMatrix);
        paint.setShader((Shader)shader);
        paint.setAlpha(clamp255(this.state.style.fillOpacity));
    }
    
    private Path makePathAndBoundingBox(final SVGBase$Circle svgBase$Circle) {
        final SVGBase.Length cx = svgBase$Circle.cx;
        float floatValueY = 0.0f;
        float floatValueX;
        if (cx != null) {
            floatValueX = svgBase$Circle.cx.floatValueX(this);
        }
        else {
            floatValueX = 0.0f;
        }
        if (svgBase$Circle.cy != null) {
            floatValueY = svgBase$Circle.cy.floatValueY(this);
        }
        final float floatValue = svgBase$Circle.r.floatValue(this);
        final float n = floatValueX - floatValue;
        final float n2 = floatValueY - floatValue;
        final float n3 = floatValueX + floatValue;
        final float n4 = floatValueY + floatValue;
        if (svgBase$Circle.boundingBox == null) {
            final float n5 = 2.0f * floatValue;
            svgBase$Circle.boundingBox = new SVGBase.Box(n, n2, n5, n5);
        }
        final float n6 = 0.5522848f * floatValue;
        final Path path = new Path();
        path.moveTo(floatValueX, n2);
        final float n7 = floatValueX + n6;
        final float n8 = floatValueY - n6;
        path.cubicTo(n7, n2, n3, n8, n3, floatValueY);
        final float n9 = floatValueY + n6;
        path.cubicTo(n3, n9, n7, n4, floatValueX, n4);
        final float n10 = floatValueX - n6;
        path.cubicTo(n10, n4, n, n9, n, floatValueY);
        path.cubicTo(n, n8, n10, n2, floatValueX, n2);
        path.close();
        return path;
    }
    
    private Path makePathAndBoundingBox(final SVGBase$Ellipse svgBase$Ellipse) {
        final SVGBase.Length cx = svgBase$Ellipse.cx;
        float floatValueY = 0.0f;
        float floatValueX;
        if (cx != null) {
            floatValueX = svgBase$Ellipse.cx.floatValueX(this);
        }
        else {
            floatValueX = 0.0f;
        }
        if (svgBase$Ellipse.cy != null) {
            floatValueY = svgBase$Ellipse.cy.floatValueY(this);
        }
        final float floatValueX2 = svgBase$Ellipse.rx.floatValueX(this);
        final float floatValueY2 = svgBase$Ellipse.ry.floatValueY(this);
        final float n = floatValueX - floatValueX2;
        final float n2 = floatValueY - floatValueY2;
        final float n3 = floatValueX + floatValueX2;
        final float n4 = floatValueY + floatValueY2;
        if (svgBase$Ellipse.boundingBox == null) {
            svgBase$Ellipse.boundingBox = new SVGBase.Box(n, n2, floatValueX2 * 2.0f, 2.0f * floatValueY2);
        }
        final float n5 = floatValueX2 * 0.5522848f;
        final float n6 = 0.5522848f * floatValueY2;
        final Path path = new Path();
        path.moveTo(floatValueX, n2);
        final float n7 = floatValueX + n5;
        final float n8 = floatValueY - n6;
        path.cubicTo(n7, n2, n3, n8, n3, floatValueY);
        final float n9 = n6 + floatValueY;
        path.cubicTo(n3, n9, n7, n4, floatValueX, n4);
        final float n10 = floatValueX - n5;
        path.cubicTo(n10, n4, n, n9, n, floatValueY);
        path.cubicTo(n, n8, n10, n2, floatValueX, n2);
        path.close();
        return path;
    }
    
    private Path makePathAndBoundingBox(final SVGBase$Line svgBase$Line) {
        final SVGBase.Length x1 = svgBase$Line.x1;
        float floatValueY = 0.0f;
        float floatValueX;
        if (x1 == null) {
            floatValueX = 0.0f;
        }
        else {
            floatValueX = svgBase$Line.x1.floatValueX(this);
        }
        float floatValueY2;
        if (svgBase$Line.y1 == null) {
            floatValueY2 = 0.0f;
        }
        else {
            floatValueY2 = svgBase$Line.y1.floatValueY(this);
        }
        float floatValueX2;
        if (svgBase$Line.x2 == null) {
            floatValueX2 = 0.0f;
        }
        else {
            floatValueX2 = svgBase$Line.x2.floatValueX(this);
        }
        if (svgBase$Line.y2 != null) {
            floatValueY = svgBase$Line.y2.floatValueY(this);
        }
        if (svgBase$Line.boundingBox == null) {
            svgBase$Line.boundingBox = new SVGBase.Box(Math.min(floatValueX, floatValueX2), Math.min(floatValueY2, floatValueY), Math.abs(floatValueX2 - floatValueX), Math.abs(floatValueY - floatValueY2));
        }
        final Path path = new Path();
        path.moveTo(floatValueX, floatValueY2);
        path.lineTo(floatValueX2, floatValueY);
        return path;
    }
    
    private Path makePathAndBoundingBox(final SVGBase$PolyLine svgBase$PolyLine) {
        final Path path = new Path();
        final float[] points = svgBase$PolyLine.points;
        int n = 0;
        int i;
        if (points != null) {
            i = svgBase$PolyLine.points.length;
        }
        else {
            i = 0;
        }
        if (i % 2 != 0) {
            return null;
        }
        if (i > 0) {
            while (i >= 2) {
                if (n == 0) {
                    path.moveTo(svgBase$PolyLine.points[n], svgBase$PolyLine.points[n + 1]);
                }
                else {
                    path.lineTo(svgBase$PolyLine.points[n], svgBase$PolyLine.points[n + 1]);
                }
                n += 2;
                i -= 2;
            }
            if (svgBase$PolyLine instanceof SVGBase$Polygon) {
                path.close();
            }
        }
        if (svgBase$PolyLine.boundingBox == null) {
            svgBase$PolyLine.boundingBox = this.calculatePathBounds(path);
        }
        return path;
    }
    
    private Path makePathAndBoundingBox(final SVGBase$Rect svgBase$Rect) {
        float floatValueX = 0.0f;
        float floatValueY = 0.0f;
        Label_0085: {
            if (svgBase$Rect.rx == null && svgBase$Rect.ry == null) {
                floatValueX = 0.0f;
                floatValueY = 0.0f;
            }
            else {
                float n;
                if (svgBase$Rect.rx == null) {
                    n = svgBase$Rect.ry.floatValueY(this);
                }
                else {
                    if (svgBase$Rect.ry != null) {
                        floatValueX = svgBase$Rect.rx.floatValueX(this);
                        floatValueY = svgBase$Rect.ry.floatValueY(this);
                        break Label_0085;
                    }
                    n = svgBase$Rect.rx.floatValueX(this);
                }
                final float n2 = n;
                floatValueX = n;
                floatValueY = n2;
            }
        }
        final float min = Math.min(floatValueX, svgBase$Rect.width.floatValueX(this) / 2.0f);
        final float min2 = Math.min(floatValueY, svgBase$Rect.height.floatValueY(this) / 2.0f);
        float floatValueX2;
        if (svgBase$Rect.x != null) {
            floatValueX2 = svgBase$Rect.x.floatValueX(this);
        }
        else {
            floatValueX2 = 0.0f;
        }
        float floatValueY2;
        if (svgBase$Rect.y != null) {
            floatValueY2 = svgBase$Rect.y.floatValueY(this);
        }
        else {
            floatValueY2 = 0.0f;
        }
        final float floatValueX3 = svgBase$Rect.width.floatValueX(this);
        final float floatValueY3 = svgBase$Rect.height.floatValueY(this);
        if (svgBase$Rect.boundingBox == null) {
            svgBase$Rect.boundingBox = new SVGBase.Box(floatValueX2, floatValueY2, floatValueX3, floatValueY3);
        }
        final float n3 = floatValueX2 + floatValueX3;
        final float n4 = floatValueY2 + floatValueY3;
        final Path path = new Path();
        if (min != 0.0f && min2 != 0.0f) {
            final float n5 = min * 0.5522848f;
            final float n6 = 0.5522848f * min2;
            final float n7 = floatValueY2 + min2;
            path.moveTo(floatValueX2, n7);
            final float n8 = n7 - n6;
            final float n9 = floatValueX2 + min;
            final float n10 = n9 - n5;
            path.cubicTo(floatValueX2, n8, n10, floatValueY2, n9, floatValueY2);
            final float n11 = n3 - min;
            path.lineTo(n11, floatValueY2);
            final float n12 = n11 + n5;
            path.cubicTo(n12, floatValueY2, n3, n8, n3, n7);
            final float n13 = n4 - min2;
            path.lineTo(n3, n13);
            final float n14 = n13 + n6;
            path.cubicTo(n3, n14, n12, n4, n11, n4);
            path.lineTo(n9, n4);
            path.cubicTo(n10, n4, floatValueX2, n14, floatValueX2, n13);
            path.lineTo(floatValueX2, n7);
        }
        else {
            path.moveTo(floatValueX2, floatValueY2);
            path.lineTo(n3, floatValueY2);
            path.lineTo(n3, n4);
            path.lineTo(floatValueX2, n4);
            path.lineTo(floatValueX2, floatValueY2);
        }
        path.close();
        return path;
    }
    
    private Path makePathAndBoundingBox(final SVGBase$Text svgBase$Text) {
        final List x = svgBase$Text.x;
        final float n = 0.0f;
        float floatValueX;
        if (x != null && svgBase$Text.x.size() != 0) {
            floatValueX = ((SVGBase.Length)svgBase$Text.x.get(0)).floatValueX(this);
        }
        else {
            floatValueX = 0.0f;
        }
        float floatValueY;
        if (svgBase$Text.y != null && svgBase$Text.y.size() != 0) {
            floatValueY = ((SVGBase.Length)svgBase$Text.y.get(0)).floatValueY(this);
        }
        else {
            floatValueY = 0.0f;
        }
        float floatValueX2;
        if (svgBase$Text.dx != null && svgBase$Text.dx.size() != 0) {
            floatValueX2 = ((SVGBase.Length)svgBase$Text.dx.get(0)).floatValueX(this);
        }
        else {
            floatValueX2 = 0.0f;
        }
        float floatValueY2 = n;
        if (svgBase$Text.dy != null) {
            if (svgBase$Text.dy.size() == 0) {
                floatValueY2 = n;
            }
            else {
                floatValueY2 = ((SVGBase.Length)svgBase$Text.dy.get(0)).floatValueY(this);
            }
        }
        float n2 = floatValueX;
        if (this.state.style.textAnchor != Style.TextAnchor.Start) {
            float calculateTextWidth = this.calculateTextWidth((SVGBase$TextContainer)svgBase$Text);
            if (this.state.style.textAnchor == Style.TextAnchor.Middle) {
                calculateTextWidth /= 2.0f;
            }
            n2 = floatValueX - calculateTextWidth;
        }
        if (svgBase$Text.boundingBox == null) {
            final SVGAndroidRenderer.SVGAndroidRenderer$TextBoundsCalculator svgAndroidRenderer$TextBoundsCalculator = new SVGAndroidRenderer.SVGAndroidRenderer$TextBoundsCalculator(this, n2, floatValueY);
            this.enumerateTextSpans((SVGBase$TextContainer)svgBase$Text, (TextProcessor)svgAndroidRenderer$TextBoundsCalculator);
            svgBase$Text.boundingBox = new SVGBase.Box(svgAndroidRenderer$TextBoundsCalculator.bbox.left, svgAndroidRenderer$TextBoundsCalculator.bbox.top, svgAndroidRenderer$TextBoundsCalculator.bbox.width(), svgAndroidRenderer$TextBoundsCalculator.bbox.height());
        }
        final Path path = new Path();
        this.enumerateTextSpans((SVGBase$TextContainer)svgBase$Text, (TextProcessor)new SVGAndroidRenderer.SVGAndroidRenderer$PlainTextToPath(this, n2 + floatValueX2, floatValueY + floatValueY2, path));
        return path;
    }
    
    private void makeRadialGradient(final boolean b, final SVGBase.Box box, final SVGBase$SvgRadialGradient svgBase$SvgRadialGradient) {
        if (svgBase$SvgRadialGradient.href != null) {
            this.fillInChainedGradientFields((SVGBase$GradientElement)svgBase$SvgRadialGradient, svgBase$SvgRadialGradient.href);
        }
        final Boolean gradientUnitsAreUser = svgBase$SvgRadialGradient.gradientUnitsAreUser;
        final int n = 0;
        final boolean b2 = gradientUnitsAreUser != null && svgBase$SvgRadialGradient.gradientUnitsAreUser;
        final RendererState state = this.state;
        Paint paint;
        if (b) {
            paint = state.fillPaint;
        }
        else {
            paint = state.strokePaint;
        }
        float n3;
        float n4;
        float n5;
        if (b2) {
            Object r = new SVGBase.Length(50.0f, SVGBase.Unit.percent);
            float n2;
            if (svgBase$SvgRadialGradient.cx != null) {
                n2 = svgBase$SvgRadialGradient.cx.floatValueX(this);
            }
            else {
                n2 = ((SVGBase.Length)r).floatValueX(this);
            }
            if (svgBase$SvgRadialGradient.cy != null) {
                n3 = svgBase$SvgRadialGradient.cy.floatValueY(this);
            }
            else {
                n3 = ((SVGBase.Length)r).floatValueY(this);
            }
            if (svgBase$SvgRadialGradient.r != null) {
                r = svgBase$SvgRadialGradient.r;
            }
            n4 = ((SVGBase.Length)r).floatValue(this);
            n5 = n2;
        }
        else {
            float floatValue;
            if (svgBase$SvgRadialGradient.cx != null) {
                floatValue = svgBase$SvgRadialGradient.cx.floatValue(this, 1.0f);
            }
            else {
                floatValue = 0.5f;
            }
            float floatValue2;
            if (svgBase$SvgRadialGradient.cy != null) {
                floatValue2 = svgBase$SvgRadialGradient.cy.floatValue(this, 1.0f);
            }
            else {
                floatValue2 = 0.5f;
            }
            if (svgBase$SvgRadialGradient.r != null) {
                n4 = svgBase$SvgRadialGradient.r.floatValue(this, 1.0f);
            }
            else {
                n4 = 0.5f;
            }
            n5 = floatValue;
            n3 = floatValue2;
        }
        this.statePush();
        this.state = this.findInheritFromAncestorState((SVGBase.SvgObject)svgBase$SvgRadialGradient);
        final Matrix localMatrix = new Matrix();
        if (!b2) {
            localMatrix.preTranslate(box.minX, box.minY);
            localMatrix.preScale(box.width, box.height);
        }
        if (svgBase$SvgRadialGradient.gradientTransform != null) {
            localMatrix.preConcat(svgBase$SvgRadialGradient.gradientTransform);
        }
        final int size = svgBase$SvgRadialGradient.children.size();
        if (size == 0) {
            this.statePop();
            if (b) {
                this.state.hasFill = false;
            }
            else {
                this.state.hasStroke = false;
            }
            return;
        }
        final int[] array = new int[size];
        final float[] array2 = new float[size];
        float n6 = -1.0f;
        final Iterator iterator = svgBase$SvgRadialGradient.children.iterator();
        int n7 = n;
        while (true) {
            final boolean hasNext = iterator.hasNext();
            float floatValue3 = 0.0f;
            if (!hasNext) {
                break;
            }
            final SVGBase$Stop svgBase$Stop = (SVGBase$Stop)iterator.next();
            if (svgBase$Stop.offset != null) {
                floatValue3 = svgBase$Stop.offset;
            }
            if (n7 != 0 && floatValue3 < n6) {
                array2[n7] = n6;
                floatValue3 = n6;
            }
            else {
                array2[n7] = floatValue3;
            }
            this.statePush();
            this.updateStyleForElement(this.state, (SVGBase$SvgElementBase)svgBase$Stop);
            SVGBase$Colour black;
            if ((black = (SVGBase$Colour)this.state.style.stopColor) == null) {
                black = SVGBase$Colour.BLACK;
            }
            array[n7] = colourWithOpacity(black.colour, this.state.style.stopOpacity);
            ++n7;
            this.statePop();
            n6 = floatValue3;
        }
        if (n4 != 0.0f && size != 1) {
            Shader$TileMode shader$TileMode = Shader$TileMode.CLAMP;
            if (svgBase$SvgRadialGradient.spreadMethod != null) {
                if (svgBase$SvgRadialGradient.spreadMethod == SVGBase.GradientSpread.reflect) {
                    shader$TileMode = Shader$TileMode.MIRROR;
                }
                else {
                    shader$TileMode = shader$TileMode;
                    if (svgBase$SvgRadialGradient.spreadMethod == SVGBase.GradientSpread.repeat) {
                        shader$TileMode = Shader$TileMode.REPEAT;
                    }
                }
            }
            this.statePop();
            final RadialGradient shader = new RadialGradient(n5, n3, n4, array, array2, shader$TileMode);
            shader.setLocalMatrix(localMatrix);
            paint.setShader((Shader)shader);
            paint.setAlpha(clamp255(this.state.style.fillOpacity));
            return;
        }
        this.statePop();
        paint.setColor(array[size - 1]);
    }
    
    private SVGBase.Box makeViewPort(final SVGBase.Length length, final SVGBase.Length length2, final SVGBase.Length length3, final SVGBase.Length length4) {
        float floatValueY = 0.0f;
        float floatValueX;
        if (length != null) {
            floatValueX = length.floatValueX(this);
        }
        else {
            floatValueX = 0.0f;
        }
        if (length2 != null) {
            floatValueY = length2.floatValueY(this);
        }
        final SVGBase.Box currentViewPortInUserUnits = this.getCurrentViewPortInUserUnits();
        float n;
        if (length3 != null) {
            n = length3.floatValueX(this);
        }
        else {
            n = currentViewPortInUserUnits.width;
        }
        float n2;
        if (length4 != null) {
            n2 = length4.floatValueY(this);
        }
        else {
            n2 = currentViewPortInUserUnits.height;
        }
        return new SVGBase.Box(floatValueX, floatValueY, n, n2);
    }
    
    private float measureText(final String s, final Paint paint) {
        final int length = s.length();
        final float[] array = new float[length];
        paint.getTextWidths(s, array);
        float n = 0.0f;
        for (int i = 0; i < length; ++i) {
            n += array[i];
        }
        return n;
    }
    
    private Path objectToPath(final SVGBase$SvgElement svgBase$SvgElement, final boolean b) {
        this.stateStack.push((Object)this.state);
        this.updateStyleForElement(this.state = new RendererState(this.state), (SVGBase$SvgElementBase)svgBase$SvgElement);
        if (this.display() && this.visible()) {
            Path path;
            if (svgBase$SvgElement instanceof SVGBase$Use) {
                if (!b) {
                    error("<use> elements inside a <clipPath> cannot reference another <use>", new Object[0]);
                }
                final SVGBase$Use svgBase$Use = (SVGBase$Use)svgBase$SvgElement;
                final SVGBase.SvgObject resolveIRI = svgBase$SvgElement.document.resolveIRI(svgBase$Use.href);
                if (resolveIRI == null) {
                    error("Use reference '%s' not found", svgBase$Use.href);
                    this.state = (RendererState)this.stateStack.pop();
                    return null;
                }
                if (!(resolveIRI instanceof SVGBase$SvgElement)) {
                    this.state = (RendererState)this.stateStack.pop();
                    return null;
                }
                final Path objectToPath = this.objectToPath((SVGBase$SvgElement)resolveIRI, false);
                if (objectToPath == null) {
                    return null;
                }
                if (svgBase$Use.boundingBox == null) {
                    svgBase$Use.boundingBox = this.calculatePathBounds(objectToPath);
                }
                path = objectToPath;
                if (svgBase$Use.transform != null) {
                    objectToPath.transform(svgBase$Use.transform);
                    path = objectToPath;
                }
            }
            else if (svgBase$SvgElement instanceof SVGBase$GraphicsElement) {
                final SVGBase$GraphicsElement svgBase$GraphicsElement = (SVGBase$GraphicsElement)svgBase$SvgElement;
                if (svgBase$SvgElement instanceof SVGBase$Path) {
                    final Path path2 = path = new SVGAndroidRenderer.SVGAndroidRenderer$PathConverter(((SVGBase$Path)svgBase$SvgElement).d).getPath();
                    if (svgBase$SvgElement.boundingBox == null) {
                        svgBase$SvgElement.boundingBox = this.calculatePathBounds(path2);
                        path = path2;
                    }
                }
                else if (svgBase$SvgElement instanceof SVGBase$Rect) {
                    path = this.makePathAndBoundingBox((SVGBase$Rect)svgBase$SvgElement);
                }
                else if (svgBase$SvgElement instanceof SVGBase$Circle) {
                    path = this.makePathAndBoundingBox((SVGBase$Circle)svgBase$SvgElement);
                }
                else if (svgBase$SvgElement instanceof SVGBase$Ellipse) {
                    path = this.makePathAndBoundingBox((SVGBase$Ellipse)svgBase$SvgElement);
                }
                else if (svgBase$SvgElement instanceof SVGBase$PolyLine) {
                    path = this.makePathAndBoundingBox((SVGBase$PolyLine)svgBase$SvgElement);
                }
                else {
                    path = null;
                }
                if (path == null) {
                    return null;
                }
                if (svgBase$GraphicsElement.boundingBox == null) {
                    svgBase$GraphicsElement.boundingBox = this.calculatePathBounds(path);
                }
                if (svgBase$GraphicsElement.transform != null) {
                    path.transform(svgBase$GraphicsElement.transform);
                }
                path.setFillType(this.getClipRuleFromState());
            }
            else {
                if (!(svgBase$SvgElement instanceof SVGBase$Text)) {
                    error("Invalid %s element found in clipPath definition", svgBase$SvgElement.getNodeName());
                    return null;
                }
                final SVGBase$Text svgBase$Text = (SVGBase$Text)svgBase$SvgElement;
                path = this.makePathAndBoundingBox(svgBase$Text);
                if (svgBase$Text.transform != null) {
                    path.transform(svgBase$Text.transform);
                }
                path.setFillType(this.getClipRuleFromState());
            }
            if (this.state.style.clipPath != null) {
                final Path calculateClipPath = this.calculateClipPath(svgBase$SvgElement, svgBase$SvgElement.boundingBox);
                if (calculateClipPath != null) {
                    path.op(calculateClipPath, Path$Op.INTERSECT);
                }
            }
            this.state = (RendererState)this.stateStack.pop();
            return path;
        }
        this.state = (RendererState)this.stateStack.pop();
        return null;
    }
    
    private void parentPop() {
        this.parentStack.pop();
        this.matrixStack.pop();
    }
    
    private void parentPush(final SVGBase.SvgContainer svgContainer) {
        this.parentStack.push((Object)svgContainer);
        this.matrixStack.push((Object)this.canvas.getMatrix());
    }
    
    private void popLayer(final SVGBase$SvgElement svgBase$SvgElement) {
        this.popLayer(svgBase$SvgElement, svgBase$SvgElement.boundingBox);
    }
    
    private void popLayer(final SVGBase$SvgElement svgBase$SvgElement, final SVGBase.Box box) {
        if (this.state.style.mask != null) {
            final Paint paint = new Paint();
            paint.setXfermode((Xfermode)new PorterDuffXfermode(PorterDuff$Mode.DST_IN));
            this.canvas.saveLayer((RectF)null, paint, 31);
            final Paint paint2 = new Paint();
            paint2.setColorFilter((ColorFilter)new ColorMatrixColorFilter(new ColorMatrix(new float[] { 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.2127f, 0.7151f, 0.0722f, 0.0f, 0.0f })));
            this.canvas.saveLayer((RectF)null, paint2, 31);
            final SVGBase$Mask svgBase$Mask = (SVGBase$Mask)this.document.resolveIRI(this.state.style.mask);
            this.renderMask(svgBase$Mask, svgBase$SvgElement, box);
            this.canvas.restore();
            final Paint paint3 = new Paint();
            paint3.setXfermode((Xfermode)new PorterDuffXfermode(PorterDuff$Mode.DST_IN));
            this.canvas.saveLayer((RectF)null, paint3, 31);
            this.renderMask(svgBase$Mask, svgBase$SvgElement, box);
            this.canvas.restore();
            this.canvas.restore();
        }
        this.statePop();
    }
    
    private void processTextChild(SVGBase.SvgObject resolveIRI, final TextProcessor textProcessor) {
        if (!textProcessor.doTextContainer((SVGBase$TextContainer)resolveIRI)) {
            return;
        }
        if (resolveIRI instanceof SVGBase$TextPath) {
            this.statePush();
            this.renderTextPath((SVGBase$TextPath)resolveIRI);
            this.statePop();
        }
        else {
            final boolean b = resolveIRI instanceof SVGBase$TSpan;
            boolean b2 = true;
            if (b) {
                debug("TSpan render", new Object[0]);
                this.statePush();
                final SVGBase$TSpan svgBase$TSpan = (SVGBase$TSpan)resolveIRI;
                this.updateStyleForElement(this.state, (SVGBase$SvgElementBase)svgBase$TSpan);
                if (this.display()) {
                    this.selectTypefaceAndFontStyling();
                    if (svgBase$TSpan.x == null || svgBase$TSpan.x.size() <= 0) {
                        b2 = false;
                    }
                    final boolean b3 = textProcessor instanceof SVGAndroidRenderer.SVGAndroidRenderer$PlainTextDrawer;
                    float n = 0.0f;
                    final float n2 = 0.0f;
                    float n3;
                    float floatValueX;
                    float floatValueY;
                    if (b3) {
                        if (!b2) {
                            n = ((SVGAndroidRenderer.SVGAndroidRenderer$PlainTextDrawer)textProcessor).x;
                        }
                        else {
                            n = ((SVGBase.Length)svgBase$TSpan.x.get(0)).floatValueX(this);
                        }
                        if (svgBase$TSpan.y != null && svgBase$TSpan.y.size() != 0) {
                            n3 = ((SVGBase.Length)svgBase$TSpan.y.get(0)).floatValueY(this);
                        }
                        else {
                            n3 = ((SVGAndroidRenderer.SVGAndroidRenderer$PlainTextDrawer)textProcessor).y;
                        }
                        if (svgBase$TSpan.dx != null && svgBase$TSpan.dx.size() != 0) {
                            floatValueX = ((SVGBase.Length)svgBase$TSpan.dx.get(0)).floatValueX(this);
                        }
                        else {
                            floatValueX = 0.0f;
                        }
                        floatValueY = n2;
                        if (svgBase$TSpan.dy != null) {
                            if (svgBase$TSpan.dy.size() == 0) {
                                floatValueY = n2;
                            }
                            else {
                                floatValueY = ((SVGBase.Length)svgBase$TSpan.dy.get(0)).floatValueY(this);
                            }
                        }
                    }
                    else {
                        floatValueY = 0.0f;
                        n3 = 0.0f;
                        floatValueX = 0.0f;
                    }
                    float n4 = n;
                    if (b2) {
                        final Style.TextAnchor anchorPosition = this.getAnchorPosition();
                        n4 = n;
                        if (anchorPosition != Style.TextAnchor.Start) {
                            float calculateTextWidth = this.calculateTextWidth((SVGBase$TextContainer)svgBase$TSpan);
                            if (anchorPosition == Style.TextAnchor.Middle) {
                                calculateTextWidth /= 2.0f;
                            }
                            n4 = n - calculateTextWidth;
                        }
                    }
                    this.checkForGradientsAndPatterns((SVGBase$SvgElement)svgBase$TSpan.getTextRoot());
                    if (b3) {
                        final SVGAndroidRenderer.SVGAndroidRenderer$PlainTextDrawer svgAndroidRenderer$PlainTextDrawer = (SVGAndroidRenderer.SVGAndroidRenderer$PlainTextDrawer)textProcessor;
                        svgAndroidRenderer$PlainTextDrawer.x = n4 + floatValueX;
                        svgAndroidRenderer$PlainTextDrawer.y = n3 + floatValueY;
                    }
                    final boolean pushLayer = this.pushLayer();
                    this.enumerateTextSpans((SVGBase$TextContainer)svgBase$TSpan, textProcessor);
                    if (pushLayer) {
                        this.popLayer((SVGBase$SvgElement)svgBase$TSpan);
                    }
                }
                this.statePop();
            }
            else if (resolveIRI instanceof SVGBase$TRef) {
                this.statePush();
                final SVGBase$TRef svgBase$TRef = (SVGBase$TRef)resolveIRI;
                this.updateStyleForElement(this.state, (SVGBase$SvgElementBase)svgBase$TRef);
                if (this.display()) {
                    this.checkForGradientsAndPatterns((SVGBase$SvgElement)svgBase$TRef.getTextRoot());
                    resolveIRI = resolveIRI.document.resolveIRI(svgBase$TRef.href);
                    if (resolveIRI instanceof SVGBase$TextContainer) {
                        final StringBuilder sb = new StringBuilder();
                        this.extractRawText((SVGBase$TextContainer)resolveIRI, sb);
                        if (sb.length() > 0) {
                            textProcessor.processText(sb.toString());
                        }
                    }
                    else {
                        error("Tref reference '%s' not found", svgBase$TRef.href);
                    }
                }
                this.statePop();
            }
        }
    }
    
    private boolean pushLayer() {
        return this.pushLayer(1.0f);
    }
    
    private boolean pushLayer(final float n) {
        if (!this.requiresCompositing() && n == 1.0f) {
            return false;
        }
        final Paint blendMode = new Paint();
        blendMode.setAlpha(clamp255(this.state.style.opacity * n));
        if (SVGAndroidRenderer.SUPPORTS_BLEND_MODE && this.state.style.mixBlendMode != Style.CSSBlendMode.normal) {
            this.setBlendMode(blendMode);
        }
        this.canvas.saveLayer((RectF)null, blendMode, 31);
        this.stateStack.push((Object)this.state);
        final RendererState state = new RendererState(this.state);
        this.state = state;
        if (state.style.mask != null && !(this.document.resolveIRI(this.state.style.mask) instanceof SVGBase$Mask)) {
            error("Mask reference '%s' not found", this.state.style.mask);
            this.state.style.mask = null;
        }
        return true;
    }
    
    private MarkerVector realignMarkerMid(final MarkerVector markerVector, final MarkerVector markerVector2, final MarkerVector markerVector3) {
        float n;
        if ((n = this.dotProduct(markerVector2.dx, markerVector2.dy, markerVector2.x - markerVector.x, markerVector2.y - markerVector.y)) == 0.0f) {
            n = this.dotProduct(markerVector2.dx, markerVector2.dy, markerVector3.x - markerVector2.x, markerVector3.y - markerVector2.y);
        }
        final float n2 = fcmpl(n, 0.0f);
        if (n2 > 0) {
            return markerVector2;
        }
        if (n2 == 0 && (markerVector2.dx > 0.0f || markerVector2.dy >= 0.0f)) {
            return markerVector2;
        }
        markerVector2.dx = -markerVector2.dx;
        markerVector2.dy = -markerVector2.dy;
        return markerVector2;
    }
    
    private void render(final SVGBase$Circle svgBase$Circle) {
        debug("Circle render", new Object[0]);
        if (svgBase$Circle.r != null) {
            if (!svgBase$Circle.r.isZero()) {
                this.updateStyleForElement(this.state, (SVGBase$SvgElementBase)svgBase$Circle);
                if (!this.display()) {
                    return;
                }
                if (!this.visible()) {
                    return;
                }
                if (svgBase$Circle.transform != null) {
                    this.canvas.concat(svgBase$Circle.transform);
                }
                final Path pathAndBoundingBox = this.makePathAndBoundingBox(svgBase$Circle);
                this.updateParentBoundingBox((SVGBase$SvgElement)svgBase$Circle);
                this.checkForGradientsAndPatterns((SVGBase$SvgElement)svgBase$Circle);
                this.checkForClipPath((SVGBase$SvgElement)svgBase$Circle);
                final boolean pushLayer = this.pushLayer();
                if (this.state.hasFill) {
                    this.doFilledPath((SVGBase$SvgElement)svgBase$Circle, pathAndBoundingBox);
                }
                if (this.state.hasStroke) {
                    this.doStroke(pathAndBoundingBox);
                }
                if (pushLayer) {
                    this.popLayer((SVGBase$SvgElement)svgBase$Circle);
                }
            }
        }
    }
    
    private void render(final SVGBase$Ellipse svgBase$Ellipse) {
        debug("Ellipse render", new Object[0]);
        if (svgBase$Ellipse.rx != null && svgBase$Ellipse.ry != null && !svgBase$Ellipse.rx.isZero()) {
            if (!svgBase$Ellipse.ry.isZero()) {
                this.updateStyleForElement(this.state, (SVGBase$SvgElementBase)svgBase$Ellipse);
                if (!this.display()) {
                    return;
                }
                if (!this.visible()) {
                    return;
                }
                if (svgBase$Ellipse.transform != null) {
                    this.canvas.concat(svgBase$Ellipse.transform);
                }
                final Path pathAndBoundingBox = this.makePathAndBoundingBox(svgBase$Ellipse);
                this.updateParentBoundingBox((SVGBase$SvgElement)svgBase$Ellipse);
                this.checkForGradientsAndPatterns((SVGBase$SvgElement)svgBase$Ellipse);
                this.checkForClipPath((SVGBase$SvgElement)svgBase$Ellipse);
                final boolean pushLayer = this.pushLayer();
                if (this.state.hasFill) {
                    this.doFilledPath((SVGBase$SvgElement)svgBase$Ellipse, pathAndBoundingBox);
                }
                if (this.state.hasStroke) {
                    this.doStroke(pathAndBoundingBox);
                }
                if (pushLayer) {
                    this.popLayer((SVGBase$SvgElement)svgBase$Ellipse);
                }
            }
        }
    }
    
    private void render(final SVGBase$Group svgBase$Group) {
        final StringBuilder sb = new StringBuilder();
        sb.append(svgBase$Group.getNodeName());
        sb.append(" render");
        debug(sb.toString(), new Object[0]);
        this.updateStyleForElement(this.state, (SVGBase$SvgElementBase)svgBase$Group);
        if (!this.display()) {
            return;
        }
        if (svgBase$Group.transform != null) {
            this.canvas.concat(svgBase$Group.transform);
        }
        this.checkForClipPath((SVGBase$SvgElement)svgBase$Group);
        final boolean pushLayer = this.pushLayer();
        this.renderChildren((SVGBase.SvgContainer)svgBase$Group, true);
        if (pushLayer) {
            this.popLayer((SVGBase$SvgElement)svgBase$Group);
        }
        this.updateParentBoundingBox((SVGBase$SvgElement)svgBase$Group);
    }
    
    private void render(final SVGBase$Image svgBase$Image) {
        int n = 0;
        debug("Image render", new Object[0]);
        if (svgBase$Image.width != null && !svgBase$Image.width.isZero() && svgBase$Image.height != null) {
            if (!svgBase$Image.height.isZero()) {
                if (svgBase$Image.href == null) {
                    return;
                }
                PreserveAspectRatio preserveAspectRatio;
                if (svgBase$Image.preserveAspectRatio != null) {
                    preserveAspectRatio = svgBase$Image.preserveAspectRatio;
                }
                else {
                    preserveAspectRatio = PreserveAspectRatio.LETTERBOX;
                }
                Bitmap bitmap;
                if ((bitmap = this.checkForImageDataURL(svgBase$Image.href)) == null) {
                    final SVGExternalFileResolver externalFileResolver = this.externalFileResolver;
                    if (externalFileResolver == null) {
                        return;
                    }
                    bitmap = externalFileResolver.resolveImage(svgBase$Image.href);
                }
                if (bitmap == null) {
                    error("Could not locate image '%s'", svgBase$Image.href);
                    return;
                }
                final SVGBase.Box box = new SVGBase.Box(0.0f, 0.0f, (float)bitmap.getWidth(), (float)bitmap.getHeight());
                this.updateStyleForElement(this.state, (SVGBase$SvgElementBase)svgBase$Image);
                if (!this.display()) {
                    return;
                }
                if (!this.visible()) {
                    return;
                }
                if (svgBase$Image.transform != null) {
                    this.canvas.concat(svgBase$Image.transform);
                }
                float floatValueX;
                if (svgBase$Image.x != null) {
                    floatValueX = svgBase$Image.x.floatValueX(this);
                }
                else {
                    floatValueX = 0.0f;
                }
                float floatValueY;
                if (svgBase$Image.y != null) {
                    floatValueY = svgBase$Image.y.floatValueY(this);
                }
                else {
                    floatValueY = 0.0f;
                }
                this.state.viewPort = new SVGBase.Box(floatValueX, floatValueY, svgBase$Image.width.floatValueX(this), svgBase$Image.height.floatValueX(this));
                if (!this.state.style.overflow) {
                    this.setClipRect(this.state.viewPort.minX, this.state.viewPort.minY, this.state.viewPort.width, this.state.viewPort.height);
                }
                svgBase$Image.boundingBox = this.state.viewPort;
                this.updateParentBoundingBox((SVGBase$SvgElement)svgBase$Image);
                this.checkForClipPath((SVGBase$SvgElement)svgBase$Image);
                final boolean pushLayer = this.pushLayer();
                this.viewportFill();
                this.canvas.save();
                this.canvas.concat(this.calculateViewBoxTransform(this.state.viewPort, box, preserveAspectRatio));
                if (this.state.style.imageRendering != Style.RenderQuality.optimizeSpeed) {
                    n = 2;
                }
                this.canvas.drawBitmap(bitmap, 0.0f, 0.0f, new Paint(n));
                this.canvas.restore();
                if (pushLayer) {
                    this.popLayer((SVGBase$SvgElement)svgBase$Image);
                }
            }
        }
    }
    
    private void render(final SVGBase$Line svgBase$Line) {
        debug("Line render", new Object[0]);
        this.updateStyleForElement(this.state, (SVGBase$SvgElementBase)svgBase$Line);
        if (!this.display()) {
            return;
        }
        if (!this.visible()) {
            return;
        }
        if (!this.state.hasStroke) {
            return;
        }
        if (svgBase$Line.transform != null) {
            this.canvas.concat(svgBase$Line.transform);
        }
        final Path pathAndBoundingBox = this.makePathAndBoundingBox(svgBase$Line);
        this.updateParentBoundingBox((SVGBase$SvgElement)svgBase$Line);
        this.checkForGradientsAndPatterns((SVGBase$SvgElement)svgBase$Line);
        this.checkForClipPath((SVGBase$SvgElement)svgBase$Line);
        final boolean pushLayer = this.pushLayer();
        this.doStroke(pathAndBoundingBox);
        this.renderMarkers((SVGBase$GraphicsElement)svgBase$Line);
        if (pushLayer) {
            this.popLayer((SVGBase$SvgElement)svgBase$Line);
        }
    }
    
    private void render(final SVGBase$Path svgBase$Path) {
        debug("Path render", new Object[0]);
        if (svgBase$Path.d == null) {
            return;
        }
        this.updateStyleForElement(this.state, (SVGBase$SvgElementBase)svgBase$Path);
        if (!this.display()) {
            return;
        }
        if (!this.visible()) {
            return;
        }
        if (!this.state.hasStroke && !this.state.hasFill) {
            return;
        }
        if (svgBase$Path.transform != null) {
            this.canvas.concat(svgBase$Path.transform);
        }
        final Path path = new SVGAndroidRenderer.SVGAndroidRenderer$PathConverter(svgBase$Path.d).getPath();
        if (svgBase$Path.boundingBox == null) {
            svgBase$Path.boundingBox = this.calculatePathBounds(path);
        }
        this.updateParentBoundingBox((SVGBase$SvgElement)svgBase$Path);
        this.checkForGradientsAndPatterns((SVGBase$SvgElement)svgBase$Path);
        this.checkForClipPath((SVGBase$SvgElement)svgBase$Path);
        final boolean pushLayer = this.pushLayer();
        if (this.state.hasFill) {
            path.setFillType(this.getFillTypeFromState());
            this.doFilledPath((SVGBase$SvgElement)svgBase$Path, path);
        }
        if (this.state.hasStroke) {
            this.doStroke(path);
        }
        this.renderMarkers((SVGBase$GraphicsElement)svgBase$Path);
        if (pushLayer) {
            this.popLayer((SVGBase$SvgElement)svgBase$Path);
        }
    }
    
    private void render(final SVGBase$PolyLine svgBase$PolyLine) {
        int length = 0;
        debug("PolyLine render", new Object[0]);
        this.updateStyleForElement(this.state, (SVGBase$SvgElementBase)svgBase$PolyLine);
        if (!this.display()) {
            return;
        }
        if (!this.visible()) {
            return;
        }
        if (!this.state.hasStroke && !this.state.hasFill) {
            return;
        }
        if (svgBase$PolyLine.transform != null) {
            this.canvas.concat(svgBase$PolyLine.transform);
        }
        if (svgBase$PolyLine.points != null) {
            length = svgBase$PolyLine.points.length;
        }
        if (length >= 2) {
            if (length % 2 != 1) {
                final Path pathAndBoundingBox = this.makePathAndBoundingBox(svgBase$PolyLine);
                this.updateParentBoundingBox((SVGBase$SvgElement)svgBase$PolyLine);
                pathAndBoundingBox.setFillType(this.getFillTypeFromState());
                this.checkForGradientsAndPatterns((SVGBase$SvgElement)svgBase$PolyLine);
                this.checkForClipPath((SVGBase$SvgElement)svgBase$PolyLine);
                final boolean pushLayer = this.pushLayer();
                if (this.state.hasFill) {
                    this.doFilledPath((SVGBase$SvgElement)svgBase$PolyLine, pathAndBoundingBox);
                }
                if (this.state.hasStroke) {
                    this.doStroke(pathAndBoundingBox);
                }
                this.renderMarkers((SVGBase$GraphicsElement)svgBase$PolyLine);
                if (pushLayer) {
                    this.popLayer((SVGBase$SvgElement)svgBase$PolyLine);
                }
            }
        }
    }
    
    private void render(final SVGBase$Polygon svgBase$Polygon) {
        int length = 0;
        debug("Polygon render", new Object[0]);
        this.updateStyleForElement(this.state, (SVGBase$SvgElementBase)svgBase$Polygon);
        if (!this.display()) {
            return;
        }
        if (!this.visible()) {
            return;
        }
        if (!this.state.hasStroke && !this.state.hasFill) {
            return;
        }
        if (svgBase$Polygon.transform != null) {
            this.canvas.concat(svgBase$Polygon.transform);
        }
        if (svgBase$Polygon.points != null) {
            length = svgBase$Polygon.points.length;
        }
        if (length < 2) {
            return;
        }
        final Path pathAndBoundingBox = this.makePathAndBoundingBox((SVGBase$PolyLine)svgBase$Polygon);
        this.updateParentBoundingBox((SVGBase$SvgElement)svgBase$Polygon);
        this.checkForGradientsAndPatterns((SVGBase$SvgElement)svgBase$Polygon);
        this.checkForClipPath((SVGBase$SvgElement)svgBase$Polygon);
        final boolean pushLayer = this.pushLayer();
        if (this.state.hasFill) {
            this.doFilledPath((SVGBase$SvgElement)svgBase$Polygon, pathAndBoundingBox);
        }
        if (this.state.hasStroke) {
            this.doStroke(pathAndBoundingBox);
        }
        this.renderMarkers((SVGBase$GraphicsElement)svgBase$Polygon);
        if (pushLayer) {
            this.popLayer((SVGBase$SvgElement)svgBase$Polygon);
        }
    }
    
    private void render(final SVGBase$Rect svgBase$Rect) {
        debug("Rect render", new Object[0]);
        if (svgBase$Rect.width != null && svgBase$Rect.height != null && !svgBase$Rect.width.isZero()) {
            if (!svgBase$Rect.height.isZero()) {
                this.updateStyleForElement(this.state, (SVGBase$SvgElementBase)svgBase$Rect);
                if (!this.display()) {
                    return;
                }
                if (!this.visible()) {
                    return;
                }
                if (svgBase$Rect.transform != null) {
                    this.canvas.concat(svgBase$Rect.transform);
                }
                final Path pathAndBoundingBox = this.makePathAndBoundingBox(svgBase$Rect);
                this.updateParentBoundingBox((SVGBase$SvgElement)svgBase$Rect);
                this.checkForGradientsAndPatterns((SVGBase$SvgElement)svgBase$Rect);
                this.checkForClipPath((SVGBase$SvgElement)svgBase$Rect);
                final boolean pushLayer = this.pushLayer();
                if (this.state.hasFill) {
                    this.doFilledPath((SVGBase$SvgElement)svgBase$Rect, pathAndBoundingBox);
                }
                if (this.state.hasStroke) {
                    this.doStroke(pathAndBoundingBox);
                }
                if (pushLayer) {
                    this.popLayer((SVGBase$SvgElement)svgBase$Rect);
                }
            }
        }
    }
    
    private void render(final SVGBase$Svg svgBase$Svg) {
        this.render(svgBase$Svg, this.makeViewPort(svgBase$Svg.x, svgBase$Svg.y, svgBase$Svg.width, svgBase$Svg.height), svgBase$Svg.viewBox, svgBase$Svg.preserveAspectRatio);
    }
    
    private void render(final SVGBase$Svg svgBase$Svg, final SVGBase.Box box) {
        this.render(svgBase$Svg, box, svgBase$Svg.viewBox, svgBase$Svg.preserveAspectRatio);
    }
    
    private void render(final SVGBase$Svg svgBase$Svg, final SVGBase.Box viewPort, final SVGBase.Box box, final PreserveAspectRatio preserveAspectRatio) {
        debug("Svg render", new Object[0]);
        if (viewPort.width != 0.0f) {
            if (viewPort.height != 0.0f) {
                PreserveAspectRatio preserveAspectRatio2;
                if ((preserveAspectRatio2 = preserveAspectRatio) == null) {
                    if (svgBase$Svg.preserveAspectRatio != null) {
                        preserveAspectRatio2 = svgBase$Svg.preserveAspectRatio;
                    }
                    else {
                        preserveAspectRatio2 = PreserveAspectRatio.LETTERBOX;
                    }
                }
                this.updateStyleForElement(this.state, (SVGBase$SvgElementBase)svgBase$Svg);
                if (!this.display()) {
                    return;
                }
                this.state.viewPort = viewPort;
                if (!this.state.style.overflow) {
                    this.setClipRect(this.state.viewPort.minX, this.state.viewPort.minY, this.state.viewPort.width, this.state.viewPort.height);
                }
                this.checkForClipPath((SVGBase$SvgElement)svgBase$Svg, this.state.viewPort);
                if (box != null) {
                    this.canvas.concat(this.calculateViewBoxTransform(this.state.viewPort, box, preserveAspectRatio2));
                    this.state.viewBox = svgBase$Svg.viewBox;
                }
                else {
                    this.canvas.translate(this.state.viewPort.minX, this.state.viewPort.minY);
                }
                final boolean pushLayer = this.pushLayer();
                this.viewportFill();
                this.renderChildren((SVGBase.SvgContainer)svgBase$Svg, true);
                if (pushLayer) {
                    this.popLayer((SVGBase$SvgElement)svgBase$Svg);
                }
                this.updateParentBoundingBox((SVGBase$SvgElement)svgBase$Svg);
            }
        }
    }
    
    private void render(final SVGBase.SvgObject svgObject) {
        if (svgObject instanceof SVGBase.NotDirectlyRendered) {
            return;
        }
        this.statePush();
        this.checkXMLSpaceAttribute(svgObject);
        if (svgObject instanceof SVGBase$Svg) {
            this.render((SVGBase$Svg)svgObject);
        }
        else if (svgObject instanceof SVGBase$Use) {
            this.render((SVGBase$Use)svgObject);
        }
        else if (svgObject instanceof SVGBase$Switch) {
            this.render((SVGBase$Switch)svgObject);
        }
        else if (svgObject instanceof SVGBase$Group) {
            this.render((SVGBase$Group)svgObject);
        }
        else if (svgObject instanceof SVGBase$Image) {
            this.render((SVGBase$Image)svgObject);
        }
        else if (svgObject instanceof SVGBase$Path) {
            this.render((SVGBase$Path)svgObject);
        }
        else if (svgObject instanceof SVGBase$Rect) {
            this.render((SVGBase$Rect)svgObject);
        }
        else if (svgObject instanceof SVGBase$Circle) {
            this.render((SVGBase$Circle)svgObject);
        }
        else if (svgObject instanceof SVGBase$Ellipse) {
            this.render((SVGBase$Ellipse)svgObject);
        }
        else if (svgObject instanceof SVGBase$Line) {
            this.render((SVGBase$Line)svgObject);
        }
        else if (svgObject instanceof SVGBase$Polygon) {
            this.render((SVGBase$Polygon)svgObject);
        }
        else if (svgObject instanceof SVGBase$PolyLine) {
            this.render((SVGBase$PolyLine)svgObject);
        }
        else if (svgObject instanceof SVGBase$Text) {
            this.render((SVGBase$Text)svgObject);
        }
        this.statePop();
    }
    
    private void render(final SVGBase$Switch svgBase$Switch) {
        debug("Switch render", new Object[0]);
        this.updateStyleForElement(this.state, (SVGBase$SvgElementBase)svgBase$Switch);
        if (!this.display()) {
            return;
        }
        if (svgBase$Switch.transform != null) {
            this.canvas.concat(svgBase$Switch.transform);
        }
        this.checkForClipPath((SVGBase$SvgElement)svgBase$Switch);
        final boolean pushLayer = this.pushLayer();
        this.renderSwitchChild(svgBase$Switch);
        if (pushLayer) {
            this.popLayer((SVGBase$SvgElement)svgBase$Switch);
        }
        this.updateParentBoundingBox((SVGBase$SvgElement)svgBase$Switch);
    }
    
    private void render(final SVGBase$Symbol svgBase$Symbol, final SVGBase.Box viewPort) {
        debug("Symbol render", new Object[0]);
        if (viewPort.width != 0.0f) {
            if (viewPort.height != 0.0f) {
                PreserveAspectRatio preserveAspectRatio;
                if (svgBase$Symbol.preserveAspectRatio != null) {
                    preserveAspectRatio = svgBase$Symbol.preserveAspectRatio;
                }
                else {
                    preserveAspectRatio = PreserveAspectRatio.LETTERBOX;
                }
                this.updateStyleForElement(this.state, (SVGBase$SvgElementBase)svgBase$Symbol);
                this.state.viewPort = viewPort;
                if (!this.state.style.overflow) {
                    this.setClipRect(this.state.viewPort.minX, this.state.viewPort.minY, this.state.viewPort.width, this.state.viewPort.height);
                }
                if (svgBase$Symbol.viewBox != null) {
                    this.canvas.concat(this.calculateViewBoxTransform(this.state.viewPort, svgBase$Symbol.viewBox, preserveAspectRatio));
                    this.state.viewBox = svgBase$Symbol.viewBox;
                }
                else {
                    this.canvas.translate(this.state.viewPort.minX, this.state.viewPort.minY);
                }
                final boolean pushLayer = this.pushLayer();
                this.renderChildren((SVGBase.SvgContainer)svgBase$Symbol, true);
                if (pushLayer) {
                    this.popLayer((SVGBase$SvgElement)svgBase$Symbol);
                }
                this.updateParentBoundingBox((SVGBase$SvgElement)svgBase$Symbol);
            }
        }
    }
    
    private void render(final SVGBase$Text svgBase$Text) {
        debug("Text render", new Object[0]);
        this.updateStyleForElement(this.state, (SVGBase$SvgElementBase)svgBase$Text);
        if (!this.display()) {
            return;
        }
        this.selectTypefaceAndFontStyling();
        if (svgBase$Text.transform != null) {
            this.canvas.concat(svgBase$Text.transform);
        }
        final List x = svgBase$Text.x;
        final float n = 0.0f;
        float floatValueX;
        if (x != null && svgBase$Text.x.size() != 0) {
            floatValueX = ((SVGBase.Length)svgBase$Text.x.get(0)).floatValueX(this);
        }
        else {
            floatValueX = 0.0f;
        }
        float floatValueY;
        if (svgBase$Text.y != null && svgBase$Text.y.size() != 0) {
            floatValueY = ((SVGBase.Length)svgBase$Text.y.get(0)).floatValueY(this);
        }
        else {
            floatValueY = 0.0f;
        }
        float floatValueX2;
        if (svgBase$Text.dx != null && svgBase$Text.dx.size() != 0) {
            floatValueX2 = ((SVGBase.Length)svgBase$Text.dx.get(0)).floatValueX(this);
        }
        else {
            floatValueX2 = 0.0f;
        }
        float floatValueY2 = n;
        if (svgBase$Text.dy != null) {
            if (svgBase$Text.dy.size() == 0) {
                floatValueY2 = n;
            }
            else {
                floatValueY2 = ((SVGBase.Length)svgBase$Text.dy.get(0)).floatValueY(this);
            }
        }
        final Style.TextAnchor anchorPosition = this.getAnchorPosition();
        float n2 = floatValueX;
        if (anchorPosition != Style.TextAnchor.Start) {
            float calculateTextWidth = this.calculateTextWidth((SVGBase$TextContainer)svgBase$Text);
            if (anchorPosition == Style.TextAnchor.Middle) {
                calculateTextWidth /= 2.0f;
            }
            n2 = floatValueX - calculateTextWidth;
        }
        if (svgBase$Text.boundingBox == null) {
            final SVGAndroidRenderer.SVGAndroidRenderer$TextBoundsCalculator svgAndroidRenderer$TextBoundsCalculator = new SVGAndroidRenderer.SVGAndroidRenderer$TextBoundsCalculator(this, n2, floatValueY);
            this.enumerateTextSpans((SVGBase$TextContainer)svgBase$Text, (TextProcessor)svgAndroidRenderer$TextBoundsCalculator);
            svgBase$Text.boundingBox = new SVGBase.Box(svgAndroidRenderer$TextBoundsCalculator.bbox.left, svgAndroidRenderer$TextBoundsCalculator.bbox.top, svgAndroidRenderer$TextBoundsCalculator.bbox.width(), svgAndroidRenderer$TextBoundsCalculator.bbox.height());
        }
        this.updateParentBoundingBox((SVGBase$SvgElement)svgBase$Text);
        this.checkForGradientsAndPatterns((SVGBase$SvgElement)svgBase$Text);
        this.checkForClipPath((SVGBase$SvgElement)svgBase$Text);
        final boolean pushLayer = this.pushLayer();
        this.enumerateTextSpans((SVGBase$TextContainer)svgBase$Text, (TextProcessor)new SVGAndroidRenderer.SVGAndroidRenderer$PlainTextDrawer(this, n2 + floatValueX2, floatValueY + floatValueY2));
        if (pushLayer) {
            this.popLayer((SVGBase$SvgElement)svgBase$Text);
        }
    }
    
    private void render(final SVGBase$Use svgBase$Use) {
        debug("Use render", new Object[0]);
        if ((svgBase$Use.width != null && svgBase$Use.width.isZero()) || (svgBase$Use.height != null && svgBase$Use.height.isZero())) {
            return;
        }
        this.updateStyleForElement(this.state, (SVGBase$SvgElementBase)svgBase$Use);
        if (!this.display()) {
            return;
        }
        final SVGBase.SvgObject resolveIRI = svgBase$Use.document.resolveIRI(svgBase$Use.href);
        if (resolveIRI == null) {
            error("Use reference '%s' not found", svgBase$Use.href);
            return;
        }
        if (svgBase$Use.transform != null) {
            this.canvas.concat(svgBase$Use.transform);
        }
        final SVGBase.Length x = svgBase$Use.x;
        float floatValueY = 0.0f;
        float floatValueX;
        if (x != null) {
            floatValueX = svgBase$Use.x.floatValueX(this);
        }
        else {
            floatValueX = 0.0f;
        }
        if (svgBase$Use.y != null) {
            floatValueY = svgBase$Use.y.floatValueY(this);
        }
        this.canvas.translate(floatValueX, floatValueY);
        this.checkForClipPath((SVGBase$SvgElement)svgBase$Use);
        final boolean pushLayer = this.pushLayer();
        this.parentPush((SVGBase.SvgContainer)svgBase$Use);
        if (resolveIRI instanceof SVGBase$Svg) {
            final SVGBase$Svg svgBase$Svg = (SVGBase$Svg)resolveIRI;
            final SVGBase.Box viewPort = this.makeViewPort(null, null, svgBase$Use.width, svgBase$Use.height);
            this.statePush();
            this.render(svgBase$Svg, viewPort);
            this.statePop();
        }
        else if (resolveIRI instanceof SVGBase$Symbol) {
            SVGBase.Length width;
            if (svgBase$Use.width != null) {
                width = svgBase$Use.width;
            }
            else {
                width = new SVGBase.Length(100.0f, SVGBase.Unit.percent);
            }
            SVGBase.Length height;
            if (svgBase$Use.height != null) {
                height = svgBase$Use.height;
            }
            else {
                height = new SVGBase.Length(100.0f, SVGBase.Unit.percent);
            }
            final SVGBase.Box viewPort2 = this.makeViewPort(null, null, width, height);
            this.statePush();
            this.render((SVGBase$Symbol)resolveIRI, viewPort2);
            this.statePop();
        }
        else {
            this.render(resolveIRI);
        }
        this.parentPop();
        if (pushLayer) {
            this.popLayer((SVGBase$SvgElement)svgBase$Use);
        }
        this.updateParentBoundingBox((SVGBase$SvgElement)svgBase$Use);
    }
    
    private void renderChildren(final SVGBase.SvgContainer svgContainer, final boolean b) {
        if (b) {
            this.parentPush(svgContainer);
        }
        final Iterator iterator = svgContainer.getChildren().iterator();
        while (iterator.hasNext()) {
            this.render((SVGBase.SvgObject)iterator.next());
        }
        if (b) {
            this.parentPop();
        }
    }
    
    private void renderMarker(final SVGBase$Marker svgBase$Marker, final MarkerVector markerVector) {
        this.statePush();
        final Float orient = svgBase$Marker.orient;
        final float n = 0.0f;
        float floatValue = 0.0f;
        Label_0083: {
            if (orient != null) {
                if (!Float.isNaN((float)svgBase$Marker.orient)) {
                    floatValue = svgBase$Marker.orient;
                    break Label_0083;
                }
                if (markerVector.dx != 0.0f || markerVector.dy != 0.0f) {
                    floatValue = (float)Math.toDegrees(Math.atan2((double)markerVector.dy, (double)markerVector.dx));
                    break Label_0083;
                }
            }
            floatValue = 0.0f;
        }
        float floatValue2;
        if (svgBase$Marker.markerUnitsAreUser) {
            floatValue2 = 1.0f;
        }
        else {
            floatValue2 = this.state.style.strokeWidth.floatValue(this.dpi);
        }
        this.state = this.findInheritFromAncestorState((SVGBase.SvgObject)svgBase$Marker);
        final Matrix matrix = new Matrix();
        matrix.preTranslate(markerVector.x, markerVector.y);
        matrix.preRotate(floatValue);
        matrix.preScale(floatValue2, floatValue2);
        float floatValueX;
        if (svgBase$Marker.refX != null) {
            floatValueX = svgBase$Marker.refX.floatValueX(this);
        }
        else {
            floatValueX = 0.0f;
        }
        float floatValueY;
        if (svgBase$Marker.refY != null) {
            floatValueY = svgBase$Marker.refY.floatValueY(this);
        }
        else {
            floatValueY = 0.0f;
        }
        final SVGBase.Length markerWidth = svgBase$Marker.markerWidth;
        float floatValueY2 = 3.0f;
        float floatValueX2;
        if (markerWidth != null) {
            floatValueX2 = svgBase$Marker.markerWidth.floatValueX(this);
        }
        else {
            floatValueX2 = 3.0f;
        }
        if (svgBase$Marker.markerHeight != null) {
            floatValueY2 = svgBase$Marker.markerHeight.floatValueY(this);
        }
        if (svgBase$Marker.viewBox != null) {
            final float n2 = floatValueX2 / svgBase$Marker.viewBox.width;
            final float n3 = floatValueY2 / svgBase$Marker.viewBox.height;
            PreserveAspectRatio preserveAspectRatio;
            if (svgBase$Marker.preserveAspectRatio != null) {
                preserveAspectRatio = svgBase$Marker.preserveAspectRatio;
            }
            else {
                preserveAspectRatio = PreserveAspectRatio.LETTERBOX;
            }
            float n4 = n2;
            float n5 = n3;
            if (!preserveAspectRatio.equals(PreserveAspectRatio.STRETCH)) {
                if (preserveAspectRatio.getScale() == PreserveAspectRatio.Scale.slice) {
                    n4 = Math.max(n2, n3);
                }
                else {
                    n4 = Math.min(n2, n3);
                }
                n5 = n4;
            }
            matrix.preTranslate(-floatValueX * n4, -floatValueY * n5);
            this.canvas.concat(matrix);
            final float n6 = svgBase$Marker.viewBox.width * n4;
            final float n7 = svgBase$Marker.viewBox.height * n5;
            float n8 = 0.0f;
            Label_0487: {
                float n9 = 0.0f;
                switch (SVGAndroidRenderer$1.$SwitchMap$com$kingagroot$kingdraw$core$graphics$svg$PreserveAspectRatio$Alignment[preserveAspectRatio.getAlignment().ordinal()]) {
                    default: {
                        n8 = 0.0f;
                        break Label_0487;
                    }
                    case 4:
                    case 5:
                    case 6: {
                        n9 = floatValueX2 - n6;
                        break;
                    }
                    case 1:
                    case 2:
                    case 3: {
                        n9 = (floatValueX2 - n6) / 2.0f;
                        break;
                    }
                }
                n8 = 0.0f - n9;
            }
            final int n10 = SVGAndroidRenderer$1.$SwitchMap$com$kingagroot$kingdraw$core$graphics$svg$PreserveAspectRatio$Alignment[preserveAspectRatio.getAlignment().ordinal()];
            float n11 = 0.0f;
            Label_0571: {
                float n12 = 0.0f;
                Label_0565: {
                    Label_0556: {
                        if (n10 != 2) {
                            if (n10 != 3) {
                                if (n10 == 5) {
                                    break Label_0556;
                                }
                                if (n10 != 6) {
                                    if (n10 == 7) {
                                        break Label_0556;
                                    }
                                    if (n10 != 8) {
                                        n11 = n;
                                        break Label_0571;
                                    }
                                }
                            }
                            n12 = floatValueY2 - n7;
                            break Label_0565;
                        }
                    }
                    n12 = (floatValueY2 - n7) / 2.0f;
                }
                n11 = 0.0f - n12;
            }
            if (!this.state.style.overflow) {
                this.setClipRect(n8, n11, floatValueX2, floatValueY2);
            }
            matrix.reset();
            matrix.preScale(n4, n5);
            this.canvas.concat(matrix);
        }
        else {
            matrix.preTranslate(-floatValueX, -floatValueY);
            this.canvas.concat(matrix);
            if (!this.state.style.overflow) {
                this.setClipRect(0.0f, 0.0f, floatValueX2, floatValueY2);
            }
        }
        final boolean pushLayer = this.pushLayer();
        this.renderChildren((SVGBase.SvgContainer)svgBase$Marker, false);
        if (pushLayer) {
            this.popLayer((SVGBase$SvgElement)svgBase$Marker);
        }
        this.statePop();
    }
    
    private void renderMarkers(final SVGBase$GraphicsElement svgBase$GraphicsElement) {
        if (this.state.style.markerStart == null && this.state.style.markerMid == null && this.state.style.markerEnd == null) {
            return;
        }
        SVGBase$Marker svgBase$Marker = null;
        Label_0113: {
            if (this.state.style.markerStart != null) {
                final SVGBase.SvgObject resolveIRI = svgBase$GraphicsElement.document.resolveIRI(this.state.style.markerStart);
                if (resolveIRI != null) {
                    svgBase$Marker = (SVGBase$Marker)resolveIRI;
                    break Label_0113;
                }
                error("Marker reference '%s' not found", this.state.style.markerStart);
            }
            svgBase$Marker = null;
        }
        SVGBase$Marker svgBase$Marker2 = null;
        Label_0186: {
            if (this.state.style.markerMid != null) {
                final SVGBase.SvgObject resolveIRI2 = svgBase$GraphicsElement.document.resolveIRI(this.state.style.markerMid);
                if (resolveIRI2 != null) {
                    svgBase$Marker2 = (SVGBase$Marker)resolveIRI2;
                    break Label_0186;
                }
                error("Marker reference '%s' not found", this.state.style.markerMid);
            }
            svgBase$Marker2 = null;
        }
        SVGBase$Marker svgBase$Marker3 = null;
        Label_0259: {
            if (this.state.style.markerEnd != null) {
                final SVGBase.SvgObject resolveIRI3 = svgBase$GraphicsElement.document.resolveIRI(this.state.style.markerEnd);
                if (resolveIRI3 != null) {
                    svgBase$Marker3 = (SVGBase$Marker)resolveIRI3;
                    break Label_0259;
                }
                error("Marker reference '%s' not found", this.state.style.markerEnd);
            }
            svgBase$Marker3 = null;
        }
        List list;
        if (svgBase$GraphicsElement instanceof SVGBase$Path) {
            list = new SVGAndroidRenderer.SVGAndroidRenderer$MarkerPositionCalculator(this, ((SVGBase$Path)svgBase$GraphicsElement).d).getMarkers();
        }
        else if (svgBase$GraphicsElement instanceof SVGBase$Line) {
            list = this.calculateMarkerPositions((SVGBase$Line)svgBase$GraphicsElement);
        }
        else {
            list = this.calculateMarkerPositions((SVGBase$PolyLine)svgBase$GraphicsElement);
        }
        if (list == null) {
            return;
        }
        final int size = list.size();
        if (size == 0) {
            return;
        }
        final Style style = this.state.style;
        final Style style2 = this.state.style;
        this.state.style.markerEnd = null;
        style2.markerMid = null;
        style.markerStart = null;
        if (svgBase$Marker != null) {
            this.renderMarker(svgBase$Marker, (MarkerVector)list.get(0));
        }
        if (svgBase$Marker2 != null && list.size() > 2) {
            MarkerVector markerVector = (MarkerVector)list.get(0);
            MarkerVector realignMarkerMid = (MarkerVector)list.get(1);
            int i = 1;
            while (i < size - 1) {
                ++i;
                final MarkerVector markerVector2 = (MarkerVector)list.get(i);
                if (realignMarkerMid.isAmbiguous) {
                    realignMarkerMid = this.realignMarkerMid(markerVector, realignMarkerMid, markerVector2);
                }
                this.renderMarker(svgBase$Marker2, realignMarkerMid);
                markerVector = realignMarkerMid;
                realignMarkerMid = markerVector2;
            }
        }
        if (svgBase$Marker3 != null) {
            this.renderMarker(svgBase$Marker3, (MarkerVector)list.get(size - 1));
        }
    }
    
    private void renderMask(final SVGBase$Mask svgBase$Mask, final SVGBase$SvgElement svgBase$SvgElement, final SVGBase.Box box) {
        debug("Mask render", new Object[0]);
        final Boolean maskUnitsAreUser = svgBase$Mask.maskUnitsAreUser;
        final boolean b = true;
        float n2;
        float n3;
        if (maskUnitsAreUser != null && svgBase$Mask.maskUnitsAreUser) {
            float n;
            if (svgBase$Mask.width != null) {
                n = svgBase$Mask.width.floatValueX(this);
            }
            else {
                n = box.width;
            }
            if (svgBase$Mask.height != null) {
                final float floatValueY = svgBase$Mask.height.floatValueY(this);
                n2 = n;
                n3 = floatValueY;
            }
            else {
                final float height = box.height;
                n2 = n;
                n3 = height;
            }
        }
        else {
            final SVGBase.Length width = svgBase$Mask.width;
            float floatValue = 1.2f;
            float floatValue2;
            if (width != null) {
                floatValue2 = svgBase$Mask.width.floatValue(this, 1.0f);
            }
            else {
                floatValue2 = 1.2f;
            }
            if (svgBase$Mask.height != null) {
                floatValue = svgBase$Mask.height.floatValue(this, 1.0f);
            }
            final float n4 = floatValue2 * box.width;
            n3 = floatValue * box.height;
            n2 = n4;
        }
        if (n2 != 0.0f) {
            if (n3 != 0.0f) {
                this.statePush();
                final RendererState inheritFromAncestorState = this.findInheritFromAncestorState((SVGBase.SvgObject)svgBase$Mask);
                this.state = inheritFromAncestorState;
                inheritFromAncestorState.style.opacity = 1.0f;
                final boolean pushLayer = this.pushLayer();
                this.canvas.save();
                int n5 = b ? 1 : 0;
                if (svgBase$Mask.maskContentUnitsAreUser != null) {
                    if (svgBase$Mask.maskContentUnitsAreUser) {
                        n5 = (b ? 1 : 0);
                    }
                    else {
                        n5 = 0;
                    }
                }
                if (n5 == 0) {
                    this.canvas.translate(box.minX, box.minY);
                    this.canvas.scale(box.width, box.height);
                }
                this.renderChildren((SVGBase.SvgContainer)svgBase$Mask, false);
                this.canvas.restore();
                if (pushLayer) {
                    this.popLayer(svgBase$SvgElement, box);
                }
                this.statePop();
            }
        }
    }
    
    private void renderSwitchChild(final SVGBase$Switch svgBase$Switch) {
        final String language = Locale.getDefault().getLanguage();
    Label_0017:
        for (final SVGBase.SvgObject svgObject : svgBase$Switch.getChildren()) {
            if (!(svgObject instanceof SVGBase.SvgConditional)) {
                continue;
            }
            final SVGBase.SvgConditional svgConditional = (SVGBase.SvgConditional)svgObject;
            if (svgConditional.getRequiredExtensions() != null) {
                continue;
            }
            final Set<String> systemLanguage = svgConditional.getSystemLanguage();
            if (systemLanguage != null) {
                if (systemLanguage.isEmpty()) {
                    continue;
                }
                if (!systemLanguage.contains((Object)language)) {
                    continue;
                }
            }
            final Set<String> requiredFeatures = svgConditional.getRequiredFeatures();
            if (requiredFeatures != null) {
                if (SVGAndroidRenderer.supportedFeatures == null) {
                    initialiseSupportedFeaturesMap();
                }
                if (requiredFeatures.isEmpty()) {
                    continue;
                }
                if (!SVGAndroidRenderer.supportedFeatures.containsAll((Collection)requiredFeatures)) {
                    continue;
                }
            }
            final Set<String> requiredFormats = svgConditional.getRequiredFormats();
            if (requiredFormats != null) {
                if (requiredFormats.isEmpty()) {
                    continue;
                }
                if (this.externalFileResolver == null) {
                    continue;
                }
                final Iterator iterator2 = requiredFormats.iterator();
                while (iterator2.hasNext()) {
                    if (!this.externalFileResolver.isFormatSupported((String)iterator2.next())) {
                        continue Label_0017;
                    }
                }
            }
            final Set<String> requiredFonts = svgConditional.getRequiredFonts();
            if (requiredFonts != null) {
                if (requiredFonts.isEmpty()) {
                    continue;
                }
                if (this.externalFileResolver == null) {
                    continue;
                }
                final Iterator iterator3 = requiredFonts.iterator();
                while (iterator3.hasNext()) {
                    if (this.externalFileResolver.resolveFont((String)iterator3.next(), this.state.style.fontWeight, String.valueOf((Object)this.state.style.fontStyle), this.state.style.fontStretch) == null) {
                        continue Label_0017;
                    }
                }
            }
            this.render(svgObject);
            break;
        }
    }
    
    private void renderTextPath(final SVGBase$TextPath svgBase$TextPath) {
        debug("TextPath render", new Object[0]);
        this.updateStyleForElement(this.state, (SVGBase$SvgElementBase)svgBase$TextPath);
        if (!this.display()) {
            return;
        }
        if (!this.visible()) {
            return;
        }
        this.selectTypefaceAndFontStyling();
        final SVGBase.SvgObject resolveIRI = svgBase$TextPath.document.resolveIRI(svgBase$TextPath.href);
        if (resolveIRI == null) {
            error("TextPath reference '%s' not found", svgBase$TextPath.href);
            return;
        }
        final SVGBase$Path svgBase$Path = (SVGBase$Path)resolveIRI;
        final Path path = new SVGAndroidRenderer.SVGAndroidRenderer$PathConverter(svgBase$Path.d).getPath();
        if (svgBase$Path.transform != null) {
            path.transform(svgBase$Path.transform);
        }
        final PathMeasure pathMeasure = new PathMeasure(path, false);
        float floatValue;
        if (svgBase$TextPath.startOffset != null) {
            floatValue = svgBase$TextPath.startOffset.floatValue(this, pathMeasure.getLength());
        }
        else {
            floatValue = 0.0f;
        }
        final Style.TextAnchor anchorPosition = this.getAnchorPosition();
        float n = floatValue;
        if (anchorPosition != Style.TextAnchor.Start) {
            float calculateTextWidth = this.calculateTextWidth((SVGBase$TextContainer)svgBase$TextPath);
            if (anchorPosition == Style.TextAnchor.Middle) {
                calculateTextWidth /= 2.0f;
            }
            n = floatValue - calculateTextWidth;
        }
        this.checkForGradientsAndPatterns((SVGBase$SvgElement)svgBase$TextPath.getTextRoot());
        final boolean pushLayer = this.pushLayer();
        this.enumerateTextSpans((SVGBase$TextContainer)svgBase$TextPath, (TextProcessor)new SVGAndroidRenderer.SVGAndroidRenderer$PathTextDrawer(this, path, n, 0.0f));
        if (pushLayer) {
            this.popLayer((SVGBase$SvgElement)svgBase$TextPath);
        }
    }
    
    private boolean requiresCompositing() {
        return this.state.style.opacity < 1.0f || this.state.style.mask != null || this.state.style.isolation == Style.Isolation.isolate || (SVGAndroidRenderer.SUPPORTS_BLEND_MODE && this.state.style.mixBlendMode != Style.CSSBlendMode.normal);
    }
    
    private void resetState() {
        this.state = new RendererState();
        this.stateStack = (Stack<RendererState>)new Stack();
        this.updateStyle(this.state, Style.getDefaultStyle());
        this.state.viewPort = null;
        this.state.spacePreserve = false;
        this.stateStack.push((Object)new RendererState(this.state));
        this.matrixStack = (Stack<Matrix>)new Stack();
        this.parentStack = (Stack<SVGBase.SvgContainer>)new Stack();
    }
    
    private void selectTypefaceAndFontStyling() {
        final List<String> fontFamily = this.state.style.fontFamily;
        final Typeface typeface = null;
        Typeface typeface2 = null;
        Typeface typeface3 = typeface;
        if (fontFamily != null) {
            typeface3 = typeface;
            if (this.document != null) {
                final Iterator iterator = this.state.style.fontFamily.iterator();
                do {
                    typeface3 = typeface2;
                    if (!iterator.hasNext()) {
                        break;
                    }
                    final String s = (String)iterator.next();
                    typeface3 = this.checkGenericFont(s, this.state.style.fontWeight, this.state.style.fontStyle);
                    if (typeface3 != null) {
                        continue;
                    }
                    final SVGExternalFileResolver externalFileResolver = this.externalFileResolver;
                    if (externalFileResolver == null) {
                        continue;
                    }
                    typeface3 = externalFileResolver.resolveFont(s, this.state.style.fontWeight, String.valueOf((Object)this.state.style.fontStyle), this.state.style.fontStretch);
                } while ((typeface2 = typeface3) == null);
            }
        }
        Typeface checkGenericFont;
        if ((checkGenericFont = typeface3) == null) {
            checkGenericFont = this.checkGenericFont("serif", this.state.style.fontWeight, this.state.style.fontStyle);
        }
        this.state.fillPaint.setTypeface(checkGenericFont);
        this.state.strokePaint.setTypeface(checkGenericFont);
        if (SVGAndroidRenderer.SUPPORTS_PAINT_FONT_VARIATION_SETTINGS) {
            this.state.fontVariationSet.addSetting("wght", this.state.style.fontWeight);
            if (this.state.style.fontStyle == Style.FontStyle.italic) {
                this.state.fontVariationSet.addSetting("ital", CSSFontVariationSettings.VARIATION_ITALIC_VALUE_ON);
                this.state.fontVariationSet.addSetting("slnt", CSSFontVariationSettings.VARIATION_OBLIQUE_VALUE_ON);
            }
            else if (this.state.style.fontStyle == Style.FontStyle.oblique) {
                this.state.fontVariationSet.addSetting("slnt", CSSFontVariationSettings.VARIATION_OBLIQUE_VALUE_ON);
            }
            this.state.fontVariationSet.addSetting("wdth", this.state.style.fontStretch);
            final String string = this.state.fontVariationSet.toString();
            final StringBuilder sb = new StringBuilder();
            sb.append("fontVariationSettings = ");
            sb.append(string);
            debug(sb.toString(), new Object[0]);
            this.state.fillPaint.setFontVariationSettings(string);
            this.state.strokePaint.setFontVariationSettings(string);
        }
        if (SVGAndroidRenderer.SUPPORTS_PAINT_FONT_FEATURE_SETTINGS) {
            final String string2 = this.state.fontFeatureSet.toString();
            final StringBuilder sb2 = new StringBuilder();
            sb2.append("fontFeatureSettings = ");
            sb2.append(string2);
            debug(sb2.toString(), new Object[0]);
            this.state.fillPaint.setFontFeatureSettings(string2);
            this.state.strokePaint.setFontFeatureSettings(string2);
        }
    }
    
    private void setBlendMode(final Paint paint) {
        final StringBuilder sb = new StringBuilder();
        sb.append("Setting blend mode to ");
        sb.append((Object)this.state.style.mixBlendMode);
        debug(sb.toString(), new Object[0]);
        switch (SVGAndroidRenderer$1.$SwitchMap$com$kingagroot$kingdraw$core$graphics$svg$utils$Style$CSSBlendMode[this.state.style.mixBlendMode.ordinal()]) {
            default: {
                paint.setBlendMode((BlendMode)null);
                break;
            }
            case 15: {
                paint.setBlendMode(BlendMode.LUMINOSITY);
                break;
            }
            case 14: {
                paint.setBlendMode(BlendMode.COLOR);
                break;
            }
            case 13: {
                paint.setBlendMode(BlendMode.SATURATION);
                break;
            }
            case 12: {
                paint.setBlendMode(BlendMode.HUE);
                break;
            }
            case 11: {
                paint.setBlendMode(BlendMode.EXCLUSION);
                break;
            }
            case 10: {
                paint.setBlendMode(BlendMode.DIFFERENCE);
                break;
            }
            case 9: {
                paint.setBlendMode(BlendMode.SOFT_LIGHT);
                break;
            }
            case 8: {
                paint.setBlendMode(BlendMode.HARD_LIGHT);
                break;
            }
            case 7: {
                paint.setBlendMode(BlendMode.COLOR_BURN);
                break;
            }
            case 6: {
                paint.setBlendMode(BlendMode.COLOR_DODGE);
                break;
            }
            case 5: {
                paint.setBlendMode(BlendMode.LIGHTEN);
                break;
            }
            case 4: {
                paint.setBlendMode(BlendMode.DARKEN);
                break;
            }
            case 3: {
                paint.setBlendMode(BlendMode.OVERLAY);
                break;
            }
            case 2: {
                paint.setBlendMode(BlendMode.SCREEN);
                break;
            }
            case 1: {
                paint.setBlendMode(BlendMode.MULTIPLY);
                break;
            }
        }
    }
    
    private void setClipRect(final float n, final float n2, float n3, float n4) {
        final float n5 = n3 + n;
        final float n6 = n4 + n2;
        float n7 = n;
        float n8 = n2;
        n4 = n5;
        n3 = n6;
        if (this.state.style.clip != null) {
            n7 = n + this.state.style.clip.left.floatValueX(this);
            n8 = n2 + this.state.style.clip.top.floatValueY(this);
            n4 = n5 - this.state.style.clip.right.floatValueX(this);
            n3 = n6 - this.state.style.clip.bottom.floatValueY(this);
        }
        this.canvas.clipRect(n7, n8, n4, n3);
    }
    
    private void setPaintColour(final RendererState rendererState, final boolean b, final SVGBase.SvgPaint svgPaint) {
        final Style style = rendererState.style;
        Float n;
        if (b) {
            n = style.fillOpacity;
        }
        else {
            n = style.strokeOpacity;
        }
        final float floatValue = n;
        int n2;
        if (svgPaint instanceof SVGBase$Colour) {
            n2 = ((SVGBase$Colour)svgPaint).colour;
        }
        else {
            if (!(svgPaint instanceof SVGBase$CurrentColor)) {
                return;
            }
            n2 = rendererState.style.color.colour;
        }
        final int colourWithOpacity = colourWithOpacity(n2, floatValue);
        if (b) {
            rendererState.fillPaint.setColor(colourWithOpacity);
        }
        else {
            rendererState.strokePaint.setColor(colourWithOpacity);
        }
    }
    
    private void setSolidColor(final boolean b, final SVGBase$SolidColor svgBase$SolidColor) {
        final boolean b2 = true;
        boolean hasFill = true;
        if (b) {
            if (this.isSpecified(svgBase$SolidColor.baseStyle, 2147483648L)) {
                this.state.style.fill = svgBase$SolidColor.baseStyle.solidColor;
                final RendererState state = this.state;
                if (svgBase$SolidColor.baseStyle.solidColor == null) {
                    hasFill = false;
                }
                state.hasFill = hasFill;
            }
            if (this.isSpecified(svgBase$SolidColor.baseStyle, 4294967296L)) {
                this.state.style.fillOpacity = svgBase$SolidColor.baseStyle.solidOpacity;
            }
            if (this.isSpecified(svgBase$SolidColor.baseStyle, 6442450944L)) {
                final RendererState state2 = this.state;
                this.setPaintColour(state2, b, state2.style.fill);
            }
        }
        else {
            if (this.isSpecified(svgBase$SolidColor.baseStyle, 2147483648L)) {
                this.state.style.stroke = svgBase$SolidColor.baseStyle.solidColor;
                this.state.hasStroke = (svgBase$SolidColor.baseStyle.solidColor != null && b2);
            }
            if (this.isSpecified(svgBase$SolidColor.baseStyle, 4294967296L)) {
                this.state.style.strokeOpacity = svgBase$SolidColor.baseStyle.solidOpacity;
            }
            if (this.isSpecified(svgBase$SolidColor.baseStyle, 6442450944L)) {
                final RendererState state3 = this.state;
                this.setPaintColour(state3, b, state3.style.stroke);
            }
        }
    }
    
    private void statePop() {
        this.canvas.restore();
        this.state = (RendererState)this.stateStack.pop();
    }
    
    private void statePush() {
        this.statePush(false);
    }
    
    private void statePush(final boolean b) {
        if (b) {
            this.canvas.saveLayer((RectF)null, (Paint)null, 31);
        }
        else {
            this.canvas.save();
        }
        this.stateStack.push((Object)this.state);
        this.state = new RendererState(this.state);
    }
    
    private String textXMLSpaceTransform(String s, final boolean b, final boolean b2) {
        if (this.state.spacePreserve) {
            return SVGAndroidRenderer.PATTERN_TABS_OR_LINE_BREAKS.matcher((CharSequence)s).replaceAll(" ");
        }
        s = SVGAndroidRenderer.PATTERN_TABS.matcher((CharSequence)s).replaceAll("");
        final String s2 = s = SVGAndroidRenderer.PATTERN_LINE_BREAKS.matcher((CharSequence)s).replaceAll(" ");
        if (b) {
            s = SVGAndroidRenderer.PATTERN_START_SPACES.matcher((CharSequence)s2).replaceAll("");
        }
        String replaceAll = s;
        if (b2) {
            replaceAll = SVGAndroidRenderer.PATTERN_END_SPACES.matcher((CharSequence)s).replaceAll("");
        }
        return SVGAndroidRenderer.PATTERN_DOUBLE_SPACES.matcher((CharSequence)replaceAll).replaceAll(" ");
    }
    
    private void updateParentBoundingBox(final SVGBase$SvgElement svgBase$SvgElement) {
        if (svgBase$SvgElement.parent == null) {
            return;
        }
        if (svgBase$SvgElement.boundingBox == null) {
            return;
        }
        final Matrix matrix = new Matrix();
        if (((Matrix)this.matrixStack.peek()).invert(matrix)) {
            final float[] array = new float[8];
            array[0] = svgBase$SvgElement.boundingBox.minX;
            array[1] = svgBase$SvgElement.boundingBox.minY;
            final float maxX = svgBase$SvgElement.boundingBox.maxX();
            int i = 2;
            array[2] = maxX;
            array[3] = svgBase$SvgElement.boundingBox.minY;
            array[4] = svgBase$SvgElement.boundingBox.maxX();
            array[5] = svgBase$SvgElement.boundingBox.maxY();
            array[6] = svgBase$SvgElement.boundingBox.minX;
            array[7] = svgBase$SvgElement.boundingBox.maxY();
            matrix.preConcat(this.canvas.getMatrix());
            matrix.mapPoints(array);
            final RectF rectF = new RectF(array[0], array[1], array[0], array[1]);
            while (i <= 6) {
                if (array[i] < rectF.left) {
                    rectF.left = array[i];
                }
                if (array[i] > rectF.right) {
                    rectF.right = array[i];
                }
                final int n = i + 1;
                if (array[n] < rectF.top) {
                    rectF.top = array[n];
                }
                if (array[n] > rectF.bottom) {
                    rectF.bottom = array[n];
                }
                i += 2;
            }
            final SVGBase$SvgElement svgBase$SvgElement2 = (SVGBase$SvgElement)this.parentStack.peek();
            if (svgBase$SvgElement2.boundingBox == null) {
                svgBase$SvgElement2.boundingBox = SVGBase.Box.fromLimits(rectF.left, rectF.top, rectF.right, rectF.bottom);
            }
            else {
                svgBase$SvgElement2.boundingBox.union(SVGBase.Box.fromLimits(rectF.left, rectF.top, rectF.right, rectF.bottom));
            }
        }
    }
    
    private void updateStyle(final RendererState rendererState, final Style style) {
        if (this.isSpecified(style, 4096L)) {
            rendererState.style.color = style.color;
        }
        if (this.isSpecified(style, 2048L)) {
            rendererState.style.opacity = style.opacity;
        }
        final boolean specified = this.isSpecified(style, 1L);
        final boolean b = false;
        if (specified) {
            rendererState.style.fill = style.fill;
            rendererState.hasFill = (style.fill != null && style.fill != SVGBase$Colour.TRANSPARENT);
        }
        if (this.isSpecified(style, 4L)) {
            rendererState.style.fillOpacity = style.fillOpacity;
        }
        if (this.isSpecified(style, 6149L)) {
            this.setPaintColour(rendererState, true, rendererState.style.fill);
        }
        if (this.isSpecified(style, 2L)) {
            rendererState.style.fillRule = style.fillRule;
        }
        if (this.isSpecified(style, 8L)) {
            rendererState.style.stroke = style.stroke;
            rendererState.hasStroke = (style.stroke != null && style.stroke != SVGBase$Colour.TRANSPARENT);
        }
        if (this.isSpecified(style, 16L)) {
            rendererState.style.strokeOpacity = style.strokeOpacity;
        }
        if (this.isSpecified(style, 6168L)) {
            this.setPaintColour(rendererState, false, rendererState.style.stroke);
        }
        if (this.isSpecified(style, 34359738368L)) {
            rendererState.style.vectorEffect = style.vectorEffect;
        }
        if (this.isSpecified(style, 32L)) {
            rendererState.style.strokeWidth = style.strokeWidth;
            rendererState.strokePaint.setStrokeWidth(rendererState.style.strokeWidth.floatValue(this));
        }
        if (this.isSpecified(style, 64L)) {
            rendererState.style.strokeLineCap = style.strokeLineCap;
            final int n = SVGAndroidRenderer$1.$SwitchMap$com$kingagroot$kingdraw$core$graphics$svg$utils$Style$LineCap[style.strokeLineCap.ordinal()];
            if (n != 1) {
                if (n != 2) {
                    if (n == 3) {
                        rendererState.strokePaint.setStrokeCap(Paint$Cap.SQUARE);
                    }
                }
                else {
                    rendererState.strokePaint.setStrokeCap(Paint$Cap.ROUND);
                }
            }
            else {
                rendererState.strokePaint.setStrokeCap(Paint$Cap.BUTT);
            }
        }
        if (this.isSpecified(style, 128L)) {
            rendererState.style.strokeLineJoin = style.strokeLineJoin;
            final int n2 = SVGAndroidRenderer$1.$SwitchMap$com$kingagroot$kingdraw$core$graphics$svg$utils$Style$LineJoin[style.strokeLineJoin.ordinal()];
            if (n2 != 1) {
                if (n2 != 2) {
                    if (n2 == 3) {
                        rendererState.strokePaint.setStrokeJoin(Paint$Join.BEVEL);
                    }
                }
                else {
                    rendererState.strokePaint.setStrokeJoin(Paint$Join.ROUND);
                }
            }
            else {
                rendererState.strokePaint.setStrokeJoin(Paint$Join.MITER);
            }
        }
        if (this.isSpecified(style, 256L)) {
            rendererState.style.strokeMiterLimit = style.strokeMiterLimit;
            rendererState.strokePaint.setStrokeMiter((float)style.strokeMiterLimit);
        }
        if (this.isSpecified(style, 512L)) {
            rendererState.style.strokeDashArray = style.strokeDashArray;
        }
        if (this.isSpecified(style, 1024L)) {
            rendererState.style.strokeDashOffset = style.strokeDashOffset;
        }
        if (this.isSpecified(style, 1536L)) {
            if (rendererState.style.strokeDashArray == null) {
                rendererState.strokePaint.setPathEffect((PathEffect)null);
            }
            else {
                final int length = rendererState.style.strokeDashArray.length;
                int n3;
                if (length % 2 == 0) {
                    n3 = length;
                }
                else {
                    n3 = length * 2;
                }
                final float[] array = new float[n3];
                int i = 0;
                float n4 = 0.0f;
                while (i < n3) {
                    array[i] = rendererState.style.strokeDashArray[i % length].floatValue(this);
                    n4 += array[i];
                    ++i;
                }
                if (n4 == 0.0f) {
                    rendererState.strokePaint.setPathEffect((PathEffect)null);
                }
                else {
                    float floatValue;
                    final float n5 = floatValue = rendererState.style.strokeDashOffset.floatValue(this);
                    if (n5 < 0.0f) {
                        floatValue = n5 % n4 + n4;
                    }
                    rendererState.strokePaint.setPathEffect((PathEffect)new DashPathEffect(array, floatValue));
                }
            }
        }
        if (this.isSpecified(style, 16384L)) {
            final float currentFontSize = this.getCurrentFontSize();
            rendererState.style.fontSize = style.fontSize;
            rendererState.fillPaint.setTextSize(style.fontSize.floatValue(this, currentFontSize));
            rendererState.strokePaint.setTextSize(style.fontSize.floatValue(this, currentFontSize));
        }
        if (this.isSpecified(style, 8192L)) {
            rendererState.style.fontFamily = style.fontFamily;
        }
        if (this.isSpecified(style, 32768L)) {
            if (style.fontWeight == Float.MIN_VALUE) {
                final float floatValue2 = rendererState.style.fontWeight;
                if (floatValue2 >= 100.0f && floatValue2 < 550.0f) {
                    rendererState.style.fontWeight = 100.0f;
                }
                else if (floatValue2 >= 550.0f && floatValue2 < 750.0f) {
                    rendererState.style.fontWeight = 400.0f;
                }
                else if (floatValue2 >= 750.0f) {
                    rendererState.style.fontWeight = 700.0f;
                }
            }
            else if (style.fontWeight == Float.MAX_VALUE) {
                final float floatValue3 = rendererState.style.fontWeight;
                if (floatValue3 < 350.0f) {
                    rendererState.style.fontWeight = 400.0f;
                }
                else if (floatValue3 >= 350.0f && floatValue3 < 550.0f) {
                    rendererState.style.fontWeight = 700.0f;
                }
                else if (floatValue3 >= 550.0f && floatValue3 < 900.0f) {
                    rendererState.style.fontWeight = 900.0f;
                }
            }
            else {
                rendererState.style.fontWeight = style.fontWeight;
            }
        }
        if (this.isSpecified(style, 65536L)) {
            rendererState.style.fontStyle = style.fontStyle;
        }
        if (this.isSpecified(style, 2251799813685248L)) {
            rendererState.style.fontStretch = style.fontStretch;
        }
        if (this.isSpecified(style, 131072L)) {
            rendererState.style.textDecoration = style.textDecoration;
            rendererState.fillPaint.setStrikeThruText(style.textDecoration == Style.TextDecoration.LineThrough);
            rendererState.fillPaint.setUnderlineText(style.textDecoration == Style.TextDecoration.Underline);
            if (SVGAndroidRenderer.SUPPORTS_STROKED_UNDERLINES) {
                rendererState.strokePaint.setStrikeThruText(style.textDecoration == Style.TextDecoration.LineThrough);
                final Paint strokePaint = rendererState.strokePaint;
                boolean underlineText = b;
                if (style.textDecoration == Style.TextDecoration.Underline) {
                    underlineText = true;
                }
                strokePaint.setUnderlineText(underlineText);
            }
        }
        if (this.isSpecified(style, 68719476736L)) {
            rendererState.style.direction = style.direction;
        }
        if (this.isSpecified(style, 262144L)) {
            rendererState.style.textAnchor = style.textAnchor;
        }
        if (this.isSpecified(style, 524288L)) {
            rendererState.style.overflow = style.overflow;
        }
        if (this.isSpecified(style, 2097152L)) {
            rendererState.style.markerStart = style.markerStart;
        }
        if (this.isSpecified(style, 4194304L)) {
            rendererState.style.markerMid = style.markerMid;
        }
        if (this.isSpecified(style, 8388608L)) {
            rendererState.style.markerEnd = style.markerEnd;
        }
        if (this.isSpecified(style, 16777216L)) {
            rendererState.style.display = style.display;
        }
        if (this.isSpecified(style, 33554432L)) {
            rendererState.style.visibility = style.visibility;
        }
        if (this.isSpecified(style, 1048576L)) {
            rendererState.style.clip = style.clip;
        }
        if (this.isSpecified(style, 268435456L)) {
            rendererState.style.clipPath = style.clipPath;
        }
        if (this.isSpecified(style, 536870912L)) {
            rendererState.style.clipRule = style.clipRule;
        }
        if (this.isSpecified(style, 1073741824L)) {
            rendererState.style.mask = style.mask;
        }
        if (this.isSpecified(style, 67108864L)) {
            rendererState.style.stopColor = style.stopColor;
        }
        if (this.isSpecified(style, 134217728L)) {
            rendererState.style.stopOpacity = style.stopOpacity;
        }
        if (this.isSpecified(style, 8589934592L)) {
            rendererState.style.viewportFill = style.viewportFill;
        }
        if (this.isSpecified(style, 17179869184L)) {
            rendererState.style.viewportFillOpacity = style.viewportFillOpacity;
        }
        if (this.isSpecified(style, 137438953472L)) {
            rendererState.style.imageRendering = style.imageRendering;
        }
        if (this.isSpecified(style, 274877906944L)) {
            rendererState.style.isolation = style.isolation;
        }
        if (this.isSpecified(style, 549755813888L)) {
            rendererState.style.mixBlendMode = style.mixBlendMode;
        }
        if (this.isSpecified(style, 562949953421312L)) {
            rendererState.style.fontKerning = style.fontKerning;
            rendererState.fontFeatureSet.applyKerning(style.fontKerning);
        }
        if (this.isSpecified(style, 35184372088832L)) {
            rendererState.style.fontFeatureSettings = style.fontFeatureSettings;
            rendererState.fontFeatureSet.applySettings(style.fontFeatureSettings);
        }
        if (this.isSpecified(style, 1099511627776L)) {
            rendererState.style.fontVariantLigatures = style.fontVariantLigatures;
            rendererState.fontFeatureSet.applySettings(style.fontVariantLigatures);
        }
        if (this.isSpecified(style, 2199023255552L)) {
            rendererState.style.fontVariantPosition = style.fontVariantPosition;
            rendererState.fontFeatureSet.applySettings(style.fontVariantPosition);
        }
        if (this.isSpecified(style, 4398046511104L)) {
            rendererState.style.fontVariantCaps = style.fontVariantCaps;
            rendererState.fontFeatureSet.applySettings(style.fontVariantCaps);
        }
        if (this.isSpecified(style, 8796093022208L)) {
            rendererState.style.fontVariantNumeric = style.fontVariantNumeric;
            rendererState.fontFeatureSet.applySettings(style.fontVariantNumeric);
        }
        if (this.isSpecified(style, 17592186044416L)) {
            rendererState.style.fontVariantEastAsian = style.fontVariantEastAsian;
            rendererState.fontFeatureSet.applySettings(style.fontVariantEastAsian);
        }
        if (SVGAndroidRenderer.SUPPORTS_PAINT_FONT_VARIATION_SETTINGS && this.isSpecified(style, 1125899906842624L)) {
            rendererState.style.fontVariationSettings = style.fontVariationSettings;
            rendererState.fontVariationSet.applySettings(style.fontVariationSettings);
        }
        if (this.isSpecified(style, 70368744177664L)) {
            rendererState.style.writingMode = style.writingMode;
        }
        if (this.isSpecified(style, 140737488355328L)) {
            rendererState.style.glyphOrientationVertical = style.glyphOrientationVertical;
        }
        if (this.isSpecified(style, 281474976710656L)) {
            rendererState.style.textOrientation = style.textOrientation;
        }
        if (this.isSpecified(style, 4503599627370496L)) {
            rendererState.style.letterSpacing = style.letterSpacing;
            if (SVGAndroidRenderer.SUPPORTS_PAINT_LETTER_SPACING) {
                rendererState.fillPaint.setLetterSpacing(style.letterSpacing.floatValue(this) / this.getCurrentFontSize());
                rendererState.strokePaint.setLetterSpacing(style.letterSpacing.floatValue(this) / this.getCurrentFontSize());
            }
        }
        if (this.isSpecified(style, 9007199254740992L)) {
            rendererState.style.wordSpacing = style.wordSpacing;
            if (SVGAndroidRenderer.SUPPORTS_PAINT_WORD_SPACING) {
                rendererState.fillPaint.setWordSpacing(style.wordSpacing.floatValue(this));
                rendererState.strokePaint.setWordSpacing(style.wordSpacing.floatValue(this));
            }
        }
    }
    
    private void updateStyleForElement(final RendererState rendererState, final SVGBase$SvgElementBase svgBase$SvgElementBase) {
        rendererState.style.resetNonInheritingProperties(svgBase$SvgElementBase.parent == null);
        if (svgBase$SvgElementBase.baseStyle != null) {
            this.updateStyle(rendererState, svgBase$SvgElementBase.baseStyle);
        }
        if (this.document.hasCSSRules()) {
            for (final CSSParser.Rule rule : this.document.getCSSRules()) {
                if (CSSParser.ruleMatch(this.ruleMatchContext, rule.selector, svgBase$SvgElementBase)) {
                    this.updateStyle(rendererState, rule.style);
                }
            }
        }
        if (svgBase$SvgElementBase.style != null) {
            this.updateStyle(rendererState, svgBase$SvgElementBase.style);
        }
    }
    
    private void viewportFill() {
        int n;
        if (this.state.style.viewportFill instanceof SVGBase$Colour) {
            n = ((SVGBase$Colour)this.state.style.viewportFill).colour;
        }
        else {
            if (!(this.state.style.viewportFill instanceof SVGBase$CurrentColor)) {
                return;
            }
            n = this.state.style.color.colour;
        }
        int colourWithOpacity = n;
        if (this.state.style.viewportFillOpacity != null) {
            colourWithOpacity = colourWithOpacity(n, this.state.style.viewportFillOpacity);
        }
        this.canvas.drawColor(colourWithOpacity);
    }
    
    private boolean visible() {
        return this.state.style.visibility == null || this.state.style.visibility;
    }
    
    private static void warn(final String s, final Object... array) {
        Log.w("SVGAndroidRenderer", String.format(s, array));
    }
    
    float getCurrentFontSize() {
        return this.state.fillPaint.getTextSize();
    }
    
    float getCurrentFontXHeight() {
        return this.state.fillPaint.getTextSize() / 2.0f;
    }
    
    SVGBase.Box getCurrentViewPortInUserUnits() {
        if (this.state.viewBox != null) {
            return this.state.viewBox;
        }
        return this.state.viewPort;
    }
    
    float getDPI() {
        return this.dpi;
    }
    
    void renderDocument(final SVGBase document, final RenderOptionsBase renderOptionsBase) {
        if (renderOptionsBase == null) {
            throw new NullPointerException("renderOptions shouldn't be null");
        }
        this.document = document;
        final SVGBase.SVGBase$Svg rootElement = document.getRootElement();
        if (rootElement == null) {
            warn("Nothing to render. Document is empty.", new Object[0]);
            return;
        }
        SVGBase.Box box;
        PreserveAspectRatio preserveAspectRatio;
        if (renderOptionsBase.hasView()) {
            final SVGBase.SVGBase$SvgElementBase elementById = this.document.getElementById(renderOptionsBase.viewId);
            if (!(elementById instanceof SVGBase$View)) {
                Log.w("SVGAndroidRenderer", String.format("View element with id \"%s\" not found.", new Object[] { renderOptionsBase.viewId }));
                return;
            }
            final SVGBase$View svgBase$View = (SVGBase$View)elementById;
            if (svgBase$View.viewBox == null) {
                Log.w("SVGAndroidRenderer", String.format("View element with id \"%s\" is missing a viewBox attribute.", new Object[] { renderOptionsBase.viewId }));
                return;
            }
            box = svgBase$View.viewBox;
            preserveAspectRatio = svgBase$View.preserveAspectRatio;
        }
        else {
            if (renderOptionsBase.hasViewBox()) {
                box = renderOptionsBase.viewBox;
            }
            else {
                box = rootElement.viewBox;
            }
            if (renderOptionsBase.hasPreserveAspectRatio()) {
                preserveAspectRatio = renderOptionsBase.preserveAspectRatio;
            }
            else {
                preserveAspectRatio = rootElement.preserveAspectRatio;
            }
        }
        if (renderOptionsBase.hasCss()) {
            if (renderOptionsBase.css != null) {
                document.addCSSRules(new CSSParser(CSSParser.Source.RenderOptions, this.externalFileResolver).parse(renderOptionsBase.css));
            }
            else if (renderOptionsBase.cssRuleset != null) {
                document.addCSSRules(renderOptionsBase.cssRuleset);
            }
        }
        if (renderOptionsBase.hasTarget()) {
            final CSSParser.RuleMatchContext ruleMatchContext = new CSSParser.RuleMatchContext();
            this.ruleMatchContext = ruleMatchContext;
            ruleMatchContext.targetElement = document.getElementById(renderOptionsBase.targetId);
        }
        this.resetState();
        this.checkXMLSpaceAttribute((SVGBase.SvgObject)rootElement);
        this.statePush(true);
        final SVGBase.Box box2 = new SVGBase.Box(renderOptionsBase.viewPort);
        if (rootElement.width != null) {
            box2.width = rootElement.width.floatValue(this, box2.width);
        }
        if (rootElement.height != null) {
            box2.height = rootElement.height.floatValue(this, box2.height);
        }
        this.render(rootElement, box2, box, preserveAspectRatio);
        this.statePop();
        if (renderOptionsBase.hasCss()) {
            document.clearRenderCSSRules();
        }
    }
    
    private static class MarkerVector
    {
        float dx;
        float dy;
        boolean isAmbiguous;
        final float x;
        final float y;
        
        MarkerVector(final float x, final float y, final float n, final float n2) {
            this.dx = 0.0f;
            this.dy = 0.0f;
            this.isAmbiguous = false;
            this.x = x;
            this.y = y;
            final double sqrt = Math.sqrt((double)(n * n + n2 * n2));
            if (sqrt != 0.0) {
                this.dx = (float)(n / sqrt);
                this.dy = (float)(n2 / sqrt);
            }
        }
        
        void add(float n, float dy) {
            final float n2 = n - this.x;
            final float n3 = dy - this.y;
            final double sqrt = Math.sqrt((double)(n2 * n2 + n3 * n3));
            dy = n2;
            n = n3;
            if (sqrt != 0.0) {
                dy = (float)(n2 / sqrt);
                n = (float)(n3 / sqrt);
            }
            if (dy == -this.dx && n == -this.dy) {
                this.isAmbiguous = true;
                this.dx = -n;
                this.dy = dy;
            }
            else {
                this.dx += dy;
                this.dy += n;
            }
        }
        
        void add(final MarkerVector markerVector) {
            if (markerVector.dx == -this.dx) {
                final float dy = markerVector.dy;
                if (dy == -this.dy) {
                    this.isAmbiguous = true;
                    this.dx = -dy;
                    this.dy = markerVector.dx;
                    return;
                }
            }
            this.dx += markerVector.dx;
            this.dy += markerVector.dy;
        }
        
        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            sb.append("(");
            sb.append(this.x);
            sb.append(",");
            sb.append(this.y);
            sb.append(" ");
            sb.append(this.dx);
            sb.append(",");
            sb.append(this.dy);
            sb.append(")");
            return sb.toString();
        }
    }
    
    public static class RendererState
    {
        final Paint fillPaint;
        final CSSFontFeatureSettings fontFeatureSet;
        final CSSFontVariationSettings fontVariationSet;
        boolean hasFill;
        boolean hasStroke;
        boolean spacePreserve;
        final Paint strokePaint;
        Style style;
        SVGBase.Box viewBox;
        SVGBase.Box viewPort;
        
        RendererState() {
            (this.fillPaint = new Paint()).setFlags(193);
            if (SVGAndroidRenderer.SUPPORTS_FONT_HINTING) {
                this.fillPaint.setHinting(0);
            }
            this.fillPaint.setStyle(Paint$Style.FILL);
            this.fillPaint.setTypeface(Typeface.DEFAULT);
            (this.strokePaint = new Paint()).setFlags(193);
            if (SVGAndroidRenderer.SUPPORTS_FONT_HINTING) {
                this.strokePaint.setHinting(0);
            }
            this.strokePaint.setStyle(Paint$Style.STROKE);
            this.strokePaint.setTypeface(Typeface.DEFAULT);
            this.fontFeatureSet = new CSSFontFeatureSettings();
            this.fontVariationSet = new CSSFontVariationSettings();
            this.style = Style.getDefaultStyle();
        }
        
        RendererState(final RendererState rendererState) {
            this.hasFill = rendererState.hasFill;
            this.hasStroke = rendererState.hasStroke;
            this.fillPaint = new Paint(rendererState.fillPaint);
            this.strokePaint = new Paint(rendererState.strokePaint);
            final SVGBase.Box viewPort = rendererState.viewPort;
            if (viewPort != null) {
                this.viewPort = new SVGBase.Box(viewPort);
            }
            final SVGBase.Box viewBox = rendererState.viewBox;
            if (viewBox != null) {
                this.viewBox = new SVGBase.Box(viewBox);
            }
            this.spacePreserve = rendererState.spacePreserve;
            this.fontFeatureSet = new CSSFontFeatureSettings(rendererState.fontFeatureSet);
            this.fontVariationSet = new CSSFontVariationSettings(rendererState.fontVariationSet);
            try {
                this.style = (Style)rendererState.style.clone();
            }
            catch (final CloneNotSupportedException ex) {
                Log.e("SVGAndroidRenderer", "Unexpected clone error", (Throwable)ex);
                this.style = Style.getDefaultStyle();
            }
        }
    }
    
    private abstract static class TextProcessor
    {
        public boolean doTextContainer(final SVGBase$TextContainer svgBase$TextContainer) {
            return true;
        }
        
        public abstract void processText(final String p0);
    }
}
