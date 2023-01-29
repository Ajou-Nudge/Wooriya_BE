# REST API

| Method | URI               | Description       |
|--------|-------------------|-------------------|
| POST   | /companypost/post | postCompanyPost   |
| POST   | /grouppost/post   | postGroupPost     |
| GET    | /companypost      | getAllCompanyPost |
| GET    | /grouppost        | getAllGroupPost   |

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