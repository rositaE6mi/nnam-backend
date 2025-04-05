package com.logonedigital.Nnam.dto;

import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;

@Data
public class PageDTO {
    private Integer pageNo = 0;
    private Integer pageSize = 5;
    private Sort.Direction sort = Sort.Direction.ASC;
    private String sortByColumn = "id";

    public Pageable getPageable(PageDTO pageDTO){
        Integer page = Objects.nonNull(pageDTO.getPageNo()) ? pageDTO.getPageNo() : this.pageNo;
        Integer size = Objects.nonNull(pageDTO.getPageSize()) ? pageDTO.getPageSize() : this.pageSize;
        Sort.Direction sort = Objects.nonNull(pageDTO.getSort()) ? pageDTO.getSort() : this.sort;
        String sortByColumn = Objects.nonNull(pageDTO.getSortByColumn()) ? pageDTO.getSortByColumn() : this.sortByColumn;
        PageRequest request = PageRequest.of(page, size, sort, sortByColumn);

        return request;
    }


}
