# Franquicias API – Spring Boot

### 1. Resumen Stack
-Java 21
-Spring Boot 4
-Maven
-MongoDB
-Docker + Docker Compose

### 2. Ejecucion Local
Clonar proyecto

```bash
git clone https://github.com/Smejia11/franchise-api.git
```
Entra a la carpeta del proyecto:

```bash
cd franchise-api
```
Ejecutar docker compose
```bash
docker compose up --build
```
### 3. Endpoints (RESTful)
**URL-BASE**
```md
localhost:8080/v1
```
###  Crear franquicia
**POST**
```md
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
```md
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
```md
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
```md
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
```md
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
```md
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
```md
/franchises/{franchiseName}/branches/{branchName}/products/{productName}
```
```curl
curl --location --request DELETE 'localhost:8080/v1/franchises/Franquicia 2/branches/Sucursal Sur/products/Perro caliente'
```
### Actualizar stock de producto
**PATCH** 
```md
/franchises/{franchiseName}/branches/{branchName}/products/{productName}/stock/{stock}
```
```curl
curl --location --request PATCH 'localhost:8080/v1/franchises/Franquicia 2/branches/Sucursal Norte/products/Hamburguesa/stock/3'
```
### Producto con mayor stock por sucursal
**GET** 
```md
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


### Ejemplo de response para Producto con mayor stock por sucursal
- `200 OK`
---

| Field   | Type   | Description                                      |
|--------|--------|--------------------------------------------------|
| branch | string | Nombre de la sucursal a la que pertenece el producto |
| product| string | Nombre del producto con mayor stock              |
| stock  | number | Cantidad de stock disponible del producto        |

#### Example Response
```json
[
  {
    "branch": "Sucursal Norte",
    "product": "Perros",
    "stock": 800
  }
]
```

## TEST – Criterios de Aceptación

---

### 2. Exponer endpoint para agregar una nueva franquicia

![Endpoint franquicia](https://github.com/user-attachments/assets/a5ed11b9-5dfb-4a65-b64a-02387dd474dd)

#### MongoDB
![MongoDB franquicia](https://github.com/user-attachments/assets/2c86b1e2-9810-4792-898e-35c565d8a79f)

---

### 3. Exponer endpoint para agregar una nueva sucursal a una franquicia

![Endpoint sucursal](https://github.com/user-attachments/assets/c21bc5e6-f3e2-4c63-b7b6-9edcbbfba996)

#### MongoDB
![MongoDB sucursal](https://github.com/user-attachments/assets/7a1623d9-2b31-4dbf-92d6-9cb87fae8bb6)

---

### 4. Exponer endpoint para agregar un nuevo producto a una sucursal

![Endpoint producto](https://github.com/user-attachments/assets/90553460-b93c-45d4-9901-1e08593e0a68)

#### MongoDB
![MongoDB producto](https://github.com/user-attachments/assets/7e6d03f3-7474-4b40-ae17-717e6179d1d0)

---

### 5. Exponer endpoint para eliminar un producto de una sucursal

![Endpoint delete producto](https://github.com/user-attachments/assets/713d52b9-4962-4fb7-9ede-d77f08f401b2)

#### MongoDB
![MongoDB delete producto](https://github.com/user-attachments/assets/7e485a40-c038-4ede-9e89-9bad3289e2be)

---

### 6. Exponer endpoint para modificar el stock de un producto

![Endpoint update stock](https://github.com/user-attachments/assets/3f005a47-83b2-4624-a1e4-feebcc83a006)

#### MongoDB
![MongoDB update stock](https://github.com/user-attachments/assets/467f32f3-495b-4b1f-a291-cac803f7f869)

---

### 7. Producto con mayor stock por sucursal para una franquicia

![Endpoint top stock](https://github.com/user-attachments/assets/a8db482e-a350-472a-8f7d-fdeb6a889920)

#### MongoDB

![MongoDB top stock](https://github.com/user-attachments/assets/2827bf80-3711-4eb7-ab87-f532a1b394a9)

---

### Plus: Endpoint para actualizar el nombre de una franquicia

#### Endpoint
![Update franchise name endpoint](https://github.com/user-attachments/assets/0c0c8e51-a693-4c00-9ed1-419570c759bf)

#### MongoDB
![MongoDB update franchise name](https://github.com/user-attachments/assets/ce2936b0-140e-40c8-b6a1-9a7aaba541c9)

### Plus: Endpoint para actualizar el nombre de una sucursal

#### Endpoint
![Update branch name endpoint](https://github.com/user-attachments/assets/a352dbfd-399e-4ffe-813f-875277b28a0d)

#### MongoDB
![MongoDB update branch name](https://github.com/user-attachments/assets/63026fa5-21fc-4979-a48f-f9b2210228fa)


### Plus: Endpoint para actualizar el nombre de un producto

#### Endpoint
![Update product name endpoint](https://github.com/user-attachments/assets/a8d92fdd-79c0-45b4-afe5-2b7f2df695b2)

#### MongoDB
![MongoDB update product name](https://github.com/user-attachments/assets/a6281cd6-614c-4f07-b1d2-79efbd001947)




