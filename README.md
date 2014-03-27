# FileChooser Cordova plugin for Android (mainly KitKat)

This plugin was created as a workaround for https://issues.apache.org/jira/browse/CB-5294 and https://code.google.com/p/android/issues/detail?id=62220.

The core pieces of the code were taken from https://github.com/iPaulPro/aFileChooser. A huge thanks to him!!!  All I did was write the plugin wrapper around it.


### Installation
```
cordova plugin add https://github.com/cdibened/filechooser.git
```

### Configuration
Since there is no way to append to the `strings.xml` found in `res/values` directory when using the `cordova plugin add` command, after successful installation, you will have to manually copy the following.

```
    <string name="empty_directory">Empty Directory</string>
    <string name="storage_removed">Storage was removed or unmounted.</string>
    <string name="choose_file">Select a file</string>
    <string name="chooser_title">File Browser</string>
    <string name="error_selecting_file">Error selecting File</string>
    <string name="internal_storage">Internal storage</string>
```

In addition, you wil have to `import your.package.name.R` into the following java files.

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
-   somehow get the installation to append the `strings.xml`
-   automatically `import your.package.name.R` in the java files
-   add support for parameters in the filechooser
