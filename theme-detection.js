var exec = require("cordova/exec");

exports.isDarkMode = function(success, error) {
  exec(success, error, "ThemeDetection");
};
