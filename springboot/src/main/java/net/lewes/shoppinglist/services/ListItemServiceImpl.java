package net.lewes.shoppinglist.services;

import net.lewes.shoppinglist.models.ListItem;
import net.lewes.shoppinglist.repositories.ListItemRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ListItemServiceImpl implements ListItemService{
    private final ProductServiceImpl productService;
    private final ListItemRepository listItemRepository;

    public ListItemServiceImpl(ProductServiceImpl productService, ListItemRepository listItemRepository) {
        this.productService = productService;
        this.listItemRepository = listItemRepository;
    }

    @Override
    public ListItem addToList(String barcode) {
        ListItem listItem = listItemRepository.findByBarcode(barcode);

        if (listItem == null )
        {
            listItem = new ListItem(productService.findProduct(barcode),0);
        }

        listItem.setCount(listItem.getCount() + 1);

        return listItemRepository.save(listItem);
    }

    @Override
    public ListItem increaseCount(String barcode) {
        ListItem listItem = listItemRepository.findByBarcode(barcode);

        if (listItem == null)
        {
            return null;
        }
        listItem.setCount(listItem.getCount() + 1);

        listItemRepository.save(listItem);

        return listItem;
    }

    @Override
    public ListItem decreaseCount(String barcode) {
        ListItem listItem = listItemRepository.findByBarcode(barcode);
        if (listItem.getCount() > 1) {
            listItem.setCount(listItem.getCount() - 1);
            listItemRepository.save(listItem);
            return listItem;
        }

        deleteByBarcode(barcode);
        return null;
    }

    @Override
    public Void deleteByBarcode(String barcode) {
        ListItem listItem = listItemRepository.findByBarcode(barcode);

        listItemRepository.delete(listItem);
        return null;
    }

    @Override
    public ListItem updateItem(ListItem passedItem) {
        ListItem listItem = listItemRepository.findByBarcode(passedItem.getBarcode());

        if (!listItem.getItemName().equals(passedItem.getItemName())) {
            listItem.setItemName(passedItem.getItemName());
            productService.updateProductName(passedItem.getBarcode(), passedItem.getItemName());
        }
        listItem.setCount(passedItem.getCount());

        return listItemRepository.save(listItem);
    }

    @Override
    public ListItem addItem(ListItem passedItem) {
        passedItem.setBarcode(UUID.randomUUID().toString());
        return listItemRepository.save(passedItem);
    }
}
