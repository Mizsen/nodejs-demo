const express = require('express');
const cors = require('cors');
const bodyParser = require('body-parser');

const app = express();
const PORT = 3000;

app.use(cors());
app.use(bodyParser.json());

let blogs = [];
let users = [];

// Mock user registration
app.post('/api/register', (req, res) => {
    const { username, password } = req.body;
    const newUser = { id: users.length + 1, username, password };
    users.push(newUser);
    res.status(201).json(newUser);
});

// Mock user login
app.post('/api/login', (req, res) => {
    const { username, password } = req.body;
    const user = users.find(u => u.username === username && u.password === password);
    if (user) {
        res.json({ message: 'Login successful', user });
    } else {
        res.status(401).json({ message: 'Invalid credentials' });
    }
});

// Mock get all blogs
app.get('/api/blogs', (req, res) => {
    res.json(blogs);
});

// Mock create a blog
app.post('/api/blogs', (req, res) => {
    const { title, content, author } = req.body;
    const newBlog = { id: blogs.length + 1, title, content, author };
    blogs.push(newBlog);
    res.status(201).json(newBlog);
});

// Mock edit a blog
app.put('/api/blogs/:id', (req, res) => {
    const { id } = req.params;
    const { title, content } = req.body;
    const blog = blogs.find(b => b.id === parseInt(id));
    if (blog) {
        blog.title = title;
        blog.content = content;
        res.json(blog);
    } else {
        res.status(404).json({ message: 'Blog not found' });
    }
});

// Mock delete a blog
app.delete('/api/blogs/:id', (req, res) => {
    const { id } = req.params;
    blogs = blogs.filter(b => b.id !== parseInt(id));
    res.status(204).send();
});

// Mock get comments for a blog
app.get('/api/blogs/:id/comments', (req, res) => {
    const { id } = req.params;
    const blogComments = comments.filter(c => c.blogId === parseInt(id));
    res.json(blogComments);
});

// Mock post a comment
app.post('/api/blogs/:id/comments', (req, res) => {
    const { id } = req.params;
    const { content, author } = req.body;
    const newComment = { id: comments.length + 1, blogId: parseInt(id), content, author };
    comments.push(newComment);
    res.status(201).json(newComment);
});

app.listen(PORT, () => {
    console.log(`Mock server is running on http://localhost:${PORT}`);
});