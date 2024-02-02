package net.lewes.shoppinglist.repositories;

import net.lewes.shoppinglist.models.ListItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListItemRepository extends JpaRepository<ListItem, Long> {
    ListItem findByBarcode(String barcode);
    Void deleteByBarcode(String barcode);
}
