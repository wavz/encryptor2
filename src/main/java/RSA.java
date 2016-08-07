/**
 * Created by wavz on 21/07/2016.
 */


import java.math.BigInteger;
import java.security.SecureRandom;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RSA  implements Encryption
{

    public BigInteger getD()
    {
        return d;
    }

    @XmlElement
    public void setN(BigInteger n)
    {
        this.n = n;
    }

    @XmlElement
    public void setD(BigInteger d)
    {
        this.d = d;
    }

    @XmlElement
    public void setE(BigInteger e)
    {
        this.e = e;
    }

    private BigInteger n, d, e;

    private int bitlen = 1024;

    public RSA(BigInteger newn, BigInteger newe)
    {
        n = newn;
        e = newe;
    }

    public RSA()
    {
        System.out.println("Hello, you chose the RSA Algorithm, there is no need to choose a key,"
                + "this algorithm will generate them for you.");
        bitlen = 1024;
        SecureRandom r = new SecureRandom();
        BigInteger p = new BigInteger(bitlen / 2, 100, r);
        BigInteger q = new BigInteger(bitlen / 2, 100, r);
        n = p.multiply(q);
        BigInteger m = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        e = new BigInteger("3");
        while (m.gcd(e).intValue() > 1)
        {
            e = e.add(new BigInteger("2"));
        }
        d = e.modInverse(m);
    }


    public String encryption(String text, String key)
    {
        key = "";
        return (new BigInteger(text.getBytes())).modPow(e, n).toString();
    }

    public BigInteger encrypt(BigInteger message)
    {
        return message.modPow(e, n);
    }


    public String decryption(String message, String key)
    {
        return new String((new BigInteger(message)).modPow(d, n).toByteArray());
    }

    public BigInteger decrypt(BigInteger message)
    {
        return message.modPow(d, n);
    }


    public String generateKey()
    {
        SecureRandom r = new SecureRandom();
        BigInteger p = new BigInteger(bitlen / 2, 100, r);
        BigInteger q = new BigInteger(bitlen / 2, 100, r);
        n = p.multiply(q);
        BigInteger m = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        e = new BigInteger("3");
        while (m.gcd(e).intValue() > 1)
        {
            e = e.add(new BigInteger("2"));
        }
        d = e.modInverse(m);

        return n.toString() + "," + d.toString() + "," + e.toString();
    }

    public BigInteger getN()
    {
        return n;
    }

    public BigInteger getE()
    {
        return e;
    }


    public int checkKey(String key)
    {

        return 1;
    }

}
