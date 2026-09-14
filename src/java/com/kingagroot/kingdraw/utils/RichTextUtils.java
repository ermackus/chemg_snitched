package com.kingagroot.kingdraw.utils;

import android.os.Handler;
import android.text.method.LinkMovementMethod;
import android.text.Spanned;
import android.os.Message;
import android.text.Html$TagHandler;
import android.text.Html;
import android.text.Html$ImageGetter;
import android.widget.TextView;
import java.io.InputStream;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.BitmapFactory;
import com.kingagroot.kingdraw.base.MApplication;
import java.net.URL;
import android.util.Log;
import android.util.Base64;
import java.io.OutputStream;
import android.graphics.Bitmap$CompressFormat;
import java.io.ByteArrayOutputStream;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.content.Context;

public class RichTextUtils
{
    private static final String TAG = "RichTextUtils >>> ";
    
    private static String bitmapToString(final Bitmap bitmap) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap$CompressFormat.PNG, 100, (OutputStream)byteArrayOutputStream);
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
    }
    
    private static boolean checkIsUrl(String host) {
        try {
            final StringBuilder sb = new StringBuilder();
            sb.append("checkIsUrl");
            sb.append(host);
            Log.d("RichTextUtils >>> ", sb.toString());
            host = new URL(host).getHost();
            if (host != null) {
                return true;
            }
            return false;
        }
        catch (final Exception ex) {
            return false;
        }
    }
    
    private static Drawable getDrawableByName(final Context context, final String s) {
        if (context == null) {
            return null;
        }
        final int identifier = context.getResources().getIdentifier(s, "drawable", context.getPackageName());
        if (identifier == 0) {
            return null;
        }
        return context.getDrawable(identifier);
    }
    
    private static Drawable getUrlDrawable(final String s) {
        try {
            final InputStream openStream = new URL(s).openStream();
            final StringBuilder sb = new StringBuilder();
            sb.append("is is ");
            sb.append(openStream.toString());
            Log.d("RichTextUtils >>> ", sb.toString());
            final BitmapDrawable bitmapDrawable = new BitmapDrawable(MApplication.getInstance().getResources(), BitmapFactory.decodeStream(openStream));
            ((Drawable)bitmapDrawable).setBounds(0, 0, ((Drawable)bitmapDrawable).getIntrinsicWidth(), ((Drawable)bitmapDrawable).getIntrinsicHeight());
            return (Drawable)bitmapDrawable;
        }
        catch (final Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public static void showRichHtmlWithImageContent(final TextView textView, final String s) {
        new Thread(s, new MyHander(textView)) {
            final MyHander val$myHander;
            final String val$richText;
            
            public void run() {
                final Spanned fromHtml = Html.fromHtml(this.val$richText, (Html$ImageGetter)new Html$ImageGetter(this) {
                    final RichTextUtils$3 this$0;
                    
                    public Drawable getDrawable(String substring) {
                        final StringBuilder sb = new StringBuilder();
                        sb.append("[showRichHtmlWithContent] source is ");
                        sb.append(substring);
                        Log.d("RichTextUtils >>> ", sb.toString());
                        if (substring == null) {
                            return null;
                        }
                        Object access$100;
                        if (checkIsUrl(substring)) {
                            access$100 = getUrlDrawable(substring);
                        }
                        else {
                            substring = substring.substring(substring.indexOf("base64,") + 7);
                            final StringBuilder sb2 = new StringBuilder();
                            sb2.append("imageContent length is ");
                            sb2.append(substring.length());
                            Log.d("RichTextUtils >>> ", sb2.toString());
                            access$100 = new BitmapDrawable(MApplication.getInstance().getResources(), stringToBitmap(substring));
                            ((Drawable)access$100).setBounds(0, 0, ((Drawable)access$100).getIntrinsicWidth(), ((Drawable)access$100).getIntrinsicHeight());
                        }
                        return (Drawable)access$100;
                    }
                }, (Html$TagHandler)null);
                final StringBuilder sb = new StringBuilder();
                sb.append("imageContent richText is ");
                sb.append(this.val$richText);
                Log.d("RichTextUtils >>> ", sb.toString());
                final Message message = new Message();
                message.what = 1;
                message.obj = fromHtml;
                this.val$myHander.sendMessage(message);
            }
        }.start();
    }
    
    public static void showRichHtmlWithImageName(final TextView textView, final String s) {
        final Spanned fromHtml = Html.fromHtml(s, (Html$ImageGetter)new Html$ImageGetter() {
            public Drawable getDrawable(String s) {
                final StringBuilder sb = new StringBuilder();
                sb.append("[showRichHtmlWithImageName] source is ");
                sb.append(s);
                Log.d("RichTextUtils >>> ", sb.toString());
                if (s == null) {
                    return null;
                }
                s = s.split("\\.")[0];
                final StringBuilder sb2 = new StringBuilder();
                sb2.append(" resName is ");
                sb2.append(s);
                Log.d("RichTextUtils >>> ", sb2.toString());
                final Drawable access$000 = getDrawableByName(MApplication.getInstance().getApplicationContext(), s);
                if (access$000 != null) {
                    access$000.setBounds(0, 0, access$000.getIntrinsicWidth(), access$000.getIntrinsicHeight());
                }
                return access$000;
            }
        }, (Html$TagHandler)null);
        if (textView != null) {
            textView.setText((CharSequence)fromHtml);
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("[showRichHtmlWithImageName] charSequence is ");
        sb.append((Object)fromHtml);
        Log.d("RichTextUtils >>> ", sb.toString());
    }
    
    public static void showRichHtmlWithImageUrl(final TextView textView, final String s) {
        new Thread((Runnable)new Runnable(s, new MyHander(textView)) {
            final MyHander val$myHander;
            final String val$richText;
            
            public void run() {
                final Spanned fromHtml = Html.fromHtml(this.val$richText, (Html$ImageGetter)new Html$ImageGetter(this) {
                    final RichTextUtils$2 this$0;
                    
                    public Drawable getDrawable(final String s) {
                        final StringBuilder sb = new StringBuilder();
                        sb.append("[showRichTextWithImageUrl] source is ");
                        sb.append(s);
                        Log.d("RichTextUtils >>> ", sb.toString());
                        if (s == null) {
                            return null;
                        }
                        return getUrlDrawable(s);
                    }
                }, (Html$TagHandler)null);
                final Message message = new Message();
                message.what = 1;
                message.obj = fromHtml;
                this.val$myHander.sendMessage(message);
            }
        }).start();
    }
    
    private static Bitmap stringToBitmap(final String s) {
        final byte[] decode = Base64.decode(s, 0);
        return BitmapFactory.decodeByteArray(decode, 0, decode.length);
    }
    
    private static class MyHander extends Handler
    {
        TextView textView;
        
        MyHander(final TextView textView) {
            this.textView = textView;
        }
        
        public void handleMessage(final Message message) {
            super.handleMessage(message);
            if (message.what == 1) {
                final TextView textView = this.textView;
                if (textView != null) {
                    textView.setText((CharSequence)message.obj);
                }
            }
        }
    }
}
