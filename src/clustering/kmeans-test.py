#!/usr/bin/python
import kmeans
import unittest

class TestKMeans(unittest.TestCase):
	def test_point_can_compute_eucliean_distance(self):
		result = kmeans.Point(0,0).edistance(kmeans.Point(2,2))
		self.assertEqual(2.83,round(result,2))

	def test_point_can_compare(self):
		self.assertTrue(kmeans.Point(1,1) == kmeans.Point(1,1))
		self.assertFalse(kmeans.Point(2,1) == kmeans.Point(1,1))

	def test_point_should_return_correct_repr(self):
		p = kmeans.Point(1,1)
		other_p = eval('kmeans.' + repr(p))
		self.assertTrue(p == other_p)		

	def test_point_can_be_added_to_cluster(self):
		c = kmeans.Cluster(kmeans.Point(1,1))
		c.add(kmeans.Point(2,2))
		self.assertEquals(kmeans.Point(2,2), c.child[0])

	def test_cluster_can_compare_with_root(self):
		c = kmeans.Cluster(kmeans.Point(1,1))
		self.assertTrue(c.same_root(kmeans.Point(1,1)))
		self.assertFalse(c.same_root(kmeans.Point(1,2)))

	def test_cluster_can_compute_mean_vector(self):
		c = kmeans.Cluster(kmeans.Point(1,1))
		c.add(kmeans.Point(1.5,2))
		c.add(kmeans.Point(3,4))
		self.assertEquals(kmeans.Point(1.8,2.3), c.mean_vector())

	def test_can_find_furthest_points(self):
		p = ([kmeans.Point(1,1), kmeans.Point(1.5,2),\
			kmeans.Point(3,4), kmeans.Point(5,7),\
			kmeans.Point(3.5,5.0), kmeans.Point(4.5,5.0),\
			kmeans.Point(3.5,4.5)])
		self.assertEquals([kmeans.Point(1,1),kmeans.Point(5,7)],\
			kmeans.furthest(p))

if __name__ == '__main__':
	unittest.main()
