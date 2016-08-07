/**
 * Created by wavz on 21/07/2016.
 */
import com.google.inject.AbstractModule;
public class AppInjector extends AbstractModule {


    protected void configure() {
        //bind the service to implementation class
        //bind(MessageService.class).to(EmailService.class);

        //bind MessageService to Facebook Message implementation
        bind(Encryption.class).to(RSA.class);

    }
}
