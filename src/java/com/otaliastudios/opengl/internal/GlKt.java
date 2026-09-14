package com.otaliastudios.opengl.internal;

import kotlin.jvm.functions.Function1;
import java.nio.FloatBuffer;
import kotlin.Unit;
import kotlin.UIntArray;
import kotlin.jvm.internal.Intrinsics;
import java.nio.Buffer;
import android.opengl.GLES30;
import android.opengl.GLES20;
import kotlin.UInt;
import kotlin.Metadata;

@Metadata(d1 = { "\u0000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0007\n\u0002\b'\n\u0002\u0010\u0002\n\u0002\b&\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001e\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020\u0005H\u0080\b\u00f8\u0001\u0000\u00f8\u0001\u0001¢\u0006\u0004\b=\u0010>\u001a&\u0010?\u001a\u00020;2\u0006\u0010@\u001a\u00020\u00052\u0006\u0010A\u001a\u00020\u0005H\u0080\b\u00f8\u0001\u0000\u00f8\u0001\u0001¢\u0006\u0004\bB\u0010C\u001a&\u0010D\u001a\u00020;2\u0006\u0010E\u001a\u00020\u00052\u0006\u0010F\u001a\u00020\u0005H\u0080\b\u00f8\u0001\u0000\u00f8\u0001\u0001¢\u0006\u0004\bG\u0010C\u001a.\u0010H\u001a\u00020;2\u0006\u0010E\u001a\u00020\u00052\u0006\u0010I\u001a\u00020\u00052\u0006\u0010F\u001a\u00020\u0005H\u0080\b\u00f8\u0001\u0000\u00f8\u0001\u0001¢\u0006\u0004\bJ\u0010K\u001a&\u0010L\u001a\u00020;2\u0006\u0010E\u001a\u00020\u00052\u0006\u0010M\u001a\u00020\u0005H\u0080\b\u00f8\u0001\u0000\u00f8\u0001\u0001¢\u0006\u0004\bN\u0010C\u001a&\u0010O\u001a\u00020;2\u0006\u0010E\u001a\u00020\u00052\u0006\u0010P\u001a\u00020\u0005H\u0080\b\u00f8\u0001\u0000\u00f8\u0001\u0001¢\u0006\u0004\bQ\u0010C\u001a.\u0010R\u001a\u00020;2\u0006\u0010E\u001a\u00020\u00052\u0006\u0010S\u001a\u00020\u00012\u0006\u0010T\u001a\u00020\u0005H\u0080\b\u00f8\u0001\u0000\u00f8\u0001\u0001¢\u0006\u0004\bU\u0010K\u001a\u001e\u0010V\u001a\u00020\u00052\u0006\u0010E\u001a\u00020\u0005H\u0080\b\u00f8\u0001\u0000\u00f8\u0001\u0001¢\u0006\u0004\bW\u0010X\u001a\u001e\u0010Y\u001a\u00020;2\u0006\u0010A\u001a\u00020\u0005H\u0080\b\u00f8\u0001\u0000\u00f8\u0001\u0001¢\u0006\u0004\bZ\u0010>\u001a\u0011\u0010[\u001a\u00020\u0005H\u0080\b\u00f8\u0001\u0000¢\u0006\u0002\u0010\u0003\u001a\u001e\u0010\\\u001a\u00020\u00052\u0006\u0010]\u001a\u00020\u0005H\u0080\b\u00f8\u0001\u0000\u00f8\u0001\u0001¢\u0006\u0004\b^\u0010X\u001a&\u0010_\u001a\u00020;2\u0006\u0010`\u001a\u00020\u00012\u0006\u0010a\u001a\u00020bH\u0080\b\u00f8\u0001\u0000\u00f8\u0001\u0001¢\u0006\u0004\bc\u0010d\u001a&\u0010e\u001a\u00020;2\u0006\u0010`\u001a\u00020\u00012\u0006\u0010a\u001a\u00020bH\u0080\b\u00f8\u0001\u0000\u00f8\u0001\u0001¢\u0006\u0004\bf\u0010d\u001a\u001e\u0010g\u001a\u00020;2\u0006\u0010@\u001a\u00020\u0005H\u0080\b\u00f8\u0001\u0000\u00f8\u0001\u0001¢\u0006\u0004\bh\u0010>\u001a\u001e\u0010i\u001a\u00020;2\u0006\u0010A\u001a\u00020\u0005H\u0080\b\u00f8\u0001\u0000\u00f8\u0001\u0001¢\u0006\u0004\bj\u0010>\u001a&\u0010k\u001a\u00020;2\u0006\u0010`\u001a\u00020\u00012\u0006\u0010a\u001a\u00020bH\u0080\b\u00f8\u0001\u0000\u00f8\u0001\u0001¢\u0006\u0004\bl\u0010d\u001a\u001e\u0010m\u001a\u00020;2\u0006\u0010a\u001a\u00020\u0005H\u0080\b\u00f8\u0001\u0000\u00f8\u0001\u0001¢\u0006\u0004\bn\u0010>\u001a.\u0010o\u001a\u00020;2\u0006\u0010p\u001a\u00020\u00052\u0006\u0010q\u001a\u00020\u00012\u0006\u0010`\u001a\u00020\u0001H\u0080\b\u00f8\u0001\u0000\u00f8\u0001\u0001¢\u0006\u0004\br\u0010K\u001a6\u0010s\u001a\u00020;2\u0006\u0010p\u001a\u00020\u00052\u0006\u0010`\u001a\u00020\u00012\u0006\u0010]\u001a\u00020\u00052\u0006\u0010t\u001a\u00020uH\u0080\b\u00f8\u0001\u0000\u00f8\u0001\u0001¢\u0006\u0004\bv\u0010w\u001a\u001e\u0010x\u001a\u00020;2\u0006\u0010a\u001a\u00020\u0005H\u0080\b\u00f8\u0001\u0000\u00f8\u0001\u0001¢\u0006\u0004\by\u0010>\u001a>\u0010z\u001a\u00020;2\u0006\u0010E\u001a\u00020\u00052\u0006\u0010{\u001a\u00020\u00052\u0006\u0010|\u001a\u00020\u00052\u0006\u0010P\u001a\u00020\u00052\u0006\u0010}\u001a\u00020\u0001H\u0080\b\u00f8\u0001\u0000\u00f8\u0001\u0001¢\u0006\u0004\b~\u0010\u007f\u001a(\u0010\u0080\u0001\u001a\u00020;2\u0006\u0010`\u001a\u00020\u00012\u0006\u0010a\u001a\u00020bH\u0080\b\u00f8\u0001\u0000\u00f8\u0001\u0001¢\u0006\u0005\b\u0081\u0001\u0010d\u001a(\u0010\u0082\u0001\u001a\u00020;2\u0006\u0010`\u001a\u00020\u00012\u0006\u0010a\u001a\u00020bH\u0080\b\u00f8\u0001\u0000\u00f8\u0001\u0001¢\u0006\u0005\b\u0083\u0001\u0010d\u001a(\u0010\u0084\u0001\u001a\u00020;2\u0006\u0010`\u001a\u00020\u00012\u0006\u0010a\u001a\u00020bH\u0080\b\u00f8\u0001\u0000\u00f8\u0001\u0001¢\u0006\u0005\b\u0085\u0001\u0010d\u001a+\u0010\u0086\u0001\u001a\u00020\u00012\u0006\u0010@\u001a\u00020\u00052\b\u0010\u0087\u0001\u001a\u00030\u0088\u0001H\u0080\b\u00f8\u0001\u0000\u00f8\u0001\u0001¢\u0006\u0006\b\u0089\u0001\u0010\u008a\u0001\u001a\u0012\u0010\u008b\u0001\u001a\u00020\u0005H\u0080\b\u00f8\u0001\u0000¢\u0006\u0002\u0010\u0003\u001a*\u0010\u008c\u0001\u001a\u00020;2\u0007\u0010\u008d\u0001\u001a\u00020\u00052\u0007\u0010a\u001a\u00030\u008e\u0001H\u0080\b\u00f8\u0001\u0000\u00f8\u0001\u0001¢\u0006\u0005\b\u008f\u0001\u0010d\u001a,\u0010\u0090\u0001\u001a\r \u0091\u0001*\u0005\u0018\u00010\u0088\u00010\u0088\u00012\u0006\u0010@\u001a\u00020\u0005H\u0080\b\u00f8\u0001\u0000\u00f8\u0001\u0001¢\u0006\u0006\b\u0092\u0001\u0010\u0093\u0001\u001a4\u0010\u0094\u0001\u001a\u00020;2\u0006\u0010@\u001a\u00020\u00052\u0007\u0010\u008d\u0001\u001a\u00020\u00052\b\u0010\u0095\u0001\u001a\u00030\u008e\u0001H\u0080\b\u00f8\u0001\u0000\u00f8\u0001\u0001¢\u0006\u0006\b\u0096\u0001\u0010\u0097\u0001\u001a,\u0010\u0098\u0001\u001a\r \u0091\u0001*\u0005\u0018\u00010\u0088\u00010\u0088\u00012\u0006\u0010A\u001a\u00020\u0005H\u0080\b\u00f8\u0001\u0000\u00f8\u0001\u0001¢\u0006\u0006\b\u0099\u0001\u0010\u0093\u0001\u001a4\u0010\u009a\u0001\u001a\u00020;2\u0006\u0010A\u001a\u00020\u00052\u0007\u0010\u008d\u0001\u001a\u00020\u00052\b\u0010\u0095\u0001\u001a\u00030\u008e\u0001H\u0080\b\u00f8\u0001\u0000\u00f8\u0001\u0001¢\u0006\u0006\b\u009b\u0001\u0010\u0097\u0001\u001a+\u0010\u009c\u0001\u001a\u00020\u00012\u0006\u0010@\u001a\u00020\u00052\b\u0010\u0087\u0001\u001a\u00030\u0088\u0001H\u0080\b\u00f8\u0001\u0000\u00f8\u0001\u0001¢\u0006\u0006\b\u009d\u0001\u0010\u008a\u0001\u001a \u0010\u009e\u0001\u001a\u00020;2\u0006\u0010@\u001a\u00020\u0005H\u0080\b\u00f8\u0001\u0000\u00f8\u0001\u0001¢\u0006\u0005\b\u009f\u0001\u0010>\u001a+\u0010 \u0001\u001a\u00020;2\u0006\u0010A\u001a\u00020\u00052\b\u0010¡\u0001\u001a\u00030\u0088\u0001H\u0080\b\u00f8\u0001\u0000\u00f8\u0001\u0001¢\u0006\u0006\b¢\u0001\u0010£\u0001\u001ai\u0010¤\u0001\u001a\u00020;2\u0006\u0010E\u001a\u00020\u00052\u0006\u0010}\u001a\u00020\u00012\u0007\u0010¥\u0001\u001a\u00020\u00012\u0007\u0010¦\u0001\u001a\u00020\u00012\u0007\u0010§\u0001\u001a\u00020\u00012\u0007\u0010¨\u0001\u001a\u00020\u00012\u0007\u0010©\u0001\u001a\u00020\u00052\u0006\u0010]\u001a\u00020\u00052\t\u0010ª\u0001\u001a\u0004\u0018\u00010uH\u0080\b\u00f8\u0001\u0000\u00f8\u0001\u0001¢\u0006\u0006\b«\u0001\u0010¬\u0001\u001a3\u0010\u00ad\u0001\u001a\u00020;2\u0006\u0010E\u001a\u00020\u00052\u0007\u0010\u008d\u0001\u001a\u00020\u00052\u0007\u0010®\u0001\u001a\u00020\u0013H\u0080\b\u00f8\u0001\u0000\u00f8\u0001\u0001¢\u0006\u0006\b¯\u0001\u0010°\u0001\u001a2\u0010±\u0001\u001a\u00020;2\u0006\u0010E\u001a\u00020\u00052\u0007\u0010\u008d\u0001\u001a\u00020\u00052\u0007\u0010®\u0001\u001a\u00020\u0001H\u0080\b\u00f8\u0001\u0000\u00f8\u0001\u0001¢\u0006\u0005\b²\u0001\u0010K\u001a*\u0010³\u0001\u001a\u00020;2\u0007\u0010´\u0001\u001a\u00020\u00012\u0006\u0010`\u001a\u00020\u00012\r\u0010®\u0001\u001a\b0µ\u0001j\u0003`¶\u0001H\u0080\b\u001a%\u0010³\u0001\u001a\u00020;2\u0007\u0010´\u0001\u001a\u00020\u00012\u0006\u0010`\u001a\u00020\u00012\b\u0010®\u0001\u001a\u00030·\u0001H\u0080\b\u001a4\u0010¸\u0001\u001a\u00020;2\u0007\u0010´\u0001\u001a\u00020\u00012\u0006\u0010`\u001a\u00020\u00012\b\u0010¹\u0001\u001a\u00030º\u00012\r\u0010®\u0001\u001a\b0µ\u0001j\u0003`¶\u0001H\u0080\b\u001a/\u0010¸\u0001\u001a\u00020;2\u0007\u0010´\u0001\u001a\u00020\u00012\u0006\u0010`\u001a\u00020\u00012\b\u0010¹\u0001\u001a\u00030º\u00012\b\u0010®\u0001\u001a\u00030·\u0001H\u0080\b\u001a \u0010»\u0001\u001a\u00020;2\u0006\u0010@\u001a\u00020\u0005H\u0080\b\u00f8\u0001\u0000\u00f8\u0001\u0001¢\u0006\u0005\b¼\u0001\u0010>\u001aM\u0010½\u0001\u001a\u00020;2\u0006\u0010I\u001a\u00020\u00052\u0006\u0010S\u001a\u00020\u00012\u0006\u0010]\u001a\u00020\u00052\b\u0010¾\u0001\u001a\u00030º\u00012\u0007\u0010¿\u0001\u001a\u00020\u00012\u0007\u0010\u00c0\u0001\u001a\u00020uH\u0080\b\u00f8\u0001\u0000\u00f8\u0001\u0001¢\u0006\u0006\b\u00c1\u0001\u0010\u00c2\u0001\u001aW\u0010\u00c3\u0001\u001a\u0003H\u00c4\u0001\"\u0005\b\u0000\u0010\u00c4\u00012\u0007\u0010¡\u0001\u001a\u00020b2\t\b\u0002\u0010\u00c5\u0001\u001a\u00020\u00012\b\b\u0002\u0010`\u001a\u00020\u00012\u0016\u0010\u00c6\u0001\u001a\u0011\u0012\u0005\u0012\u00030\u008e\u0001\u0012\u0005\u0012\u0003H\u00c4\u00010\u00c7\u0001H\u0082\b\u00f8\u0001\u0000\u00f8\u0001\u0001¢\u0006\u0006\b\u00c8\u0001\u0010\u00c9\u0001\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0019\u0010\u0004\u001a\u00020\u0005X\u0080\u0004\u00f8\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0006\u0010\u0003\"\u0019\u0010\b\u001a\u00020\u0005X\u0080\u0004\u00f8\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\t\u0010\u0003\"\u0019\u0010\n\u001a\u00020\u0005X\u0080\u0004\u00f8\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u000b\u0010\u0003\"\u0019\u0010\f\u001a\u00020\u0005X\u0080\u0004\u00f8\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\r\u0010\u0003\"\u0019\u0010\u000e\u001a\u00020\u0005X\u0080\u0004\u00f8\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u000f\u0010\u0003\"\u0019\u0010\u0010\u001a\u00020\u0005X\u0080\u0004\u00f8\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0011\u0010\u0003\"\u0014\u0010\u0012\u001a\u00020\u0013X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0019\u0010\u0016\u001a\u00020\u0005X\u0080\u0004\u00f8\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0017\u0010\u0003\"\u0014\u0010\u0018\u001a\u00020\u0013X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0015\"\u0014\u0010\u001a\u001a\u00020\u0001X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0003\"\u0019\u0010\u001c\u001a\u00020\u0005X\u0080\u0004\u00f8\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u001d\u0010\u0003\"\u001b\u0010\u001e\u001a\u00020\u00058\u0000X\u0081\u0004\u00f8\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u001f\u0010\u0003\"\u0019\u0010 \u001a\u00020\u0005X\u0080\u0004\u00f8\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b!\u0010\u0003\"\u0019\u0010\"\u001a\u00020\u0005X\u0080\u0004\u00f8\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b#\u0010\u0003\"\u0019\u0010$\u001a\u00020\u0005X\u0080\u0004\u00f8\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b%\u0010\u0003\"\u0019\u0010&\u001a\u00020\u0005X\u0080\u0004\u00f8\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b'\u0010\u0003\"\u0019\u0010(\u001a\u00020\u0005X\u0080\u0004\u00f8\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b)\u0010\u0003\"\u0019\u0010*\u001a\u00020\u0005X\u0080\u0004\u00f8\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b+\u0010\u0003\"\u0019\u0010,\u001a\u00020\u0005X\u0080\u0004\u00f8\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b-\u0010\u0003\"\u0019\u0010.\u001a\u00020\u0005X\u0080\u0004\u00f8\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b/\u0010\u0003\"\u0019\u00100\u001a\u00020\u0005X\u0080\u0004\u00f8\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b1\u0010\u0003\"\u0014\u00102\u001a\u00020\u0001X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b3\u0010\u0003\"\u0019\u00104\u001a\u00020\u0005X\u0080\u0004\u00f8\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b5\u0010\u0003\"\u0019\u00106\u001a\u00020\u0005X\u0080\u0004\u00f8\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b7\u0010\u0003\"\u0014\u00108\u001a\u00020\u0001X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b9\u0010\u0003\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006\u00ca\u0001" }, d2 = { "GL_CLAMP_TO_EDGE", "", "getGL_CLAMP_TO_EDGE", "()I", "GL_COLOR_ATTACHMENT0", "Lkotlin/UInt;", "getGL_COLOR_ATTACHMENT0", "I", "GL_COMPILE_STATUS", "getGL_COMPILE_STATUS", "GL_FLOAT", "getGL_FLOAT", "GL_FRAGMENT_SHADER", "getGL_FRAGMENT_SHADER", "GL_FRAMEBUFFER", "getGL_FRAMEBUFFER", "GL_FRAMEBUFFER_COMPLETE", "getGL_FRAMEBUFFER_COMPLETE", "GL_LINEAR", "", "getGL_LINEAR", "()F", "GL_LINK_STATUS", "getGL_LINK_STATUS", "GL_NEAREST", "getGL_NEAREST", "GL_NO_ERROR", "getGL_NO_ERROR", "GL_RGBA", "getGL_RGBA", "GL_SHADER_STORAGE_BUFFER", "getGL_SHADER_STORAGE_BUFFER", "GL_TEXTURE0", "getGL_TEXTURE0", "GL_TEXTURE_EXTERNAL_OES", "getGL_TEXTURE_EXTERNAL_OES", "GL_TEXTURE_MAG_FILTER", "getGL_TEXTURE_MAG_FILTER", "GL_TEXTURE_MIN_FILTER", "getGL_TEXTURE_MIN_FILTER", "GL_TEXTURE_WRAP_S", "getGL_TEXTURE_WRAP_S", "GL_TEXTURE_WRAP_T", "getGL_TEXTURE_WRAP_T", "GL_TRIANGLES", "getGL_TRIANGLES", "GL_TRIANGLE_FAN", "getGL_TRIANGLE_FAN", "GL_TRIANGLE_STRIP", "getGL_TRIANGLE_STRIP", "GL_TRUE", "getGL_TRUE", "GL_UNSIGNED_BYTE", "getGL_UNSIGNED_BYTE", "GL_VERTEX_SHADER", "getGL_VERTEX_SHADER", "GL_VIEWPORT", "getGL_VIEWPORT", "glActiveTexture", "", "unit", "glActiveTexture-WZ4Q5Ns", "(I)V", "glAttachShader", "program", "shader", "glAttachShader-feOb9K0", "(II)V", "glBindBuffer", "target", "id", "glBindBuffer-feOb9K0", "glBindBufferBase", "index", "glBindBufferBase-zly0blg", "(III)V", "glBindFramebuffer", "framebuffer", "glBindFramebuffer-feOb9K0", "glBindTexture", "texture", "glBindTexture-feOb9K0", "glBufferData", "size", "usage", "glBufferData-Mv_zs3U", "glCheckFramebufferStatus", "glCheckFramebufferStatus-WZ4Q5Ns", "(I)I", "glCompileShader", "glCompileShader-WZ4Q5Ns", "glCreateProgram", "glCreateShader", "type", "glCreateShader-WZ4Q5Ns", "glDeleteBuffers", "count", "array", "Lkotlin/UIntArray;", "glDeleteBuffers-wZx4R44", "(I[I)V", "glDeleteFramebuffers", "glDeleteFramebuffers-wZx4R44", "glDeleteProgram", "glDeleteProgram-WZ4Q5Ns", "glDeleteShader", "glDeleteShader-WZ4Q5Ns", "glDeleteTextures", "glDeleteTextures-wZx4R44", "glDisableVertexAttribArray", "glDisableVertexAttribArray-WZ4Q5Ns", "glDrawArrays", "mode", "first", "glDrawArrays-OzbTU-A", "glDrawElements", "indices", "Ljava/nio/Buffer;", "glDrawElements-b1QGwmY", "(IIILjava/nio/Buffer;)V", "glEnableVertexAttribArray", "glEnableVertexAttribArray-WZ4Q5Ns", "glFramebufferTexture2D", "attachment", "textureTarget", "level", "glFramebufferTexture2D-guggwrw", "(IIIII)V", "glGenBuffers", "glGenBuffers-wZx4R44", "glGenFramebuffers", "glGenFramebuffers-wZx4R44", "glGenTextures", "glGenTextures-wZx4R44", "glGetAttribLocation", "name", "", "glGetAttribLocation-qim9Vi0", "(ILjava/lang/String;)I", "glGetError", "glGetIntegerv", "parameter", "", "glGetIntegerv-qim9Vi0", "glGetProgramInfoLog", "kotlin.jvm.PlatformType", "glGetProgramInfoLog-WZ4Q5Ns", "(I)Ljava/lang/String;", "glGetProgramiv", "result", "glGetProgramiv-t3GQkyU", "(II[I)V", "glGetShaderInfoLog", "glGetShaderInfoLog-WZ4Q5Ns", "glGetShaderiv", "glGetShaderiv-t3GQkyU", "glGetUniformLocation", "glGetUniformLocation-qim9Vi0", "glLinkProgram", "glLinkProgram-WZ4Q5Ns", "glShaderSource", "source", "glShaderSource-qim9Vi0", "(ILjava/lang/String;)V", "glTexImage2D", "internalFormat", "width", "height", "border", "format", "pixels", "glTexImage2D-IcfoKm0", "(IIIIIIIILjava/nio/Buffer;)V", "glTexParameterf", "value", "glTexParameterf-t3GQkyU", "(IIF)V", "glTexParameteri", "glTexParameteri-t3GQkyU", "glUniform4fv", "location", "Ljava/nio/FloatBuffer;", "Lcom/otaliastudios/opengl/types/FloatBuffer;", "", "glUniformMatrix4fv", "transpose", "", "glUseProgram", "glUseProgram-WZ4Q5Ns", "glVertexAttribPointer", "normalized", "stride", "pointer", "glVertexAttribPointer-GaBhZ9s", "(IIIZILjava/nio/Buffer;)V", "withSignedArray", "T", "pos", "block", "Lkotlin/Function1;", "withSignedArray-p-eMuHY", "([IIILkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "library_release" }, k = 2, mv = { 1, 5, 1 }, xi = 48)
public final class GlKt
{
    private static final int GL_CLAMP_TO_EDGE;
    private static final int GL_COLOR_ATTACHMENT0;
    private static final int GL_COMPILE_STATUS;
    private static final int GL_FLOAT;
    private static final int GL_FRAGMENT_SHADER;
    private static final int GL_FRAMEBUFFER;
    private static final int GL_FRAMEBUFFER_COMPLETE;
    private static final float GL_LINEAR;
    private static final int GL_LINK_STATUS;
    private static final float GL_NEAREST;
    private static final int GL_NO_ERROR = 0;
    private static final int GL_RGBA;
    private static final int GL_SHADER_STORAGE_BUFFER;
    private static final int GL_TEXTURE0;
    private static final int GL_TEXTURE_EXTERNAL_OES;
    private static final int GL_TEXTURE_MAG_FILTER;
    private static final int GL_TEXTURE_MIN_FILTER;
    private static final int GL_TEXTURE_WRAP_S;
    private static final int GL_TEXTURE_WRAP_T;
    private static final int GL_TRIANGLES;
    private static final int GL_TRIANGLE_FAN;
    private static final int GL_TRIANGLE_STRIP;
    private static final int GL_TRUE = 1;
    private static final int GL_UNSIGNED_BYTE;
    private static final int GL_VERTEX_SHADER;
    private static final int GL_VIEWPORT;
    
    static {
        GL_SHADER_STORAGE_BUFFER = UInt.constructor-impl(37074);
        GL_VIEWPORT = 2978;
        GL_UNSIGNED_BYTE = UInt.constructor-impl(5121);
        GL_FLOAT = UInt.constructor-impl(5126);
        GL_RGBA = UInt.constructor-impl(6408);
        GL_TRIANGLES = UInt.constructor-impl(4);
        GL_TRIANGLE_FAN = UInt.constructor-impl(6);
        GL_TRIANGLE_STRIP = UInt.constructor-impl(5);
        GL_TEXTURE0 = UInt.constructor-impl(33984);
        GL_TEXTURE_EXTERNAL_OES = UInt.constructor-impl(36197);
        GL_TEXTURE_MIN_FILTER = UInt.constructor-impl(10241);
        GL_TEXTURE_MAG_FILTER = UInt.constructor-impl(10240);
        GL_TEXTURE_WRAP_S = UInt.constructor-impl(10242);
        GL_TEXTURE_WRAP_T = UInt.constructor-impl(10243);
        GL_CLAMP_TO_EDGE = 33071;
        GL_NEAREST = 9728.0f;
        GL_LINEAR = 9729.0f;
        GL_FRAMEBUFFER = UInt.constructor-impl(36160);
        GL_FRAMEBUFFER_COMPLETE = UInt.constructor-impl(36053);
        GL_COLOR_ATTACHMENT0 = UInt.constructor-impl(36064);
        GL_COMPILE_STATUS = UInt.constructor-impl(35713);
        GL_LINK_STATUS = UInt.constructor-impl(35714);
        GL_VERTEX_SHADER = UInt.constructor-impl(35633);
        GL_FRAGMENT_SHADER = UInt.constructor-impl(35632);
    }
    
    public static final int getGL_CLAMP_TO_EDGE() {
        return GlKt.GL_CLAMP_TO_EDGE;
    }
    
    public static final int getGL_COLOR_ATTACHMENT0() {
        return GlKt.GL_COLOR_ATTACHMENT0;
    }
    
    public static final int getGL_COMPILE_STATUS() {
        return GlKt.GL_COMPILE_STATUS;
    }
    
    public static final int getGL_FLOAT() {
        return GlKt.GL_FLOAT;
    }
    
    public static final int getGL_FRAGMENT_SHADER() {
        return GlKt.GL_FRAGMENT_SHADER;
    }
    
    public static final int getGL_FRAMEBUFFER() {
        return GlKt.GL_FRAMEBUFFER;
    }
    
    public static final int getGL_FRAMEBUFFER_COMPLETE() {
        return GlKt.GL_FRAMEBUFFER_COMPLETE;
    }
    
    public static final float getGL_LINEAR() {
        return GlKt.GL_LINEAR;
    }
    
    public static final int getGL_LINK_STATUS() {
        return GlKt.GL_LINK_STATUS;
    }
    
    public static final float getGL_NEAREST() {
        return GlKt.GL_NEAREST;
    }
    
    public static final int getGL_NO_ERROR() {
        return GlKt.GL_NO_ERROR;
    }
    
    public static final int getGL_RGBA() {
        return GlKt.GL_RGBA;
    }
    
    public static final int getGL_SHADER_STORAGE_BUFFER() {
        return GlKt.GL_SHADER_STORAGE_BUFFER;
    }
    
    public static final int getGL_TEXTURE0() {
        return GlKt.GL_TEXTURE0;
    }
    
    public static final int getGL_TEXTURE_EXTERNAL_OES() {
        return GlKt.GL_TEXTURE_EXTERNAL_OES;
    }
    
    public static final int getGL_TEXTURE_MAG_FILTER() {
        return GlKt.GL_TEXTURE_MAG_FILTER;
    }
    
    public static final int getGL_TEXTURE_MIN_FILTER() {
        return GlKt.GL_TEXTURE_MIN_FILTER;
    }
    
    public static final int getGL_TEXTURE_WRAP_S() {
        return GlKt.GL_TEXTURE_WRAP_S;
    }
    
    public static final int getGL_TEXTURE_WRAP_T() {
        return GlKt.GL_TEXTURE_WRAP_T;
    }
    
    public static final int getGL_TRIANGLES() {
        return GlKt.GL_TRIANGLES;
    }
    
    public static final int getGL_TRIANGLE_FAN() {
        return GlKt.GL_TRIANGLE_FAN;
    }
    
    public static final int getGL_TRIANGLE_STRIP() {
        return GlKt.GL_TRIANGLE_STRIP;
    }
    
    public static final int getGL_TRUE() {
        return GlKt.GL_TRUE;
    }
    
    public static final int getGL_UNSIGNED_BYTE() {
        return GlKt.GL_UNSIGNED_BYTE;
    }
    
    public static final int getGL_VERTEX_SHADER() {
        return GlKt.GL_VERTEX_SHADER;
    }
    
    public static final int getGL_VIEWPORT() {
        return GlKt.GL_VIEWPORT;
    }
    
    public static final void glActiveTexture-WZ4Q5Ns(final int n) {
        GLES20.glActiveTexture(n);
    }
    
    public static final void glAttachShader-feOb9K0(final int n, final int n2) {
        GLES20.glAttachShader(n, n2);
    }
    
    public static final void glBindBuffer-feOb9K0(final int n, final int n2) {
        GLES20.glBindBuffer(n, n2);
    }
    
    public static final void glBindBufferBase-zly0blg(final int n, final int n2, final int n3) {
        GLES30.glBindBufferBase(n, n2, n3);
    }
    
    public static final void glBindFramebuffer-feOb9K0(final int n, final int n2) {
        GLES20.glBindFramebuffer(n, n2);
    }
    
    public static final void glBindTexture-feOb9K0(final int n, final int n2) {
        GLES20.glBindTexture(n, n2);
    }
    
    public static final void glBufferData-Mv_zs3U(final int n, final int n2, final int n3) {
        GLES20.glBufferData(n, n2, (Buffer)null, n3);
    }
    
    public static final int glCheckFramebufferStatus-WZ4Q5Ns(final int n) {
        return UInt.constructor-impl(GLES20.glCheckFramebufferStatus(n));
    }
    
    public static final void glCompileShader-WZ4Q5Ns(final int n) {
        GLES20.glCompileShader(n);
    }
    
    public static final int glCreateProgram() {
        return UInt.constructor-impl(GLES20.glCreateProgram());
    }
    
    public static final int glCreateShader-WZ4Q5Ns(final int n) {
        return UInt.constructor-impl(GLES20.glCreateShader(n));
    }
    
    public static final void glDeleteBuffers-wZx4R44(int n, final int[] array) {
        Intrinsics.checkNotNullParameter((Object)array, "array");
        final int size-impl = UIntArray.getSize-impl(array);
        final int[] array2 = new int[size-impl];
        final int n2 = 0;
        for (int i = 0; i < size-impl; ++i) {
            array2[i] = UIntArray.get-pVg5ArA(array, i);
        }
        GLES20.glDeleteBuffers(n, array2, 0);
        final Unit instance = Unit.INSTANCE;
        final int n3 = n + 0;
        if (n3 > 0) {
            n = n2;
            while (true) {
                final int n4 = n + 1;
                UIntArray.set-VXSXFK8(array, n, UInt.constructor-impl(array2[n]));
                if (n4 >= n3) {
                    break;
                }
                n = n4;
            }
        }
    }
    
    public static final void glDeleteFramebuffers-wZx4R44(int n, final int[] array) {
        Intrinsics.checkNotNullParameter((Object)array, "array");
        final int size-impl = UIntArray.getSize-impl(array);
        final int[] array2 = new int[size-impl];
        final int n2 = 0;
        for (int i = 0; i < size-impl; ++i) {
            array2[i] = UIntArray.get-pVg5ArA(array, i);
        }
        GLES20.glDeleteFramebuffers(n, array2, 0);
        final Unit instance = Unit.INSTANCE;
        final int n3 = n + 0;
        if (n3 > 0) {
            n = n2;
            while (true) {
                final int n4 = n + 1;
                UIntArray.set-VXSXFK8(array, n, UInt.constructor-impl(array2[n]));
                if (n4 >= n3) {
                    break;
                }
                n = n4;
            }
        }
    }
    
    public static final void glDeleteProgram-WZ4Q5Ns(final int n) {
        GLES20.glDeleteProgram(n);
    }
    
    public static final void glDeleteShader-WZ4Q5Ns(final int n) {
        GLES20.glDeleteShader(n);
    }
    
    public static final void glDeleteTextures-wZx4R44(int n, final int[] array) {
        Intrinsics.checkNotNullParameter((Object)array, "array");
        final int size-impl = UIntArray.getSize-impl(array);
        final int[] array2 = new int[size-impl];
        final int n2 = 0;
        for (int i = 0; i < size-impl; ++i) {
            array2[i] = UIntArray.get-pVg5ArA(array, i);
        }
        GLES20.glDeleteTextures(n, array2, 0);
        final Unit instance = Unit.INSTANCE;
        final int n3 = n + 0;
        if (n3 > 0) {
            n = n2;
            while (true) {
                final int n4 = n + 1;
                UIntArray.set-VXSXFK8(array, n, UInt.constructor-impl(array2[n]));
                if (n4 >= n3) {
                    break;
                }
                n = n4;
            }
        }
    }
    
    public static final void glDisableVertexAttribArray-WZ4Q5Ns(final int n) {
        GLES20.glDisableVertexAttribArray(n);
    }
    
    public static final void glDrawArrays-OzbTU-A(final int n, final int n2, final int n3) {
        GLES20.glDrawArrays(n, n2, n3);
    }
    
    public static final void glDrawElements-b1QGwmY(final int n, final int n2, final int n3, final Buffer buffer) {
        Intrinsics.checkNotNullParameter((Object)buffer, "indices");
        GLES20.glDrawElements(n, n2, n3, buffer);
    }
    
    public static final void glEnableVertexAttribArray-WZ4Q5Ns(final int n) {
        GLES20.glEnableVertexAttribArray(n);
    }
    
    public static final void glFramebufferTexture2D-guggwrw(final int n, final int n2, final int n3, final int n4, final int n5) {
        GLES20.glFramebufferTexture2D(n, n2, n3, n4, n5);
    }
    
    public static final void glGenBuffers-wZx4R44(int n, final int[] array) {
        Intrinsics.checkNotNullParameter((Object)array, "array");
        final int size-impl = UIntArray.getSize-impl(array);
        final int[] array2 = new int[size-impl];
        final int n2 = 0;
        for (int i = 0; i < size-impl; ++i) {
            array2[i] = UIntArray.get-pVg5ArA(array, i);
        }
        GLES20.glGenBuffers(n, array2, 0);
        final Unit instance = Unit.INSTANCE;
        final int n3 = n + 0;
        if (n3 > 0) {
            n = n2;
            while (true) {
                final int n4 = n + 1;
                UIntArray.set-VXSXFK8(array, n, UInt.constructor-impl(array2[n]));
                if (n4 >= n3) {
                    break;
                }
                n = n4;
            }
        }
    }
    
    public static final void glGenFramebuffers-wZx4R44(int n, final int[] array) {
        Intrinsics.checkNotNullParameter((Object)array, "array");
        final int size-impl = UIntArray.getSize-impl(array);
        final int[] array2 = new int[size-impl];
        final int n2 = 0;
        for (int i = 0; i < size-impl; ++i) {
            array2[i] = UIntArray.get-pVg5ArA(array, i);
        }
        GLES20.glGenFramebuffers(n, array2, 0);
        final Unit instance = Unit.INSTANCE;
        final int n3 = n + 0;
        if (n3 > 0) {
            n = n2;
            while (true) {
                final int n4 = n + 1;
                UIntArray.set-VXSXFK8(array, n, UInt.constructor-impl(array2[n]));
                if (n4 >= n3) {
                    break;
                }
                n = n4;
            }
        }
    }
    
    public static final void glGenTextures-wZx4R44(int n, final int[] array) {
        Intrinsics.checkNotNullParameter((Object)array, "array");
        final int size-impl = UIntArray.getSize-impl(array);
        final int[] array2 = new int[size-impl];
        final int n2 = 0;
        for (int i = 0; i < size-impl; ++i) {
            array2[i] = UIntArray.get-pVg5ArA(array, i);
        }
        GLES20.glGenTextures(n, array2, 0);
        final Unit instance = Unit.INSTANCE;
        final int n3 = n + 0;
        if (n3 > 0) {
            n = n2;
            while (true) {
                final int n4 = n + 1;
                UIntArray.set-VXSXFK8(array, n, UInt.constructor-impl(array2[n]));
                if (n4 >= n3) {
                    break;
                }
                n = n4;
            }
        }
    }
    
    public static final int glGetAttribLocation-qim9Vi0(final int n, final String s) {
        Intrinsics.checkNotNullParameter((Object)s, "name");
        return GLES20.glGetAttribLocation(n, s);
    }
    
    public static final int glGetError() {
        return UInt.constructor-impl(GLES20.glGetError());
    }
    
    public static final void glGetIntegerv-qim9Vi0(final int n, final int[] array) {
        Intrinsics.checkNotNullParameter((Object)array, "array");
        GLES20.glGetIntegerv(n, array, 0);
    }
    
    public static final String glGetProgramInfoLog-WZ4Q5Ns(final int n) {
        return GLES20.glGetProgramInfoLog(n);
    }
    
    public static final void glGetProgramiv-t3GQkyU(final int n, final int n2, final int[] array) {
        Intrinsics.checkNotNullParameter((Object)array, "result");
        GLES20.glGetProgramiv(n, n2, array, 0);
    }
    
    public static final String glGetShaderInfoLog-WZ4Q5Ns(final int n) {
        return GLES20.glGetShaderInfoLog(n);
    }
    
    public static final void glGetShaderiv-t3GQkyU(final int n, final int n2, final int[] array) {
        Intrinsics.checkNotNullParameter((Object)array, "result");
        GLES20.glGetShaderiv(n, n2, array, 0);
    }
    
    public static final int glGetUniformLocation-qim9Vi0(final int n, final String s) {
        Intrinsics.checkNotNullParameter((Object)s, "name");
        return GLES20.glGetUniformLocation(n, s);
    }
    
    public static final void glLinkProgram-WZ4Q5Ns(final int n) {
        GLES20.glLinkProgram(n);
    }
    
    public static final void glShaderSource-qim9Vi0(final int n, final String s) {
        Intrinsics.checkNotNullParameter((Object)s, "source");
        GLES20.glShaderSource(n, s);
    }
    
    public static final void glTexImage2D-IcfoKm0(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final Buffer buffer) {
        GLES20.glTexImage2D(n, n2, n3, n4, n5, n6, n7, n8, buffer);
    }
    
    public static final void glTexParameterf-t3GQkyU(final int n, final int n2, final float n3) {
        GLES20.glTexParameterf(n, n2, n3);
    }
    
    public static final void glTexParameteri-t3GQkyU(final int n, final int n2, final int n3) {
        GLES20.glTexParameteri(n, n2, n3);
    }
    
    public static final void glUniform4fv(final int n, final int n2, final FloatBuffer floatBuffer) {
        Intrinsics.checkNotNullParameter((Object)floatBuffer, "value");
        GLES20.glUniform4fv(n, n2, floatBuffer);
    }
    
    public static final void glUniform4fv(final int n, final int n2, final float[] array) {
        Intrinsics.checkNotNullParameter((Object)array, "value");
        GLES20.glUniform4fv(n, n2, array, 0);
    }
    
    public static final void glUniformMatrix4fv(final int n, final int n2, final boolean b, final FloatBuffer floatBuffer) {
        Intrinsics.checkNotNullParameter((Object)floatBuffer, "value");
        GLES20.glUniformMatrix4fv(n, n2, b, floatBuffer);
    }
    
    public static final void glUniformMatrix4fv(final int n, final int n2, final boolean b, final float[] array) {
        Intrinsics.checkNotNullParameter((Object)array, "value");
        GLES20.glUniformMatrix4fv(n, n2, b, array, 0);
    }
    
    public static final void glUseProgram-WZ4Q5Ns(final int n) {
        GLES20.glUseProgram(n);
    }
    
    public static final void glVertexAttribPointer-GaBhZ9s(final int n, final int n2, final int n3, final boolean b, final int n4, final Buffer buffer) {
        Intrinsics.checkNotNullParameter((Object)buffer, "pointer");
        GLES20.glVertexAttribPointer(n, n2, n3, b, n4, buffer);
    }
    
    private static final <T> T withSignedArray-p-eMuHY(final int[] array, int n, int n2, final Function1<? super int[], ? extends T> function1) {
        final int size-impl = UIntArray.getSize-impl(array);
        final int[] array2 = new int[size-impl];
        for (int i = 0; i < size-impl; ++i) {
            array2[i] = UIntArray.get-pVg5ArA(array, i);
        }
        final Object invoke = function1.invoke((Object)array2);
        final int n3 = n2 + n;
        if (n < n3) {
            while (true) {
                n2 = n + 1;
                UIntArray.set-VXSXFK8(array, n, UInt.constructor-impl(array2[n]));
                if (n2 >= n3) {
                    break;
                }
                n = n2;
            }
        }
        return (T)invoke;
    }
}
