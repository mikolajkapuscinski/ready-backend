# Read-y
A comprehensive book tracking application with AI-powered recommendations and cover recognition capabilities. Read-y helps users manage their reading habits, discover new books, and engage with a community of readers.

## Features
- Reading Habit Tracking: Log and monitor your reading progress, set goals, and track your reading statistics
- Book Discovery: Find new books tailored to your preferences and reading history
- AI-Powered Book Recommendations: Get personalized book suggestions based on your reading history and preferences
- Cover Recognition System: Identify books by simply uploading a photo of the cover
- User Authentication: Secure JWT authentication with Google OAuth2 integration
- Responsive Design: Seamless experience across all devices

## Technology Stack
### Backend

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)
![ChatGPT](https://img.shields.io/badge/chatGPT-74aa9c?style=for-the-badge&logo=openai&logoColor=white)
- Java - Core programming language
- Spring Boot - Application framework
- Spring Security - Authentication and authorization
- Spring AI - AI integration for book recommendations and cover recognition
- Hibernate - ORM for database operations
- PostgreSQL - Relational database
- JWT - Token-based authentication
- OAuth2 - Integration with Google for authentication

## Frontend
![TypeScript](https://img.shields.io/badge/typescript-%23007ACC.svg?style=for-the-badge&logo=typescript&logoColor=white)
![React](https://img.shields.io/badge/react-%2320232a.svg?style=for-the-badge&logo=react&logoColor=%2361DAFB)
![TailwindCSS](https://img.shields.io/badge/tailwindcss-%2338B2AC.svg?style=for-the-badge&logo=tailwind-css&logoColor=white)
## Infrastructure

AWS S3 - Cloud storage for book covers and user uploads
Docker - Containerization for development and deployment
CI/CD Pipeline - Automated testing and deployment

## Key Technical Implementations
### AI Recommendation System
The recommendation system uses collaborative filtering and content-based algorithms to suggest books based on user preferences, reading history, and similar readers' choices.
### Cover Recognition System
Leveraging computer vision and machine learning techniques, the cover recognition system can identify books from uploaded cover images with high accuracy, even with partial or skewed images.
### Security Implementation
JWT-based authentication for secure API access
OAuth2 integration for streamlined Google authentication
Role-based access control for different user types
Secure storage of sensitive user information

## Future Enhancements

- Mobile applications for iOS and Android
- Integration with e-reader devices
- Social features for reader communities
- Advanced analytics dashboard for reading insights
