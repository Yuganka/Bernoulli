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

You just add @RequiresPermission or @RequiresSetting to the method(s) which need it and Mr. Bernoulli will take care
of the processing at runtime. In addition, you can also choose an appropriate permission disabled policy and setting
state mismatch policy.

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