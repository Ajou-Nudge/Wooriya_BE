# API Docs

| Method | URI                      | Description           |
|--------|--------------------------|-----------------------|
| POST   | /companypost/post        | postCompanyPost       |
| POST   | /grouppost/post          | postGroupPost         |
| POST   | /companypost/update/{id} | updateCompanyPost     |
| POST   | /grouppost/update/{id}   | updateGroupPost       |
| POST   | /imageupload             | postPostImage         |
| POST   | /user/join               | postUserJoin          |
| POST   | /user/login              | postUserLogin         |
| GET    | /image/{pathString}      | gettPostImage         |
| GET    | /companypost             | getAllCompanyPost     |
| GET    | /grouppost               | getAllGroupPost       |
| GET    | /companypost/{id}        | getCompanyPostById    |
| GET    | /grouppost/{id}          | getGroupPostById      |
| GET    | /user/info               | getUserInfo           |
| DELETE | /companypost/delete/{id} | deleteCompanyPostById |
| DELETE | /grouppost/delete/{id}   | deleteCompanyPostById |


## [POST] postCompanyPost

URI

```
/companypost/post
```

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

## [POST] postGroupPost

URI

```
/grouppost/post
```

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

## [POST] updateCompanyPost
URI

```
/companypost/update/{id}
```

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

## [POST] updateGroupPost

URI

```
/grouppost/update/{id}
```

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

## [POST] postPostImage

URI

```
/imageupload
```

req.body
```formdata
{
  img : [FILE]
}
```

res.body
```json
[pathString]
```

## [POST] postUserJoin

URI

```
/user/join
```

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

## [POST] postUserLogin

URI

```
/user/login
```

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

## [GET] getPostImage

URI

```
/image/{pathString}
```

res.body
```json
[FILE]
```

## [GET] getAllCompanyPost

URI

```
/companypost
```

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

## [GET] getAllGroupPost

URI

```
/grouppost
```

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

## [GET] getCompanyPostById

URI

```
/grouppost/{id}
```

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

## [GET] getGroupPostById

URI

```
/grouppost/{id}
```

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

## [GET] getUserInfo

URI

```
/user/info
```

res.body

```json
{
    "email": "testId",
    "memberRole": "COMPANY"
}
```

## [DELETE] deleteCompanyPostById

URI

```
/companypost/delete/{id}
```

## [DELETE] deleteGroupPostById

URI

```
/grouppost/delete/{id}
```

