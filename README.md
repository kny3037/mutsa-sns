<div align="center">
    <h2> ๐ฆ๋ฉ์ฌ์ค๋ค์ค (Mutsa SNS) </h2>
</div>


๋ฉ์์ด ์ฌ์์ฒ๋ผ ๋ฐฑ์๋ ์ค์ฟจ 2๊ธฐ์์ ์งํํ๋ ํ์๊ฐ์, ๋ก๊ทธ์ธ, ๊ธ์ฐ๊ธฐ, ๋๊ธ, ์ข์์, ๋ง์ด ํผ๋, ์๋ฆผ ๋ฑ์ ๊ธฐ๋ฅ์ด ์๋ SNS ์๋๋ค. 

<h5>๐ปSwagger</h5>
<hr>
http://ec2-52-78-186-104.ap-northeast-2.compute.amazonaws.com:8081/swagger-ui/

<br>
<br>

<h5>โEndPoint</h5>
<hr>
<h6>User</h6>
<li>ํ์ ๊ฐ์ (POST /api/v1/users/join)
<blockquote>
    ํ์๊ฐ์ ์ฑ๊ณต ์ "ํ์๊ฐ์ ์ฑ๊ณต" ์ ๋ฆฌํดํ๋ค. 
</blockquote>
    <ul>
        http://ec2-52-78-186-104.ap-northeast-2.compute.amazonaws.com:8081/swagger-ui/api/v1/users/join
    </ul>
</li>
    
<li>๋ก๊ทธ์ธ (POST /api/v1/users/login)
<blockquote>
Spring Security์ JWT๋ฅผ ์ฌ์ฉํ์ฌ ๊ตฌํํ๋ฉฐ ๋ก๊ทธ์ธ ์ฑ๊ณต ์ `token` ์ ๋ฆฌํดํ๋ค.
</blockquote>
    <ul>
        http://ec2-52-78-186-104.ap-northeast-2.compute.amazonaws.com:8081/swagger-ui/api/v1/users/login
    </ul>
</li>

<h6>Post</h6>
<li>๊ธ ์ฐ๊ธฐ (POST /api/v1/posts)
<blockquote>
    ํ์๋ง์ด ๊ธ ์์ฑ์ ํ  ์ ์๋ค. 
</blockquote>
    <ul>
        http://ec2-52-78-186-104.ap-northeast-2.compute.amazonaws.com:8081/swagger-ui/api/v1/posts
    </ul>
</li>

<li>๊ธ ์์ธ (GET /api/v1/posts/{postId})
<blockquote>
    ํ์, ๋นํ์ ๋ชจ๋ ๋ณผ ์ ์๊ณ  ๊ธ์ ์ ๋ชฉ, ๋ด์ฉ, ๊ธ์ด์ด, ์์ฑ๋ ์ง, ๋ง์ง๋ง ์์ ๋ ์ง๊ฐ ํ์๋๋ค.
</blockquote>
    <ul>
        http://ec2-52-78-186-104.ap-northeast-2.compute.amazonaws.com:8081/swagger-ui/api/v1/posts
    </ul>
</li>

<li>๊ธ ์์  (PUT /api/v1/posts/{postId})
<blockquote>
    ADMINํ์์ด๋ ๊ธ์ ์์ฑํ ์ผ๋ฐํ์์ด ๊ธ์ ๋ํ ์์ ์ ํ  ์ ์๋ค. 
</blockquote>
    <ul>
        http://ec2-52-78-186-104.ap-northeast-2.compute.amazonaws.com:8081/swagger-ui/api/v1/posts/1
    </ul>
</li>

<li>๊ธ ์ญ์  (DELETE /api/v1/posts/{postId})
<blockquote>
    ADMINํ์์ด๋ ๊ธ์ ์์ฑํ ์ผ๋ฐํ์์ด ๊ธ์ ๋ํ ์ญ์ ๋ฅผ ํ  ์ ์๋ค. 
</blockquote>
    <ul>
        http://ec2-52-78-186-104.ap-northeast-2.compute.amazonaws.com:8081/swagger-ui/api/v1/posts
    </ul>
</li>

<li>๊ธ ๋ฆฌ์คํธ (GET /api/v1/posts)
<blockquote>
    1. ํ์, ๋นํ์ ๋ชจ๋ ๋ณผ ์ ์์ผ๋ฉฐ ์ ๋ชฉ, ๊ธ์ด์ด, ๋ง์ง๋ง ์์ ๋ ์ง๊ฐ ํ์๋๋ค.<br>
    2. ๋ชฉ๋ก ๊ธฐ๋ฅ์ ํ์ด์ง ๊ธฐ๋ฅ์ด ํฌํจ๋๋ค. (Pageable ์ฌ์ฉ) ํ ํ์ด์ง๋น default ํผ๋ ๊ฐฏ์๊ฐ 20๊ฐ.
</blockquote>
    <ul>
        http://ec2-52-78-186-104.ap-northeast-2.compute.amazonaws.com:8081/swagger-ui/api/v1/posts/1
    </ul>
</li>

<h6>Comment</h6>
<li> ๋๊ธ ์ฐ๊ธฐ (POST /posts/{postsId}/comments)
<blockquote>
    ํ์๋ง์ด ๋๊ธ ์์ฑ์ ํ  ์ ์๋ค. 
</blockquote>
    <ul>
        http://ec2-52-78-186-104.ap-northeast-2.compute.amazonaws.com:8081/swagger-ui/api/v1/posts/1/comments
    </ul>
</li>

<li> ๋๊ธ ์์  (PUT /posts/{postId}/comments/{id})
<blockquote>
    ๋๊ธ ์์ ์ ๋๊ธ์ ์์ฑํ ํ์๋ง์ด ๊ถํ์ ๊ฐ์ง๋ค. 
</blockquote>
    <ul>
        http://ec2-52-78-186-104.ap-northeast-2.compute.amazonaws.com:8081/swagger-ui/api/v1/posts/1/comments/1
    </ul>
</li>

<li> ๋๊ธ ์ญ์  (DELETE /posts/{postsId}/comments/{id})
<blockquote>
    ๋๊ธ ์ญ์ ๋ ๋๊ธ์ ์์ฑํ ํ์๋ง์ด ๊ถํ์ ๊ฐ์ง๋ค. 
</blockquote>
    <ul>
        http://ec2-52-78-186-104.ap-northeast-2.compute.amazonaws.com:8081/swagger-ui/api/v1/posts/1/comments/1
    </ul>
</li>

<li> ๋๊ธ ๋ชฉ๋ก ์กฐํ (GET /posts/{postId}/comments[?page=0])
<blockquote>
    Pageable์ ์ฌ์ฉํ์ฌ 10๊ฐ์ฉ ์ต์ ์์ผ๋ก ํ์ด์งํ๋ค. 
</blockquote>
    <ul>
        http://ec2-52-78-186-104.ap-northeast-2.compute.amazonaws.com:8081/swagger-ui/api/v1/posts/1/comments
    </ul>
</li>

<h6>Like</h6>
<li> ์ข์์ ๋๋ฅด๊ธฐ (POST /posts/{postId}/likes)
<blockquote>
    ํ์๋ง์ด ์ข์์๋ฅผ ๋๋ฅผ ์ ์๋ค. 
</blockquote>
    <ul>
        http://ec2-52-78-186-104.ap-northeast-2.compute.amazonaws.com:8081/swagger-ui/api/v1/posts/1/likes
    </ul>
</li>

<li> ์ข์์ ๊ฐ์ (GET /posts/{postsId}/likes)
<blockquote>
  ์ข์์ ๊ฐ ์๋ฅผ ํ์ธํ  ์ ์๋ค. 
</blockquote>
    <ul>
        http://ec2-52-78-186-104.ap-northeast-2.compute.amazonaws.com:8081/swagger-ui/api/v1/posts/1/likes
    </ul>
</li>

<h6>MyFeed</h6>
<li> ์กฐํ ๊ธฐ๋ฅ  (GET /posts/my)
<blockquote>
    ๋ก๊ทธ์ธ๋ ์ ์ ๋ง์ ํผ๋๋ชฉ๋ก์ ํํฐ๋ง ํ๋ ๊ธฐ๋ฅ (Pageable ์ฌ์ฉ)
</blockquote>
    <ul>
        http://ec2-52-78-186-104.ap-northeast-2.compute.amazonaws.com:8081/swagger-ui/api/v1/posts/my
    </ul>
</li>

<h6>Alarm</h6>
<li> ์๋ ๋ฆฌ์คํธ (GET /alarms)
<blockquote>
    ๊ธ์ ์ข์์, ๋๊ธ์ด ๋ฌ๋ฆฌ๋ฉด ์๋์ด ์ค๊ณ  ์๋์ ๋ฆฌ์คํธ๋ก ํ์ธ ๊ฐ๋ฅํ ๊ธฐ๋ฅ
</blockquote>
    <ul>
        http://ec2-52-78-186-104.ap-northeast-2.compute.amazonaws.com:8081/swagger-ui/api/v1/alarms
    </ul>
</li>

<br>
<h5>๐ERD</h5>
<hr>

![2์ฐจ ERD ๋ค์ด์ด๊ทธ๋จ](https://user-images.githubusercontent.com/90165539/211471379-9c843057-4045-4211-a503-0d39e56990c7.png)



<br>
<h5>โ์ฒดํฌ ๋ฆฌ์คํธ</h5>
<hr>
      <p><input type="checkbox" value="01">AWS EC2 Docker ๋ฐฐํฌ</p>
      <p><input type="checkbox" value="02"> Swagger ์ฌ์ฉ </p>
      <p><input type="checkbox" value="03">Gitlab CI/CD ์ ์ฉ</p>
      <p><input type="checkbox" value="04">1์ฃผ์ฐจ ๊ธฐ๋ฅ ์ฝ๋ ์์ฑ</p>
      <p><input type="checkbox" value="05">1์ฃผ์ฐจ ๊ธฐ๋ฅ ํ์คํธ ์ฝ๋ ์์ฑ</p>
      <p><input type="checkbox" value="04">2์ฃผ์ฐจ ๊ธฐ๋ฅ ์ฝ๋ ์์ฑ</p>
      <p><input type="checkbox" value="05">2์ฃผ์ฐจ ๊ธฐ๋ฅ ํ์คํธ ์ฝ๋ ์์ฑ</p>

<br>
<br>

<h5>โ์ ๊ทผ ๋ฐฉ๋ฒ</h5>
<hr>
Post์์ ๊ธ ๋ฆฌ์คํธ๋ ๋ก๊ทธ์ธ์ ํ์ง ์์๋ ๊ฐ๋ฅํ๊ฒ ํ์ผ๋ฉฐ ๊ธ ์์ฑ, detail์ ๋ก๊ทธ์ธ ํ ์ฌ์ฉ์๋ง ๋ณผ ์ ์๊ฒ ์ฒ๋ฆฌํ์ต๋๋ค.<br>
update์ delete๋ ๋ก๊ทธ์ธ ํ ์ฌ์ฉ์์ ๊ธ์ ์ ์ ์ฌ์ฉ์์ ์์ด๋๊ฐ ๊ฐ์ ๊ฒฝ์ฐ์๋ง ๊ฐ๋ฅํ๋๋ก ํ์์ต๋๋ค. 

User์์๋ 
ํ์๊ฐ์์ userName๊ณผ password๋ฅผ ์๋ ฅํ์ฌ ๊ฐ์ํ๊ณ  userName์ด ๊ฒน์น  ์ DUPLICATED_USER_NAME ์๋ฌ๊ฐ ๋ฐ์๋๊ฒ ํ์์ต๋๋ค.<br> 
๋ก๊ทธ์ธ์ ํ  ๋ Spring Security์ JWT๋ฅผ ์ฌ์ฉํ์ฌ ๋ก๊ทธ์ธ์ด ์ฑ๊ณต ํ  ์์ ํ ํฐ์ ๋ฆฌํดํ๊ฒ ํ์์ต๋๋ค. 

Comment์์๋ ๋๊ธ ๋ฆฌ์คํธ๋ ๋ก๊ทธ์ธ์ ํ์ง ์์๋ ๊ฐ๋ฅํ๊ฒ ํ์ผ๋ฉฐ ๋๊ธ ์์ฑ, ์์  ๋ฐ ์ญ์ ๋ ๋ก๊ทธ์ธ ํ ์ฌ์ฉ์๋ง ๊ฐ๋ฅํ๊ฒ ์ฒ๋ฆฌํ์ต๋๋ค.

MyFeed๋ ๋ก๊ทธ์ธ ํ ์ฌ์ฉ์๊ฐ ๋ณธ์ธ์ด ์์ฑํ ๊ธ ๋ฆฌ์คํธ๋ฅผ ๋ณผ ์ ์๊ฒ๋ ํ์์ต๋๋ค. 

Alarm์ ๊ธ์ ๋ฌ๋ฆฐ ๋๊ธ, ์ข์์๋ฅผ ์๋์ผ๋ก ์ฒ๋ฆฌํ์ผ๋ฉฐ, ๋ก๊ทธ์ธ ํ ์ฌ์ฉ์๋ง ๋ณธ์ธ์ ์๋์ ํ์ธ ๊ฐ๋ฅํ๊ฒ ํ์์ต๋๋ค. 

<br>
<br>

<h5>๐ฌํน์ด์ฌํญ</h5>
<hr>
<h6>1์ฐจ ๋ฏธ์</h6>
๋ฐฐ์ ๋ ๋ด์ฉ๊ณผ ์๋ก์ด ๋ด์ฉ๋ค์ ํฉ์ณ ํผ์์ ์ฒ์๋ถํฐ ๋ง๋ค์ด๋ณด๋ ค๋ ์ฝ์ง ์์์ต๋๋ค. ๊ธฐ๋ฅ๋ค์ ๊ทธ๋๋ ์์์ ๋ค์๋ ๋ด์ฉ์ 
์๊ฐํด๋ณด๋ฉฐ ์กฐ๊ธ์ฉ ๋์ ํด๋ดค์ง๋ง Test์ฝ๋๋ ์ ๋ง ํ๋ค์์ต๋๋ค. ์์ ๋ ์ ์๋ ์ฝ๋์ ๊ฒ์์ ํ๋ฉฐ ๊ฑฐ์ ๋ค ์์ฑํ ๊ฒ ๊ฐ์
๋ค์ Test์ฝ๋๋ ํผ์์ ์ฝ๋๋ฅผ ์ง๋ณผ ์ ์๊ฒ ๋ ๋ธ๋ ฅํด์ผ๊ฒ ๋ค๋ ๋ง์์ด ๋ค์์ต๋๋ค. 

<br>

<h6>2์ฐจ ๋ฏธ์</h6>
์๋๊ณผ test ์ฝ๋๋ฅผ ์์ฑํ๋๋ฐ ๋ง์ ์ด๋ ค์์ ๊ฒช์์ต๋๋ค. ์๋กญ๊ฒ ์ ์ฉํด์ผ ํ๋ ๊ฒ๋ค์ด ๋ง์๊ณ  ์ ๋๋ก ์ดํดํ์ง ๋ชปํ๋ฉด ํ์ฉํ๊ธฐ ์ด๋ ค์ด ๊ฒ๋ค๋ ๋ง์๊ธฐ ๋๋ฌธ์ 
ํ๋ก์ ํธ๋ฅผ ์งํํ๋ฉฐ ๊ณต๋ถํ๋ ๋ด์ฉ๋ ๋ง์๋ ๊ฑฐ ๊ฐ์ต๋๋ค. 1์ฐจ ๋ฏธ์ ๋๋ณด๋ค๋ ์กฐ๊ธ ๋ ์ฝ๋์ ๊ฐ๋์ฑ์ ๋์ด๋ ค ๋ธ๋ ฅํ๊ณ  1์ฐจ ๋ฏธ์ ๋ ํ์๋ถ๋ค๊ป ๋ฆฌ๋ทฐ๋ฅผ ๋ฐ์๋ 
๋ถ๋ถ๋ค๋ ์ ์ฉํด๋ณด๋ ค ๋ธ๋ ฅํ์์ต๋๋ค. ์์ง ๋ชจ๋ฅด๋ ๊ฒ ๋ง๋ค๋ ์๊ฐ์ด ๋ค์ด ๋ ๊ณต๋ถํ๊ณ  ์ถ๋ค๋ ๋ง์์ด ๋ค์์ต๋๋ค.๐