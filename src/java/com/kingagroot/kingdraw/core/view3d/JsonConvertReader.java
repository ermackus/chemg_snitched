package com.kingagroot.kingdraw.core.view3d;

import java.io.Reader;
import java.io.StringReader;
import com.kingagroot.kingdraw.core.view3d.element.KdProperty;
import com.kingagroot.kingdraw.core.view3d.bean.KdPoint;
import com.kingagroot.kingdraw.core.view3d.datas.PeriodicTable;
import java.io.IOException;
import com.kingagroot.kingdraw.core.view3d.element.KdChemElement;
import android.util.JsonReader;
import java.util.Collection;
import com.kingagroot.kingdraw.core.view3d.element.KdElement;
import java.util.Iterator;
import java.util.ArrayList;
import com.kingagroot.kingdraw.core.view3d.element.KdNode;
import com.kingagroot.kingdraw.core.view3d.element.KdBond;
import java.util.List;

public class JsonConvertReader
{
    List<KdBond> bonds;
    List<KdNode> nodes;
    
    public JsonConvertReader() {
        this.nodes = (List<KdNode>)new ArrayList();
        this.bonds = (List<KdBond>)new ArrayList();
    }
    
    private KdNode findNodeByIndex(final int n) {
        for (final KdNode kdNode : this.nodes) {
            if (kdNode.index == n) {
                return kdNode;
            }
        }
        return null;
    }
    
    private List<KdElement> getReadElements() {
        final ArrayList list = new ArrayList();
        ((List)list).addAll((Collection)this.nodes);
        ((List)list).addAll((Collection)this.bonds);
        return (List<KdElement>)list;
    }
    
    private KdBond readBond(final JsonReader jsonReader) throws IOException {
        final KdBond kdBond = new KdBond();
        jsonReader.beginObject();
        KdNode nodeByIndex = null;
        KdNode nodeByIndex2 = null;
        while (jsonReader.hasNext()) {
            final String nextName = jsonReader.nextName();
            if (nextName.equals((Object)"index")) {
                kdBond.index = jsonReader.nextInt();
            }
            else if (nextName.equals((Object)"colorHex")) {
                kdBond.colorHex = jsonReader.nextString();
            }
            else if (nextName.equals((Object)"type")) {
                kdBond.type = jsonReader.nextInt();
            }
            else if (nextName.equals((Object)"startNode")) {
                nodeByIndex = this.findNodeByIndex(jsonReader.nextInt());
            }
            else if (nextName.equals((Object)"endNode")) {
                nodeByIndex2 = this.findNodeByIndex(jsonReader.nextInt());
            }
            else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        if (nodeByIndex != null && nodeByIndex2 != null) {
            kdBond.addRelate((KdChemElement)nodeByIndex);
            kdBond.addRelate((KdChemElement)nodeByIndex2);
            nodeByIndex.addRelate((KdChemElement)kdBond);
            nodeByIndex2.addRelate((KdChemElement)kdBond);
            return kdBond;
        }
        return null;
    }
    
    private List<KdBond> readBonds(final JsonReader jsonReader) throws IOException {
        final ArrayList list = new ArrayList();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            final KdBond bond = this.readBond(jsonReader);
            if (bond != null) {
                ((List)list).add((Object)bond);
            }
        }
        jsonReader.endArray();
        return (List<KdBond>)list;
    }
    
    private KdNode readNode(final JsonReader jsonReader) throws IOException {
        final KdNode kdNode = new KdNode();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            final String nextName = jsonReader.nextName();
            if (nextName.equals((Object)"index")) {
                kdNode.index = jsonReader.nextInt();
            }
            else if (nextName.equals((Object)"colorHex")) {
                kdNode.colorHex = jsonReader.nextString();
            }
            else if (nextName.equals((Object)"type")) {
                final String nextString = jsonReader.nextString();
                if (nextString.equals((Object)"R")) {
                    kdNode.type = nextString;
                }
                else if (PeriodicTable.getInstance().findAtomByName(nextString) == null) {
                    kdNode.type = "Undefined";
                }
                else {
                    kdNode.type = nextString;
                }
            }
            else if (nextName.equals((Object)"label")) {
                kdNode.label = jsonReader.nextString();
            }
            else if (nextName.equals((Object)"hCount")) {
                kdNode.hCount = jsonReader.nextInt();
            }
            else if (nextName.equals((Object)"point")) {
                kdNode.setPoint(this.readPoint(jsonReader));
            }
            else if (nextName.equals((Object)"propertys")) {
                kdNode.addPropertys((List)this.readPropertys(jsonReader));
            }
            else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return kdNode;
    }
    
    private List<KdNode> readNodes(final JsonReader jsonReader) throws IOException {
        final ArrayList list = new ArrayList();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            final KdNode node = this.readNode(jsonReader);
            if (node != null) {
                ((List)list).add((Object)node);
            }
        }
        jsonReader.endArray();
        return (List<KdNode>)list;
    }
    
    private KdPoint readPoint(final JsonReader jsonReader) throws IOException {
        final KdPoint kdPoint = new KdPoint();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            final String nextName = jsonReader.nextName();
            if (nextName.equals((Object)"x")) {
                kdPoint.x = (float)jsonReader.nextDouble();
            }
            else if (nextName.equals((Object)"y")) {
                kdPoint.y = (float)jsonReader.nextDouble();
            }
            else {
                if (!nextName.equals((Object)"z")) {
                    continue;
                }
                kdPoint.z = (float)jsonReader.nextDouble();
            }
        }
        jsonReader.endObject();
        return kdPoint;
    }
    
    private KdProperty readProperty(final JsonReader jsonReader) throws IOException {
        final KdProperty kdProperty = new KdProperty();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            final String nextName = jsonReader.nextName();
            if (nextName.equals((Object)"index")) {
                kdProperty.index = jsonReader.nextInt();
            }
            else if (nextName.equals((Object)"type")) {
                kdProperty.type = jsonReader.nextInt();
            }
            else if (nextName.equals((Object)"chg")) {
                kdProperty.setChg(jsonReader.nextInt());
            }
            else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return kdProperty;
    }
    
    private List<KdProperty> readPropertys(final JsonReader jsonReader) throws IOException {
        final ArrayList list = new ArrayList();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            final KdProperty property = this.readProperty(jsonReader);
            if (property != null) {
                ((List)list).add((Object)property);
            }
        }
        jsonReader.endArray();
        return (List<KdProperty>)list;
    }
    
    public void clear() {
        this.nodes.clear();
        this.bonds.clear();
    }
    
    public List<KdElement> read(final String s) {
        this.clear();
        final JsonReader jsonReader = new JsonReader((Reader)new StringReader(s));
        try {
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                final String nextName = jsonReader.nextName();
                if (nextName.equals((Object)"nodes")) {
                    final List<KdNode> nodes = this.readNodes(jsonReader);
                    if (nodes == null) {
                        continue;
                    }
                    this.nodes.addAll((Collection)nodes);
                }
                else if (nextName.equals((Object)"bonds")) {
                    final List<KdBond> bonds = this.readBonds(jsonReader);
                    if (bonds == null) {
                        continue;
                    }
                    this.bonds.addAll((Collection)bonds);
                }
                else {
                    jsonReader.skipValue();
                }
            }
            jsonReader.endObject();
        }
        catch (final IOException ex) {
            ex.printStackTrace();
        }
        return this.getReadElements();
    }
}
