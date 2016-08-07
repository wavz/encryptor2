import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by wavz on 25/07/2016.
 */
public class CeasarCipherTest {
    @Test
    public void encryption() throws Exception {
        CeasarCipher c=new CeasarCipher();
        String s="try to encrypt that!!";
        String encrypted=c.encryption(s,"2");
        assertEquals(0,IsEmpty(encrypted));
    }

    @Test
    public void checkKey() throws Exception {

        CeasarCipher c=new CeasarCipher();
        assertEquals(1,c.checkKey("2"));
    }

    @Test
    public void generateKey() throws Exception {
        CeasarCipher c=new CeasarCipher();
        String key=c.generateKey();
        assertEquals(1,c.checkKey(key));
    }

    public int IsEmpty(String s){
        if("".equals(s)){
            return 1;
        }
        return 0;
    }

}