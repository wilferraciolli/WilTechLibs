package com.wiltech;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

/**
 * The type Event publisher. Base class to allow publishing events.
 */
@Service
public class EventPublisher implements ApplicationEventPublisherAware {

    /**
     * The Publisher.
     */
    private ApplicationEventPublisher publisher;

    //MUST. It will give you access to ApplicationEventPublisher
    @Override
    public void setApplicationEventPublisher(final ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    public void publishEvent(final ApplicationEvent event) {
        publisher.publishEvent(event);
    }
}
