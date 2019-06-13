#### Steps to run tests

#####APK-debug version
* using maven to add io.appium dependency in `pom.xml` ( latest version is recommended )

#####Bundle version
* first download google bundletool-all from [here](https://github.com/KazuCocoa/AppBundleSample/blob/master/apks/bundletool-all-0.6.0.jar) and move it
to apks folder **"if not exists"**
* you should use your own key configurations [name,alias,password] and paste it on 
`apks/scripts/generateAPKS.sh`

* then `chmod +x ` all scripts before running them or you get an exception
permission denied .

* use `adb devices` to get your simulator id and paste it in `apks/scripts/generateAPKS.sh` after `--device-id `