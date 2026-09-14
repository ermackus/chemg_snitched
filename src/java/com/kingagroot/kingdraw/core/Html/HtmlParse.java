package com.kingagroot.kingdraw.core.Html;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Stack;

class HtmlParse
{
    private ParseStack CurrentParse;
    private final int STATUS_READ_ATTRS;
    private final int STATUS_READ_CONTENT;
    private final int STATUS_READ_END;
    private final int STATUS_READ_NAME;
    private final int STATUS_READ_START;
    private Stack<ParseStack> TagStack;
    int status;
    
    HtmlParse() {
        this.status = -1;
        this.STATUS_READ_START = -1;
        this.STATUS_READ_NAME = 1;
        this.STATUS_READ_ATTRS = 2;
        this.STATUS_READ_CONTENT = 3;
        this.STATUS_READ_END = 4;
        this.TagStack = (Stack<ParseStack>)new Stack();
    }
    
    private HashMap<String, String> parseAttr(String substring) {
        final HashMap hashMap = new HashMap();
        final String[] split = substring.split("\" ");
        for (int length = split.length, i = 0; i < length; ++i) {
            final String[] split2 = split[i].split("=");
            if (split2.length == 2) {
                final String s = split2[0];
                final String s2 = substring = split2[1];
                if (s2.startsWith("\"")) {
                    substring = s2.substring(1, s2.length());
                }
                String substring2 = substring;
                if (substring.endsWith("\"")) {
                    substring2 = substring.substring(0, substring.length() - 1);
                }
                hashMap.put((Object)s, (Object)substring2);
            }
        }
        return (HashMap<String, String>)hashMap;
    }
    
    public List<HtmlTag> parse(final String s) {
        final ArrayList list = new ArrayList();
        int n2;
        for (int length = s.length(), i = 0; i < length; i = n2 + 1) {
            final char char1 = s.charAt(i);
            final int n = i + 1;
            char char2;
            if (n < length) {
                char2 = s.charAt(n);
            }
            else {
                char2 = '\0';
            }
            final int status = this.status;
            if (status != -1) {
                if (status != 1) {
                    if (status != 2) {
                        if (status != 3) {
                            if (status != 4) {
                                n2 = i;
                                continue;
                            }
                            n2 = i;
                            if (char1 != '>') {
                                continue;
                            }
                            if (!this.TagStack.empty()) {
                                final ParseStack currentParse = (ParseStack)this.TagStack.pop();
                                this.CurrentParse = currentParse;
                                this.status = currentParse.status;
                                n2 = i;
                                continue;
                            }
                            ((List)list).add((Object)this.CurrentParse.htmlTag);
                            this.status = -1;
                            n2 = i;
                            continue;
                        }
                        else if (char1 == '<' && char2 == '/') {
                            this.status = 4;
                            this.CurrentParse.readContent = "";
                            if (this.CurrentParse.htmlTag.name.equals((Object)"text") && !this.TagStack.empty()) {
                                this.CurrentParse = (ParseStack)this.TagStack.pop();
                            }
                        }
                        else {
                            if (char1 == '<') {
                                final HtmlTag htmlTag = new HtmlTag();
                                if (this.CurrentParse.htmlTag.name.equals((Object)"text")) {
                                    this.CurrentParse.status = this.status;
                                    this.TagStack.push((Object)this.CurrentParse);
                                }
                                else {
                                    this.CurrentParse = (ParseStack)this.TagStack.peek();
                                }
                                this.CurrentParse.htmlTag.addChild(htmlTag);
                                final ParseStack currentParse2 = new ParseStack();
                                this.CurrentParse = currentParse2;
                                currentParse2.htmlTag = htmlTag;
                                this.status = 1;
                                n2 = i;
                                continue;
                            }
                            if (this.CurrentParse.htmlTag.name.equals((Object)"text")) {
                                final StringBuilder sb = new StringBuilder();
                                final HtmlTag htmlTag2 = this.CurrentParse.htmlTag;
                                sb.append(htmlTag2.text);
                                sb.append(char1);
                                htmlTag2.text = sb.toString();
                                n2 = i;
                                continue;
                            }
                            final HtmlTag htmlTag3 = new HtmlTag();
                            htmlTag3.name = "text";
                            final StringBuilder sb2 = new StringBuilder();
                            sb2.append(htmlTag3.text);
                            sb2.append(char1);
                            htmlTag3.text = sb2.toString();
                            this.CurrentParse.status = this.status;
                            this.CurrentParse.htmlTag.addChild(htmlTag3);
                            this.TagStack.push((Object)this.CurrentParse);
                            final ParseStack currentParse3 = new ParseStack();
                            this.CurrentParse = currentParse3;
                            currentParse3.htmlTag = htmlTag3;
                            n2 = i;
                            continue;
                        }
                    }
                    else {
                        if (char1 == '>') {
                            this.status = 3;
                            this.CurrentParse.htmlTag.setAttr(this.parseAttr(this.CurrentParse.readContent));
                            this.CurrentParse.readContent = "";
                            n2 = i;
                            continue;
                        }
                        final StringBuilder sb3 = new StringBuilder();
                        final ParseStack currentParse4 = this.CurrentParse;
                        sb3.append(currentParse4.readContent);
                        sb3.append(char1);
                        currentParse4.readContent = sb3.toString();
                        n2 = i;
                        continue;
                    }
                }
                else {
                    if (char1 == ' ') {
                        this.status = 2;
                        this.CurrentParse.htmlTag.name = this.CurrentParse.readContent;
                        this.CurrentParse.readContent = "";
                        n2 = i;
                        continue;
                    }
                    if (char1 == '/' && char2 == '>') {
                        this.CurrentParse.htmlTag.name = this.CurrentParse.readContent;
                        this.CurrentParse.readContent = "";
                        if (!this.TagStack.empty()) {
                            final ParseStack currentParse5 = (ParseStack)this.TagStack.pop();
                            this.CurrentParse = currentParse5;
                            this.status = currentParse5.status;
                        }
                        else {
                            ((List)list).add((Object)this.CurrentParse.htmlTag);
                            this.status = -1;
                        }
                    }
                    else {
                        if (char1 == '>') {
                            this.CurrentParse.htmlTag.name = this.CurrentParse.readContent;
                            this.CurrentParse.readContent = "";
                            this.status = 3;
                            n2 = i;
                            continue;
                        }
                        final StringBuilder sb4 = new StringBuilder();
                        final ParseStack currentParse6 = this.CurrentParse;
                        sb4.append(currentParse6.readContent);
                        sb4.append(char1);
                        currentParse6.readContent = sb4.toString();
                        n2 = i;
                        continue;
                    }
                }
                n2 = n;
            }
            else {
                n2 = i;
                if (char1 == '<') {
                    this.CurrentParse = new ParseStack();
                    this.CurrentParse.htmlTag = new HtmlTag();
                    this.status = 1;
                    n2 = i;
                }
            }
        }
        return (List<HtmlTag>)list;
    }
    
    class ParseStack
    {
        public HtmlTag htmlTag;
        public String readContent;
        public int status;
        final HtmlParse this$0;
        
        ParseStack(final HtmlParse this$0) {
            this.this$0 = this$0;
            this.readContent = "";
        }
    }
}
