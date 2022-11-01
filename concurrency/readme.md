## Concurrency issue test

Using MYSQL and Redis
> https://www.inflearn.com/course/동시성이슈-재고시스템/dashboard

```bash
docker run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=1234 --name mysql mysql
docker run -d -p 6379:6379 --name redis redis
```
