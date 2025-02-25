package shop.managementapplication.service;


import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import shop.managementapplication.dao.InventoryRepository;
import shop.managementapplication.domain.products.Inventory;
import shop.managementapplication.dto.StockUpdateRequest;
import shop.managementapplication.errorhandling.InsufficientStockException;
import shop.managementapplication.errorhandling.InventoryNotFoundException;

@Service
@Transactional
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    private static final Logger logger = LoggerFactory.getLogger(InventoryService.class);

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public Inventory getInventoryForProduct(Long productNr) {
        // Log the search operation (optional but useful)
        logger.debug("Fetching inventory for product number: {}", productNr);

        // Fetch inventory or throw a more informative exception
        return inventoryRepository.findByProduct_ProductNr(productNr)
                .orElseThrow(() -> {
                    String errorMessage = String.format("Inventory not found for product Nr: %d", productNr);
                    logger.error(errorMessage);  // Log the error for future reference
                    return new InventoryNotFoundException(errorMessage);
                });
    }

    public Inventory updateStock(Long productNr, StockUpdateRequest request) throws InsufficientStockException {
        Inventory inventory = getInventoryForProduct(productNr);

        inventory.setAvailableQuantity(request.availableQuantity());
        inventory.setReservedQuantity(request.reservedQuantity());

        if(inventory.getAvailableQuantity() < 0 || inventory.getReservedQuantity() < 0) {
            throw new InsufficientStockException("Stock quantities cannot be negative");
        }

        return inventoryRepository.save(inventory);
    }

    public void reserveStock(Long productNr, Integer quantity) throws InsufficientStockException {
        Inventory inventory = getInventoryForProduct(productNr);

        if(inventory.getAvailableQuantity() < quantity) {
            throw new InsufficientStockException("Not enough stock. productNr: "+ productNr+ " Quantity: " +  quantity);
        }

        inventory.setAvailableQuantity(inventory.getAvailableQuantity() - quantity);
        inventory.setReservedQuantity(inventory.getReservedQuantity() + quantity);
        inventoryRepository.save(inventory);
    }

    public void releaseStock(Long productNr, Integer quantity) throws InsufficientStockException {
        Inventory inventory = getInventoryForProduct(productNr);

        if(inventory.getReservedQuantity() < quantity) {
            throw new InsufficientStockException("Not enough stock on reserved. productNr: "+ productNr+ " Quantity: " +  quantity);
        }

        inventory.setReservedQuantity(inventory.getReservedQuantity() - quantity);
        inventory.setAvailableQuantity(inventory.getAvailableQuantity() + quantity);
        inventoryRepository.save(inventory);
    }
}
