/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {
    
    table = $('#routesTab').dataTable({
        "bFilter": false,
        "processing": true,
        "async": false,
        "ajax": {
            "url": "RoutesTableLoader",
            "dataSrc": "routes",
            "type": "GET"
        }
    });
    $('#routesTab tbody').on( 'click', 'tr', function () {
        if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        }
        else {
            table.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    });
   
});

