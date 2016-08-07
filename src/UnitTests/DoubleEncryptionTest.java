import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by wavz on 25/07/2016.
 */
public class DoubleEncryptionTest {
    @Test
    public void encryption() throws Exception {
        DoubleEncryption d=new DoubleEncryption();
        String key=d.generateKey();
        String s="flkjfnblejbnlgb";
        String encrypted=d.encryption(s,key);
        assertEquals(0,IsEmpty(encrypted));
    }

    @Test
    public void generateKey() throws Exception {
        DoubleEncryption d=new DoubleEncryption();
        String key=d.generateKey();
        assertEquals(1,d.checkKey(key));
    }

    @Test
    public void checkKey() throws Exception {
        DoubleEncryption d=new DoubleEncryption();
        String key="1,1";
        assertEquals(0,d.checkKey(key));

    }
    public int IsEmpty(String s){
        if("".equals(s)){
            return 1;
        }
        return 0;
    }

}