package az.baku.card.model.mapper;

import az.baku.card.model.request.CardOrderRequest;
import az.baku.card.model.dto.CardOrderDto;
import az.baku.card.model.entity.CardOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CardOrderMapper {

    @Mapping(target = "orderId", source = "id")
    CardOrderDto toCardOrderDto(CardOrder cardOrder);

    List<CardOrderDto> toCardOrderDtos(List<CardOrder> cardOrders);

    CardOrder toCardOrder(CardOrderRequest cardOrderRequest);



}
