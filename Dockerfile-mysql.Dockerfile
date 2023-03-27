# Use official MySQL 8.0 image as a parent image
FROM mysql:8.0

# Set the root password for MySQL

ENV MYSQL_ROOT_PASSWORD=yourpassword
ENV MYSQL_USER=user
# Create a database and user for the Blockbuster simulation
ENV MYSQL_DATABASE=movie_database

# Copy the SQL script to initialize the database


# Expose port 3306 for MySQL
EXPOSE 3306