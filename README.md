Bernoulli
==================

This is a fork of Fernando Cejas's example of [Aspect Oriented Programming in Android](https://github.com/android10/Android-AOPExample). A big shout out to him for providing a working example of AOP in Android.


Daniel Bernoulli was a Swiss mathematician and physicist who came up with his famous Bernoulli's Principle in 1738.

The principle concerns characterising the flow of a fluid and says that the velocity and pressure at any point are
inversely related - more the pressure slower the velocity and vice versa.

This library is trying to characterise the flow of code execution in Android on similar lines.

It aims to provide a clear representation of the impediments in the flow of certain code sequences (for example,
requirements of certain Android permissions or settings) to help you better plan how your application should
proceed in the presence or absence of the same.

We can think of each class as a river and each method in the class like a stream in the river.

You just add `@RequiresPermission` or `@RequiresSetting` annotation(s) to the method(s) which need it and Mr. Bernoulli
 will take care of the processing at runtime. In addition, you can also choose an appropriate
  `PermissionDisabledPolicy` and `SettingsStateMismatchPolicy`.
  

Setup
-----

1. In your project level build.gradle file, add the following -  

```groovy
allprojects {
    repositories {

        maven {
            url "http://15.206.89.169:8081/repository/bernoulli/"
        }
    }
}
```

2. Then, in your module's build.gradle file's dependencies - 

 *  Add the following before any plugins that you may be applying - 

```groovy
        buildscript {
            dependencies {
                classpath 'org.aspectj:aspectjtools:1.9.4'
            }
        }
```

 *  Bernoulli requires Java 8 to function. So, in the android section, add the following -

```groovy
        compileOptions {
                sourceCompatibility JavaVersion.VERSION_1_8
                targetCompatibility JavaVersion.VERSION_1_8
            }
```

 *  Add the following in your dependencies section - 

```groovy
            implementation 'org.aspectj:aspectjrt:1.9.4'
            implementation 'com.yugankasharan.bernoulli:bernoulli:$version'
```

 *  Finally, add these lines at the bottom of the file. Relevant imports should be possible at this point - 

```groovy
        final def variants = project.android.applicationVariants
        
        variants.all { variant ->
        
            variant.javaCompile.doLast {
        
                String[] args = ["-showWeaveInfo",
                                 "-1.5",
                                 "-inpath", javaCompile.destinationDir.toString(),
                                 "-aspectpath", javaCompile.classpath.asPath,
                                 "-d", javaCompile.destinationDir.toString(),
                                 "-classpath", javaCompile.classpath.asPath,
                                 "-bootclasspath", project.android.bootClasspath.join(File.pathSeparator)]
        
                new Main().run(args, new MessageHandler(true));
            }
        }
```

Usage
-----

1. Initialise Bernoulli in your application sub-class's `onCreate` - 

    Bernoulli.startFlowing(this)
 
        
2. You can use Bernoulli annotations only in a sub-class of `BernoulliActivity`. So, create a sub-class and then you
 are free to use `@RequiresPermission` and `@RequiresSetting` annotations on its methods.

License
--------

    Copyright 2021 Yuganka Sharan

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.