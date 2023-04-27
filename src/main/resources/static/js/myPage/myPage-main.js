const rankBtn = document.getElementById('rankBtn'); // 버튼 요소를 가져옴

rankBtn.addEventListener('click', () => { // 버튼을 클릭하면 실행될 함수
  const modal = document.createElement('div'); // 모달을 생성
  modal.innerHTML = `
    <div class="modal-content">
    <section class="modal">
    <div class="modal-shape">
        <div class="modal-header">
            <h3>내 배지</h3>
            <a class="modal-close">
                <svg
                    xmlns="http://www.w3.org/2000/svg"
                    data-name="Capa 1"
                    id="Capa_1"
                    viewBox="0 0 20 19.84"
                >
                    <path d="M10.17,10l3.89-3.89a.37.37,0,1,0-.53-.53L9.64,9.43,5.75,5.54a.37.37,0,1,0-.53.53L9.11,10,5.22,13.85a.37.37,0,0,0,0,.53.34.34,0,0,0,.26.11.36.36,0,0,0,.27-.11l3.89-3.89,3.89,3.89a.34.34,0,0,0,.26.11.35.35,0,0,0,.27-.11.37.37,0,0,0,0-.53Z"/>
                </svg>
            </a>
        </div>
        <div class="content-container">
            <h4 class="detail-title">배지 정보</h4>
            <!-- 뱃지 정보들이 담길 테이블 -->
            <div class="content-wrap">
                <table class="table">
                    <thead>
                        <tr>
                            <th>항목</th>
                            <th>순위</th>
                            <th>수치</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <!-- 배지 이미지 들어가는 곳 -->
                            <th><img src="https://cdn3.iconfinder.com/data/icons/ranking-2/500/nim1393_15_ranking_bronze_coin-128.png" style="width: 20px;" alt=""></th>
                            <th>800등</th>
                            <th>200</th>
                        </tr>
                        <tr>
                            <th><img src="https://cdn3.iconfinder.com/data/icons/ranking-2/500/nim1393_16_ranking_silver_coin-128.png" style="width: 20px;" alt=""></th>
                            <th>600등</th>
                            <th>400</th>
                        </tr>
                        <tr>
                            <th><img src="https://cdn3.iconfinder.com/data/icons/ranking-2/500/nim1393_17_ranking_gold_coin_silver-128.png" style="width: 20px;" alt=""></th>
                            <th>10등</th>
                            <th>800</th>
                        </tr>
                        <tr>
                            <th><img src="https://cdn3.iconfinder.com/data/icons/ranking-2/500/nim1393_17_ranking_gold_coin_silver-128.png" style="width: 20px;" alt=""></th>
                            <th>10등</th>
                            <th>800</th>
                        </tr>
                        <tr>
                            <th><img src="https://cdn3.iconfinder.com/data/icons/ranking-2/500/nim1393_17_ranking_gold_coin_silver-128.png" style="width: 20px;" alt=""></th>
                            <th>10등</th>
                            <th>800</th>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <!-- 사용자의 배지 정보가 나오는 곳 -->
        <div class="content-container">
            <h4 class="detail-title">나의 배지</h4>
            <div class="content-wrap">
                <div class="user-info">
                    <span>당신의 배지는 현재 ㅇㅇㅇ 입니다.</span>
                    <div>
                        <img src="https://cdn3.iconfinder.com/data/icons/ranking-2/500/nim1393_15_ranking_bronze_coin-128.png" alt="">
                    </div>
                    <span>다음 배지까지 활동이 00회 남았습니다.</span>
                </div>
            </div>
        </div>
    </div>
</section>
    </div>
  `;
  modal.classList.add('modal'); // 모달 클래스 추가

  const closeBtn = document.createElement('button'); // 닫기 버튼 생성
  closeBtn.innerHTML = '&times;'; // x 표시
  closeBtn.classList.add('close'); // 닫기 버튼 클래스 추가
  closeBtn.addEventListener('click', () => {
    modal.remove(); // 모달 제거
  });

  modal.appendChild(closeBtn); // 닫기 버튼을 모달에 추가
  document.body.appendChild(modal); // 모달을 body에 추가
});
