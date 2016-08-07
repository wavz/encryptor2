import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by wavz on 25/07/2016.
 */
public class ReverseEncryptionTest {
    @Test
    public void generateKey() throws Exception {
        ReverseEncryption r=new ReverseEncryption();
        String key=r.generateKey();
        assertEquals(1,r.checkKey(key));
    }

    @Test
    public void checkKey() throws Exception {
        ReverseEncryption r=new ReverseEncryption();
        String key="j";
        assertEquals(1,r.checkKey(key));

    }

}