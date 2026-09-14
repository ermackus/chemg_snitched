package com.goodsrc.ui.library.widget;

import java.io.PrintStream;

public class CharacterParser
{
    private static final CharacterParser characterParser;
    public static String[] pystr;
    private static final int[] pyvalue;
    private StringBuilder buffer;
    private String resource;
    
    static {
        pyvalue = $d2j$hex$20ed1658$decode_I("a1b0ffffa3b0ffffb0b0ffffb9b0ffffbcb0ffffc5b0ffffd7b0ffffdfb0ffffeeb0fffffab0ffffadb1ffffbcb1ffffc0b1ffffc6b1ffffdeb1ffffeab1ffffeeb1fffff2b1fffff8b1ffffa3b2ffffb8b2ffffc1b2ffffc2b2ffffcdb2ffffd4b2ffffd9b2ffffdeb2ffffe3b2ffffe5b2fffff0b2fffff3b2fffffdb2ffffacb3ffffb5b3ffffbbb3ffffc5b3ffffd4b3ffffe4b3ffffe9b3fffff5b3ffffa7b4ffffa8b4ffffafb4ffffb5b4ffffbab4ffffc1b4ffffc3b4ffffcfb4ffffd5b4ffffd6b4ffffdab4ffffddb4ffffe5b4ffffe8b4ffffeeb4fffff4b4ffffa2b5ffffb1b5ffffb6b5ffffc2b5ffffc5b5ffffccb5ffffdfb5ffffefb5fffff8b5ffffa1b6ffffaab6ffffabb6ffffb5b6ffffbcb6ffffcbb6ffffd1b6ffffd5b6ffffdeb6ffffeab6fffff7b6fffff8b6ffffa2b7ffffaab7ffffbbb7ffffc6b7ffffd2b7ffffe1b7fffff0b7fffff1b7fffff2b7ffffc1b8ffffc3b8ffffc9b8ffffd4b8ffffddb8ffffe7b8fffff8b8fffff9b8fffffbb8ffffa4b9ffffb3b9ffffbcb9ffffceb9ffffd4b9ffffd7b9ffffe2b9ffffe5b9fffff5b9fffff8b9fffffeb9ffffa1baffffa8baffffbbbaffffbebaffffc7baffffd9baffffdbbaffffdfbaffffe4baffffedbafffff4baffffa8bbffffb1bbffffb6bbffffc4bbffffd2bbffffe7bbffffedbbfffff7bbffffcebcffffdfbcffffa9bdffffb6bdffffd2bdffffedbdffffa3beffffbcbeffffbebeffffcfbeffffe8beffffefbefffff9beffffa6bfffffaabfffffafbfffffb5bfffffbcbfffffc0bfffffcfbfffffd3bfffffd5bfffffd9bfffffddbfffffe4bfffffe9bfffffedbfffffefbffffff7bfffffa4c0ffffa8c0ffffacc0ffffb3c0ffffb6c0ffffc5c0ffffccc0ffffd5c0ffffd7c0ffffe2c0ffffe5c0ffffa9c1ffffaac1ffffb8c1ffffc3c1ffffd0c1ffffd5c1ffffe1c1ffffefc1fffffac1ffffa5c2ffffabc2ffffbfc2ffffcdc2ffffd3c2ffffd5c2ffffdcc2ffffe8c2fffff1c2fffff7c2ffffa2c3ffffa8c3ffffb4c3ffffb5c3ffffc5c3ffffc8c3ffffd0c3ffffdec3ffffe7c3ffffefc3fffff1c3fffff7c3fffffdc3fffffec3ffffb1c4ffffb4c4ffffc3c4ffffcac4ffffcfc4ffffd2c4ffffd3c4ffffd8c4ffffd9c4ffffdbc4ffffdcc4ffffddc4ffffe8c4ffffefc4fffff1c4fffff3c4fffffac4fffffbc4ffffa3c5ffffa7c5ffffabc5ffffaec5ffffafc5ffffb0c5ffffb2c5ffffb6c5ffffb7c5ffffbec5ffffc4c5ffffcac5ffffd2c5ffffd7c5ffffdec5ffffe7c5ffffe9c5fffff7c5ffffaac6ffffaec6ffffb2c6ffffb4c6ffffb9c6ffffc2c6ffffcbc6ffffdac6fffffec6ffffa3c7ffffb9c7ffffc1c7ffffd0c7ffffd5c7ffffe0c7ffffedc7ffffefc7fffff7c7ffffa6c8ffffb1c8ffffb9c8ffffbbc8ffffbfc8ffffc4c8ffffc7c8ffffc9c8ffffd3c8ffffd5c8ffffd6c8ffffe0c8ffffe3c8ffffedc8ffffefc8fffff2c8fffff4c8fffff6c8fffff9c8fffffdc8ffffa3c9ffffa6c9ffffaac9ffffadc9ffffaec9ffffafc9ffffb8c9ffffbac9ffffcac9ffffd2c9ffffddc9ffffe9c9fffff9c9ffffa6caffffd5caffffdfcaffffa2cbffffa4cbffffa8cbffffaacbffffadcbffffb1cbffffb5cbffffb9cbffffc9cbffffd1cbffffd4cbffffe1cbffffe4cbffffefcbfffff2cbfffffacbffffa5ccffffaeccffffc0ccffffcdccffffd8ccffffd9ccffffddccffffecccfffff4ccfffff9ccfffffcccffffa8cdffffb5cdffffb9cdffffc4cdffffc6cdffffcccdffffcfcdffffdacdffffe1cdffffe3cdfffff4cdfffffecdffffc1ceffffcbceffffceceffffd7cefffff4ceffffb9cfffffc6cfffffe0cffffff4cfffffa8d0ffffbdd0ffffc7d0ffffd6d0ffffddd0ffffe6d0fffff9d0ffffa5d1ffffabd1ffffb9d1ffffc9d1ffffead1fffffbd1ffffacd2ffffbbd2fffff0d2ffffa2d3ffffb4d3ffffb5d3ffffc4d3ffffd9d3ffffa7d4ffffbbd4ffffc5d4ffffd1d4ffffd4d4ffffdbd4ffffdfd4ffffe2d4fffff0d4fffff4d4fffff5d4fffff6d4fffffad4ffffaad5ffffb0d5ffffc1d5ffffd0d5ffffdad5ffffe4d5fffff4d5ffffa5d6ffffd0d6ffffdbd6ffffe9d6ffffa5d7ffffa7d7ffffa8d7ffffaed7ffffb5d7ffffbbd7ffffbdd7ffffc8d7ffffd7d7ffffded7ffffe2d7ffffead7ffffecd7fffff0d7fffff2d7ffff");
        CharacterParser.pystr = new String[] { "a", "ai", "an", "ang", "ao", "ba", "bai", "ban", "bang", "bao", "bei", "ben", "beng", "bi", "bian", "biao", "bie", "bin", "bing", "bo", "bu", "ca", "cai", "can", "cang", "cao", "ce", "ceng", "cha", "chai", "chan", "chang", "chao", "che", "chen", "cheng", "chi", "chong", "chou", "chu", "chuai", "chuan", "chuang", "chui", "chun", "chuo", "ci", "cong", "cou", "cu", "cuan", "cui", "cun", "cuo", "da", "dai", "dan", "dang", "dao", "de", "deng", "di", "dian", "diao", "die", "ding", "diu", "dong", "dou", "du", "duan", "dui", "dun", "duo", "e", "en", "er", "fa", "fan", "fang", "fei", "fen", "feng", "fo", "fou", "fu", "ga", "gai", "gan", "gang", "gao", "ge", "gei", "gen", "geng", "gong", "gou", "gu", "gua", "guai", "guan", "guang", "gui", "gun", "guo", "ha", "hai", "han", "hang", "hao", "he", "hei", "hen", "heng", "hong", "hou", "hu", "hua", "huai", "huan", "huang", "hui", "hun", "huo", "ji", "jia", "jian", "jiang", "jiao", "jie", "jin", "jing", "jiong", "jiu", "ju", "juan", "jue", "jun", "ka", "kai", "kan", "kang", "kao", "ke", "ken", "keng", "kong", "kou", "ku", "kua", "kuai", "kuan", "kuang", "kui", "kun", "kuo", "la", "lai", "lan", "lang", "lao", "le", "lei", "leng", "li", "lia", "lian", "liang", "liao", "lie", "lin", "ling", "liu", "long", "lou", "lu", "lv", "luan", "lue", "lun", "luo", "ma", "mai", "man", "mang", "mao", "me", "mei", "men", "meng", "mi", "mian", "miao", "mie", "min", "ming", "miu", "mo", "mou", "mu", "na", "nai", "nan", "nang", "nao", "ne", "nei", "nen", "neng", "ni", "nian", "niang", "niao", "nie", "nin", "ning", "niu", "nong", "nu", "nv", "nuan", "nue", "nuo", "o", "ou", "pa", "pai", "pan", "pang", "pao", "pei", "pen", "peng", "pi", "pian", "piao", "pie", "pin", "ping", "po", "pu", "qi", "qia", "qian", "qiang", "qiao", "qie", "qin", "qing", "qiong", "qiu", "qu", "quan", "que", "qun", "ran", "rang", "rao", "re", "ren", "reng", "ri", "rong", "rou", "ru", "ruan", "rui", "run", "ruo", "sa", "sai", "san", "sang", "sao", "se", "sen", "seng", "sha", "shai", "shan", "shang", "shao", "she", "shen", "sheng", "shi", "shou", "shu", "shua", "shuai", "shuan", "shuang", "shui", "shun", "shuo", "si", "song", "sou", "su", "suan", "sui", "sun", "suo", "ta", "tai", "tan", "tang", "tao", "te", "teng", "ti", "tian", "tiao", "tie", "ting", "tong", "tou", "tu", "tuan", "tui", "tun", "tuo", "wa", "wai", "wan", "wang", "wei", "wen", "weng", "wo", "wu", "xi", "xia", "xian", "xiang", "xiao", "xie", "xin", "xing", "xiong", "xiu", "xu", "xuan", "xue", "xun", "ya", "yan", "yang", "yao", "ye", "yi", "yin", "ying", "yo", "yong", "you", "yu", "yuan", "yue", "yun", "za", "zai", "zan", "zang", "zao", "ze", "zei", "zen", "zeng", "zha", "zhai", "zhan", "zhang", "zhao", "zhe", "zhen", "zheng", "zhi", "zhong", "zhou", "zhu", "zhua", "zhuai", "zhuan", "zhuang", "zhui", "zhun", "zhuo", "zi", "zong", "zou", "zu", "zuan", "zui", "zun", "zuo" };
        characterParser = new CharacterParser();
    }
    
    private int getChsAscii(String bytes) {
        int n = 0;
        Label_0097: {
            try {
                bytes = (Exception)(Object)((String)bytes).getBytes("gb2312");
                if (bytes != null && bytes.length <= 2 && bytes.length > 0) {
                    if (bytes.length == 1) {
                        n = bytes[0];
                    }
                    else {
                        n = 0;
                    }
                    int n2 = n;
                    try {
                        if (bytes.length == 2) {
                            n = bytes[0];
                            n2 = (n + 256) * 256 + (bytes[1] + 256) - 65536;
                            return n2;
                        }
                        return n2;
                    }
                    catch (final Exception bytes) {
                        break Label_0097;
                    }
                }
                bytes = (Exception)new RuntimeException("illegal resource string");
                throw bytes;
            }
            catch (final Exception ex) {}
        }
        final PrintStream out = System.out;
        final StringBuilder sb = new StringBuilder();
        sb.append("ERROR:ChineseSpelling.class-getChsAscii(String chs)");
        sb.append((Object)bytes);
        out.println(sb.toString());
        return n;
    }
    
    public static CharacterParser getInstance() {
        return CharacterParser.characterParser;
    }
    
    public String convert(String value) {
        final int chsAscii = this.getChsAscii(value);
        if (chsAscii > 0 && chsAscii < 160) {
            value = String.valueOf((char)chsAscii);
        }
        else {
            for (int i = CharacterParser.pyvalue.length - 1; i >= 0; --i) {
                if (CharacterParser.pyvalue[i] <= chsAscii) {
                    value = CharacterParser.pystr[i];
                    return value;
                }
            }
            value = null;
        }
        return value;
    }
    
    public String getResource() {
        return this.resource;
    }
    
    public String getSelling(final String s) {
        this.buffer = new StringBuilder();
        int n;
        for (int i = 0; i < s.length(); i = n) {
            n = i + 1;
            String s3;
            final String s2 = s3 = s.substring(i, n);
            if (s2.getBytes().length >= 2 && (s3 = this.convert(s2)) == null) {
                s3 = "unknown";
            }
            this.buffer.append(s3);
        }
        return this.buffer.toString();
    }
    
    public String getSpelling() {
        return this.getSelling(this.getResource());
    }
    
    public void setResource(final String resource) {
        this.resource = resource;
    }
}
