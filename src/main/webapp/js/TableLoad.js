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
        "bFilter": false,
        "processing": true,
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
   
});

