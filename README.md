# BlurImageSwitcher
Android Blurred ImageSwitcher Library

 [![License: MIT](https://img.shields.io/badge/License-MIT-silver.svg)](https://opensource.org/licenses/MIT) [![](https://jitpack.io/v/Chrisvin/BlurImageSwitcher.svg)](https://jitpack.io/#Chrisvin/BlurImageSwitcher) [![API](https://img.shields.io/badge/API-21%2B-blue.svg?style=flat)](https://android-arsenal.com/api?level=21) 

BlurImageSwitcher is a lightweight ImageSwitcher library to switch images with a blur animation.

## Demo app
To run the demo project, clone the repository and run it via Android Studio. 
</br>(OR)
</br>Download the latest demo apk from [releases](https://github.com/Chrisvin/BlurImageSwitcher/releases).

## Usage
#### Set up the dependency
1. Add the JitPack repository to your root build.gradle at the end of repositories:
```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```
2. Add the BlurImageSwitcher dependency in the build.gradle:
```
implementation 'com.github.Chrisvin:BlurImageSwitcher:1.0'
```

#### Use `BlurImageSwitcher` instead of the normal `ImageSwitcher`
```
<com.jem.blurimageswitcher.BlurImageSwitcher
    android:id="@+id/imageSwitcher"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:animationDuration="400"
    app:blurFactor="100"
    app:scaleFactor="1.2">

    <!--  Add your ImageViews here  -->

</com.jem.blurimageswitcher.BlurImageSwitcher>
```

#### Use `showNext` or `setImage` functions to transition to the new image using Blur animation.
```
imageSwitcher.showNext()
imageSwitcher.setImageResource(id)
imageSwitcher.setImageDrawable(drawable)
imageSwitcher.setImageURI(uri)
```

## Bugs and Feedback
For bugs, questions and discussions please use the [Github Issues](https://github.com/Chrisvin/BlurImageSwitcher/issues).

## License
```
MIT License

Copyright (c) 2019 Jem

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
