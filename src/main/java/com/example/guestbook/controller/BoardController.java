package com.example.guestbook.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.guestbook.entity.Post;
import com.example.guestbook.service.PostService;
@Controller
@RequestMapping("/posts")
public class BoardController {
    private final PostService service;
    public BoardController(PostService service) { this.service = service; }
    @GetMapping public String list(Model model) {
        model.addAttribute("posts", service.list()); return "list";
    }
    @GetMapping("/new") public String newForm(Model model) {
        model.addAttribute("post", new Post()); return "form";
    }
    @PostMapping public String create(@ModelAttribute Post post) {
        service.save(post); return "redirect:/posts";
    }
    @GetMapping("/{id}") public String view(@PathVariable Long id, Model model) {
        model.addAttribute("post", service.get(id)); return "view";
    }
    @GetMapping("/{id}/edit") public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("post", service.get(id)); return "form";
    }
    @PostMapping("/{id}/delete") public String delete(@PathVariable Long id) {
        service.delete(id); return "redirect:/posts";
    }
}