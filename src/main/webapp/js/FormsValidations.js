$(document).ready(function() {
      $( "#signupform" ).validate({
          rules:{
             username: {
                required: true,
                minlength: 4
              },
              password: {
                required: true,
                minlength: 8
              },
              nombre: {
                required: true
              },
              apellidos: {
                required: true
              },
              email: {
                required: true,
                email: true
              } 
          },
          messages: {
            username: "El nombre de usuario es obligatorio",
            password: "La password es obligatoria",
            nombre: "El nombre es obligatorio",
            apellidos: "El/los apellido/apellidos es/son obligatorios",
            email: {
              required: "El email es obligatorio",
              email: "El formato de email es --> name@domain.com"
            }
          }/*,
          submitHandler: function(form) {
            $(form).submit();
          }*/
      });
     $( "#loginForm" ).validate({
          rules:{
             loginUsername: {
                required: true,
                minlength: 4
              },
              loginPassword: {
                required: true,
                minlength: 8
              }
          },
          messages: {
            username: "El nombre de usuario es obligatorio para iniciar sesión",
            password: "La password es obligatoria para iniciar sesión"
          }/*,
          submitHandler: function(form) {
            $(form).submit();
          }*/
      });
    $("form").submit(function() {
        waitingDialog.show(); 
    });
    $( "#birthday" ).datepicker({
        dateFormat:"dd-mm-yy"
    });
    $( "#creaNew" ).validate({
          rules:{
             title: {
                required: true
              },
              text: {
                required: true
              }
          },
          messages: {
            title: "Title is mandatory",
            text: "New Text is mandatory",
          },
          submitHandler: function(form) {
            $("#text").val(CKEDITOR.instances.editor.getData());
            $(form).submit();
          }
      });
});


