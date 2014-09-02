#!/usr/bin/python

string = "aaaaa"
number = 1

print(string)
print(number)
#print(string + number)

def some_function(maybe_list):
  maybe_list.sort()
  return maybe_list

print(some_function([5,2,3,1,4]))
print(some_function("What is this?"))

