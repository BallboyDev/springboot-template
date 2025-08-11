package com.ballboy.shop.item;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

// @Controller
@RestController
// @CrossOrigin(origins = "*")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/detail/{id}")
    public Item detail(@PathVariable Integer id) {

        Optional<Item> result = itemService.detail(id);

        System.out.println(result.get());

        return result.get();
    }

    @GetMapping("/list")
    // public List<Item> list() {
    public List<Item> list() {
        List<Item> result = itemService.list();

        System.out.println(result.get(0));

        return result;
    }

    @PostMapping("/add")
    // public String add(@RequestParam("title") String title, @RequestParam("price")
    // Integer price) {
    public Item add(@RequestBody Item item) {

        Item result = itemService.add(item);

        return result;
    }

    @PostMapping("/modify")
    public Item modify(@RequestBody Item item) {
        Item result = itemService.modify(item);

        return result;
    }

    @PostMapping("/delete")
    public Integer delete(@RequestBody Item item) {
        System.out.println("delete");

        itemService.delete(item.getId());

        return item.getId();
    }

    @GetMapping("/hash")
    public String getMethodName() {
        var result = new BCryptPasswordEncoder().encode("ballboy");
        System.out.println(result);
        return new String();
    }

}
