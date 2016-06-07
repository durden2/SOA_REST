<!DOCTYPE html>
<html>
<head>
    <title>Form to create a new resource</title>
</head>
<body>
<form action="/soa/rest/produce/post" method="POST">
    <label for="name">Name</label>
    <input name="name" id="name" />
    <br/>
    <label for="age">Age</label>
    <input type="number" name="age" id="age" />
    <br/>
    <label for="gpa">Gpa</label>
    <input type="number" id="gpa" name="gpa" />
    <br/>
    <input type="submit" value="Submit" />
</form>
</body>
</html>
