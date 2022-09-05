user manage micro

系统主要有user-manage-portal, user-manage-gateway, user-manage-server, user-manage-worker模块组成

运行系统关键步骤
1 启动zk server实例
    docker run --name 3.5.9  --restart always -d zookeeper:3.5.9

2 启动mysql 实例
    docker run --name 5.7.0  --restart always -d mysql:5.7

3 初始化mysql库，表
    docker exec -it f172e3228bbb（mysql containerId） bash
    
    脚本：
    create database interview;

    CREATE TABLE `common_user` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name` varchar(32) NOT NULL DEFAULT '',
    `email` varchar(32) NOT NULL DEFAULT '' COMMENT '电子邮箱',
    `email_send_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '邮件发送状态（0-未发送 1-发送失败 3-发送中 4-发送成功）',
    `email_send_time` datetime DEFAULT NULL COMMENT '邮件发送时间',
    `user_status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '1-正常用户 2-已删除',
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=UTF-8;

4 构建，运行user-manage-server镜像（user-manage-server根目录）：
    docker build -t user-manage-server:0.0.1 -f Dockerfile .
    docker run -p 8081:8081 -d user-manage-server:0.0.1

5 构建，运行user-manage-gateway镜像（user-manage-gateway根目录）：
    docker build -t user-manage-gateway:0.0.1 -f Dockerfile .
    docker run -p 8082:8082 -d user-manage-gateway:0.0.1

6 验证系统可用性验证：
    http://localhost:8082/hello?name=world