package com.knoldus.models

case class Employee(name: String, age: Int, address: Address, phoneNumber: Int,company:Company)

case class Address(locality:String, state:String)

case class Company(name:String, address: Address)
