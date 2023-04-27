

    
    /* 정규식 확인 */
    // 인원수
    const $peopleCount = $(".people-count");
    const $peopleCountWarning = $(".people-count-error");
    
    $peopleCount.on("blur", function(){
        var countPattern =  /^[0-9]+$/;  //인원수 정규식
        if (!countPattern.test($peopleCount.val())) {
            $peopleCountWarning.text("숫자만 입력해 주세요.");
            $peopleCountWarning.css("display", "block");
            $peopleCount.css("border-color", "#e52929");
            // $(this).find('.mb').attr('class', 'mb0');
            checkAll[0] = false;
    
        } else {
            $peopleCountWarning.css("display", "none");
            $peopleCount.css("border-color", "#e0e0e0");
            checkAll[0] = true;
        }
    });
    
        // 가격
    const $recruitmentPrice = $(".recruitment-price");
    const $priceWarning = $(".price-error");
    
    
    $recruitmentPrice.on("blur", function(){
        var countPattern =  /^[0-9]+$/;  //인원수 정규식
        if (!countPattern.test($recruitmentPrice.val())) {
            $priceWarning.text("숫자만 입력해 주세요.");
            $priceWarning.css("display", "block");
            $recruitmentPrice.css("border-color", "#e52929");
            checkAll[0] = false;
    
        } else {
            $priceWarning.css("display", "none");
            $recruitmentPrice.css("border-color", "#e0e0e0");
            checkAll[0] = true;
        }
    });

        // 오픈채팅 비밀번호
    const $openKakaoPassword = $(".open-kakao-password");
    const $openKakaoPasswordWarning = $(".open-password-error");
    
    
    $openKakaoPassword.on("blur", function(){
        var countPattern =  /^[0-9]+$/;  //인원수 정규식
        if (!countPattern.test($openKakaoPassword.val())) {
            $openKakaoPasswordWarning.text("숫자만 입력해 주세요.");
            $openKakaoPasswordWarning.css("display", "block");
            $openKakaoPassword.css("border-color", "#e52929");
            checkAll[0] = false;
    
        } else {
            $openKakaoPasswordWarning.css("display", "none");
            $openKakaoPassword.css("border-color", "#e0e0e0");
            checkAll[0] = true;
        }
    });

    
        // 오픈채팅
    const $openKakao = $(".open-kakao");
    const $openKakaoWarning = $(".open-kakao-error");
    
    $openKakao.on("blur", function(){
        var koreanPattern = /[^A-Za-z0-9_\`\~\!\@\#\$\%\^\&\*\(\)\-\=\+\\\{\}\[\]\'\"\;\:\<\,\>\.\?\/\s]/gm;   //한글 정규식
        if (!koreanPattern.test($openKakao.val())) {
            // console.log($openKakao.val());
            $openKakaoWarning.text("한글은 입력 불가능합니다.");
            $openKakaoWarning.css("display", "block");
            $openKakao.css("border-color", "#e52929");
            // $(this).find('.mb').attr('class', 'mb0');
            checkAll[0] = false;
        
        } else {
            console.log($openKakao.val());
            $openKakaoWarning.css("display", "none");
            $openKakao.css("border-color", "#e0e0e0");
            checkAll[0] = true;
        }
        });


        /* 필수사항 체크 */
        const $submitBtn = $(".submit-btn") //등록 버튼
        // 모든 정규식(약관 제외)
        let checkAll = [false, false, false, false, false, false, false, false, false];


    // 회원가입 버튼 활성화
	$submitBtn.on("click", function() {
		var flag = false;
		if (checkbox) {
			for (let i = 0; i < checkAll.length; i++) {
				var check = checkAll[i];s
				if (!check) {
					flag = true;
					break;
				}
			}
		}
    	});
    
    
    