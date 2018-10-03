# Payroll Application

_(obs: build instructions and main features found under Documentation section below)_

## Project Description

Hours are tracked per employee, per day in comma-separated value files (CSV).
Each individual CSV file is known as a "time report", and will contain:

1. A header, denoting the columns in the sheet (`date`, `hours worked`,
   `employee id`, `job group`)
1. 0 or more data rows
1. A footer row where the first cell contains the string `report id`, and the
   second cell contains a unique identifier for this report.

Our partner has guaranteed that:

1. Columns will always be in that order.
1. There will always be data in each column.
1. There will always be a well-formed header line.
1. There will always be a well-formed footer line.

### What your web-based application must do:

We've agreed to build the following web-based prototype for our partner.

1. Your app must accept (via a form) a comma separated file with the schema
   described in the previous section.
1. Your app must parse the given file, and store the timekeeping information in
   a relational database for archival reasons.
1. After upload, your application should display a _payroll report_. This
   report should also be accessible to the user without them having to upload a
   file first.
1. If an attempt is made to upload two files with the same report id, the
   second upload should fail with an error message indicating that this is not
   allowed.

The payroll report should be structured as follows:

1. There should be 3 columns in the report: `Employee Id`, `Pay Period`,
   `Amount Paid`
1. A `Pay Period` is a date interval that is roughly biweekly. Each month has
   two pay periods; the _first half_ is from the 1st to the 15th inclusive, and
   the _second half_ is from the 16th to the end of the month, inclusive.
1. Each employee should have a single row in the report for each pay period
   that they have recorded hours worked. The `Amount Paid` should be reported
   as the sum of the hours worked in that pay period multiplied by the hourly
   rate for their job group.
1. If an employee was not paid in a specific pay period, there should not be a
   row for that employee + pay period combination in the report.
1. The report should be sorted in some sensical order (e.g. sorted by employee
   id and then pay period start.)
1. The report should be based on all _of the data_ across _all of the uploaded
   time reports_, for all time.

As an example, a sample file with the following data:

<table>
<tr>
  <th>
    date
  </th>
  <th>
    hours worked
  </th>
  <th>
    employee id
  </th>
  <th>
    job group
  </th>
</tr>
<tr>
  <td>
    4/11/2016
  </td>
  <td>
    10
  </td>
  <td>
    1
  </td>
  <td>
    A
  </td>
</tr>
<tr>
  <td>
    14/11/2016
  </td>
  <td>
    5
  </td>
  <td>
    1
  </td>
  <td>
    A
  </td>
</tr>
<tr>
  <td>
    20/11/2016
  </td>
  <td>
    3
  </td>
  <td>
    2
  </td>
  <td>
    B
  </td>
</tr>
</table>

should produce the following payroll report:

<table>
<tr>
  <th>
    Employee ID
  </th>
  <th>
    Pay Period
  </th>
  <th>
    Amount Paid
  </th>
</tr>
<tr>
  <td>
    1
  </td>
  <td>
    1/11/2016 - 15/11/2016
  </td>
  <td>
    $300.00
  </td>
</tr>
  <td>
    2
  </td>
  <td>
    16/11/2016 - 30/11/2016
  </td>
  <td>
    $90.00
  </td>
</tr>
</table>

   
## Build instructions

This project was built using the Java Spring Boot Framework. To run it, execute from the root folder:

``mvn spring-boot:run``

Or alternatively, build the project with:

``mvn clean install``

And execute it with: 

``java -jar target/payroll-0.0.1-SNAPSHOT.jar``

The application starts a webserver on `localhost:8080`, where both options are available:
1. Upload a CSV file following the specified format, showing the current pay report after;
1. Get the current pay report, based on previous uploaded time reports on DB.

For simplicity, the application uses an in-memory H2 database, provided by Spring Boot.

## Main features

All input file entries are stored in DB, but would be redundant to recalculate historically during each upload of a new file,
which would certainly slow down the application as the number of records grow.  
The application was built to just increment the current _Amount Paid_ values (calculated on previous input uploads) 
per combination _employeeId+PayPeriod_, based on just the new entries received in the current CSV being processed.

For the pay report sorting (based on _EmployeeId_, followed by _Pay Period_), the implementation consists of:
- The _Model_ class for _PayReport_ extending _Comparable_ Interface;
- The method _compareTo_ is _overridden_ with the custom logic, to compare employeeId, and then compare the period if needed. 
- A call to `Collections.sort(List<PayReport>)` then sorts a given list following this customization.

## Helpers

I'm not very comfortable with frontend in general, so the following websites were extremely helpful on getting this exercise
to its current state:

1.	Frontend upload file base:
https://www.callicoder.com/spring-boot-file-upload-download-rest-api-example/

2.	Convert input csv File to Objects:
https://www.linkedin.com/pulse/spring-boot-upload-csv-file-store-records-database-garaddi/

3.	Frontend print report
https://stackoverflow.com/questions/41122990/output-json-to-html-table

4.	Simple CSS for report
http://johnsardine.com/freebies/dl-html-css/simple-little-tab/

5.	Read last line to get reportId:
https://stackoverflow.com/questions/686231/quickly-read-the-last-line-of-a-text-file/7322581#7322581
