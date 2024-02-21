package com.dysb.scented.dto;

import com.dysb.scented.entity.Perfume;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PerfumeDto {
    private Long id;

    @Schema(description = "향수명")
    private String name;
    @Schema(description = "브랜드명")
    private String brandName;

    @Schema(description = "향수 댓글")
    List<CommentDto> commentList;


    public PerfumeDto(Perfume perfume) {
        if (perfume == null) {
            return;
        }
        this.id = perfume.getId();
        this.name = perfume.getName();
        this.brandName = perfume.getBrandName();
        if (perfume.getComments() != null) {
            this.commentList = CommentDto.by(perfume.getComments().stream().toList());
        }
    }

    public static List<PerfumeDto> by(List<Perfume> personList) {
        List<PerfumeDto> r = new ArrayList<>();
        if (personList != null) {
            personList.forEach(entity -> r.add(new PerfumeDto(entity)));
        }
        return r;
    }

    public Perfume to(){
        Perfume perfume = new Perfume();
        perfume.setId(this.getId());
        perfume.setName(this.getName());
        perfume.setBrandName(this.getBrandName());

        return perfume;
    }

}
