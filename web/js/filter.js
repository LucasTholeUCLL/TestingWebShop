function filterProducts() {
    var input, filter, table, tr, td, i;
    input = document.getElementById("productFilterInput");
    filter = input.value.toUpperCase();
    table = document.getElementById("products");
    tr = table.getElementsByTagName("tr");
    for (i = 0; i < tr.length; i++) {
        var display = false;
        for (j = 0; j < 2; j++) {
            td = tr[i].getElementsByTagName("td")[j];
            if (td) {
                if (td.innerHTML.toUpperCase().indexOf(filter) > -1) display=true;
            }
        }
        if (display) tr[i].style.display = "";
        else tr[i].style.display = "none";
    }
}