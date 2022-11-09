package org.daniel;


import io.reactivex.Flowable;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.streams.operators.ReactiveStreams;
import org.reactivestreams.Publisher;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Path("/hello")
public class GreetingResource {

    @Inject
    @Channel("generated-temperature")
    Emitter<Integer> emitter;

    @GET
    @Path("/emit/{dato}")
    public void emit(@PathParam("dato") Integer dato) {
        emitter.send(dato);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public CompletionStage<String> hello() {
        return ReactiveStreams.of("h", "e", "l", "l", "o")
                .map(String::toUpperCase)
                .toList()
                .run()
                .thenApply(list -> list.toString());
    }

}
