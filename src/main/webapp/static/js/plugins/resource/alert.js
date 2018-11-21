/*   弹出框*/

$.alerts = {
            
            verticalOffset: 0,                
            horizontalOffset: 0,          
            repositionOnResize: true,         
            overlayOpacity:.03,             
            overlayColor: '#FFF',               
            draggable: true,                   
            okButton: ' 确定 ',         // 确定按钮
            cancelButton: ' 取消 ', 
            dialogClass: null,                  
            
            
            alert: function(message, title, callback) {
                if( title == null ) title = 'Alert';
                $.alerts._show(title, message, null, 'alert', function(result) {
                    if( callback ) callback(result);
                });
            },
            confirm: function(message, title, callback) {
                if( title == null ) title = 'Confirm';
                $.alerts._show(title, message, null, 'confirm', function(result) {
                    if( callback ) callback(result);
                });
            },
        
        
            
            _show: function(title, msg, value, type, callback) {
                
                $.alerts._hide();
                $.alerts._overlay('show');
                $("body").append(
                  '<div id="popup_container">' +
                    '<h3 id="popup_title"></h3>' +
                    '<div id="popup_content">' +
                      '<div id="popup_message"></div>' +
                    '</div>' +
                  '</div>');
                
                if( $.alerts.dialogClass ) $("#popup_container").addClass($.alerts.dialogClass);
                
                $("#popup_container").css({
                    position: 'absolute',
                    zIndex: 99999,
                    padding: 0,
                    margin: 0
                });
                $("#popup_title").text(title);
                $("#popup_content").addClass(type);
                $("#popup_message").text(msg);
                $("#popup_message").html( $("#popup_message").text().replace(/\n/g, '<br />') );
                
                $("#popup_container").css({
                    minWidth: $("#popup_container").outerWidth(),
                    maxWidth: $("#popup_container").outerWidth()
                });
                $.alerts._reposition();
                $.alerts._maintainPosition(true);
                
                switch( type ) {
                    case 'alert':
                        $("#popup_message").after('<div id="popup_panel"><input type="button" value="' + $.alerts.okButton + '" id="popup_ok" /></div>');
                        $("#popup_ok").click( function() {
                            //关闭对话框时， 背景可以滑动
                            $(window).unbind('touchmove');
                            
                            $.alerts._hide();
                            callback(true);
                        });
                        $("#popup_ok").focus().keypress( function(e) {
                            if( e.keyCode == 13 || e.keyCode == 27 ) $("#popup_ok").trigger('click');
                        });
                    break;
                    case 'confirm':
                        $("#popup_message").after('<div id="popup_panel"><input type="button" value="' + $.alerts.okButton + '" id="popup_ok" /> <input type="button" value="' + $.alerts.cancelButton + '" id="popup_cancel" /></div>');
                        $("#popup_ok").click( function() {
                            $.alerts._hide();
                           if( callback ) callback(true);
                        });
                        $("#popup_cancel").click( function() {
                            //关闭对话框时， 背景可以滑动
                            $(window).unbind('touchmove');
                            
                            $.alerts._hide();
                            if( callback ) callback(false);
                        });
                        $("#popup_ok").focus();
                        $("#popup_ok, #popup_cancel").keypress( function(e) {
                            if( e.keyCode == 13 ) $("#popup_ok").trigger('click');
                            if( e.keyCode == 27 ) $("#popup_cancel").trigger('click');
                        });
                    break;
                }
                if( $.alerts.draggable ) {
                    try {
                        $("#popup_container").draggable({ handle: $("#popup_title") });
                        $("#popup_title").css({ cursor: 'move' });
                    } catch(e) { }
                }
            },
            
            _hide: function() {
                $("#popup_container").remove();
                $.alerts._overlay('hide');
                $.alerts._maintainPosition(false);
            },
            
            _overlay: function(status) {
                switch( status ) {
                    case 'show':
                        $.alerts._overlay('hide');
                        $("body").append('<link rel="stylesheet" href="/css/wap.css" />'+'<div id="popup_overlay"></div>');
                    break;
                    case 'hide':
                        $("#popup_overlay").remove();
                    break;
                }
            },
            
            _reposition: function() {
                var top = (($(window).height() / 2) - ($("#popup_container").outerHeight() / 2)) + $.alerts.verticalOffset;
                var left = (($(window).width() / 2) - ($("#popup_container").outerWidth() / 2)) + $.alerts.horizontalOffset;
                if( top < 0 ) top = 0;
                if( left < 0 ) left = 0;
                
                $("#popup_container").css({
                    top: top + 'px',
                    left: left + 'px'
                });
                $("#popup_overlay").height( $(document).height() );
            },
            
            _maintainPosition: function(status) {
                if( $.alerts.repositionOnResize ) {
                    switch(status) {
                        case true:
                            $(window).bind('resize', function() {
                                $.alerts._reposition();
                            });
                        break;
                        case false:
                            $(window).unbind('resize');
                        break;
                    }
                }
            }
            
        }
        
        jAlert = function(message, title, callback) {
            $.alerts.alert(message, title, callback);
        }
        jConfirm = function(message, title, callback) {
            $.alerts.confirm(message, title, function(r) {
            });
        };