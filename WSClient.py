#!/usr/bin/env python
# encoding: utf-8
from zeep import Client

client = Client('http://172.17.0.2:8080/BookStoreWeb/service/soap?wsdl')

bookID = input("Input book ID: ")

print(client.service.getDescription(bookID))
