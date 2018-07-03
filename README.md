# AutoSearchBug
> 这是一款可以在项目崩溃时获取异常  
自动联网在Stack Overflow,百度或其他平台上寻找回答链接和详情

[![AutoSearchBug][AutoSearchBugsvg]][AutoSearchBug] 
[![api+svg][api+svg]][api+svg] 
[![sizesvg][sizesvg]][sizesvg] 

## 如何引入

### Android Studio 引入

#### 第1步 将JitPack存储库添加到您的构建文件  
将其添加到存储库末尾的根build.gradle中：

       allprojects {
            repositories {
                ...
                maven { url 'https://jitpack.io' }
            }
        }
        
#### 第2步 添加依赖关系
    
        dependencies {
        	   implementation 'com.github.SHPDZY:AutoSearchBug:1.0.0'
        }
        	
        	
### Eclipse 引入
建议使用As，方便版本更新。

        dependencies {
               compile project(path: ':AutoSearchBug')
        }
    

## 如何使用
        
        AutoSearchBugControl.getBuilder()
                .setMaxSize(3)    //显示数量
                .showAnswer(true) //true显示回答详情
                .setSearchType(0) //0在stackoverflow搜索  1：在百度搜索
                .init();

## 效果图

![效果图](http://blog.9aiplay.com/zb_users/upload/2018/07/20180703145628153060098854731.png)


## 作者信息：

* [zhangyong](http://9aiplay.com)
