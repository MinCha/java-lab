#!/usr/bin/python
import kmeans
import unittest

def p(x,y):
	return kmeans.Point(x,y)		

class TestKMeans(unittest.TestCase):

	def test_point_can_compute_eucliean_distance(self):
		result = p(0,0).edistance(p(2,2))
		self.assertEqual(2.83,round(result,2))

	def test_point_can_compare(self):
		self.assertTrue(p(1,1) == p(1,1))
		self.assertFalse(p(2,1) == p(1,1))

	def test_point_should_return_correct_repr(self):
		original_p = p(1,1)
		other_p = eval('kmeans.' + repr(original_p))
		self.assertTrue(original_p == other_p)		

	def test_point_can_be_added_to_cluster(self):
		c = kmeans.Cluster(p(1,1))
		c.add(p(2,2))
		self.assertEquals(p(2,2), c.points[0])

	def test_cluster_can_compare_with_mean(self):
		c = kmeans.Cluster(p(1,1))
		self.assertTrue(c.same_mean(p(1,1)))
		self.assertFalse(c.same_mean(p(1,2)))

	def test_cluster_can_compute_mean_vector(self):
		c = kmeans.Cluster(p(1,1))
		c.add(p(1,1))
		c.add(p(1.5,2))
		c.add(p(3,4))
		self.assertEquals(p(1.8,2.3), c.mean_vector())

	def test_can_find_furthest_points(self):
		points = ([p(1,1), p(1.5,2),\
			p(3,4), p(5,7),\
			p(3.5,5.0), p(4.5,5.0),\
			p(3.5,4.5)])
		self.assertEquals([p(1,1),p(5,7)],\
			kmeans.furthest(points))

if __name__ == '__main__':
	unittest.main()
