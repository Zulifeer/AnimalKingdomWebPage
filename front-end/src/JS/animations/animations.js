import $ from 'jquery';
import * as dt from 'datatables.net';
$(document).ready(function () {

    $('#sidebarCollapse').on('click', function () {
        $('#sidebar').toggleClass('active');
        $('#overlay').toggleClass('active');
    });

    var info_table = $('#info-table').DataTable({
        "searching": true,
        "pagingType": "simple_numbers"
    });
    var table = document.getElementById("user-table"); 
    if (table) {
        for (var i = 0; i < table.rows.length; i++) {
            table.rows[i].onclick = function() {
            tableText(this);
            };
        }
    }
    function tableText(tableRow) {
        var id = tableRow.childNodes[1].innerHTML;
        var name = tableRow.childNodes[3].innerHTML;
        var obj = {'id': id, 'name': name};
        console.log(obj);
        return '<p> Testing </p>';
    }
    $('.dataTables_length').addClass('bs-select');
}); 