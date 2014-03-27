# FileChooser Cordova plugin for Android (mainly KitKat)

This plugin was created as a workaround for https://issues.apache.org/jira/browse/CB-5294 and https://code.google.com/p/android/issues/detail?id=62220.

The core pieces of the code were taken from https://github.com/iPaulPro/aFileChooser. A huge thanks to him!!!  All I did was write the plugin wrapper around it.


### Installation
```
cordova plugin add https://github.com/cdibened/filechooser.git
```

### Configuration

You will have to `import your.package.name.R` into the following java files.

```
    FileChooser.java 
    FileChooserActivity.java 
    FileListAdapter.java
    FileListFragment.java
    LocalStorageProvider.java
```

### Usage

The first argument, which will eventually be filechooser parameters such as multi-select, mime-types...., is currently ignored but must be passed in.

```
    var success = function(data) {
        console.log( data.filepath );
    };
    
    var error = function(msg) {
        console.log( msg );
    };
    
    window.filechooser.open({},success,error);
```


### Next up
-   automatically `import your.package.name.R` in the java files
-   add support for parameters in the filechooser
