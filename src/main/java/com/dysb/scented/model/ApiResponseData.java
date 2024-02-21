package com.dysb.scented.model;

import com.dysb.scented.enumerate.ErrorMessage;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponseData {
    private Boolean result;
    private Object data;
    private ApiPageData page;
    private Object info;
    private Map<String, Object> fail;
    private Object stat;

    public ApiResponseData(String failCode, String failMsg) {
        this.result = false;
        this.fail = new HashMap<>();
        this.fail.put("code", failCode);
        this.fail.put("msg", failMsg);
    }

    public static ResponseEntity<Object> ok(Object body) {
        return ResponseEntity.ok(ApiResponseData.builder().data(body).build());
    }

    public static ResponseEntity<Object> okStat(Object body, Object st) {
        return ResponseEntity.ok(ApiResponseData.builder().data(body).stat(st).build());
    }

    public static ResponseEntity<Object> ok(String key, Object body) {
        return ResponseEntity.ok(ApiResponseData.builder().data(key, body).build());
    }
    public static ResponseEntity<Object> ok(String k, Map<String, Object> map) {
        ApiResponseData r = ApiResponseData.builder()
                .data(k, map.get("data"))
                .data(map.get("data"))
                .page((ApiPageData) map.get("page"))
                .build();
        return ResponseEntity.ok(r);
    }

    public static ResponseEntity<Object> ok(Object body, ApiPageData page) {
        if (page.getStat() != null)
            return ResponseEntity.ok(ApiResponseData.builder().data(body).page(page).stat(page.getStat()).build());
        return ResponseEntity.ok(ApiResponseData.builder().data(body).page(page).build());
    }

    public static ResponseEntity<Object> ok(ApiPageData page) {
        Object body = page.getBody();
        page.setBody(null);
        if (page.getStat() != null) {
            return ResponseEntity.ok(ApiResponseData.builder()
                    .data(body)
                    .page(page)
                    .stat(page.getStat())
                    .build());
        }
        return ResponseEntity.ok(
                ApiResponseData.builder()
                        .data(body)
                        .page(page)
                        .build());
    }

    public static ResponseEntity<Object> ok() {
        return ResponseEntity.ok(ApiResponseData.builder().result(true).build());
    }

    public static class ApiResponseDataBuilder {
        public ApiResponseDataBuilder data(Object v) {
            this.result = true;
            this.data = v;
            return this;
        }

        public ApiResponseDataBuilder data(String k, Object v) {
            this.result = true;

            if (this.data == null)
                this.data = new HashMap<>();

            Map<String, Object> m;
            m = (Map<String, Object>) this.data;
            m.put(k, v);

            return this;
        }

        public ApiResponseDataBuilder page(ApiPageData p) {
            this.page = p;
            this.page.setBody(null);
            return this;
        }

        public ApiResponseDataBuilder page(Page<?> p) {
            this.page = ApiPageData.builder()
                    .totCnt(p.getTotalElements())
                    .size(p.getSize())
                    .curPage(p.getNumber())
                    .totPage(p.getTotalPages()).build();
            return this;
        }

        public ApiResponseDataBuilder failCodeMsg(ErrorMessage em, String msg) {
            this.result = false;
            if (this.fail == null)
                this.fail = new HashMap<>();
            this.fail.put("code", em.getCode());
            this.fail.put("msg", msg);
            return this;
        }

        public ApiResponseDataBuilder stat(Object s) {
            this.stat = s;
            return this;
        }
    }
}
