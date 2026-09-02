# 🌐 Web 스터디 정리

## 📌 목차

- [x] HTML · CSS 기초
- [x] JavaScript · jQuery · Ajax
- [ ] JSP 기초 (문법 · 내장객체)
- [ ] JSP MVC
- [ ] Vue.js
- [ ] 배포 (AWS · 톰캣)

> 아래 각 항목을 클릭하면 상세 내용이 펼쳐집니다.

---

<details>
<summary><b>HTML · CSS 기초</b></summary>

### 1. 웹 동작 원리

```plain text
사용자(브라우저) ──── 파일 요청(URL) ────▶ 웹서버(톰캣)
                                          │ 파일 찾기 (없으면 404)
                                          │ HTML/IMAGE/CSS/JS → 바로 전송
                                          │ JSP → 자바 번역 → HTML로 변환 후 전송
              ◀──── 응답(브라우저에서 렌더링) ──────┘
```

- **웹**: HTTP 기반의 서비스(HTML 문서를 전달). JSP/PHP/ASP는 실행 시 HTML로 변환된다.
- **웹서버(톰캣)**: 요청받기 / 응답하기 + 자바를 번역해서 HTML로 변환하는 엔진 역할
- 브라우저에서 실행되는 언어는 HTML / XML (ML = MarkUp Language)

**언어별 역할**

| 언어 | 역할 |
|---|---|
| HTML / CSS | 화면 UI(정적) — 화면 디자인, 기능은 없음. 표준화되어 있어 사용자 정의 없음 |
| JavaScript | 브라우저 안에서 사용자 요청 처리(이벤트), 애니메이션·동적 페이지 |
| Java | 요청 받기/처리, 브라우저-오라클 사이의 매개 역할 (JDBC/JPA/MyBatis) |
| Oracle | 사이트에 필요한 공유 데이터 저장 (SQL) |

**웹 표준**: HTML5 + CSS3 + ES6(자바스크립트)

**HTML5의 특징**: ① 시멘틱 태그 지원(`<header>`, `<video>` 등 의미 있는 태그명) ② 멀티미디어 지원(video, audio) ③ 입력 폼 강화

**주요 HTTP 에러 코드**

| 코드 | 의미 |
|---|---|
| 404 | 요청한 파일이 없는 경우 |
| 403 | 서버 접근 거부 (Spring 보안 등) |
| 412 | 전송값이 틀린 경우 (페이지 이름 오류) |
| 415 | 한글 변환이 틀린 경우 (UTF-8 / EUC-KR) |
| 400 | POST/GET인지 확인이 안 되는 경우 |
| 500 | 자바 문법 에러 시 (JSP에서 주로 출력) |

### 2. HTML 구조

```html
<!DOCTYPE html>          <!-- HTML5 버전으로 읽기 -->
<html>                    <!-- 문서 시작 (main 같은 시작점) -->
<head>                    <!-- 설정 파일 (화면 출력 X) -->
    <meta charset="UTF-8">  <!-- 한글 설정, 검색, 화면 크기 -->
    <title>제목</title>      <!-- 브라우저 탭 이름 -->
    <!-- <script> <style> <link> -->
</head>
<body>                    <!-- 화면에 출력되는 태그 -->
    <h1>Hello HTML!!</h1>
</body>
</html>
```

> HTML은 인터프리터 언어라 한 줄씩 번역하며, 컴파일이 없어서 에러는 개발자 도구(F12) → console에서 확인한다. 태그와 속성은 모두 소문자로 작성한다.

**태그의 종류**

| 종류 | 형태 | 예시 |
|---|---|---|
| 값을 가진 태그 | `<태그>값</태그>` | `<h1>제목</h1>` |
| 값이 없는 태그 | `<태그>` | `<br>`, `<hr>` |
| 속성에 값이 있는 태그 | `<태그 속성="값">` | `<img src="cat.jpg">`, `<a href="#">` |

**inline vs block**
- block: 한 줄 전체를 차지(세로로 출력, 자동 줄바꿈) — `System.out.println()`과 비슷
- inline: 가로로 출력 — `System.out.print()`와 비슷

### 3. 자주 쓰는 태그

**문자 관련 태그**

| 태그 | 용도 |
|---|---|
| `<h1>~<h6>` | 제목 — 숫자가 클수록 글자가 작아짐, bold 포함, 주로 h1/h3 사용 (block) |
| `<br>` | 줄바꿈 (`\n`), 독립 태그 |
| `<p>` | 문단/단락 나누기 — 회원가입 폼, 카드 제목에 주로 사용 |
| `<hr>` | 수평선 (구분선), 독립 태그 |
| `<pre>` | 입력한 그대로 출력 — 상세보기(내용, 줄거리, 맛집 소개)에 사용 |

**구조/이동/입력 태그**

| 태그 | 용도 |
|---|---|
| `<a href="">` | 클릭 시 다른 페이지로 이동 |
| `<img src="">` | 이미지 출력 |
| `<button>` | 클릭 버튼 (클릭 이벤트) |
| `<input>` | 사용자 입력칸 |
| `<form>` | 입력값을 서버로 전송하는 영역 |
| `<div>` | 화면 영역을 나누는 가장 큰 상자 (JPanel처럼 카드 제작에 사용) |
| `<ul>` / `<li>` | 목록 전체 / 목록 한 줄 (메뉴 잡을 때) |
| `<table>` / `<tr>` / `<td>` | 표(게시판, 관리자 페이지) — 실무에서는 가급적 `div`+`p`로 대체 |

### 4. CSS

**CSS(Cascading Style Sheets)**: 웹 페이지 디자인을 우선순위에 따라 적용하는 언어(화면 UI). 이미 제작되어 있어서 약간 수정하는 정도로 쓰며, 무료 웹 템플릿을 수정해서 사용한다.

**CSS 적용 방법 3가지**

| 방법 | 범위 | 형태 |
|---|---|---|
| 내부 CSS | 파일 1개 제어 | `<head>` 안에 `<style>` |
| 인라인 CSS | 태그 1개 제어 | `<a style="">` |
| 외부 CSS | 여러 파일 제어 | `.css` 파일 만들고 `<link>`로 연결 |

**선택자 우선순위**

```plain text
전체(*) < 태그 < 클래스 < 아이디 < 인라인 < !important
```

**선택자 종류**

| 선택자 | 형식 | 특징 |
|---|---|---|
| 전체 선택자 | `* { }` | 모든 태그에 적용 — 간격조절(margin/padding), 글꼴 통일에 사용 |
| 태그 선택자 | `p { }` | 해당 태그에 디자인 — 중복 태그가 많아 자주는 안 씀 |
| 아이디 선택자 | `#a { }` | 중복 없는 구분자(`<p id="a">`) — 남발 방지 |
| 클래스 선택자 | `.a { }` | 같은 형식의 디자인을 여러 태그에 적용(`<a class="a">`) — **가장 많이 사용** |

> 아이디는 `태그명#아이디명`, 클래스는 `태그명.클래스명` 형태로도 쓸 수 있다. 태그(`<p>`)와 태그 선택자(`p { }`)를 구분할 줄 알아야 한다.

**자주 쓰는 CSS 속성**

```css
* { font-family: '맑은 고딕'; }   /* 시스템에 등록된 폰트, 또는 import로 구글 폰트 */
.container { margin-top: 50px; }   /* 화면에 바짝 붙지 않게 간격 */
.text-center { text-align: center; }  /* 가운데 정렬 */
p { white-space: pre-wrap; }        /* 자동 줄바꿈 */
div { background-size: cover; }     /* 이미지가 잘리지 않게 배경 채우기 */
```

</details>

<details>
<summary><b>JavaScript · jQuery · Ajax</b></summary>

### 1. 자바스크립트 자료형 (데이터 타입)

변수 안에 어떤 종류의 데이터가 들어있는지를 나타내는 것.

```javascript
let age = 20;          // 20 → 숫자
let name = "김기기";     // 김기기 → 문자
let isLogin = true;    // true → 참/거짓
```

> 자바에서는 변수를 만들 때 자료형을 먼저 적지만(`int age = 20;`), **자바스크립트에서는 자료형을 안 적는다.** 값을 보고 자료형을 자동으로 판단해준다.

| 자료형 | 특징 |
|---|---|
| Number(숫자) | 자바와 다르게 int, double, float, long 구분 없이 전부 `Number` |
| String(문자열) | `" "` 또는 `' '`로 작성. 문자와 문자열을 구분하지 않음 |
| Boolean | `true` / `false` |

### 2. jQuery · Ajax

**Ajax**: 페이지 전체를 새로고침하지 않고, 서버와 데이터만 주고받아 화면 일부만 갱신하는 방식(비동기 통신). 게시판 목록, 페이지 이동처럼 화면 일부만 바뀌는 곳에 사용한다.

> 보안이 필요한 소스는 자바스크립트 파일로 따로 빼서 작성하면, 실행 시 페이지 소스에 코드가 안 보인다. 또 중복되는 코드는 함수로 따로 만들어서 반복 코드를 제거한다.

**Ajax 기본 구조**

```javascript
function dataRecv(page) {
    $.ajax({
        type: 'post',              // 전송 방식 (post/get)
        url: 'list_ajax.do',       // 요청 보낼 서버 주소
        data: { "page": page },    // 서버로 보낼 데이터
        success: function(json) {  // 요청 성공 시 자동 호출(callback), 결과값을 매개변수로 받음
            console.log(json)      // 서버가 보낸 원본(문자열) 확인
            json = JSON.parse(json)  // 문자열 → 배열/객체로 변환
            console.log(json)      // 변환 후 배열 형태로 들어옴
            foodPrint(json)        // 받은 데이터를 화면에 출력하는 함수 호출
        }
    })
}
```

> `success`는 서버 응답이 오면 자동으로 호출되는 콜백 함수다. 서버가 보낸 값은 처음엔 문자열이라 `JSON.parse()`로 배열/객체로 바꿔야 자바스크립트에서 다룰 수 있다.

**받은 데이터를 화면에 그리기 (jQuery)**

```javascript
function foodPrint(json) {
    let html = ''
    json.forEach((food) => {           // 배열을 하나씩 순회하며 HTML 문자열 조립
        html += '<a href="#">'
              + '<div class="col-sm-3">'
              + '<img src="' + food.poster + '">'
              + '<p>' + food.name + '</p>'
              + '</div>'
              + '</a>'
    })
    $('#print').html(html)             // id가 print인 요소 안에 완성된 html을 넣음
}
```

| 문법 | 의미 |
|---|---|
| `$('#print')` | id가 `print`인 요소를 선택 (CSS 아이디 선택자와 동일) |
| `.html(내용)` | 선택한 요소 안의 HTML을 통째로 교체 |
| `json.forEach((food) => { })` | 배열의 각 요소를 하나씩 꺼내서 처리 |

**페이지네이션 예시 — 서버가 준 페이지 정보로 페이지 버튼 만들기**

```javascript
let curpage   = json[0].curpage
let totalpage = json[0].totalpage
let startPage = json[0].startPage
let endPage   = json[0].endPage

let pagePrint = '<ul class="pagination">'
if (startPage > 1) {
    pagePrint += '<li><a class="link" onclick="prev(' + (startPage - 1) + ')">&laquo;</a></li>'
}
for (let i = startPage; i <= endPage; i++) {
    pagePrint += '<li><a class="link" onclick="change(' + i + ')">' + i + '</a></li>'
}
if (endPage < totalpage) {
    pagePrint += '<li><a class="link" onclick="next(' + (endPage + 1) + ')">&raquo;</a></li>'
}
pagePrint += '</ul>'
$('#pagination').html(pagePrint)
```

> `list.jsp`에서 `<c:forEach>`로 만들던 페이지 목록을, Ajax 방식에서는 자바스크립트 `for`문으로 만들어서 화면에 넣는다. 이전(prev)·다음(next)·특정 페이지(change) 버튼은 모두 같은 `dataRecv(page)` 함수를 호출해서 중복 코드를 제거했다.

**window.onload — 페이지 로드 시 자동 실행**

```javascript
window.onload = () => {
    dataRecv(1)    // 화면이 처음 열리면 1페이지 데이터를 자동으로 불러옴
}
```

</details>
