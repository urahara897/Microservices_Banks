# Microservices Banking Application

A modern, cloud-native banking application built using microservices architecture with Spring Boot, Spring Cloud, and Kubernetes support.

## Architecture Overview

The application is composed of several microservices:

### Core Services

1. **Accounts Service** (Port: 8080)

   - Core banking account management
   - Customer profile handling
   - OpenFeign client integration for inter-service communication
   - Kafka integration for event streaming

2. **Loans Service** (Port: 8090)

   - Loan account management
   - Resilience4j retry mechanism
   - Rate limiting implementation

3. **Cards Service** (Port: 9000)
   - Credit/Debit card management
   - Rate limiting with Redis
   - Circuit breaker implementation

### Infrastructure Services

4. **Config Server** (Port: 8071)

   - Centralized configuration management
   - Environment-specific configurations
   - Git-backed configuration storage

5. **Gateway Server** (Port: 8072)

   - Spring Cloud Gateway implementation
   - Route configurations for all microservices
   - Circuit breaker patterns
   - Rate limiting
   - Path rewriting

6. **Eureka Server** (Port: 8070)
   - Service registration and discovery
   - Load balancing support

## Technical Stack

### Core Technologies

- Java 17/21
- Spring Boot 3.4.x
- Spring Cloud 2024.0.0
- Spring Cloud Config
- Spring Cloud Netflix Eureka
- Spring Cloud Gateway
- Spring Cloud OpenFeign
- Spring Cloud Stream with Kafka

### Service Discovery & Load Balancing

1. **Client-Side Service Discovery (Netflix Eureka)**

   - Each microservice registers itself with Eureka Server
   - Services discover and communicate with each other through Eureka
   - No need for hardcoded URLs or service locations
   - Dynamic service registration and de-registration
   - Health monitoring and self-healing

2. **Benefits of Client-Side Discovery**

   - Reduced dependency on external load balancers
   - Better resilience and fault tolerance
   - Runtime service discovery
   - Automatic load balancing
   - Service health monitoring

3. **Implementation Details**

   - Eureka Server (Port: 8070)
   - Service Registration:
     ```yaml
     eureka:
       instance:
         preferIpAddress: true
       client:
         fetchRegistry: true
         registerWithEureka: true
         serviceUrl:
           defaultZone: http://localhost:8070/eureka/
     ```
   - Client-side load balancing with Spring Cloud LoadBalancer
   - Integration with Spring Cloud Gateway for routing

4. **Service Communication**

   - OpenFeign clients for inter-service communication
   - Load-balanced service calls
   - Circuit breaker integration
   - Retry mechanisms
   - Example service call:
     ```java
     @FeignClient(name = "loans", fallback = LoansFallback.class)
     public interface LoansClient {
         @GetMapping("/api/fetch")
         LoansDto fetchLoanDetails(@RequestParam String mobileNumber);
     }
     ```

5. **High Availability**
   - Multiple Eureka instances possible
   - Peer-to-peer replication
   - Service redundancy
   - Automatic failover

### Database

- MySQL (Separate instances for each service)
  - accountsdb (Port: 3306)
  - loansdb (Port: 3307)
  - cardsdb (Port: 3308)

### Monitoring & Observability Stack

1. **Grafana Stack**

   - Grafana (Metrics Visualization)
   - Loki (Log Aggregation)
   - Tempo (Distributed Tracing)
   - Prometheus (Metrics Collection)

2. **Metrics Collection**

   - Spring Boot Actuator endpoints
   - Micrometer integration
   - Custom metrics support
   - Application and JVM metrics

3. **Distributed Tracing**

   - OpenTelemetry integration
   - Trace correlation
   - Span management
   - Service dependency mapping

4. **Logging**
   - Centralized logging with Loki
   - Structured logging format
   - Log correlation with traces
   - Environment-specific log levels

### Monitoring Stack Setup

1. **Grafana**

   - Default Port: 3000
   - Dashboards for:
     - Service metrics
     - JVM metrics
     - Business metrics
     - Trace visualization

2. **Prometheus**

   - Default Port: 9090
   - Scrape configurations for:
     - All microservices
     - Infrastructure metrics
     - Custom metrics

3. **Loki**

   - Log aggregation
   - Log querying
   - Label-based log filtering
   - Integration with Grafana

4. **Tempo**
   - Trace data storage
   - Trace visualization
   - Service graph
   - Span analytics

### OpenTelemetry Configuration

yaml
OTEL_EXPORTER_OTLP_ENDPOINT: http://tempo:4318
OTEL_EXPORTER_OTLP_PROTOCOL: http/protobuf
OTEL_TRACES_SAMPLER: parentbased_always_on
JAVA_TOOL_OPTIONS: -javaagent:/app/libs/opentelemetry-javaagent-1.27.0.jar

### Monitoring Access URLs

- Grafana: http://localhost:3000
- Prometheus: http://localhost:9090
- Tempo: http://localhost:3200
- Loki: http://localhost:3100

### Dashboard Access

1. **Default Credentials**

   - Username: admin
   - Password: admin (change on first login)

2. **Available Dashboards**
   - Microservices Overview
   - Service Performance
   - JVM Metrics
   - Business Metrics
   - Trace Analysis
   - Log Analysis

### Security & Resilience

- Resilience4j
- Circuit Breaker
- Rate Limiting
- Retry Mechanism
- Redis for rate limiting

### DevOps & Deployment

- Docker with JIB plugin
- Kubernetes deployment support
- Helm charts for K8s deployment
- Docker Compose for local development

## Getting Started

### Prerequisites

- JDK 17 or later
- Maven 3.6+
- Docker
- Kubernetes cluster (for K8s deployment)
- MySQL

### Building Services

bash
mvn clean install

### Building Docker Images

bash
mvn clean install jib:build

### Running with Docker Compose

bash
docker-compose up -d

### Deploying to Kubernetes

For development environment

cd helm/environments/dev-env
helm install microservices-bank .

For production environment

cd helm/environments/prod-env
helm install microservices-bank .

For QA environment
cd helm/environments/qa-env
helm install microservices-bank .

Each environment can have its own values and configurations while reusing the same base charts from `microservices-bank-services`. The environment-specific Charts (`dev-env` and `prod-env`) pull in all the required dependencies from:

- microservices-bank-common
- Individual service charts (configserver, eurekaserver, accounts, cards, loans, gatewayserver, message)

## Service URLs

- Accounts Service: http://localhost:8080
- Loans Service: http://localhost:8090
- Cards Service: http://localhost:9000
- Config Server: http://localhost:8071
- Gateway Server: http://localhost:8072
- Eureka Server: http://localhost:8070

## API Documentation

Each service has Swagger UI documentation available at:
`http://{service-host}:{service-port}/swagger-ui.html`

## Configuration

The application uses a hierarchical configuration system:

1. Default configurations in application.yml
2. Environment-specific configurations in Config Server
3. Docker environment overrides
4. Kubernetes ConfigMaps

## Monitoring

- Health checks: `/actuator/health`
- Metrics: `/actuator/metrics`
- Prometheus endpoints: `/actuator/prometheus`

## Docker Support

The project uses Google's JIB plugin for creating optimized Docker images.
