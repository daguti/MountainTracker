<style>
    .table-striped>tbody>tr:nth-of-type(odd).selected{background-color:red}
    .table-striped>tbody>tr:nth-of-type(even).selected{background-color:red}
</style>
<div class="panel panel-inverse">
   <div id="inverse-heading" class="panel-heading">
       <span style="color: white;" class="glyphicon glyphicon-envelope"></span>
      <h5 class="panel-inverse-title" style="display: inline-block;">
         Sended Messages
      </h5>
   </div>
   <div class="table-responsive">
       <table id="sendedTab" class="table table-striped table-bordered" cellSpacing="0">
            <thead>
                <tr>
                    <th>From</th>
                    <th>To</th>
                    <th>Date</th>
                    <th>Subject</th>
                    <th>Message</th>
                </tr>
            </thead>
        </table> 
   </div>
</div>
