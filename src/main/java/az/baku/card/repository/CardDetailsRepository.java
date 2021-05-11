package az.baku.card.repository;

import az.baku.card.model.entity.CardDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface CardDetailsRepository extends JpaRepository<CardDetails, Long> {
}
