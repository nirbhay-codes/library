// Author format
{
  "name": "Author 1",
  "bio": "This is short bio of Author 1"
}


[
    {
        "id": 1,
        "name": "Author 1",
        "bio": "This is short bio of Author 1",
        "books": [
            {
                "id": 1,
                "title": "Sample Title 1",
                "isbn": "ISBN0001",
                "publishedDate": "2023-01-01"
            },
            {
                "id": 2,
                "title": "Sample Title 2",
                "isbn": "ISBN0002",
                "publishedDate": "2023-01-02"
            }
        ]
    },
    {
        "id": 2,
        "name": "Author 2",
        "bio": "This is short bio of Author 2",
        "books": [
            {
                "id": 3,
                "title": "Sample Title 3",
                "isbn": "ISBN0003",
                "publishedDate": "2023-01-03"
            },
            {
                "id": 4,
                "title": "Sample Title 4",
                "isbn": "ISBN0004",
                "publishedDate": "2023-01-04"
            }
        ]
    },
    {
        "id": 3,
        "name": "Author 3",
        "bio": "This is short bio of Author 3",
        "books": [
            {
                "id": 5,
                "title": "Sample Title 5",
                "isbn": "ISBN0005",
                "publishedDate": "2023-01-05"
            },
            {
                "id": 6,
                "title": "Sample Title 6",
                "isbn": "ISBN0006",
                "publishedDate": "2023-01-06"
            }
        ]
    }
]
============================================================
// Books format 
{
    "title": "Sample Title 1",
    "isbn": "ISBN0001",
    "publishedDate": "2023-01-01",
    "author": {
      "id": 1
    }
}

and so on.

[
    {
        "id": 1,
        "title": "Sample Title 1",
        "isbn": "ISBN0001",
        "publishedDate": "2023-01-01",
        "authorId": 1
    },
    {
        "id": 2,
        "title": "Sample Title 2",
        "isbn": "ISBN0002",
        "publishedDate": "2023-01-02",
        "authorId": 1
    },
    {
        "id": 3,
        "title": "Sample Title 3",
        "isbn": "ISBN0003",
        "publishedDate": "2023-01-03",
        "authorId": 2
    },
    {
        "id": 4,
        "title": "Sample Title 4",
        "isbn": "ISBN0004",
        "publishedDate": "2023-01-04",
        "authorId": 2
    },
    {
        "id": 5,
        "title": "Sample Title 5",
        "isbn": "ISBN0005",
        "publishedDate": "2023-01-05",
        "authorId": 3
    },
    {
        "id": 6,
        "title": "Sample Title 6",
        "isbn": "ISBN0006",
        "publishedDate": "2023-01-06",
        "authorId": 3
    }
]