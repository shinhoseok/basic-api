package com.basic.api.repository.impl;

import com.basic.api.entity.QUserEntity;
import com.basic.api.repository.UserManageRepoCustom;
import com.basic.api.vo.UserVO;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import java.util.List;

@RequiredArgsConstructor
public class UserManageRepoImpl implements UserManageRepoCustom {

    private final JPAQueryFactory jpaQueryFactory;

    private QUserEntity userEntity = QUserEntity.userEntity;

    @Override
    public Page<UserVO> selectUserList(UserVO userVO, Pageable pageable) {
        List<UserVO> userList = jpaQueryFactory
                .select(Projections.fields(UserVO.class,
                        userEntity.userId,
                        userEntity.userNm,
                        userEntity.emailAddr,
                        userEntity.regDt))
                .from(userEntity)
                .where(
                        selectUserListWhere(userVO)
                )
                .orderBy(selectUserListSort(userVO))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return new PageImpl<>(userList, pageable, selectUserListCnt(userVO));
    }

    public Long selectUserListCnt(UserVO userVO) {
        Long cnt = jpaQueryFactory
                .select(
                        userEntity.count()
                )
                .from(userEntity)
                .where(
                        selectUserListWhere(userVO)
                )
                .fetchOne();
        return cnt;
    }

    private BooleanExpression selectUserListWhere(UserVO userVO) {
        if(StringUtils.hasText(userVO.getSearchCondition())) {
            if(userVO.getSearchCondition().equals("userNm")) {
                return userEntity.userNm.like(userVO.getSearchKeyword());
            } else {
                return userEntity.emailAddr.like(userVO.getSearchKeyword());
            }
        } else {
            return null;
        }
    }

    private OrderSpecifier<?> selectUserListSort(UserVO userVO) {
        Order sort = Order.DESC;
        if(StringUtils.hasText(userVO.getSortDescend())) {
            sort = (userVO.getSortDescend().equals("asc")) ? Order.ASC : Order.DESC;
        }

        if(StringUtils.hasText(userVO.getSortSubject())) {
            if(userVO.getSearchCondition().equals("userNm")) {
                return new OrderSpecifier<>(sort, userEntity.userNm);
            }
        } else {
            return new OrderSpecifier<>(sort, userEntity.userId);
        }
        return null;
    }
}
