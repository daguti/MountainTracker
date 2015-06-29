/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {
    
    var pat;
    if(location.href.indexOf("myRoutes") !== -1) {
        pat = "RoutesTableLoader?mine=1";
    } else {
        pat = "RoutesTableLoader";
    }
    table = $('#routesTab').dataTable({
        "bFilter": true,
        "processing": false,
        "async": false,
        "ajax": {
            "url": pat,
            "dataSrc": "routes",
            "type": "GET",
            beforeSend: function(responseText) {
                waitingDialog.show();
            }
        }
    });
    $('#routesTab').on('draw.dt', function() {
       waitingDialog.hide(); 
    });
    $('#routesTab tbody').on( 'hover', 'tr', function () {
        $(this).css("cursor", "pointer");
        if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        }
        else {
            table.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    });
    $('#routesTab tbody').on( 'click', 'tr', function () {
        if ( $(this).hasClass('selected') ) {
            location.href="routesDetail.jsp?id='" + $("td:eq(0)", "tbody tr.selected").text() + "'" 
                                        + "&name='"+$("td:eq(1)", "tbody tr.selected").text() + "'" 
                                        + "&description='" +$("td:eq(2)", "tbody tr.selected").text() + "'" 
                                        + "&distance='" +$("td:eq(3)", "tbody tr.selected").text() + "'" 
                                        + "&ascend='" +$("td:eq(4)", "tbody tr.selected").text() + "'" 
                                        + "&descend='" +$("td:eq(5)", "tbody tr.selected").text() + "'" 
                                        + "&minHeight='" +$("td:eq(6)", "tbody tr.selected").text() + "'" 
                                        + "&maxHeight='" +$("td:eq(7)", "tbody tr.selected").text() + "'" ;
        }
    });
    $("#searchbox").keyup(function() {
        table.fnFilter(this.value);
    });
   receivedTable = $('#receivedTab').dataTable({
       "columnDefs": [
            {
                "targets": [4],
                "sClass": "hide_me"
            },
            {
                "targets": [5],
                "sClass": "hide_me"
            },
            {
                "targets": [6],
                "sClass": "hide_me"
            }
        ],
        "bFilter": true,
        "processing": true,
        "async": false,
        "ajax": {
            "url": "MessageLoader",
            "dataSrc": "messages",
            "type": "GET",
            beforeSend: function(responseText) {
                waitingDialog.show();
            }
        }
    });
    $('#receivedTab').on('draw.dt', function() {
        var count = 0;
        var change = false;
        $("#receivedTab tr.odd").each(function() {
           $(this).children("td").each(function(conta) {
               if(conta === 5 && $(this).text() === 'false') {
                   count++;
                   change = true;
               }
           });
           if(change) {
               //$(this).css("background-color", "#BDF5BD");
               $(this).addClass('unread');
               change = false;
           }
       });
       $(".badge").text(count);
       waitingDialog.hide(); 
    });
    $('#receivedTab tbody').on( 'hover', 'tr', function () {
        $(this).css("cursor", "pointer");
        if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        }
        else {
            receivedTable.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    });
    $('#receivedTab tbody').on( 'click', 'tr', function () {
        if ( $(this).hasClass('selected') ) {
            waitingDialog.show();
            $.ajax({
                url : 'MessageLoader?id=' + $("td:eq(6)", "tbody tr.selected").text(),
                async: false,
                type: "GET",
                dataType: "text",
                success: function(responseText) {
                    location.href="messageDetail.jsp?userFrom='" + $("td:eq(0)", "tbody tr.selected").text() + "'" 
                                    + "&userTo='"+$("td:eq(1)", "tbody tr.selected").text() + "'" 
                                    + "&date='" +$("td:eq(2)", "tbody tr.selected").text() + "'" 
                                    + "&subject='" +$("td:eq(3)", "tbody tr.selected").text() + "'" 
                                    + "&text='" +$("td:eq(4)", "tbody tr.selected").text() + "'" 
                                    + "&id='" + $("td:eq(6)", "tbody tr.selected").text() + "'";
                }
            });
            
        }
    });
    
    sendedTable = $('#sendedTab').dataTable({
        "columnDefs": [
            {
                "targets": [ 4 ],
                "sClass": "hide_me"
            },
            {
                "targets": [ 5 ],
                "sClass": "hide_me"
            }
        ],
        "bFilter": true,
        "processing": true,
        "async": false,
        "ajax": {
            "url": "MessageLoader?sended=1",
            "dataSrc": "messages",
            "type": "GET",
            beforeSend: function(responseText) {
                waitingDialog.show();
            }
        }
    });
    $('#sendedTab').on('draw.dt', function() {
       waitingDialog.hide(); 
    });
    $('#sendedTab tbody').on( 'hover', 'tr', function () {
        $(this).css("cursor", "pointer");
        if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        }
        else {
            sendedTable.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    });
    $('#sendedTab tbody').on( 'click', 'tr', function () {
        if ( $(this).hasClass('selected') ) {
            waitingDialog.show();
            location.href="messageDetail.jsp?userFrom='" + $("td:eq(0)", "tbody tr.selected").text() + "'" 
                                        + "&userTo='"+$("td:eq(1)", "tbody tr.selected").text() + "'" 
                                        + "&date='" +$("td:eq(2)", "tbody tr.selected").text() + "'" 
                                        + "&subject='" +$("td:eq(3)", "tbody tr.selected").text() + "'" 
                                        + "&text='" +$("td:eq(4)", "tbody tr.selected").text() + "'"
                                        + "&id='" + $("td:eq(6)", "tbody tr.selected").text() + "'";
        }
    });
    
    $("#searchbox").keyup(function() {
        sendedTable.fnFilter(this.value);
    });
    $("#searchbox").keyup(function() {
        receivedTable.fnFilter(this.value);
    });
});

