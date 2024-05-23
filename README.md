# 项目结构

```shell
$project
├── demo.mp4
├── back-end
│   ├── db.sql
│   ├── homework.iml
│   ├── log
│   ├── mvnw
│   ├── pom.xml
│   ├── src
│   ├── target
│   └── upload
├── front-end
│   ├── babel.config.js
│   ├── build
│   ├── images
│   ├── node_modules
│   ├── package-lock.json
│   ├── package.json
│   ├── public
│   └── src
└── readme.md
```



# 后端



java 版本: java8

mysql: 8

```sql
CREATE DATABASE homework_management_system;

CREATE TABLE `homework` (
  `homework_id` int NOT NULL AUTO_INCREMENT,
  `teacher_id` int NOT NULL,
  `homework_title` varchar(30) NOT NULL,
  `homework_content` text NOT NULL,
  PRIMARY KEY (`homework_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `student` (
  `student_id` int NOT NULL,
  `student_name` varchar(20) NOT NULL,
  `password` varchar(32) NOT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `student_homework` (
  `student_homework_id` int NOT NULL AUTO_INCREMENT,
  `student_id` int NOT NULL,
  `homework_id` int NOT NULL,
  `title` varchar(30) NOT NULL,
  `content` text NOT NULL,
  `teacher_comment` text,
  PRIMARY KEY (`student_homework_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `student_homework_file` (
  `student_homework_file_id` int NOT NULL AUTO_INCREMENT,
  `student_id` int NOT NULL,
  `homework_id` int NOT NULL,
  `file_id` int NOT NULL,
  `file_name` varchar(100) NOT NULL,
  `file_path` varchar(100) NOT NULL,
  PRIMARY KEY (`student_homework_file_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `teacher` (
  `teacher_id` int NOT NULL,
  `teacher_name` varchar(20) NOT NULL,
  `password` varchar(32) NOT NULL,
  PRIMARY KEY (`teacher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



CREATE TABLE `file` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '文件名',
  `md5` varchar(32) DEFAULT NULL COMMENT 'MD5值',
  `path` varchar(100) NOT NULL COMMENT '文件路径',
  `upload_time` datetime(3) NOT NULL COMMENT '上传时间',
  `ext` varchar(255) DEFAULT NULL COMMENT '文件后缀名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```



Then go to application.yml to configure your mysql port, username, password.

After then, you can start the system.

# 前端

node: v14.17.6

```
cd $project/front-end
npm install
npm run serve
```

## 登录注册

![image-20240523155847343](https://p.ipic.vip/p3j0mv.png)

注册成功后自动跳转到登录界面：

![image-20240523160140097](https://p.ipic.vip/j2fc00.png)

密码通过md5加密存储：

![image-20240523160435122](https://p.ipic.vip/shaq1s.png)

登录失败:

![image-20240523160007080](https://p.ipic.vip/k9obwb.png)

登录成功：

![image-20240523155914369](https://p.ipic.vip/ev85cf.png)

## 学生完成作业并上传

在作业列表中，有教师上传的作业，学生完成后，等待老师批改。

![image-20240523170249336](https://p.ipic.vip/2muad0.png)

提交作业：

![image-20240523170318586](https://p.ipic.vip/3wgno1.png)

![image-20240523174724965](https://p.ipic.vip/6lqv2w.png)

![image-20240523174758851](https://p.ipic.vip/lhqlmh.png)

收到点评：

![image-20240523184158722](https://p.ipic.vip/ynqg9y.png)

## 教师发布并批改作业

发布作业:

![image-20240523170132379](https://p.ipic.vip/7anj0x.png)

![image-20240523170054872](https://p.ipic.vip/2fs176.png)

作业点评：

![image-20240523184122750](https://p.ipic.vip/5mjkdj.png)



## 多端运行

```shell
cd $project/front-end
npm run electron:serve # 不支持arm64
```

![image-20240523191059202](https://p.ipic.vip/mkq7jo.png)