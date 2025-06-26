# Prescription Management System - Frontend

## Overview

This project is the frontend part of the Prescription Management System, designed to provide a userEntity-friendly interface for managing prescriptions and drugs. It is built using Vue 3 and Vite, ensuring a modern and efficient development experience.

## Features

- **User Authentication**: Secure login and registration process with role-based access control.
- **Prescription Management**: Create, edit, view, and delete prescriptions with associated drugs and images.
- **Drug Management**: Manage drug information, including adding new drugs and editing existing ones.
- **Image Uploading**: Support for uploading multiple images for prescriptions and drugs with real-time previews.
- **Responsive Design**: A clean and responsive UI that works on various devices.

## Project Structure

```
frontend
├── src
│   ├── api               # API service files
│   ├── assets            # Static assets (images, styles)
│   ├── components        # Vue components
│   ├── router            # Vue Router configuration
│   ├── store             # Vuex store for state management
│   ├── views             # Vue views for different pages
│   ├── App.vue           # Root component
│   └── main.js           # Entry point of the application
├── public
│   └── index.html        # Main HTML file
├── package.json          # NPM configuration
└── vite.config.js        # Vite configuration
```

## Getting Started

1. **Clone the repository**:
   ```
   git clone <repository-url>
   cd prescription-management-system/frontend
   ```

2. **Install dependencies**:
   ```
   npm install
   ```

3. **Run the application**:
   ```
   npm run dev
   ```

4. **Open your browser** and navigate to `http://localhost:3000` to view the application.

## Contributing

Contributions are welcome! Please open an issue or submit a pull request for any improvements or bug fixes.

## License

This project is licensed under the MIT License. See the LICENSE file for more details.