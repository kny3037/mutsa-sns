<div align="center">
    <h2> 멋사스네스 (Mutsa SNS) </h2>
</div>

<hr>

멋쟁이 사자처럼 백엔드 스쿨 2기에서 진행하는 글쓰기, 로그인, 피드 등의 기능이 있는 SNS 입니다. 

<h5>💻Swagger</h5>
<hr>
<a>http://ec2-52-78-186-104.ap-northeast-2.compute.amazonaws.com:8081/swagger-ui/</a>

<br>
<br>

<h5>❗EndPoint</h5>
<hr>
<h6>User</h6>
<li>회원 가입 (Post /api/v1/users/join)
<blockquote>
    회원가입 성공 시 "회원가입 성공" 을 리턴한다. 
</blockquote>
    <ul>
        http://ec2-52-78-186-104.ap-northeast-2.compute.amazonaws.com:8081/swagger-ui/api/v1/users/join
    </ul>
</li>
    
<li>로그인 (Post /api/v1/users/login)
<blockquote>
Spring Security와 JWT를 사용하여 구현하며 로그인 성공 시 `token` 을 리턴한다.
</blockquote>
    <ul>
        http://ec2-52-78-186-104.ap-northeast-2.compute.amazonaws.com:8081/swagger-ui/api/v1/users/login
    </ul>
</li>

<h6>Post</h6>
<li>글 쓰기 (Post /api/v1/posts)
<blockquote>
    회원만이 글 작성을 할 수 있다. 
</blockquote>
    <ul>
        http://ec2-52-78-186-104.ap-northeast-2.compute.amazonaws.com:8081/swagger-ui/api/v1/posts
    </ul>
</li>

<li>글 상세 (Get /api/v1/posts)
<blockquote>
    회원, 비회원 모두 볼 수 있고 글의 제목, 내용, 글쓴이, 작성날짜, 마지막 수정날짜가 표시된다.
</blockquote>
    <ul>
        http://ec2-52-78-186-104.ap-northeast-2.compute.amazonaws.com:8081/swagger-ui/api/v1/posts
    </ul>
</li>

<li>글 수정 (Put /api/v1/posts/{postId})
<blockquote>
    ADMIN회원이나 글을 작성한 일반회원이 글에 대한 수정을 할 수 있다. 
</blockquote>
    <ul>
        http://ec2-52-78-186-104.ap-northeast-2.compute.amazonaws.com:8081/swagger-ui/api/v1/posts/1
    </ul>
</li>

<li>글 삭제 (Delete /api/v1/posts)
<blockquote>
    ADMIN회원이나 글을 작성한 일반회원이 글에 대한 삭제를 할 수 있다. 
</blockquote>
    <ul>
        http://ec2-52-78-186-104.ap-northeast-2.compute.amazonaws.com:8081/swagger-ui/api/v1/posts
    </ul>
</li>

<li>글 리스트 (Get /api/v1/posts/{postId})
<blockquote>
    1. 회원, 비회원 모두 볼 수 있으며 제목, 글쓴이, 마지막 수정날짜가 표시된다.<br>
    2. 목록 기능은 페이징 기능이 포함된다. (Pageable 사용) 한 페이지당 default 피드 갯수가 20개.
</blockquote>
    <ul>
        http://ec2-52-78-186-104.ap-northeast-2.compute.amazonaws.com:8081/swagger-ui/api/v1/posts/1
    </ul>
</li>
<br>
<br>
<h5>✅체크 리스트</h5>
<hr>
<form method="POST">
      <p><label><input type="checkbox" value="01">AWS EC2 Docker 배포</label></p>
      <p><label><input type="checkbox" value="02"> Swagger 사용 </label></p>
      <p><label><input type="checkbox" value="03">Gitlab CI/CD 적용</label></p>
      <p><label><input type="checkbox" value="04">1주차 기능 코드 작성</label></p>
      <p><label><input type="checkbox" value="05">1주차 기능 테스트 코드 작성</label></p>
    </form>
<br>
<br>

<h5>❓접근 방법</h5>
<hr>


<br>
<br>

<h5>💬특이사항</h5>
<hr>
