package com.app.dodamdodam.repository.banner;

import com.app.dodamdodam.entity.banner.BannerApply;
import org.springframework.boot.Banner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BannerRepository extends JpaRepository<BannerApply, Long>, BannerqueryDsl {
}
