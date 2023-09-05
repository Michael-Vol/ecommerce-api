# Spring Boot Ecommerce API

#### A RESTful API for an e-commerce application built with Spring Boot.

## :page_with_curl: Features

- [x] :door: Secure User Registration and Login
- [x] :busts_in_silhouette: User Profile Management
- [x] :closed_lock_with_key: Token-based Authentication
- [x] :open_file_folder: Product Catalog Management
- [x] :shopping_cart: Add, Update and Remove Products in Shopping Cart
- [x] :page_facing_up: Order Checkout, Shipping and Payment

## :rocket: Getting Started

1. Clone this repository:

```bash
git clone https://github.com/Michael-Vol/ecommerce-api.git
```

2. Create a MySQL database and
   run [this SQL script](https://github.com/Michael-Vol/ecommerce-api/blob/main/utils/create_ecommerce_db.sql)  to
   create
   the data structure.


3. Add the following env variables to the application.properties file as follows:

```properties
spring.datasource.url={YOUR_DATABASE_URL}
spring.datasource.username={YOUR_DATABASE_USERNAME}
spring.datasource.password={YOUR_DATABASE_PASSWORD}
spring.jpa.hibernate.ddl-auto=update
spring.jpa.hibernate.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
ecommerce.api.jwtSecret={YOUR_JWT_SECRET}
```

4. Build and Run the Application:

```bash
./mvnw clean install
./mvnw spring-boot:run
```

### API Endpoints