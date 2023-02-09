Java version 14.0
MySQL version in docker is 8.0.31
Redis version is 6.2.2 RELEASE

This application was created to demonstrate the difference in time in receiving requests between Redis and MySQL. 
-

For running the application you have to go step by step as shown below:
-
 
1. Open the Docker and run it on Git Bash:
   - docker run --name world -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root --restart 
   unless-stopped -v mysql:/var/lib/mysql mysql:8.0.31
2. Open the Workbench:
   - connect to the Docker local database
   - import dump
3. Run it for the Redis and Redis-insight:
   - docker run -d --name redis-world -p 6379:6379 -p 8001:8001 redis/redis-stack:latest
   - docker run -d --name redis -p 6379:6379 redis:latest (snip code for when you have the image but remove container)
   - after all this check the containers. If all is ok than you will see redis and mysql containers
     - docker container ls 

In my application different was as shown below
-

;
Redis:	61 ms
MySQL:	83 ms
Feb 09, 2023 9:24:52 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl$PoolState stop
INFO: HHH10001008: Cleaning up connection pool [jdbc:p6spy:mysql://localhost:3306/world]

Process finished with exit code 0
