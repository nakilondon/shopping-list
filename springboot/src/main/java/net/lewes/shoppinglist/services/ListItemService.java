package net.lewes.shoppinglist.services;

import net.lewes.shoppinglist.models.ListItem;

public interface ListItemService {
    ListItem addToList(String barcode);
    ListItem increaseCount(String barcode);
    ListItem decreaseCount(String barcode);
    Void deleteByBarcode(String barcode);
    ListItem updateItem(ListItem passedItem);
    ListItem addItem(ListItem passedItem);
}
