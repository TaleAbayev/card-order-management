package az.baku.card.repository;

import az.baku.card.model.dto.CardOrderDto;
import az.baku.card.model.entity.CardOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CardOrderRepository extends JpaRepository<CardOrder, Long> {

    List<CardOrder> findAllByUserId(Long userId);

    //Optional<CardOrder> findByIdAndUserId(Long orderId, Long userId);
}
