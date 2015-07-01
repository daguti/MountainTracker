<script type="text/javascript">
 
    $(document).ready(function () {
        editorResize();
        $(window).resize(function(){
           editorResize(); 
        });
    });
    function editorResize() {
        if (CKEDITOR.instances['editor']) {
            CKEDITOR.instances['editor'].destroy();
        }
        if(screen.width < 500) {
            CKEDITOR.replace( 'editor', {
                toolbarGroups: [
                    { name: 'document',	   groups: [ 'mode', 'document' ] },			// Displays document group with its two subgroups.
                    { name: 'clipboard',   groups: [ 'clipboard', 'undo' ] },			// Group's name will be used to create voice label.
                    '/',																// Line break - next group will be placed in new line.
                    { name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },
                    { name: 'links' }
                ],
                removePlugins: 'save'
            });
        } else {
          CKEDITOR.replace( 'editor', {
                removePlugins: 'save'
            });  
        }
    }
</script>
<div>
    <div>
        <textarea id="editor" rows="10" cols="80"></textarea>
    </div>
</div>
