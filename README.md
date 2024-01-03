# Wooriya_BE

## API Docs

| Method | URI                                                                           | Description                                        |
|--------|-------------------------------------------------------------------------------|----------------------------------------------------|
| POST   | [/companypost/post](#post-companypostpost)                                    | make post for company                              |
| POST   | [/grouppost/post](#post-grouppostpost)                                        | make post for group                                |
| POST   | [/companypost/update/{id}](#post-companypostupdateid)                         | update post for company                            |
| POST   | [/grouppost/update/{id}](#post-grouppostupdateid)                             | update post for group                              |
| GET    | [/companypost](#get-companypost)                                              | get all posts for company                          |
| GET    | [/grouppost](#get-grouppost)                                                  | get all posts for group                            |
| GET    | [/companypost/{id}](#get-companypostid)                                       | get post for company using id                      |
| GET    | [/grouppost/{id}](#get-grouppostid)                                           | get post for group using id                        |
| DELETE | [/companypost/delete/{id}](#delete-companypostdeleteid)                       | delete post for company using id                   |
| DELETE | [/grouppost/delete/{id}](#delete-grouppostdeleteid)                           | delete post for group using id                     |
| POST   | [/img/upload](#post-imgupload)                                                | upload image url and create metadata for post      |
| POST   | [/sign/upload](#post-signupload)                                              | upload image url and create metadata for signature |
| GET    | [/sign/delete](#get-signdelete)                                               | delete image metadata for signature                |
| POST   | [/user/join](#post-userjoin)                                                  | user register                                      |
| POST   | [/user/login](#post-userlogin)                                                | user login                                         |
| POST   | [/user/join/confirmcode](#post-userjoinconfirmcode)                           | send confirmcode for user register                 |
| GET    | [/user/join/confirm-mail/{confirmcode}](#get-userjoinconfirm-mailcomfirmcode) | check confirmcode for user register                |
| GET    | [/user/info](#get-userinfo)                                                   | get user info                                      |


### [POST] /companypost/post

make a post for company

req.body
```json
{
  "title" : "test",
  "coType" : "test",
  "coSize" : 100,
  "body" : "testBody"
}
```

res.body
```json
1
```

### [POST] /grouppost/post

make post for group

req.body
```json
{
  "title" : "test",
  "coType" : "test",
  "coSize" : 100,
  "body" : "testBody"
}
```

res.body
```json
1
```

### [POST] /companypost/update/{id}

update post for company

req.body
```json
{
  "title" : "testModify",
  "companyName" : "testGroupName",
  "coType" : "test",
  "coSize" : 100,
  "body" : "testBody"
}
```

res.body
```json
1
```

### [POST] /grouppost/update/{id}

update post for group

req.body
```json
{
  "title" : "testModify",
  "coType" : "test",
  "coSize" : 100,
  "body" : "testBody"
}
```

res.body
```json
1
```

### [GET] /companypost

get all posts for company

res.body

```json
[
  {
    "id": 1,
    "title": "test",
    "authorId" : "testname",
    "companyName": "testCoName",
    "coType": "test",
    "coSize": 100,
    "body" : "testBody"
  },
  {
    "id": 2,
    "title": "test",
    "authorId" : "testname",
    "companyName": "testCoName",
    "coType": "test",
    "coSize": 100,
    "body" : "testBody"
  }
]
```

### [GET] /grouppost

get all posts for group

res.body

```json
[
  {
    "id": 1,
    "title": "test",
    "authorId" : "testname",
    "groupName": "testGroupName",
    "coType": "test",
    "coSize": 100,
    "body": "testBody"
  },
  {
    "id": 2,
    "title": "test",
    "authorId" : "testname",
    "groupName": "testGroupName",
    "coType": "test",
    "coSize": 100,
    "body": "testBody"
  },
  {
    "id": 3,
    "title": "test",
    "authorId" : "testname",
    "groupName": "testGroupName",
    "coType": "test",
    "coSize": 100,
    "body": "testBody"
  }
]
```

### [GET] /companypost/{id}

get post for company using id

res.body

```json
{
  "id": 3,
  "title": "testTitle",
  "authorId" : "testname",
  "companyName": "testCompanyName",
  "coType": "testCoType",
  "coSize": 100,
  "body": "testBody"
}
```

### [GET] /grouppost/{id}

get post for group using id

res.body

```json
{
  "id": 1,
  "title": "testTitle",
  "authorId" : "testname",
  "groupName": "testGroupName",
  "coType": "testCoType",
  "coSize": 100,
  "body": "testBody"
}
```

### [DELETE] /companypost/delete/{id}

delete post for company using id

### [DELETE] /grouppost/delete/{id}

delete post for group using id

### [POST] /img/upload

upload image url and create metadata for post

req.body
```json
{
  "s3Url" : [S3 URL]
}
```

res.body
```json
[S3 URL]
```

### [POST] /sign/upload

upload image url and create metadata for signature

req.body
```json
{
  "s3Url" : [S3 URL]
}
```

res.body
```json
[S3 URL]
```

### [POST] /sign/delete

delete image metadata for signature

req.body
```json
{
  "s3Url" : [S3 URL]
}
```

res.body
```json
"삭제 완료"
```

### [POST] /user/login

user login

req.body
```json
{
    "email" : "testId",
    "password" : "testPw"
}
```

res.body
```json
{
    "grantType": "Bearer",
    "accessToken": "",
    "refreshToken": "",
    "email": "testemail@email.com",
    "memberRole": "ROLE"
}
```

### [POST] /user/join

user register

req.body
```json
{
    "email" : "testId",
    "password" : "testPw",
    "role" : "COMPANY",
    "userName" : "testName",
    "userNum" : 11111111
}
```

res.body
```json
COMPANY
```

### [POST] /user/join/confirmcode

send confirmcode for user register

req.body
```json
sungyeon52@gmail.com
```

res.body
```json
인증코드 전송 완료
```

### [GET] /user/join/confirm-mail/{confirmCode}

check confirmcode for user register

res.body
```json
이메일 인증 완료
```

### [GET] /user/info

get user info

res.body

```json
{
    "email": "testId",
    "memberRole": "COMPANY"
}
```

