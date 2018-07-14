package com.example.suit;

import com.example.suit.domain.Message;
import com.example.suit.repository.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class GreetingController {

    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Map<String, Object> model) {
        model.put("name", name);
        return "greeting";
    }

    @GetMapping
    public String mainPage(Map<String, Object> model) {
        Iterable<Message> messageRepoAll = messageRepo.findAll();
        model.put("message", messageRepoAll);
        return "index";
    }

    @PostMapping
    public String addMessage(@RequestParam String text, @RequestParam String tag, Map<String, Object> model) {
        Message message = new Message(text, tag);
        messageRepo.save(message);
        Iterable<Message> all = messageRepo.findAll();
        model.put("message", all);
        return "index";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String tag, Map<String, Object> model) {
        Iterable<Message> byTag;
        if (tag != null && !tag.isEmpty()) {
            byTag = messageRepo.findByTag(tag);
        } else {
            byTag = messageRepo.findAll();
        }
        model.put("message", byTag);
        return "index";
    }

}