# Franquicias API – Spring Boot

### 1. Resumen Stack
-Java 21
-Spring Boot 4
-Maven
-MongoDB
-Docker + Docker Compose

### 2. Ejecucion Local

```bash
docker compose up --build
```
### 3. Endpoints (RESTful)
**URL-BASE**
```http
localhost:8080/v1
```
###  Crear franquicia
**POST**
```http 
 /franchises
```

Request body
```json
{
    "name": "Franquicia 1"
}
```
```curl
curl --location 'localhost:8080/v1/franchises' \
--header 'Content-Type: application/json' \
--data '{
    "name": "Franquicia 1"
}'
```
###  Actualizar nombre de franquicia
**PATCH** 
```http 
/franchises/{franchiseName}/update/name
```
Request body
```json
{
    "name": "Franquicia 2"
}
```
```curl
curl --location --request PATCH 'localhost:8080/v1/franchises/Franquicia 1/update/name' \
--header 'Content-Type: application/json' \
--data '{
    "name": "Franquicia 2"
}'
```
### Agregar sucursal a franquicia
**POST** 
```http 
/franchises/{franchiseName}/branches
```
```json
{
  "name": "Sucursal Norte"
}
```
```curl
curl --location 'localhost:8080/v1/franchises/Franquicia 1/branches' \
--header 'Content-Type: application/json' \
--data '{
  "name": "Sucursal Norte"
}'
```
### Actualizar nombre de sucursal
**PATCH** 
```http 
/franchises/{franchiseName}/branches/{branchName}/update/name
```
```json
{
  "name": "Sucursal Sur"
}
```
```curl
curl --location --request PATCH 'localhost:8080/v1/franchises/Franquicia 2/branches/Sucursal buena/update/name' \
--header 'Content-Type: application/json' \
--data '{
    "name": "Sucursal Sur"
}'
```
### Agregar producto a sucursal
**POST**
```http
/franchises/{franchiseName}/branches/{branchName}/products
```
```body
{
  "name": "Hamburguesa",
  "stock": 200
}
```
```curl
curl --location 'localhost:8080/v1/franchises/Franquicia 2/branches/Sucursal Sur/products' \
--header 'Content-Type: application/json' \
--data '{
  "name": "Hamburguesa",
  "stock": 200
}'
```
### Actualizar nombre de producto
**PATCH** 
```http
/franchises/{franchiseName}/branches/{branchName}/products/{productName}/update/name
```
```body
{
    "name":"Perro caliente"
}
```
```curl
curl --location --request PATCH 'localhost:8080/v1/franchises/Franquicia 2/branches/Sucursal Sur/products/Hamburguesa/update/name' \
--header 'Content-Type: application/json' \
--data '{
    "name":"Perro caliente"
}'
```
### Eliminar producto de sucursal
**DELETE** 
```http
/franchises/{franchiseName}/branches/{branchName}/products/{productName}
```
```curl
curl --location --request DELETE 'localhost:8080/v1/franchises/Franquicia 2/branches/Sucursal Sur/products/Perro caliente'
```
### Actualizar stock de producto
**PATCH** 

```http
/franchises/{franchiseName}/branches/{branchName}/products/{productName}/stock/{stock}
```
```curl
curl --location --request PATCH 'localhost:8080/v1/franchises/Franquicia 2/branches/Sucursal Norte/products/Hamburguesa/stock/3'
```
### Producto con mayor stock por sucursal
**GET** 
```http
/franchises/{franchiseName}/branches/products/top
```
```curl 
curl --location 'localhost:8080/v1/franchises/Franquicia 2/branches/products/top'
```
## Respuestas
- `201 Created`
- `204 No Content`
- `200 OK`
- `404 Not Found`
