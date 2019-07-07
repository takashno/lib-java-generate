# lib-java-generate
[![Build Status](https://travis-ci.org/takashno/lib-java-generate.svg?branch=master)](https://travis-ci.org/takashno/lib-java-generate)
[ ![Download](https://api.bintray.com/packages/takashno/takashno_maven_repo/lib-java-generate/images/download.svg?version=0.0.2) ](https://bintray.com/takashno/takashno_maven_repo/lib-java-generate/0.0.2/link)

## 概要
これは、Javaの自動生成を行うための補助ライブラリです.  
良く見かける出力したいJavaクラスの雛形ファイルのテンプレートを何かしらのテンプレートエンジンを用いて用意し
そのテンプレートに合致したJavaBeans（Modelクラス）をバインドして生成するようなものではありません.

このライブラリが取る方式が良いかは別として、
上記のような思想ではなく
Javaの言語仕様に沿った [mustache](https://mustache.github.io/) テンプレートを用意し、それに対するJavaBeans（Modelクラス）を用意しています。

## サポートしているJavaバージョン
- Java8

## サポートしている言語仕様

|要素|Class|Field|Constructor|Method|
|:---|:---:|:---:|:---:|:---:|
|アクセス修飾子|◯|◯|◯|◯|
|修飾子（複数可）|◯|◯||◯|
|JavaDoc|◯|◯|◯|◯|
|アノテーション（複数可）|◯|◯|◯|◯|
|継承|◯||||
|実装（複数可）|◯||||
|ジェネリクス|◯||||
|import|◯||||
|static import|◯||||
|on demand import|◯||||
|初期値||◯||||
|default||||◯|
|引数|||◯|◯|
|戻り値||||◯|
|ロジック生成|||◯|◯|

## 少し便利機能

- メソッドの戻り値、引数等で指定しているクラスについて自動でimportに追加します。
- 最終的にコードフォーマッターをかけています。
- 固有のテンプレートを適用し、任意のモデルクラスを[mustache](https://mustache.github.io/) テンプレートで扱えます。
- ロジック生成についても固有のテンプレートを適用し、任意のモデルクラスを[mustache](https://mustache.github.io/) テンプレートで扱えます。
- Getter/Setterの自動生成
- フィールドを扱うコンストラクタのロジックを自動生成

## 使い方

### Dependency

ライブラリの公開はJCenterにて行っています。  
リポジトリ設定は適宜行ってください。  
ライブラリが取得できない場合は、お手数ですが以下のMavenリポジトリを参照するようにしてください。
```
maven {
    url  "https://dl.bintray.com/takashno/takashno_maven_repo"
}
```

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


### Example

サンプルの一覧は以下です

|概要|詳細|
|:---|:---|
|[クラス出力サンプル](https://github.com/takashno/lib-java-generate/blob/master/examples/src/main/java/example/Java8ClassGenerate.java)|シンプルなクラスの出力例。出力しているクラス自体の定義を例として出力するもの。|
|[Enum出力サンプル](https://github.com/takashno/lib-java-generate/blob/master/examples/src/main/java/example/Java8EnumGenerate.java)|シンプルなEnumの出力例。Getterとコンストラクタの自動生成機能を利用しています。|

### Java言語仕様のモデル化について

Java言語の自動生成であるため、何を出力しようとしても `Class` にたどり着きます。  
従って、`ClassModel` というモデルクラスを作成することが、本ライブラリを利用する上でのメイン処理になります。 `ClassModel` はBuilderを用いて作成することが可能です。

例
```java
ClassModel classModel = 
  ClassModel.builder()
    .commentHeader("/* コピーライトなど */")
    .packageName("com.zomu_t...")
    // omitted
    .build();
```

`ClassModel` はJavaの言語仕様をモデル化しているため、 `Field` や `Method` といった要素持ちます。また本ライブラリはソースコードの自動生成を目的としているため、 `JavaDoc` 等についてもモデルの一部として表しています。 `JavaDocのアノテーション` まで作ることが可能です。（@author、@Param、@return など）
どのようなメソッドが公開されているかは、[JavaDoc](https://takashno.github.io/lib-java-generate/)を参考にしていただきたいです。

