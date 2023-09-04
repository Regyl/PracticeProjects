package com.deepspace.concurrency.eratosthenessieve;

import edu.rice.pcdp.Actor;
import edu.rice.pcdp.PCDP;

/**
 * An actor-based implementation of the Sieve of Eratosthenes.
 */
public class ConcurrentSieveImpl extends Sieve {
    
    private static final int FIRST_PRIME_NUMBER = 2;
    
    @Override
    public int countPrimes(final int limit) {
        SieveActor firstSieveActor = new SieveActor(FIRST_PRIME_NUMBER);
        
        PCDP.finish(() -> {
            for (int i = 2; i < limit; i++) {
                firstSieveActor.send(i);
            }
        });
        
        return countResult(firstSieveActor);
    }
    
    private int countResult(SieveActor actor) {
        int result = 0;
        SieveActor nextActor = actor;
        while (nextActor != null) {
            result += 1;
            nextActor = nextActor.getNextActor();
        }
        
        return result;
    }

    /**
     * An actor class that helps implement the Sieve of Eratosthenes in parallel.
     */
    public static final class SieveActor extends Actor {
        
        private final int filter;
        
        private SieveActor nextActor;
        
        public SieveActor(int filter) {
            this.filter = filter;
        }
        
        /**
         * Process a single message sent to this actor.
         *
         * @param msg Received message
         */
        @Override
        public void process(final Object msg) {
            int number = (int) msg;
            if (number == filter) {
                //Do nothing, the target Actor's number is found
            } else if (number % filter == 0) {
                //Do nothing, this is the case when filter = 2 and number = 4
            } else if (number > filter) {
                if (this.nextActor == null) {
                    this.nextActor = new SieveActor(number);
                }
                
                this.nextActor.send(number);
            }
        }
        
        public SieveActor getNextActor() {
            return nextActor;
        }
    }
}
