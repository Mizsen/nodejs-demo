# nodejs-demo

## 全栈电商项目

- npm start
- npx ts-node src/app.ts

测试：
curl http://localhost:3000/api/products
[{"id":1,"name":"Product 1","description":"Description for Product 1","price":100,"stock":10},{"id":2,"name":"Product 2","description":"Description for Product 2","price":200,"stock":5},{"id":3,"name":"Product 3","description":"Description for Product 3","price":300,"stock":0}]


## 电商秒杀系统

node -v
v20.19.0
npm -v
10.8.2

测试：
1. 创建产品（POST）
'''
curl -X POST http://localhost:3000/api/products \
  -H "Content-Type: application/json" \
  -d '{"name":"Test Product","price":99.9,"stock":100}'
'''
2. 获取产品（GET）
'''
curl http://localhost:3000/api/products/1
'''
3. 更新产品（PUT）
'''
curl -X PUT http://localhost:3000/api/products/1 \
  -H "Content-Type: application/json" \
  -d '{"price":88.8,"stock":80}'
'''
每个用户只能参与一次”和“库存扣减”逻辑：
1. 创建秒杀活动
'''
curl -X POST http://localhost:3000/api/flash-sales \
  -H "Content-Type: application/json" \
  -d '{"productId":1,"price":9.9,"stock":2,"startTime":"2024-05-23T10:00:00Z","endTime":"2099-05-23T12:00:00Z"}'
'''
2. 获取秒杀详情
'''
curl http://localhost:3000/api/flash-sales/1
'''
3. 参与秒杀
'''
curl -X POST http://localhost:3000/api/flash-sales/1/participate \
  -H "Content-Type: application/json" \
  -d '{"userId":123}'
curl -X POST http://localhost:3000/api/flash-sales/1/participate \
  -H "Content-Type: application/json" \
  -d '{"userId":123}'
curl -X POST http://localhost:3000/api/flash-sales/1/participate \
  -H "Content-Type: application/json" \
  -d '{"userId":456}'
curl -X POST http://localhost:3000/api/flash-sales/1/participate \
  -H "Content-Type: application/json" \
  -d '{"userId":789}'
'''

