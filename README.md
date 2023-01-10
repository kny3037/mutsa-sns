<div align="center">
    <h2> 🦁멋사스네스 (Mutsa SNS) </h2>
</div>


멋쟁이 사자처럼 백엔드 스쿨 2기에서 진행하는 회원가입, 로그인, 글쓰기, 댓글, 좋아요, 마이 피드, 알림 등의 기능이 있는 SNS 입니다. 

<h5>💻Swagger</h5>
<hr>
http://ec2-52-78-186-104.ap-northeast-2.compute.amazonaws.com:8081/swagger-ui/

<br>
<br>

<h5>❗EndPoint</h5>
<hr>
<h6>User</h6>
<li>회원 가입 (POST /api/v1/users/join)
<blockquote>
    회원가입 성공 시 "회원가입 성공" 을 리턴한다. 
</blockquote>
    <ul>
        http://ec2-52-78-186-104.ap-northeast-2.compute.amazonaws.com:8081/swagger-ui/api/v1/users/join
    </ul>
</li>
    
<li>로그인 (POST /api/v1/users/login)
<blockquote>
Spring Security와 JWT를 사용하여 구현하며 로그인 성공 시 `token` 을 리턴한다.
</blockquote>
    <ul>
        http://ec2-52-78-186-104.ap-northeast-2.compute.amazonaws.com:8081/swagger-ui/api/v1/users/login
    </ul>
</li>

<h6>Post</h6>
<li>글 쓰기 (POST /api/v1/posts)
<blockquote>
    회원만이 글 작성을 할 수 있다. 
</blockquote>
    <ul>
        http://ec2-52-78-186-104.ap-northeast-2.compute.amazonaws.com:8081/swagger-ui/api/v1/posts
    </ul>
</li>

<li>글 상세 (GET /api/v1/posts/{postId})
<blockquote>
    회원, 비회원 모두 볼 수 있고 글의 제목, 내용, 글쓴이, 작성날짜, 마지막 수정날짜가 표시된다.
</blockquote>
    <ul>
        http://ec2-52-78-186-104.ap-northeast-2.compute.amazonaws.com:8081/swagger-ui/api/v1/posts
    </ul>
</li>

<li>글 수정 (PUT /api/v1/posts/{postId})
<blockquote>
    ADMIN회원이나 글을 작성한 일반회원이 글에 대한 수정을 할 수 있다. 
</blockquote>
    <ul>
        http://ec2-52-78-186-104.ap-northeast-2.compute.amazonaws.com:8081/swagger-ui/api/v1/posts/1
    </ul>
</li>

<li>글 삭제 (DELETE /api/v1/posts/{postId})
<blockquote>
    ADMIN회원이나 글을 작성한 일반회원이 글에 대한 삭제를 할 수 있다. 
</blockquote>
    <ul>
        http://ec2-52-78-186-104.ap-northeast-2.compute.amazonaws.com:8081/swagger-ui/api/v1/posts
    </ul>
</li>

<li>글 리스트 (GET /api/v1/posts)
<blockquote>
    1. 회원, 비회원 모두 볼 수 있으며 제목, 글쓴이, 마지막 수정날짜가 표시된다.<br>
    2. 목록 기능은 페이징 기능이 포함된다. (Pageable 사용) 한 페이지당 default 피드 갯수가 20개.
</blockquote>
    <ul>
        http://ec2-52-78-186-104.ap-northeast-2.compute.amazonaws.com:8081/swagger-ui/api/v1/posts/1
    </ul>
</li>

<h6>Comment</h6>
<li> 댓글 쓰기 (POST /posts/{postsId}/comments)
<blockquote>
    회원만이 댓글 작성을 할 수 있다. 
</blockquote>
    <ul>
        http://ec2-52-78-186-104.ap-northeast-2.compute.amazonaws.com:8081/swagger-ui/api/v1/posts/1/comments
    </ul>
</li>

<li> 댓글 수정 (PUT /posts/{postId}/comments/{id})
<blockquote>
    댓글 수정은 댓글을 작성한 회원만이 권한을 가진다. 
</blockquote>
    <ul>
        http://ec2-52-78-186-104.ap-northeast-2.compute.amazonaws.com:8081/swagger-ui/api/v1/posts/1/comments/1
    </ul>
</li>

<li> 댓글 삭제 (DELETE /posts/{postsId}/comments/{id})
<blockquote>
    댓글 삭제는 댓글을 작성한 회원만이 권한을 가진다. 
</blockquote>
    <ul>
        http://ec2-52-78-186-104.ap-northeast-2.compute.amazonaws.com:8081/swagger-ui/api/v1/posts/1/comments/1
    </ul>
</li>

<li> 댓글 목록 조회 (GET /posts/{postId}/comments[?page=0])
<blockquote>
    Pageable을 사용하여 10개씩 최신순으로 페이징한다. 
</blockquote>
    <ul>
        http://ec2-52-78-186-104.ap-northeast-2.compute.amazonaws.com:8081/swagger-ui/api/v1/posts/1/comments
    </ul>
</li>

<h6>Like</h6>
<li> 좋아요 누르기 (POST /posts/{postId}/likes)
<blockquote>
    회원만이 좋아요를 누를 수 있다. 
</blockquote>
    <ul>
        http://ec2-52-78-186-104.ap-northeast-2.compute.amazonaws.com:8081/swagger-ui/api/v1/posts/1/likes
    </ul>
</li>

<li> 좋아요 개수 (GET /posts/{postsId}/likes)
<blockquote>
  좋아요 개 수를 확인할 수 있다. 
</blockquote>
    <ul>
        http://ec2-52-78-186-104.ap-northeast-2.compute.amazonaws.com:8081/swagger-ui/api/v1/posts/1/likes
    </ul>
</li>

<h6>MyFeed</h6>
<li> 조회 기능  (GET /posts/my)
<blockquote>
    로그인된 유저만의 피드목록을 필터링 하는 기능 (Pageable 사용)
</blockquote>
    <ul>
        http://ec2-52-78-186-104.ap-northeast-2.compute.amazonaws.com:8081/swagger-ui/api/v1/posts/my
    </ul>
</li>

<h6>Alarm</h6>
<li> 알람 리스트 (GET /alarms)
<blockquote>
    글에 좋아요, 댓글이 달리면 알람이 오고 알람을 리스트로 확인 가능한 기능
</blockquote>
    <ul>
        http://ec2-52-78-186-104.ap-northeast-2.compute.amazonaws.com:8081/swagger-ui/api/v1/alarms
    </ul>
</li>

<br>
<h5>📑ERD</h5>
<hr>

![2차 ERD 다이어그램](https://user-images.githubusercontent.com/90165539/211471379-9c843057-4045-4211-a503-0d39e56990c7.png)



<br>
<h5>✅체크 리스트</h5>
<hr>
      <p><input type="checkbox" value="01">AWS EC2 Docker 배포</p>
      <p><input type="checkbox" value="02"> Swagger 사용 </p>
      <p><input type="checkbox" value="03">Gitlab CI/CD 적용</p>
      <p><input type="checkbox" value="04">1주차 기능 코드 작성</p>
      <p><input type="checkbox" value="05">1주차 기능 테스트 코드 작성</p>
      <p><input type="checkbox" value="04">2주차 기능 코드 작성</p>
      <p><input type="checkbox" value="05">2주차 기능 테스트 코드 작성</p>

<br>
<br>

<h5>❓접근 방법</h5>
<hr>
Post에서 글 리스트는 로그인을 하지 않아도 가능하게 했으며 글 작성, detail은 로그인 한 사용자만 볼 수 있게 처리했습니다.<br>
update와 delete는 로그인 한 사용자와 글을 적은 사용자의 아이디가 같을 경우에만 가능하도록 하였습니다. 

User에서는 
회원가입은 userName과 password를 입력하여 가입하고 userName이 겹칠 시 DUPLICATED_USER_NAME 에러가 발생되게 하였습니다.<br> 
로그인을 할 때 Spring Security와 JWT를 사용하여 로그인이 성공 할 시에 토큰을 리턴하게 하였습니다. 

Comment에서는 댓글 리스트는 로그인을 하지 않아도 가능하게 했으며 댓글 작성, 수정 및 삭제는 로그인 한 사용자만 가능하게 처리했습니다.

MyFeed는 로그인 한 사용자가 본인이 작성한 글 리스트를 볼 수 있게끔 하였습니다. 

Alarm은 글에 달린 댓글, 좋아요를 알람으로 처리했으며, 로그인 한 사용자만 본인의 알람을 확인 가능하게 하였습니다. 

<br>
<br>

<h5>💬특이사항</h5>
<hr>
<h6>1차 미션</h6>
배웠던 내용과 새로운 내용들을 합쳐 혼자서 처음부터 만들어보려니 쉽지 않았습니다. 기능들은 그래도 수업을 들었던 내용을 
생각해보며 조금씩 도전해봤지만 Test코드는 정말 힘들었습니다. 수업 때 적었던 코드와 검색을 하며 거의 다 작성한 것 같아
다음 Test코드는 혼자서 코드를 짜볼 수 있게 더 노력해야겠다는 마음이 들었습니다. 

<br>

<h6>2차 미션</h6>
알람과 test 코드를 작성하는데 많은 어려움을 겪었습니다. 새롭게 적용해야 하는 것들이 많았고 제대로 이해하지 못하면 활용하기 어려운 것들도 많았기 때문에 
프로젝트를 진행하며 공부했던 내용도 많았던 거 같습니다. 1차 미션 때보다는 조금 더 코드의 가독성을 높이려 노력했고 1차 미션 때 팀원분들께 리뷰를 받았던 
부분들도 적용해보려 노력하였습니다. 아직 모르는 게 많다는 생각이 들어 더 공부하고 싶다는 마음이 들었습니다.😊