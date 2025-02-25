package shop.managementapplication.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shop.managementapplication.domain.products.Inventory;
import shop.managementapplication.domain.products.Product;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Optional<Inventory> findByProduct_ProductNr(Long productNr);
}
