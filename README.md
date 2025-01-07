# Micro Service with Spring Boot + Spring Scheduler, MySQL via Docker, Spring Mail and Design Pattern

This project is a communication platform inspired by a backend challenge from Magalu at the youtube Channel Build & Run.
It features a RESTful API built using Java and Spring Boot, with MySQL as the database for data persistence.

The project showcases modern backend development practices, focusing on scalability, maintainability, and performance. It serves as a practical implementation of a real-world backend system.





## Usage/Examples

The first step of the project involves setting up MySQL using Docker. This is achieved by configuring the docker-compose.yml file and running the docker-compose up command in the terminal to start the database.

Here's a reference to help you with that step : 
- <https://spring.io/guides/gs/accessing-data-mysql>

Go to part of Preparing to Build the Application and follow the steps

Ensure Docker is installed on your machine before proceeding with this step.

```bash
docker compose up
```
After setting up the database, the next step is to configure the communication between Spring Boot and MySQL. This involves defining the necessary connection properties in the application.properties or application.yml file, such as the database URL, username, password, and driver class name.

Once the entire API is built, the next step is to implement automatic scheduling and notifications using Spring Scheduler. This will involve configuring scheduled tasks to trigger at specific intervals and integrating the notification system to send alerts based on predefined conditions.

### Enable Scheduling

```java
@SpringBootApplication
@EnableScheduling
public class MagalumsApplication {
```
This annotation enables Spring’s scheduled task execution capability.

### Create a Scheduled Task

```java
@Component
public class MagaluTaskScheduler {
    
    private static final Logger logger = LoggerFactory.getLogger(MagaluTaskScheduler.class); 

    private final NotificationService notificationService;

    public MagaluTaskScheduler(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Scheduled(fixedDelay = 1L , timeUnit = TimeUnit.MINUTES)
    public void checkTasks(){
        var dateTime = LocalDateTime.now();
        logger.info("Task executed at: {}", dateTime);
        notificationService.checkAndSend(dateTime);
    }
}
```
The Scheduled annotation defines when a particular method runs,
in this project, **fixedDelay** is used to schedule tasks. This specifies the interval between method invocations, measured from the start time of each invocation. Additionally, you can define the desired **TimeUnit** to customize the scheduling behavior according to your requirements.

Here's a reference to help you with that step : 
- <https://spring.io/guides/gs/scheduling-tasks>

Up to this point in the project, we have covered the full scope of the proposed challenge. However, I added a few additional features to make the application more complete and functional.

### Design Pattern - Strategy

#### Concept

Strategy is a behavioral design pattern that lets you define a family of algorithms, put each of them into a separate class, and make their objects interchangeable. 

### Implementation of the Strategy Pattern

The **NotificationStrategy** interface and its implementation in the **checkAndSend** method demonstrate the use of the Strategy Pattern to handle the process of sending notifications based on the notification channel dynamically.

#### Purpose

The Strategy Pattern is used here to define a family of algorithms (in this case, notification sending strategies for different channels like email, SMS, etc.), encapsulate each algorithm, and make them interchangeable without modifying the code that uses them.

#### How It Works
- Interface Definition:

The NotificationStrategy interface defines a common method, sendNotification(Notification notification), which all strategies must implement. Each strategy represents the logic for sending a notification via a specific channel (e.g., Email, SMS, Push).

- Dynamic Strategy Mapping:

In the **checkAndSend** method, a **mapStrategy** object is used to map each notification channel (Channel) to its corresponding strategy. This allows the logic for sending notifications to be dynamically selected at runtime based on the notification's channel.

- Dynamic Invocation:

Notifications are fetched from the database using **notificationRepository.findByStatusInAndDateTimeBefore**, filtering by pending or errored notifications that are scheduled to be sent before the specified dateTime.
If no notifications meet the criteria, the method exits early.
For each eligible notification, the appropriate strategy is retrieved from the mapStrategy and executed by calling **sendNotification(n)**.

### Advantages
**Open/Closed Principle**: The system is open for extension but closed for modification. New notification channels can be added as new strategies without altering the existing code.

**Separation of Concerns**: Each channel's notification logic is encapsulated in its own class, keeping the main service clean and maintainable.

**Flexibility**: Strategies can be changed or updated independently without affecting the rest of the application.

#### Learn More About Design Patterns
If you're interested in exploring design patterns and their practical applications in software development, visit the following link:
- <https://refactoring.guru/design-patterns>

This resource provides in-depth explanations, examples, and visualizations of various design patterns, making it an excellent reference for developers.


### Implementation of Notification Sending Logic

For the notification system, I have implemented the sending logic for email notifications using Spring Email. This includes configuring the necessary email properties, such as the SMTP server, port, and authentication details, to ensure reliable and secure email delivery.

The email notification logic is encapsulated in a specific strategy class that adheres to the NotificationStrategy interface. This class handles the construction and sending of email notifications dynamically when the system processes email-based notifications.

#### How Spring Email Works
Spring Email provides an abstraction over JavaMail, simplifying the process of sending email messages in Spring-based applications. It allows you to send emails with minimal configuration while supporting features like plain text, HTML content, and attachments.

#### Sending a Simple Email
To send a basic email using Spring Email, the key component is the **JavaMailSender** interface. A simple email contains fields like the recipient, subject, and body, which can be configured using **SimpleMailMessage**.

#### Learn More About Spring Email
For a more detailed explanation of how to use Spring Email, including advanced features like sending HTML emails or emails with attachments, visit the following link:
- <https://refactoring.guru/design-patterns>

This resource provides comprehensive examples and insights into configuring and utilizing Spring Email in various use cases.

### Future Work
While the email notification logic is fully implemented, the logic for other notification channels (e.g., SMS, Push, WhatsApp) has not yet been developed. These will follow the same design pattern and can be easily integrated by creating corresponding strategy classes for each channel.


## Authors

- [@VitorCostaVianna](https://github.com/VitorCostaVianna)

## License

[MIT](https://choosealicense.com/licenses/mit/)
