package com.app.dodamdodam.repository.banner;

import com.app.dodamdodam.entity.banner.BannerApply;
import com.app.dodamdodam.search.banner.AdminBannerSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BannerqueryDsl {
    //관리자 배너 검색
    public Page<BannerApply> findAdminBannerApplyWithPaging_QueryDSL(AdminBannerSearch bannerSearch, Pageable pageable);
    }

