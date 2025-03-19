package com.logonedigital.Nnam.mapper;

import com.logonedigital.Nnam.dto.stock.StockReqDTO;
import com.logonedigital.Nnam.dto.stock.StockResDTO;
import com.logonedigital.Nnam.entities.Stock;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Configuration;

@Mapper(componentModel = "spring")
public interface StockMapper {
    @Mapping(source = "id", target = "idStock")
    StockResDTO toDTO(Stock stock);
    Stock toEntity(StockReqDTO dto);
}
