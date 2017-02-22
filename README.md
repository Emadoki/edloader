# EdLoader
## Android library for loading animation
![](https://github.com/Emadoki/edloader/raw/master/edloader.gif)

## How to use
#### Import project
Add this to your root gradle
```
allprojects {
    repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
    }
```

Add this to your app gradle
```
compile 'com.github.Emadoki:edloader:1.0.1'
```

#### XML
```xml
<!-- Basic -->
<com.emadoki.edloader.EdLoader
            android:layout_width="72dp"
            android:layout_height="72dp"
            app:loader_type="classic"/>

<!-- Complex -->
<com.emadoki.edloader.EdLoader
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            app:loader_type="classic"
            app:loader_radius="12dp"
            app:loader_margin="8dp"
            app:loader_amount="4"
            app:loader_color="#fff"
            app:loader_speed="1.5"/>
```
```
android:layout_width match_parent/wrap_content/dp/px
android:layout_height match_parent/wrap_content/dp/px
app:loader_radius (default 6dp)
app:loader_margin (default 6dp)
app:loader_amount (default 3) x >= 2
app:loader_color (default #fff) #RGBA/#RGB/@color
app:loader_speed (default 1)  faster < 1 < slower
```
#### Inspiration
[Loader Exploration](https://material.uplabs.com/posts/loader-exploration)

## License
```
Copyright 2017 Emadoki

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