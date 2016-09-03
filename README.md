#Common features
All projects use Maven.

#JavaEE-Inject-Produces-Servlets
Shows usage of CDI (Injection of managed beans) and producers together with simple servlets. Note that you do not need to understand producers to be able to use CDI, so do not look there yet if you just want to know about the basics of CDI.

* The `advanced` package illustrates dynamic instance creation. That advanced servlet also has a question that you might want to solve as an exercice (look at the Javadoc).

#JavaEE-Inject-Produces-Servlets-JSF
Same as JavaEE-Inject-Produces-Servlets with a supplementary very simple JSF page that calls the producer.

#JavaEE-JPA-Inject-Servlets
A simple JPA project, using a single entity, simple CDI managed beans and two simple servlets (posting items and getting items).

* The `advanced` package illustrates different transaction management approaches.

#JavaEE-JPA-JSF
A simple JPA project with two simple JSF pages.

* Also illustrated: [typesafe JPA queries](#metamodel).
* Also illustrated: [multi field validation](#multifield).

#JavaSE-JUL-JPA-Hib-H2
A simple JavaSE project featuring JPA, starring Hibernate as a JPA provider and H2 as a DB provider. This sample project uses JUL as a logger (contradicting my [own preference](#logging-choice)).

#Maven best practices

##Encoding
Include property `<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>` to ensure platform-independent behavior (and get rid of the warning Maven prints otherwise).

##Java version
Include properties: `<maven.compiler.source>1.8</maven.compiler.source> <maven.compiler.target>1.8</maven.compiler.target>` (or adjust to the desired java version) to indicate which java version you develop in (and for). This is [officially supported](https://maven.apache.org/plugins/maven-compiler-plugin/compile-mojo.html) and is thus to be preferred to the more verbose explicit [configuration](https://maven.apache.org/plugins/maven-compiler-plugin/examples/set-compiler-source-and-target.html) of the `maven-compiler-plugin`.

##Do not require web descriptor
Web applications do not require a web descriptor (the `web.xml` file that goes in the `WEB-INF` folder). Maven however required it by default when asked to package a war file. If you do not need a web descriptor, make your project as clean as possible and do not include one! Rather, include the property `<failOnMissingWebXml>false</failOnMissingWebXml>` in your `pom.xml`.

* [Note 1: As of Release 3.0.0 of the Apache Maven WAR Plugin, this is no more necessary: “The default value for failOnMissingWebXml has been changed from true to false.” ([source](https://maven.apache.org/plugins/maven-war-plugin/index.html)). However including the property anyway improves your project compatibility. See also [source code history](http://svn.apache.org/viewvc/maven/plugins/trunk/maven-war-plugin/src/main/java/org/apache/maven/plugins/war/WarMojo.java?view=log), [releases](http://svn.apache.org/viewvc/maven/plugins/tags/).]
* [Note 2: Web applications do not require a web descriptor any more since more than 10 years, since Servlet 2.5 MR 5 (see JSR 340 Servlet 3.1 [spec](http://download.oracle.com/otn-pub/jcp/servlet-3_1-fr-eval-spec/servlet-3_1-final.pdf), A.7.6 and 10.13).]

#CDI best practices
##Explicit no-argument constructor on managed beans?
You might want to include an explicit no-argument constructor for managed beans, with a comment inside stating that it is used for instanciation by the container. That is to avoid a risk of runtime failure: runtime failure will occur if later a constructor is added with arguments and the developer forbids to then include an explicit no-argument constructor as well. Addition of a constructor with arguments would make the default no-argument constructor disappear, and no compile-time error would be visible if the class is never instanciated manually, and (if the added constructor is not injectable) the container will not be able to instanciate the class any more.

However I find this scenario far-fetched. If indeed you never instanciate a class manually but let the container manage it, why would you suddenly need a constructor with arguments for manual use? Plus, it’s possible that the container would actually detect such problem at deployment time instead of runtime (not tested).

#JPA best practices
##Use delimited identifiers
Tell JPA to use delimited identifiers (see the object/relational xml mapping file, `orm.xml`, in the JPA samples). (See JSR 338 JPA 2.1 [spec](), section 2.13.) JPA will then “delimit” (quote) all SQL identifiers, thus avoiding conflicts with e.g. table names that are also SQL keywords. Note that this render identifiers case sensitive. Delimited identifiers is off by default and may cause nasty problems, especially because SQL keywords differ by vendor (and it’s hard to keep track of all of them, see e.g. [here](http://hsqldb.org/doc/guide/lists-app.html) or [here](https://www.drupal.org/node/141051)). A typical problem with non delimited identifiers occurs when using an entity named `User`: this may work on some DBMS and fail at runtime on others.

##<a name="metamodel"></a>Metamodel generator for typesafe JPA queries
To generate the metamodel for use with typesafe Criteria queries, suffices to include a metamodel generator in your classpath ([doc](http://hibernate.org/orm/tooling/)). Set it to `<provided>` scope as your code does not depend on it at runtime.

* For correct integration in Eclipse (neon, Java EE version), I have installed `Maven Integration for Eclipse JDT Annotation Processor Toolkit
`, then in the project settings, Maven / Annotation Processing, selected: Automatically configure JDT APT. (Eclipse also has an internal option to generate the metamodel, see the project options at JPA / Canonical metamodel, but I don’t use that as it hinders compatibility of the project with other IDEs.)

#JSF best practices

##<a name="multifield"></a>Multiple field validation
Sometimes the validation logic requires knowledge of the value of multiple fields. (Example: required `start` ≤ `end`, where `start` and `end` are two request parameters.) In such case I consider the usual JSF validation approach (namely, by treating the problem in the JSF validation phase) inelegant, because it is designed for single-field validation. I prefer to use case-based navigation in the action phase. Some may disagree, see e.g. BalusC, “JSF ajax/action/listener methods are semantically the wrong place to do validation” [here](http://stackoverflow.com/a/5897183/859604) and an alternative proposal [here](http://balusc.omnifaces.org/2007/12/validator-for-multiple-fields.html).

#Logging best practices

##<a name="logging-choice"></a>Framework
One of the PITA in Java world is the multiplicity of logging framework. My personal choice is to go for JUL for Java EE projects and SLF4J + logback for Java SE projects.
