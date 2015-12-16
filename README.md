# Etu Hadoop 2015 比賽
精誠Hadoop比賽之記錯時間兩天奮鬥史。

# 比賽規則
比賽規定找出 Web Log 當月銷售排名前二十的商品，資料請至[商品交易 Log](http://files.imaclouds.com/dataset/HMC-Contest.log)下載，資料格式如下：
```txt
203.145.207.188 - - [01/Feb/2015:00:00:00 +0800] "GET /action?;act=view;uid=;pid=0005158462;cat=J,J_007,J_007_001,J_007_001_001;erUid=41ee27d6-5f83-b982-69f9-f378dc9fc11b; HTTP/1.1" 302 160 "-" "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0) Gecko/20100101 Firefox/23.0"
1.171.24.21 - - [01/Feb/2015:00:00:00 +0800] "GET /action?;act=view;uid=;pid=0022007845;cat=I,I_001,I_001_003,I_001_003_016;erUid=a6ef6b96-4e4-21af-e645-b582a3333d57; HTTP/1.1" 302 160 "-" "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; WOW64; Trident/6.0)"
1.172.0.185 - - [01/Feb/2015:00:00:00 +0800] "GET /action?;act=view;uid=;pid=0006604986;cat=G_001_006_002;erUid=6c27cc0b-d35a-addb-feb2-561b6bb28a5; HTTP/1.1" 302 160 "-" "Mozilla/5.0 (Linux; U; Android 4.1.1; zh-tw; PadFone 2 Build/JRO03L) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30"
101.13.126.139 - - [01/Feb/2015:00:00:00 +0800] "GET /action?;act=cart;uid=;plist=0022356972,3,139,0009755432,1,199;erUid=dfba63c-23c2-f0d3-2b49-996125c66535; HTTP/1.1" 302 160 "-" "Mozilla/5.0 (iPad; CPU OS 5_0_1 like Mac OS X) AppleWebKit/534.46 (KHTML, like Gecko) Version/5.1 Mobile/9A405 Safari/7534.48.3"
61.58.145.131 - - [01/Feb/2015:00:00:00 +0800] "GET /action?;act=view;uid=;pid=0024134891;cat=L,L_006,L_006_002,L_006_002_030;erUid=280f45c-dccf-fb70-a3a7-1f8f4ee7661a; HTTP/1.1" 302 160 "-" "Mozilla/5.0 (Linux; U; Android 4.1.2; zh-tw; GT-P3100 Build/JZO54K) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Safari/534.30"
114.41.4.218 - - [01/Feb/2015:00:00:01 +0800] "GET /action?;act=order;uid=U312622727;plist=0006944501,1,1069;erUid=252b97f1-25bd-39ea-6006-3f3ebf52c80; HTTP/1.1" 302 160 "-" "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; WOW64; Trident/6.0; MAARJS)"
```
主要是計算 ```action=order``` 部分的購買資訊。如下面資料為例：
```txt
plist=0006944501,1,1069
```
逗點切割後第一個為```pid```。第二個為```number```。第三為```price```。之後以此類推，來計算出每個商品的總銷售。

# 執行方式
比賽規定採用撰寫一個 shell 來完成所有動作，執行方式如以下：
```sh
$ time ./run.sh
```
