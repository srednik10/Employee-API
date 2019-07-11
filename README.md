# Employee-API

STS runs by default http://localhost:8080

Add new employee
POST http://localhost:8080/employee 
{
	"name": "Name",
	"surname": "Surname",
	"age": 20
}

Update existing employee
PUT http://localhost:8080/employee/{employeeId}
{
	"name": "Name",
	"surname": "Surname",
	"age": 20
}

Get existing employee
GET http://localhost:8080/employee/{employeeId}

Delete existing employee
DELETE http://localhost:8080/employee/1

Filter employees
POST http://localhost:8080/employee/filter

Filters are available for columns: name,surname,age
Available predicates: lessThan, equal, gt, like, between
Values are red from left side, for example age between 10 and 20 is represent by values array [10,20]

Example for filtering employees with names like "a%" and age greater than 1
{
    "predicates": [
        {
            "columnName": "name",
            "predicateName": "like",
            "values": [
                "a%"
            ]
        },
        {
            "columnName": "age",
            "predicateName": "gt",
            "values": [
                1
            ]
        }
    ]
}







