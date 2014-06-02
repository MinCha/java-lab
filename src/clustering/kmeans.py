#!/usr/bin/python
import math

class Point:
	def __init__(self, x, y):
		self.x = float(x)
		self.y = float(y)

	def __eq__(self, other):
        	if isinstance(other, self.__class__):
			return self.__dict__ == other.__dict__

	def __ne__(self, other):
        	return not self.__eq__(other)

	def __str__(self):
		return repr(self);

	def __repr__(self):
		return "Point(%s,%s)" % (self.x,self.y)

	def edistance(self, point):
		return math.sqrt(pow(self.x - point.x, 2) + pow(self.y - point.y, 2));


class Cluster:
	def __init__(self, root, child=None):
		self.root = root
		self.child = child
		if (self.child == None): 
			self.child = []

	def __str__(self):
		return repr(self);

	def __repr__(self):
		return "Cluster(%s,%s)" % (self.root,self.child)

	def add(self, child):
		self.child.append(child)

	def same_root(self, other):
		return self.root == other

	def all(self): 
		return [self.root] + self.child
	
	def mean_vector(self):
		x = reduce(lambda i, x: i+x, [p.x for p in self.all()])
		y = reduce(lambda i, y: i+y, [p.y for p in self.all()])
		size = len(self.child) + 1
		return Point(round(x / size,1), round(y / size,1))

def furthest(p):
	combi = [[x,y] for i,x in enumerate(p) for y in p[i:len(p)] if x != y]
	score = [{x[0].edistance(x[1]):[x[0], x[1]]} for x in combi]
	score.sort()
	return score[len(score)-1].values()[0]

def clustering(c, p):
	result = {repr(eachc): Cluster(eachc) for eachc in c}
	p = [x for x in p if x not in c]
	for eachp in p:
		result[repr(closestcluster(eachp,c))].add(eachp)		
	return result

def closestcluster(p, c):
	result = None
	for a in c:
		if result == None or a.edistance(p) < result.edistance(p):
			result = a
	return result
	

p = ([Point(1,1), Point(1.5,2), Point(3,4), Point(5,7), Point(3.5,5.0),\
	Point(4.5,5.0), Point(3.5,4.5)])
initial = furthest(p)
print clustering(initial, p)
