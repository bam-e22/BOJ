* 지원자 정보
- 이름 : 권병수
- Email : stack.07142@gmail.com

* 외부 라이브러리 사용
- 이름 : GSON
- url : https://github.com/google/gson
- 목적 : JSON 파싱

* 구현 내용
제출용 토큰을 받아온다 (Token API)
    if Token API 요청의 responseCode가 200 이면
        카테고리별 문서의 URL을 얻어온다 (Seed API)
            각 카테고리에 대하여 문서를 수집한다 (Document API)
                if (next_url == 현재 url) && 현재 문서의 image가 비어있는 경우
                    재요청한다(Document API)
                else 
                    현재 카테고리의 next_url에 대하여 문서를 수집한다

            수집한 images에 대하여 중복을 제거하고 Feature를 추출한다 (Feature Extraction API)
            Featrue를 서버에 저장한다(Feature Save API)
	    Feature를 서버에서 삭제한다(Feature Delete API)





















                


    