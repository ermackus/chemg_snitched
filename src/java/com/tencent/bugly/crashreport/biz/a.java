package com.tencent.bugly.crashreport.biz;

import com.tencent.bugly.proguard.w;
import com.tencent.bugly.proguard.am;
import com.tencent.bugly.proguard.ar;
import java.util.Iterator;
import com.tencent.bugly.proguard.t;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.proguard.k;
import java.util.ArrayList;
import com.tencent.bugly.proguard.u;
import java.util.List;
import com.tencent.bugly.proguard.o;
import com.tencent.bugly.proguard.p;
import android.database.Cursor;
import com.tencent.bugly.proguard.x;
import android.os.Parcelable;
import com.tencent.bugly.proguard.z;
import android.content.ContentValues;
import android.content.Context;

public final class a
{
    private Context a;
    private long b;
    private int c;
    private boolean d;
    
    public a(final Context a, final boolean d) {
        this.d = true;
        this.a = a;
        this.d = d;
    }
    
    private static ContentValues a(final UserInfoBean userInfoBean) {
        if (userInfoBean == null) {
            return null;
        }
        try {
            final ContentValues contentValues = new ContentValues();
            if (userInfoBean.a > 0L) {
                contentValues.put("_id", Long.valueOf(userInfoBean.a));
            }
            contentValues.put("_tm", Long.valueOf(userInfoBean.e));
            contentValues.put("_ut", Long.valueOf(userInfoBean.f));
            contentValues.put("_tp", Integer.valueOf(userInfoBean.b));
            contentValues.put("_pc", userInfoBean.c);
            contentValues.put("_dt", z.a((Parcelable)userInfoBean));
            return contentValues;
        }
        finally {
            final Throwable t;
            if (!x.a(t)) {
                t.printStackTrace();
            }
            return null;
        }
    }
    
    private static UserInfoBean a(final Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            final byte[] blob = cursor.getBlob(cursor.getColumnIndex("_dt"));
            if (blob == null) {
                return null;
            }
            final long long1 = cursor.getLong(cursor.getColumnIndex("_id"));
            final UserInfoBean userInfoBean = z.a(blob, UserInfoBean.CREATOR);
            if (userInfoBean != null) {
                userInfoBean.a = long1;
            }
            return userInfoBean;
        }
        finally {
            final Throwable t;
            if (!x.a(t)) {
                t.printStackTrace();
            }
            return null;
        }
    }
    
    static /* synthetic */ void a(final a a, final UserInfoBean userInfoBean, final boolean b) {
        if (userInfoBean != null) {
            if (!b && userInfoBean.b != 1) {
                final List<UserInfoBean> a2 = a.a(a.a(a.a).d);
                if (a2 != null && a2.size() >= 20) {
                    x.a("[UserInfo] There are too many user info in local: %d", a2.size());
                    return;
                }
            }
            final long a3 = p.a().a("t_ui", a(userInfoBean), null, true);
            if (a3 >= 0L) {
                x.c("[Database] insert %s success with ID: %d", "t_ui", a3);
                userInfoBean.a = a3;
            }
        }
    }
    
    private static void a(final List<UserInfoBean> list) {
        if (list != null) {
            if (list.size() != 0) {
                final StringBuilder sb = new StringBuilder();
                for (int n = 0; n < list.size() && n < 50; ++n) {
                    final UserInfoBean userInfoBean = (UserInfoBean)list.get(n);
                    sb.append(" or _id");
                    sb.append(" = ");
                    sb.append(userInfoBean.a);
                }
                String s2;
                final String s = s2 = sb.toString();
                if (s.length() > 0) {
                    s2 = s.substring(4);
                }
                sb.setLength(0);
                try {
                    x.c("[Database] deleted %s data %d", "t_ui", p.a().a("t_ui", s2, null, null, true));
                }
                finally {
                    final Throwable t;
                    if (!x.a(t)) {
                        t.printStackTrace();
                    }
                }
            }
        }
    }
    
    private void c() {
        synchronized (this) {
            if (!this.d) {
                return;
            }
            final u a = u.a();
            if (a == null) {
                return;
            }
            final com.tencent.bugly.crashreport.common.strategy.a a2 = com.tencent.bugly.crashreport.common.strategy.a.a();
            if (a2 == null) {
                return;
            }
            if (a2.b() && !a.b(1001)) {
                return;
            }
            final String d = com.tencent.bugly.crashreport.common.info.a.a(this.a).d;
            final ArrayList list = new ArrayList();
            final List<UserInfoBean> a3 = this.a(d);
            Object o = null;
            boolean b = false;
            Label_0444: {
                if (a3 != null) {
                    final int n = a3.size() - 20;
                    if (n > 0) {
                        int n2;
                        for (int i = 0; i < a3.size() - 1; i = n2) {
                            int j;
                            for (n2 = (j = i + 1); j < a3.size(); ++j) {
                                if (((UserInfoBean)a3.get(i)).e > ((UserInfoBean)a3.get(j)).e) {
                                    final UserInfoBean userInfoBean = (UserInfoBean)a3.get(i);
                                    a3.set(i, a3.get(j));
                                    a3.set(j, (Object)userInfoBean);
                                }
                            }
                        }
                        for (int k = 0; k < n; ++k) {
                            ((List)list).add(a3.get(k));
                        }
                    }
                    final Iterator iterator = a3.iterator();
                    int n3 = 0;
                    while (iterator.hasNext()) {
                        final UserInfoBean userInfoBean2 = (UserInfoBean)iterator.next();
                        if (userInfoBean2.f != -1L) {
                            iterator.remove();
                            if (userInfoBean2.e < z.b()) {
                                ((List)list).add((Object)userInfoBean2);
                            }
                        }
                        if (userInfoBean2.e > System.currentTimeMillis() - 600000L && (userInfoBean2.b == 1 || userInfoBean2.b == 4 || userInfoBean2.b == 3)) {
                            ++n3;
                        }
                    }
                    o = a3;
                    if (n3 > 15) {
                        x.d("[UserInfo] Upload user info too many times in 10 min: %d", n3);
                        b = false;
                        o = a3;
                        break Label_0444;
                    }
                }
                else {
                    o = new ArrayList();
                }
                b = true;
            }
            if (((List)list).size() > 0) {
                a((List<UserInfoBean>)list);
            }
            if (!b || ((List)o).size() == 0) {
                x.c("[UserInfo] There is no user info in local database.", new Object[0]);
                return;
            }
            x.c("[UserInfo] Upload user info(size: %d)", ((List)o).size());
            int n4;
            if (this.c == 1) {
                n4 = 1;
            }
            else {
                n4 = 2;
            }
            final ar a4 = com.tencent.bugly.proguard.a.a((List<UserInfoBean>)o, n4);
            if (a4 == null) {
                x.d("[UserInfo] Failed to create UserInfoPackage.", new Object[0]);
                return;
            }
            final byte[] a5 = com.tencent.bugly.proguard.a.a((k)a4);
            if (a5 == null) {
                x.d("[UserInfo] Failed to encode data.", new Object[0]);
                return;
            }
            final am a6 = com.tencent.bugly.proguard.a.a(this.a, 840, a5);
            if (a6 == null) {
                x.d("[UserInfo] Request package is null.", new Object[0]);
                return;
            }
            u.a().a(1001, a6, com.tencent.bugly.crashreport.common.strategy.a.a().c().p, StrategyBean.a, (t)new a$1(this, (List)o), this.c == 1);
        }
    }
    
    public final List<UserInfoBean> a(String s) {
        try {
            if (z.a(s)) {
                s = null;
            }
            else {
                final StringBuilder sb = new StringBuilder("_pc = '");
                sb.append(s);
                sb.append("'");
                s = sb.toString();
            }
            s = (String)p.a().a("t_ui", null, s, null, null, true);
            if (s == null) {
                if (s != null) {
                    ((Cursor)s).close();
                }
                return null;
            }
            try {
                final StringBuilder sb2 = new StringBuilder();
                final ArrayList list = new ArrayList();
                while (((Cursor)s).moveToNext()) {
                    final UserInfoBean a = a((Cursor)s);
                    if (a != null) {
                        ((List)list).add((Object)a);
                    }
                    else {
                        try {
                            final long long1 = ((Cursor)s).getLong(((Cursor)s).getColumnIndex("_id"));
                            sb2.append(" or _id");
                            sb2.append(" = ");
                            sb2.append(long1);
                        }
                        finally {
                            x.d("[Database] unknown id.", new Object[0]);
                        }
                    }
                }
                final String string = sb2.toString();
                if (string.length() > 0) {
                    x.d("[Database] deleted %s error data %d", "t_ui", p.a().a("t_ui", string.substring(4), null, null, true));
                }
                if (s != null) {
                    ((Cursor)s).close();
                }
                return (List<UserInfoBean>)list;
            }
            finally {}
        }
        finally {
            s = null;
        }
        try {
            final Throwable t;
            if (!x.a(t)) {
                t.printStackTrace();
            }
            return null;
        }
        finally {
            if (s != null) {
                ((Cursor)s).close();
            }
        }
    }
    
    public final void a() {
        this.b = z.b() + 86400000L;
        w.a().a((Runnable)new b(), this.b - System.currentTimeMillis() + 5000L);
    }
    
    public final void a(final int b, final boolean b2, final long n) {
        final com.tencent.bugly.crashreport.common.strategy.a a = com.tencent.bugly.crashreport.common.strategy.a.a();
        int o = 0;
        if (a != null && !a.c().f && b != 1 && b != 3) {
            x.e("UserInfo is disable", new Object[0]);
            return;
        }
        if (b == 1 || b == 3) {
            ++this.c;
        }
        final a a2 = com.tencent.bugly.crashreport.common.info.a.a(this.a);
        final UserInfoBean userInfoBean = new UserInfoBean();
        userInfoBean.b = b;
        userInfoBean.c = a2.d;
        userInfoBean.d = a2.g();
        userInfoBean.e = System.currentTimeMillis();
        userInfoBean.f = -1L;
        userInfoBean.n = a2.k;
        if (b == 1) {
            o = 1;
        }
        userInfoBean.o = o;
        userInfoBean.l = a2.a();
        userInfoBean.m = a2.q;
        userInfoBean.g = a2.r;
        userInfoBean.h = a2.s;
        userInfoBean.i = a2.t;
        userInfoBean.k = a2.u;
        userInfoBean.r = a2.t();
        userInfoBean.s = a2.y();
        userInfoBean.p = a2.z();
        userInfoBean.q = a2.A();
        w.a().a((Runnable)new a(userInfoBean, b2), 0L);
    }
    
    public final void b() {
        final w a = w.a();
        if (a != null) {
            a.a((Runnable)new Runnable(this) {
                private a a;
                
                public final void run() {
                    try {
                        this.a.c();
                    }
                    finally {
                        final Throwable t;
                        x.a(t);
                    }
                }
            });
        }
    }
    
    final class a implements Runnable
    {
        private boolean a;
        private UserInfoBean b;
        private com.tencent.bugly.crashreport.biz.a c;
        
        public a(final com.tencent.bugly.crashreport.biz.a c, final UserInfoBean b, final boolean a) {
            this.c = c;
            this.b = b;
            this.a = a;
        }
        
        public final void run() {
            try {
                if (this.b != null) {
                    final UserInfoBean b = this.b;
                    if (b != null) {
                        final com.tencent.bugly.crashreport.common.info.a b2 = com.tencent.bugly.crashreport.common.info.a.b();
                        if (b2 != null) {
                            b.j = b2.e();
                        }
                    }
                    x.c("[UserInfo] Record user info.", new Object[0]);
                    com.tencent.bugly.crashreport.biz.a.a(this.c, this.b, false);
                }
                if (this.a) {
                    final com.tencent.bugly.crashreport.biz.a c = this.c;
                    final w a = w.a();
                    if (a != null) {
                        a.a((Runnable)new Runnable(c) {
                            private a a;
                            
                            public final void run() {
                                try {
                                    this.a.c();
                                }
                                finally {
                                    final Throwable t;
                                    x.a(t);
                                }
                            }
                        });
                    }
                }
            }
            finally {
                final Throwable t;
                if (!x.a(t)) {
                    t.printStackTrace();
                }
            }
        }
    }
    
    final class b implements Runnable
    {
        private a a;
        
        b(final a a) {
            this.a = a;
        }
        
        public final void run() {
            final long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis < this.a.b) {
                w.a().a((Runnable)this.a.new b(), this.a.b - currentTimeMillis + 5000L);
                return;
            }
            this.a.a(3, false, 0L);
            this.a.a();
        }
    }
    
    final class c implements Runnable
    {
        private long a;
        private a b;
        
        public c(final a b, final long a) {
            this.b = b;
            this.a = 21600000L;
            this.a = a;
        }
        
        public final void run() {
            final a b = this.b;
            final w a = w.a();
            if (a != null) {
                a.a((Runnable)new Runnable(b) {
                    private a a;
                    
                    public final void run() {
                        try {
                            this.a.c();
                        }
                        finally {
                            final Throwable t;
                            x.a(t);
                        }
                    }
                });
            }
            final a b2 = this.b;
            final long a2 = this.a;
            w.a().a((Runnable)b2.new c(a2), a2);
        }
    }
}
