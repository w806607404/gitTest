$.extend({
        ajaxUpload: function (args, id) {
            var src = $('#' + id);
            if(!src || !src.parent() || !args.url){
                return;
            }
            var parent = src.parent(), addedId = id + '_area', newId = id + '_new', addedNode;
            // 不使用内联样式
            if (args.css) {
                addedNode = $('<div id='+ addedId + ' class="addedArea">' +
                        '<input type="hidden" name="' + id + '" id="' + id  +'">' +
                        '<img />' +
                        '<span class="deleteItem" title="删除上传的文件" style="cursor: pointer;position: relative;">x</span>' +
                        '</div>');
            } else {
                addedNode = $('<div id='+ addedId + ' style="display: none; margin-left: 90px;margin-top: -6px;" class="addedArea">' +
                        '<input type="hidden" name="' + id + '" id="' + id  +'">' +
                        '<img />' +
                        '<span class="deleteItem" title="删除上传的文件" style="cursor: pointer;position: relative;">x</span>' +
                        '</div>');
            }
            $(src).attr('id', newId);
            $(parent).append(addedNode);
            var input = $('#' + addedId).find('input'), img = $('#' + addedId).find('img'), span = $('#' + addedId).find('span');
            args.referenceId && $(input).remove() && (input = $('#' + args.referenceId));
            var imgWidth = 80 || args.imgWidth, imgHeight = 30 || args.imgHeight, value = args.value || '';
            $(img).css({width: imgWidth + 'px', height: imgHeight + 'px'});
            $(span).css({left: '-10px', top: -imgHeight/2 + 2 + 'px'});
            $(span).click(function(evt){
                $(input).val('');
                $(addedNode).css('display', 'none');
                value = '';
            });
            var defaultArgs = {
                width: args.width || 101,
                //height: args.height || 30,
                //buttonText: args.label || '浏览到',
                buttonText:'',
                auto: true,
                fileObjName:'file',
                buttonImage:'/images/file_upload.png',
                swf: '/scripts/common/uploadify/uploadify.swf',
                uploader: args.url,
                fileTypeExts: args.type === 'img'?'*.jpg;*.jpge;*.gif;*.png': '*.*',
                fileSizeLimit: args.fileSizeLimit || '3MB',
                queueSizeLimit: 1,
                removeTimeout:1,
                queueID:false,
                queueData:{errorMsg:''},
                'overrideEvents' : [ 'onDialogClose','onUploadError', 'onSelectError' ],
                onSelect:function(){
                },
                onSelectError:function(file, errorCode, errorMsg){
                    switch(errorCode) {
                        case -100:
                            errorMsg="上传的文件数量已经超出系统限制的"+args.queueSizeLimit+"个文件！";
                            break;
                        case -110:
                            errorMsg="文件 ["+file.name+"] 大小超出系统限制的"+args.fileSizeLimit+"大小！";
                            break;
                        case -120:
                            errorMsg="文件 ["+file.name+"] 大小异常！";
                            break;
                        case -130:
                            errorMsg="文件 ["+file.name+"] 类型不正确！";
                            break;
                    }
                    alert(errorMsg);
                },
                onUploadStart  : function(){
                    //设置上传进度条的位置， 
                    !args.hide && $('#' + newId + '-queue').css({
                        position: 'relative',
                        top: args.height?(args.height - 10 + 'px' ) : '20px',               
                    });
                },
                onFallback: function(){
                    alert("请安装FLASH插件后再试。");
                },
                onUploadSuccess: function(file, data, response){
                    data = $.parseJSON(data ||'{"url":"https"}');
                    if(data.error==0){
                        //默认预览
                        var imgSrc = data.url || '/images/file.png';
                        if(!args.type || args.type != 'img'){
                            imgSrc = '/images/file.png';
                        }
                        setValue(data.url, imgSrc);
                        (typeof args.success).toLowerCase() === 'function' && args.success(data);
                    }else if(data.error==1){
                        alert(data.message);
                        var imgSrc = data.url || '/images/file.png';
                        if(!args.type || args.type != 'img'){
                            imgSrc = '/images/file.png';
                        }
                        setValue(data.url, imgSrc);
                        return;
                    }
                },
                onUploadError: function(){
                    setValue( '');       
                    (typeof args.error).toLowerCase() === 'function' && args.error();
                }
            };
            args.formData && ((typeof args.formData).toLowerCase() == 'object') && (defaultArgs.formData = args.formData);
            $(src).uploadify(defaultArgs);

            function setValue(newValue, url){
                value = newValue;
                if(value){
                    $(img).attr('src', url || value);
                    $(input).val(value);
                    !args.hide && $(addedNode).css('display', 'block');
                }else{
                    $(input).val('');
                    $(addedNode).css('display', 'none');
                }
                args.hide && $('#' + newId + '-queue').css('display', 'none');
            }
            setValue(value);  
               
            $('#' + newId).css({'float': 'left', 'margin-top': '10px'});
            $('#' + newId).after($(addedNode)); 
            if(!args.referenceId){
                $(addedNode).css('margin-top', '-40px');
                $(addedNode).css('margin-left', '120px');
                $('#' + newId).css('float', 'none');
                $('#' + newId).css('margin-top', '0px');
                $('#' + newId).css('display', 'inline-block');
            }
            if(args.hide){
                $(addedNode).css('display', 'none');
            }
        }   
    });