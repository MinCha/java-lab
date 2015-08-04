top
===

apache, nginx, tomcat or etc monitoring tool like linux top

apachetop
===

If you would like to monitor only localhost

```
./apachetop.pl localhost    

=============================================================
         SERVER   RPS  BUSY  WAIT    KA
=============================================================
      localhost    33   146     9   133
=============================================================
Summary
=============================================================
       RPS(avg) =    33
   Waiting(sum) =     9

```

If you would like to monitor multiple hosts which may be connected by ssh

```
./apachetop.pl server1,server2    

=============================================================
         SERVER   RPS  BUSY  WAIT    KA
=============================================================
        server1    33   146     9   133
        server2    23   116    10   127
=============================================================
Summary
=============================================================
       RPS(avg) =    28
   Waiting(sum) =    19

```
