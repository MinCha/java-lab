brighttp
========

Safe, fast and elaborate Java HTTP client which is compatible with spring RestTemplate.

Goals are

* Fail-Over : Return immediately an error when http calls of same URI are failed more than N(Variable).
* Connection-Pool : Re-use connections with Keep-Alive.
* Pipe-lining : Use pipe-lining for performance.
* Statistics 