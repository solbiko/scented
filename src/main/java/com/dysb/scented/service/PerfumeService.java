package com.dysb.scented.service;


import com.dysb.scented.dto.PerfumeDto;
import com.dysb.scented.entity.Perfume;
import com.dysb.scented.enumerate.ErrorMessage;
import com.dysb.scented.exception.ApiResponseException;
import com.dysb.scented.model.ApiPageData;
import com.dysb.scented.model.ApiPageParameter;
import com.dysb.scented.model.ApiQueryParameter;
import com.dysb.scented.repository.PerfumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PerfumeService {

    private final PerfumeRepository repository;

    @Transactional(readOnly = true)
    public ApiPageData getList(ApiQueryParameter para, ApiPageParameter page) {

        Sort sort = Sort.by(
                new Sort.Order(Sort.Direction.ASC, "id", Sort.NullHandling.NULLS_LAST));
        page.setSort(sort);

        Page<Perfume> perfumePage = repository.findAll(page.to());
        List<PerfumeDto> resList = PerfumeDto.by(perfumePage.getContent());

        return new ApiPageData(perfumePage, resList);
    }


    public PerfumeDto addPerfume(PerfumeDto perfumeDto){
        return new PerfumeDto(repository.save(perfumeDto.to()));
    }

    public PerfumeDto updatePerfume(PerfumeDto perfumeDto){
        Perfume perfume = repository.findById(perfumeDto.getId())
                .orElseThrow(() -> new ApiResponseException(ErrorMessage.ERROR_NOT_FOUND));

        return new PerfumeDto(repository.save(perfumeDto.to()));
    }

    public void deletePerfume(Long id){

        Perfume perfume = repository.findById(id)
                .orElseThrow(() -> new ApiResponseException(ErrorMessage.ERROR_NOT_FOUND));

        repository.deleteById(perfume.getId());
    }



}
