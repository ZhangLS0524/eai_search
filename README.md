# Virtual Shop API

A Spring Boot REST API for managing products with advanced search functionality.

## Features

- Product CRUD operations
- Advanced search with word boundary matching
- Filtering by category, seller, price, rating, etc.
- PostgreSQL database integration
- Supabase database support

## API Endpoints

### Products
- `GET /Virtualshop/products` - Get all products
- `GET /Virtualshop/products/{id}` - Get product by ID
- `POST /Virtualshop/products` - Create new product
- `PUT /Virtualshop/products/{id}` - Update product
- `DELETE /Virtualshop/products/{id}` - Delete product

### Search & Filter
- `GET /Virtualshop/products/search?keyword={keyword}` - Search products
- `GET /Virtualshop/products/filter` - Filter products with multiple criteria
- `GET /Virtualshop/products/category?category={category}` - Get products by category
- `GET /Virtualshop/products/seller?sellerName={sellerName}` - Get products by seller
- `GET /Virtualshop/products/price-original?priceOriginal={price}` - Filter by original price
- `GET /Virtualshop/products/price-actual?priceActual={price}` - Filter by actual price
- `GET /Virtualshop/products/item-rating?itemRating={rating}` - Filter by item rating
- `GET /Virtualshop/products/total-rating?totalRating={rating}` - Filter by total rating
- `GET /Virtualshop/products/sold?totalSold={count}` - Filter by total sold
- `GET /Virtualshop/products/favorite?favoriteCount={count}` - Filter by favorite count

## Local Development

1. Clone the repository
2. Configure your database in `application.properties`
3. Run: `./mvnw spring-boot:run`

## Deployment on Render

### Prerequisites
- GitHub repository with your code
- Supabase database (or any PostgreSQL database)

### Steps

1. **Push your code to GitHub**
   ```bash
   git add .
   git commit -m "Initial commit"
   git push origin main
   ```

2. **Create Render Account**
   - Go to [render.com](https://render.com)
   - Sign up with your GitHub account

3. **Create New Web Service**
   - Click "New +" → "Web Service"
   - Connect your GitHub repository
   - Select the repository

4. **Configure the Service**
   - **Name**: `virtualshop-api` (or any name you prefer)
   - **Environment**: `Java`
   - **Build Command**: `./mvnw clean package -DskipTests`
   - **Start Command**: `java -jar target/project-0.0.1-SNAPSHOT.jar`

5. **Set Environment Variables**
   - `SPRING_PROFILES_ACTIVE`: `production`
   - `SPRING_DATASOURCE_URL`: Your Supabase database URL
   - `SPRING_DATASOURCE_USERNAME`: `postgres`
   - `SPRING_DATASOURCE_PASSWORD`: Your Supabase database password

6. **Deploy**
   - Click "Create Web Service"
   - Render will automatically build and deploy your application

### Environment Variables Format

For Supabase, your database URL should look like:
```
jdbc:postgresql://db.ockbwzxuklucptbfmmji.supabase.co:5432/postgres
```

## Technology Stack

- **Backend**: Spring Boot 3.5.3
- **Database**: PostgreSQL (Supabase)
- **ORM**: Spring Data JPA / Hibernate
- **Build Tool**: Maven
- **Java Version**: 17

## Search Features

The search functionality supports:
- Word boundary matching (exact word search)
- Space-separated keywords
- Case-insensitive search
- Fallback to partial matches if no exact matches found 