package com.ballboy.shop.item;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Optional<Item> detail(Integer id) {
        Optional<Item> result = itemRepository.findById(id);

        return result;
    }

    public List<Item> list() {
        List<Item> result = itemRepository.findAll();

        return result;
    }

    public Item add(Item item) {
        Item result = itemRepository.save(item);

        return result;
    }

    public Item modify(Item item) {
        Item result = itemRepository.save(item);
        return result;
    }

    public void delete(Integer id) {
        itemRepository.deleteById(id);
    }
}
