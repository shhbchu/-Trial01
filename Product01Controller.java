package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Product01Controller {
    
    @Autowired
    private BlogRepository blogRepository;
    
    @Autowired
    private NameRepository nameRepository;

    @GetMapping("/")
    public String index(Model model) {
        List<Blog> blogs = blogRepository.findAll();
        model.addAttribute("blogs", blogs);
        return "index";
    }
    
    @GetMapping("/top")
    public String top(Blog blog) {
        return "top";
    }    
    
    @GetMapping("/index")
    public String index(Blog blog) {
        return "index";
    }
    
    @GetMapping("/form")
    public String form(Blog blog) {
        return "form";
    }
    
    @PostMapping("/create")
    public String create(Blog blog) {
        blogRepository.save(blog); 
        return "redirect:/blog/" + blog.getId();
    }
    
    @PostMapping("/result")
    public String result(Blog blog) {
        return "result";
    }
	
    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Integer id, Model model) {
        Optional<Blog> blog = blogRepository.findById(id);
        model.addAttribute("blog", blog.get());

        Name name = new Name();
        name.setBlog(blog.get());
        model.addAttribute("name", name);

        return "blog";
    }
    
    @PostMapping("/name")
    public String createName(Name name) {
        nameRepository.save(name);
        return "redirect:/blog/" + name.getBlog().getId();
    }

    
    
 
}
