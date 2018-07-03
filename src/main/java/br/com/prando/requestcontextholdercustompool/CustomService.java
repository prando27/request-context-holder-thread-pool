package br.com.prando.requestcontextholdercustompool;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.concurrent.CompletableFuture;

@Service
public class CustomService {

    public void delegateTask() {
        System.out.println("servlet-thread: " + Thread.currentThread().getName());
        System.out.println("servlet-thread-request-attributes: " + RequestContextHolder.getRequestAttributes());

        CompletableFuture.runAsync(new RequestContextAwareRunnableWrapper(() -> {

            System.out.println("completable-future-thread: " + Thread.currentThread().getName());
            System.out.println("completable-future-request-attributes: " + RequestContextHolder.getRequestAttributes());

        }, RequestContextHolder.getRequestAttributes()));
    }
}
