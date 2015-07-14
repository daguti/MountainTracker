function openImageModal(image) {
    var src = $(image).attr('src');
    var img = '<img src="' + src + '" class="img-responsive"/>';

    //start of new code new code
    var index = $(image).parent('li').index();   
    //index = 0;
    var html = '';
    html += img;                
    html += '<div style="height:25px;clear:both;display:block;">';
    html += '<a class="controls next" href="'+ (index+2) + '">next &raquo;</a>';
    html += '<a class="controls previous" href="' + (index) + '">&laquo; prev</a>';
    html += '</div>';

    $('#myModal').modal();
    //$('#myModal .modal-body').html(html);
    $('#myModal').on('shown.bs.modal', function(){
            $('#myModal .modal-body').html(html);
            //new code
            //$('a.controls').trigger('click');
    })
    $('#myModal').on('hidden.bs.modal', function(){
            $('#myModal .modal-body').html('');
    });	
}         
$(document).on('click', 'a.controls', function(){
    var index = $(this).attr('href');
    var src = $('.ui-accordion-content-active ul.row li:nth-child('+ index +') img').attr('src');             

    $('.modal-body img').attr('src', src);

    var newPrevIndex = parseInt(index) - 1; 
    var newNextIndex = parseInt(newPrevIndex) + 2; 

    if($(this).hasClass('previous')){               
            $(this).attr('href', newPrevIndex); 
            $('a.next').attr('href', newNextIndex);
    }else{
            $(this).attr('href', newNextIndex); 
            $('a.previous').attr('href', newPrevIndex);
    }

    var total = $('ul.row li').length + 1; 
    //hide next button
    if(total === newNextIndex){
            $('a.next').hide();
    }else{
            $('a.next').show()
    }            
    //hide previous button
    if(newPrevIndex === 0){
            $('a.previous').hide();
    }else{
            $('a.previous').show()
    }


    return false;
});


function openImageModalAlbum(image) {
    var src = $(image).attr('src');
    var img = '<img src="' + src + '" class="img-responsive"/>';

    //start of new code new code
    var index = $(image).parent('li').index();   
    //index = 0;
    var html = '';
    html += img;                
    html += '<div style="height:25px;clear:both;display:block;">';
    html += '<a class="controlers next" href="'+ (index+2) + '">next &raquo;</a>';
    html += '<a class="controlers previous" href="' + (index) + '">&laquo; prev</a>';
    html += '</div>';

    $('#myModal').modal();
    //$('#myModal .modal-body').html(html);
    $('#myModal').on('shown.bs.modal', function(){
            $('#myModal .modal-body').html(html);
            //new code
            //$('a.controls').trigger('click');
    })
    $('#myModal').on('hidden.bs.modal', function(){
            $('#myModal .modal-body').html('');
    });	
}         
$(document).on('click', 'a.controlers', function(){
    var index = $(this).attr('href');
    var src = $('ul.row li:nth-child('+ index +') img').attr('src');             

    $('.modal-body img').attr('src', src);

    var newPrevIndex = parseInt(index) - 1; 
    var newNextIndex = parseInt(newPrevIndex) + 2; 

    if($(this).hasClass('previous')){               
            $(this).attr('href', newPrevIndex); 
            $('a.next').attr('href', newNextIndex);
    }else{
            $(this).attr('href', newNextIndex); 
            $('a.previous').attr('href', newPrevIndex);
    }

    var total = $('ul.row li').length + 1; 
    //hide next button
    if(total === newNextIndex){
            $('a.next').hide();
    }else{
            $('a.next').show()
    }            
    //hide previous button
    if(newPrevIndex === 0){
            $('a.previous').hide();
    }else{
            $('a.previous').show()
    }


    return false;
});