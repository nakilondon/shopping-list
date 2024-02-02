package net.lewes.shoppinglist.controllers;

import net.lewes.shoppinglist.models.ListItem;
import net.lewes.shoppinglist.repositories.ListItemRepository;
import net.lewes.shoppinglist.services.ListItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ListController {
    private final ListItemService listItemService;
    private final ListItemRepository listItemRepository;

    public ListController(ListItemService listItemService, ListItemRepository listItemRepository) {
        this.listItemService = listItemService;
        this.listItemRepository = listItemRepository;
    }

    @CrossOrigin
    @GetMapping("/list")
    List<ListItem> getItems(Model model) {
        return (List<ListItem>) listItemRepository.findAll();
    }


    @PostMapping(value="/barcode/{barcode}")
    public ListItem addBarcode(@PathVariable String barcode) {
        ListItem listItem = listItemService.addToList(barcode);
        return listItem;
    }

    @CrossOrigin
    @DeleteMapping(value = "/listItem/{barcode}")
    public ResponseEntity deleteItem(@PathVariable String barcode) {
        listItemService.deleteByBarcode(barcode);

        return  ResponseEntity.ok("deleted: " + barcode);
    }

    @CrossOrigin
    @PostMapping(value = "/increase/{barcode}")
    public ListItem increaseItem(@PathVariable String barcode) {
        return listItemService.increaseCount(barcode);
    }

    @CrossOrigin
    @PostMapping(value = "/decrease/{barcode}")
    public ListItem decreaseItem(@PathVariable String barcode) {
        return listItemService.decreaseCount(barcode);
    }

    @CrossOrigin
    @PostMapping(value = "/updateItem/{barcode}")
    public ListItem updateItemName(@PathVariable String barcode, @RequestBody ListItem passedItem ) {
        return listItemService.updateItem(passedItem);
    }

    @CrossOrigin
    @PostMapping(value = "/addItem")
    public ListItem updateItemName(@RequestBody ListItem passedItem ) {
        return listItemService.addItem(passedItem);
    }

    @CrossOrigin
    @PostMapping("/clearList")
    List<ListItem> clearList(Model model) {
        listItemRepository.deleteAll();
        return (List<ListItem>) listItemRepository.findAll();
    }
}
