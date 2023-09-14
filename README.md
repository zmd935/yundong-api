##辰领科技
单模块项目
##部署到外网
mvn clean package -Ptest wagon:upload-single wagon:sshexec

restart.sh运行错误解决方法
:set fileformat=unix 修改ubuntu 和linux服务器、 dos等非图形界面冲突
:wq保存

签名验证
- 签名请求头sign或者拼接在url，2种方式都可以
- 按字典升序对所有参数进行排序，包括url和body参数
- key1=val1&key2=val2&key3=val3...（签名字符串不包括sign自身）
- 将字符串和securityKey拼接生成新字符串=原字符串+"&securityKey=${securityKey}"
- 使用md5加密字符串返回hex字符串
