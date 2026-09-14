package com.kingagroot.kingdraw.core.view3d;

import java.io.Writer;
import java.io.StringWriter;
import com.kingagroot.kingdraw.core.view3d.element.KdElement;
import java.util.ArrayList;
import com.kingagroot.kingdraw.core.view3d.element.KdFragment;
import com.kingagroot.kingdraw.core.view3d.bean.KdPoint;
import com.kingagroot.kingdraw.core.view3d.element.KdProperty;
import java.util.Iterator;
import java.util.List;
import java.io.IOException;
import com.kingagroot.kingdraw.core.view3d.element.KdNode;
import com.kingagroot.kingdraw.core.view3d.element.KdBond;
import android.util.JsonWriter;

public class JsonConvertWriter
{
    JsonWriter writer;
    
    private void writeBond(final KdBond kdBond) throws IOException {
        final KdNode startNode = kdBond.getStartNode();
        final KdNode endNode = kdBond.getEndNode();
        if (startNode != null && endNode != null) {
            this.writer.beginObject();
            this.writer.name("index");
            this.writer.value((long)kdBond.index);
            this.writer.name("layerIndex");
            this.writer.value((long)kdBond.layerIndex);
            this.writer.name("colorHex");
            this.writer.value(kdBond.colorHex);
            this.writer.name("type");
            this.writer.value((long)kdBond.type);
            this.writer.name("align");
            this.writer.value((long)kdBond.align);
            this.writer.name("startNode");
            this.writer.value((long)startNode.index);
            this.writer.name("endNode");
            this.writer.value((long)endNode.index);
            this.writer.endObject();
        }
    }
    
    private void writeBonds(final List<KdBond> list) throws IOException {
        this.writer.beginArray();
        final Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            this.writeBond((KdBond)iterator.next());
        }
        this.writer.endArray();
    }
    
    private void writeNode(final KdNode kdNode) throws IOException {
        this.writer.beginObject();
        this.writer.name("index");
        this.writer.value((long)kdNode.index);
        this.writer.name("layerIndex");
        this.writer.value((long)kdNode.layerIndex);
        this.writer.name("colorHex");
        this.writer.value(kdNode.colorHex);
        this.writer.name("type");
        this.writer.value(kdNode.type);
        this.writer.name("label");
        this.writer.value(kdNode.label);
        this.writer.name("hCount");
        this.writer.value((long)kdNode.hCount);
        this.writer.name("html");
        this.writer.value(kdNode.html);
        this.writer.name("point");
        this.writePoint(kdNode.getPoint());
        this.writer.name("propertys");
        this.writeProperty((List<KdProperty>)kdNode.getPropertys());
        this.writer.endObject();
    }
    
    private void writeNodes(final List<KdNode> list) throws IOException {
        this.writer.beginArray();
        final Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            this.writeNode((KdNode)iterator.next());
        }
        this.writer.endArray();
    }
    
    private void writePoint(final KdPoint kdPoint) throws IOException {
        this.writer.beginObject();
        this.writer.name("x");
        this.writer.value((double)kdPoint.x);
        this.writer.name("y");
        this.writer.value((double)kdPoint.y);
        this.writer.name("z");
        this.writer.value((double)kdPoint.z);
        this.writer.endObject();
    }
    
    private void writeProperty(final KdProperty kdProperty) throws IOException {
        this.writer.beginObject();
        this.writer.name("index");
        this.writer.value((long)kdProperty.index);
        this.writer.name("aIndex");
        this.writer.value((long)kdProperty.aIndex);
        this.writer.name("type");
        this.writer.value((long)kdProperty.type);
        this.writer.name("angle");
        this.writer.value((double)kdProperty.angle);
        this.writer.name("chg");
        this.writer.value((long)kdProperty.getChg());
        this.writer.name("point");
        this.writePoint(kdProperty.getPoint());
        this.writer.endObject();
    }
    
    private void writeProperty(final List<KdProperty> list) throws IOException {
        this.writer.beginArray();
        final Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            this.writeProperty((KdProperty)iterator.next());
        }
        this.writer.endArray();
    }
    
    public String write(final KdFragment kdFragment) {
        final List childElemnts = kdFragment.getChildElemnts();
        final ArrayList list = new ArrayList();
        final ArrayList list2 = new ArrayList();
        for (final KdElement kdElement : childElemnts) {
            if (kdElement instanceof KdNode) {
                final KdNode kdNode = (KdNode)kdElement;
                kdNode.index = ((List)list).size() + 1;
                ((List)list).add((Object)kdNode);
            }
            else {
                if (!(kdElement instanceof KdBond)) {
                    continue;
                }
                final KdBond kdBond = (KdBond)kdElement;
                kdBond.index = ((List)list).size() + 1;
                ((List)list2).add((Object)kdBond);
            }
        }
        final StringWriter stringWriter = new StringWriter();
        final JsonWriter writer = new JsonWriter((Writer)stringWriter);
        this.writer = writer;
        try {
            writer.beginObject();
            this.writer.name("type");
            this.writer.value(1L);
            this.writer.name("nodes");
            this.writeNodes((List<KdNode>)list);
            this.writer.name("bonds");
            this.writeBonds((List<KdBond>)list2);
            this.writer.endObject();
        }
        catch (final IOException ex) {
            ex.printStackTrace();
        }
        return stringWriter.toString();
    }
}
