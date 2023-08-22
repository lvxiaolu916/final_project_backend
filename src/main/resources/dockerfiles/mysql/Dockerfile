FROM mysql:5.7

# 设置环境变量
ENV MYSQL_ROOT_PASSWORD=123456

# 将自定义的初始化脚本复制到容器中,容器启动时将会自动执行
COPY backend_scripts.sql /docker-entrypoint-initdb.d/

# 暴露 MySQL 服务的默认端口
EXPOSE 3306
