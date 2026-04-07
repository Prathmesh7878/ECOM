# ECOM — Full Stack E-Commerce Application

A full-stack e-commerce web application built with a **Spring Boot** REST API backend and a **React** frontend. It supports product listing, image upload and retrieval, category filtering, cart management, and a checkout flow.

---

## Tech Stack

| Layer      | Technology                                      |
|------------|-------------------------------------------------|
| Backend    | Java 17, Spring Boot 3.5, Spring Data JPA, Lombok |
| Database   | H2 (in-memory)                                  |
| Frontend   | React 18, Vite, React Router v6, Axios          |
| UI Library | Bootstrap 5, React-Bootstrap                    |

---

## Project Structure

```
ECOM-master/
├── backend/
│   └── ecomApp/                  # Spring Boot application
│       ├── src/main/java/com/example/ecom/ecomApp/
│       │   ├── controller/       # ProductController.java
│       │   ├── product/          # Product entity
│       │   ├── repository/       # ProductRepository (JPA)
│       │   └── service/          # ProductService
│       └── src/main/resources/
│           └── application.properties
└── frontend/                     # React + Vite application
    └── src/
        ├── components/           # Home, Navbar, Cart, Product, AddProduct, CheckoutPopup
        ├── Context/              # Global state (AppContext)
        └── axios.jsx             # Axios base config
```

---

## Features

- **Product Listing** — Browse all products in a responsive card grid with product image, name, brand, and price
- **Category Filtering** — Filter products by category via the Navbar
- **Product Detail** — Click any product card to view its full details
- **Add Product** — Admin form to add a new product with name, brand, description, price, category, stock quantity, release date, availability, and an image upload
- **Image Storage** — Product images are stored as binary blobs in the database and served via a dedicated image endpoint
- **Cart** — Add products to cart, adjust quantities, remove items; cart state is persisted to `localStorage`
- **Checkout** — Checkout popup modal to complete an order and clear the cart

---

## Getting Started

### Prerequisites

- Java 17+
- Maven 3.8+
- Node.js 18+ and npm

---

### Backend Setup

```bash
cd backend/ecomApp
./mvnw spring-boot:run
```

The API will start on **http://localhost:8080**.

The H2 in-memory database is auto-configured — no external database setup is needed. The H2 console is accessible at `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:pratham`).

---

### Frontend Setup

```bash
cd frontend
npm install
npm run dev
```

The React app will start on **http://localhost:5173**.

---

## API Endpoints

| Method | Endpoint                    | Description                        |
|--------|-----------------------------|------------------------------------|
| GET    | `/api/products`             | Fetch all products                 |
| GET    | `/api/product/{id}`         | Fetch a product by ID              |
| POST   | `/api/product`              | Add a new product (multipart form) |
| GET    | `/api/product/{pid}/image`  | Fetch product image as binary      |

### Add Product — Request Format

`POST /api/product` accepts `multipart/form-data` with two parts:

- `product` — JSON blob with product fields (name, brand, desc, price, category, releaseDate, available, quantity)
- `imageFile` — the image file

---

## Configuration

`backend/ecomApp/src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:h2:mem:pratham
spring.datasource.driverClassName=org.h2.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show.sql=true
spring.jpa.defer-datasource-initialization=true
```

Frontend API base URL is set in `frontend/src/axios.jsx`:

```js
baseURL: "http://localhost:8080/api"
```

---

## Frontend Routes

| Path             | Component     | Description              |
|------------------|---------------|--------------------------|
| `/`              | `Home`        | Product listing grid     |
| `/product/:id`   | `Product`     | Product detail page      |
| `/add_product`   | `AddProduct`  | Add new product form     |
| `/cart`          | `Cart`        | Shopping cart            |

---

## Notes

- The H2 database is **in-memory** — all data resets on each backend restart.
- To switch to a persistent database (e.g. PostgreSQL), update `application.properties` with the appropriate datasource URL and driver, and add the corresponding JDBC dependency to `pom.xml`.
- CORS is globally enabled on the backend (`@CrossOrigin` on the controller) for local development.
