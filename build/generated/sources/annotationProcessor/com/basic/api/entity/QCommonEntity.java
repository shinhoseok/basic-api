package com.basic.api.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCommonEntity is a Querydsl query type for CommonEntity
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QCommonEntity extends EntityPathBase<CommonEntity> {

    private static final long serialVersionUID = 1896523448L;

    public static final QCommonEntity commonEntity = new QCommonEntity("commonEntity");

    public final DateTimePath<java.time.LocalDateTime> modDt = createDateTime("modDt", java.time.LocalDateTime.class);

    public final NumberPath<Long> modId = createNumber("modId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> regDt = createDateTime("regDt", java.time.LocalDateTime.class);

    public final NumberPath<Long> regId = createNumber("regId", Long.class);

    public QCommonEntity(String variable) {
        super(CommonEntity.class, forVariable(variable));
    }

    public QCommonEntity(Path<? extends CommonEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCommonEntity(PathMetadata metadata) {
        super(CommonEntity.class, metadata);
    }

}

