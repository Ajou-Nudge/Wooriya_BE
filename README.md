# API Docs

| Method | URI                      | Description           |
|--------|--------------------------|-----------------------|
| POST   | /companypost/post        | postCompanyPost       |
| POST   | /grouppost/post          | postGroupPost         |
| POST   | /companypost/update/{id} | updateCompanyPost     |
| POST   | /grouppost/update/{id}   | updateGroupPost       |
| POST   | /imageupload             | postPostImage         |
| GET    | /image/{pathString}      | gettPostImage         |
| GET    | /companypost             | getAllCompanyPost     |
| GET    | /grouppost               | getAllGroupPost       |
| GET    | /companypost/{id}        | getCompanyPostById    |
| GET    | /grouppost/{id}          | getGroupPostById      |
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
  "companyName" : "testCoName",
  "coType" : "test",
  "coSize" : 100
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
  "groupName" : "testGroupName",
  "coType" : "test",
  "coSize" : 100
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
  "coSize" : 100
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
  "groupName" : "testGroupName",
  "coType" : "test",
  "coSize" : 100
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
    "companyName": "testCoName",
    "coType": "test",
    "coSize": 100
  },
  {
    "id": 2,
    "title": "test",
    "companyName": "testCoName",
    "coType": "test",
    "coSize": 100
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
    "groupName": "testGroupName",
    "coType": "test",
    "coSize": 100
  },
  {
    "id": 2,
    "title": "test",
    "groupName": "testGroupName",
    "coType": "test",
    "coSize": 100
  },
  {
    "id": 3,
    "title": "test",
    "groupName": "testGroupName",
    "coType": "test",
    "coSize": 100
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
  "groupName": "testGroupName",
  "coType": "testCoType",
  "coSize": 100,
  "body": "testBody"
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

