package com.app.dodamdodam.service.banner;

import com.app.dodamdodam.domain.BannerDTO;
import com.app.dodamdodam.entity.banner.BannerApply;
import com.app.dodamdodam.entity.recruitment.RecruitmentBoard;
import com.app.dodamdodam.repository.banner.BannerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BannerApplyServiceImpl implements BannerApplyService {
    private final BannerRepository bannerRepository;

    @Override //관리자 배너 목록 불러오기
    public Page<BannerDTO> showList(Pageable pageable) {
        Page<BannerApply> bannerApplyPage = bannerRepository.findAllWithPaging(pageable);
        List<BannerDTO> bannerDTOS = bannerApplyPage.get().map(this::toBannerDTO).collect(Collectors.toList());
        return new PageImpl<>(bannerDTOS, pageable, bannerApplyPage.getTotalElements());
    }

    @Override //관리자 배너 상세보기
    public BannerDTO getAdminBannerDetail(Long id) {
        Optional<BannerApply> bannerApply = bannerRepository.findById(id);
        if (bannerApply.isPresent()) {
            return toBannerDTO(bannerApply.get());
        } else {
            // 값이 없을 경우에 대한 처리 로직
            // 예를 들어, null을 반환하거나 예외를 throw할 수 있습니다.
            return null; // 혹은 예외 처리 코드 작성
        }
    }

    @Override //관리자 삭제
    public void deleteAdminBannerList(List<Long> bannerApplyIds) {
        for (Long bannerApplyId : bannerApplyIds) {
            bannerRepository.deleteById(bannerApplyId);
        }
    }

}
