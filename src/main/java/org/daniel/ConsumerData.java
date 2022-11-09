package org.daniel;

import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ConsumerData {


    @Incoming("temperature")
    public void printRandom(int randomNumber){
        System.out.println(randomNumber);
    }

}
