package br.com.prando.requestcontextholdercustompool;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

public class RequestContextAwareRunnableWrapper implements Runnable {

    private Runnable runnable;
    private RequestAttributes requestAttributes;

    public RequestContextAwareRunnableWrapper(Runnable runnable, RequestAttributes requestAttributes) {
        this.runnable = runnable;
        this.requestAttributes = requestAttributes;
    }

    @Override
    public void run() {
        try {
            RequestContextHolder.setRequestAttributes(requestAttributes);
            runnable.run();
        } finally {
            RequestContextHolder.resetRequestAttributes();
        }
    }
}
