**使用方式**:
Step 1. 在工程下的build.gradle添加

```
allprojects {
		repositories {
			...
			maven { url 'https://www.jitpack.io' }
		}
	}
```
Step 2. 在项目下的build.gradle添加

```
dependencies {
	        implementation 'com.github.JulyJiangL:ToastNotification:1.0.2'
	}
```
PS:由于时间原因,本版本暂未实现数据库插入功能 如需插入本地数据库GreenDao可自行添加文件 InsertMessage.java

**注:**

##### v1.0.0

 1. 根据自定义 ToastView 和 Notification 定制需要的弹窗布局 
 2. 由于时间原因,本版本暂未实现数据库插入功能 如需插入本地数据库GreenDao可自行添加文件 InsertMessage.java 
 3. 本项目引入 blankj:utilcode 库,如需详细资料 请移步 [https://blankj.com/2016/07/31/android-utils-code/](https://blankj.com/2016/07/31/android-utils-code/)
[https://github.com/JulyJiangL/ToastNotification/blob/master/toastnotificationlibrary/build.gradle](https://github.com/JulyJiangL/ToastNotification/blob/master/toastnotificationlibrary/build.gradle)
