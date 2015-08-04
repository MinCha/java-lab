#!/usr/bin/python

v1 = [3,4,5]
v2 = [2,3,4]

def vector_product_dot(v1,v2):
  return sum([v1[i]*v2[i] for i in range(0,len(v1))])
