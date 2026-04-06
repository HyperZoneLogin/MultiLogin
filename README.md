<div align="center">

# MultiLogin

_✨ 正版与多种外置登录共存 ✨_

[![GitHub license](https://img.shields.io/github/license/HyperZoneLogin/MultiLogin?style=flat-square)](https://github.com/HyperZoneLogin/MultiLogin/blob/master/LICENSE)
[![QQ Group](https://img.shields.io/badge/QQ%20group-832210691-yellow?style=flat-square)](https://jq.qq.com/?_wv=1027&k=WrOTGIC7)
[![Join our Discord](https://img.shields.io/discord/1225725211727499347.svg?logo=discord&label=)](https://discord.gg/9vh4kZRFCj)

</div>

> [!CAUTION]
> 🚧 本项目已暂缓维护，将逐渐退役，但仍可使用  
> 可加入QQ交流群了解详细内幕和获取同类型需求的解决方案.

## 概述

MultiLogin 是一款主要为 Minecraft 代理端设计的插件，旨在实现对正版与多种外置登录共存的支持，用于连接两个或多个外置验证服务器下的玩家，使他们能够在同一个服务器上一起游戏。

## 特性

* 支持多达 128 个不同来源的 Yggdrasil 同时共存
* 鉴权代理、重试机制
* 游戏内档案管理系统
* 异步/同步皮肤修复机制
* 支持接管 Floodgate

## 安装

最低需要 `Java 21`， 不需要安装 `authlib-injector` ，没有任何前置插件，也不需要添加和更改 `JVM` 参数

1. [下载](https://github.com/HyperZoneLogin/MultiLogin/releases/latest) 插件
2. 丢进 plugins
3. 启动服务器

### 注意

HyperZoneLogin部分需要修改配置以绕过管理机制，不要安装额外子模块  
misc.conf中

```yaml
# 是否启用替换 GameProfile
enable-replace-game-profile=false
```

## 配置

详见 [Wiki](https://github.com/HyperZoneLogin/MultiLogin/wiki)

## 构建

1. 克隆这个项目
2. 执行 `./gradlew shadowJar` / `gradlew shadowJar`
3. 在 `*/build/libs` 下寻找你需要的

或者你也可以

1. [Fork](https://github.com/HyperZoneLogin/MultiLogin/fork) 此项目
2. 开启 Actions
3. 随便提交一个文件

## BUG 汇报

[Weekly Ver](https://github.com/HyperZoneLogin/MultiLogin/releases/tag/weekly) 点击此处，也许你遇到的问题已修复

[832210691](https://jq.qq.com/?_wv=1027&k=WrOTGIC7) 点击此处，来加入QQ交流群

[new issue](https://github.com/HyperZoneLogin/MultiLogin/issues/new) 点击此处，提交你的问题

[Discord](https://discord.gg/HJXHCZRS) 进来聊聊你的问题

## 贡献者

<a href="https://github.com/HyperZoneLogin/MultiLogin/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=HyperZoneLogin/MultiLogin"  alt="作者头像"/>
</a>

[我也想为贡献者之一？](https://github.com/HyperZoneLogin/MultiLogin/pulls)
