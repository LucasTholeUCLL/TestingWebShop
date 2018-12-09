var currentTab = 0;
showTab(currentTab);
document.getElementById("myBar").style.width = 34 + '%';

function showTab(n) {
    var x = document.getElementsByClassName("tab");
    x[n].style.display = "block";
    if (n == 0) {
        document.getElementById("prevBtn").style.display = "none";
    } else {
        document.getElementById("prevBtn").style.display = "inline";
    }
    if (n == (x.length - 1)) {
        document.getElementById("nextBtn").innerHTML = "Submit";
    } else {
        document.getElementById("nextBtn").innerHTML = "Next";
    }
}

function nextPrev(n) {
    if (n == -1) move(n);
    var x = document.getElementsByClassName("tab");
    if (n == 1 && !validateForm()) return false;
    if (n == 1) move(n);
    x[currentTab].style.display = "none";
    currentTab = currentTab + n;
    if (currentTab >= x.length) {
        document.getElementById("orderForm").submit();
        return false;
    }
    document.getElementById("adrRead").innerHTML = document.getElementById("adr").value;
    document.getElementById("cityRead").innerHTML = document.getElementById("city").value;
    document.getElementById("stateRead").innerHTML = document.getElementById("state").value;
    document.getElementById("zipRead").innerHTML = document.getElementById("zip").value;
    showTab(currentTab);
}

function validateForm() {
    var x, y, i, valid = true;
    x = document.getElementsByClassName("tab");
    y = x[currentTab].getElementsByTagName("input");
    for (i = 0; i < y.length; i++) {
        if (y[i].value.trim() == "") {
            y[i].className += " invalid";
            valid = false;
            document.getElementById("orderFormWarning").innerHTML = "Please fill in all fields!";
        }
    }
    if (valid) {
        for (i = 0; i < y.length; i++) {
            if (y[i].className.includes("numberInput")) {
                if (isNaN(y[i].value.replace("-", ""))) {
                    y[i].className += " invalid";
                    document.getElementById("orderFormWarning").innerHTML = "Not a valid number!";
                    valid = false;
                }
            }
        }
    }
    if (valid) document.getElementById("orderFormWarning").innerHTML = "";
    return valid;
}

function move(amt) {
    var elem = document.getElementById("myBar");
    var widthString = elem.style.width.toString();
    var width = widthString.substr(0, widthString.length - 1)
    if (width < 99) {
        var next = 33;

        var id = setInterval(frame, 10);

        function frame() {
            if (next < 1) {
                clearInterval(id);
            } else {
                next--;

                if (amt > 0) width++;
                else width--;

                elem.style.width = width + '%';
            }
        }
    }
}