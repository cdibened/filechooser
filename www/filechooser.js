/**
 * cordova FileChooser plugin
 */
 (function(cordova){
    var FileChooser = function() {

    };

    FileChooser.prototype.open = function(params, success, fail) {
        return cordova.exec(function(args) {
            success(args);
        }, function(args) {
            fail(args);
        }, 'FileChooser', 'open', [params||{}]);
    };

    window.filechooser = new FileChooser();
    
    // backwards compatibility
    window.plugins = window.plugins || {};
    window.plugins.filechooser = window.filechooser;
})(window.PhoneGap || window.Cordova || window.cordova);