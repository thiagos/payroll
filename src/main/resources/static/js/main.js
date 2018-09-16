'use strict';

var singleUploadForm = document.querySelector('#singleUploadForm');
var singleFileUploadInput = document.querySelector('#singleFileUploadInput');
var singleFileUploadError = document.querySelector('#singleFileUploadError');
var singleFileUploadSuccess = document.querySelector('#singleFileUploadSuccess');


function uploadTimeReport(file) {
    var formData = new FormData();
    formData.append("file", file);

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/uploadTimeReport");

    xhr.onload = function() {
        console.log(xhr.responseText);
        var response = JSON.parse(xhr.responseText);
        if(xhr.status == 200) {
            timeReportUploadError.style.display = "none";
            timeReportUploadSuccess.innerHTML = "<p>File Uploaded Successfully!</p><p>Current pay report:</p>" + drawTable(response);
            timeReportUploadSuccess.style.display = "block";
        } else {
            timeReportUploadSuccess.style.display = "none";
            timeReportUploadError.innerHTML = (response && response.message) || "Some Error Occurred";
        }
    }

    xhr.send(formData);
}

function drawTable(data) {
  var html = '<table id="table"><thead><tr><th>Employee Id</th><th>Period</th><th>Amount Paid</th></tr></thead>';
  for (var i = 0; i < data.length; i++) {
    html += '<tr><td>' + data[i].employeeId + '</td><td>' + data[i].period + '</td><td>' + data[i].amountPaid + '</td></tr>';
  }
  return html + '</table>';
}

timeReportUploadForm.addEventListener('submit', function(event){
    var files = timeReportUploadInput.files;
    if(files.length === 0) {
        singleFileUploadError.innerHTML = "Please select a file";
        singleFileUploadError.style.display = "block";
    }
    uploadTimeReport(files[0]);
    event.preventDefault();
}, true);