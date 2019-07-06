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
|default|||◯|
|引数|||◯|
|戻り値|||◯|
|ロジック生成|||◯|

## 少し便利機能

- メソッドの戻り値、引数等で指定しているクラスについて自動でimportに追加します。
- 最終的にコードフォーマッターをかけています。
- 固有のテンプレートを適用し、任意のモデルクラスを[mustache](https://mustache.github.io/) テンプレートで扱えます。
- ロジック生成についても固有のテンプレートを適用し、任意のモデルクラスを[mustache](https://mustache.github.io/) テンプレートで扱えます。

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


### クラスの作成

シンプルなクラスの作成を行うものを例にあげます。

```java
/* Java8 Class Generate Test. */
package com.zomu_t.lib.java.generate.java8;

import com.zomu_t.lib.java.generate.common.context.ConvertContext;
import com.zomu_t.lib.java.generate.common.context.ConvertTarget;
import com.zomu_t.lib.java.generate.common.type.DefaultTemplate;
import com.zomu_t.lib.java.generate.java8.converter.Java8Converter;
import com.zomu_t.lib.java.generate.java8.model.*;
import com.zomu_t.lib.java.generate.java8.type.AccessModifier;
import com.zomu_t.lib.java.generate.java8.type.MethodModifier;
import com.zomu_t.lib.java.generate.java8.util.JavaDocUtils;
import com.zomu_t.lib.java.generate.java8.util.TypeUtils;

import java.io.StringWriter;

/**
 * Java8のClass出力テスト.<br>
 *
 * @author takashno
 * @since v0.0.2
 */
public class Java8ClassGenerateTest {

  /**
   * 出力テスト.
   *
   * @param args 引数
   */
  public static void main(String[] args) {

  ConvertContext context = new ConvertContext();
  ConvertTarget target = context.newTarget(DefaultTemplate.JAVA8);

  // Setting Class
  ClassModel clazz =
    ClassModel.builder()
      // ヘッダーコメント
      .commentHeader("/* Java8 Class Generate Test. */")
      // パッケージ
      .packageName("com.zomu_t.lib.java.generate.java8")
      // インポート定義
      .imports(ImportModel.builder()
        .packageName("com.zomu_t.lib.java.generate.common.context")
        .className("ConvertContext").build())
      .imports(ImportModel.builder()
        .packageName("com.zomu_t.lib.java.generate.common.context")
        .className("ConvertTarget").build())
      .imports(ImportModel.builder()
        .packageName("com.zomu_t.lib.java.generate.common.type")
        .className("DefaultTemplate").build())
      .imports(ImportModel.builder()
        .packageName("com.zomu_t.lib.java.generate.java8.converter")
        .className("Java8Converter").build())
      .imports(ImportModel.builder()
        .packageName("com.zomu_t.lib.java.generate.java8.model")
        .wildcard(true).build())
      .imports(ImportModel.builder()
        .packageName("com.zomu_t.lib.java.generate.java8.type")
        .className("AccessModifier").build())
      .imports(ImportModel.builder()
        .packageName("com.zomu_t.lib.java.generate.java8.type")
        .className("MethodModifier").build())
      .imports(ImportModel.builder()
        .packageName("com.zomu_t.lib.java.generate.java8.util")
        .className("JavaDocUtils").build())
      .imports(ImportModel.builder()
        .packageName("com.zomu_t.lib.java.generate.java8.util")
        .className("TypeUtils").build())
      // アクセス修飾子
      .accessModifier(AccessModifier.PUBLIC)
      // JavaDoc
      .javaDoc(JavaDocModel.builder()
        .mainContent("Java8のClass出力テスト.")
        .annotation(JavaDocAnnotationModel.builder()
          .name("author").content("takashno").build())
        .annotation(JavaDocAnnotationModel.builder()
          .name("since").content("v0.0.2").build())
        .build())
      // クラス名
      .className("Java8ClassGenerateTest")
      .build();

  target.setClazz(clazz);

  // Setting Method
  MethodModel method =
    MethodModel.builder()
      .accessModifier(AccessModifier.PUBLIC)
      .methodModifier(MethodModifier.STATIC)
      .name("main")
      .javaDoc(JavaDocUtils.getMethodJavaDocModel("出力テスト.",
        JavaDocAnnotationModel.builder()
          .name("param")
          .content("args")
          .content("引数").build()))
      .arg(ArgModel.builder()
        .type(TypeUtils.getStringClassModel())
        .array(true)
        .name("args").build())
      .build();

  clazz.getMethods().add(method);


  // Convert
  StringWriter sw = new StringWriter();
  target.setOutputWriter(sw);
  Java8Converter converter = new Java8Converter();
  converter.convert(context);

  log.info(sw.toString());

  }


}
```

このコードの出力は以下となります。

```java
/* Java8 Class Generate Test. */
package com.zomu_t.lib.java.generate.java8;

import com.zomu_t.lib.java.generate.common.context.ConvertContext;
import com.zomu_t.lib.java.generate.common.context.ConvertTarget;
import com.zomu_t.lib.java.generate.common.type.DefaultTemplate;
import com.zomu_t.lib.java.generate.java8.converter.Java8Converter;
import com.zomu_t.lib.java.generate.java8.model.*;
import com.zomu_t.lib.java.generate.java8.type.AccessModifier;
import com.zomu_t.lib.java.generate.java8.type.MethodModifier;
import com.zomu_t.lib.java.generate.java8.util.JavaDocUtils;
import com.zomu_t.lib.java.generate.java8.util.TypeUtils;

/**
 * Java8のClass出力テスト.<br>
 *
 * @author takashno
 * @since v0.0.2
 */
public class Java8ClassGenerateTest {

  /**
   * 出力テスト.<br>
   *
   * @param args 引数
   */
  public static void main(String[] args) {
    // TODO : To Be Logic...
  }
}
```

ロジックが存在しない空のクラス定義になりますが、同じクラスを出力しているわけです。