exports.setInnerHtml = function (props) {
    return function()
    {
        $(props.node).html(props.html);
        if (props.script)
        {
          window.eval(props.script);
        }
    }
}

exports.clearInnerHtml = function(node) {
  return function() {
    $(node).empty()
  }
}

function collectParams(form, command, args)
{
  var vals = [];
  if (command) { 
    vals.push({name: "event__", value:command});
  }
  args.forEach(function (c, i){
    vals.push({name: "eventp__" + i, value: c})
  });
  form.querySelectorAll("input,select,textarea").forEach(
    function (v) { 
      if (v.type)
      {
        switch (v.type)
        {
          case 'button': 
            return;
          case 'checkbox':
          case 'radio':
            if (!v.checked || v.disabled) 
              return;
        }
      }
      vals.push({name: v.name, value: v.value}); 
    }
  );
  return vals;
}


exports.setupLegacyHooks = function(ps) {
    function stdSubmit(validate) {
      return function(command) {
        _trigger("presubmit");
        if (validate)
        {
          if (!_trigger("validate"))
          {
            return false;
          }
        }
        var vals = collectParams(document.getElementById("eqpageForm"), command, [].slice.call(arguments, 1));
        ps.submit({vals:vals, callback:null});
        return false;
      }
    }
    return function() {
    window.EQ = {
      event: stdSubmit(true),
      eventnv: stdSubmit(false),
      postAjax: function(form, name, params, callback, errorcallback) {
        var vals = collectParams(form, name, params);
        ps.submit({vals:vals, callback: callback});
      },
      updateIncludes: ps.updateIncludes
    }
  }
}
