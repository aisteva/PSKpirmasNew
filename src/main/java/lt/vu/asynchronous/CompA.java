package lt.vu.asynchronous;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


@Named
@ApplicationScoped
public class CompA implements Serializable {

    @Inject
    private CompB compB;

    private Future<String> resultInFuture = null;

    public String callAsyncMethod() throws ExecutionException, InterruptedException {
        if (resultInFuture == null) {
            resultInFuture = compB.asyncMethod();
            return "Buvo iskviesta, kazkas blogai? " + resultInFuture.isDone();
        } else {
            if (resultInFuture.isDone()) {
                String result = resultInFuture.get();
                resultInFuture = null;
                return result;
            } else {
                return "Skaiciavimai vis dar nebaigti...";
            }
        }
    }
}
