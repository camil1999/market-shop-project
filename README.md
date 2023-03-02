# Technologies used for this project
- Spring Data JPA
- Spring Web
- Spring Security
- Swagger
- Lombok
- H2
- MySql
- Model Mapper
- DevTools
- Validation

# Requirement for running
- Postman - for testing project
- Eclipse IDE - for opening project and checking code

# How to run locally
1. Make sure that, you have installed java to your pc and added it to system path
2. Make sure that, you have installed Mysql to your pc
3. Make sure that, you have Eclipse IDE  or anther IDE installed
4. Clone project to your pc
5. Open it with your favorite editor
6. Run it
7. Test endpoints you are interested in

#Endpoint
<ul>
<li> Main url-http://localhost:7777/marketshop </li>
<li> POST http://localhost:7777/marketshop/user/login for login users </li>
<li> POST http://localhost:7777/marketshop/user/signUp for registration </li>
<li> GET http://localhost:7777/marketshop/user/ for get all users </li>
<li> GET http://localhost:7777/marketshop/admin/ for get all admins </li>
<li> PUT http://localhost:7777/marketshop/admin/{id} for update a admin </li>
<li> DELETE http://localhost:7777/marketshop/admin/{id} for delete a admin </li>
<li> GET http://localhost:7777/marketshop/cashier/ for get all cashiers </li>
<li> PUT http://localhost:7777/marketshop/cashier/{id} for update a cashier </li>
<li> DELETE http://localhost:7777/marketshop/cashier/{id} for delete a cashier </li>
<li> GET http://localhost:7777/marketshop/product/barcode for get all products by barcode </li>
<li> POST http://localhost:7777/marketshop/product/ for save a product </li> 
<li> POST http://localhost:7777/marketshop/product/incoming for save a incoming product </li>
<li> DELETE http://localhost:7777/marketshop/product/ for delete a product </li>
<li> PUT http://localhost:7777/marketshop/product/{id}/ for update a product </li>
<li> GET http://localhost:7777/marketshop/product/search/ for search a product </li>
<li> GET http://localhost:7777/marketshop/report/total-report/ for get total report </li>
<li> GET http://localhost:7777/marketshop/report/by-date/ for get report by date </li>
<li> GET http://localhost:7777/marketshop/sale/ for get all sales </li>
<li> GET http://localhost:7777/marketshop/sale/date/ for get sales by date </li>
<li> POST http://localhost:7777/marketshop/sale/ for save sale </li>
<li> Swagger url: http://localhost:7777/marketshop/swagger-ui.html#/ </li>
</ul>
