/**
 * Created by wavz on 21/07/2016.
 */
import javax.inject.Inject;
public class MyApp   {

    private Encryption algo;

//	constructor based injector
//	@Inject
//	public MyApplication(MessageService svc){
//		this.service=svc;
//	}

    //setter method injector
    @Inject
    public void setService(Encryption svc){
        this.algo=svc;
    }

    public String encryption(String msg, String key){
        //some business logic here
        return algo.encryption(msg, key);
    }
    public String decryption(String msg, String key){
        return algo.decryption(msg,key);
    }
    public String generateKey(){
        return algo.generateKey();
    }
    public int checkKey(String key){
        return algo.checkKey(key);
    }

}
