# REST API

| Method | URI               | Description        |
|--------|-------------------|--------------------|
| POST   | /companypost/post | postCompanyPost    |
| POST   | /grouppost/post   | postGroupPost      |
| POST   | /imageupload      | postPostImage      |
| GET    | /companypost      | getAllCompanyPost  |
| GET    | /grouppost        | getAllGroupPost    |
| GET    | /companypost/{id} | getCompanyPostById |
| GET    | /grouppost/{id}   | getGroupPostById   |

# Issuer

## postCompanyPost

### URI

```
/companypost/post
```

### req.body
```json
{
  "title" : "test",
  "companyName" : "testCoName",
  "coType" : "test",
  "coSize" : 100
}
```

### res.body
```json
1
```

## postGroupPost

### URI

```
/grouppost/post
```

### req.body
```json
{
  "title" : "test",
  "groupName" : "testGroupName",
  "coType" : "test",
  "coSize" : 100
}
```

### res.body
```json
1
```

## postPostImage

### URI

```
/imageupload
```

### req.body
```formdata
{
  img : [FILE]
}
```

### res.body
```json
[pathString]
```

## getPostImane

### URI

```
/image/{pathString}
```

### res.body
```json
[FILE]
```

## getAllCompanyPost

### URI

```
/companypost
```

### res.body

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

## getAllGroupPost

### URI

```
/grouppost
```

### res.body

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

## getCompanyPostById

### URI

```
/grouppost/{id}
```

### res.body

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

## getGroupPostById

### URI

```
/grouppost/{id}
```

### res.body

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
