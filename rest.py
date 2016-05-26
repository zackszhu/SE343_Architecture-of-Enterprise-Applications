#!/usr/bin/env python
# encoding: utf-8
import json
import requests

def CreateBooks():

    url = "http://172.17.0.2:8080/BookStoreWeb/service/api/books"

    print("Creating book: ")

    data = {"name": "House Trained",
            "author": "Jackie Bouchard",
            "publisher": "Lake Union Publishing",
            "isbn": "978-1503947122",
            "price": 7.99,
            "description": "Description F"}

    print(data)

    data_json = json.dumps(data)
    headers = {'Content-type': 'application/json'}

    response = requests.post(url, data=data_json, headers=headers)
    print(response.status_code)

def QueryAllBooks():

    url = "http://localhost:8080/BookStoreWeb/service/api/books"
    response = requests.get(url)
    print(response.json())

def QueryBook():

    book = input('Input the bookID: ')
    url = "http://localhost:8080/BookStoreWeb/service/api/book/"
    response = requests.get(url + book)
    print(response.json())

if __name__ == "__main__":
    print('1 - QueryAllBooks')
    print('2 - QueryOneBook')
    print('3 - CreateBook')
    action = input('Please input the action: ')
    actions = [QueryAllBooks, QueryBook, CreateBooks]
    actions[int(action) - 1]()
