package com.otaliastudios.cameraview.filter;

import com.otaliastudios.opengl.core.Egloo;
import android.opengl.GLES20;
import com.otaliastudios.opengl.program.GlProgram;
import com.otaliastudios.opengl.texture.GlFramebuffer;
import com.otaliastudios.opengl.texture.GlTexture;
import java.util.Arrays;
import java.util.Iterator;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import com.otaliastudios.cameraview.size.Size;
import java.util.List;

public class MultiFilter implements Filter, OneParameterFilter, TwoParameterFilter
{
    final List<Filter> filters;
    private final Object lock;
    private float parameter1;
    private float parameter2;
    private Size size;
    final Map<Filter, MultiFilter.MultiFilter$State> states;
    
    public MultiFilter(final Collection<Filter> collection) {
        this.filters = (List<Filter>)new ArrayList();
        this.states = (Map<Filter, MultiFilter.MultiFilter$State>)new HashMap();
        this.lock = new Object();
        this.size = null;
        this.parameter1 = 0.0f;
        this.parameter2 = 0.0f;
        final Iterator iterator = collection.iterator();
        while (iterator.hasNext()) {
            this.addFilter((Filter)iterator.next());
        }
    }
    
    public MultiFilter(final Filter... array) {
        this((Collection<Filter>)Arrays.asList((Object[])array));
    }
    
    private void maybeCreateFramebuffer(final Filter filter, final boolean b, final boolean b2) {
        final MultiFilter.MultiFilter$State multiFilter$State = (MultiFilter.MultiFilter$State)this.states.get((Object)filter);
        if (b2) {
            MultiFilter.MultiFilter$State.access$102(multiFilter$State, false);
            return;
        }
        if (MultiFilter.MultiFilter$State.access$100(multiFilter$State)) {
            this.maybeDestroyFramebuffer(filter);
            MultiFilter.MultiFilter$State.access$102(multiFilter$State, false);
        }
        if (!multiFilter$State.isFramebufferCreated) {
            multiFilter$State.isFramebufferCreated = true;
            MultiFilter.MultiFilter$State.access$202(multiFilter$State, new GlTexture(33984, 3553, multiFilter$State.size.getWidth(), multiFilter$State.size.getHeight()));
            MultiFilter.MultiFilter$State.access$302(multiFilter$State, new GlFramebuffer());
            MultiFilter.MultiFilter$State.access$300(multiFilter$State).attach(MultiFilter.MultiFilter$State.access$200(multiFilter$State));
        }
    }
    
    private void maybeCreateProgram(final Filter filter, final boolean b, final boolean b2) {
        final MultiFilter.MultiFilter$State multiFilter$State = (MultiFilter.MultiFilter$State)this.states.get((Object)filter);
        if (multiFilter$State.isProgramCreated) {
            return;
        }
        multiFilter$State.isProgramCreated = true;
        String s;
        if (b) {
            s = filter.getFragmentShader();
        }
        else {
            s = filter.getFragmentShader().replace((CharSequence)"samplerExternalOES ", (CharSequence)"sampler2D ");
        }
        MultiFilter.MultiFilter$State.access$002(multiFilter$State, GlProgram.create(filter.getVertexShader(), s));
        filter.onCreate(MultiFilter.MultiFilter$State.access$000(multiFilter$State));
    }
    
    private void maybeDestroyFramebuffer(final Filter filter) {
        final MultiFilter.MultiFilter$State multiFilter$State = (MultiFilter.MultiFilter$State)this.states.get((Object)filter);
        if (!multiFilter$State.isFramebufferCreated) {
            return;
        }
        multiFilter$State.isFramebufferCreated = false;
        MultiFilter.MultiFilter$State.access$300(multiFilter$State).release();
        MultiFilter.MultiFilter$State.access$302(multiFilter$State, (GlFramebuffer)null);
        MultiFilter.MultiFilter$State.access$200(multiFilter$State).release();
        MultiFilter.MultiFilter$State.access$202(multiFilter$State, (GlTexture)null);
    }
    
    private void maybeDestroyProgram(final Filter filter) {
        final MultiFilter.MultiFilter$State multiFilter$State = (MultiFilter.MultiFilter$State)this.states.get((Object)filter);
        if (!multiFilter$State.isProgramCreated) {
            return;
        }
        multiFilter$State.isProgramCreated = false;
        filter.onDestroy();
        GLES20.glDeleteProgram(MultiFilter.MultiFilter$State.access$000(multiFilter$State));
        MultiFilter.MultiFilter$State.access$002(multiFilter$State, -1);
    }
    
    private void maybeSetSize(final Filter filter) {
        final MultiFilter.MultiFilter$State multiFilter$State = (MultiFilter.MultiFilter$State)this.states.get((Object)filter);
        final Size size = this.size;
        if (size != null && !size.equals((Object)multiFilter$State.size)) {
            multiFilter$State.size = this.size;
            MultiFilter.MultiFilter$State.access$102(multiFilter$State, true);
            filter.setSize(this.size.getWidth(), this.size.getHeight());
        }
    }
    
    public void addFilter(final Filter filter) {
        if (filter instanceof MultiFilter) {
            final Iterator iterator = ((MultiFilter)filter).filters.iterator();
            while (iterator.hasNext()) {
                this.addFilter((Filter)iterator.next());
            }
            return;
        }
        final Object lock = this.lock;
        synchronized (lock) {
            if (!this.filters.contains((Object)filter)) {
                this.filters.add((Object)filter);
                this.states.put((Object)filter, (Object)new MultiFilter.MultiFilter$State());
            }
        }
    }
    
    public Filter copy() {
        final Object lock = this.lock;
        synchronized (lock) {
            final MultiFilter multiFilter = new MultiFilter(new Filter[0]);
            if (this.size != null) {
                multiFilter.setSize(this.size.getWidth(), this.size.getHeight());
            }
            final Iterator iterator = this.filters.iterator();
            while (iterator.hasNext()) {
                multiFilter.addFilter(((Filter)iterator.next()).copy());
            }
            return (Filter)multiFilter;
        }
    }
    
    public void draw(final long n, final float[] array) {
        final Object lock;
        monitorenter(lock = this.lock);
        int i = 0;
        try {
            while (i < this.filters.size()) {
                boolean b = true;
                final boolean b2 = i == 0;
                if (i != this.filters.size() - 1) {
                    b = false;
                }
                final Filter filter = (Filter)this.filters.get(i);
                final MultiFilter.MultiFilter$State multiFilter$State = (MultiFilter.MultiFilter$State)this.states.get((Object)filter);
                this.maybeSetSize(filter);
                this.maybeCreateProgram(filter, b2, b);
                this.maybeCreateFramebuffer(filter, b2, b);
                GLES20.glUseProgram(MultiFilter.MultiFilter$State.access$000(multiFilter$State));
                if (!b) {
                    MultiFilter.MultiFilter$State.access$300(multiFilter$State).bind();
                    GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
                }
                else {
                    GLES20.glBindFramebuffer(36160, 0);
                }
                if (b2) {
                    filter.draw(n, array);
                }
                else {
                    filter.draw(n, Egloo.IDENTITY_MATRIX);
                }
                if (!b) {
                    MultiFilter.MultiFilter$State.access$200(multiFilter$State).bind();
                }
                else {
                    GLES20.glBindTexture(3553, 0);
                    GLES20.glActiveTexture(33984);
                }
                GLES20.glUseProgram(0);
                ++i;
            }
        }
        finally {
            monitorexit(lock);
        }
    }
    
    public String getFragmentShader() {
        return "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 vTextureCoord;\nuniform samplerExternalOES sTexture;\nvoid main() {\n    gl_FragColor = texture2D(sTexture, vTextureCoord);\n}\n";
    }
    
    public float getParameter1() {
        return this.parameter1;
    }
    
    public float getParameter2() {
        return this.parameter2;
    }
    
    public String getVertexShader() {
        return "uniform mat4 uMVPMatrix;\nuniform mat4 uTexMatrix;\nattribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n    gl_Position = uMVPMatrix * aPosition;\n    vTextureCoord = (uTexMatrix * aTextureCoord).xy;\n}\n";
    }
    
    public void onCreate(final int n) {
    }
    
    public void onDestroy() {
        final Object lock = this.lock;
        synchronized (lock) {
            for (final Filter filter : this.filters) {
                this.maybeDestroyFramebuffer(filter);
                this.maybeDestroyProgram(filter);
            }
        }
    }
    
    public void setParameter1(final float n) {
        this.parameter1 = n;
        final Object lock = this.lock;
        synchronized (lock) {
            for (final Filter filter : this.filters) {
                if (filter instanceof OneParameterFilter) {
                    ((OneParameterFilter)filter).setParameter1(n);
                }
            }
        }
    }
    
    public void setParameter2(final float n) {
        this.parameter2 = n;
        final Object lock = this.lock;
        synchronized (lock) {
            for (final Filter filter : this.filters) {
                if (filter instanceof TwoParameterFilter) {
                    ((TwoParameterFilter)filter).setParameter2(n);
                }
            }
        }
    }
    
    public void setSize(final int n, final int n2) {
        this.size = new Size(n, n2);
        final Object lock = this.lock;
        synchronized (lock) {
            final Iterator iterator = this.filters.iterator();
            while (iterator.hasNext()) {
                this.maybeSetSize((Filter)iterator.next());
            }
        }
    }
}
