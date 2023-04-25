let $content = $('#main-content');
let $issueType = $('#request_issue_type_select');
// select[name=request[ticket_form_id]]에서 선택된 값 selected 추가
// $("select[name='request[ticket_form_id]']").change(function() {
//     $("select[name='request[ticket_form_id]']").find($("option").prop('selected', true)).prop('selected', false);
//     console.log($(this));
//     $(this).prop('selected', true);
//     $('.nesty-input').text($(this).text()); 
// });

$issueType.change(function(e) {
    var selectedText = $(this).find("option:selected").text();
    console.log(selectedText);
    $('#request_ticket_form_id').text(selectedText);
})


const $text = $content.find('.nesty-input').text()
 if($text === '-') {

 }

 const mainContent = () => {
    function normal() {
        var text = "";
        text += `
            <form id="new_request" class="request-form" data-form="" data-form-type="request" action="/hc/ko/requests" accept-charset="UTF-8" method="post"><input name="utf8" type="hidden" value="✓" autocomplete="off">
                <div class="form-field select optional request_ticket_form_id">
                    <label for="request_issue_type_select">문의 접수하실 내용을 선택해주세요:)</label>
                    <a class="nesty-input" tabindex="0" aria-expanded="true" aria-activedescendant="-" aria-label="문의 접수하실 내용을 선택해주세요:)" style="max-width: 96%;">
                        -
                    </a>
                    <select name="request[ticket_form_id]" id="request_issue_type_select" aria-label="문의 접수하실 내용을 선택해주세요:)" autofocus="autofocus" style="display: none;">
                        <option data-url="" value="-">-</option>
                        <option data-url="" value="">펀딩 서비스 문의하기</option>
                        <option data-url="" value="">스토어 서비스 문의하기</option>
                        <option data-url="" value="">파이낸스 서비스 문의하기</option>
                    </select>
                </div>
            </form>
        `;
        return text;
    }

    function option1() {
        var text = "";
        text += `
            <form id="new_request" class="request-form" data-form="" data-form-type="request" action="/hc/ko/requests" accept-charset="UTF-8" method="post"><input name="utf8" type="hidden" value="✓" autocomplete="off">
                <input name="utf8" type="hidden" value="✓" autocomplete="off">
                <div class="form-field select optional request_ticket_form_id">
                    <label for="request_issue_type_select">문의 접수하실 내용을 선택해주세요:)</label>
                    <a class="nesty-input" tabindex="0" aria-expanded="true" aria-activedescendant="-" aria-label="문의 접수하실 내용을 선택해주세요:)" style="max-width: 96%;">
                        -
                    </a>
                    <select name="request[ticket_form_id]" id="request_issue_type_select" aria-label="문의 접수하실 내용을 선택해주세요:)" autofocus="autofocus" style="display: none;">
                        <option data-url="" value="-">-</option>
                        <option data-url="" value="">펀딩 서비스 문의하기</option>
                        <option data-url="" value="">스토어 서비스 문의하기</option>
                        <option data-url="" value="">파이낸스 서비스 문의하기</option>
                    </select>
                </div>
            </form>
        `;
        return text;
    }


 }