# lib-java-generate

[ ![Download](https://api.bintray.com/packages/takashno/takashno_maven_repo/lib-java-generate/images/download.svg?version=0.0.2) ](https://bintray.com/takashno/takashno_maven_repo/lib-java-generate/0.0.2/link)

## 概要
これは、Javaの自動生成を行うための補助ライブラリです.  
良く見かける出力したいJavaクラスの雛形ファイルのテンプレートを何かしらのテンプレートエンジンを用いて用意し
そのテンプレートに合致したJavaBeans（Modelクラス）をバインドして生成するようなものではありません.

このライブラリが取る方式が良いかは別として、、、  
上記のような思想ではなく
Javaの言語仕様に沿ったテンプレートを用意し、それに対するJavaBeans（Modelクラス）を用意しています。

## サポートしているバージョン
- Java8

## サポートしている言語仕様

|要素|Class|Field|Method|
|:---|:---:|:---:|:---:|
|アクセス修飾子|◯|◯||
|修飾子（複数可）|◯|◯||
|JavaDoc|◯|◯||
|アノテーション（複数可）|◯|◯||
|継承|◯|||
|実装（複数可）|◯|||
|ジェネリクス|◯|||
|import|◯|||
|static import|◯|||
|on demand import|◯|||
|初期値||◯|||
|引数|||
|戻り値|||


## 使い方

ライブラリの公開はJCenterにて行っています。  
リポジトリ設定は適宜行ってください。

### Dependency

#### Maven

```xml
<dependency>
  <groupId>com.zomu_t</groupId>
  <artifactId>lib-java-generate</artifactId>
  <version>0.0.2</version>
</dependency>
```

#### Gradle

```groovy
implementation 'com.zomu_t:lib-java-generate:0.0.2'
```


