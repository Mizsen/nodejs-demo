# Blog System Frontend

This is the frontend part of the Blog System project built using Vue 3. The application allows users to register, log in, and manage their blogs. Below are the key features and components of the frontend.

## Features

- **User Authentication**: Users can register and log in to their accounts.
- **Personal Homepage**: Each user has a personal homepage displaying their published blogs.
- **Blog Management**: Users can create, edit, and delete their blog posts.
- **Blog List**: The main page displays a list of all blogs with options to view details.
- **Blog Details**: Users can view the content of a blog post along with comments.
- **Commenting**: Users can leave comments on blog posts.

## Project Structure

- **src/assets**: Contains static assets such as images and stylesheets.
- **src/components**: Contains reusable Vue components:
  - `BlogList.vue`: Displays a list of blogs.
  - `BlogEditor.vue`: Form for creating and editing blog posts.
  - `BlogDetail.vue`: Shows details of a specific blog post.
  - `CommentSection.vue`: Handles comments for blog posts.
  - `Login.vue`: User login form.
  - `Register.vue`: User registration form.
  - `UserProfile.vue`: Displays user profile and their blogs.
  
- **src/views**: Contains view components:
  - `Home.vue`: Main landing page.
  - `PersonalHome.vue`: User's personal homepage.
  - `NotFound.vue`: Displays when a route is not found.

- **src/router**: Contains routing configuration.
- **src/store**: Contains Vuex store for state management.
- **src/App.vue**: Root component of the application.
- **src/main.ts**: Entry point of the Vue application.

## Installation

1. Clone the repository.
2. Navigate to the `frontend` directory.
3. Install dependencies:
   ```
   npm install
   ```
4. Run the application:
   ```
   npm run serve
   ```

## Contributing

Feel free to submit issues or pull requests for improvements or bug fixes. 

## License

This project is licensed under the MIT License.