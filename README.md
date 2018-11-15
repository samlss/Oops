![Oops](https://github.com/samlss/Oops/blob/master/screenshots/logo.png)

[![Download](https://api.bintray.com/packages/samlss/maven/oops/images/download.svg)](https://bintray.com/samlss/maven/oops/_latestVersion)   [![Api reqeust](https://img.shields.io/badge/API-12+-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=12#l12)    [![Apache License 2.0](https://img.shields.io/hexpm/l/plug.svg)](https://github.com/samlss/Oops/blob/master/LICENSE)  



### Features

- Tiny

- One line of code shows empty, error, loading, etc.

- No additional layout nesting required


![Oops](https://github.com/samlss/Oops/blob/master/screenshots/screenshot1.gif)


### Dependency

#### Gradle
Add it in your module build.gradle at the end of repositories:
  ```java
  dependencies {
      implementation 'me.samlss:oops:1.0.0'
  }
  ```

#### Maven
```java
<dependency>
  <groupId>me.samlss</groupId>
  <artifactId>oops</artifactId>
  <version>1.0.0</version>
  <type>pom</type>
</dependency>
```

### Sample Usage

```java
//Show empty layout
Oops.with('activity' or 'viewgroup').show(R.layout.layout_empty);

//show error view
View errorView = getLayoutInflater().inflate(R.layout.layout_no_net_error, null);    
Oops.with('activity' or 'viewgroup').show(errorView);

//When you do not need to show the layout(empty, error, loading,etc.) anymore
//Please invoke the method - release.    
Oops.with('activity' or 'viewgroup').release();

```



### License

```
Copyright 2018 samlss

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
