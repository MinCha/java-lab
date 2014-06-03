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
	def __init__(self, mean, points=None):
		self.mean = mean
		if points != None:
			self.points = points
		else:
			self.points = []

	def __eq__(self, other):
        	if isinstance(other, self.__class__):
			return self.__dict__ == other.__dict__

	def __ne__(self, other):
        	return not self.__eq__(other)

	def __str__(self):
		return repr(self);

	def __repr__(self):
		return "Cluster(%s,%s)" % (self.mean,self.points)

	def add(self, p):
		self.points.append(p)

	def same_mean(self, other):
		return self.mean == other

	def mean_vector(self):
		x = reduce(lambda i, x: i+x, [p.x for p in self.points])
		y = reduce(lambda i, y: i+y, [p.y for p in self.points])
		size = len(self.points)
		return Point(round(x / size,1), round(y / size,1))

def furthest(p):
	combi = [[x,y] for i,x in enumerate(p) for y in p[i:len(p)] if x != y]
	score = [{x[0].edistance(x[1]):[x[0], x[1]]} for x in combi]
	score.sort()
	return score[len(score)-1].values()[0]

def clustering(r,p):
	print "Cluster Mean Vector "+str(r)
	result = [Cluster(eachc) for eachc in r]
	for eachp in p:
		target=closestcluster(r,eachp)
		for eachc in result:
			if eachc.mean == target:
				eachc.add(eachp)
	print "Cluster Result  "+str(result)
	return result

def closestcluster(c,p):
	result = None
	for cluster in c:
		if result == None or cluster.edistance(p) < result.edistance(p):
			result = cluster
	return result

def title(t):
	return "===" + t + "==="

def main():
	p = ([Point(1,1), Point(1.5,2), Point(3,4), Point(5,7), Point(3.5,5.0),\
		Point(4.5,5.0), Point(3.5,4.5)])
	initial = furthest(p)
	cluster = clustering(initial, p)
	print cluster
	iterator = 1
	while True:
		print title(str(iterator) + "th relocating")
		newcluster = clustering([c.mean_vector() for c in cluster], p)
		if newcluster == cluster:
			break
		cluster = newcluster
		iterator += 1
	print title("Result=" + str(cluster))

if __name__ == '__main__':
	main()
