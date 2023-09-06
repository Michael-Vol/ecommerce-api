# Spring Boot Ecommerce API

#### A RESTful API for an e-commerce application built with Spring Boot.

## :page_with_curl: Features

- [x] :door: Secure User Registration and Login
- [x] :busts_in_silhouette: User Profile Management
- [x] :closed_lock_with_key: Token-based Authentication
- [x] :open_file_folder: Product Catalog Management & Search
- [x] :shopping_cart: Add, Update and Remove Products in Shopping Cart
- [x] :page_facing_up: Order Checkout, Shipping and Payment

## :rocket: Getting Started

1. Clone this repository:

```bash
git clone https://github.com/Michael-Vol/ecommerce-api.git
```

2. Create a MySQL database and
   run [this SQL script](https://github.com/Michael-Vol/ecommerce-api/blob/main/utils/create_ecommerce_db.sql) to
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

## :globe_with_meridians: API Endpoints

### :closed_lock_with_key: Authentication

| Method | Endpoint             | Description          | Request Body           |
| ------ | -------------------- | -------------------- | ---------------------- |
| POST   | api/v1/auth/register | Registers a new user | [JSON](#auth_register) |
| POST   | api/v1/auth/login    | Authenticates a user | [JSON](#auth_login)    |
| POST   | api/v1/auth/logout   | Logs out a user      |                        |

### :busts_in_silhouette: User Profile Management

| Method | Endpoint        | Description                     | Request Body |
| ------ | --------------- | ------------------------------- | ------------ |
| GET    | api/v1/users/me | Gets the current user's profile |              |

### :open_file_folder: Product Catalog Management

| Method | Endpoint                     | Description                 | Request Body               |
| ------ | ---------------------------- | --------------------------- | -------------------------- |
| POST   | /api/v1/products             | Creates a new product       | [JSON](#products_create)   |
| GET    | /api/v1/products             | Gets all products           | [Params](#products_get)    |
| GET    | /api/v1/products/{productId} | Gets a product by id        |                            |
| GET    | /api/v1/products/search      | Searches in product catalog | [Params](#products_search) |
| PATCH  | /api/v1/products/{productId} | Updates a product by id     | [JSON](#products_update)   |
| DELETE | /api/v1/products/{productId} | Deletes a product by id     |                            |

### :shopping_cart: Shopping Cart Management

| Method | Endpoint                     | Description                          | Request Body           |
| ------ | ---------------------------- | ------------------------------------ | ---------------------- |
| POST   | /api/v1/carts/addToCart      | Adds a product to cart               | [JSON](#cart_add)      |
| GET    | /api/v1/carts                | Gets the user's cart info            |                        |
| PATCH  | /api/v1/carts/updateQuantity | Updates an item's quantity           | [JSON](#cart_updateq)  |
| DELETE | /api/v1/carts/removeFromCart | Removes an item from cart            | [JSON](#cart_remove)   |
| DELETE | /api/v1/carts/clearCart      | Empties the user's cart              |                        |
| POST   | /api/v1/carts/checkout       | Creates an order from the cart items | [JSON](#cart_checkout) |

### :page_facing_up: Order Management

| Method | Endpoint                        | Description                       | Request Body |
| ------ | ------------------------------- | --------------------------------- | ------------ |
| GET    | /api/v1/orders/{orderId}        | Gets an order by id               |              |
| GET    | /api/v1/orders/{orderId}/status | Gets the status of an order by id |              |
| PATCH  | /api/v1/orders/{orderId}/status | Updates an order's status         |              |

## Sample JSON Request Bodies

##### <a id="auth_register">User Registration</a>

```json
{
  "firstName": "John",
  "lastName": "Appleseed",
  "username": "john",
  "email": "john@example.com",
  "password": "password",
  "address": "225 Brickyard Lane Racine, WI 53402"
}
```

##### <a id="auth_login">User Login</a>

```json
{
  "email": "john@example.com",
  "password": "password"
}
```

##### <a id="products_create"> Create Product</a>

```json
{
  "name": "Apple iPhone 12 Pro Max",
  "description": "Apple iPhone 12 Pro Max 128GB Graphite",
  "price": 1099.99,
  "quantity": 10,
  "category": "Electronics",
  "brand": "Apple",
  "image": "https://i.imgur.com/0oY8F0X.jpg"
}
```

##### <a id="products_get"> Get Products</a>

```
Query Params:

   - page
   - size
   - sortBy
   - direction

```

##### <a id="products_search"> Search Products</a>

```
Query Params:

   - category
   - minPrice
   - maxPrice
   - sortDirection
   - sortBy
   - page
   - pageSize
```

##### <a id="products_update"> Update Product by Id</a>

```json
{
  "name": "Apple iPhone 11 Pro Max",
  "description": "Apple iPhone 1 Pro Max 128GB Graphite",
  "price": 899.99,
  "quantity": 10
}
```

##### <a id="cart_add"> Add Product to Cart</a>

```json
{
  "productId": 1,
  "quantity": 2
}
```

##### <a id="cart_updateq"> Update Cart Item Quantity</a>

```json
{
  "productId": 1,
  "quantity": 3
}
```

##### <a id="cart_remove"> Remove Product From Cart</a>

```json
{
  "productId": 1
}
```

##### <a id="cart_checkout"> Checkout & Create Order</a>

```json
{
  "cartId": 6,
  "shippingAddress": "169 Tallwood Drive Jackson, NJ 08527",
  "billingAddress": "169 Tallwood Drive Jackson, NJ 08527",
  "paymentMethod": "CREDIT_CARD"
}
```
