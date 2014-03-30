# FileChooser Cordova plugin for Android (mainly KitKat)

This plugin was created as a workaround for https://issues.apache.org/jira/browse/CB-5294 and https://code.google.com/p/android/issues/detail?id=62220. This plugin is mainly configured for Android 4.4 so I would recommend to continue to use the native file dialogs for earlier versions of Android. There might be issues with the plugin as I have not fully tested all possible scenarios on many devices, but I have installed it on a Nexus 5 and it worked fine.


The core pieces of the code were taken from https://github.com/iPaulPro/aFileChooser. A huge thanks to him!!!  All I did was write the plugin wrapper around it.  Please take note of the second part of the `Setup` step from the aforementioned **[aFileChooser](https://github.com/iPaulPro/aFileChooser)**.
```
    Note that like a ContentProvider, the DocumentProvider authority must be unique. 
    You should change com.ianhanniballake.localstorage.documents in your Manifest, as 
    well as the LocalStorageProvider.AUTHORITY field.
```


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
    
    filechooser.open({},success,error);
```

### Example

```
    var success = function( data ) {
        var filepath = data.filepath;
        function win(r) {
            console.log("Code = " + r.responseCode);
            console.log("Response = " + r.response);
            console.log("Sent = " + r.bytesSent);
        }

        function fail(error) {
            console.log("An error has occurred: Code = " + error.code);
            console.log("upload error source " + error.source);
            console.log("upload error target " + error.target);
        }

        var uri = encodeURI("http://localhost/upload/processupload.php");
        var options = new FileUploadOptions();
        options.fileKey="file";
        options.fileName=filepath.substr(filepath.lastIndexOf('/')+1);

        var ft = new FileTransfer();
        ft.onprogress = function(progressEvent) {
            if (progressEvent.lengthComputable) {
                loadingStatus.setPercentage(progressEvent.loaded / progressEvent.total);
            } 
            else {
                loadingStatus.increment();
            }
        };

        ft.upload(filepath, uri, win, fail, options);  
    };
    
    var error = function( msg ) {
        console.log( msg );
    };


    <input type="file" id="fileinput" name="fileinput"/>
    if( device.platform.toLowerCase() === 'android' && device.version.indexOf( '4.4' ) === 0 ) {
        $('#fileinput').click( function(e) {
            filechooser.open( {}, success, error );
        });
    }
```


### Next up
-   automatically `import your.package.name.R` in the java files
-   add support for parameters in the filechooser
