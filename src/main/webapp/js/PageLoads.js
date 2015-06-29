function loadMyPhotos() {
    $.ajax({
        url : 'photos?mine=1',
        async: false,
        type: "GET",
        dataType: "text",
        beforeSend: function() {
            waitingDialog.show();
        },
        success: function(responseText) {
            $("#photoGallery").append(responseText);
             waitingDialog.hide();
        }
    });
    $('li img').on('click',function(){
        openImageModal(this);
    });
}

function checkUnreadMessages(messages) {
    if(messages !== null) {
        if(messages === 0) {
            $(".badge").hide();
        } else {
            $(".badge").text(messages);
        }
    } else {
        $.ajax({
            url : 'MessageLoader?unread=1',
            async: false,
            type: "GET",
            dataType: "text",
            beforeSend: function() {
                waitingDialog.show();
            },
            success: function(responseText) {
                //location.reload();
                if(responseText === '0') {
                    $(".badge").hide();
                } else {
                    $(".badge").text(responseText);
                }
                waitingDialog.hide();
            }
        });
    }
}

function loadAllPhotos() {
    $.ajax({
        url : 'photos',
        async: false,
        type: "GET",
        dataType: "text",
        beforeSend: function() {
          waitingDialog.show();  
        },
        success: function(responseText) {
            $("#photoGallery").append(responseText);
            waitingDialog.hide();
        }
    });
    $('li img').on('click',function(){
        openImageModal(this);
    });
}

function loadRouteDetails(name, description, distance, ascend, descend, minHeight, maxHeight, id) {
    $("#routeName").val(name);
    $("#description").val(description);
    $("#distance").val(distance);
    $("#totalAscend").val(ascend);
    $("#totalDescend").val(descend);
    $("#minHeight").val(minHeight);
    $("#maxHeight").val(maxHeight);

    $("#routeName").prop("disabled", true);
    $("#description").prop("disabled", true);
    $("#distance").prop("disabled", true);
    $("#totalAscend").prop("disabled", true);
    $("#totalDescend").prop("disabled", true);
    $("#minHeight").prop("disabled", true);
    $("#maxHeight").prop("disabled", true);

    $.ajax({
        url : 'routes?detail=1&routeId=' + id,
        async: false,
        type: "GET",
        dataType: "text",
        beforeSend: function(responseText) {
            waitingDialog.show();
        },
        success: function(responseText) {
            createMap(responseText);
            waitingDialog.hide();
        }
    });
}

function loadUserProfile() {
    $.ajax({
        url : 'userStorage?profile=1',
        async: false,
        type: "GET",
        dataType: "text",
        beforeSend: function() {
            waitingDialog.show();
        },
        success: function(responseText) {
            var props = responseText.split(";");
            $("#username").val(props[0]);
            $("#password").val(props[1]);
            $("#nombre").val(props[2]);
            $("#apellidos").val(props[3]);
            $("#birthday").val(props[4]);
            $("#email").val(props[5]);
            $("#ciudad").val(props[6]);
            $("#pais").val(props[7]);
            waitingDialog.hide();
            $("#username").prop("disabled", true);
        }
    });
}

function loadAllNews() {
    $.ajax({
        url : 'news',
        async: false,
        type: "GET",
        dataType: "text",
        beforeSend: function() {
            waitingDialog.show();
        },
        success: function(responseText) {
            //location.reload(); 
            $("#allNews").append(responseText);
            CKEDITOR.disableAutoInline = true;
            CKEDITOR.inline( 'showEditor', {
                removePlugins: 'toolbar'
            } );
            waitingDialog.hide();
        }
    });
}

function loadMyNews() {
    $.ajax({
        url : 'news?mine=1',
        async: true,
        type: "GET",
        dataType: "text",
        beforeSend: function() {
        waitingDialog.show();
        },
        success: function(responseText) {
            //location.reload(); 
            $("#myNews").append(responseText);
            CKEDITOR.disableAutoInline = true;
            CKEDITOR.inline( 'showEditor', {
                removePlugins: 'toolbar'
            } );
            waitingDialog.hide();
        }
    });
}

function openNewDetail(newId) {
    $.ajax({
        url : 'news?detail=1&id=' + newId,
        async: true,
        type: "GET",
        dataType: "text",
        beforeSend: function(responseText) {
            waitingDialog.show();
        },
        success: function(responseText) {
            waitingDialog.hide();
            location.href = "/MountainTracker/newsDetail.jsp";
        }
    });
}

function loadMessages() {
    $('#userTo').autocomplete({
        source: function(request, response) {
          $.get( "MessageLoader?filtro='ok'", function( data ) {
                var result = "";
                var match = $("#userTo").val();
                var values = data.split(",");
                var i = 0;
                while(i < values.length) {
                    if(values[i].indexOf(match) > -1) {
                        result += values[i] + ",";
                    }
                    i++;
                }
                result.substring(0, result.lastIndexOf(","));
                response(result.split(","));
          });  
        }
    });
}

function loadMessageDetail(userFrom, userTo, date, subject, text) {
    $("#userFrom").val(userFrom);
    $("#userTo").val(userTo);
    $("#date").val(date);
    $("#subject").val(subject);
    $("#text").val(text);

    $("#userFrom").prop("disabled", true);
    $("#userTo").prop("disabled", true);
    $("#date").prop("disabled", true);
    $("#subject").prop("disabled", true);
    $("#text").prop("disabled", true);
}

function deleteMessage(id) {
    $.ajax({
        url : 'MessageLoader?delete=1&idDel=' + id,
        async: true,
        type: "GET",
        dataType: "text",
        beforeSend: function(responseText) {
            waitingDialog.show();
        },
        success: function(responseText) {
            waitingDialog.hide();
            location.href = "/MountainTracker/messages.jsp";
        }
    });
}

