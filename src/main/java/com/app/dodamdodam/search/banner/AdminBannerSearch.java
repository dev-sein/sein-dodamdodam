package com.app.dodamdodam.search.banner;

import com.app.dodamdodam.type.BannerType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AdminBannerSearch {
    private BannerType bannerStatus;
    private String memberName;
    private String memberPhone;
    private LocalDate bannerRegisterDate;

}
