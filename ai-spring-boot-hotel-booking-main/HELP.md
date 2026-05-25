# Help & Support - Hotel Booking System

## 🚨 Common Issues & Solutions

### Database Connection Issues

**Problem:** `Communications link failure` or `Access denied for user`
```
Error: com.mysql.cj.jdbc.exceptions.CommunicationsException: Communications link failure
```

**Solutions:**
1. **Check MySQL is running:**
   ```bash
   # Windows
   net start mysql

   # Linux/Mac
   sudo systemctl start mysql
   ```

2. **Verify credentials in `application.properties`:**
   ```properties
   spring.datasource.username=root
   spring.datasource.password=your_actual_password
   ```

3. **Test connection manually:**
   ```bash
   mysql -u root -p -h localhost
   ```

4. **Check database exists:**
   ```sql
   CREATE DATABASE IF NOT EXISTS hotel_booking_db;
   ```

### Port Already in Use

**Problem:** `Port 8080 is already in use`

**Solutions:**
1. **Change port in `application.properties`:**
   ```properties
   server.port=8081
   ```

2. **Find and kill process:**
   ```bash
   # Windows
   netstat -ano | findstr :8080
   taskkill /PID <PID> /F

   # Linux/Mac
   lsof -i :8080
   kill -9 <PID>
   ```

### Maven Build Failures

**Problem:** `Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin`

**Solutions:**
1. **Clear Maven cache:**
   ```bash
   mvn clean
   rm -rf ~/.m2/repository
   ```

2. **Check Java version:**
   ```bash
   java -version  # Should be 21+
   ```

3. **Rebuild:**
   ```bash
   mvn clean install -U
   ```

### Swagger UI Not Loading

**Problem:** Blank page or `Failed to load swagger-ui`

**Solutions:**
1. **Wait for full startup** - Application needs time to initialize
2. **Check URL:** `http://localhost:8080/swagger-ui.html`
3. **Clear browser cache** (Ctrl+Shift+R)
4. **Check logs** for OpenAPI errors

### Validation Errors

**Problem:** `400 Bad Request` with validation messages

**Common Issues:**
- **Empty required fields**
- **Invalid email format** (if added later)
- **Negative prices**
- **Past dates** (if validation added)

**Solution:** Check request body matches API documentation

---

## 📞 Getting Help

### 1. Check Documentation First
- **README.md** - Quick start and overview
- **GETTING_STARTED.md** - Detailed setup guide
- **API_DOCUMENTATION.md** - Complete API reference
- **ARCHITECTURE.md** - System design
- **QUICK_REFERENCE.md** - Fast lookups

### 2. Review Logs
```bash
# Application logs show detailed error information
# Look for:
# - Spring Boot startup messages
# - Database connection status
# - Hibernate DDL generation
# - Error stack traces
```

### 3. Test Step by Step

#### Database Setup Test
```bash
# 1. Connect to MySQL
mysql -u root -p

# 2. Create database
CREATE DATABASE hotel_booking_db;

# 3. Verify
SHOW DATABASES;
```

#### Application Startup Test
```bash
# 1. Build project
mvn clean install

# 2. Start application
mvn spring-boot:run

# 3. Check logs for "Started AiSpringBootHotelBookingApplication"
```

#### API Test
```bash
# 1. Check health
curl http://localhost:8080/api-docs

# 2. Test hotel creation
curl -X POST http://localhost:8080/api/v1/hotels \
  -H "Content-Type: application/json" \
  -d '{"name":"Test Hotel","location":"Test City"}'
```

### 4. Common Error Patterns

#### Error: `Table 'hotel_booking_db.hotels' doesn't exist`
- **Cause:** Database not created or wrong database name
- **Fix:** Create database and restart application

#### Error: `Access denied for user 'root'@'localhost'`
- **Cause:** Wrong MySQL credentials
- **Fix:** Update `application.properties` with correct password

#### Error: `Port 8080 is already in use`
- **Cause:** Another application using the port
- **Fix:** Change port or stop other application

#### Error: `Failed to determine a suitable driver class`
- **Cause:** MySQL driver not found
- **Fix:** Check Maven dependencies in `pom.xml`

---

## 🔧 Configuration Troubleshooting

### application.properties Issues

**Wrong database URL:**
```properties
# Wrong
spring.datasource.url=jdbc:mysql://localhost/hotel_booking_db

# Correct
spring.datasource.url=jdbc:mysql://localhost:3306/hotel_booking_db?useSSL=false&serverTimezone=UTC
```

**Missing MySQL driver:**
```xml
<!-- Add to pom.xml if missing -->
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <version>8.0.33</version>
</dependency>
```

### JPA/Hibernate Issues

**Tables not created:**
```properties
# Ensure this is set
spring.jpa.hibernate.ddl-auto=update
```

**SQL logging:**
```properties
# Add for debugging
spring.jpa.show-sql=true
logging.level.org.hibernate.SQL=DEBUG
```

---

## 🐛 Debugging Steps

### 1. Enable Debug Logging
Add to `application.properties`:
```properties
logging.level.root=DEBUG
logging.level.com.dipdeveloper.ai_spring_boot_hotel_booking=DEBUG
logging.level.org.springframework.web=DEBUG
logging.level.org.hibernate=DEBUG
```

### 2. Check Application Health
```bash
# Check if application is responding
curl http://localhost:8080/api-docs

# Check database connection
curl http://localhost:8080/api/v1/hotels
```

### 3. Database Debugging
```sql
-- Check tables created
USE hotel_booking_db;
SHOW TABLES;

-- Check table structure
DESCRIBE hotels;
DESCRIBE rooms;
DESCRIBE bookings;

-- Check data
SELECT * FROM hotels;
SELECT * FROM rooms;
SELECT * FROM bookings;
```

### 4. API Debugging
```bash
# Test with verbose output
curl -v -X POST http://localhost:8080/api/v1/hotels \
  -H "Content-Type: application/json" \
  -d '{"name":"Test","location":"Test"}'
```

---

## 📋 Checklist for Issues

### Before Reporting an Issue
- [ ] MySQL is running
- [ ] Database `hotel_booking_db` exists
- [ ] `application.properties` has correct credentials
- [ ] Java 21+ is installed
- [ ] Maven 3.6+ is installed
- [ ] Port 8080 is free
- [ ] Project builds successfully (`mvn clean install`)
- [ ] Application starts without errors

### When Reporting Issues
**Include:**
- Operating System and version
- Java version (`java -version`)
- Maven version (`mvn -version`)
- MySQL version
- Full error message and stack trace
- Steps to reproduce
- `application.properties` (without passwords)

---

## 🚀 Advanced Help

### Extending the Application

**Adding new endpoints:**
1. Add method to Controller
2. Add method to Service interface
3. Implement in ServiceImpl
4. Update DTOs if needed
5. Update documentation

**Adding validation:**
1. Add annotations to DTOs
2. Add custom validation in Service layer
3. Update exception handling

### Performance Tuning

**Database optimization:**
```properties
# Add to application.properties
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.use_sql_comments=true
```

**Connection pooling:**
```properties
spring.datasource.hikari.maximum-pool-size=10
spring.datasource.hikari.minimum-idle=5
```

---

## 📞 Contact & Support

For additional help:
1. Review all documentation files
2. Check application logs
3. Test with Swagger UI
4. Verify database setup

**Remember:** Most issues are related to database configuration or port conflicts.

---

**Last Updated:** 2026-03-16
