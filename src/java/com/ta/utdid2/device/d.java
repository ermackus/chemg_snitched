package com.ta.utdid2.device;

import com.ta.utdid2.b.a.a;
import com.ta.utdid2.b.a.b;
import java.util.Random;

public class d
{
    private static Random a;
    private String p;
    
    static {
        d.a = new Random();
    }
    
    public d() {
        this.p = "XwYp8WL8bm6S4wu6yEYmLGy4RRRdJDIhxCBdk3CiNZTwGoj1bScVZEeVp9vBiiIsgwDtqZHP8QLoFM6o6MRYjW8QqyrZBI654mqoUk5SOLDyzordzOU5QhYguEJh54q3K1KqMEXpdEQJJjs1Urqjm2s4jgPfCZ4hMuIjAMRrEQluA7FeoqWMJOwghcLcPVleQ8PLzAcaKidybmwhvNAxIyKRpbZlcDjNCcUvsJYvyzEA9VUIaHkIAJ62lpA3EE3H";
        this.p = b.encodeToString("XwYp8WL8bm6S4wu6yEYmLGy4RRRdJDIhxCBdk3CiNZTwGoj1bScVZEeVp9vBiiIsgwDtqZHP8QLoFM6o6MRYjW8QqyrZBI654mqoUk5SOLDyzordzOU5QhYguEJh54q3K1KqMEXpdEQJJjs1Urqjm2s4jgPfCZ4hMuIjAMRrEQluA7FeoqWMJOwghcLcPVleQ8PLzAcaKidybmwhvNAxIyKRpbZlcDjNCcUvsJYvyzEA9VUIaHkIAJ62lpA3EE3H".getBytes(), 2);
    }
    
    public String a(final String s) {
        return com.ta.utdid2.b.a.a.d(this.p, s);
    }
    
    public String b(final String s) {
        return com.ta.utdid2.b.a.a.e(this.p, s);
    }
    
    public String c(final byte[] array) {
        return com.ta.utdid2.b.a.a.d(this.p, b.encodeToString(array, 2));
    }
}
