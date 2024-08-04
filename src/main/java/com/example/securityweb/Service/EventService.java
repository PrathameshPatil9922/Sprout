package com.example.securityweb.Service;

import org.springframework.stereotype.Service;

import com.example.securityweb.Entity.Event;
import com.example.securityweb.Repository.EventRepository;

import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;

    
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public void addEvent(Event event) {
        eventRepository.save(event);
    }
}

