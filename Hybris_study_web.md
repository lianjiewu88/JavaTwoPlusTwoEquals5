# 2018-02-16 created initially

1. All extensions **of the platform** are included into the build process by default. For **other extensions** it's necessary to tell hybris that they should be included.

* spring-security（整体的用户权限之类验证，webservice,登录之类的）
* spring-integration(omsats, datahub)
* jmx(监控)
* zk(各个cockpit backoffice底层)
* velocity 邮件内容产生使用的技术(VelocityTemplateRenderer), oauth2 手机 外部调用，webservice验证
* solr 商品搜索

2. [解读Hybris自带的拦截器框架的使用方法和配置文件](http://www.ku2n.com/ku2n/index.php?c=read&id=1364&page=2)

3. addon 是hybris插件的概念。 其实就是普通的java工程,不过需要执行下列语句将addon导入你的商城网站项目下。
> ant addoninstall -Daddonnames="weixinpay" -DaddonStorefront.yacceleratorstorefront="xxxstorefront"

4. @Resource   //如果将该注释应用于一个字段或方法，那么初始化应用程序组件时容器（Spring）将把所请求资源的一个实例注入其中。
private StadiumDAO stadiumDAO

5. Impex是基于java Model的一种面向对象的数据操作手段
[原文链接](http://blog.csdn.net/tanrunfang/article/details/50518415)
```java
House{
    String id
    Person owner;
    String address;
}

Person{
    String id;
    String name;
}
```

```sql
INSERT_UPDATE House;id[unique=true];owner(id[unique=true],name);address
```
Jerry: impex里只能看到attribute name，看不到attribute data type

在对照页面模板的impex文件时，发现TypeCode，Attribute在hybris并没有相应的java文件和属性对应，而是impex文件里对应的TypeCode在Hybris系统里对应一个编译后生成的Model文件，Hybris在生成的java文件通过一些属性和方法使impex文件TypeCode，Attributes与Mode文件里的属性对应。
e.g:
```sql
INSERT_UPDATE
 PageTemplate;catalogVersion(catalog(id[default=hybrisContentCatalog]),version[default=Online])[unique=true];uid[unique=true];name;frontendTemplateName;restrictedPageTypes(code);active[default=true]
```

PageTemplate对应一个编译后生成的PageTemplateModel

PageTemplateModel.java里有如下代码

```java
public final static String _TYPECODE = "PageTemplate";

public static final String CATALOGVERSION = "catalogVersion";
```

by default any files named essentialdata*.impex and projectdata*.impex that reside in <extension>/resources/impex will be imported during essential and project data creation  
    
6. debug: yacceleratorstorefront

[hybris new data model讲解](http://blog.csdn.net/tanrunfang/article/details/50524322)

```sql
itemtype code=Stadium generate=true autocreate=true
```

* defines a new type Stadium which implicitly extends from GenericItem
* autocreate=true indicates that Stadium is a new type
* generate=true creates required sourcecode (not the model class) for the type
* persistence type=property - Defines how item are stored. property reflects the normal persistent behaviour

当我们改变了数据模型（通过更改items.xml文件），我们必须update System，将更改的内容同步到数据库中，type definitions should involve type system localization

7. [CronJobs](http://blog.csdn.net/xxxcyzyy/article/details/51056683)
