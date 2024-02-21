package com.dysb.scented.annotation;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;

@Target({METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Parameter(in = ParameterIn.QUERY, name = "page", schema = @Schema(type = "Integer"), description = "페이지 번호 0부터 시작")
@Parameter(in = ParameterIn.QUERY, name = "size", schema = @Schema(type = "Integer"), description = "페이지 당 row 갯수")
public @interface ApiPageParameters {
}
