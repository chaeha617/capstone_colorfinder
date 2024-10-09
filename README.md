<p align="center">
  <img src="https://github.com/chaeha617/capstone_colorfinder/blob/master/README/logo.png" alt="로고" width="70%" />
</p>

# ColorFinder 🎨
안면 색상 데이터 기반 퍼스널 컬러 진단 및
맞춤형 의류 추천 쇼핑몰

<hr style="border: 2px solid #d3d3d3; margin: 20px 0;">

## 참여 인원
<a href="https://github.com/chaeha617" align="center">
  <img src="https://img.shields.io/badge/chaeha617-e67c7c?style=flat-square"/>
</a>
<a href="https://github.com/go-ring" align="center">
  <img src="https://img.shields.io/badge/goring-7dd600?style=flat-square"/>
</a>
<a href="https://github.com/kimju-hee" align="center">
  <img src="https://img.shields.io/badge/kimjuhee-ed61e6?style=flat-square"/>
</a>

<hr style="border: 2px solid #d3d3d3; margin: 20px 0;">


<h2> ☁️ 문제 상황 </h2>

요즘 의류에 대한 관심이 폭발하면서, 의류 소비도 꾸준히 증가하고 있습니다. 거기에 <span style="color: #1E90FF;">퍼스널 컬러</span>가 대세로 떠오르면서, 나만의 완벽한 색을 찾는 게 트렌드게 되었습니다. 그런데 혹시 '톤그로'라는 말을 들어보셨나요? 어울리지 않는 색을 입었을 때 그 어색한 느낌을 표현한 신조어입니다! 😅

그래서 저희는 여러분이 쇼핑하면서 어울리지 않는 옷 때문에 고민하지 않게, <span style="color: #1E90FF;">의류 소비 만족도</span>를 제대로 높여줄 서비스를 개발하였습니다. 이제 어색한 색상 때문에 고통받지 말고, 나만의 찰떡 컬러로 자신감을 업그레이드해보세요! 💪


<hr style="border: 2px solid #d3d3d3; margin: 20px 0;">

## 사용 기술

#### 언어
<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/python-3776AB?style=for-the-badge&logo=python&logoColor=white">

#### 서버
<img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> <img src="https://img.shields.io/badge/flask-000000?style=for-the-badge&logo=flask&logoColor=white">

#### DB
<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">

#### 형상관리
<img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">

<hr style="border: 2px solid #d3d3d3; margin: 20px 0;">


<h2> ✨ 주요 기능 </h2>

* * *

### 회원가입 & 로그인
<div style="text-align: center;">
  <img src="https://github.com/chaeha617/capstone_colorfinder/blob/master/README/회원가입.png" alt="회원가입" width="40%" style="margin: 0 10px;" />
  <img src="https://github.com/chaeha617/capstone_colorfinder/blob/master/README/로그인.png" alt="로그인" width="40%" style="margin: 0 10px;" /></div>

<p>이메일, 비밀번호, 닉네임, 퍼스널 컬러, 성별 정보를 받아 회원 가입을 진행합니다.<br>
회원가입 시 퍼스널 컬러 진단, 맞춤형 의류 추천 서비스 사용이 가능합니다.<br>
퍼스널 컬러와 성별을 선택하지 않은 채 회원가입 진행도 가능합니다.</p>

* * *

### 퍼스널 컬러 진단
<div style="text-align: center;">
  <img src="https://github.com/chaeha617/capstone_colorfinder/blob/master/README/진단1.png" alt="진단1" width="40%" style="margin: 0 10px;" />
  <img src="https://github.com/chaeha617/capstone_colorfinder/blob/master/README/진단2.png" alt="진단2" width="40%" style="margin: 0 10px;" /></div>

<p>사용자는 이미지 파일을 업로드하여 퍼스널 컬러 진단을 진행할 수 있습니다.<br>
봄 웜톤, 여름 쿨톤, 가을 웜톤, 겨울 쿨톤의 4가지 퍼스널 컬러로 진단됩니다.<br>
진단 결과를 저장할 경우, 유저 정보에 해당 정보가 저장됩니다.</p>

* * *

### 사용자 정보 맞춤형 의류 추천
<p>퍼스널 컬러, 기온 정보를 기반으로 한 의류 추천 기능입니다.</p>

<img src="https://github.com/chaeha617/capstone_colorfinder/blob/master/README/추천1.png" alt="추천1" width="40%" style="margin: 0 10px;" />
<p>퍼스널 컬러 정보가 없을 경우, 비로그인 상태일 경우 기온 정보로 추천합니다.</p>

<img src="https://github.com/chaeha617/capstone_colorfinder/blob/master/README/추천2.png" alt="추천2" width="40%" style="margin: 0 10px;" />
<p>퍼스널 컬러 정보가 있을 경우, 퍼스널 컬러와 기온 정보로 추천합니다.</p>

![gif](https://github.com/chaeha617/capstone_colorfinder/blob/master/README/영상.gif)

* * *

### 의류 필터링 & 정렬
<p>모든 의류는 퍼스널 컬러, 카테고리 별로 필터링이 가능합니다.<br>
인기순, 최신순, 저가순, 고가순으로 정렬이 가능합니다.
</p>

<img src="https://github.com/chaeha617/capstone_colorfinder/blob/master/README/의류1.png" alt="의류1" width="40%" style="margin: 0 10px;" />

* * *

### 장바구니 & 구매하기
<p>장바구니 버튼을 눌렀을 경우 제품 이름, 수량, 사이즈 등의 정보가 장바구니에 들어갑니다.<br>
구매하기 기능을 진행할 경우 선택한 상품들 주문 목록에 추가됩니다.
</p>

<div style="text-align: center;">
  <img src="https://github.com/chaeha617/capstone_colorfinder/blob/master/README/장바구니.png" alt="장바구니" width="40%" style="margin: 0 10px;" />
<img src="https://github.com/chaeha617/capstone_colorfinder/blob/master/README/구매하기.png" alt="구매하기" width="40%" style="margin: 0 10px;" />
</div>

<hr style="border: 2px solid #d3d3d3; margin: 20px 0;">

# [📜Notion](https://luxuriant-operation-960.notion.site/ColorFinder-10e82ee6f50a80cf9fbcfc06deac29e5?pvs=4)
