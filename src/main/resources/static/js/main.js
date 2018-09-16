'use strict';

var timeReportUploadForm = document.querySelector('#timeReportUploadForm');
var getReportForm = document.querySelector('#getReportForm');
var timeReportUploadInput = document.querySelector('#timeReportUploadInput');
var timeReportUploadError = document.querySelector('#timeReportUploadError');
var timeReportUploadSuccess = document.querySelector('#timeReportUploadSuccess');


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
            timeReportUploadError.innerHTML = response.message;
            timeReportUploadError.style.display = "block";
        }
    }
    xhr.send(formData);
}

function getReport() {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "/getReport");

    xhr.onload = function() {
        console.log(xhr.responseText);
        var response = JSON.parse(xhr.responseText);
        if(xhr.status == 200) {
            timeReportUploadError.style.display = "none";
            timeReportUploadSuccess.innerHTML = "<p>Report retrieved Successfully!</p><p>Current pay report:</p>" + drawTable(response);
            timeReportUploadSuccess.style.display = "block";
        } else {
            timeReportUploadSuccess.style.display = "none";
            timeReportUploadError.innerHTML = response.message;
            timeReportUploadError.style.display = "block";
        }
    }
    xhr.send();
}

function drawTable(data) {
  var html = '<table id="table"><thead><tr><th>Employee Id</th><th>Pay Period</th><th>Amount Paid</th></tr></thead>';
  for (var i = 0; i < data.length; i++) {
    html += '<tr><td>' + data[i].employeeId + '</td><td>' + data[i].period + '</td><td>' + data[i].amountPaid + '</td></tr>';
  }
  return html + '</table>';
}

timeReportUploadForm.addEventListener('submit', function(event){
    var files = timeReportUploadInput.files;
    if(files.length === 0) {
        timeReportUploadError.innerHTML = "Please select a file";
        timeReportUploadError.style.display = "block";
    }
    uploadTimeReport(files[0]);
    event.preventDefault();
}, true);

getReportForm.addEventListener('submit', function(event){
    console.log("listener trigger");
    getReport();
    event.preventDefault();
}, true);