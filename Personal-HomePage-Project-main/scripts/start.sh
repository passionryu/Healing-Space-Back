#!/bin/bash
cd /home/ec2-user/app

# 실행 중인 애플리케이션 종료 - stop.sh 개발할 필요 없음
sudo pkill -f 'java -jar' || true

# 새 애플리케이션 실행 (application.properties에 대한 고려가 없는 코드)
# nohup java -jar myapp.jar > app.log 2>&1 &

# 새 애플리케이션 실행 (EC2 내부 설정 파일 application.properties사용)
nohup java -jar myapp.jar --spring.config.location=/home/ec2-user/app/application.properties > app.log 2>&1 &

