package com.dysb.scented.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @Comment("생성자")
    @Column(name = "reg_id")
    @CreatedBy
    private String regId;

    @Comment("수정자")
    @Column(name = "mod_id")
    @LastModifiedBy
    private String modId;

    @Comment("생성일자")
    @CreatedDate
    @Column(name = "reg_dt")
    private String regDt;

    @Comment("수정일자")
    @LastModifiedDate
    @Column(name = "mod_dt")
    private String modDt;

}