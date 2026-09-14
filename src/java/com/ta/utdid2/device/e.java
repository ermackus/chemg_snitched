package com.ta.utdid2.device;

import com.ta.utdid2.b.a.i;
import com.ta.utdid2.b.a.a;
import com.ta.utdid2.b.a.b;

public class e
{
    private String p;
    
    public e() {
        this.p = "XwYp8WL8bm6S4wu6yEYmLGy4RRRdJDIhxCBdk3CiNZTwGoj1bScVZEeVp9vBiiIsgwDtqZHP8QLoFM6o6MRYjW8QqyrZBI654mqoUk5SOLDyzordzOU5QhYguEJh54q3K1KqMEXpdEQJJjs1Urqjm2s4jgPfCZ4hMuIjAMRrEQluA7FeoqWMJOwghcLcPVleQ8PLzAcaKidybmwhvNAxIyKRpbZlcDjNCcUvsJYvyzEA9VUIaHkIAJ62lpA3EE3H";
        this.p = b.encodeToString("XwYp8WL8bm6S4wu6yEYmLGy4RRRdJDIhxCBdk3CiNZTwGoj1bScVZEeVp9vBiiIsgwDtqZHP8QLoFM6o6MRYjW8QqyrZBI654mqoUk5SOLDyzordzOU5QhYguEJh54q3K1KqMEXpdEQJJjs1Urqjm2s4jgPfCZ4hMuIjAMRrEQluA7FeoqWMJOwghcLcPVleQ8PLzAcaKidybmwhvNAxIyKRpbZlcDjNCcUvsJYvyzEA9VUIaHkIAJ62lpA3EE3H".getBytes(), 0);
    }
    
    public String b(final String s) {
        return a.e(this.p, s);
    }
    
    public String c(String e) {
        e = a.e(this.p, e);
        Label_0031: {
            if (i.a(e)) {
                break Label_0031;
            }
            try {
                e = new String(b.decode(e, 0));
                return e;
                return null;
            }
            catch (final IllegalArgumentException ex) {
                return null;
            }
        }
    }
}
