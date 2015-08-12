#!/usr/bin/python
#pip install pymongo
#how to insert binary data into mongodb ? use GridFS ?
from datetime import datetime
from pymongo import MongoClient
client = MongoClient("mongodb://mongodbrouter194.wodezoon.com:27017")
db = client.testAug

if type == 1:
  #insert data into mongodb
  result = db.restaurants.insert_one(
    {
      "address": {
        "zipcode": "10075",
        "building": "1480"
      },
      "borough": "2 Avenue",
      "cuisine": "Italian",
      "grades": [
        {
          "date": datetime.strptime("2014-10-01", "%Y-%m-%d"),
          "grade": "A",
          "score": 11
        },
        {
          "date": datetime.strptime("2014-01-16", "%Y-%m-%d"),
          "grade": "B",
          "score": 17
        }
      ],
    "name": "Vella",
    "restaurant_id": "41704620"
    }
  )
  print result.inserted_id
elif type == 2:
  #search data from mongodb
  cursor  = db.restaurants.find()
  for document in cursor:
    print(document)
elif type == 3:
  #updated data from mongodb
  result  = db.restaurants.update_one(
    {"name": "Vella"},
    {"$set": {"cuisine": "American(new)"},"$currentDate":{"lastModified": True}}
  )
  print(result.modified_count)
else:
  #deleted data from mongodb
  cursor  = db.restaurants.delete_many({"borough":"2 Avenue"})
  print(cursor.deleted_count)
