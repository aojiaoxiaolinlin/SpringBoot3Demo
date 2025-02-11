# 配置笔记

## Maven

- 拉取依赖源文件

```bash
    mvn dependency:resolve -Dclassifier=sources
```

## IDEA Docker部署配置

- 在设置中配置`docker` `ssh`连接。
- 下载`docker`可执行文件并配置`Path`路径，[下载地址](https://download.docker.com/win/static/stable/x86_64/)
- 下载[buildx](https://github.com/docker/buildx/releases/download/v0.13.1/buildx-v0.13.1.windows-amd64.exe)
  放置于路径`%USERPROFILE%/.docker/cli-plugins/docker-buildx.exe`

> [参考](https://blog.csdn.net/Equent/article/details/137779505)

- `docker-compose` 需要在服务器中执行，通过`ftp`服务将`docker`配置文件上传到服务器。
- 运行配置: 最新版本`docker` 包含 `docker-compose` 仅需要执行`docker compose up`