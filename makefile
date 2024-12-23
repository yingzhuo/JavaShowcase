usage:
	@echo "========================================================================================="
	@echo "usage (default)      : 显示本菜单"
	@echo "wrapper              : 初始化GradleWrapper"
	@echo "compile              : 编译所有文件"
	@echo "test                 : 执行单元测试"
	@echo "clean                : 清理文件"
	@echo "github               : 推送文件到Github"
	@echo "========================================================================================="

wrapper:
	@gradle wrapper \
		--gradle-distribution-url 'https://mirrors.cloud.tencent.com/gradle/gradle-8.12-bin.zip'

compile:
	@gradlew classes -x test

test:
	@gradlew test

clean:
	@gradlew clean

github: clean
	@git status
	@git add .
	@git commit -m "$(shell /bin/date "+%F %T")"
	@git push

.PHONY: usage wrapper compile test clean github