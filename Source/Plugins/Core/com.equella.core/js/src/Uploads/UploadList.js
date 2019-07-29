exports.updateCtrlErrorText = function(ctrlId, text) {
  var contElem = document.querySelector("DIV#" + ctrlId + " > DIV.control");
  if (text == "") contElem.classList.remove("ctrlinvalid");
  else contElem.classList.add("ctrlinvalid");
  contElem.querySelector("P.ctrlinvalidmessage").textContent = text;
};

exports.updateDuplicateMessage = function(display) {
  var duplicateMessageDiv = document.querySelector(
    ".attachment-duplicate-message"
  );
  console.log(display);
  if (display) {
    duplicateMessageDiv.setAttribute("style", "color:red; display:inline");
  } else {
    duplicateMessageDiv.setAttribute("style", "color:red; display:none");
  }
};

exports.simpleFormat = function(format) {
  return function(args) {
    return format.replace(/{(\d+)}/g, function(match, number) {
      return typeof args[number] != "undefined" ? args[number] : match;
    });
  };
};

exports.register = function(exp) {
  window.UploadList = exp;
};
