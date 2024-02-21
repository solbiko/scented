package com.dysb.scented.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiPageData {

    private Long    totCnt;
    private Integer totPage;
    private Integer curPage;
    private Integer size;
    private Integer count;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object  body;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object  stat;

    public ApiPageData(Page<?> p, Object body){
        this.totCnt = p.getTotalElements();
        this.size = p.getSize();
        this.curPage = p.getNumber();
        this.totPage = p.getTotalPages();
        this.count = p.getNumberOfElements();

        this.body = body;
    }

    public <T> ApiPageData(ApiPageParameter p, List<T> body){
        this.totCnt = (long) body.size();
        this.size = p.getSize();
        this.curPage = p.getPage();
        this.totPage = this.totCnt.intValue() / this.size + (this.totCnt % this.size > 0 ? 1 : 0);

        int startPos = this.size * this.curPage;
        if(startPos >= this.totCnt) {
            this.count = 0;
            this.body = new ArrayList<>();
            return;
        }
        int endPos = startPos + this.size;
        if(endPos >= this.totCnt)
            endPos = this.totCnt.intValue();

        List<T> slice = body.subList(startPos, endPos);
        this.count = slice.size();
        this.body = slice;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
