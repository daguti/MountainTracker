<style>
    .table-striped>tbody>tr:nth-of-type(odd).selected{background-color:red}
    .table-striped>tbody>tr:nth-of-type(even).selected{background-color:red}
</style>
<div id="tableSearchBox">
    <form class="form-horizontal">
        <div class="form-group">
            <label for="nombre" style="padding-left: 15px;" class="control-label">Search Box: </label>
            <div class="input-group" style="padding-left:15px;max-width:300px;">
                <input type="text" id="searchbox" class="form-control">
                <div class="input-group-addon"><i class="glyphicon glyphicon-search"></i></div>
            </div>
        </div>
    </form>
</div>
<div class="panel panel-inverse">
   <div id="inverse-heading" class="panel-heading">
       <span style="color: white;" class="glyphicon glyphicon-globe"></span>
      <h5 class="panel-inverse-title" style="display: inline-block;">
         Routes
      </h5>
   </div>
   <div class="table-responsive">
       <table id="routesTab" class="table table-striped table-bordered" cellSpacing="0">
            <thead>
                <tr>
                    <th>Ref. Route</th>
                    <th>Route Name</th>
                    <th>Description</th>
                    <th>Distance(m)</th>
                    <th>Total Ascend(m)</th>
                    <th>Total Descend(m)</th>
                    <th>Min Height(m)</th>
                    <th>Max Height(m)</th>
                </tr>
            </thead>
        </table> 
   </div>
</div>
