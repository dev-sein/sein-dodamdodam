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

    @Override
    public Page<BannerApply> findAllWithPaging(Pageable pageable) {
        List<BannerApply> bannerApplyList = query.select(bannerApply)
                .from(bannerApply)
                .orderBy(bannerApply.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        Long count = query.select(bannerApply.count())
                .from(bannerApply)
                .fetchOne();

        return new PageImpl<>(bannerApplyList, pageable, count);
    }

    @Override //관리자 배너 검색
    public Page<BannerApply> findAdminBannerApplyWithPaging_QueryDSL(AdminBannerSearch adminbannerSearch, Pageable pageable) {
        BooleanExpression searchAll = null;
        if (adminbannerSearch.getMemberPhone() != null || adminbannerSearch.getMemberName() != null) {
            BooleanExpression searchPhone = adminbannerSearch.getMemberPhone() != null ? bannerApply.member.memberPhone.contains(adminbannerSearch.getMemberPhone()) : null;
            BooleanExpression searchName = adminbannerSearch.getMemberName() != null ? bannerApply.member.memberName.contains(adminbannerSearch.getMemberName()) : null;

            searchAll = searchPhone.or(searchName);
        }
        
        List<BannerApply> bannerApplies = query.select(bannerApply)
                .from(bannerApply)
                .where(searchAll)
                .orderBy(bannerApply.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        Long count = query.select(bannerApply.count()).from(bannerApply).where(searchAll).fetchOne();

        return new PageImpl<>(bannerApplies, pageable, count);
    }
}
