package com.basic.api.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
public abstract class CommonEntity {
    @Column(name = "regId", updatable = false)
    protected Long regId;
    @Column(name = "regDt", updatable = false)
    protected LocalDateTime regDt;
    @Column(name = "modId", insertable = false)
    protected Long modId;
    @Column(name = "modDt", insertable = false)
    protected LocalDateTime modDt;
    @Transient
    protected String searchCondition;
    @Transient
    protected String searchKeyword;
}
