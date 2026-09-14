package com.otaliastudios.cameraview.filter;

import com.otaliastudios.opengl.draw.GlRect;
import com.otaliastudios.cameraview.size.Size;
import com.otaliastudios.opengl.draw.GlDrawable;
import com.otaliastudios.opengl.program.GlTextureProgram;
import com.otaliastudios.cameraview.CameraLogger;

public abstract class BaseFilter implements Filter
{
    protected static final String DEFAULT_FRAGMENT_TEXTURE_COORDINATE_NAME = "vTextureCoord";
    protected static final String DEFAULT_VERTEX_MVP_MATRIX_NAME = "uMVPMatrix";
    protected static final String DEFAULT_VERTEX_POSITION_NAME = "aPosition";
    protected static final String DEFAULT_VERTEX_TEXTURE_COORDINATE_NAME = "aTextureCoord";
    protected static final String DEFAULT_VERTEX_TRANSFORM_MATRIX_NAME = "uTexMatrix";
    private static final CameraLogger LOG;
    private static final String TAG;
    protected String fragmentTextureCoordinateName;
    GlTextureProgram program;
    private GlDrawable programDrawable;
    Size size;
    protected String vertexModelViewProjectionMatrixName;
    protected String vertexPositionName;
    protected String vertexTextureCoordinateName;
    protected String vertexTransformMatrixName;
    
    static {
        LOG = CameraLogger.create(TAG = BaseFilter.class.getSimpleName());
    }
    
    public BaseFilter() {
        this.program = null;
        this.programDrawable = null;
        this.vertexPositionName = "aPosition";
        this.vertexTextureCoordinateName = "aTextureCoord";
        this.vertexModelViewProjectionMatrixName = "uMVPMatrix";
        this.vertexTransformMatrixName = "uTexMatrix";
        this.fragmentTextureCoordinateName = "vTextureCoord";
    }
    
    private static String createDefaultFragmentShader(final String s) {
        final StringBuilder sb = new StringBuilder();
        sb.append("#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 ");
        sb.append(s);
        sb.append(";\nuniform samplerExternalOES sTexture;\nvoid main() {\n  gl_FragColor = texture2D(sTexture, ");
        sb.append(s);
        sb.append(");\n}\n");
        return sb.toString();
    }
    
    private static String createDefaultVertexShader(final String s, final String s2, final String s3, final String s4, final String s5) {
        final StringBuilder sb = new StringBuilder();
        sb.append("uniform mat4 ");
        sb.append(s3);
        sb.append(";\nuniform mat4 ");
        sb.append(s4);
        sb.append(";\nattribute vec4 ");
        sb.append(s);
        sb.append(";\nattribute vec4 ");
        sb.append(s2);
        sb.append(";\nvarying vec2 ");
        sb.append(s5);
        sb.append(";\nvoid main() {\n    gl_Position = ");
        sb.append(s3);
        sb.append(" * ");
        sb.append(s);
        sb.append(";\n    ");
        sb.append(s5);
        sb.append(" = (");
        sb.append(s4);
        sb.append(" * ");
        sb.append(s2);
        sb.append(").xy;\n}\n");
        return sb.toString();
    }
    
    public final BaseFilter copy() {
        final BaseFilter onCopy = this.onCopy();
        final Size size = this.size;
        if (size != null) {
            onCopy.setSize(size.getWidth(), this.size.getHeight());
        }
        if (this instanceof OneParameterFilter) {
            ((OneParameterFilter)onCopy).setParameter1(((OneParameterFilter)this).getParameter1());
        }
        if (this instanceof TwoParameterFilter) {
            ((TwoParameterFilter)onCopy).setParameter2(((TwoParameterFilter)this).getParameter2());
        }
        return onCopy;
    }
    
    protected String createDefaultFragmentShader() {
        return createDefaultFragmentShader(this.fragmentTextureCoordinateName);
    }
    
    protected String createDefaultVertexShader() {
        return createDefaultVertexShader(this.vertexPositionName, this.vertexTextureCoordinateName, this.vertexModelViewProjectionMatrixName, this.vertexTransformMatrixName, this.fragmentTextureCoordinateName);
    }
    
    public void draw(final long n, final float[] array) {
        if (this.program == null) {
            BaseFilter.LOG.w(new Object[] { "Filter.draw() called after destroying the filter. This can happen rarely because of threading." });
        }
        else {
            this.onPreDraw(n, array);
            this.onDraw(n);
            this.onPostDraw(n);
        }
    }
    
    public String getVertexShader() {
        return this.createDefaultVertexShader();
    }
    
    protected BaseFilter onCopy() {
        try {
            return (BaseFilter)this.getClass().newInstance();
        }
        catch (final InstantiationException ex) {
            throw new RuntimeException("Filters should have a public no-arguments constructor.", (Throwable)ex);
        }
        catch (final IllegalAccessException ex2) {
            throw new RuntimeException("Filters should have a public no-arguments constructor.", (Throwable)ex2);
        }
    }
    
    public void onCreate(final int n) {
        this.program = new GlTextureProgram(n, this.vertexPositionName, this.vertexModelViewProjectionMatrixName, this.vertexTextureCoordinateName, this.vertexTransformMatrixName);
        this.programDrawable = (GlDrawable)new GlRect();
    }
    
    public void onDestroy() {
        this.program.release();
        this.program = null;
        this.programDrawable = null;
    }
    
    protected void onDraw(final long n) {
        this.program.onDraw(this.programDrawable);
    }
    
    protected void onPostDraw(final long n) {
        this.program.onPostDraw(this.programDrawable);
    }
    
    protected void onPreDraw(final long n, final float[] textureTransform) {
        this.program.setTextureTransform(textureTransform);
        final GlTextureProgram program = this.program;
        final GlDrawable programDrawable = this.programDrawable;
        program.onPreDraw(programDrawable, programDrawable.getModelMatrix());
    }
    
    public void setSize(final int n, final int n2) {
        this.size = new Size(n, n2);
    }
}
