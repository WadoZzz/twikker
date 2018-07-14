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

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {

        return "greeting";
    }

    @GetMapping("/main")
    public String mainPage(Map<String, Object> model) {
        Iterable<Message> messageRepoAll = messageRepo.findAll();
        model.put("message", messageRepoAll);
        return "main";
    }

    @PostMapping("/main")
    public String addMessage(@RequestParam String text, @RequestParam String tag, Map<String, Object> model) {
        Message message = new Message(text, tag);
        messageRepo.save(message);
        Iterable<Message> all = messageRepo.findAll();
        model.put("message", all);
        return "main";
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
        return "main";
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