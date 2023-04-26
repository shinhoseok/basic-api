package com.basic.api.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserEntity is a Querydsl query type for UserEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserEntity extends EntityPathBase<UserEntity> {

    private static final long serialVersionUID = -1757136104L;

    public static final QUserEntity userEntity = new QUserEntity("userEntity");

    public final QCommonEntity _super = new QCommonEntity(this);

    public final StringPath addr = createString("addr");

    public final StringPath emailAddr = createString("emailAddr");

    public final StringPath mblPno = createString("mblPno");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDt = _super.modDt;

    //inherited
    public final NumberPath<Long> modId = _super.modId;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDt = _super.regDt;

    //inherited
    public final NumberPath<Long> regId = _super.regId;

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public final StringPath userNm = createString("userNm");

    public final StringPath userPw = createString("userPw");

    public QUserEntity(String variable) {
        super(UserEntity.class, forVariable(variable));
    }

    public QUserEntity(Path<? extends UserEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserEntity(PathMetadata metadata) {
        super(UserEntity.class, metadata);
    }

}

