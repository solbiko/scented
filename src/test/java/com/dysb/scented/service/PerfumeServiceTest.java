package com.dysb.scented.service;

import com.dysb.scented.dto.PerfumeDto;
import com.dysb.scented.repository.PerfumeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PerfumeServiceTest {

    @Mock
    PerfumeRepository perfumeRepository;

    @InjectMocks
    PerfumeService perfumeService;

    @Test
    public void 향수_등록() {

        // given
        PerfumeDto perfumeDto = new PerfumeDto();
        perfumeDto.setName("테스트");
        perfumeDto.setBrandName("브랜드이름");
        when(perfumeRepository.save(any())).thenReturn(perfumeDto.to());

        // when
        perfumeService.addPerfume(perfumeDto);

        // test execute
        PerfumeDto res = perfumeService.addPerfume(perfumeDto);

        // then
        assertEquals(perfumeDto, res); // expected , actual

    }

}