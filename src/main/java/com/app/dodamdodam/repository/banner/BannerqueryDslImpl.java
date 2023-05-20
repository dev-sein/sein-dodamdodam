package com.app.dodamdodam.repository.banner;

import com.app.dodamdodam.entity.banner.BannerApply;
import com.app.dodamdodam.search.banner.AdminBannerSearch;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.app.dodamdodam.entity.banner.QBannerApply.bannerApply;


@RequiredArgsConstructor
public class BannerqueryDslImpl implements BannerqueryDsl {

    @Autowired
    private JPAQueryFactory query;

    @Override //관리자 배너 검색
    public Page<BannerApply> findAdminBannerApplyWithPaging_QueryDSL(AdminBannerSearch bannerSearch, Pageable pageable) {
        BooleanExpression bannerStatusEq = bannerSearch.getBannerStatus() == null ? null : bannerApply.bannerStatus.eq(bannerSearch.getBannerStatus());
        BooleanExpression memberNameEq = bannerSearch.getMemberName() == null ? null : bannerApply.member.memberName.eq(bannerSearch.getMemberName());
        BooleanExpression memberPhoneEq = bannerSearch.getMemberPhone() == null ? null : bannerApply.member.memberPhone.eq(bannerSearch.getMemberPhone());
        BooleanExpression bannerRegisterDateEq = bannerSearch.getBannerRegisterDate() == null ? null : bannerApply.bannerRegisterDate.eq(bannerSearch.getBannerRegisterDate());

        List<BannerApply> bannerApplies = query.select(bannerApply)
                .from(bannerApply)
                .where(bannerStatusEq, memberPhoneEq, memberNameEq, bannerRegisterDateEq)
                .orderBy(bannerApply.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        Long count = query.select(bannerApply.count()).from(bannerApply).fetchOne();

        return new PageImpl<>(bannerApplies, pageable, count);
    }
}
