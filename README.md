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

<h1>Endpoint</h1>
<ul>
<li> Main url <code>http://localhost:7777/marketshop </code></li>
<li> POST <code>http://localhost:7777/marketshop/user/login</code> for login users </li>
<li> POST <code>http://localhost:7777/marketshop/user/signUp</code> for registration </li>
<li> GET <code>http://localhost:7777/marketshop/user/</code> for get all users </li>
<li> GET <code>http://localhost:7777/marketshop/admin/</code> for get all admins </li>
<li> PUT <code>http://localhost:7777/marketshop/admin/{id}/</code> for update a admin </li>
<li> DELETE <code>http://localhost:7777/marketshop/admin/{id}/</code> for delete a admin </li>
<li> GET <code>http://localhost:7777/marketshop/cashier/</code> for get all cashiers </li>
<li> PUT <code>http://localhost:7777/marketshop/cashier/{id}/</code> for update a cashier </li>
<li> DELETE <code>http://localhost:7777/marketshop/cashier/{id}/</code> for delete a cashier </li>
<li> GET <code>http://localhost:7777/marketshop/product/barcode/</code> for get all products by barcode </li>
<li> POST <code>http://localhost:7777/marketshop/product/</code> for save a product </li> 
<li> POST <code>http://localhost:7777/marketshop/product/incoming/</code> for save a incoming product </li>
<li> DELETE <code>http://localhost:7777/marketshop/product/</code> for delete a product </li>
<li> PUT <code>http://localhost:7777/marketshop/product/{id}/</code> for update a product </li>
<li> GET <code>http://localhost:7777/marketshop/product/search/</code> for search a product </li>
<li> GET <code>http://localhost:7777/marketshop/report/total-report/</code> for get total report </li>
<li> GET <code>http://localhost:7777/marketshop/report/by-date/</code> for get report by date </li>
<li> GET <code>http://localhost:7777/marketshop/sale/</code> for get all sales </li>
<li> GET <code>http://localhost:7777/marketshop/sale/date/</code> for get sales by date </li>
<li> POST <code>http://localhost:7777/marketshop/sale/</code> for save sale </li>
<li> Swagger url: <code>http://localhost:7777/marketshop/swagger-ui.html#/</code> </li>
</ul>
