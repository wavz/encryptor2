import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by wavz on 25/07/2016.
 */
public class SplitEncryptionTest {
    @Test
    public void generateKey() throws Exception {
        SplitEncryption s=new SplitEncryption();
        String key=s.generateKey();
        assertEquals(1,s.checkKey(key));
    }

    @Test
    public void checkKey() throws Exception {

        SplitEncryption s=new SplitEncryption();
        String key="j,1";
        assertEquals(0,s.checkKey(key));

    }

}