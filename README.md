# Cab Booking App

## Overview
The **Cab Booking App** is a backend application designed for ride-hailing services. It is built with **Spring Boot** and integrates **OSRM (Open Source Routing Machine)** for efficient route calculations. The app provides a robust authentication and authorization mechanism, ride management, driver onboarding, and a strategy-based system for payments and driver selection.

## Tech Stack
- **Backend**: Spring Boot, Hibernate, JPA
- **Database**: PostgreSQL with PostGIS
- **API Documentation**: Swagger
- **Authentication & Authorization**: JWT (Access Token & Refresh Token)
- **Hosting & Deployment**: AWS (Elastic Beanstalk, CodePipeline, RDS, EC2)
- **Routing**: OSRM (Open Source Routing Machine)
- **Java Version**: Java 21 (Corretto 21 for AWS)

## Features
### **Authentication & Authorization**
- Secure login and registration
- JWT-based authentication (Access & Refresh Tokens)
- Role-based access control

### **Ride Management**
- Riders can request rides
- Drivers can **accept rides, start rides with stops, end rides, and cancel rides**
- Riders and drivers can **rate each other** after ride completion

### **Driver Management**
- Admin can onboard new drivers
- The app automatically selects drivers based on:
  - Nearest driver
  - Nearest high-rated driver
- **PostGIS and Geometry Utils** are used for optimized driver selection

### **Fare Management**
- Default fare calculation
- Surge pricing based on demand
- Managed through a strategy-based system

### **Global Error Handling**
- Centralized error handling for consistency

### **Environments**
- Development and Production environments are supported

## Installation & Setup
### **1. Clone the Repository**
```sh
 git clone https://github.com/ProfessorXcommunity/CabBookingApplication.git
 cd CabBookingApplication
```

### **2. Configure the Database**
Ensure PostgreSQL is installed and configure the connection in `application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://your-db-host:5432/cab_booking
spring.datasource.username=your-username
spring.datasource.password=your-password
spring.jpa.hibernate.ddl-auto=update
```

### **3. Run the Application**
```sh
mvn spring-boot:run
```

## API Documentation
The API documentation is available at:
```
http://car-booking-app.ap-south-1.elasticbeanstalk.com/swagger-ui/index.html#/
```

## Deployment (AWS)
### **AWS Services Used**
- **Elastic Beanstalk**: Hosts the application
- **RDS (PostgreSQL)**: Manages the database
- **EC2**: Runs additional backend services
- **CodePipeline**: Automates CI/CD

### **Deployment Steps**
1. Package the application:
   ```sh
   mvn clean package
   ```
2. Deploy to AWS Elastic Beanstalk:
   ```sh
   eb init -p java-21 cab-booking-app
   eb create cab-booking-env
   ```

## API Endpoints

### **Rider Controller**
| Endpoint                          | Method | Description |
|-----------------------------------|--------|-------------|
| `/rider/requestRide`              | POST   | Rider requests a ride |
| `/rider/rateDriver`               | POST   | Rider rates a driver |
| `/rider/cancelRide/{rideId}`      | POST   | Rider cancels a ride |
| `/rider/getMyRides`               | GET    | Get rider's ride history |
| `/rider/getMyProfile`             | GET    | Get rider's profile |

### **Driver Controller**
| Endpoint                          | Method | Description |
|-----------------------------------|--------|-------------|
| `/driver/startRide/{rideRequestId}` | POST   | Driver starts a ride |
| `/driver/rateRider`               | POST   | Driver rates a rider |
| `/driver/endRide/{rideId}`        | POST   | Driver ends a ride |
| `/driver/cancelRide/{rideId}`     | POST   | Driver cancels a ride |
| `/driver/acceptRide/{rideRequestId}` | POST   | Driver accepts a ride request |
| `/driver/getMyRides`              | GET    | Get driver's ride history |
| `/driver/getMyProfile`            | GET    | Get driver's profile |

### **Auth Controller**
| Endpoint                          | Method | Description |
|-----------------------------------|--------|-------------|
| `/auth/signup`                    | POST   | User signup |
| `/auth/refresh`                    | POST   | Refresh authentication token |
| `/auth/onBoardNewDriver/{userId}`  | POST   | Admin onboards a new driver |
| `/auth/login`                      | POST   | User login |

### **Health Check Controller**
| Endpoint | Method | Description |
|----------|--------|-------------|
| `/`      | GET    | Health check endpoint |

## Contribution
Contributions are welcome! Please follow the standard pull request process.

